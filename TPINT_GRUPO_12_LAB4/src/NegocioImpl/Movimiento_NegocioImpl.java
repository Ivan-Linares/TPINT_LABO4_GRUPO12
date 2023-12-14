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
	public boolean insertar(int cuenta, float importe, int tipoMovimiento) {
		Cdao = new MovimientoDaoImpl();
		return Cdao.insertar(cuenta, importe, tipoMovimiento);
	}

	@Override
	public ArrayList<Movimiento> listar() {
		Cdao = new MovimientoDaoImpl();
		return Cdao.listar();
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

	@Override
	public Double totalTipoMov(String fechaInicio, String fechaFin, int tipoMovimiento) {
		Cdao = new MovimientoDaoImpl();
		return Cdao.totalTipoMov(fechaInicio, fechaFin, tipoMovimiento);
	}

	@Override
	public ArrayList<Movimiento> listarFiltrada(String cuenta, int importe, int tipoMov) {
		Cdao = new MovimientoDaoImpl();
		return Cdao.listarFiltrada(cuenta, importe, tipoMov);
	}

}
