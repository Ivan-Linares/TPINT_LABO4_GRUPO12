package daoImpl;

import java.util.ArrayList;

import Entidad.Prestamo;
import dao.PrestamoDao;

public class PrestamoDaoImpl implements PrestamoDao {

	@Override
	public boolean insertar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Prestamo getPrestamoPorID(int idPrestamo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> getPrestamosPorDNI(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidad_totalPrestamos(String desde, String hasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidad_prestamosSolicitados(String desde, String hasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidad_prestamosAprobados(String desde, String hasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidad_prestamosRechazados(String desde, String hasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double promedio_prestamos(String desde, String hasta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
