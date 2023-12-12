package Negocio;

import java.util.ArrayList;

import Entidad.Cuenta;


public interface CuentaNegocio {
	public boolean insertar (Cuenta cuenta);
	public boolean insert(String DNI, int tc);
	public boolean modificar (Cuenta cuenta);
	public boolean Eliminar (Cuenta cuenta);
	public ArrayList<Cuenta> listar();
	public ArrayList<Cuenta> listarXcuenta(String numerocuenta);
	public ArrayList<Cuenta> listar(String buscar, int compare);
	public ArrayList<Cuenta> listar(float Importe, int compare);
	public String ultimoCBU();
	
	public ArrayList<Cuenta> CuentasAsociadas(String nc);
	public String BuscarCteporCuenta(String numerocuenta);
	public ArrayList<Cuenta> CuentasxDNI(String dni);
	public ArrayList<Cuenta> listaFiltrada(String dato, String campo);
	public boolean CuentasPendientes (String DNI);
	public ArrayList<Cuenta> ListarCuentasPendientes();
}
