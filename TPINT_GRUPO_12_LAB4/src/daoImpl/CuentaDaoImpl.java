package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entidad.Cuenta;
import Entidad.Fecha;
import dao.CuentaDao;

public class CuentaDaoImpl implements CuentaDao {

	@Override
	public boolean insertar(Cuenta cuenta) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into cuentas values(?,?,?,?,?,?)");
			statement.setString(1, cuenta.getNumero());
			statement.setString(2, cuenta.getFechaCreacion().toString());
			statement.setInt(3, cuenta.getTipoCuenta().getCode());
			statement.setString(4, cuenta.getCBU());
			statement.setDouble(5, 10000.00);
			statement.setString(6, cuenta.getEstado());
			
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
	public boolean eliminar(Cuenta cuenta) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas Set Estado=? where Cuenta=?");
			statement.setString(1, cuenta.getEstado());
			statement.setString(2, cuenta.getNumero());
			
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
	public boolean modificar(Cuenta cuenta) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas SET Tipo_De_Cuenta=?, Saldo=?, Estado=? Where Cuenta=?");
			statement.setInt(1, cuenta.getTipoCuenta().getCode());
			statement.setDouble(2, cuenta.getSaldo());
			statement.setString(3, cuenta.getEstado());
			statement.setString(4, cuenta.getNumero());
			
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
	public ArrayList<Cuenta> listar() {
		
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cuentas;");
			while(rs.next()) {
				cuenta = setCuenta(rs, cuenta);
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
		
	}
	
	private Cuenta setCuenta(ResultSet rs, Cuenta cuenta) throws SQLException {
		cuenta = new Cuenta();
		cuenta.setCBU(rs.getString("CBU"));
		cuenta.setTipoCuenta(new TipoCuentaDaoImpl().getPorID(rs.getInt("Tipo_De_Cuenta")));
		cuenta.setEstado(rs.getString("Estado"));
		Fecha f = new Fecha(LocalDateTime.parse(rs.getString("Fecha_creacion"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		cuenta.setFechaCreacion(f);
		cuenta.setNumero(rs.getString("Cuenta"));
		cuenta.setSaldo(rs.getFloat("Saldo"));
		//En caso de que creamos necesario agregar DNI a la tabla cuentas:
		//cuenta.setUsuario(new UsuarioDaoImpl().getUsuarioPorDNI(rs.getString("DNI")));
		return cuenta;
	}

	@Override
	public ArrayList<Cuenta> listar(String b, int busqueda) {
		
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		if (busqueda==1) {
			try {
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM cuentas;");
				while(rs.next()) {
					cuenta = setCuenta(rs, cuenta);
					cuentas.add(cuenta);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (busqueda==2) {
			try {
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM cuentas;");
				while(rs.next()) {
					cuenta = setCuenta(rs, cuenta);
					cuentas.add(cuenta);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (busqueda==3) {
			try {
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM cuentas;");
				while(rs.next()) {
					cuenta = setCuenta(rs, cuenta);
					cuentas.add(cuenta);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cuentas;
	}


	@Override
	public int total_cuentas(int dni) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
