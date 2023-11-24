package NegocioImpl;

import java.util.ArrayList;

import Entidad.TipoCuenta;
import Negocio.Tipo_de_CuentaNegocio;
import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;

public class Tipo_de_CuentaNegocioImpl implements Tipo_de_CuentaNegocio{
	private TipoCuentaDao Tpc;

	@Override
	public boolean insertar(TipoCuenta tipocuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(TipoCuenta tipocuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Eliminar(TipoCuenta tipocuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoCuenta> listar() {
		Tpc= new TipoCuentaDaoImpl();
		return Tpc.listar();
	}
}
