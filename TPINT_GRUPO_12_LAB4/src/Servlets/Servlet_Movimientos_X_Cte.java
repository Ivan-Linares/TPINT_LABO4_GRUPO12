package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Movimiento;
import Negocio.MovimientoNegocio;
import NegocioImpl.Movimiento_NegocioImpl;

/**
 * Servlet implementation class Servlet_Movimientos_X_Cte
 */
@WebServlet("/Servlet_Movimientos_X_Cte")
public class Servlet_Movimientos_X_Cte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cuenta;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Movimientos_X_Cte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("cuenta")!= null) {
			ArrayList<Movimiento> listaMov = new ArrayList<Movimiento>();
			MovimientoNegocio mn = new Movimiento_NegocioImpl();
			cuenta = request.getParameter("cuenta");
			listaMov = mn.listarXcuenta(cuenta);
			
			request.setAttribute("listaMovimientos", listaMov);
			
			RequestDispatcher rd = request.getRequestDispatcher("Servlets_Cuentas_del_Cte?Param2="+cuenta);
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnfiltro")!= null) {
			int importe=Integer.parseInt(request.getParameter("txtvalor"));
			int tipoMov=Integer.parseInt(request.getParameter("filtro"));
			
			ArrayList<Movimiento> listaMov = new ArrayList<Movimiento>();
			MovimientoNegocio mn = new Movimiento_NegocioImpl();
			
			listaMov = mn.listarFiltrada(this.cuenta, importe, tipoMov);
			request.setAttribute("listaMovimientos", listaMov);
			
			RequestDispatcher rd = request.getRequestDispatcher("Servlets_Cuentas_del_Cte?Param2="+cuenta);
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
