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
		if(request.getParameter("Param")!=null) {
			String dni=request.getParameter("Param");
			ArrayList<Cuenta> listadocuentas = new ArrayList<Cuenta>();
			CuentaNegocio cn= new CuentaNegocioImpl();
			listadocuentas = cn.CuentasAsociadas(dni);
			
			if (listadocuentas.isEmpty()) {
				String ok="Está null";
				request.setAttribute("mensajeOk", ok);
			}
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);			
		}
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
			try {
				float monto = Integer.parseInt(request.getParameter("Monto"));				
				CuentaNegocio cn = new CuentaNegocioImpl();
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
			catch (NumberFormatException e) {
				msj ="Debe ingresar un monto a transferir.";
				e.printStackTrace();
			}
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btntransferir")!=null && request.getParameter("CBU")!=null) {
			int origen = Integer.parseInt(request.getParameter("cuentasorigen"));
			String cbu=request.getParameter("CBU");
			try {
				float monto = Integer.parseInt(request.getParameter("Monto"));
				CuentaNegocio cn = new CuentaNegocioImpl();
				int destino = cn.Cuenta_x_CBU(cbu);
				if(destino!=0) {
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
					}
					else {
						//MSJ cbu inexistente
						msj ="Cbu inexistente, verifique los datos ingresados.";
					}
				} catch (FondosInsuficientesEx e) {
					// TODO Auto-generated catch block
					msj ="Fondos insuficientes.";
					e.printStackTrace();
				}
				catch (NumberFormatException e) {
					msj ="Debe ingresar un monto a transferir.";
					e.printStackTrace();
				}
			request.setAttribute("msj", msj);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
	}
}
