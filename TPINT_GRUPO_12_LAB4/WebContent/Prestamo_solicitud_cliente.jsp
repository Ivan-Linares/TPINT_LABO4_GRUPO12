<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes de prestamos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>
<%! boolean admin = false; %>
<!-- Navbar Admin -->
<%if (admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Lio Messi�s Bank</a>
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
<%if (!admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Lio Messi�s Bank</a>
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
            <li><a class="dropdown-item" href="Prestamo_solicitud_cliente.jsp" >Pedir Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamo_solicitud_cliente.jsp">Ver Estado Solicitud</a></li>
            <li><a class="dropdown-item" href="Prestamos_Principal.jsp">Ver Prestamos</a></li>
            <li><a class="dropdown-item" href="Detalle_Prestamos_Cte.jsp">Pagar Cuotas</a></li>
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
	<h2>Solicitar nuevo prestamo</h2>
	<h3>Utilice esta secci�n para nuevas solicitudes de prestamos a acreditar en su cuenta personal. Tenga en cuenta que las solicitudes est�n sujetas a aprobaci�n, dicho proceso puede demoras hasta 48hs h�biles.</h3>
	
	<%if(request.getAttribute("nuevasolicitud")!=null){%>
	<form action="SolicitarPrestamoClienteServlet" method="get">
	<table>
			<tr><th>Cuenta de acreditaci�n </th>
			<th><select name="Cuenta" id="Cuenta">
				<option value="Cuenta1" >Cuenta1</option>
				<option value="Cuenta2" >Cuenta2</option>
				<option value="Cuenta3" >Cuenta3</option></th></tr>
				<tr><th>Monto Solicitado</th><th><input type="number" name="txtcostocontratacion" min="10000" required></th></tr>
				<tr><th>Cantidad de cuotas</th>
				<th><select name="Cuota" id="Cuota">
				<option value="6" >6</option>
				<option value="12" >12</option>
				<option value="24" >24</option></th></tr>
	</table>
	
		<input type="submit" name="btnSimular" value="Simular prestamo">
	</form>
	<%}%>
	
	<%if(request.getAttribute("Prestamo")!=null){%>
		<form action="SolicitarPrestamoClienteServlet" method="post">
	<table>
			<tr><th>Cuenta Seleccionada</th>
			<th>1000001</th></tr>
			<tr><th>Monto a acreditar</th>
				<th>$30000</th></tr>
				<tr><th>Plazo de pago(en meses)</th>
				<th>12</th></tr>
				<tr><th>Valor de cuota</th>
				<th>2500</th></tr>
				
	</table>
	
	<input type="submit" name="btnConfirmar" value="Confirmar prestamo">
</form>
<form action="SolicitarPrestamoClienteServlet" method="post">
	<input type="submit" name="btnCancelar" value="Cancelar prestamo">
</form>
<%}%>
</body>
</html>