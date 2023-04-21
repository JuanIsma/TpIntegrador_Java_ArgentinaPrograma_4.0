package tpIntegradorJavaEntrega1;

public class Pronostico {

	String nombre;
	Partido partido;
	Equipo equipo;
	ResultadoEnum resultado;
	String resultadoPartido;

	public Pronostico() {
		// TODO Esbozo de constructor generado automáticamente
	}

	public Pronostico( Partido partido, Equipo equipo, ResultadoEnum resultado) {
	 
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;

	}
 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public ResultadoEnum getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}

	public String getResultadoPartido() {
		return resultadoPartido;
	}

	public void setResultadoPartido(String resultadoPartido) {
		this.resultadoPartido = resultadoPartido;
	}

	public int puntos() {
		int puntos = 0;

		if (this.partido.resultado(equipo).toString().equals(getResultadoPartido())) {
			puntos = 1;
		}
		return puntos;

	}

	@Override
	public String toString() {
		return "Participante "+nombre+":\n *Pronóstico del Partido: " + partido + "\n * Equipo seleccionado: \n" + equipo
				+ " -Resultado: " + resultado + "\n";
	}

}
