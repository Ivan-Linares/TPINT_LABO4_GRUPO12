package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entidad.Cuenta;
import Entidad.Fecha;
import Entidad.TipoCuenta;
import dao.CuentaDao;

public class CuentaDaoImpl implements CuentaDao {

	@Override
	public boolean insertar(Cuenta cuenta) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		LocalDate localDate = LocalDate.now();
        java.sql.Date fecha = java.sql.Date.valueOf(localDate);
        fecha.setDate(cuenta.getFechaCreacion().getDia());
        fecha.setMonth(cuenta.getFechaCreacion().getMes());
        fecha.setYear(cuenta.getFechaCreacion().getYear());
        
		try {
			statement = con.prepareStatement("INSERT into cuentas values(?,?,?,?,?,?,?)");
			statement.setString(1, cuenta.getNumero());
			statement.setString(2, cuenta.getDni());
			statement.setDate(3, fecha);
			statement.setInt(4, cuenta.getTipoCuenta().getCode());
			statement.setString(5, cuenta.getCBU());
			statement.setDouble(6, 10000.00);
			statement.setString(7, cuenta.getEstado());
			
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
			statement = con.prepareStatement("Update Cuentas SET Tipo_De_Cuenta=?, DNI=?, Estado=?,CBU=? Where Cuenta=?");
			statement.setInt(1, cuenta.getTipoCuenta().getCode());
			statement.setString(2, cuenta.getDni());
			statement.setString(3, cuenta.getEstado());
			statement.setString(4, cuenta.getCBU());
			statement.setString(5, cuenta.getNumero());
			
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
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.Estado='a';");
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setDni(rs.getString("DNI"));
				cuenta.setNumero(rs.getString("Cuenta"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				TipoCuenta obj= new TipoCuenta();
				obj.setCode(rs.getInt("tdc"));
				obj.setName(rs.getString("descripcion"));
				cuenta.setTipoCuenta(obj);
				
				lista.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
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

	@Override
	public ArrayList<Cuenta> listarPorCuenta(String numero) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			String query="SELECT DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.cuenta=?;";
			PreparedStatement pst= cn.prepareStatement(query);
			pst.setString(1, numero);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setDni(rs.getString("DNI"));
				cuenta.setNumero(rs.getString("Cuenta"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				TipoCuenta obj= new TipoCuenta();
				obj.setCode(rs.getInt("tdc"));
				obj.setName(rs.getString("descripcion"));
				cuenta.setEstado(rs.getString("estado"));
				cuenta.setTipoCuenta(obj);
				
				lista.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
}
