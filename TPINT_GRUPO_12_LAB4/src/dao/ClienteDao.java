package dao;

import java.util.ArrayList;

import Entidad.Cliente;
import Entidad.Usuario;

public interface ClienteDao {
	
	public boolean insertar(Cliente cliente);
	public boolean eliminar(Cliente cliente);
	public boolean modificar(Cliente cliente);
	public Cliente existeDNI(String DNI);
	public Cliente Cte_Seleccinado(String DNI);
	public Cliente getClientePorUsuario(Usuario usuario);
	public ArrayList<Cliente> listar();
	public ArrayList<Cliente> listarPendientes();
	public boolean rechazar(String DNI);
	public boolean aprobar(String DNI);
	public boolean baja(String DNI);
	public ArrayList<Cliente> listarActivos();

	public ArrayList<Cliente> listarFiltrada(String dato, String campo);
}
