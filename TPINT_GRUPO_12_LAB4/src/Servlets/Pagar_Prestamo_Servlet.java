package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Prestamo;
import Excepciones.FondosInsuficientesEx;
import daoImpl.PrestamoDaoImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.MovimientoDaoImpl;

/**
 * Servlet implementation class Pagar_Prestamo_Servlet
 */
@WebServlet("/Pagar_Prestamo_Servlet")
public class Pagar_Prestamo_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private String valorDesdeGet;
	
    public Pagar_Prestamo_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			
			String DNI = request.getParameter("Param").toString();
			valorDesdeGet = DNI;
			
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoDaoImpl pn = new PrestamoDaoImpl();
			
			listaPrestamos = pn.getPrestamoActivosDNICliente(DNI);
			
			request.setAttribute("listaPrestamos", listaPrestamos);
			
			RequestDispatcher rd = request.getRequestDispatcher("Detalle_Prestamos_Cte.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		PrestamoDaoImpl pn = new PrestamoDaoImpl();
		
		listaPrestamos = pn.getPrestamoActivosDNICliente(valorDesdeGet);
		
		request.setAttribute("listaPrestamos", listaPrestamos);

		if(request.getParameter("btnSeleccionar") != null) {
			try {
				int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo")); 
				
				Prestamo prestamo = new Prestamo();
				
				prestamo = pn.getPrestamoPorID(IDPrestamo);
				
				request.setAttribute("Prestamo", prestamo);
				request.setAttribute("PrestamoSelec", 1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//----------------------------------------------------------
		if(request.getParameter("btnPagar") != null) {
			try {
				
				int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamoPagar")); 
				
				MovimientoDaoImpl mv = new MovimientoDaoImpl();
				CuentaDaoImpl cn = new CuentaDaoImpl();	
				Prestamo prestamo = pn.getPrestamoPorID(IDPrestamo);

				try {
					cn.debito(prestamo.getNroCuenta(), prestamo.getImporteMensual());
					pn.PagarCuota(prestamo.getIDPrestamo(), prestamo.getCuotasRestantes());
					mv.insertar(prestamo.getNroCuenta(), (float)prestamo.getImporteMensual(), 3);
					
					String msj = "Cuota Pagada con exito!";
					request.setAttribute("msj", msj);
					
				} catch (FondosInsuficientesEx e) {
					
					e.printStackTrace();
					
					String msj = "Fondos insuficientes para realizar esta operacion!";
					request.setAttribute("msj", msj);
				}
				request.setAttribute("PrestamoSelec", 2);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Detalle_Prestamos_Cte.jsp");
		rd.forward(request, response);
	}

}
