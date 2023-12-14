<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="Entidad.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Prestamo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle del Prestamo</title>
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
            <li><a class="dropdown-item" href="Prestamo_Aprobar.jsp">Aprobar Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamo_Ver.jsp">Ver Prestamos</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Cuentas_Asignar.jsp">Asignar Cuentas</a></li>
            <li><a class="dropdown-item" href="Cuentas_Ver.jsp">Ver Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Administrar
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Administrar_Clientes.jsp">Admin Clientes</a></li>
            <li><a class="dropdown-item" href="Administrar_Cuentas.jsp">Admin Cuentas</a></li>
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
<%if (!admin){ 
	String nombre=user.getPersona().getNombre()+" "+user.getPersona().getApellido();
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
            <li><a class="dropdown-item" href="Solicitud_Prestamo_Cte.jsp" >Pedir Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamos_Principal.jsp">Ver Prestamos</a></li>
            <li><a class="dropdown-item" href="Detalle_Prestamos_Cte.jsp">Pagar Cuotas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Cuentas_del_Cliente.jsp" >Ver Cuentas</a></li>
            <li><a class="dropdown-item" href="#">Administrar</a></li>
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
<%} %>

<br><br>
<div class="container">
	<div class="row justify-content-md-center">
		<div class="col">
				<%
					ArrayList<Prestamo> listaJSP = new ArrayList<>();
					if (request.getAttribute("listaPrestamos") != null)
						listaJSP = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");

					if (listaJSP.isEmpty()) {
				%>
				<h3>No tienes registrado ningun prestamo!</h3>
				<%
					} else {
				%>
				<table class="table accordion-collapse" id="tablaPrestamos">
					<thead>
						<th>Codigo Prestamo</th>
						<th>Cuenta Destino</th>
						<th>Fecha Solicitud</th>
						<th>Monto Solicitado</th>
						<th>Cuotas Restantes</th>
						<th>Seleccionar</th>
					</thead>
					<%
						if (listaJSP != null)
								for (Prestamo prestamo : listaJSP) {
					%>
					<tr>
						<form action="Pagar_Prestamo_Servlet" method="post">
							<td><%=prestamo.getIDPrestamo()%> <input type="hidden" name="IDPrestamo" value="<%=prestamo.getIDPrestamo()%>"> </td>
							<td><%=prestamo.getNroCuenta()%></td>
							<td><%=prestamo.getFecha()%></td>
							<td>$<%=prestamo.getImporteSolicitado()%></td>
							<td><%=prestamo.getCuotasRestantes()%></td>
							<td><input type="submit" class="btn btn-primary" name="btnSeleccionar" Value="Seleccionar"></td>
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
		
		<div class="col">
		<%
		Prestamo prestamo = new Prestamo();
		if (request.getAttribute("Prestamo") != null)
			prestamo = (Prestamo) request.getAttribute("Prestamo");
		
		int PrestamoSelec = 0;
		if (request.getAttribute("PrestamoSelec") != null)
			PrestamoSelec = (int) request.getAttribute("PrestamoSelec");
		
		if (PrestamoSelec == 1) {%>
			<form action="Pagar_Prestamo_Servlet" method="post">
				<h2>Prestamo seleccionado</h2>
				<br>
				<table class="table-sm table-group-divider w-50">
					<tr>
						<th>N° Prestamo</th>
						<th>N° Cuenta</th>
					</tr>
					<tr>
						<th><input readonly="true" name="IDPrestamoPagar" value="<%= prestamo.getIDPrestamo() %>"></th>
						<th><input readonly="true" value="<%= prestamo.getNroCuenta() %>"></th>
					</tr>
					<tr>
						<th>Fecha de solicitud</th>
						<th>Importe solicitado</th>
					</tr>
					<tr>
						<th><input readonly="true" value="<%= prestamo.getFecha().toString() %>"></th>
						<th><input readonly="true" value="$<%= prestamo.getImporteSolicitado() %>"></th>
					</tr>
					<tr>
						<th>Cuotas Restantes</th>
						<th>valor por cuota</th>
					</tr>
					<tr>
						<th><input readonly="true" value="<%= prestamo.getCuotasRestantes() %>"></th>
						<th><input readonly="true" value="$<%= prestamo.getImporteMensual() %>"></th>
					</tr>
				</table>
				<br><br>
				<div>
					<input type="submit" name="btnPagar" class="btn btn-success" value="Pagar cuota">
				</div>
			</form>
			<%} 
			String msj ="";
			if (request.getAttribute("msj") != null)
				msj = request.getAttribute("msj").toString();
			if (PrestamoSelec == 2){
			%>
			<h3><%= msj %></h3>
			<%} %>
		</div>
	</div>
</div>

<script>
    $(document).ready(function() {
        $('#tablaPrestamos').DataTable();
    });
</script>

</body>
</html>