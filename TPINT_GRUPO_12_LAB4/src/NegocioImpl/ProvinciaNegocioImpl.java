package NegocioImpl;

import java.util.ArrayList;

import Entidad.Provincia;
import Negocio.ProvinciaNegocio;
import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{
	
	private ProvinciaDao pDao;
	@Override
	public boolean insertar(Provincia p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Provincia p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Provincia> listar() {
		ProvinciaDao pd = new ProvinciaDaoImpl();
		return pd.listar();
		
	}

	@Override
	public ArrayList<Provincia> listar(int id) {
		
		pDao=new ProvinciaDaoImpl();
		
		return pDao.filtrarPorPais(id);
		
	}

}
