package NegocioImpl;

import Entidad.Cliente;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;
import dao.ClienteDao;
import dao.UsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.UsuarioDaoImpl;


public class UsuarioNegocioImpl implements UsuarioNegocio{

	private UsuarioDao uDao;
	@Override
	public boolean insertar(Usuario usuario) {
		
		if(usuario.getUser().trim().length()>0 && usuario.getPass().trim().length()>0)
				
		{
			Usuario utest=new Usuario();
			utest=uDao.getUsuarioPorUsuario(usuario.getUser());
			
			if(!(utest.getUser().trim().length()>0)) {
				return uDao.insertar(usuario);
			}
			else return false;
		}
		else return false;
	}

	@Override
	public boolean validar(String usuario, String pass) {
		// TODO Auto-generated method stub
			uDao=new UsuarioDaoImpl();
			Usuario utest=new Usuario();
			utest=uDao.getUsuarioPorUsuario(usuario);
			
			if(utest.getUser().trim().length()>0) {
				if(pass.equals(utest.getPass())) {
					return true;
				}
				else return false;
			}
			else return false;		
	}

	@Override
	public boolean verificarExistencia(String usuario) {
		
		uDao=new UsuarioDaoImpl();
		Usuario utest=new Usuario();
		utest=uDao.getUsuarioPorUsuario(usuario);
		
		if(utest.getUser()!=null) {
			return true;
		}
		
		else return false;
	}

	@Override
	public Usuario obtenerUsuario(String usuario) {
		uDao=new UsuarioDaoImpl();
		Usuario utest=new Usuario();
		utest=uDao.getUsuarioPorUsuario(usuario);
		
		if(utest.getUser()!=null) {
			return utest;
		}
		
		else return null;
	}

	@Override
	public Usuario asignarCliente(Usuario user) {
		
		ClienteDao cDao=new ClienteDaoImpl();
		Cliente c=new Cliente();
		c=cDao.getClientePorUsuario(user);
		user.setPersona(c);
		return user;
	}

	@Override
	public boolean insertar2(Usuario usuario) {
		return uDao.insertar(usuario);
	}

	@Override
	public boolean actualizarUsuario(Usuario user) {
		// TODO Auto-generated method stub
		uDao=new UsuarioDaoImpl();
		
		if(uDao.modificar(user)) {
			return true;
		}
		else return false;
	}
	
	

}
