package tpIntegradorJavaEntrega1;

public class ResultadoEnum {

	String ganador;
	String perdedor;
	String empate;

	public ResultadoEnum() {

	}

	public ResultadoEnum(String ganador) {
		this.ganador = ganador;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public String getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(String perdedor) {
		this.perdedor = perdedor;
	}

	public String getEmpate() {
		return empate;
	}

	public void setEmpate(String empate) {
		this.empate = empate;
	}

	@Override
	public String toString() {
		String valor = "";

		if (this.getGanador() != null) {
			valor = this.getGanador();

		}
		if (this.getPerdedor() != null) {
			valor = this.getPerdedor();

		}
		if (this.getEmpate() != null) {
			valor = this.getEmpate();
		}

		return valor;
	}

}
