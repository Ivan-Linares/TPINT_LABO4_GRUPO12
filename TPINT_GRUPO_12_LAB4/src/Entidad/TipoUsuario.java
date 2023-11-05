package Entidad;

public class TipoUsuario {
	
	private String Tipo;
	private String descripcion;
	
	public TipoUsuario() {
		
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return descripcion ;
	}
	
	

}
