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

import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Fecha;
import Entidad.TipoCuenta;
import Negocio.ClienteNegocio;
import Negocio.CuentaNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class cuentasAsignarServlet
 */
@WebServlet("/cuentasAsignarServlet")
public class cuentasAsignarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cuentasAsignarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			ArrayList<Cliente> lista = cNeg.listarPendientes();
			
			request.setAttribute("listaPendientes", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas_Asignar.jsp");
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("BtnVerDetalle") != null) {
			
			String DNI = request.getParameter("dniCliente");
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			Cliente cli = cNeg.Cte_Seleccionado(DNI);
			
			request.setAttribute("ClientePend", cli);
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas_VerDetalle.jsp");
			rd.forward(request, response);
			
		}
		
if(request.getParameter("btnEnviar") != null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			CuentaNegocio ctaNeg = new CuentaNegocioImpl();
			Cuenta cta = new Cuenta();
			String msj = "";
			
			try {
				
				String DNI = request.getParameter("dniCliente");
				int estado = Integer.parseInt(request.getParameter("selectAction"));
				
				if(estado == 2) {
					if(cNeg.rechazar(DNI)) {
						msj = "Cliente rechazado!";
					}
				}
				
				if(estado == 1) {
					if(cNeg.aprobar(DNI)) {
						
						cta.setDni(DNI);
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  						
						LocalDate FechaCreacion = LocalDate.now();
						dtf.format(FechaCreacion);
						String fechaString = FechaCreacion.toString();	
						java.sql.Date Fecha = java.sql.Date.valueOf(fechaString);
						cta.setFechaCreacion(Fecha); 
						
						cta.setTipoCuenta(new TipoCuenta());
						cta.getTipoCuenta().setCode(1);
						
						cta.getTipoCuenta().setName("Caja de ahorro");
						cta.setSaldo(10000);
						cta.setEstado("A");
						
						if(ctaNeg.insertar(cta)) {
							msj = "Cliente aprobado con exito!";
						}
						else {
							msj = "Ocurrio un error al crear la cuenta";
						}
					}
					else {
						msj = "Ocurrio un error al aprobar el cliente";
					}
				}	
			} 
			catch (Exception e) {
				msj = "Ocurrio un error";
				throw e;
				
			}
			
			ArrayList<Cliente> lista = cNeg.listarPendientes();
			
			request.setAttribute("listaPendientes", lista);
			request.setAttribute("msj", msj);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas_Asignar.jsp");
			rd.forward(request, response);	
			
		}
		
	}

}
