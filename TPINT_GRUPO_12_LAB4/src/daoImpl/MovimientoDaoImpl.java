package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Movimiento;
import Entidad.TipoMovimiento;
import dao.movimientoDao;

public class MovimientoDaoImpl implements movimientoDao{

	@Override
	public boolean insertar(int cuenta, float importe, int tipoMovimiento) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT INTO Movimientos (Cuenta, IMPORTE, Tipo_movimiento) VALUES (?, ?, ?)");
			statement.setInt(1, cuenta);
			statement.setFloat(2, importe);
			statement.setInt(3, tipoMovimiento);
			
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
	public boolean eliminar(Movimiento mov) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Movimiento mov) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movimiento getMovimientoPorID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> getMovimientosByDNI(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> listar() {
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Movimiento obj= new Movimiento();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * FROM movimientos m inner join tipos_movimientos tm on m.Tipo_movimiento=tm.Tipo_movimiento");
			while(rs.next()) {
				obj = setMovimientos(rs);
				movimientos.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return movimientos;
	}

	@Override
	public int cantMovimientos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Movimiento> listarXcuenta(String cuenta) {
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Movimiento obj= new Movimiento();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * FROM movimientos m inner join tipos_movimientos tm on m.Tipo_movimiento=tm.Tipo_movimiento where m.Cuenta='" + cuenta + "' order by m.ID desc;");
			while(rs.next()) {
				obj = setMovimientos(rs);
				movimientos.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return movimientos;
	}
	
	private Movimiento setMovimientos(ResultSet rs) {
		try {
			Movimiento obj = new Movimiento();
			TipoMovimiento tm = new TipoMovimiento();
			
			obj.setCuenta(rs.getString("Cuenta"));
			obj.setImporte(rs.getFloat("Importe"));
			tm.setCode(rs.getInt("Tipo_movimiento"));
			tm.setName(rs.getString("Descripcion"));
			obj.setTipoMovimiento(tm);
			
			String fechaStr = rs.getString("Fecha");
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			obj.setFechaTef(fechaSQL);
			
			return obj;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Double totalTipoMov(String fechaInicio, String fechaFin, int tipoMovimiento) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		Double total = (double) 2;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT SUM(Importe) AS Total FROM Movimientos WHERE Fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' AND Tipo_Movimiento = '"+tipoMovimiento+"'");
			while(rs.next()) {
				total = rs.getDouble("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public ArrayList<Movimiento> listarFiltrada(String cuenta, int importe, int tipoMov) {
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Movimiento obj= new Movimiento();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * FROM movimientos m inner join tipos_movimientos tm on m.Tipo_movimiento=tm.Tipo_movimiento where m.Cuenta='" + cuenta + "' AND m.Importe>='"+ importe +"' and m.Tipo_movimiento='"+ tipoMov +"' order by m.fecha desc;");
			while(rs.next()) {
				obj = setMovimientos(rs);
				movimientos.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return movimientos;
	}

}
