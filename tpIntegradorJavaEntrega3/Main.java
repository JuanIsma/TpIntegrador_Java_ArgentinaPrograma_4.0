package tpIntegradorJavaEntrega3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		/***************************************************************************
		                   *LECTURA DE ARCHIVO RESULTADOS *
		 ***************************************************************************/

		Path ruta;
		List<String> lineasArchivo = null;
		String lineaVector[] = new String[4];
		List<Partido> partidos = new ArrayList<Partido>();
		String equipoLocal = "", equipoVisitante = "";
		int rondaId = 0, golesLocal = 0, golesVisitante = 0;
		ResultadoEnum resultado = null;

		try {
			ruta = Paths.get("src\\tpIntegrador\\resultados.csv");
			lineasArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		boolean primera = true;
		
		//Recorro el Archivo de Resultados
		for (String linea : lineasArchivo) {
			if (primera) {
				primera = false;
			} else {
				lineaVector = linea.split(";"); // guardo los valores en un vector[]
				// System.out.println(linea); // IMPRIME DE PRUEBA
				equipoLocal = lineaVector[2];
				equipoVisitante = lineaVector[5];
				Equipo equipo1 = new Equipo(equipoLocal);
				Equipo equipo2 = new Equipo(equipoVisitante);
				Partido partido = new Partido(equipo1, equipo2);

				try {
					golesLocal = Integer.parseInt(lineaVector[3]);
					golesVisitante = Integer.parseInt(lineaVector[4]);
					partido.setGolesEquipo1(golesLocal);
					partido.setGolesEquipo2(golesVisitante);
					partido.setRondaPartido(rondaId);
					partidos.add(partido);

				} catch (NumberFormatException ex) {
					System.err.println("ERROR:\nEn Goles se recibió una Cadena\n" + ex.getMessage());
					System.exit(1);
				}
			  }
		    }

		// ------------------------------------------------->

		// REVISAMOS LA LISTA DE PARTIDOS !
		/*
		 * for (Partido partidoResutaldo : partidos) {
		 * System.out.println(partidoResutaldo.toString()); }
		 */

		/*************************************************************************************
		                *LECTURA DE ARCHIVO CONFIGURACION DE PTOS*
		 *************************************************************************************/
		Path rutaC;
		List<String> lineasArchivoConfig = null;
		String lineaVectorC[] = new String[4];
		int ptoxPartido = 0, ptoExtraXRonda = 0, PtExtraXFase = 0, extraXRondaAcertada = 0, Total_de_Ronda = 0;
		String User_BD = null, Pass_DB = null;

		try {

			rutaC = Paths.get("src\\tpIntegrador\\configuracion.csv");
			lineasArchivoConfig = Files.readAllLines(rutaC, StandardCharsets.UTF_8);
		} catch (IOException e) {

			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		boolean primeraLinea = true;
		for (String linea : lineasArchivoConfig) {
			if (primeraLinea) {
				primeraLinea = false;
			} else {
				lineaVectorC = linea.split(";"); // guardo los valores en un vector[]

				try {
					ptoxPartido = Integer.parseInt(lineaVectorC[0]);
					ptoExtraXRonda = Integer.parseInt(lineaVectorC[1]);
					PtExtraXFase = Integer.parseInt(lineaVectorC[2]);
					User_BD = lineaVectorC[3];
					Pass_DB = lineaVectorC[4];
					extraXRondaAcertada = Integer.parseInt(lineaVectorC[5]);
					Total_de_Ronda = Integer.parseInt(lineaVectorC[6]);

				} catch (NumberFormatException ex) {
					System.err.println("ERROR:\nEn Goles se recibió una Cadena\n" + ex.getMessage());
					System.exit(1);
				}

			}
		}

		/************************************************************************************ 
		                 * LECTURA DE BD PRONOSTICO *
		 ************************************************************************************/

		// Lista de Personas
		List<Persona> personaList = new ArrayList<>();
		int rondaID = 0;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Abrir conexión a la BD. Obtener un objeto de Conexión
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticos?user=" + User_BD
					+ "&password=" + Pass_DB + "&useSSL=false");
			// Crear Sentencia
			Statement consulta = con.createStatement();
			// Ejecutar Consultar la BD
			ResultSet result = consulta.executeQuery("select * from pronostico");

			//Recorro la DB de Pronosticos
			while (result.next()) {
				Equipo equipo1X = new Equipo(result.getString("EquipoL"));
				Equipo equipo2X = new Equipo(result.getString("EquipoV"));
				String estaPersona = result.getString("Participante");
				rondaID = Integer.parseInt(result.getString("Ronda"));
				String fase = result.getString("Fase");
				Partido partido = null;

				for (Partido partidoColeccion : partidos) {
					// Cual es el partido es igual al partido de pronostico que estamos comparando
					if (partidoColeccion.getEquipo1().getNombre().equals(equipo1X.getNombre())
							&& partidoColeccion.getEquipo2().getNombre().equals(equipo2X.getNombre())) {
						partido = partidoColeccion;
					}
				}
				Equipo equipo = null;

				if ("X".equals(result.getString("GanaL"))) {
					equipo = equipo1X;
					resultado = ResultadoEnum.GANA;
				}
				if ("X".equals(result.getString("Empata"))) {
					equipo = equipo1X;
					resultado = ResultadoEnum.EMPATA;
				}
				if ("X".equals(result.getString("GanaV"))) {
					equipo = equipo1X;
					resultado = ResultadoEnum.PIERDE;
				}

	/***********************************************************************************************************
				  
				 * Sumar los puntos correspondientes a cada participante *
				  
	***********************************************************************************************************/

				// Buscamos si la persona ya existe en la lista, si no, la creamos
				boolean personaExiste = false;
				for (Persona persona : personaList) {
					if (estaPersona.equals(persona.getNombre())) {
						personaExiste = true;
						break;
					}
				}
				if (!personaExiste) {
					personaList.add(new Persona(estaPersona));
				}

	// ----------------------------------------------------------------------------------------->
				// Por último agregamos un pronóstico a la lista, con las variables que
				// procesamos de la línea
				for (Persona persona : personaList) {
					if (persona.getNombre().equals(estaPersona)) {
						// Asignar los valores a los atributos de la Clase Pronóstico
						persona.addPronostico(new Pronostico(fase, rondaID, partido, equipo, resultado));
					}
				}
			}

	// Cerrar conexión a la BD// ---------------------------------->
			consulta.close();
			result.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("No se pudo leer la linea de pronostico...");
			System.out.println(e.getMessage());
			System.exit(1);
			e.printStackTrace();
		}

		// REVISAMOS LA LISTA DE PERSONAS ! ----------------------->
		/*
		 * for (Persona p : personaList) { System.out.println(p.toString()); }
		 */
		
 	// MOSTRAR LOS PUNTOS POR PARTICIPANTE
		// -------------------------------------------------------------------------------------------->
		System.out.println("Aciertos por Equipo Local:");
		for (Persona pr : personaList) {
			 
			System.out.printf("%s: %d%n", "Puntaje Obtenido por: \n" + pr.getNombre(),
					contarPuntosconExtra(pr, extraXRondaAcertada, PtExtraXFase, Total_de_Ronda)); 
		    }
	      }
	// ------------------------------------------------------------------------------------------------>

	
	//MÉTODO PARA CONTAR PUNTOS EXTRAS X RONDA Y FASE
	public static int contarPuntosconExtra(Persona p, int extraXRondaAcertada, int PtExtraXFase, int Total_de_Ronda) {
		int puntajeRonda1 = 0, puntajeRonda2 = 0, puntajeRonda3 = 0, puntajeFase = 0, total = 0;
		boolean verdad = false;
		for (Pronostico q : p.getPronosticoList()) {
			if (q.getRondaID() == 1) {
				puntajeRonda1 += q.puntosExtra();
				if (puntajeRonda1 == Total_de_Ronda) {
					puntajeRonda1 += extraXRondaAcertada;
					verdad = true;
				}
			}
			if (q.getRondaID() == 2) {
				puntajeRonda2 += q.puntosExtra();
				if (puntajeRonda2 == Total_de_Ronda) {
					puntajeRonda2 += extraXRondaAcertada;
					verdad = true;
				}
			}
			if (q.getRondaID() == 3) {
				puntajeRonda3 += q.puntosExtra();
				if (puntajeRonda3 == Total_de_Ronda) {
					puntajeRonda3 += extraXRondaAcertada;
					verdad = true;
				}
			}
			if (verdad) {
				puntajeFase = PtExtraXFase;
			}
		  }
		total += puntajeRonda1 + puntajeRonda2 + puntajeRonda3 + puntajeFase;
		return total;
	  }
   }

 

