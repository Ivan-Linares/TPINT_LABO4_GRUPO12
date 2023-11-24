package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cuenta;
import Entidad.TipoCuenta;
import NegocioImpl.CuentaNegocioImpl;
import NegocioImpl.Tipo_de_CuentaNegocioImpl;

/**
 * Servlet implementation class Servlet_Modificar_Cuenta
 */
@WebServlet("/Servlet_Modificar_Cuenta")
public class Servlet_Modificar_Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Modificar_Cuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("seleccionada")!=null) {
			ArrayList<TipoCuenta> listaTC = new ArrayList<TipoCuenta>();
			Tipo_de_CuentaNegocioImpl tci= new Tipo_de_CuentaNegocioImpl();
			listaTC= tci.listar();
			
			request.setAttribute("ListaDeCuentas", listaTC);
			
			RequestDispatcher rd= request.getRequestDispatcher("Administrar_Cuentas_Modificar.jsp");
			rd.forward(request, response);
			}
		if(request.getParameter("btnConfirmar")!=null) {
			Cuenta obj = new Cuenta();
			TipoCuenta au = new TipoCuenta();
			
			au.setCode(Integer.parseInt(request.getParameter("Tipo Cuenta")));
			obj.setTipoCuenta(au);
			
			obj.setCBU(request.getParameter("CBU"));
			obj.setDni(request.getParameter("DNI"));
			obj.setEstado(request.getParameter("Estado"));
			obj.setNumero(request.getParameter("Numero"));
			
			CuentaNegocioImpl cni= new CuentaNegocioImpl();
			cni.modificar(obj);
			
			RequestDispatcher rd= request.getRequestDispatcher("Servlets_AdministraCuentas?Param=1");
			rd.forward(request, response);
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
