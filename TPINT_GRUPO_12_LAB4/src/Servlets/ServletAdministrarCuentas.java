package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.protocol.x.ReusableOutputStream;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

import Entidad.Cuenta;
import NegocioImpl.CuentaNegocioImpl;
import daoImpl.CuentaDaoImpl;

/**
 * Servlet implementation class ServletAdministrarCuentas
 */
@WebServlet("/ServletAdministrarCuentas")
public class ServletAdministrarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdministrarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			ArrayList<Cuenta> lista= new ArrayList<Cuenta>();
			CuentaNegocioImpl cn= new CuentaNegocioImpl();
			lista= cn.listar();
			
			request.setAttribute("listado", lista);
			
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas.jsp");
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
