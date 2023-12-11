<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
    <%@page import="Entidad.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle Clientes por Aprobar</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
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
		    <form action="validarLoginServlet" method="get">
		        <input type="submit" value="Cerrar Sesión">
		    </form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%} %>
<br />
<%!
    Cliente cliente = null;
%>
<%	
    if(request.getAttribute("ClientePend") != null) {
        cliente = (Cliente) request.getAttribute("ClientePend");
    }
%>
<div class="row">
    <div class="col">
        <label for="nombre">DNI:</label>
        <input type="text" name="DNI" value="<%= cliente.getDni() %>" class="form-control" disabled><br>
        
        <label for="CUIL">CUIL:</label>
        <input type="text" name="CUIL" value="<%= cliente.getCuil() %>" class="form-control" disabled><br>
        
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" value="<%= cliente.getNombre() %>" class="form-control" disabled><br>
        
        <label for="nombre">Apellido:</label>
        <input type="text" name="Apellido" value="<%= cliente.getApellido() %>" class="form-control" disabled><br>
        
        <label for="nombre">Sexo:</label>
        <input type="text" name="Sexo" value="<%= cliente.getSexo() %>" class="form-control" disabled><br>
        
        <label for="nombre">Email:</label>
        <input type="text" name="Email" value="<%= cliente.getEmail() %>" class="form-control" disabled><br>
	  </div>
	  <div class="col">
 		<label for="nombre">Pais:</label>
        <input type="text" name="Pais" value="<%= cliente.getLocalidad().getProvincia().getPais().getName() %>" class="form-control" disabled><br>
        
        <label for="nombre">Provincia:</label>
        <input type="text" name="Provincia" value="<%= cliente.getLocalidad().getProvincia().getNombreProvincia() %>" class="form-control" disabled><br>
        
        <label for="nombre">Localidad:</label>
        <input type="text" name="Localidad" value="<%= cliente.getLocalidad().getNombreLocalidad() %>" class="form-control" disabled><br>
        
        <label for="nombre">Direccion:</label>
        <input type="text" name="Direccion" value="<%= cliente.getDireccion() %>" class="form-control" disabled><br>
        
        <label for="nombre">Fecha Nacimiento:</label>
        <input type="text" name="FechaNac" value="<%= cliente.getFechaNac().toString() %>" class="form-control" disabled><br>
        
        <label for="nombre">Telefono:</label>
        <input type="text" name="Telefono" value="<%= cliente.getTelefono() %>" class="form-control" disabled><br>
	  </div>
	</div> <br>
	<a class="btn btn-primary" href="cuentasAsignarServlet?Param=1">Volver</a>

</body>
</html>