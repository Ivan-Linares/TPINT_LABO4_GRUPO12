package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Provincia;
import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;

/**
 * Servlet implementation class cargarDropdownsServlet
 */
@WebServlet("/cargarDropdownsServlet")
public class cargarDropdownsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarDropdownsServlet() {
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
		
		String action = request.getParameter("action");

        if ("getProvincias".equals(action)) {
            int paisId = Integer.parseInt(request.getParameter("paisId"));
            ArrayList<Provincia> provincias = getProvinciasPorPais(paisId);
            response.setContentType("text/html");
            response.getWriter().write(cargarProvinciasEnSelect(provincias));
        }
	}
	
	
	private ArrayList<Provincia> getProvinciasPorPais(int paisId) {
		
        ProvinciaDao pd = new ProvinciaDaoImpl();
		ArrayList<Provincia> listaProv = pd.listar();
		
        ArrayList<Provincia> listaProvFiltradas = new ArrayList<>();

        for (Provincia provincia : listaProv) {  	
            if (provincia.getPais().getCode() == paisId) { 
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
