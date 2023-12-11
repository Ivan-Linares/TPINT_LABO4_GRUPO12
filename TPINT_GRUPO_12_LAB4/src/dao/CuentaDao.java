package dao;

import java.util.ArrayList;

import Entidad.Cuenta;

public interface CuentaDao {
	
	public boolean insertar(Cuenta cuenta);
	public boolean insert(String DNI, int tc);
	public boolean eliminar(Cuenta cuenta);
	public boolean modificar(Cuenta cuenta);
	public ArrayList<Cuenta> listar();
	
	//Metodos opcionales
	//public Cuenta getCuentaPorID(int numeroCuenta);
	//public Cuenta getCuentaPorCbu(String cbu);
	public ArrayList<Cuenta> listarPorCuenta(String numero);
	public int total_cuentas(int dni);
	public ArrayList<Cuenta> listar(String b, int busqueda);
	public String ultimoCBU();

	public ArrayList<Cuenta> CuentasAsociadas(String nc);
	public String BuscarCteporCuenta(String numerocuenta);
	public ArrayList<Cuenta> CuentasxDNI(String dni);
	public ArrayList<Cuenta> listaFiltrada(String dato, String campo);
	public boolean CuentasPendientes (String DNI);
}
