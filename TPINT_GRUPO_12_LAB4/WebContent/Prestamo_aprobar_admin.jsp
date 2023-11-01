<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
	<jsp:include page="Css\Styles.css"></jsp:include>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos en solicitud</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
	<div>
		<h2> Solicitudes en proceso </h2><br>
		<table>
			<tr>
			<th>Filtrar por: </th>
			<th>
				<select>
					<option> En proceso </option>
					<option> Rechazadas </option>
					<option> Aceptadas </option>
				</select>
			</th>
			<th> Cuenta/DNI: <input type="text" name="txtfiltro"> </th>
			<th><input type="submit" name="btnfiltrar" value="Buscar"></th>
			</tr>
		</table>
	</div>
	<div>
		<table class="table accordion-collapse">
			<tr>
				<th> N° cuenta </td>
				<th> DNI </td>
				<th> Apellido y nombre </td>
				<th> Tipo de cuenta </td>
				<th> Estado </td>
				<th> Accion </td>
			</tr>
			<tr>
				<td> 123456 </td>
				<td> 41555888 </td>
				<td> Fremna jose </td>
				<td> Cuenta corriente </td>
				<td> Proceso </td>
				<td> <input type="submit" value="Ampliar" name="txtaccion"> </td>
			</tr>
						<tr>
				<td> 123456 </td>
				<td> 41555888 </td>
				<td> Fremna jose </td>
				<td> Cuenta corriente </td>
				<td> Proceso </td>
				<td>  <input type="submit" value="Ampliar" name="txtaccion">  </td>
			</tr>
		</table><br>
		<input type="submit" name="btnvolver" value="Volver">
	</div>
</body>
</html>