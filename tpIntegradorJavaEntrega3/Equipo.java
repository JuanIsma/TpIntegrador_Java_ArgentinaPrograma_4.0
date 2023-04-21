package tpIntegradorJavaEntrega3;

public class Equipo {

	String nombre;

	public Equipo() {

	}

	public Equipo(String nombre) {

		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return " -Equipo: " + nombre + "\n";
	}

}
