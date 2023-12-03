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
import Negocio.ClienteNegocio;
import NegocioImpl.ClienteNegocioImpl;

/**
 * Servlet implementation class admClientes_Servlet
 */
@WebServlet("/admClientes_Servlet")
public class admClientes_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admClientes_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null) {
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			ArrayList<Cliente> lista = cNeg.listarActivos();
			
			request.setAttribute("listaClientes", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!=null) {
			String DNI = request.getParameter("dniCliente");
			ClienteNegocio ctn = new ClienteNegocioImpl();
			
			Cliente obj = new Cliente();
			obj = ctn.Cte_Seleccionado(DNI);
			

			request.setAttribute("Seleccionado", obj);
			
			RequestDispatcher rd = request.getRequestDispatcher("admCtes_Modificar_Servlets?Param1=1");
			rd.forward(request, response);
		}
		if(request.getParameter("btnEliminar") != null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			String msj = "";
			String DNI = request.getParameter("dniCliente");
			//String accion = request.getParameter("accion");
		    //Agregar confirmacion con modal
			
			if(cNeg.baja(DNI)) {
				msj = "Cliente dado de baja con exito";
			} 
			else {
				msj = "Sucedio un error";
			}
			
			ArrayList<Cliente> lista = cNeg.listarActivos();
			request.setAttribute("listaClientes", lista);
			request.setAttribute("msj", msj);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes.jsp");
			rd.forward(request, response);
			
		}
	}
}
