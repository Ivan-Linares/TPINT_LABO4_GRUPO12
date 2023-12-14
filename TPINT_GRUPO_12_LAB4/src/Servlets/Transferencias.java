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
import Excepciones.FondosInsuficientesEx;
import Negocio.CuentaNegocio;
import NegocioImpl.CuentaNegocioImpl;

@WebServlet("/Transferencias")
public class Transferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Transferencias() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btntranferencia1mismo")!=null) {
			String nc = request.getParameter("Param");
			
			ArrayList<Cuenta> listadocuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn= new CuentaNegocioImpl();
			listadocuentas = cn.CuentasAsociadas(nc);
			
			request.setAttribute("miscuentas",listadocuentas );
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btntranferencia3ro")!=null) {
			String nc = request.getParameter("Param");
			ArrayList<Cuenta> listadocuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn= new CuentaNegocioImpl();
			listadocuentas = cn.CuentasAsociadas(nc);
			
			request.setAttribute("3ros",listadocuentas );
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btntransferir")!=null && request.getParameter("cuentasdestino")!=null) {
			int origen = Integer.parseInt(request.getParameter("cuentasorigen"));
			int destino = Integer.parseInt(request.getParameter("cuentasdestino"));
			float monto = Integer.parseInt(request.getParameter("Monto"));
			
			CuentaNegocio cn = new CuentaNegocioImpl();
			try {
				if (cn.Transferencia(origen, destino, monto)) {
					//Carte o confirmacion de transferencia
				}
				else {
					//No se pudo realizar la transferencia
				}
			} catch (FondosInsuficientesEx e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btntransferir")!=null && request.getParameter("CBU")!=null) {
			int origen = Integer.parseInt(request.getParameter("cuentasorigen"));
			float monto = Integer.parseInt(request.getParameter("Monto"));
			
			CuentaNegocio cn = new CuentaNegocioImpl();
			int cbu=Integer.parseInt(request.getParameter("CBU"));
			int destino = cn.Cuenta_x_CBU(cbu);
			if(destino!=0) {
				try {
					if (cn.Transferencia(origen, destino, monto)) {
						//Carte o confirmacion de transferencia
					}
					else {
						//No se pudo realizar la transferencia
					}
				} catch (FondosInsuficientesEx e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			else {
				//MSJ cbu inexistente
			}
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
	}
}
