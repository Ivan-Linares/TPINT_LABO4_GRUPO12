package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import Entidad.Cliente;
import Entidad.Cuenta;
import Negocio.ClienteNegocio;
import Negocio.CuentaNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;


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
		
		if(request.getParameter("Param")!=null) {
			
			String DNI = request.getParameter("Param").toString();
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn = new CuentaNegocioImpl();
			listaCuentas = cn.CuentasxDNI(request.getParameter("Param"));
					
			request.setAttribute("DNI", DNI);
			request.setAttribute("listaCuentas", listaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("Solicitud_Prestamo_Cte.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnSimular") != null) {
			
			if(request.getParameter("InputMontoPrueba") != null) {	
				
				int montoInicial = Integer.parseInt(request.getParameter("InputMontoPrueba")); 
				int cuotas = Integer.parseInt(request.getParameter("Cuotas"));
				double montoFinal = 0;
				
				if(cuotas == 6) {
					montoFinal = montoInicial * 1.15;
				}
				else if(cuotas == 12) {
					montoFinal = montoInicial * 1.35;
				}
				else if(cuotas == 24) {
					montoFinal = montoInicial * 1.65;
				}
				
				request.setAttribute("MontoSimulacion", montoFinal);
			}
			
			String DNI = request.getParameter("dniCliente").toString();
			
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn = new CuentaNegocioImpl();
			listaCuentas = cn.CuentasxDNI(DNI);
					

			request.setAttribute("listaCuentas", listaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Solicitud_Prestamo_Cte.jsp");
			rd.forward(request, response);
		}
	}

}
