package NegocioImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.PagoPrestamo;
import Negocio.PagoPrestamoNegocio;
import dao.PagoPrestamoDAO;
import daoImpl.PagoPrestamoDAOImpl;

public class PagoPrestamoNegocioImpl implements PagoPrestamoNegocio
{
	private PagoPrestamoDAO PagoDAO;
	@Override
	public PagoPrestamo setPagoPrestamo(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(PagoPrestamo pago) 
	{
		PagoDAO = new PagoPrestamoDAOImpl();
		
		if(PagoDAO.insertar(pago))
			return true;
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
		PagoDAO = new PagoPrestamoDAOImpl();
		return PagoDAO.ListarPagosPorCliente(DNI);
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
