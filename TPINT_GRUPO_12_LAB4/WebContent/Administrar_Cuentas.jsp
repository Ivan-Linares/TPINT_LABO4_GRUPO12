<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar Cuentas</title>
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
            <li><a class="dropdown-item" href="admClientes_Servlet?Param=1"> Admin Clientes </a></li>
            <li><a class="dropdown-item" href="Servlets_AdministraCuentas?Param=1"> Admin Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Informes.jsp">Informes</a>
        </li>
        <li class="nav-item">
         <button class="btn btn-outline-danger" type="submit">Log Out</button>
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
            <li><a class="dropdown-item" href="SolicitudPrestamo.jsp" >Pedir Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamo_solicitud_cliente.jsp">Ver Estado Solicitud</a></li>
            <li><a class="dropdown-item" href="Prestamo_Ver.jsp">Ver Prestamos</a></li>
            <li><a class="dropdown-item" href="#">Pagar Cuotas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="CuentasCliente.jsp" >Ver Cuentas</a></li>
            <li><a class="dropdown-item" href="#">Administrar</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Transferencias.jsp">Transferir</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Datos Personales</a>
        </li>
        <li class="nav-item">
         <button class="btn btn-outline-danger" type="submit">Log Out</button>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%} %>
<br />
	
	<div class="container text-center">
		<div class="row justify-content-md-center">
			<div class="col col-lg-2"></div>
			<div class="col-md-auto">
				<h3>Cuentas Activas:</h3>
				<form id="formulario" action="Servlets_AdministraCuentas" method="get">
					<div>
						Filtrar por:
						<select name="filtro">
							<option value="c.DNI"> Numero de cliente </option>
							<option value="c.Cuenta"> Numero de cuenta </option>
							<option value="tc.descripcion"> Tipo de cuenta </option>
						</select>
						<input type="text" name="txtdatos" onchange="enviardatos" >
						<input type="submit" name="Btnbuscar" value="Buscar">
					</div></br>
				</form>
					<table class="table table-hover">
						<thead>
							<td>Cliente Asignado</td>
							<td>Numero Cuenta</td>
							<td>Tipo Cuenta</td>
							<td>CBU</td>
							<td>Ver Detalle</td>
							<td>Modificar</td>
							<td>Eliminar</td>
						</thead>
						<%
						ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
						if(request.getAttribute("listadocompleto")!=null){
							lista=(ArrayList<Cuenta>)request.getAttribute("listadocompleto");
							
							for(Cuenta cuenta: lista){%>
							<tr>
								<form method="get" action="Servlets_AdministraCuentas" id="cuentasForm">
								<td><%= cuenta.getDni()%></td>
								<td><%= cuenta.getNumero()%><input type="hidden" name="CuentaSeleccionada" value="<%= cuenta.getNumero()%>" ></td>
								<td><%= cuenta.getTipoCuenta().getName()%></td>
								<td><%= cuenta.getCBU()%></td>
								<td><input type="submit" name="btnDetalle" value="Ver Detalle" class="btn btn-primary"></td>
								<td><input type="submit" name="btnModificar" value="Modificar" class="btn btn-primary"></td>
								<td><input type="submit" id="btnEliminar" onclick="return confirmacionEliminar()" name="btnEliminar" value="Eliminar" class="btn btn-primary" ></td>																
								</form>
							</tr>
							<%}
						}
						%>
					</table>
				</div>
			<div class="col col-lg-2"></div>
		</div>
	</div>
	
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="JS/script.js"></script>

<script>
    function confirmacionEliminar() {
       	var respuesta = confirm("Estas seguro de dar de baja esta cuenta?");
        return respuesta; 
    }
</script>	
	
</body>
</html>