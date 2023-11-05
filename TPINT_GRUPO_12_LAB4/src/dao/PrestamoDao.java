package dao;

import java.util.ArrayList;

import Entidad.Prestamo;

public interface PrestamoDao {
	
	public boolean insertar(Prestamo prestamo);
	public boolean eliminar(Prestamo prestamo);
	public boolean modificar(Prestamo prestamo);
	public Prestamo getPrestamoPorID(int idPrestamo);
	public ArrayList<Prestamo> listar();
	public ArrayList<Prestamo> getPrestamosPorDNI(int dni);
	
	//Para reportes
	public int cantidad_totalPrestamos(String desde, String hasta);
	public int cantidad_prestamosSolicitados(String desde, String hasta);
	public int cantidad_prestamosAprobados(String desde,String hasta);
	public int cantidad_prestamosRechazados(String desde, String hasta);
	public Double promedio_prestamos(String desde, String hasta);

}
