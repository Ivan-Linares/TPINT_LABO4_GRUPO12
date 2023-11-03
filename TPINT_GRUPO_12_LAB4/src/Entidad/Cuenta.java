package Entidad;

public class Cuenta {
	
	private String dni;
	private String numero;
	private Fecha fechaCreacion;
	private TipoCuenta tipoCuenta;
	private float saldo;
	private String estado;
	
	public Cuenta() {
		
	}
	
	public Cuenta(String d, String n, Fecha fechaC, TipoCuenta t, float s, String e) {
		
		this.dni=d;
		this.numero=n;
		this.fechaCreacion=fechaC;
		this.tipoCuenta=t;
		this.saldo=s;
		this.estado=e;
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

	public Fecha getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Fecha fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	
	

}
