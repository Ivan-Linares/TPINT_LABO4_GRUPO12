package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Movimiento;
import Entidad.TipoMovimiento;
import dao.movimientoDao;

public class MovimientoDaoImpl implements movimientoDao{

	@Override
	public boolean insertar(Movimiento mov) {
		// TODO Auto-generated method stub
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
			ResultSet rs = st.executeQuery("select * FROM movimientos m inner join tipos_movimientos tm on m.Tipo_movimiento=tm.Tipo_movimiento where m.Cuenta='" + cuenta + "'");
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

}
