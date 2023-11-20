package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Fecha;
import Entidad.Pais;
import Entidad.Provincia;
import Entidad.TipoUsuario;
import Entidad.Usuario;
import Negocio.ClienteNegocio;
import Negocio.UsuarioNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;
import dao.ClienteDao;
import dao.ProvinciaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.ProvinciaDaoImpl;

/**
 * Servlet implementation class ContactoClienteServlet
 */
@WebServlet("/ContactoClienteServlet")
public class ContactoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactoClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*boolean insertCte, insertUser, insertOk = false;*/
		
		if(request.getParameter("btnRegistrar")!= null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			UsuarioNegocio uNeg = new UsuarioNegocioImpl();
			Cliente cte = new Cliente();
			Usuario user = new Usuario();
			String resp = "test";
			
			try {
				
				cte.setApellido(request.getParameter("txtApellido"));
				cte.setNombre(request.getParameter("txtNombre"));
				cte.setDni(request.getParameter("txtDNI"));
				cte.setCuil(request.getParameter("txtCUIL"));
				cte.setSexo(request.getParameter("selSexo"));
				cte.setPais(new Pais());
				cte.getPais().setCode(Integer.parseInt(request.getParameter("selPais")));
				cte.setProv(new Provincia());
				cte.getProv().setCodPais(Integer.parseInt(request.getParameter("selPais")));
				cte.getProv().setCodProvincia(Integer.parseInt(request.getParameter("selProvincia")));
				cte.setLocalidad(request.getParameter("txtLocalidad"));
				cte.setDireccion(request.getParameter("txtDireccion"));			
				cte.setFechaNac(LocalDate.parse(request.getParameter("dateFechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				cte.setTelefono(request.getParameter("txtTelefono"));
				cte.setEmail(request.getParameter("txtEmail"));
				cte.setUsuario(request.getParameter("txtUser"));
				cte.setPass(request.getParameter("txtPass"));
				cte.setEstado("P");
				
				user.setPersona(cte);	
				user.setTipoUsuario(new TipoUsuario());
				user.getTipoUsuario().setTipo(2);
				//Leer desde DB?
				user.getTipoUsuario().setDescripcion("Cliente");
				//
				user.setUser(cte.getUsuario());
				user.setPass(cte.getPass());
				user.setEstado(true);
				
				if(!uNeg.verificarExistencia(user.getUser())) {
					if(uNeg.insertar2(user) && cNeg.insertar(cte)) {
						resp = "Solicitud procesada!";
					}
					
					else {
						resp = "Error al insertar cliente";
					}
				}
				
				else {
					resp = "El usuario ya existe";
				}
				
				
			} catch (Exception e) {
				resp = "Ocurrio un error al crear el usuario";
				throw e;
			}
			
			request.setAttribute("usuarioCreado", resp);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactoCliente.jsp");
			rd.forward(request, response);	
		}
	}
}
