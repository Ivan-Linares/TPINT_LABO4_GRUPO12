<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Entidad.Prestamo"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

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
          <a class="nav-link" href="Transferencias?Param=<%= nusuario %>">Transferir</a>
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

	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-2"></div>
			<div class="col-md-auto">

				<%
					ArrayList<Prestamo> listaJSP = new ArrayList<>();
					if (request.getAttribute("listaPrestamos") != null)
						listaJSP = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");

					if (listaJSP.isEmpty()) {
				%>
				<h3>No tienes registrado ningun prestamo!</h3>
				<%
					} 
					else {
				%>
				
				<table class="table accordion-collapse" id="tablaPrestamos">
					<thead>
						<th>Codigo Prestamo</th>
						<th>Cuenta Destino</th>
						<th>Fecha Solicitud</th>
						<th>Monto Solicitado</th>
						<th>Monto a Pagar</th>
						<th>Cuotas Restantes</th>
						<th>Estado</th>
						<th>Más información</th>
					</thead>
					<%
						if (listaJSP != null)
								for (Prestamo prestamo : listaJSP) {
					%>
					<tr>
						<form action="Ver_Pretamos_Cte" method="post">
						<th><%=prestamo.getIDPrestamo()%></th>
						<input type="hidden" name="IDPrestamo"
							value="<%=prestamo.getIDPrestamo()%>">
						</td>
						<th><%=prestamo.getNroCuenta()%></th>
						<th><%=prestamo.getFecha()%></th>
						<th>$<%=prestamo.getImporteSolicitado()%></th>
						<th>$<%=prestamo.getImporteTotal()%></th>
						<th><%=prestamo.getCuotasRestantes()%></th>
						<th><%=prestamo.getEstado()%></th>
						<th><input type="submit" class="btn btn-primary"
							name="btnInfo" Value="Más información"></th>
						</form>
					</tr>
					<%
						}
					%>
				</table>
				<%
					}
				%>
			</div>
			<div class="col"></div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
		    $('#tablaPrestamos').DataTable();
		});
	</script>

</body>
</html>