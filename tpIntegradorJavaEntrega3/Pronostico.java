package tpIntegradorJavaEntrega3;

public class Pronostico {

	private String fase;
	private int rondaID;
 	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;

	public Pronostico() {
		// TODO Esbozo de constructor generado automáticamente
	}

	public Pronostico(String fase ,int rondaID ,Partido partido, Equipo equipo, ResultadoEnum resultado) {
		this.fase=fase;
        this.rondaID=rondaID;
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;

	}
	
	 
	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public int getRondaID() {
		return rondaID;
	}

	public void setRondaID(int rondaID) {
		this.rondaID = rondaID;
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

	
	public int puntosExtra( ) {

		ResultadoEnum resultadoReal = this.partido.resultado(this.equipo);
		if (this.resultado.equals(resultadoReal)  ) {
			return 1;
		} else {
			return 0;
		}

	}



	@Override
	public String toString() {
		return "\n *Pronóstico del Partido: " + partido + "\n *Datos Seleccionados del Equipo: \n" + equipo
				+ " -Resultado: " + resultado + "\n";
	}

}
