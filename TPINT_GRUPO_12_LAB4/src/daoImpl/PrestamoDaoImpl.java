package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import Entidad.Cliente;
import Entidad.PagoPrestamo;
import Entidad.Prestamo;
import dao.PrestamoDao;

public class PrestamoDaoImpl implements PrestamoDao {

	@Override
	public boolean insertar(Prestamo prestamo) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT INTO Prestamos (Cuenta, dni, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado) VALUES (?,?,?,?,?,?,?,?,?)");
			
			statement.setInt(1, prestamo.getNroCuenta());
			statement.setString(2, prestamo.getDNI());
			statement.setString(3, prestamo.getFecha().toString());
			statement.setDouble(4, prestamo.getImporteSolicitado());
			statement.setDouble(5, prestamo.getImporteTotal());
			statement.setDouble(6, prestamo.getImporteMensual());
			statement.setInt(7, prestamo.getCantCuotas());
			statement.setInt(8, prestamo.getCuotasRestantes());
			statement.setString(9, "P");
			
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
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Prestamo prestamo = new Prestamo();
		String ID = String.valueOf(idPrestamo);
		
		try {
			statement = con.prepareStatement(" select ID_Prestamo, DNI, Cuenta, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado from prestamos where ID_Prestamo = ?; ");
			statement.setString(1, ID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPrestamo(rs);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}

	@Override
	public ArrayList<Prestamo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> getPrestamoDNICliente(String dni) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Prestamo prestamo = new Prestamo();
		ArrayList<Prestamo> listPrestamo = new ArrayList<Prestamo>();
		
		try {
			statement = con.prepareStatement(" select ID_Prestamo, DNI, Cuenta, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado from prestamos where DNI = ?; ");
			statement.setString(1, dni);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPrestamo(rs);
				listPrestamo.add(prestamo);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPrestamo;
	}
	
	public ArrayList<Prestamo> getPrestamoActivosDNICliente(String dni) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Prestamo prestamo = new Prestamo();
		ArrayList<Prestamo> listPrestamo = new ArrayList<Prestamo>();
		
		try {
			statement = con.prepareStatement(" select ID_Prestamo, DNI, Cuenta, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado from prestamos where Estado = 'A' AND DNI = ?; ");
			statement.setString(1, dni);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPrestamo(rs);
				listPrestamo.add(prestamo);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPrestamo;
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

	public boolean PagarCuota(int ID, int CoutasRestantes) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		String IDPrestamo = String.valueOf(ID);
		String Coutas = String.valueOf(CoutasRestantes - 1);
		
		if(CoutasRestantes == 1) {
			try {
				statement = con.prepareStatement("Update Prestamos set Estado='E', cuotas_pendientes=? Where ID_Prestamo=?");
				statement.setString(1, Coutas);
				statement.setString(2, IDPrestamo);
				
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
		}
		else if(CoutasRestantes > 1){
			try {
				statement = con.prepareStatement("Update Prestamos set cuotas_pendientes=? Where ID_Prestamo=?");
				statement.setString(1, Coutas);
				statement.setString(2, IDPrestamo);
				
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
		}
		
		return false;
	}
	
	@Override
	public boolean Aprobar(int ID) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		String IDPrestamo = String.valueOf(ID);
		
		try {
			statement = con.prepareStatement("Update Prestamos set Estado='A' Where ID_Prestamo=?");
			statement.setString(1, IDPrestamo);
			
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
	public boolean Rechazar(int ID) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		String IDPrestamo = String.valueOf(ID);
		
		try {
			statement = con.prepareStatement("Update Prestamos set Estado='R' Where ID_Prestamo=?");
			statement.setString(1, IDPrestamo);
			
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
	public ArrayList<Prestamo> listarPendientes() {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Prestamo prestamo = new Prestamo();
		ArrayList<Prestamo> listPrestamo = new ArrayList<Prestamo>();
		
		try {
			statement = con.prepareStatement(" select ID_Prestamo, DNI, Cuenta, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado from prestamos where Estado = 'P'; ");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPrestamo(rs);
				listPrestamo.add(prestamo);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPrestamo;
	}

	@Override
	public Prestamo setPrestamo(ResultSet rs) {
		
		Prestamo prestamo = new Prestamo();
		try {
			prestamo.setIDPrestamo(rs.getInt("ID_Prestamo"));
			prestamo.setDNI(rs.getString("DNI"));
			prestamo.setNroCuenta(rs.getInt("Cuenta"));
			String fechaStr = rs.getString("Fecha");
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			prestamo.setFecha(fechaSQL);
			prestamo.setImporteSolicitado(rs.getDouble("Importe_solicitado"));
			prestamo.setImporteTotal(rs.getDouble("Importe_total"));
			prestamo.setImporteMensual(rs.getDouble("Importe_mensual_a_pagar"));
			prestamo.setCantCuotas(rs.getInt("CantidadCuotas"));
			prestamo.setCuotasRestantes(rs.getInt("cuotas_pendientes"));
			prestamo.setEstado(rs.getString("Estado"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}

	@Override
	public Prestamo getPrestamoPorID(String idPrestamo) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Prestamo prestamo = new Prestamo();
		String ID = String.valueOf(idPrestamo);
		
		try {
			statement = con.prepareStatement(" select ID_Prestamo, DNI, Cuenta, Fecha, Importe_solicitado, Importe_total, "
					+ "Importe_mensual_a_pagar, CantidadCuotas, cuotas_pendientes, Estado from prestamos where ID_Prestamo = ?; ");
			statement.setString(1, ID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPrestamo(rs);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}

	@Override
	public ArrayList<PagoPrestamo> listarPagos(String idPrestamo) {

		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		ArrayList<PagoPrestamo> listaprestamo = new ArrayList<PagoPrestamo>();
		PagoPrestamo prestamo=new PagoPrestamo();
		String ID = String.valueOf(idPrestamo);
		
		try {
			statement = con.prepareStatement(" select ID_Pago, ID_Prestamo, Cuenta, NumeroCuota, Importe_cuota, Fecha from pagoprestamos where ID_Prestamo = ?; ");
			statement.setString(1, ID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				prestamo = setPagoPrestamo(rs);
				listaprestamo.add(prestamo);
			}
			 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaprestamo;
	}
	
	public PagoPrestamo setPagoPrestamo(ResultSet rs) {
		
		PagoPrestamo prestamo = new PagoPrestamo();
		
		try {
			prestamo.setPrestamo(rs.getInt("ID_Prestamo"));
			prestamo.setIDPagoPrestamo(rs.getInt("ID_pago"));
			String fechaStr = rs.getString("Fecha");
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			prestamo.setFecha(fechaSQL);
			prestamo.setImporteCuota(rs.getDouble("Importe_cuota"));
			prestamo.setNroCuota(rs.getInt("NumeroCuota"));
			prestamo.setCuenta(rs.getInt("Cuenta"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}
	
	

}
