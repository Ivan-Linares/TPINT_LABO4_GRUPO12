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

	@Override
	public Cliente getClientePorDNI(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cliente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
