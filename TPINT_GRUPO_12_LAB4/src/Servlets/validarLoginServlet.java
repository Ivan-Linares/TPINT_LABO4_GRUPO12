package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Usuario;
import Negocio.UsuarioNegocio;
import NegocioImpl.UsuarioNegocioImpl;
import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;

/**
 * Servlet implementation class validarLoginServlet
 */
@WebServlet("/validarLoginServlet")
public class validarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validarLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String User=request.getParameter("txtusuario");
		String Pass=request.getParameter("txtcontraseña");
		String resp="No está entrando al try";
		
		UsuarioNegocio uDao=new UsuarioNegocioImpl();
		
		try {
			Usuario utest=new Usuario();
			
			if (uDao.verificarExistencia(User)) {
				if (uDao.validar(User, Pass)) {
					resp="Conexión exitosa";
				}
				
				else {
					resp="Usuario o contraseña incorrectos";
				}
			}
			else {
				resp="Usuario no registrado o habilitado";
			}
		}
		
		catch(Exception e) {
			resp="falló conexión a DB";
		}
		
		request.setAttribute("Validar", resp);
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

}
