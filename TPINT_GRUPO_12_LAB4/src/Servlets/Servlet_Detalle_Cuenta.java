package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.TipoCuenta;
import NegocioImpl.Tipo_de_CuentaNegocioImpl;

/**
 * Servlet implementation class Servlet_Detalle_Cuenta
 */
@WebServlet("/Servlet_Detalle_Cuenta")
public class Servlet_Detalle_Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Detalle_Cuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getAttribute("seleccionada")!=null) {
			ArrayList<TipoCuenta> listaTC = new ArrayList<TipoCuenta>();
			Tipo_de_CuentaNegocioImpl tci= new Tipo_de_CuentaNegocioImpl();
			listaTC= tci.listar();
			
			request.setAttribute("", listaTC);
			
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas_Detalle.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnRegresar")!=null) {
			
			RequestDispatcher rd= request.getRequestDispatcher("Servlets_AdministraCuentas?Param=1");
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
