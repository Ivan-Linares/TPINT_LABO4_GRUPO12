package dao;

import java.util.ArrayList;

import Entidad.TipoCuenta;

public interface TipoCuentaDao {
	
	public boolean insertar(TipoCuenta tp);
	public boolean eliminar(TipoCuenta tp);
	public boolean modificar(TipoCuenta tp);
	public ArrayList<TipoCuenta> filtar(int id);
	public ArrayList<TipoCuenta> listar();
	public TipoCuenta getPorID(int id);

}
