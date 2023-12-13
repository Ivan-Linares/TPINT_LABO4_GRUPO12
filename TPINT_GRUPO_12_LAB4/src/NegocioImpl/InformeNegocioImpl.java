package NegocioImpl;

import java.util.ArrayList;

import Entidad.Informe;
import Negocio.InformeNegocio;
import dao.InformeDao;
import daoImpl.InformeDaoImpl;

public class InformeNegocioImpl implements InformeNegocio {

	private InformeDao IDao;
	@Override
	public ArrayList<Informe> consultar() {
		IDao = new InformeDaoImpl();
		return IDao.CargarInforme();
	}

	@Override
	public ArrayList<Informe> consultar(int pais) {
		IDao = new InformeDaoImpl();
		return IDao.CargarInforme(pais);
	}

	@Override
	public ArrayList<Informe> consultar(int pais, int provincia) {
		IDao = new InformeDaoImpl();
		return IDao.CargarInforme(pais, provincia);
	}

}
