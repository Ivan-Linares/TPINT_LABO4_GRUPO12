package dao;

import java.util.ArrayList;

import Entidad.Pais;

public interface PaisDao {
	
	public boolean insertar(Pais p);
	public boolean eliminar(Pais p);
	public boolean modificar(Pais p);
	public ArrayList<Pais> filtrar(int id);
	public ArrayList<Pais> listar();

}
