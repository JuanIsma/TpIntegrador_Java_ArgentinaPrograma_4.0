package tpIntegradorJavaEntrega1;

public class Partido {
	Equipo equipo1;
	Equipo equipo2;
	int golesEquipo1;
	int golesEquipo2;

	public Partido() {
	}

	public Partido(Equipo equipo1, Equipo equipo2) {

		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {

		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	@Override
	public String toString() {
		return " \n -Equipo1: \n" + equipo1 + " -GolesEquipo1: " + golesEquipo1 + "\n -Equipo2: \n" + equipo2
				+ " GolesEquipo2: " + golesEquipo2 + "\n";
	}

	public ResultadoEnum resultado(Equipo e) {

		ResultadoEnum res = new ResultadoEnum();
		String empata = "EMPATE";
		String gana = "GANE";
		String pierde = "PIERDE";

		if ((this.equipo1.equals(e))) {

			if (getGolesEquipo1() > getGolesEquipo2()) {
				res.setGanador(gana);
			} else {
				res.setPerdedor(pierde);
			}
			if (getGolesEquipo1() == getGolesEquipo2()) {
				res.setEmpate(empata);
			}
		}

		if ((this.equipo2.equals(e))) {

			if (getGolesEquipo2() > getGolesEquipo1()) {
				res.setGanador(gana);
			} else {
				res.setPerdedor(pierde);
			}
			if (getGolesEquipo2() == getGolesEquipo1()) {
				res.setEmpate(empata);
			}
		}

		return res;
	}
}
