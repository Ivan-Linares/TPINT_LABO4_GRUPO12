package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cuenta;
import Negocio.CuentaNegocio;
import NegocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class Servlets_AdministraCuentas
 */
@WebServlet("/Servlets_AdministraCuentas")
public class Servlets_AdministraCuentas extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlets_AdministraCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			ArrayList<Cuenta> listado = new ArrayList<Cuenta>();
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			listado=cni.listar();
			
			request.setAttribute("listadocompleto", listado);
			
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnModificar")!=null) {
			String seleccionada= request.getParameter("CuentaSeleccionada");
			ArrayList<Cuenta> listado = new ArrayList<Cuenta>();
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			listado=cni.listarXcuenta(seleccionada);
			
			request.setAttribute("seleccionada", listado);
			
			RequestDispatcher rd= request.getRequestDispatcher("Servlet_Modificar_Cuenta");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnEliminar")!=null) {
			Cuenta obj= new Cuenta();
			String estado="I";
			obj.setNumero(request.getParameter("CuentaSeleccionada"));
			obj.setEstado(estado);
			
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			cni.Eliminar(obj);
			
			RequestDispatcher rd= request.getRequestDispatcher("Servlets_AdministraCuentas?Param=1");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnDetalle")!=null) {
			String seleccionada= request.getParameter("CuentaSeleccionada");
			ArrayList<Cuenta> listado = new ArrayList<Cuenta>();
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			listado=cni.listarXcuenta(seleccionada);
			
			request.setAttribute("seleccionada", listado);
			
			RequestDispatcher rd= request.getRequestDispatcher("Servlet_Detalle_Cuenta");
			rd.forward(request, response);
		}
		if(request.getParameter("Btnbuscar")!=null && !(request.getParameter("Param")!=null)) {
			RequestDispatcher rd= request.getRequestDispatcher("Servlets_AdministraCuentas?Param=1");
			rd.forward(request, response);
		}
		if(request.getParameter("btnPendientes")!=null) {
			ArrayList<Cuenta> listado = new ArrayList<Cuenta>();
			CuentaNegocio cni= new CuentaNegocioImpl();
			listado=cni.ListarCuentasPendientes();
			
			
			request.setAttribute("listadocompleto", listado);
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
