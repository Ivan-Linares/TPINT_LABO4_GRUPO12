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
		if(request.getParameter("CuentaSeleccionada")!=null) {
			String seleccionada= request.getParameter("CuentaSeleccionada");
			ArrayList<Cuenta> listado = new ArrayList<Cuenta>();
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			listado=cni.listarXcuenta(seleccionada);
			
			request.setAttribute("seleccionada", listado);
			
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas_Modificar.jsp");
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
