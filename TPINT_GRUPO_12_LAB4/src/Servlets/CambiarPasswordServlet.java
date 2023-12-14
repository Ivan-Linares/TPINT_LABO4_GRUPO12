package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Localidad;
import Entidad.Pais;
import Entidad.Provincia;
import Entidad.TipoUsuario;
import Entidad.Usuario;
import Negocio.ClienteNegocio;
import Negocio.UsuarioNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class CambiarPasswordServlet
 */
@WebServlet("/CambiarPasswordServlet")
public class CambiarPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	       
    	if ("checkUser".equals(action)) {
    		UsuarioNegocio u=new UsuarioNegocioImpl();
    		if(!u.verificarExistencia(request.getParameter("user"))) {
    			response.getWriter().write("El usuario ingresado no se encuentra registrado");
    		}
    	}
    	
    	if ("checkPass".equals(action)) {
    		String pass=request.getParameter("pass");
    		String pass2=request.getParameter("pass2");
    		if(!pass.equals(pass2)) {
    			response.getWriter().write("Las passwords no coinciden");
    		}
    	}
    	
    	if ("checkDni".equals(action)) {
    		ClienteNegocio c=new ClienteNegocioImpl();
    		if(!c.verificarExistencia(request.getParameter("dni"))) {
    			response.getWriter().write("El DNI no existe en nuestra base de datos, ¿Desea registrarse?");
    		}
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("CambiarPassword")!= null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			UsuarioNegocio uNeg = new UsuarioNegocioImpl();
			Cliente cte = new Cliente();
			Usuario user = new Usuario();
			String resp = "test";
			String dni=request.getParameter("dni");
			String cuil=request.getParameter("cuil");
			String email=request.getParameter("email");
			
			try {
				user=uNeg.obtenerUsuario(request.getParameter("user"));
				cte=cNeg.clientePorUsuario(user);
				
				if(email.equals(cte.getEmail()) && cuil.equals(cte.getCuil()) && dni.equals(cte.getDni())) {
					
					user.setPass(request.getParameter("pass"));
					
					if(uNeg.actualizarUsuario(user)) {
						resp="La contraseña fue actualizada";
					}
					
					else {
						resp = "Error al cargar los cambios";
					}
				}
				
				else {
					resp = "Los datos ingresados no coinciden con los del cliente del usuario ingresado, por favor validar";
				}
				
				
			} catch (Exception e) {
				resp = "Error al cargar los cambios";
				e.printStackTrace();
			}
			
			request.setAttribute("CambioRealizado", resp);
			RequestDispatcher rd = request.getRequestDispatcher("/CambiarPassword.jsp");
			rd.forward(request, response);	
		}
	}
	
}
