<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Prestamo"%>
<%@page import="Entidad.PagoPrestamo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle de prestamo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
	<link rel="stylesheet" href="Styles/Css.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
	
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
</head>
<body>
<%!
boolean admin=true;
Usuario user=new Usuario();%>
<%if(session!=null){
	user=(Usuario)session.getAttribute("Client");
		if(user.getTipoUsuario().getTipo()==2){
			admin=false;
		}
}%>

<!-- Navbar Admin -->
<%if (admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Admin Page</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Index.jsp">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Prestamos
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Prestamo_Aprobar_Servlet?Param=1">Aprobar Prestamos</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="cuentasAsignarServlet?Param=1">Asignar Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Administrar
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="admClientes_Servlet?Param=1"> Admin Clientes </a></li>
            <li><a class="dropdown-item" href="Servlets_AdministraCuentas?Param=1"> Admin Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Informes.jsp">Informes</a>
        </li>
        <li class="nav-item">
		    <form action="validarLoginServlet" method="get">
		        <input type="submit" value="Cerrar Sesión">
		    </form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%} %>
<!-- Navbar Cliente -->

<%
String nusuario = "";
if (!admin){ 
	nusuario = user.getUser();
	String nombre=user.getPersona().getNombre()+" "+user.getPersona().getApellido();
	String UsuarioDni = user.getPersona().getDni();
	%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><%=nombre%></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Index.jsp">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Prestamos  
          </a>
          <ul class="dropdown-menu">
          	<li><a class="dropdown-item" href="SolicitarPrestamoClienteServlet?Param=<%= UsuarioDni%>" >Pedir Prestamos</a></li>
            <li><a class="dropdown-item" href="Ver_Pretamos_Cte?Param=<%= UsuarioDni%>">Ver Prestamos</a></li>
            <li><a class="dropdown-item" href="Pagar_Prestamo_Servlet?Param=<%= UsuarioDni%>">Pagar Cuotas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Servlets_Cuentas_del_Cte?Param=<%= nusuario %>" >Ver Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Transferencias.jsp">Transferir</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Datos_Personales_cte.jsp">Datos Personales</a>
        </li>
        <li class="nav-item">
		    <form action="validarLoginServlet" method="get">
		        <input type="submit" value="Cerrar Sesión">
		    </form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%}%>
<br />

<%
	String DNI = "";
	if (request.getAttribute("DNIUsuario") != null)
		DNI = (String)request.getAttribute("DNIUsuario");

	Prestamo pres = new Prestamo();
	if (request.getAttribute("Prestamo") != null){
		pres = (Prestamo)request.getAttribute("Prestamo");

%>
	<div class="row">
	  <div class="col">
	    <label for="ID">ID:</label>
        <input type="text" id="ID" name="ID" value="<%=pres.getIDPrestamo()%>" class="form-control" disabled><br>
        
        <label for="fecha">Fecha de solicitud:</label>
        <input type="text" id="fecha" name="fecha" value="<%=pres.getFecha()%>"  class="form-control" disabled><br>
        
        <label for="importeSolicitado">Importe original solicitado:</label>
        <input type="text" id="importeSolicitado" name="importeSolicitado" value="<%=pres.getImporteSolicitado()%>"  class="form-control" disabled><br>
        
        <label for="importeFinal">Importe final a abonar:</label>
        <input type="text" id="importeFinal" name="importeFinal" value="<%=pres.getImporteTotal()%>"  class="form-control" disabled><br>
        
	  </div>
	  <div class="col">
	  	 <label for="cuota">Importe de cuota:</label>
        <input type="text" id="cuota" name="cuota" value="<%=pres.getImporteMensual()%>"  class="form-control" disabled><br>
        
        <label for="cuotasTotales">Cantidad de cuotas totales:</label>
        <input type="text" id="cuotasTotales" name=cuotasTotales value="<%=pres.getCantCuotas()%>" class="form-control" disabled><br>
	  
 		<label for="cuotasRestantes">Cantidad de cuotas restantes:</label>
        <input type="text" id="cuotasRestantes" name="cuotasRestantes" value="<%=pres.getCuotasRestantes()%>"  class="form-control" disabled><br>
        
	  </div>
	</div>
	
	<a class="btn btn-primary" href="Ver_Pretamos_Cte?Param=<%= DNI%>">Volver</a>
<%}%>

	<div>
		<%
		ArrayList<PagoPrestamo> listadoPagos = new ArrayList<PagoPrestamo>();
		if(request.getAttribute("listaPagos")!=null){
			listadoPagos = (ArrayList<PagoPrestamo>)request.getAttribute("listaPagos");%>
			
		<h2>Historial de Pagos</h2></br>
		<h4><input type="text" value="Cuenta seleccionada" disabled></h4>
		
		<table class="table accordion-button">
			<tr>
				<th>ID Prestamo</th>
				<th>Cuota número</th>
				<th>Importe</th>	
				<th>Fecha</th>		
			</tr>
			<%for(PagoPrestamo p : listadoPagos){%>
				<tr>
					<td><%= p.getPrestamo()%></td>
					<td><%= p.getNroCuota() %></td>
					<td><%= p.getImporteCuota() %></td>
					<td><%= p.getFecha().toString()%></td>
	
				</tr>
			<%}
		}%>
		</table>
	</div>

</body>
</html>