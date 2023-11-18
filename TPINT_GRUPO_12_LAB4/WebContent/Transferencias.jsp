<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencia</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<%! boolean admin = false; %>

<!-- Navbar Admin -->

<%if (admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Lio Messi´s Bank</a>
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
    <a class="navbar-brand" href="#">Lio Messi´s Bank</a>
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
          <a class="nav-link" href="Datos_Personales_cte.jsp">Datos Personales</a>
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
<h2>Nueva trasferencia</h2><br>
<form method="get" action="Transferencias" class="alert">
<div>
		<input type="submit" name="btntranferencia1mismo" value="Transferir a una cuenta propia">
		<input type="submit" name="btntranferencia3ro" value=" Transferir a un tercero ">	
</div><br>
<%if(request.getAttribute("miscuentas")!=null){%>
	<div>
		<label>Seleccionar cuenta desde la cual realizara la trasnferencia </label><br>
		<b>cuentas</b>
		<select name="cuentas">
			<option>cuenta 1</option>
			<option>cuenta 2</option>
			<option>cuenta 3</option>
		</select>
	</div><br>
	<div>
		<label>Seleccionar cuenta destino </label><br>
		<b>cuentas</b>
		<select name="cuentas">
			<option>cuenta 1</option>
			<option>cuenta 2</option>
			<option>cuenta 3</option>
		</select>
	</div></br></br><%	
}%>
<%if(request.getAttribute("3ros")!=null){%>
	<div>
		<label>Selecionar medio </label>
		<div class="form-check form-check-inline">
	  		<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="CBU">
	  		<label class="form-check-label" for="inlineRadio1"> CBU</label>
		</div>
		<div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="Alias">
		  <label class="form-check-label" for="inlineRadio2"> Alias</label>
		</div><br><br>
		<input type="text" value="" placeholder=""></br></br>	
<%}%>
<%if(request.getAttribute("3ros")!=null || request.getAttribute("miscuentas")!=null) {%>
	<input type="text" value="" placeholder="Ingresar monto"></br></br>
<%}%>
	
	<input   class="btn btn-success"  type="submit"    name="btnvolver" value="Volver">
<%if(request.getAttribute("3ros")!=null || request.getAttribute("miscuentas")!=null) {%>
	<input   class="btn btn-info"  type="submit" name="btntransferir" value="Transferir">	
<%}%>
</div>
</form>
</body>
</html>