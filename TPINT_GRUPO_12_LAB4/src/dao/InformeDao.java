package dao;

import java.util.ArrayList;

import Entidad.Informe;


public interface InformeDao {
	
	public ArrayList<Informe> CargarInforme();
	public ArrayList<Informe> CargarInforme(int pais);
	public ArrayList<Informe> CargarInforme(int pais, int provincia);
	
}
