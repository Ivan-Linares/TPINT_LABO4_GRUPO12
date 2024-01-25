package Entidad;

import java.util.Date;

public class PagoPrestamo 
{
	private int IDPagoPrestamo;
	private int ID_Prestamo;
	private int ID_Cuenta;
	private double ImporteCuota; 
	private int NroCuota; 
	private Date Fecha;
	
	public PagoPrestamo(int iDPagoPrestamo, int prestamo, int cuenta, double importeCuota, int nroCuota,
			Date fecha) {
		super();
		IDPagoPrestamo = iDPagoPrestamo;
		this.ID_Prestamo = prestamo;
		this.ID_Cuenta = cuenta;
		ImporteCuota = importeCuota;
		NroCuota = nroCuota;
		Fecha = fecha;
	}
	
	public PagoPrestamo()
	{
		
	}
	
	public int getIDPagoPrestamo() {
		return IDPagoPrestamo;
	}
	public void setIDPagoPrestamo(int iDPagoPrestamo) {
		IDPagoPrestamo = iDPagoPrestamo;
	}
	public int getPrestamo() {
		return ID_Prestamo;
	}
	public void setPrestamo(int prestamo) {
		this.ID_Prestamo = prestamo;
	}
	public int getCuenta() {
		return ID_Cuenta;
	}
	public void setCuenta(int cuenta) {
		this.ID_Cuenta = cuenta;
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
