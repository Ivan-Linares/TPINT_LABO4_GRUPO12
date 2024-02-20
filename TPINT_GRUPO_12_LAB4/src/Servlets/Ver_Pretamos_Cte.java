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
import Entidad.Movimiento;
import Entidad.PagoPrestamo;
import Negocio.CuentaNegocio;
import Negocio.MovimientoNegocio;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.Movimiento_NegocioImpl;
import dao.PrestamoDao;
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
	
	private String DNIUsuario;
	
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
			DNIUsuario = DNI;
			
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoDaoImpl pn = new PrestamoDaoImpl();
			
			listaPrestamos = pn.getPrestamoDNICliente(DNI);
			
			for(Prestamo p : listaPrestamos) {
				System.out.println(p.getDNI());
			}
			
			request.setAttribute("listaPrestamos", listaPrestamos);
			
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos_Principal.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnInfo")!=null) 
		{
			String Prestamo= request.getParameter("IDPrestamo").toString();
			PrestamoDao p = new PrestamoDaoImpl();
			Prestamo pr=p.getPrestamoPorID(Prestamo);
			
			request.setAttribute("Prestamo", pr);
			
			ArrayList<PagoPrestamo>listaPagos = p.listarPagos(Prestamo);
				
			request.setAttribute("listaPagos", listaPagos);
			
			request.setAttribute("DNIUsuario", DNIUsuario);
		}
			
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos_informacion.jsp");
			rd.forward(request, response);
	}
	

}
