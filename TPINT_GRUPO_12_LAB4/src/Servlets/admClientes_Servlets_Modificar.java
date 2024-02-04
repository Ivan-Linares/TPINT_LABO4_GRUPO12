package Servlets;

import java.io.IOException;
import java.util.ArrayList;

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
import Negocio.ClienteNegocio;
import Negocio.LocalidadNegocio;
import Negocio.PaisNegocio;
import Negocio.ProvinciaNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.PaisNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class admClientes_Servlets_Modificar
 */
@WebServlet("/admClientes_Servlets_Modificar")
public class admClientes_Servlets_Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admClientes_Servlets_Modificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Seleccionado")!=null) {
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
	}

}
