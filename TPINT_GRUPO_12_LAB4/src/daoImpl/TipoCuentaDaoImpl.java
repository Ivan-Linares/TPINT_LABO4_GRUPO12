package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.TipoCuenta;
import dao.TipoCuentaDao;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
	
	private TipoCuenta tipoCuenta;

	@Override
	public boolean insertar(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoCuenta> filtar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TipoCuenta> listar() {
		ArrayList<TipoCuenta> lista = new ArrayList<TipoCuenta>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select Tipo_De_Cuenta, Descripcion from Tipos_Cuentas");
			while(rs.next()) {
				iniciar();
				tipoCuenta.setCode(rs.getInt("Tipo_De_Cuenta"));
				tipoCuenta.setName(rs.getString("Descripcion"));
				
				lista.add(tipoCuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void iniciar() {
		tipoCuenta = new TipoCuenta();
	}

	@Override
	public TipoCuenta getPorID(int id) {
		TipoCuenta tipoCuenta = new TipoCuenta();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Tipos_Cuentas WHERE Tipo_de_cuenta = " + id);
			while (rs.next()) {
				tipoCuenta.setCode(rs.getInt("Tipo_De_Cuenta"));
				tipoCuenta.setName(rs.getString("Descripcion"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCuenta;
	}

}
