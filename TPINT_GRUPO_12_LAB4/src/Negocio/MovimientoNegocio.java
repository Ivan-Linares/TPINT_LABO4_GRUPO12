package Negocio;

import java.util.ArrayList;

import Entidad.Movimiento;
import Entidad.Fecha;
import Entidad.TipoMovimiento;

public interface MovimientoNegocio {
	public boolean insertar(int cuenta, float importe, int tipoMovimiento);
	public ArrayList<Movimiento> listar();
	public ArrayList<Movimiento> listar(Fecha fecha);
	public ArrayList<Movimiento> listar(TipoMovimiento tipoMovimiento);
	public ArrayList<Movimiento> listar(String Cuenta);
	public ArrayList<Movimiento> listar(float Importe, int compare);
	
	public ArrayList<Movimiento> listarXcuenta(String cuenta);
	public Double totalTipoMov(String fechaInicio, String fechaFin, int tipoMovimiento);
	public ArrayList<Movimiento> listarFiltrada(String cuenta, int importe, int tipoMov);
}
