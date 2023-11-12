package Entidad;

public class TipoUsuario {
	
	private int Tipo;
	private String descripcion;
	
	public TipoUsuario() {
		
	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
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
