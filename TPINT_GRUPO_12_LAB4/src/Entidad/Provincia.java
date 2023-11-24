package Entidad;

public class Provincia {
	
	private Pais pais;
	private int codProvincia;
	private String NombreProvincia;
	
	public Provincia() {
		
	}



	public Pais getPais() {
		return pais;
	}



	public void setPais(Pais pais) {
		this.pais = pais;
	}



	public int getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(int codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getNombreProvincia() {
		return NombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		NombreProvincia = nombreProvincia;
	}

	
}
