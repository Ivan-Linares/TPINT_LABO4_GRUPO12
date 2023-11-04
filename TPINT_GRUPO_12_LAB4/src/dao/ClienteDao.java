package dao;

import java.util.ArrayList;

import Entidad.Cliente;

public interface ClienteDao {
	
	public boolean insertar(Cliente cliente);
	public boolean eliminar(Cliente cliente);
	public boolean modificar(Cliente cliente);
	public Cliente getClientePorDNI(String DNI);
	public ArrayList<Cliente> listar();

}
