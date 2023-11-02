<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle del Prestamo</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<h2>Prestamo seleccionado</h2><br>
<table class="table-group-divider w-50">
	<tr><th> N° cuenta </th><th> Fecha de solicitud </th></tr>
	<tr><th> <input readonly="true" value="8"> </th><th> <input readonly="true" value="8"> </th></tr>
	<tr><th> Importe solicitado  </th><th> valor por cuota </th></tr>
	<tr><th> <input readonly="true" value="8"> </th><th> <input readonly="true" value="8"> </th></tr>
	<tr><th> Cantidad de cuotas  </th><th> N° de cuotas pagadas </th></tr>
	<tr><th> <input readonly="true" value="8"> </th><th> <input readonly="true" value="8"> </th></tr>
</table><br>
<div>
	Proxima cuota a pagar  <input type="number" readonly="true" value="8"> <br>
	
</div><br><br>
<div>
	<input type="submit" class="btn btn-primary btn-sm" value="Volver">
	<input type="submit" class="btn btn-secondary btn-sm" value="Pagar cuota">
</div>
</body>
</html>