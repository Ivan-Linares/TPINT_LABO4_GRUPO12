package Entidad;

public class Localidad {
	
	private Provincia provincia;
	private int codLocalidad;
	private String NombreLocalidad;
	
	public Localidad() {
		
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public int getCodLocalidad() {
		return codLocalidad;
	}
	public void setCodLocalidad(int codLocalidad) {
		this.codLocalidad = codLocalidad;
	}
	public String getNombreLocalidad() {
		return NombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		NombreLocalidad = nombreLocalidad;
	}
	
	

}
