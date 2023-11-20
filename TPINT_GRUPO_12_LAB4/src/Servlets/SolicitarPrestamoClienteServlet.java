package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


/**
 * Servlet implementation class SolicitarPrestamoClienteServlet
 */
@WebServlet("/SolicitarPrestamoClienteServlet")
public class SolicitarPrestamoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitarPrestamoClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnsolicitarprestamo")!=null) {
			int p=1;
			request.setAttribute("nuevasolicitud", p);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Solicitud_Prestamo_Cte.jsp");
			rd.forward(request, response);	
			doGet(request, response);
		}
		if(request.getParameter("btnSimular")!=null) {
			int p=1;
			request.setAttribute("Prestamo", p);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Solicitud_Prestamo_Cte.jsp");
			rd.forward(request, response);	
			doGet(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("btnConfirmar")!=null) {
			
			String msj="Prestamo solicitado con éxito";
			
			request.setAttribute("mensaje", msj);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
			rd.forward(request, response);			
		}
		
		if(request.getParameter("btnCancelar")!=null) {
			
			String msj="Se canceló la solicitud";
			
			request.setAttribute("mensaje", msj);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
			rd.forward(request, response);			
		}
	}

}
