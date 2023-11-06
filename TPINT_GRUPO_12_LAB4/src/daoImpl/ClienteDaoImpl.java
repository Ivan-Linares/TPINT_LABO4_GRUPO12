package daoImpl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Entidad.Cliente;
import Entidad.Fecha;
import Entidad.Pais;
import Entidad.Provincia;
import dao.ClienteDao;

public class ClienteDaoImpl implements ClienteDao{

	@Override
	public boolean insertar(Cliente cliente) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		//metodo insertar usuario antes??
		
		try {
			statement = con.prepareStatement("INSERT into clientes values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getCuil());
			statement.setString(3, cliente.getApellido());
			statement.setString(4, cliente.getNombre());
			statement.setString(5, cliente.getSexo());
			statement.setString(6, cliente.getFechaNac().toString());
			statement.setString(7, cliente.getDireccion());
			statement.setString(8, cliente.getLocalidad());
			statement.setInt(9, cliente.getProv().getCode());
			statement.setInt(10, cliente.getPais().getCode());
			statement.setString(11, cliente.getEmail());
			statement.setString(12, cliente.getUsuario());
			statement.setString(13, cliente.getEstado());
			
			
			
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
			statement = con.prepareStatement("Update Cuentas SET Cuil=?, Apellido=?, Nombre=?, Sexo=?, FechaNac=?, Direccion=?, Localidad=?, Provincia=?, Pais=?, Email=?, Usuario=?, Estado=? Where DNI=?");
			statement.setString(1, cliente.getCuil());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getSexo());
			statement.setString(5, cliente.getFechaNac().toString());
			statement.setString(6, cliente.getDireccion());
			statement.setString(7, cliente.getLocalidad());
			statement.setInt(8, cliente.getProv().getCode());
			statement.setInt(9, cliente.getPais().getCode());
			statement.setString(10, cliente.getEmail());
			statement.setString(11, cliente.getEstado());
			statement.setString(12, cliente.getUsuario());
			statement.setString(13, cliente.getDni());
			
			
			
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
			ResultSet rs = st.executeQuery("select * from Clientes c inner join Usuarios u on c.Usuario=u.Usuario inner join Provincia p on c.provincia=p.codprovincia inner join Pais p on c.pais=p.codpais;");
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
	public Cliente getClientePorDNI(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}

	private Cliente setCliente(ResultSet rs) throws SQLException {
		
		Cliente cliente = new Cliente();
		Provincia pro =new Provincia();
		Pais pais=new Pais();
		cliente.setDni(rs.getString("DNI"));
		cliente.setCuil(rs.getString("Cuil"));
		cliente.setApellido(rs.getString("Apellido"));
		cliente.setNombre(rs.getString("Nombre"));
		cliente.setCuil(rs.getString("Cuil"));
		cliente.setDireccion(rs.getString("Direccion"));
		cliente.setLocalidad(rs.getString("Localidad"));
		cliente.setDireccion(rs.getString("Direccion"));
		cliente.setUsuario(rs.getString("Usuario"));
		pro.setName(rs.getString("NombreProvincia"));
		pro.setCode((rs.getInt("codProvincia")));
		cliente.setProv(pro);
		pais.setCode((rs.getInt("codPais")));
		pais.setName(rs.getString("NombrePais"));
		cliente.setPais(pais);
		cliente.setPass(rs.getString("Password"));
		cliente.setEstado(rs.getString("Estado"));
		Fecha f = new Fecha(LocalDateTime.parse(rs.getString("Fecha_creacion"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		cliente.setFechaNac(f);
		return cliente;
	}
}