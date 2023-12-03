<%@page import="Entidad.Localidad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar cliente</title>
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

<form action="admCtes_Modificar_Servlets" method="post">
	<div class="row">
	  <div class="col">
	  <%
	  Cliente obj = new Cliente();
	  if(request.getAttribute("Seleccionado")!=null){
		  obj= (Cliente)request.getAttribute("Seleccionado");
		  %>
	    <label for="nombre">DNI:</label>
        <input type="text" id="nombre" name="DNI" value="<%= obj.getDni() %>" class="form-control"><br>
        
        <label for="nombre">CUIL:</label>
        <input type="text" id="nombre" name="cuil" value="<%= obj.getCuil() %>" class="form-control"><br>
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= obj.getNombre() %>" class="form-control"><br>
        
        <label for="nombre">Apellido:</label>
        <input type="text" id="nombre" name="Apellido" value="<%= obj.getApellido() %>" class="form-control"><br>
        
        <label for="nombre">Email:</label>
        <input type="text" id="nombre" name="Email" value="<%= obj.getEmail() %>" class="form-control"><br>
        
        <label for="nombre">Usuario:</label>
        <input type="text" id="nombre" name="Usuario" value="<%= obj.getUsuario() %>" class="form-control"><br>
        
	  </div>
	  <div class="col">
	  	<label for="nombre">Sexo:</label><br>
	  	<select name="Sexo" class="ratio w-25 tooltip-inner">
	  		<option value="<%= obj.getSexo() %>" selected> <%= obj.getSexo() %> </option>
	  		<option value="F">F</option>
	  		<option value="M">M</option>
	  		<option value="X">X</option>
	  	</select><br><br>
       
        <label for="nombre">Fecha Nacimiento:</label>
        <input type="text" id="nombre" name="FechaNac" value="<%= obj.getFechaNac()%>" class="form-control"><br>
        
        <label for="nombre">Telefono:</label>
        <input type="text" id="nombre" name="Tel" value="<%= obj.getTelefono() %>" class="form-control"><br>
        
        <label for="nombre">Direccion:</label>
        <input type="text" id="nombre" name="Direccion" value="<%= obj.getDireccion() %>" class="form-control"><br>
        
 		<label for="nombre">Localidad  -  Provincia  -  Pais</label><br><br>
 		<select name="localidad" class="ratio w-50 tooltip-inner">
 			<option value="<%= obj.getLocalidad().getCodLocalidad() %>" selected ><%= obj.getLocalidad().getNombreLocalidad() + "  -  " + obj.getLocalidad().getProvincia().getNombreProvincia()+"  -  "+obj.getLocalidad().getProvincia().getPais().getName() %></option>
	 		<%
	 		ArrayList<Localidad> listado = new ArrayList<Localidad>();
	 		if(request.getAttribute("listaLocalidades")!=null){
	 			listado = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
	 			for(Localidad loc : listado){
	 				%>
	 				<option value="<% loc.getCodLocalidad(); %>"><%= loc.getNombreLocalidad() + " - " + loc.getProvincia().getNombreProvincia() + " - " + loc.getProvincia().getPais().getName()%></option>
	 			<%	
	 			}
	 		}
	 		%>
 			<option></option>
 		</select><br><br>
 		        
        <label for="nombre">Contraseña:</label>
        <input type="password" id="nombre" name="Contraseña" value="<%= obj.getPass() %>" class="form-control"><br>
        <%
        }
       %>
	  </div>
	</div>
	<input type="submit" value="Cancelar" name="btncancelar" class="btn btn-danger">
	<input type="submit" value="Confirmar" name="btnconfirmar" class="btn btn-success">
</form>

</body>
</html>