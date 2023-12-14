<%@page import="Entidad.TipoMovimiento"%>
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
}
%>
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

<%


if (!admin){ 
	String nusuario = user.getUser();
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
		String nc="";
		for(Cuenta cuenta : listacuentas){
			c++;
			nc=cuenta.getDni();
		%>
    <div class="col" style="margin-left:8%">
		<div class="card" style="width: 18rem; margin-bottom:15px">
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
			<form method="post" action="Serv_Solicitar_CuentaNueva?Param=<%=nc%>">
			    <div>
			    	<input type="submit" value="Solicitar nueva caja de ahorro" name="BtnCA">
			    	<input type="submit" value="Solicitar nueva cuenta corriente" name="BtnCC">
			    </div>			
			</form>
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
	
	<form method="get" action="Servlet_Movimientos_X_Cte">
		<select name="filtro">
	<%ArrayList<TipoMovimiento> lista = new ArrayList<TipoMovimiento>();
		int tope=2;
		if(request.getAttribute("listafiltro")!=null){
			lista = (ArrayList<TipoMovimiento>)request.getAttribute("listafiltro");
			for(TipoMovimiento tm : lista){
				if(tm.getCode()>tope){%>
					<option value="<%= tm.getCode() %>"><%= tm.getName() %> mayor a $</option>
					
				<%}
				}
			}
		%>
		</select>
		<input type="text" name="txtvalor">
		<input type="submit" name="btnfiltro" value="Filtrar">
	</form>
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