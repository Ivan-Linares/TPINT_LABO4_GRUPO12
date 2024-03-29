package NegocioImpl;

import java.util.ArrayList;

import Entidad.Cuenta;
import Excepciones.FondosInsuficientesEx;
import Negocio.CuentaNegocio;
import dao.CuentaDao;
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

	@Override
	public ArrayList<Cuenta> CuentasAsociadas(String nc) {
		cDao = new CuentaDaoImpl();
		return cDao.CuentasAsociadas(nc);
	}

	@Override
	public String BuscarCteporCuenta(String numerocuenta) {
		cDao = new CuentaDaoImpl();
		return cDao.BuscarCteporCuenta(numerocuenta);
	}

	@Override
	public ArrayList<Cuenta> CuentasxDNI(String dni) {
		cDao = new CuentaDaoImpl();
		return cDao.CuentasxDNI(dni);
	}

	@Override
	public ArrayList<Cuenta> listaFiltrada(String dato, String campo) {
		cDao = new CuentaDaoImpl();
		return cDao.listaFiltrada(dato, campo);
	}

	@Override
	public boolean insert(String DNI, int tc) {
		cDao = new CuentaDaoImpl();
		return cDao.insert(DNI, tc);
	}

	@Override
	public boolean CuentasPendientes(String DNI) {
		cDao = new CuentaDaoImpl();
		return cDao.CuentasPendientes(DNI);
	}

	@Override
	public ArrayList<Cuenta> ListarCuentasPendientes() {
		cDao = new CuentaDaoImpl();
		return cDao.ListarCuentasPendientes();
	}

	@Override
	public boolean Transferencia(int origen, int destino, float monto) throws FondosInsuficientesEx {
		cDao = new CuentaDaoImpl();
		return cDao.Transferencia(origen, destino, monto);
	}

	@Override
	public int Cuenta_x_CBU(String cbu) {
		cDao = new CuentaDaoImpl();
		return cDao.Cuenta_x_CBU(cbu);
	}

	@Override
	public int Cuenta_x_DNI(String DNI) {
		cDao = new CuentaDaoImpl();
		return cDao.Cuenta_x_DNI(DNI);
	}	
}
