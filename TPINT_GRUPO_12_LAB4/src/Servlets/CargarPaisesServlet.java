package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Localidad;
import Entidad.Pais;
import Entidad.Provincia;
import Negocio.ClienteNegocio;
import Negocio.LocalidadNegocio;
import Negocio.ProvinciaNegocio;
import NegocioImpl.ClienteNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class CargarPaisesServlet
 */
@WebServlet("/CargarPaisesServlet")
public class CargarPaisesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarPaisesServlet() {
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
            List<Provincia> provincias = getProvinciasPorPais(paisId);
            response.setContentType("text/html");
            response.getWriter().write(cargarProvinciasEnSelect(provincias));
        }
        
        if ("getLocalidades".equals(action)) {
            int provinciaId = Integer.parseInt(request.getParameter("provinciaId"));
            List<Localidad> localidades = getLocalidadesPorProvincia(provinciaId);
            response.setContentType("text/html");
            response.getWriter().write(cargarLocalidadesEnSelect(localidades));
        }
    }

    private List<Provincia> getProvinciasPorPais(int paisId) {
    	
    	ProvinciaNegocio pNeg = new ProvinciaNegocioImpl();
        List<Provincia> provincias = new ArrayList<>();
        provincias=pNeg.listar(paisId);
        return provincias;     
    }

    private String cargarProvinciasEnSelect(List<Provincia> provincias) {
        StringBuilder dropdown = new StringBuilder();
        for (Provincia provincia : provincias) {
            dropdown.append("<option value=\"").append(provincia.getCodProvincia()).append("\">")
                    .append(provincia.getNombreProvincia()).append("</option>");
        }
        return dropdown.toString();
    }
    
    private List<Localidad> getLocalidadesPorProvincia(int provinciaId) {
        List<Localidad> localidades = new ArrayList<>();
        LocalidadNegocio lNeg= new LocalidadNegocioImpl();
        localidades=lNeg.listar(provinciaId);

        return localidades;     
    }

    private String cargarLocalidadesEnSelect(List<Localidad> localidades) {
        StringBuilder dropdown = new StringBuilder();
        for (Localidad localidad : localidades) {
            dropdown.append("<option value=\"").append(localidad.getCodLocalidad()).append("\">")
                    .append(localidad.getNombreLocalidad()).append("</option>");
        }
        return dropdown.toString();
    }

}
