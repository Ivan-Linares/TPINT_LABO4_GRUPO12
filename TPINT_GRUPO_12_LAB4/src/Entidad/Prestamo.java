package Entidad;

import java.time.LocalDate;

public class Prestamo {
	
	private Integer IDPrestamo; 
	private Cuenta Cuenta; 
	private LocalDate Fecha; 
	private double ImporteSolicitado; 
	private int PlazoPagoMeses; 
	private int CuotasRestantes; 
	private String Estado;
	//Tasa nominal anual
	private double TNA;

	public Prestamo() {

	}

	public Prestamo(Integer iDPrestamo, Cuenta cuenta, LocalDate fecha,
			double importeSolicitado, int plazoPagoMeses, double tna, int cuotasRestantes, String estado) {
		this.IDPrestamo = iDPrestamo;
		this.Cuenta = cuenta;
		this.Fecha = fecha;
		this.ImporteSolicitado = importeSolicitado;
		this.PlazoPagoMeses = plazoPagoMeses;
		this.TNA = tna;
		this.CuotasRestantes = cuotasRestantes;
		this.Estado = estado;
	}

	public Integer getIDPrestamo() {
		return IDPrestamo;
	}

	public void setIDPrestamo(Integer iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public double getImporteSolicitado() {
		return ImporteSolicitado;
	}

	public void setImporteSolicitado(double importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}

	public int getPlazoPagoMeses() {
		return PlazoPagoMeses;
	}

	public void setPlazoPagoMeses(int plazoPagoMeses) {
		PlazoPagoMeses = plazoPagoMeses;
	}

	public double getTna() {
		return TNA;
	}

	public void setTna(double tna) {
		this.TNA = tna;
	}

	public int getCuotasRestantes() {
		return CuotasRestantes;
	}

	public void setCuotasRestantes(int cuotasRestantes) {
		CuotasRestantes = cuotasRestantes;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		this.Estado = estado;
	}

}
