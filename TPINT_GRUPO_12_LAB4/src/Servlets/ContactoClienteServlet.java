package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Fecha;
import Entidad.Pais;
import Entidad.Provincia;
import Entidad.TipoUsuario;
import Entidad.Usuario;
import Negocio.ClienteNegocio;
import Negocio.UsuarioNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;
import dao.ClienteDao;
import dao.ProvinciaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.ProvinciaDaoImpl;

/**
 * Servlet implementation class ContactoClienteServlet
 */
@WebServlet("/ContactoClienteServlet")
public class ContactoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactoClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean insertCte, insertUser, insertOk = false;
		
		String action = request.getParameter("action");

        if ("getProvincias".equals(action)) {
            int paisId = Integer.parseInt(request.getParameter("paisId"));
            ArrayList<Provincia> provincias = getProvinciasPorPais(paisId);
            response.setContentType("text/html");
            response.getWriter().write(cargarProvinciasEnSelect(provincias));
        }
		
		if(request.getParameter("btnRegistrar")!= null) {
			
			ClienteNegocio cNeg = new ClienteNegocioImpl();
			UsuarioNegocio uNeg = new UsuarioNegocioImpl();
			
			try {
				
				Cliente cte = new Cliente();
				Usuario user = new Usuario();
				
				cte.setApellido(request.getParameter("txtApellido"));
				cte.setNombre(request.getParameter("txtNombre"));
				cte.setDni(request.getParameter("txtDNI"));
				cte.setCuil(request.getParameter("txtCUIL"));
				cte.setSexo(request.getParameter("selSexo"));
				cte.setPais(new Pais());
				cte.getPais().setCode(Integer.parseInt(request.getParameter("selPais")));
				cte.setProv(new Provincia());
				cte.getProv().setCodPais(Integer.parseInt(request.getParameter("selPais")));
				cte.getProv().setCodProvincia(Integer.parseInt(request.getParameter("selProvincia")));
				cte.setLocalidad(request.getParameter("txtLocalidad"));
				cte.setDireccion(request.getParameter("txtDireccion"));			
				cte.setFechaNac(LocalDate.parse(request.getParameter("dateFechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				cte.setTelefono(request.getParameter("txtTelefono"));
				cte.setEmail(request.getParameter("txtEmail"));
				cte.setUsuario(request.getParameter("txtUser"));
				cte.setPass(request.getParameter("txtPass"));
				cte.setEstado("P");
				
				user.setPersona(cte);
				//Leer desde DB?
				user.setTipoUsuario(new TipoUsuario());
				user.getTipoUsuario().setTipo(2);
				user.getTipoUsuario().setDescripcion("");
				user.setUser(cte.getUsuario());
				user.setPass(cte.getPass());
				user.setEstado(true);
				
				insertUser = uNeg.insertar(user);
				insertCte = cNeg.insertar(cte);
				
				if(insertUser && insertCte) insertOk = true;
				
			} catch (Exception e) {
				request.setAttribute("usuarioCreado", "Ocurrio un error al crear el usuario");
				throw e;
			}
			
			request.setAttribute("insert", insertOk);
			RequestDispatcher rd = request.getRequestDispatcher("/ContactoCliente.jsp");
			rd.forward(request, response);	
		}
	}
	
	private ArrayList<Provincia> getProvinciasPorPais(int paisId) {
		
        ProvinciaDao pd = new ProvinciaDaoImpl();
		ArrayList<Provincia> listaProv = pd.listar();
		
        ArrayList<Provincia> listaProvFiltradas = new ArrayList<>();

        for (Provincia provincia : listaProv) {  	
            if (provincia.getCodPais() == paisId) { 
            	listaProvFiltradas.add(provincia);
            }
        }

        return listaProvFiltradas;     
    }

    private String cargarProvinciasEnSelect(ArrayList<Provincia> listaProv) {
        StringBuilder dropdown = new StringBuilder();
        for (Provincia provincia : listaProv) {
            dropdown.append("<option value=\"").append(provincia.getCodProvincia()).append("\">")
                    .append(provincia.getNombreProvincia()).append("</option>");
        }
        return dropdown.toString();
    }

}
