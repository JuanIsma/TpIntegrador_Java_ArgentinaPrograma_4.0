package tpIntegradorJavaEntrega2;

import lombok.Data;

 
@Data
public class Partido {

	int ronda;
	Equipo equipo1;
	Equipo equipo2;
	int golesEquipo1;
	int golesEquipo2;

  

	public Partido(int ronda, Equipo equipo1, Equipo equipo2) {
		this.ronda = ronda;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}


	public ResultadoEnum resultado(Equipo equipo) {

		if (golesEquipo1 == golesEquipo2) {
			return ResultadoEnum.EMPATA;
		}
		if (equipo.getNombre().equals(equipo1.getNombre())) {
			if (golesEquipo1 > golesEquipo2) {
				return ResultadoEnum.GANA;
			} else {
				return ResultadoEnum.PIERDE;
			}
		} else {
			// como equipo no es equipo1, entonces es equipo2
			if (golesEquipo2 > golesEquipo1) {
				return ResultadoEnum.GANA;
			} else {
				return ResultadoEnum.PIERDE;
			}
		}

	}
	
	
 
	
	
	@Override
	public String toString() {
		return " \n -Equipo1: \n" + equipo1 + " -GolesEquipo1: " + golesEquipo1 + "\n -Equipo2: \n" + equipo2
				+ " GolesEquipo2: " + golesEquipo2 + "\n";
	}
	
}
