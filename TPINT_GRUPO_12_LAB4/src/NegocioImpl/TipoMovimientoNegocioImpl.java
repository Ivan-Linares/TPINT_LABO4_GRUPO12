package NegocioImpl;

import java.util.ArrayList;

import Entidad.TipoMovimiento;
import Negocio.TipoMovimientoNegocio;
import dao.TipoMovimientoDao;
import daoImpl.TipoMovimientoDaoImpl;

public class TipoMovimientoNegocioImpl implements TipoMovimientoNegocio {
	
	private TipoMovimientoDao tipoMovDao;

	@Override
	public boolean insertar(TipoMovimiento tipoMovimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(TipoMovimiento tipoMovimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Eliminar(TipoMovimiento tipoMovimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoMovimiento> listar() {
		tipoMovDao = new TipoMovimientoDaoImpl();
		return tipoMovDao.listar();
	}

}
