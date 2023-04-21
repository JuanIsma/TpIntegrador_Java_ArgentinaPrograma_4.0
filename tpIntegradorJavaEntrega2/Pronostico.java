package tpIntegradorJavaEntrega2;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pronostico {

	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;

	public Pronostico() {

	}

	public int puntos() {
		// Si el resultado del partido es = al del pronostico q se compara sumo 1 punto
		ResultadoEnum resultadoReal = this.partido.resultado(this.equipo);
		if (resultado.equals(resultadoReal)) {
			return 1;
		} else {
			return 0;
		}

	}

	public int sumaPuntos() {

		int sumar = 0;
		sumar += puntos();
		return sumar;

	}

	@Override
	public String toString() {
		return "\n *Pronóstico del Partido: " + partido + "\n *Datos Seleccionados del Equipo: \n" + equipo
				+ " -Resultado: " + resultado;
	}
}
