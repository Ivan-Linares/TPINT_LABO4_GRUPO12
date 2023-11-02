<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<div class="btn-group" role="group" aria-label="Basic example">
	<form method="get" action="SolicitarPrestamoClienteServlet">
	  <input type="submit" class="btn btn-primary" Value="Solicitar nuevo prestamo" name="btnsolicitarprestamo"><br><br>
	</form>
</div>
<div>
	<div><h3>Prestamos solicitados</h3></div>
	<table class="table accordion-collapse">
		<form method="get" action="DetalleDelPrestamo">
			<tr>
				<th> N° cuenta </th>
				<th> Fecha solicitud </th>
				<th> Importe </th>
				<th> Cuotas pendientes </th>
				<th> Estado </th>
				<th> Accion </th>
			</tr>
			<tr>
				<td> 123456 </td>
				<td> 24/04/2021 </td>
				<td> $33000 </td>
				<td> 6 </td>
				<td> Proceso </td>
				<td> <input type="submit" value="Ampliar" name="txtaccion"> </td>
			</tr>
			<tr>
				<td> 123456 </td>
				<td> 02/04/2021 </td>
				<td> $800000</td>
				<td> x </td>
				<td> Cancelado  </td>
				<td>  <input type="submit" value="Ampliar" name="txtaccion">  </td>
			</tr>
		</form>
	</table>
</div>
<div class="btn-group" role="group" aria-label="Basic example">
  <input type="submit" class="btn btn-primary" Value="Volver">
</div>
</body>
</html>