package NegocioImpl;

import java.util.ArrayList;

import Entidad.Cuenta;
import Negocio.CuentaNegocio;
import dao.CuentaDao;

public class CuentaNegocioImpl implements CuentaNegocio{

	private CuentaDao cDao;
	@Override
	public boolean insertar(Cuenta cuenta) {
			if(cuenta.getDni().trim().length()>0 && cuenta.getFechaCreacion() != null && cuenta.getNumero().trim().length()>0 &&
					cuenta.getTipoCuenta()!=null && cuenta.getCBU().trim().length()>0 && cuenta.getEstado().trim().length()>0)
			{
				return cDao.insertar(cuenta);
			}
			return false;
		}

	@Override
	public boolean modificar(Cuenta cuenta) {
		if(cuenta.getDni().trim().length()>0 && cuenta.getFechaCreacion() != null && cuenta.getNumero().trim().length()>0 &&
				cuenta.getTipoCuenta()!=null && cuenta.getCBU().trim().length()>0 && cuenta.getEstado().trim().length()>0 &&
				cuenta.getSaldo()>=0)
		{
			return cDao.insertar(cuenta);
		}
		return false;
	}

	@Override
	public ArrayList<Cuenta> listar() {
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
	
	

}
