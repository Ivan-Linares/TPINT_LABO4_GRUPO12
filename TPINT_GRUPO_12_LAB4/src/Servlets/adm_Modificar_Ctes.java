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
import Entidad.Pais;
import Entidad.Provincia;
import Negocio.LocalidadNegocio;
import Negocio.PaisNegocio;
import Negocio.ProvinciaNegocio;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.PaisNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class adm_Modificar_Ctes
 */
@WebServlet("/adm_Modificar_Ctes")
public class adm_Modificar_Ctes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adm_Modificar_Ctes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!= null) {
			ArrayList<Pais> listaPais = new ArrayList<Pais>();
			PaisNegocio ln = new PaisNegocioImpl();
			listaPais = ln.listar();
			
			request.setAttribute("listadoPais", listaPais);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes_Modificar.jsp");
			rd.forward(request, response);			
		}
		if(request.getParameter("btnPaisok")!=null) {
			int id =Integer.parseInt(request.getParameter("Pais"));
			ArrayList<Provincia> listaPr = new ArrayList<Provincia>();
			ProvinciaNegocio ln = new ProvinciaNegocioImpl();
			listaPr = ln.listar(id);
			
			request.setAttribute("listadoProvincias", listaPr);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes_Modificar.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnprovinciaok")!= null) {
			ArrayList<Localidad> lista = new ArrayList<Localidad>();
			LocalidadNegocio ln = new LocalidadNegocioImpl();
			int id =Integer.parseInt(request.getParameter("Provincia"));
			lista = ln.listar(id);
			
			request.setAttribute("listado", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes_Modificar.jsp");
			rd.forward(request, response);
		}
	}

}
