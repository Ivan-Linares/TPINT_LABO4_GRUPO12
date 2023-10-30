<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informes</title>
</head>
<body>
<div class="navbar">
	<div>
		*Logo del banco*
	</div>
	<ul>
		<li><a href="#ASIGNARCUENTA">ASIGNAR CUENTA</a></li>
    	<li><a href="#PRESTAMOSSOLICITUD">PRESTAMOS EN SOLICITUD</a></li>
    	<li><a href="#INFORMES">INFORMES</a></li>
    	<li><a href="#CUENTAS">CUENTAS</a></li>
   		<li><a href="#TRANSFERENCIAS">TRANSFERENCIAS</a></li>
   		<li><a href="#PRESTAMOS">PRESTAMOS</a></li>
	</ul>
	<div>
		*Bienvenido usuario x*
	</div>
</div>

<h1>Seleccione un Informe</h1>
    <div action="servlet" method="post">
        <button type="submit">Resumen de Cuentas</button>
        <button type="submit">Historial de Movimientos</button>
        <button type="submit">Registros de actividad</button>
    </div>
    
</body>
</html>