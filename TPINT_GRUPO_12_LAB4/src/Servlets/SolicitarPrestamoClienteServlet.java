package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import Entidad.Prestamo;
import daoImpl.PrestamoDaoImpl;;


/**
 * Servlet implementation class SolicitarPrestamoClienteServlet
 */
@WebServlet("/SolicitarPrestamoClienteServlet")
public class SolicitarPrestamoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String valorDesdeGet;
	
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
			listaCuentas = cn.CuentasxDNI(DNI);
			
			valorDesdeGet = DNI;
			
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
		try {
			
			String DNI = valorDesdeGet;
			
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn = new CuentaNegocioImpl();
			listaCuentas = cn.CuentasxDNI(DNI);
					
			request.setAttribute("DNI", DNI);
			request.setAttribute("listaCuentas", listaCuentas);
			
			
			if(request.getParameter("btnSimular") != null) {
				if(request.getParameter("InputMontoPrueba") != null) {	
					
					int montoInicial = Integer.parseInt(request.getParameter("InputMontoPrueba")); 
					int cuotas = Integer.parseInt(request.getParameter("Cuotas"));
					double montoFinal = 0;
					double montoCuota = 0;
					
					if(cuotas == 6) {
						montoFinal = montoInicial * 1.15;
					}
					else if(cuotas == 12) {
						montoFinal = montoInicial * 1.35;
					}
					else if(cuotas == 24) {
						montoFinal = montoInicial * 1.65;
					}
					
					montoCuota = montoFinal / cuotas;
					
					request.setAttribute("CuotaSimulacion", montoCuota);
					request.setAttribute("MontoSimulacion", montoFinal);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/Solicitud_Prestamo_Cte.jsp");
				rd.forward(request, response);
			}
			
			//-----------------------------
			if(request.getParameter("btnConfirmar") != null) {
				if(request.getParameter("MontoFinal") != null) {	
					
					int montoInicial = Integer.parseInt(request.getParameter("MontoFinal")); 
					int cuotas = Integer.parseInt(request.getParameter("CuotasFinal"));
					String CuentaSeleccionada = request.getParameter("CuentaDest").toString();
					double montoFinal = 0;
					double montoCuota = 0;
					
					Prestamo newPrestamo = new Prestamo();
					PrestamoDaoImpl newPDAO = new PrestamoDaoImpl();
					
					if(cuotas == 6) {
						montoFinal = montoInicial * 1.15;
					}
					else if(cuotas == 12) {
						montoFinal = montoInicial * 1.35;
					}
					else if(cuotas == 24) {
						montoFinal = montoInicial * 1.65;
					}
					
					montoCuota = montoFinal / cuotas;
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  						
					LocalDate FechaCreacion = LocalDate.now();
					dtf.format(FechaCreacion);
					String fechaString = FechaCreacion.toString();	
					java.sql.Date Fecha = java.sql.Date.valueOf(fechaString);
					
					newPrestamo.setDNI(DNI);
					newPrestamo.setNroCuenta(Integer.parseInt(CuentaSeleccionada));
					newPrestamo.setImporteSolicitado(montoInicial);
					newPrestamo.setImporteTotal(montoFinal);
					newPrestamo.setImporteMensual(montoCuota);
					newPrestamo.setCuotasRestantes(cuotas);
					newPrestamo.setFecha(Fecha);
					newPrestamo.setEstado("P");
					
					if(newPDAO.insertar(newPrestamo)) {
						
						String msj = "Prestamo solicitado con exito! Debe ser aprobado antes de depositarse en tu cuenta";
						
						request.setAttribute("msj", msj);
						request.setAttribute("PrestamoSolicitado", true);
					}
				}
				
				
			}
			
			
			if(request.getParameter("btnNuevoPrest") != null) {
				request.setAttribute("PrestamoSolicitado", false);
			}
			

			RequestDispatcher rd = request.getRequestDispatcher("/Solicitud_Prestamo_Cte.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
