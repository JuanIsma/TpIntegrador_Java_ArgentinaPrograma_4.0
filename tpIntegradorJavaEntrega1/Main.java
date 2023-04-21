package tpIntegradorJavaEntrega1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		

		// ManipularCSV archivo = new ManipularCSV(); // CREA OBJETO
		 String nomArchResultado = "C:\\Users\\Win X\\eclipse-workspace\\TpIntegradorJavaEntrega1\\src\\tpIntegrador\\resultados.csv";
     	 String nombreArchivoPronostico = "C:\\Users\\Win X\\eclipse-workspace\\TpIntegradorJavaEntrega1\\src\\tpIntegrador\\pronostico.csv";
	 
     	 leerArchivoResultados(nomArchResultado ,nombreArchivoPronostico);
		 
	}



	public static void leerArchivoResultados(String nombreArchResultado, String nombreArchivoPronostico) {
		
		/***************************************************************************
	                               * ARCHIVO RESULTADOS *
	     ***************************************************************************/
        Pronostico pro1 = new Pronostico();
  
		Partido p1;
		Equipo e1, e2;
		ResultadoEnum resultadoPartido = new ResultadoEnum();

		Path ruta;
		List<String> lineasArchivo = null;
		String lineaVector[] = new String[4];
		String vector2[] = new String[4];

		List<ResultadoEnum> ResultadoEnumArray = new ArrayList<ResultadoEnum>();
		List<Pronostico> pronosticoArray = new ArrayList<Pronostico>();

		String   equipoLocal = null, equipoVisitante = null ,nombre=null;
		int id1 = 0, id2 = 0, golesLocal = 0, golesVisitante = 0;

		List<String> lineasArchivo01 = null;
		int acumulaPtos = 0, acumulaPtos2 = 0 ,acumulaPtos3=0 ,cant=-1;
    

		try {
			ruta = Paths.get(nombreArchResultado);
			lineasArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		     } catch (IOException e) {
			e.printStackTrace();
		                        }

		for (String linea : lineasArchivo) {
			lineaVector = linea.split(";"); // guardo los valores en un vector[]
			// System.out.println(linea); // IMPRIME DE PRUEBA
			id1 = Integer.parseInt(lineaVector[0]);
			equipoLocal = lineaVector[1];
			golesLocal = Integer.parseInt(lineaVector[2]);
			golesVisitante = Integer.parseInt(lineaVector[3]);
			id2 = Integer.parseInt(lineaVector[4]);
			equipoVisitante = lineaVector[5];
			

			// Creo un Objeto Equipo id1 | equipo1 | descripcion1
			e1 = new Equipo(id1, equipoLocal);
			e2 = new Equipo(id2, equipoVisitante);
			// creo un objeto partido y cargo los valores de los parametros en el
			// cosntructor
			p1 = new Partido(e1, e2, golesLocal, golesVisitante);
			// Asigno el resultado a variable de tipo Objeto ResultadoEnum el Equipo1
			resultadoPartido = p1.resultado(e1);
			ResultadoEnumArray.add(resultadoPartido);
			
			pro1 = new Pronostico(p1, e1, resultadoPartido);
			pro1.setResultadoPartido(resultadoPartido.toString());
			pro1.setNombre("Mariana");
    		pronosticoArray.add(pro1);
                 	           	}

		/***************************************************************************
		                            * ARCHIVO PRONOSTICO *
		 ***************************************************************************/
	
		try {
			ruta = Paths.get(nombreArchivoPronostico);
			lineasArchivo01 = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String linea1 : lineasArchivo01) {
			vector2 = linea1.split(";"); // Guarda los valore en un Vector[]
 
             cant++;
              
	  /* ============================================================================  */
			try {
				if ( vector2[2].equals("X")) { //si el vector2 en la posición 1 es igual a X
					String resultaGana = "GANE"; 
					if (ResultadoEnumArray.get(cant).toString().equals(resultaGana)) { //Compara el resultado de partido con el de Pronostico si es igual a gane
						acumulaPtos = pro1.puntos();
                    	System.out.println(pronosticoArray.get(cant).toString()); 
				    	}
				      }
      /* ============================================================================  */
				 if ( vector2[3].equals("X")) {
					String resultaEmpate = "EMPATE";
					if (ResultadoEnumArray.get(cant).toString().equals(resultaEmpate)) { // Compara el resultado de partido con el de Pronostico si es igual a empate
						acumulaPtos2 = pro1.puntos();
						System.out.println( pronosticoArray.get(cant).toString());
					     }
				       }
	 /* ============================================================================  */ 
	 
				 if ( vector2[4].equals("X")) {
					String resultaEmpate = "PIERDE";
					if (ResultadoEnumArray.get(cant).toString().equals(resultaEmpate)) { // Compara el resultado de partido con el de Pronostico si es igual a pierde
						acumulaPtos3 += pro1.puntos();
						System.out.println( pronosticoArray.get(cant).toString());
								     }
							       }
		  /* ============================================================================  */
							 				 
 		
			  } catch (Exception e) {
			 }
		    }
		int total = acumulaPtos + acumulaPtos2 +acumulaPtos3;
		System.out.print("Aciertos por Equipo Local:\n+ Puntaje Total: " + total);
	
          }
        }




