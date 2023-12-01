package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Localidad;
import Negocio.LocalidadNegocio;
import NegocioImpl.LocalidadNegocioImpl;

/**
 * Servlet implementation class admCtes_Modificar_Servlets
 */
@WebServlet("/admCtes_Modificar_Servlets")
public class admCtes_Modificar_Servlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admCtes_Modificar_Servlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param1")!=null) {
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			LocalidadNegocio pn = new LocalidadNegocioImpl();
			listaLocalidades = pn.listar();
			

			request.setAttribute("listaLocalidades", listaLocalidades);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes_Modificar.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
