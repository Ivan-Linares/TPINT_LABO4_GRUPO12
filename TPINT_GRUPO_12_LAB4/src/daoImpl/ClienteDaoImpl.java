package daoImpl;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Entidad.Cliente;
import Entidad.Fecha;
import Entidad.Localidad;
import Entidad.Pais;
import Entidad.Provincia;
import Entidad.Usuario;
import dao.ClienteDao;

public class ClienteDaoImpl implements ClienteDao{

	@Override
	public boolean insertar(Cliente cliente) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		//metodo insertar usuario antes??
		//German: ???
		
		try {
			statement = con.prepareStatement("INSERT into clientes values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getCuil());
			statement.setString(3, cliente.getApellido());
			statement.setString(4, cliente.getNombre());
			statement.setString(5, cliente.getSexo());
			statement.setString(6, cliente.getFechaNac().toString());
			statement.setInt(7, cliente.getLocalidad().getCodLocalidad());
			statement.setString(8, cliente.getDireccion());
			statement.setString(9, cliente.getEmail());
			statement.setString(10, cliente.getUsuario());
			statement.setString(11, cliente.getEstado());			
			
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
	public boolean eliminar(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Cliente cliente) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update clientes SET Cuil=?, Apellido=?, Nombre=?, Sexo=?, FechaNac=?, Localidad=? ,Direccion=?, Email=?, Usuario=?, Estado='A' Where DNI=?");
			statement.setString(1, cliente.getCuil());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getSexo());
			statement.setString(5, cliente.getFechaNac().toString());
			statement.setInt(6, cliente.getLocalidad().getCodLocalidad());
			statement.setString(7, cliente.getDireccion());
			statement.setString(8, cliente.getEmail());
			//statement.setString(9, cliente.getEstado());
			statement.setString(9, cliente.getUsuario());
			statement.setString(10, cliente.getDni());
						
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
	
	public ArrayList<Cliente> listar() {
		
		Cliente cliente = new Cliente();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Clientes c inner join Usuarios u on c.Usuario=u.Usuario inner join Localidades l on l.codLocalidad=c.Localidad inner join Provincia p on l.codProvincia=p.codprovincia inner join Pais pa on p.codpais=pa.codpais;");
			while(rs.next()) {
				cliente = setCliente(rs);
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
		
	}

	@Override
	public Cliente existeDNI(String DNI) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Cliente cliente = new Cliente();
		
		try {
			statement = con.prepareStatement("Select * From Clientes c where c.DNI=?");
			statement.setString(1, DNI);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				cliente.setDni(rs.getString("c.DNI"));
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
		
		return cliente;
	}
	
	

private Cliente setCliente(ResultSet rs){
		try {
			Cliente cliente = new Cliente();
			Localidad l=new Localidad();
			Provincia pro =new Provincia();
			Pais pais=new Pais();
			
			cliente.setDni(rs.getString("c.DNI"));
			cliente.setCuil(rs.getString("c.Cuil"));
			cliente.setApellido(rs.getString("c.Apellido"));
			cliente.setNombre(rs.getString("c.Nombre"));
			cliente.setDireccion(rs.getString("c.Direccion"));
			pais.setCode((rs.getInt("pa.codPais")));
			pais.setName(rs.getString("pa.NombrePais"));
			pro.setNombreProvincia(rs.getString("p.NombreProvincia"));
			pro.setCodProvincia((rs.getInt("p.codProvincia")));
			pro.setPais(pais);
			l.setNombreLocalidad(rs.getString("l.NombreLocalidad"));
			l.setCodLocalidad((rs.getInt("l.codLocalidad")));
			l.setProvincia(pro);
			cliente.setLocalidad(l);
			cliente.setUsuario(rs.getString("c.Usuario"));
			cliente.setSexo(rs.getString("c.Sexo"));
			cliente.setEmail(rs.getString("c.email"));
			cliente.setPass(rs.getString("u.Password"));
			
			/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha= rs.getString("c.FechaNac");
			Date f = (Date) dateFormat.parse(fecha);*/
			
			//LocalDate f = new Fecha(LocalDate.parse(fe,DateTimeFormatter.ofPattern("yyyy-MM-dd")).getDayOfMonth(),LocalDate.parse(fe, DateTimeFormatter.ofPattern("yyyy-MM-dd")).getMonthValue(),LocalDate.parse(fe, DateTimeFormatter.ofPattern("yyyy-MM-dd")).getYear());
			
			String fechaStr = rs.getString("c.FechaNac");
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaStr);
			
			cliente.setFechaNac(fechaSQL);
			cliente.setEstado(rs.getString("c.Estado"));
			
			
			return cliente;			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cliente getClientePorUsuario(Usuario usuario) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Cliente cli=new Cliente();
		
		try {
			statement = con.prepareStatement("select * from Clientes c inner join Usuarios u on c.Usuario=u.Usuario inner join Localidades l on l.codLocalidad=c.Localidad inner join Provincia p on l.codProvincia=p.codprovincia inner join Pais pa on p.codpais=pa.codpais where c.Usuario=?;");
			statement.setString(1, usuario.getUser());
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				cli = setCliente(r);
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
		
		return cli;
	}

	@Override
	public ArrayList<Cliente> listarPendientes() {
		
		Cliente cliente = new Cliente();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Clientes c inner join Usuarios u on c.Usuario=u.Usuario inner join Localidades l on l.codLocalidad=c.Localidad inner join Provincia p on l.codProvincia=p.codprovincia inner join Pais pa on p.codpais=pa.codpais where c.Estado = 'P'");
			while(rs.next()) {
				cliente = setCliente(rs);
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}

	@Override
	public boolean rechazar(String DNI) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Clientes SET Estado='I' Where DNI=?");
			statement.setString(1, DNI);
			
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
	public boolean aprobar(String DNI) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Clientes SET Estado='A' Where DNI=?");
			statement.setString(1, DNI);
			
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
	public boolean baja(String DNI) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Clientes SET Estado='I' Where DNI=?");
			statement.setString(1, DNI);
			
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
	public ArrayList<Cliente> listarActivos() {
		Cliente cliente = new Cliente();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Clientes c inner join Usuarios u on c.Usuario = u.Usuario inner join Localidades l on c.Localidad = l.codLocalidad inner join Provincia p on l.codProvincia = p.codProvincia inner join Pais pa on p.codPais = pa.codPais where c.Estado = 'A'");
			while(rs.next()) {
				cliente = setCliente(rs);
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}

	@Override
	public Cliente Cte_Seleccinado(String DNI) {
		
		Cliente cliente = new Cliente();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Clientes c inner join Usuarios u on c.Usuario = u.Usuario inner join Localidades l on c.Localidad = l.codLocalidad inner join Provincia p on l.codProvincia = p.codProvincia inner join Pais pa on p.codPais = pa.codPais where c.DNI='"+ DNI +"';");
			while(rs.next()) {
				cliente = setCliente(rs);
				return cliente;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
