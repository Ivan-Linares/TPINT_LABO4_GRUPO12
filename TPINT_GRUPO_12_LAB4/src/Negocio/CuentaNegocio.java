package Negocio;

import java.util.ArrayList;

import Entidad.Cuenta;


public interface CuentaNegocio {
	public boolean insertar (Cuenta cuenta);
	public boolean modificar (Cuenta cuenta);
	public ArrayList<Cuenta> listar();
	public ArrayList<Cuenta> listar(String buscar, int compare);
	public ArrayList<Cuenta> listar(float Importe, int compare);
}
