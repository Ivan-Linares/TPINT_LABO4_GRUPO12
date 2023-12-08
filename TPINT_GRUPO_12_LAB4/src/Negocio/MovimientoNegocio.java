package Negocio;

import java.util.ArrayList;

import Entidad.Movimiento;
import Entidad.Fecha;
import Entidad.TipoMovimiento;

public interface MovimientoNegocio {
	public boolean insertar(Movimiento movimiento);
	public ArrayList<Movimiento> listar();
	public ArrayList<Movimiento> listar(Fecha fecha);
	public ArrayList<Movimiento> listar(TipoMovimiento tipoMovimiento);
	public ArrayList<Movimiento> listar(String Cuenta);
	public ArrayList<Movimiento> listar(float Importe, int compare);
	
	public ArrayList<Movimiento> listarXcuenta(String cuenta);

}
