package daoImpl;

import java.util.ArrayList;

import com.sun.corba.se.pept.transport.Connection;

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
		// TODO Auto-generated method stub
		return null;
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
				obj = setmovimientos(rs);
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
	private Movimiento setmovimientos(ResultSet rs) {
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
			LocalDate localDate = fechaSQL.toLocalDate();
			
			Fecha f = new Fecha();
			f.setDia(localDate.getDayOfMonth());
			f.setMes(localDate.getMonthValue());
			f.setYear(localDate.getYear());	
			obj.setFechaTef(f);
			
			return obj;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
