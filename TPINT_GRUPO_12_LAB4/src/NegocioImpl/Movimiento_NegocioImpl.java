package NegocioImpl;

import java.util.ArrayList;

import Entidad.Fecha;
import Entidad.Movimiento;
import Entidad.TipoMovimiento;
import Negocio.MovimientoNegocio;
import dao.movimientoDao;
import daoImpl.MovimientoDaoImpl;

public class Movimiento_NegocioImpl implements MovimientoNegocio{
	movimientoDao Cdao;

	@Override
	public boolean insertar(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Movimiento> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listar(Fecha fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listar(TipoMovimiento tipoMovimiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listar(String Cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listar(float Importe, int compare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listarXcuenta(String cuenta) {
		Cdao =  new MovimientoDaoImpl();
		return Cdao.listarXcuenta(cuenta);
	}

}
