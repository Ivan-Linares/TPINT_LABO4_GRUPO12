package Entidad;

public class Fecha {
	
	private int dia;
	private int mes;
	private int year;
	
	public Fecha() {
		
	}
	
	public Fecha(int d, int m, int y) {
		this.dia=d;
		this.mes=m;
		this.year=y;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return dia + "-" + mes + "-" + year;
	}
	
	

}
