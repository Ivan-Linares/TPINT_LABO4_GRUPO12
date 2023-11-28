package Negocio;

import java.util.ArrayList;
import Entidad.Cliente;
import Entidad.Usuario;


public interface ClienteNegocio {
	public boolean insertar (Cliente cliente);
	public boolean modificar (Cliente cliente);
	public boolean verificarExistencia(Cliente cliente);
	public ArrayList<Cliente> listar();
	public ArrayList<Cliente> listarPendientes();
	public Cliente clientePorUsuario(Usuario usuario);
	public Cliente Cte_Seleccionado (String DNI);
	public boolean rechazar(String DNI);
	public boolean aprobar(String DNI);
	public boolean baja(String DNI);
	public ArrayList<Cliente> listarActivos();
}


