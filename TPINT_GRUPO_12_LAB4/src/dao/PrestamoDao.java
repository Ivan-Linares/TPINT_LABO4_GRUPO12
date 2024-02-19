package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.PagoPrestamo;
import Entidad.Prestamo;

public interface PrestamoDao {
	
	public Prestamo setPrestamo(ResultSet rs);
	public boolean insertar(Prestamo prestamo);
	public boolean eliminar(Prestamo prestamo);
	public boolean modificar(Prestamo prestamo);
	public boolean Aprobar(int ID);
	public boolean Rechazar(int ID);
	public Prestamo getPrestamoPorID(int idPrestamo);
	public Prestamo getPrestamoPorID(String idPrestamo);
	public ArrayList<Prestamo> getPrestamoDNICliente(String DNI);
	public ArrayList<Prestamo> listar();
	public ArrayList<Prestamo> listarPendientes();
	public ArrayList<PagoPrestamo> listarPagos(String idPrestamo);
	
	
	//Para reportes
	public int cantidad_totalPrestamos(String desde, String hasta);
	public int cantidad_prestamosSolicitados(String desde, String hasta);
	public int cantidad_prestamosAprobados(String desde,String hasta);
	public int cantidad_prestamosRechazados(String desde, String hasta);
	public Double promedio_prestamos(String desde, String hasta);

}
