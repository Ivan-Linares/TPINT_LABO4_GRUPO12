<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva transferencia</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<div>
	<h2>Seleccionar cuenta</h2>
	<select id="listaCuentas" onchange="cargarHistorial()">
    <%
       for(int i = 0; i < 2; i++) { // Simulamos 2 cuentas
    %>
    <option value="<%= i %>"><%= "Cuenta " + (i + 1)%></option>
    <% } %>
	</select>
	<table><br><br>
		<tr>
			<th><input type="text" placeholder="Ingresar CBU...."></th>
			<th><input type="text" placeholder="Monto a transferir...."></th>
		</tr>
	</table>
	<button type="button" class="btn btn-primary btn-lg">Transferir</button>
</div>
</body>
</html>