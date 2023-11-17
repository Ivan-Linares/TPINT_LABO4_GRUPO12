package Negocio;

import java.util.ArrayList;

import Entidad.Pais;


public interface PaisNegocio {
	public boolean insertar (Pais p);
	public boolean modificar (Pais p);
	public ArrayList<Pais> listar();
}
