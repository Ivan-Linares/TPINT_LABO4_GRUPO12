package Negocio;

import java.util.ArrayList;

import Entidad.Provincia;


public interface ProvinciaNegocio {
	public boolean insertar (Provincia p);
	public boolean modificar (Provincia p);
	public ArrayList<Provincia> listar();
	public ArrayList<Provincia> listar(int id);
}
