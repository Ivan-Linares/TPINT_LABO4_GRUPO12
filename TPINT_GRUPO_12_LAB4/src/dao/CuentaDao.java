package dao;

import java.util.ArrayList;

import Entidad.Cuenta;

public interface CuentaDao {
	
	public boolean insertar(Cuenta cuenta);
	public boolean eliminar(Cuenta cuenta);
	public boolean modificar(Cuenta cuenta);
	public ArrayList<Cuenta> listar();
	
	//Metodos opcionales
	public Cuenta getCuentaPorID(int numeroCuenta);
	public Cuenta getCuentaPorCbu(String cbu);
	public ArrayList<Cuenta> getCuentasPorDNI(String dni);
	public int total_cuentas(int dni);

}
