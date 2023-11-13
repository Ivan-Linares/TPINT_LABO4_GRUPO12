<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	.Caja-1{
	position: absolute;
	top:100px;
	right:40%;
	width: 340px;
	height: 160px;
	background-color: skyblue;
	border: 2px solid black;
}
.Caja-1-5{
	display: grid;
	place-items: center;
	margin-top:15px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<div class="Caja-1">
	<div class="Caja-1-5">
		<form action="validarLoginServlet" method="post">
			<input  type="text"  placeholder="Usuario..."  required name="txtusuario"></br></br>
			<input  type="text"  placeholder="Contraseña.."  required name="txtcontraseña"></br>
			<a Style=margin-left:20px; href="servlet">Olvidaste la clave?</a></br></br>
			<input  Style=margin-left:50px;  type="submit"    name="btningresar" value="Ingresar">
		</form>	
	</div>
</div>

<%if(request.getAttribute("Validar")!=null){
		String resp=(String)request.getAttribute("Validar");%>
		<h2><%= resp %></h2>
<%}%>

<%if(request.getAttribute("Client")!=null){
		Usuario resp=(Usuario)request.getAttribute("Client");%>
		<h2><%=resp.getPersona().getNombre()%></h2>
<%}%>
</body>
</html>