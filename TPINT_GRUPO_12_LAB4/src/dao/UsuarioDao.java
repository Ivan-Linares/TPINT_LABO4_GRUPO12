package dao;

import java.util.ArrayList;

import Entidad.Usuario;

public interface UsuarioDao {
	
	public boolean insertar(Usuario usuario);
	public boolean eliminar(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public Usuario getUsuarioPorDNI(String dni);
	public Usuario getUsuarioPorUsuario(String usuario);

}
