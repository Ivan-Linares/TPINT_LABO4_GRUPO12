package Negocio;

import java.util.ArrayList;

import Entidad.Localidad;

public interface LocalidadNegocio {
	public ArrayList<Localidad> listar();
	public ArrayList<Localidad> listar(int id);
	public Localidad Seleccionado (int cod);
}
