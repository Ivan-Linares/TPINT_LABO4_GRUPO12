package dao;

import java.util.ArrayList;

import Entidad.Cliente;
import Entidad.Telefono;

public interface TelefonoDao {
	
	public boolean insertar(Telefono tel);
	public boolean eliminar(Telefono tel);
	public boolean modificar(Telefono tel);
	public ArrayList<Telefono> filtrar(Cliente cliente);
	public ArrayList<Telefono> listar();

}
