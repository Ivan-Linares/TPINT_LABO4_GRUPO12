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

/**
 * Servlet implementation class Servlets_Cuentas_del_Cte
 */
@WebServlet("/Servlets_Cuentas_del_Cte")
public class Servlets_Cuentas_del_Cte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlets_Cuentas_del_Cte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			String ss = request.getParameter("Param");
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn = new CuentaNegocioImpl();
			listaCuentas = cn.CuentasAsociadas(request.getParameter("Param"));
					

			request.setAttribute("listaCuentas", listaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("Cuentas_del_Cliente.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("Param2")!=null) {
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn = new CuentaNegocioImpl();
			String dni = cn.BuscarCteporCuenta(request.getParameter("Param2"));
			listaCuentas = cn.CuentasxDNI(dni);
					

			request.setAttribute("listaCuentas", listaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("Cuentas_del_Cliente.jsp");
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
