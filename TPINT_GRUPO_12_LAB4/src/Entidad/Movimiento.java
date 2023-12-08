package Entidad;

import java.sql.Date;

public class Movimiento {
	
	private String cuenta;
	private Date fechaTef;
	private float importe;
	private TipoMovimiento tipoMovimiento;
	
	public Movimiento() {
		
	}
	
	public Movimiento(String c, Date f, float i, TipoMovimiento t) {
		
		this.cuenta=c;
		this.fechaTef=f;
		this.importe=i;
		this.tipoMovimiento=t;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaTef() {
		return fechaTef;
	}

	public void setFechaTef(Date fechaTef) {
		this.fechaTef = fechaTef;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
}

