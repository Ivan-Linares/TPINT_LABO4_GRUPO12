package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cuenta;
import Negocio.CuentaNegocio;
import NegocioImpl.CuentaNegocioImpl;
import Entidad.Prestamo;
import daoImpl.PrestamoDaoImpl;

/**
 * Servlet implementation class Ver_Pretamos_Cte
 */
@WebServlet("/Ver_Pretamos_Cte")
public class Ver_Pretamos_Cte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private String valorDesdeGet;
	
    public Ver_Pretamos_Cte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			
			String DNI = request.getParameter("Param").toString();
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoDaoImpl pn = new PrestamoDaoImpl();
			
			listaPrestamos = pn.getPrestamoDNICliente(DNI);
			
			valorDesdeGet = DNI;
			
			request.setAttribute("DNI", DNI);
			request.setAttribute("listaCuentas", listaPrestamos);
			
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos_Principal.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
