package Entidad;

import java.sql.Date;
import java.time.LocalDate;

public class Cuenta {
	
	private String dni;
	private String numero;
	private Date Fecha;
	private TipoCuenta tipoCuenta;
	private String CBU;
	private float saldo;
	private String estado;
	
	//private static int ContCBU = 0;
	
	public Cuenta() 
	{
		//this.setCBU(ContCBU++);
	}
	
	public Cuenta(String d, String n, Date fechaC, TipoCuenta t, String c, float s, String e) {
		
		this.dni=d;
		this.numero=n;
		this.Fecha=fechaC;
		this.tipoCuenta=t;
		this.CBU=c;
		this.saldo=s;
		this.setEstado(e);
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return Fecha;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.Fecha = fechaCreacion;
	}
	
	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cbu) {
		this.CBU = cbu;
	}
	
	public void setCBU(int cbu) {
		 this.CBU = "1" + String.format("%023d", cbu); //mas info en https://www.javatpoint.com/java-string-format
	}


	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
