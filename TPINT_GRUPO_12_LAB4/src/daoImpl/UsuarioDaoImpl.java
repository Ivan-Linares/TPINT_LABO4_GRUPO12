package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entidad.Fecha;
import Entidad.TipoUsuario;
import Entidad.Usuario;
import dao.UsuarioDao;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public boolean insertar(Usuario usuario) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into usuarios values(?,?,?,?)");
			statement.setString(1,usuario.getUser());
			statement.setString(2, usuario.getPass());
			statement.setInt(3, usuario.getTipoUsuario().getTipo());
			statement.setBoolean(4, usuario.getEstado());
			
			
			if(statement.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		}
		
		catch (Exception e){
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
	public boolean eliminar(Usuario usuario) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Usuarios Set Estado=? where Usuario=?");
			statement.setBoolean(1, usuario.getEstado());
			statement.setString(2, usuario.getUser());
			
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
	public boolean modificar(Usuario usuario) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Usuarios Set Estado=?, Password=? where Usuario=?");
			statement.setBoolean(1, usuario.getEstado());
			statement.setString(2, usuario.getPass());
			statement.setString(2, usuario.getUser());
			
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
	public Usuario getUsuarioPorDNI(String dni) {
		
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Usuario usuario=new Usuario();
		
		try {
			statement = con.prepareStatement("Select * From Usuarios u inner join clientes c on c.Usuario=u.Usuario inner join Tipos_Usuarios ts on ts.Tipo_usuario=u.Tipo_usuario where c.DNI=?");
			statement.setString(1, dni);
			
			ResultSet r=statement.executeQuery();
			usuario=setUsuario(r);
			
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
		
		return usuario;
	}

	@Override
	public Usuario getUsuarioPorUsuario(String user) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Usuario usuario=new Usuario();
		
		try {
			statement = con.prepareStatement("Select * From Usuarios u inner join Tipos_Usuarios ts on ts.Tipo_usuario=u.Tipo_usuario where u.Usuario=?");
			statement.setString(1, user);
			
			ResultSet r=statement.executeQuery();
			usuario=setUsuario(r);
			
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
		
		return usuario;
	}
	
	private Usuario setUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		TipoUsuario tipo = new TipoUsuario();
		
		while (rs.next()) {
			boolean e=rs.getBoolean("Estado");
			usuario.setEstado(rs.getBoolean("Estado"));
			usuario.setPass(rs.getString("Password"));
			usuario.setUser(rs.getString("u.Usuario"));
			tipo.setTipo(rs.getInt("ts.Tipo_Usuario"));
			tipo.setDescripcion(rs.getString("ts.Descripcion"));
			usuario.setTipoUsuario(tipo);
		}

		return usuario;
		
	}


}
