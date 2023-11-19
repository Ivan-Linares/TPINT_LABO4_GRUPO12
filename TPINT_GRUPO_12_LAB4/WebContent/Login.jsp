<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<div class="container text-center" style=padding-top:100px>
  <div class="row align-items-center">
    <div class="col align-self-center">
      		<form action="validarLoginServlet" method="post">
			<input  type="text"  placeholder="Usuario..."  required name="txtusuario"></br></br>
			<input  type="text"  placeholder="Contraseña.."  required name="txtcontraseña"></br>
			<a Style=margin-left:20px; href="servlet">Olvidaste la clave?</a></br></br>
			<input   class="btn btn-info"  type="submit"    name="btningresar" value="Ingresar">
			<input   class="btn btn-success"  type="submit"    name="btnregistrarse" value="registrarse">
    </div>
  </div>
</div>

<%if(request.getAttribute("Validar")!=null){
		String resp=(String)request.getAttribute("Validar");%>
		<h3><%= resp %></h3>
<%}%>

<%if(request.getAttribute("Client")!=null){
		Usuario resp=(Usuario)request.getAttribute("Client");%>
		<h3><%=resp.getPersona().getNombre()%></h3>
<%}%>
</body>
</html>