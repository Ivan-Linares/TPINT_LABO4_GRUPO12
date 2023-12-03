package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Localidad;
import Negocio.ClienteNegocio;
import Negocio.LocalidadNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;

/**
 * Servlet implementation class admCtes_Modificar_Servlets
 */
@WebServlet("/admCtes_Modificar_Servlets")
public class admCtes_Modificar_Servlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admCtes_Modificar_Servlets() {
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
		if(request.getParameter("Param1")!=null) {
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			LocalidadNegocio pn = new LocalidadNegocioImpl();
			listaLocalidades = pn.listar();
			
			
			request.setAttribute("listaLocalidades", listaLocalidades);
			
			RequestDispatcher rd = request.getRequestDispatcher("Administrar_Clientes_Modificar.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnconfirmar")!=null) {
			try {
				Cliente obj = new Cliente();
				Localidad loc = new Localidad();
				LocalidadNegocio lcn= new LocalidadNegocioImpl();
				String estado = "A";
				
				obj.setApellido(request.getParameter("Apellido"));
				obj.setCuil(request.getParameter("cuil"));
				obj.setDireccion(request.getParameter("Direccion"));
				obj.setDni(request.getParameter("DNI"));
				obj.setEmail(request.getParameter("Email"));
				obj.setEstado(estado);
				obj.setNombre(request.getParameter("nombre"));
				obj.setPass(request.getParameter("Contraseña"));
				obj.setUsuario(request.getParameter("Usuario"));
				
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fecha= request.getParameter("FechaNac");
				
				obj.setFechaNac((Date)dateFormat.parse(fecha));
				
				obj.setSexo(request.getParameter("Sexo"));
				int cod = Integer.parseInt(request.getParameter("localidad"));
				loc = lcn.Seleccionado(cod);
				
				obj.setLocalidad(loc);
				
				ClienteNegocio cln = new ClienteNegocioImpl();
				cln.modificar(obj);
				
				RequestDispatcher rd = request.getRequestDispatcher("admClientes_Servlet?Param=1");
				rd.forward(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
