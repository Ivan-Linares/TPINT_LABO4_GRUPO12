package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Prestamo;
import Negocio.ClienteNegocio;
import Negocio.CuentaNegocio;
import Negocio.MovimientoNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.Movimiento_NegocioImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.PrestamoDaoImpl;

/**
 * Servlet implementation class Prestamo_Aprobar_Servlet
 */
@WebServlet("/Prestamo_Aprobar_Servlet")
public class Prestamo_Aprobar_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prestamo_Aprobar_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param")!=null) {
						
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoDaoImpl pn = new PrestamoDaoImpl();
			
			listaPrestamos = pn.listarPendientes();
					
			request.setAttribute("listaPrestamos", listaPrestamos);
			
			RequestDispatcher rd = request.getRequestDispatcher("Prestamo_Aprobar.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrestamoDaoImpl pn = new PrestamoDaoImpl();
		CuentaDaoImpl cn = new CuentaDaoImpl();
		MovimientoNegocio mvn = new Movimiento_NegocioImpl();
		String msj = "";
		
		if (request.getParameter("btnEnviar") != null) {

			try {

				int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
				int estado = Integer.parseInt(request.getParameter("selectAction"));

				Prestamo pr = new Prestamo();
				pr = pn.getPrestamoPorID(IDPrestamo);
				
				if (estado == 2) {
					if (pn.Rechazar(IDPrestamo)) {
						msj = "Cliente rechazado!";
					}
				}

				if (estado == 1) {
					if (pn.Aprobar(IDPrestamo)) {

						if (cn.credito(pr.getNroCuenta(), (float)pr.getImporteSolicitado())) {
							mvn.insertar(pr.getNroCuenta(), (float)pr.getImporteSolicitado(), 2);
							msj = "Cliente aprobado con exito!";
						} else {
							msj = "Ocurrio un error al crear la cuenta";
						}
					} else {
						msj = "Ocurrio un error al aprobar el cliente";
					}
				}
			} catch (Exception e) {
				msj = "Ocurrio un error";
				throw e;

			}

			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			listaPrestamos = pn.listarPendientes();
			
			request.setAttribute("listaPrestamos", listaPrestamos);
			request.setAttribute("msj", msj);
			
			RequestDispatcher rd = request.getRequestDispatcher("Prestamo_Aprobar.jsp");
			rd.forward(request, response);
		}
	}

}
