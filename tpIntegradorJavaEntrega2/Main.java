package tpIntegradorJavaEntrega2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 

public class Main {

	public static void main(String[] args) {

		/***************************************************************************
		                   * LECTURA DE ARCHIVO RESULTADOS *
		 ***************************************************************************/

		Path ruta;
		List<String> lineasArchivo = null;
		String lineaVector[] = new String[4];
		String camposV[] = new String[4];
		// Leer resultados
		Collection<Partido> partidos = new ArrayList<Partido>();
		String equipoLocal = "", equipoVisitante = "";
		int ronda = 0, golesLocal = 0, golesVisitante = 0;
		ResultadoEnum resultado = null;
 

		try {
			ruta = Paths.get("src\\main\\java\\tpIntegrador\\resultados.csv");
			lineasArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		} catch (IOException e) {

			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		boolean primera = true;
		for (String linea : lineasArchivo) {
			if (primera) {
				primera = false;
			} else {
				lineaVector = linea.split(";"); // guardo los valores en un vector[]  y los separamos usando el separador split
 
				ronda = Integer.parseInt(lineaVector[0]);
				equipoLocal = lineaVector[1];
				equipoVisitante = lineaVector[4];
				Equipo equipo1 = new Equipo(equipoLocal);
				Equipo equipo2 = new Equipo(equipoVisitante);
				Partido partido = new Partido(ronda, equipo1, equipo2);

				try {
					golesLocal = Integer.parseInt(lineaVector[2]);
					golesVisitante = Integer.parseInt(lineaVector[3]);
					partido.setGolesEquipo1(golesLocal);
					partido.setGolesEquipo2(golesVisitante);
					partidos.add(partido);
				} catch (NumberFormatException ex) {
					System.err.println("ERROR:\nEn Goles se recibió una Cadena\n" + ex.getMessage());
					System.exit(1);
				}

			}
		}

		/*********************************************************************************
		                 * LECTURA DE ARCHIVO PRONOSTICO *
		 *********************************************************************************/

		 
		List<String> lineasPronostico = null;
    	// Lista de Personas
		List<Persona> personaList = new ArrayList<>();

		try {
			ruta = Paths.get("src\\main\\java\\tpIntegrador\\pronostico.csv");
			lineasPronostico = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de pronosticos...");
			System.out.println(e.getMessage());
			System.exit(1); 
		}

		primera = true;
		for (String lineaPronost : lineasPronostico) {
			if (primera) {
				primera = false;
			} else {

				// ----------------------------------------------------------------------------------------->
				camposV = lineaPronost.split(";"); // Guarda los valore en un Vector[] y los separamos usando el separador split
            	String estaPersona = camposV[0];
				Equipo equipo1 = new Equipo(camposV[1]);
				Equipo equipo2 = new Equipo(camposV[5]);
				Partido partido = null;

				for (Partido partidoColeccion : partidos) {
            // Cual es el partido es igual al partido de pronostico que estamos comparando
					if (partidoColeccion.getEquipo1().getNombre().equals(equipo1.getNombre())
							&& partidoColeccion.getEquipo2().getNombre().equals(equipo2.getNombre())) {
						partido = partidoColeccion;
					}
				}
				Equipo equipo = null;

				//Resultados por valor seleccionado
				if ("X".equals(camposV[2])) {
					equipo = equipo1;
					resultado = ResultadoEnum.GANA;
				}
				if ("X".equals(camposV[3])) {
					equipo = equipo1;
					resultado = ResultadoEnum.EMPATA;
				}
				if ("X".equals(camposV[4])) {
					equipo = equipo1;
					resultado = ResultadoEnum.PIERDE;
				}

				// ----------------------------------------------------------------------------------------->
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
						//Agregamos los valores a los parámetros de la Clase Pronóstico
						persona.addPronostico(new Pronostico(partido, equipo, resultado));
					}
				}

			}
		}

         // mostrar los puntos

		// ------------------------------------------------------------------------------------------------>
		// Iterando por los pronósticos de la lista de pronósticos, vamos sumando el
		// puntaje total
		int suma=0;
		System.out.println("Aciertos por Equipo Local:");
		for (Persona p : personaList) {
			System.out.printf("%s: %d%n", p.getNombre(), contarPuntos(p));
            suma+=sumarTotalPuntos(p);
		     }
         System.out.println("Suma Total de Ptos: "+suma  );
    	}

    	// ------------------------------------------------------------------------------------------------>
	
	
	
	    // Método contarPuntos:
	    public static int contarPuntos(Persona p) {
		  int puntaje = 0;
		  for (Pronostico q : p.getPronosticoList()) {
			puntaje += q.puntos();
		    }
		  return puntaje;
	     }
	
	    // Método sumarTotalPuntos
	public static int sumarTotalPuntos(Persona p) {
		int puntaje = 0 ,ptos=0;
		for (Pronostico q : p.getPronosticoList()) {
			puntaje += q.sumaPuntos();
		}
		ptos=puntaje+ptos;
		return ptos;
	}

}
