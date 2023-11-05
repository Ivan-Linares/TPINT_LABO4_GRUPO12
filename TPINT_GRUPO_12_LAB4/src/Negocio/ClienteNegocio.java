package Negocio;

import java.util.ArrayList;
import Entidad.Cliente;


public interface ClienteNegocio {
	public boolean insertar (Cliente cliente);
	public boolean modificar (Cliente cliente);
	public ArrayList<Cliente> listar();
	
}


