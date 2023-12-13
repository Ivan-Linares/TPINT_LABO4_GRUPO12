package Negocio;

import java.util.ArrayList;

import Entidad.Informe;

public interface InformeNegocio {
	public ArrayList<Informe> consultar();
	public ArrayList<Informe> consultar(int pais);
	public ArrayList<Informe> consultar(int pais, int provincia);
}
