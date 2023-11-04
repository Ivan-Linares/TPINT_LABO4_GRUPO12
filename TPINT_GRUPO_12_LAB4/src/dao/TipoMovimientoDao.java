package dao;

import java.util.ArrayList;

import Entidad.TipoMovimiento;

public interface TipoMovimientoDao {
	
	public boolean insertar(TipoMovimiento tm);
	public boolean eliminar(TipoMovimiento tm);
	public boolean modificar(TipoMovimiento tm);
	public ArrayList<TipoMovimiento> filtar(int id);
	public ArrayList<TipoMovimiento> listar();
	public TipoMovimiento getPorID(int id);
	
}
