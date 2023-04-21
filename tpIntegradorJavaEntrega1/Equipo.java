package tpIntegradorJavaEntrega1;

public class Equipo {

	int id;
	String nombre;
	String descripcion;

	public Equipo() {

	    }

	public Equipo(int id, String nombre ) {

		this.id = id;
		this.nombre = nombre;
		 
	} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return " -Id: " + id + "\n -Equipo: " + nombre +"\n" ;
	}
}
