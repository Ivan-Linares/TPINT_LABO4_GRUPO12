<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Movimiento"%>
<%@page import="Entidad.Informe"%>
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

    <script>
 
    	
        $(document).ready(function () {
        	
            $.post("CargarPaisesServlet", {action: "getPaises"}, function (data) {
                $("#pais").html("<option value='' selected disabled>Seleccione una opción...</option>" + data);
            });
        	
            $("#pais").change(function () {
                var paisId = $(this).val();
                $.post("CargarPaisesServlet", {action: "getProvincias", paisId: paisId}, function (data) {
                    $("#provincia").html("<option value='' selected disabled>Seleccione una opción...</option>" + data);
                });
            });
            
        }); 
        
    </script>

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
        <button type="submit" name="btnPorRegion">Resumen por región</button>
        <button type="submit" name="btnAcumuladoTipoMov">Acumulado por Tipo de movimiento</button>
    </form>
    
<% 	String tipoInforme = "";
	if(request.getAttribute("tipoInforme") != null){
		tipoInforme = request.getAttribute("tipoInforme").toString();
	}	
%>

<%if ("resumenRegion".equals(tipoInforme)) { %>
	<div style="margin-top: 30px;"> 
		<form action="informes_Servlet" method="post">		
		        <label for="pais">Pais:</label>
	            <select id="pais" name="pais">
	            <option value="" selected disabled>Seleccione una opción...</option>
	            </select>
	            
	            <label for="provincia">Provincia:</label>
	            <select id="provincia" name="provincia">
	            </select> 
	            <button type="submit" name="btnPorRegionFilter">Generar reporte</button>
	            <button type="submit" name="btnPorRegion">Borrar filtros</button>
     	</form>
     </div>
 <%
 	ArrayList<Informe> listado = new ArrayList<Informe>();
	String filtro = "";
 	String title="";
	if (request.getAttribute("filtro") != null) {
		filtro = request.getAttribute("filtro").toString();
		title="Resultados para "+filtro;
	}
	
	else{
		title="Resultados totales por región";
	}
	
	if(request.getAttribute("InformeRegion")!=null){
		listado=(ArrayList<Informe>)request.getAttribute("InformeRegion");
	}
%>
	<div style="margin-top: 20px;"> 
		<h2><%=title%></h2>
	</div>
	
		<div class="container text-center" style="margin-top: 30px;">
		<div class="row justify-content-md-center">
			<div class="col col-lg-2"></div>
			<div class="col-md-auto">
				<table class="table table-hover" id="tablaMovimientos">
					<thead>
						<tr>
							<th>Region</th>
							<th>Clientes Activos</th>
							<th>Clientes Inactivos</th>
							<th>Clientes pendientes</th>
							<th>Saldo total</th>
							<th>Movimientos altas cuenta</th>
							<th>Movimientos altas prestamos</th>
							<th>Movimientos pago prestamos</th>
							<th>Movimientos extracciones</th>
							<th>Movimientos depositos</th>
						</tr>
					</thead>
					<tbody>
					<%  if (listado != null) 
						for(Informe i : listado){
					%>
						<tr>
							<td><%= i.getRegion() %></td>
							<td><%= i.getClientesActivos() %></td>
							<td><%= i.getClientesInactivos() %></td>
							<td><%= i.getClientesPendientes() %></td>
							<td><%= i.getSaldoTotal() %></td>
							<td><%= i.getMovimientosAltasCuenta() %></td>
							<td><%= i.getMovimientosAltasPrestamo() %></td>
							<td><%= i.getMovimientosPagoPrestamo() %></td>
							<td><%= i.getMovimientosExtracciones() %></td>
							<td><%= i.getMovimientosDeposito() %></td>
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

              
<%} %>

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