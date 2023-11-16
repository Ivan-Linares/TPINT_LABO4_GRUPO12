package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cliente;
import Entidad.Pais;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;

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
		
		boolean insert = false;
		if(request.getParameter("btnRegistrar")!= null) {
			
			Cliente cte = new Cliente();
			Pais p = new Pais();
			cte.setApellido(request.getParameter("txtApellido"));
			cte.setNombre(request.getParameter("txtNombre"));
			cte.setDni(request.getParameter("txtDNI"));
			cte.setCuil(request.getParameter("txtCUIL"));
			cte.setSexo(request.getParameter("selSexo"));
			
			System.out.println(cte.getApellido());
			System.out.println(cte.getNombre());
			System.out.println(cte.getSexo());
			/*ClienteDaoImpl cteDao = new ClienteDaoImpl();
			insert = cteDao.insertar(cte);*/
		}
		
		request.setAttribute("insert", insert);
		RequestDispatcher rd = request.getRequestDispatcher("/ContactoCliente.jsp");
		rd.forward(request, response);
		
	}

}
