<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes de prestamos</title>
</head>
<body>
	<h2>Solicitar nuevo prestamo</h2>
	<h3>Utilice esta sección para nuevas solicitudes de prestamos a acreditar en su cuenta personal. Tenga en cuenta que las solicitudes están sujetas a aprobación, dicho proceso puede demoras hasta 48hs hábiles.</h3>
	
	<%if(request.getAttribute("nuevasolicitud")!=null){%>
	<form action="SolicitarPrestamoClienteServlet" method="get">
	<table>
			<tr><th>Cuenta de acreditación </th>
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
	
		<input type="submit" name="btnConfirmar" value="Confirmar prestamo"></form><form action="SolicitarPrestamoClienteServlet" method="post"><input type="submit" name="btnCancelar" value="Cancelar prestamo"></form>

	
	<%}%>
	
</body>
</html>