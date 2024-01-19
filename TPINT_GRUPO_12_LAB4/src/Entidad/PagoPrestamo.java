package Entidad;

import java.util.Date;

public class PagoPrestamo 
{
	private int IDPagoPrestamo;
	private Prestamo prestamo;
	private Cuenta cuenta;
	private double ImporteCuota; 
	private int NroCuota; 
	private Date Fecha;
	
	public PagoPrestamo(int iDPagoPrestamo, Prestamo prestamo, Cuenta cuenta, double importeCuota, int nroCuota,
			Date fecha) {
		super();
		IDPagoPrestamo = iDPagoPrestamo;
		this.prestamo = prestamo;
		this.cuenta = cuenta;
		ImporteCuota = importeCuota;
		NroCuota = nroCuota;
		Fecha = fecha;
	}
	
	public int getIDPagoPrestamo() {
		return IDPagoPrestamo;
	}
	public void setIDPagoPrestamo(int iDPagoPrestamo) {
		IDPagoPrestamo = iDPagoPrestamo;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public double getImporteCuota() {
		return ImporteCuota;
	}
	public void setImporteCuota(double importeCuota) {
		ImporteCuota = importeCuota;
	}
	public int getNroCuota() {
		return NroCuota;
	}
	public void setNroCuota(int nroCuota) {
		NroCuota = nroCuota;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	} 
}
