package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.PagoPrestamo;
import Entidad.Prestamo;
import dao.PagoPrestamoDAO;

public class PagoPrestamoDAOImpl implements PagoPrestamoDAO {

	@Override
	public PagoPrestamo setPagoPrestamo(ResultSet rs) 
	{
		PagoPrestamo PagoPrestamo = new PagoPrestamo();
		try 
		{
			PagoPrestamo.setIDPagoPrestamo(rs.getInt("ID_Pago"));
			PagoPrestamo.setPrestamo(rs.getInt("ID_Prestamo"));
			PagoPrestamo.setCuenta(rs.getInt("Cuenta"));
			PagoPrestamo.setNroCuota(rs.getInt("NumeroCuota"));
			PagoPrestamo.setImporteCuota(rs.getDouble("Importe_cuota"));
			String fechaStr = rs.getString("Fecha");
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			PagoPrestamo.setFecha(fechaSQL);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return PagoPrestamo;
	}

	@Override
	public boolean insertar(PagoPrestamo pago) 
	{
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT INTO PagoPrestamos (ID_Prestamo, Cuenta, NumeroCuota, Importe_cuota, Fecha) VALUES (?,?,?,?,?)");
			
			statement.setInt(1, pago.getIDPagoPrestamo());
			statement.setInt(2, pago.getCuenta());
			statement.setInt(3, pago.getNroCuota());
			statement.setDouble(4, pago.getImporteCuota());
			statement.setString(5, pago.getFecha().toString());

			if(statement.executeUpdate() > 0) 
			{
				con.commit();
				return true;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			try 
			{
				con.rollback();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
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
	public ArrayList<PagoPrestamo> ListarPagosPorCliente(String DNI) 
	{
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		PagoPrestamo PagoPrestamo = new PagoPrestamo();
		ArrayList<PagoPrestamo> listPrestamo = new ArrayList<PagoPrestamo>();
		
		try {
			statement = con.prepareStatement("select ID_Pago, ID_Prestamo, Cuenta, NumeroCuota, Importe_cuota, Fecha "
					+ "from PagoPrestamos PP inner join Prestamos Pres on PP.ID_Prestamo = Pres.ID_Prestamo"
					+ "where pres.DNI = ?;");
			statement.setString(1, DNI);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) 
			{
				PagoPrestamo = setPagoPrestamo(rs);
				listPrestamo.add(PagoPrestamo);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPrestamo;
	}

	@Override
	public ArrayList<PagoPrestamo> listar() {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		PagoPrestamo PagoPrestamo = new PagoPrestamo();
		ArrayList<PagoPrestamo> listPrestamo = new ArrayList<PagoPrestamo>();
		
		try {
			statement = con.prepareStatement("select ID_Pago, ID_Prestamo, Cuenta, NumeroCuota, Importe_cuota, Fecha from PagoPrestamos");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) 
			{
				PagoPrestamo = setPagoPrestamo(rs);
				listPrestamo.add(PagoPrestamo);
			}
			 rs.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return listPrestamo;
	}

	@Override
	public ArrayList<PagoPrestamo> listarPagosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
