package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.PagoPrestamo;
import dao.PagoPrestamoDAO;

public class PagoPrestamoDAOImpl implements PagoPrestamoDAO {

	@Override
	public PagoPrestamo setPagoPrestamo(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(PagoPrestamo pago) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(PagoPrestamo pago) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(PagoPrestamo pago) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PagoPrestamo getPrestamoPorID(int idPago) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PagoPrestamo> ListarPagosPorCliente(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PagoPrestamo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PagoPrestamo> listarPagosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
