package Servlets;

import java.io.IOException;
import java.time.LocalDate;
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
						msj = "Cliente rechazado con exito!";
					}
				}
				
				if(estado == 1) {
					if(cNeg.aprobar(DNI)) {
						
						cta.setNumero("1");
						cta.setDni(DNI);
						LocalDate hoy = LocalDate.now();
						cta.setFechaCreacion(new Fecha());
						cta.getFechaCreacion().setDia(hoy.getDayOfMonth());
						cta.getFechaCreacion().setMes(hoy.getMonthValue());
						cta.getFechaCreacion().setYear(hoy.getYear());
						cta.setTipoCuenta(new TipoCuenta());
						cta.getTipoCuenta().setCode(1);
						cta.getTipoCuenta().setName("Caja de ahorro");
						cta.setCBU("12345...");
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
				
				
			} catch (Exception e) {
				msj = "Ocurrio un error";
				throw e;
			}
			
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas_Asignar.jsp");
			rd.forward(request, response);	
			
		}
		
	}

}
