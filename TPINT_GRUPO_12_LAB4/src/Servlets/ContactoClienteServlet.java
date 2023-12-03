package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import Entidad.Localidad;
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
				cte.setLocalidad(new Localidad());
				cte.getLocalidad().setProvincia(new Provincia());
				cte.getLocalidad().getProvincia().setPais(new Pais());
				cte.getLocalidad().getProvincia().getPais().setCode(Integer.parseInt(request.getParameter("pais")));
				cte.getLocalidad().getProvincia().setCodProvincia(Integer.parseInt(request.getParameter("provincia")));
				
				//Hacer metodos 
				cte.getLocalidad().setCodLocalidad(Integer.parseInt(request.getParameter("localidad")));
				
				cte.setDireccion(request.getParameter("txtDireccion"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fecha=request.getParameter("dateFechaNacimiento");
				
				
				cte.setFechaNac((Date)dateFormat.parse(fecha));
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
				
				if(!uNeg.verificarExistencia(user.getUser()) && !cNeg.verificarExistencia(cte) ) {
					if(uNeg.insertar2(user) && cNeg.insertar(cte)) {
						resp = "Solicitud procesada!";
					}
					
					else {
						resp = "Error al insertar cliente";
					}
				}
				
				else {
					resp = "Usuario o Cliente ya registrado";
				}
				
				
			} catch (Exception e) {
				resp = "Ocurrio un error al crear el usuario";
				e.printStackTrace();
			}
			
			request.setAttribute("usuarioCreado", resp);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactoCliente.jsp");
			rd.forward(request, response);	
		}
	}
}
