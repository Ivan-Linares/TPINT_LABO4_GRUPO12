<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Movimiento"%>
<%@page import="Entidad.TipoMovimiento"%>
<%@page import="NegocioImpl.TipoMovimientoNegocioImpl"%>
<%@page import="Negocio.TipoMovimientoNegocio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informes</title>
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


<h1>Seleccione un Informe</h1>
    <form action="informes_Servlet" method="post">
        <button type="submit">Resumen de Cuentas</button>
        <button type="submit">Historial de Movimientos</button>
        <button type="submit">Registros de actividad</button>
        <button type="submit" name="btnAcumuladoTipoMov">Acumulado por Tipo de movimiento</button>
    </form>
    
<% 	String tipoInforme = "";
	if(request.getAttribute("tipoInforme") != null){
		tipoInforme = request.getAttribute("tipoInforme").toString();
	}	
%>

<%if ("acumuladoTipoMov".equals(tipoInforme)) { %>   
	
	
	<form action="informes_Servlet" method="post">
		
		<br><br>
		
		<label for="tipoMov">Tipo de movimiento:</label>
        <select name="SeltipoMov">
			<%TipoMovimientoNegocio tmNegocio = new TipoMovimientoNegocioImpl();
			for (TipoMovimiento tm : tmNegocio.listar()) {
			%>
			<option value="<%=tm.getCode()%>" ><%=tm.getName()%></option>			
			<% } %>
		</select>
	
		<label for="fechaInicio">Fecha de inicio:</label>
    	<input type="date" id="fechaInicio" name="fechaInicio" required>
        
        <label for="fechaFin">Fecha de fin:</label>
    	<input type="date" id="fechaFin" name="fechaFin" required>
    	
    	<button type="submit" name="btnGenerarReporte">Generar reporte</button>
    	
    	<br><br>
    	
    </form>
    
    <%
	String total = "";
	if (request.getAttribute("total") != null) {
		total = request.getAttribute("total").toString();
	}
%>

<%if (total != "") { %>   
	<br><br>
	<label for="TotalMov">El total de movimientos relacionados a fechas y tipo seleccionados es:</label>
    <input type="text" name="TotalMov" value="<%= total%>" class="form-control d-inline w-auto" disabled><br>
<%} %>
    
    <%
				ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
				if(request.getAttribute("listaMovimientos") != null)
					listaMovimientos = (ArrayList<Movimiento>)request.getAttribute("listaMovimientos");
				
				if (listaMovimientos.isEmpty()) {
				%>
				<h3>No hay movimientos para mostrar!</h3>
				<%
					} 
				else {
				%>
				
	<h3>Historial completo de movimientos</h3>
	
	<div class="container text-center">
		<div class="row justify-content-md-center">
			<div class="col col-lg-2"></div>
			<div class="col-md-auto">
				<table class="table table-hover" id="tablaMovimientos">
					<thead>
						<tr>
							<th>Cuenta</th>
							<th>Fecha</th>
							<th>Concepto</th>
							<th>Importe</th>
						</tr>
					</thead>
					<tbody>
					<%  if (listaMovimientos != null) 
						for(Movimiento mov : listaMovimientos){
					%>
						<tr>
							<td><%= mov.getCuenta() %></td>
							<td><%= mov.getFechaTef() %></td>
							<td><%= mov.getTipoMovimiento() %></td>
							<td><%= mov.getImporte() %></td>
						</tr>
					<%
						}
					%>	
					</tbody>
				</table>		
			</div>
			<div class="col col-lg-2"></div>
		</div>
	</div>	
	<% 
		}
	%>
	
<% 
	}
%>


<script>
    $(document).ready(function() {
        $('#tablaMovimientos').DataTable();
    });
</script>

    
</body>
</html>