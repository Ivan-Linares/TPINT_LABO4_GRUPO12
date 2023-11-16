<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Registro</title>
<link rel="stylesheet" type="text/css" href="CSS/ContactoCliente.css">
</head>
<body>
<div class="container">
        <form action="ContactoClienteServlet" method="post">
            <h2>Formulario de Registro</h2>
            
            <label for="lblApellido">Apellido:</label>
            <input type="text" id="apellido" name="txtApellido" required>
            
            <label for="lblNombre">Nombre:</label>
            <input type="text" id="nombre" name="txtNombre" required>
            
            <label for="lblDNI">DNI:</label>
            <input type="text" id="dni" name="txtDNI" required>
            
            <label for="lblCUIL">CUIL:</label>
            <input type="text" id="cuil" name="txtCUIL" required>
            
            <label for="lblSexo">Sexo:</label>
            <select id="sexo" name="selSexo" required>
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="NA">Prefiero no decirlo</option>
            </select>
            
            <label for="lblPais">País:</label>
            <input type="text" id="pais" name="txtPais" required>
            
            <label for="lblProvincia">Provincia:</label>
            <input type="text" id="provincia" name="txtProvincia" required>
            
            <label for="lblLocalidad">Localidad:</label>
            <input type="text" id="localidad" name="txtLocalidad" required>
            
            <label for="lblDireccion">Dirección:</label>
            <input type="text" id="direccion" name="txtDireccion" required>
            
            <label for="lblFechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="dateFechaNacimiento" required>
            
            <label for="lblTelefono">Teléfono:</label>
            <input type="text" id="telefono" name="txtTelefono" required>
            
            <label for="lblEmail">Email:</label>
            <input type="email" id="email" name="txtEmail" required>
            
            <label for="lblUser">Nombre de Usuario:</label>
            <input type="text" id="user" name="txtUser" required>
            
            <label for="lblPass">Contraseña:</label>
            <input type="text" id="pass" name="txtPass" required>
            
            <label for="lblPass2">Repetetir Contraseña:</label>
            <input type="text" id="pass2" name="txtPass2" required>
            
            <input type="submit" value="Registrar" name="btnRegistrar">
        </form>
    </div>
    
<%
	boolean insert = false;
	if(request.getAttribute("insert")!=null)
		insert = (boolean)request.getAttribute("insert");		
	
%>

<% if(insert)
     { %>
     
	Solicitud procesada con exito
	
<% } %>


</body>
</html>