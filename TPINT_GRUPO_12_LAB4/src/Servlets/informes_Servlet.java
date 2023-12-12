package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class informes_Servlet
 */
@WebServlet("/informes_Servlet")
public class informes_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public informes_Servlet() {
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
		
		if(request.getParameter("btnAcumuladoTipoMov") != null) {
			
			String tipoInforme = "acumuladoTipoMov";
			request.setAttribute("tipoInforme", tipoInforme);
			RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnGenerarReporte") != null) {
			String tipoInforme = "acumuladoTipoMov";
			
			
			
			
			request.setAttribute("tipoInforme", tipoInforme);
			RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
			rd.forward(request, response);
		}
	}

}
