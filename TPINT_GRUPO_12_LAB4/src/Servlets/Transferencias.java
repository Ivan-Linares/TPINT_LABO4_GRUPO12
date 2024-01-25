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
	private String msj;
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
				if(origen==destino) {
					msj ="La cuenta de origen debe ser diferente a la cuenta destino.";
				}
				else {
					if (cn.Transferencia(origen, destino, monto)) {
						//Carte o confirmacion de transferencia
						msj ="Tranferencia generada con exito.";
					}
					else {
							//No se pudo realizar la transferencia
							msj ="No es posible realizar la transferencia.";
					}						
				}
			} catch (FondosInsuficientesEx e) {
				msj ="Fondos insuficientes.";
				e.printStackTrace();
			}
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btntransferir")!=null && request.getParameter("CBU")!=null) {
			int origen = Integer.parseInt(request.getParameter("cuentasorigen"));
			float monto = Integer.parseInt(request.getParameter("Monto"));
			String cbu=request.getParameter("CBU");
			CuentaNegocio cn = new CuentaNegocioImpl();
			int destino = cn.Cuenta_x_CBU(cbu);
			if(destino!=0) {
				try {
					String DNIorigen = cn.BuscarCteporCuenta(request.getParameter("cuentasorigen"));
					String DNIDestino = cn.BuscarCteporCuenta(Integer.toString(destino));
					if(DNIorigen.equals(DNIDestino)) {
						msj ="No es posible realizar la transferencia. El CBU corresponde a una cuenta asociada";
					}
					else {
						if (cn.Transferencia(origen, destino, monto)) {
							//Carte o confirmacion de transferencia
							msj ="Tranferencia generada con exito.";
						}
						else {
							//No se pudo realizar la transferencia
							msj ="No es posible realizar la transferencia.";
						}
					}
				} catch (FondosInsuficientesEx e) {
					// TODO Auto-generated catch block
					msj ="Fondos insuficientes.";
					e.printStackTrace();
				}				
			}
			else {
				//MSJ cbu inexistente
				msj ="Cbu inexistente, verifique los datos ingresados.";
			}
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
	}
}
