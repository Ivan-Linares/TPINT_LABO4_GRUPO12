package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.TipoCuenta;
import Negocio.MovimientoNegocio;
import NegocioImpl.Movimiento_NegocioImpl;
import dao.CuentaDao;
import Excepciones.FondosInsuficientesEx;;

public class CuentaDaoImpl implements CuentaDao {
	private final int tipoMovimientoExtraccion=4;
	private final int tipoMovimientoDeposito=5;

	@Override
public boolean insert(String DNI, int tc) {
		Connection con = null;
		try {
			PreparedStatement statement;
			con = Conexion.getConexion().getSQLConexion();
			statement = con.prepareStatement("INSERT INTO Cuentas (DNI, Tipo_De_Cuenta) VALUES(?,?)");
			statement.setString(1, DNI);
			statement.setInt(2, tc);
			
			if(statement.executeUpdate()>0) {
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
	
	public boolean insertar(Cuenta cuenta) {
		
		String cbu = this.ultimoCBU();
		
		Connection con = null;
		
		try {
			PreparedStatement statement;
			con = Conexion.getConexion().getSQLConexion();
			
			statement = con.prepareStatement("INSERT INTO Cuentas (DNI, Fecha_creacion, Tipo_De_Cuenta, CBU, Saldo, Estado) VALUES(?,?,?,?,?,?)");
			
			statement.setString(1, cuenta.getDni());
			statement.setString(2, cuenta.getFechaCreacion().toString());
			statement.setInt(3, cuenta.getTipoCuenta().getCode());
			statement.setString(4, cbu);
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
		cuenta.setTipoCuenta(new TipoCuentaDaoImpl().getPorID(rs.getInt("Tipo_De_Cuenta")));
		cuenta.setEstado(rs.getString("Estado"));
		//Fecha f = new Fecha(LocalDateTime.parse(rs.getString("Fecha_creacion"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		//cuenta.setFechaCreacion(f);
		cuenta.setFechaCreacion(rs.getDate("FechaNac"));
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

	@Override
	public String ultimoCBU() {

		 String cbu = "0";
		    
		    try (Connection cn = Conexion.getConexion().getSQLConexion();
		         Statement st = cn.createStatement();
		         ResultSet rs = st.executeQuery("SELECT CBU FROM CUENTAS ORDER BY 1 DESC LIMIT 1")) {

		        if (rs.next()) {
		            cbu = rs.getString("CBU");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    finally {
		    	 try {
		             Conexion.instancia.cerrarConexion(); 
		         } catch (Exception e) {
		             e.printStackTrace();
		         }
		    }
		    
			cbu.replaceAll("^0+", "");
			int numero = Integer.parseInt(cbu);
		    numero++;
		    cbu = String.format("%024d", numero);

		    return cbu;
	}

	@Override
	public ArrayList<Cuenta> CuentasAsociadas(String nc) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			String query="SELECT c.DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta inner join clientes cl on c.DNI=cl.DNI inner join usuarios u on cl.Usuario= u.Usuario where c.Estado='A' AND u.Usuario like '" + nc + "' ORDER BY c.Cuenta asc";
			PreparedStatement pst= cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setDni(rs.getString("DNI"));
				cuenta.setNumero(rs.getString("Cuenta"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				String fechaStr = rs.getString("Fecha_creacion");
				java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			//	LocalDate localDate = fechaSQL.toLocalDate();
					
				cuenta.setFechaCreacion(fechaSQL);
				
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
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return lista;
	}

	@Override
	public String BuscarCteporCuenta(String numerocuenta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String dni;
		try {
			Statement st = cn.createStatement();
			String query="Select DNI from cuentas where Cuenta='" + numerocuenta + "'";
			PreparedStatement pst= cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				dni = rs.getString("DNI");
				return dni;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return null;
	}

	@Override
	public ArrayList<Cuenta> CuentasxDNI(String dni) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			String query="SELECT c.DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.Estado='A' AND c.DNI='"+ dni +"' ORDER BY c.Cuenta asc;";
			PreparedStatement pst= cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setDni(rs.getString("DNI"));
				cuenta.setNumero(rs.getString("Cuenta"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				String fechaStr = rs.getString("Fecha_creacion");
				java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
				LocalDate localDate = fechaSQL.toLocalDate();
					
				cuenta.setFechaCreacion(fechaSQL);
				
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
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listaFiltrada(String dato, String campo) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			String complemento=" and "+ campo +" like '"+ dato +"%'";
			String query="SELECT DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.Estado='a'";
			ResultSet rs = st.executeQuery(query+complemento);
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

	@Override
	public boolean CuentasPendientes(String DNI) {	
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("SELECT c.DNI FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.Estado='P' AND c.DNI='"+ DNI +"';");
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				return true;
				}
			}
			catch (Exception e) {
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
	public ArrayList<Cuenta> ListarCuentasPendientes() {
		Connection cn = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try {
			Statement st = cn.createStatement();
			String query="SELECT DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta where c.Estado='P'";
			ResultSet rs = st.executeQuery(query);
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

	public Cuenta GetCuentaxID(int idCuenta) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();		
		Cuenta cuenta = new Cuenta();
		
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DNI, Cuenta, Fecha_creacion, c.Tipo_De_Cuenta as tdc, tc.descripcion, c.cbu, c.saldo, c.estado "
					+ "FROM cuentas c inner join tipos_cuentas tc on c.Tipo_De_Cuenta=tc.Tipo_De_Cuenta "
					+ "where c.Estado='a' and Cuenta =" + idCuenta + ";");
			while(rs.next()) {
				
				cuenta.setCBU(rs.getString("CBU"));
				cuenta.setDni(rs.getString("DNI"));
				cuenta.setNumero(rs.getString("Cuenta"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				
				TipoCuenta obj= new TipoCuenta();
				obj.setCode(rs.getInt("tdc"));
				obj.setName(rs.getString("descripcion"));
				cuenta.setTipoCuenta(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuenta;
	}
	
	@Override
	public boolean Transferencia(int origen, int destino, float monto) throws FondosInsuficientesEx {
		MovimientoNegocio mn = new Movimiento_NegocioImpl();
		if (origen==destino) {
			return false;
		}
		if (debito(origen, monto) && mn.insertar(origen,monto, tipoMovimientoExtraccion)) {
			
			if (credito(destino, monto) && mn.insertar(destino, monto, tipoMovimientoDeposito)) {
					return true;
			}
		}			
		return false;
	}
	
	public boolean debito(int origen, double d) throws FondosInsuficientesEx {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		Cuenta cuenta = GetCuentaxID(origen);
		
		if(cuenta.getSaldo() > d) {
		
			try {
				statement = con.prepareStatement("UPDATE cuentas set Saldo=(Saldo)-? where Cuenta=?;");
				statement.setDouble(1, d);
				statement.setInt(2, origen);
				
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
		else {
			throw new FondosInsuficientesEx();
		}
		return false;
	}
	
	
	public boolean credito(int destino, float monto) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("UPDATE cuentas set Saldo=(Saldo)+? where Cuenta=?;");
			statement.setFloat(1, monto);
			statement.setInt(2, destino);
			
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
	public int Cuenta_x_CBU(String cbu) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int cuenta=0;
		try {
			Statement st = cn.createStatement();
			String query="SELECT c.cuenta FROM cuentas c where c.Estado='A' AND c.CBU="+ cbu +";";
			PreparedStatement pst= cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				cuenta = rs.getInt("cuenta");
				return cuenta;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return cuenta;
	}
	public int Cuenta_x_DNI(String DNI) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int cuenta=0;
		try {
			Statement st = cn.createStatement();
			String query="SELECT c.cuenta FROM cuentas c where c.Estado='P' AND c.DNI="+ DNI +" order by c.Fecha_creacion desc LIMIT 1;";
			PreparedStatement pst= cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				cuenta = rs.getInt("cuenta");
				return cuenta;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		return cuenta;
	}

}
