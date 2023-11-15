package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Transferencias")
public class Transferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Transferencias() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btntranferencia1mismo")!=null) {
			int valor=1;
			request.setAttribute("miscuentas",valor );
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btntranferencia3ro")!=null) {
			int valor=1;
			request.setAttribute("3ros",valor );
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
