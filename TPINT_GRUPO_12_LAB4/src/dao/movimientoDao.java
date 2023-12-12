package dao;

import java.util.ArrayList;

import Entidad.Movimiento;

public interface movimientoDao {
	
	public boolean insertar(Movimiento mov);
	public boolean eliminar(Movimiento mov);
	public boolean modificar(Movimiento mov);
	public Movimiento getMovimientoPorID(int id);
	public ArrayList<Movimiento> getMovimientosByDNI(String dni);
	public ArrayList<Movimiento> listar();
	public int cantMovimientos();

	public ArrayList<Movimiento> listarXcuenta(String cuenta);
	public Double totalTipoMov(String fechaInicio, String fechaFin, int tipoMovimiento);
}
