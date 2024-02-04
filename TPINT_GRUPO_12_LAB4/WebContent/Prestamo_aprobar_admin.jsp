<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Prestamos en solicitud</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
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

<%! boolean SolicitudPrestamo = true; %>
<div class="container text-center">
  <div class="row justify-content-md-center">
    <div class="col col-lg-2">
    </div>
    <div class="col-md-auto">
     	<%if (!SolicitudPrestamo) {%>
		<h3>No hay solicitudes pendientes de aprobacion!</h3>
		<%} else {%>
		<h3>Tienes solicitudes de los siguientes clientes:</h3>
		 <table class="table table-hover">
		 <thead>
	            <td>DNI</td>
	            <td>Nombre</td>
	            <td>Apellido</td>
	            <td>Monto del Préstamo</td>
	            <td>Fecha Solicitud</td>
	            <td>Acción</td>
	            <td>Enviar</td>
	        </thead>
 		<%
 		    int i = 0;
            while (i < 3) {
        %>	        
	        <tr>
	            <td>42899522</td>
	            <td>Ivan</td>
	            <td>Linares</td>
	            <td>1.000.000</td>
	            <td>2/11/2023</td>
	            <td> 
	            	<select class="form-select" aria-label="Default select example">
					  <option selected>Aprobar</option>
					  <option value="1">Rechazar</option>
					</select>
				</td>
				<td><input type="submit" value="Enviar" class="btn btn-primary"></td>
	        </tr>
	    <%
          i++;
            }
        %>
		</table>
		<%} %>
    </div>
    <div class="col col-lg-2">
    </div>
  </div>
</div>

</body>
</html>