<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
	<jsp:include page="Css\Styles.css"></jsp:include>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar cuenta</title>
</head>
<body>
<div>
	<h2>DNI del solicitante <input> <input type="submit" value="Buscar">
	<h6>usuario existente</h6></br>
</div>
<div>
	<h2>Datos</h2></br>
		Apellido <input>
		Nombre <input>
		sexo <select>
				<option>Masculino</option>
				<option>Femenino</option>
			</select>	
		DNI <input></br></br>
		CUIL <input> Telefono <input> Email <input> Fecha de nacimiento <input type="date"></br></br>
		Nacionalidad <input> Provincia <input>	Localidad  <input> Direccion <input></br></br>
		Usuario <input>
		
		<h2>Cuentas</h2>
		1° Cuenta  <input> 2° Cuenta  <input> 3° Cuenta <input></br></br>
	
		<input type="submit" value="volver"> <input type="submit" value="Ingresar"> o <input type="submit" value="Agregar">
</div>
</body>
</html>