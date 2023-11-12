package Negocio;

import Entidad.Cliente;
import Entidad.Usuario;

public interface UsuarioNegocio {

	public boolean insertar (Usuario usuario);
	public boolean validar(String usuario, String pass);
	public boolean verificarExistencia(String usuario);
	public Usuario obtenerUsuario(String usuario);
}
