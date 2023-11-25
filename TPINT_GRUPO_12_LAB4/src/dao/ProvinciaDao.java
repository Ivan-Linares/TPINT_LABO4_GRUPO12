package dao;

import java.util.ArrayList;

import Entidad.Provincia;

public interface ProvinciaDao {
	
	public boolean insertar(Provincia p);
	public boolean eliminar(Provincia p);
	public boolean modificar(Provincia p);
	public ArrayList<Provincia> filtrar(int id);
	public ArrayList<Provincia> filtrarPorPais(int id);
	public ArrayList<Provincia> listar();

}
