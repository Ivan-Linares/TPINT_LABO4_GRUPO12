package NegocioImpl;

import java.util.ArrayList;

import Entidad.Localidad;
import Entidad.Provincia;
import Negocio.LocalidadNegocio;
import dao.LocalidadDAO;
import daoImpl.LocalidadDAOImpl;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	private LocalidadDAO ldao;
	@Override
	public ArrayList<Localidad> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Localidad> listar(int id) {
		ldao=new LocalidadDAOImpl();
		
		return ldao.filtrar(id);
	}

}
