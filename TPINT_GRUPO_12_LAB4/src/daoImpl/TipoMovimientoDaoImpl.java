package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.TipoCuenta;
import Entidad.TipoMovimiento;
import dao.TipoMovimientoDao;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao {
	
	private TipoMovimiento tipoMovimiento;

	@Override
	public boolean insertar(TipoMovimiento tm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(TipoMovimiento tm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(TipoMovimiento tm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoMovimiento> filtar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TipoMovimiento> listar() {
		ArrayList<TipoMovimiento> lista = new ArrayList<TipoMovimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select Tipo_movimiento, Descripcion from Tipos_Movimientos");
			while(rs.next()) {
				iniciar();
				tipoMovimiento.setCode(rs.getInt("Tipo_movimiento"));
				tipoMovimiento.setName(rs.getString("Descripcion"));
				
				lista.add(tipoMovimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public TipoMovimiento getPorID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void iniciar() {
		tipoMovimiento = new TipoMovimiento();
	}
	
	
}
