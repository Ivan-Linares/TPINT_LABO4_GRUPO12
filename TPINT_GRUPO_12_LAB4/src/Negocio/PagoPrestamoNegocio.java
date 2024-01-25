package Negocio;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.PagoPrestamo;

public interface PagoPrestamoNegocio 
{
	public PagoPrestamo setPagoPrestamo(ResultSet rs);
	public boolean insertar(PagoPrestamo pago);
	public boolean eliminar(PagoPrestamo pago);
	public boolean modificar(PagoPrestamo pago);
	
	public PagoPrestamo getPrestamoPorID(int idPago);
	
	public ArrayList<PagoPrestamo> ListarPagosPorCliente(String DNI);
	public ArrayList<PagoPrestamo> listar();
	public ArrayList<PagoPrestamo> listarPagosPendientes();
}
