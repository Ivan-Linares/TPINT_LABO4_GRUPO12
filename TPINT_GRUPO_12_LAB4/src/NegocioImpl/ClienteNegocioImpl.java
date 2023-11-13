package NegocioImpl;

import java.util.ArrayList;

import Entidad.Cliente;
import Entidad.Usuario;
import Negocio.ClienteNegocio;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;

public class ClienteNegocioImpl implements ClienteNegocio{
	
	private ClienteDao cDao;
	@Override
	public boolean insertar(Cliente cliente) {
		return false;
	}

	@Override
	public boolean modificar(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cliente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente clientePorUsuario(Usuario usuario) {
		
		cDao=new ClienteDaoImpl();
		
		if(usuario.getUser().trim().length()>0 && usuario.getUser()!=null) {
			
			Cliente cli=new Cliente();
			cli=cDao.getClientePorUsuario(usuario);
			return cli;
			
		}
		else return null;
	}

}
