package NegocioImpl;

import java.util.ArrayList;

import Entidad.Pais;
import Negocio.PaisNegocio;
import dao.PaisDao;
import daoImpl.PaisDaoImpl;

public class PaisNegocioImpl implements PaisNegocio {

	@Override
	public boolean insertar(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Pais> listar() {
		PaisDao pd = new PaisDaoImpl();
		return pd.listar();
	}

}
