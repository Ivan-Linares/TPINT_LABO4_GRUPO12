package dao;

import java.util.ArrayList;

import Entidad.Localidad;

public interface LocalidadDAO {
	
	public ArrayList<Localidad> filtrar(int id);
	public ArrayList<Localidad> listar();
	
	public Localidad Seleccionado (int cod);
}
