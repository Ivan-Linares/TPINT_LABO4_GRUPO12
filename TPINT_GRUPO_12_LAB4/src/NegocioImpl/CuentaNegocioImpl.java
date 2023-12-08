package NegocioImpl;

import java.util.ArrayList;

import Entidad.Cuenta;
import Negocio.CuentaNegocio;
import dao.CuentaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;

public class CuentaNegocioImpl implements CuentaNegocio{

	private CuentaDao cDao;
	@Override
	public boolean insertar(Cuenta cuenta) {
		
			cDao = new CuentaDaoImpl();
			
			if(cuenta.getDni().trim().length()>0 && cuenta.getFechaCreacion() != null &&
			   cuenta.getTipoCuenta()!=null && cuenta.getEstado().trim().length()>0)
			{
				return cDao.insertar(cuenta);
			}
			return false;
		}

	@Override
	public boolean modificar(Cuenta cuenta) {
		cDao= new CuentaDaoImpl();
		return cDao.modificar(cuenta);
		
		/*if(cuenta.getFechaCreacion() != null && cuenta.getSaldo()>=0 && cuenta.getDni().trim().length()>0 && cuenta.getNumero().trim().length()>0 &&
				cuenta.getTipoCuenta()!=null && cuenta.getCBU().trim().length()>0 && cuenta.getEstado().trim().length()>0)
		{
			return cDao.modificar(cuenta);
		}
		return false;*/
	}

	@Override
	public ArrayList<Cuenta> listar() {
		cDao = new CuentaDaoImpl();
		return cDao.listar();
	}

	@Override
	public ArrayList<Cuenta> listar(String buscar, int compare) {
		return cDao.listar(buscar, compare);
	}

	@Override
	public ArrayList<Cuenta> listar(float Importe, int compare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuenta> listarXcuenta(String numerocuenta) {
		cDao = new CuentaDaoImpl();
		return cDao.listarPorCuenta(numerocuenta);
	}

	@Override
	public boolean Eliminar(Cuenta cuenta) {
		cDao = new CuentaDaoImpl();
		return cDao.eliminar(cuenta);
	}

	@Override
	public String ultimoCBU() {
		cDao = new CuentaDaoImpl();
		return cDao.ultimoCBU();
	}
	
}
