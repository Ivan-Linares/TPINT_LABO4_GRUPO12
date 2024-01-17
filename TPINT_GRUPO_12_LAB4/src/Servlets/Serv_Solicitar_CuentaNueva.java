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
import Negocio.CuentaNegocio;
import NegocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class Serv_Solicitar_CuentaNueva
 */
@WebServlet("/Serv_Solicitar_CuentaNueva")
public class Serv_Solicitar_CuentaNueva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String msj;
	private int Tipocuenta;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv_Solicitar_CuentaNueva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
			String dni = request.getParameter("Param").toString();
			CuentaNegocio cn = new CuentaNegocioImpl();
			
			
			if(!cn.CuentasPendientes(dni)){
				if(request.getParameter("BtnCA")!=null) {
					Tipocuenta=1;
				}else {
					Tipocuenta=2;
				}
				if (cn.insert(dni, Tipocuenta)) {
					msj="Solicitud enviada con exito.";
				}
			}
			else {
				msj="Ya posee una solicitud de cuenta en Proceso, debera aguardar su aprobacion.";
			}
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
			rd.forward(request, response);
		}
	}

}
