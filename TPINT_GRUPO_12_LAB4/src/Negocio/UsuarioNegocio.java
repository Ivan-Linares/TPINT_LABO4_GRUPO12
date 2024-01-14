package Negocio;

import Entidad.Usuario;

public interface UsuarioNegocio {

	public boolean insertar (Usuario usuario);
	public boolean insertar2 (Usuario usuario);
	public boolean validar(String usuario, String pass);
	public boolean verificarExistencia(String usuario);
	public Usuario obtenerUsuario(String usuario);
	public Usuario asignarCliente(Usuario user);
	public boolean actualizarUsuario(Usuario user);
	public Usuario obtenerUsuarioDNI(String DNI);
	public boolean habilitarUsuario (Usuario user);
}
