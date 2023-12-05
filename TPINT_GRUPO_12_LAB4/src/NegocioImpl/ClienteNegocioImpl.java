package NegocioImpl;

import java.util.ArrayList;

import Entidad.Cliente;
import Entidad.Usuario;
import Negocio.ClienteNegocio;
import dao.ClienteDao;
import dao.UsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.UsuarioDaoImpl;

public class ClienteNegocioImpl implements ClienteNegocio{
	
	private ClienteDao cDao;
	@Override
	public boolean insertar(Cliente cliente) {
		cDao = new ClienteDaoImpl();
		if(cDao.insertar(cliente)) return true;
		return false;
	}

	@Override
	public boolean modificar(Cliente cliente) {
		cDao = new ClienteDaoImpl();
		return cDao.modificar(cliente);
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

	@Override
	public boolean verificarExistencia(Cliente cliente) {
		
		cDao = new ClienteDaoImpl();
		Cliente cTest= new Cliente();
		cTest = cDao.existeDNI(cliente.getDni());
		
		if(cTest.getDni()!=null) {
			return true;
		}
		
		else return false;
	}

	@Override
	public ArrayList<Cliente> listarPendientes() {
		cDao = new ClienteDaoImpl();
		return cDao.listarPendientes();
	}

	@Override
	public boolean rechazar(String DNI) {
		cDao = new ClienteDaoImpl();
		if(cDao.rechazar(DNI)) return true;
		return false;
	}

	@Override
	public boolean aprobar(String DNI) {
		cDao = new ClienteDaoImpl();
		if(cDao.aprobar(DNI)) return true;
		return false;
	}

	@Override
	public boolean baja(String DNI) {
		cDao = new ClienteDaoImpl();
		if(cDao.baja(DNI)) return true;
		return false;
	}

	@Override
	public ArrayList<Cliente> listarActivos() {
		cDao = new ClienteDaoImpl();
		return cDao.listarActivos();
	}

	@Override
	public Cliente Cte_Seleccionado(String DNI) {
		cDao = new ClienteDaoImpl();
		return cDao.Cte_Seleccinado(DNI);
	}

}
