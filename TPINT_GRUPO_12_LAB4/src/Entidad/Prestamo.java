package Entidad;

import java.util.Date;

public class Prestamo {
	
	private int IDPrestamo;
	private String DNI;
	private int NroCuenta;
	private Date Fecha; 
	private double ImporteSolicitado; 
	private double ImporteTotal;
	private double ImporteMensual; 
	private int CuotasRestantes; 
	private String Estado;
	
	
	public Prestamo() {

	}
	
	public int getIDPrestamo() {
		return IDPrestamo;
	}
	public void setIDPrestamo(int iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public int getNroCuenta() {
		return NroCuenta;
	}
	public void setNroCuenta(int nroCuenta) {
		NroCuenta = nroCuenta;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public double getImporteSolicitado() {
		return ImporteSolicitado;
	}
	public void setImporteSolicitado(double importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}
	public double getImporteTotal() {
		return ImporteTotal;
	}
	public void setImporteTotal(double importeTotal) {
		ImporteTotal = importeTotal;
	}
	public double getImporteMensual() {
		return ImporteMensual;
	}
	public void setImporteMensual(double montoCuota) {
		ImporteMensual = montoCuota;
	}
	public int getCuotasRestantes() {
		return CuotasRestantes;
	}
	public void setCuotasRestantes(int cuotasRestantes) {
		CuotasRestantes = cuotasRestantes;
	}
	public String getEstado() {
		
		if(Estado.equals("A"))
			return "Activo";
		if(Estado.equals("E"))
			return "Pago";
		if(Estado.equals("P"))
			return "Pendiente";
		if(Estado.equals("R"))
			return "Rechazado";
		
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}

	

}
