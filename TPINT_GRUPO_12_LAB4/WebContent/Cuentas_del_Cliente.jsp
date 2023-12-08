<%@page import="Entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
         <button class="btn btn-outline-danger" type="submit">Log Out</button>
        </li>
      </ul>
    </div>
  </div>
</nav>
</br>

<h2>Bienvenido, <%= nombre %></h2> 
<%} %>
<h3>Tus cuentas:</h3></br>

<div class="container text-center">
  <div class="row align-items-center">
	<%
	Cuenta obj = new Cuenta();
	ArrayList<Cuenta> listacuentas=new ArrayList<Cuenta>();
	if(request.getAttribute("listaCuentas")!=null){
		listacuentas = (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
		int c=0;
		for(Cuenta cuenta : listacuentas){
			c++;
		%>
    <div class="col">
		<div class="card" style="width: 18rem;">
		  <div class="card-body">
			    <h5 class="card-title">Cuenta N°<%= c %></h5>
			    <h6 class="card-subtitle mb-2 text-body-secondary"><%= cuenta.getNumero() %></h6>
			    <p class="card-text">CBU: <%= cuenta.getCBU() %></p>
			    <p class="card-text">Saldo: $<%= cuenta.getSaldo() %></p>
			    <p class="card-text"><%= cuenta.getTipoCuenta().getName() %></p>
			    <a href="Servlet_Movimientos_X_Cte?cuenta=<%= cuenta.getNumero() %>" class="card-link">Ver movimientos</a>
			  </div>
		</div>      
    </div>
  		<%}
		int max=3;
			if(c<3){%>
		    <div>
		    	<input type="submit" value="Solicitar nueva cuenta">
		    </div>
			<%}
		}
		%>
  </div>
</div></br></br>
<div Class="border border-black">
</div> 
</br>
<div>
	<%
	ArrayList<Movimiento> listadomov = new ArrayList<Movimiento>();
	if(request.getAttribute("listaMovimientos")!=null){
		listadomov = (ArrayList<Movimiento>)request.getAttribute("listaMovimientos");%>
	<h2>Historial de movimientos</h2></br>
	<h4><input type="text" value="Cuenta seleccionada" disabled></h4>
	<table class="table accordion-button">
		<tr>
			<th>Fecha</th>
			<th>Movimiento</th>
			<th>Importe</th>		
		</tr>
		<%for(Movimiento mv : listadomov){%>
			<tr>
				<td><%= mv.getFechaTef().toString() %></td>
				<td><%= mv.getTipoMovimiento().getName() %></td>
				<td>$<%= mv.getImporte() %></td>		
			</tr>
		<%}
	}
	%>
	</table>
</div>

</body>
</html>