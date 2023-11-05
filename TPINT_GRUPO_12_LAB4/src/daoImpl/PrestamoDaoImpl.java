package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import Entidad.Prestamo;
import dao.PrestamoDao;

public class PrestamoDaoImpl implements PrestamoDao {

	@Override
	public boolean insertar(Prestamo prestamo) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into prestamos values(?,?,?,?,?,?)");
			statement.setInt(1, prestamo.getIDPrestamo());
			statement.setString(2, prestamo.getCuenta().getNumero());
			//Quizas es mejor trabajar las fechas como string en la DB
			statement.setString(3, prestamo.getFecha().toString());
			//En caso de que las trabajemos como Date:
			//statement.setDate(3, Date.valueOf(prestamo.getFecha()));
			statement.setDouble(4, prestamo.getImporteSolicitado());
			statement.setInt(5, prestamo.getPlazoPagoMeses());
			statement.setString(6, prestamo.getEstado());
			
			if(statement.executeUpdate() > 0) {
				con.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
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
