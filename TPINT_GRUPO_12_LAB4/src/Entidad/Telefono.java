package Entidad;


public class Telefono {
	
	//Podriamos usar Cliente para reemplazar DNI
	
	private String Dni;
	private String Telefono;
	
	//Agregar estado a la tabla 
	private boolean Estado;
	
	public Telefono() {
		
	}

	public Telefono(String persona, String telefono, boolean estado) {
		this.Dni = persona;
		this.Telefono = telefono;
		this.Estado = estado;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		this.Dni = dni;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Telefono [DNI " + Dni + ", Telefono=" + Telefono + ", Estado=" + Estado + "]";
	}

}
