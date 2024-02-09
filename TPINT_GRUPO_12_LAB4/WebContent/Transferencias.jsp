<%@page import="Entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencia</title>
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
<%} %>
<br />
	<h2>Nueva trasferencia</h2>
	<br>
	<form method="post" action="Transferencias?Param=<%=nusuario%>"
		class="alert">
		<div>
			<input type="submit" name="btntranferencia1mismo"
				value="Transferir a una cuenta propia"> <input type="submit"
				name="btntranferencia3ro" value=" Transferir a un tercero ">
		</div>
		<br>
		<%String msj;
			if(request.getAttribute("msj")!=null){
				msj=request.getAttribute("msj").toString();%>
				<H2><%= msj %></H2>
			<%}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		if (request.getAttribute("miscuentas") != null) {
		%>
		<div>
			<label>Seleccionar cuenta desde la cual realizara la
				transferencia </label><br> <b>Cuentas</b> <select name="cuentasorigen">
				<%
					lista = (ArrayList<Cuenta>) request.getAttribute("miscuentas");
						for (Cuenta ct : lista) {
				%>
				<option value="<%=ct.getNumero()%>">N°
					<%=ct.getNumero()%>- Saldo $<%=ct.getSaldo()%></option>
				<%
					}
				%>
			</select>
		</div>
		<br>
		<div>
			<label>Seleccionar cuenta destino </label><br> <b>Cuentas</b> <select
				name="cuentasdestino">
				<%
					for (Cuenta ct : lista) {
				%>
				<option value="<%=ct.getNumero()%>">N°
					<%=ct.getNumero()%>- Saldo $<%=ct.getSaldo()%></option>
				<%
					}
				%>

			</select>
		</div>
		</br>
		</br>
		<%
			}
		%>

		<%
			if (request.getAttribute("3ros") != null) {
		%>
		<div></div>
		<br>
		<div>
			<label>Seleccionar cuenta desde la cual realizara la
				transferencia </label><br> <b>Cuentas</b> <select name="cuentasorigen">
				<%
					lista = (ArrayList<Cuenta>) request.getAttribute("3ros");
						for (Cuenta ct : lista) {
				%>
				<option value="<%=ct.getNumero()%>">N°
					<%=ct.getNumero()%>- Saldo $<%=ct.getSaldo()%></option>
				<%
					}
				%>
			</select>
		</div>
		<br> <input type="number" name="CBU" placeholder="Ingresar CBU" required ></br>
		</br>
		<%
			}
		%>
		<%
			if (request.getAttribute("3ros") != null || request.getAttribute("miscuentas") != null) {
		%>
		<input type="number" min="100" name="Monto" placeholder="Ingresar monto" ></br>
		</br>
		<%
			}
		%>
		<%
			if (request.getAttribute("3ros") != null || request.getAttribute("miscuentas") != null) {
		%>
		<input class="btn btn-info" type="submit" name="btntransferir" value="Transferir">
		<%}%>
		</div>
	</form>
</body>
</html>