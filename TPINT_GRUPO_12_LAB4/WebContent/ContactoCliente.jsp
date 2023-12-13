<%@page import="Entidad.Provincia"%>
<%@page import="NegocioImpl.ProvinciaNegocioImpl"%>
<%@page import="Negocio.ProvinciaNegocio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Entidad.Pais"%>
<%@ page import="Negocio.PaisNegocio"%>
<%@ page import="NegocioImpl.PaisNegocioImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Registro</title>
<link rel="stylesheet" type="text/css" href="CSS/ContactoCliente.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
 
    	
        $(document).ready(function () {
        	
            $.post("CargarPaisesServlet", {action: "getPaises"}, function (data) {
                $("#pais").html(data);
            });
        	
            $("#pais").change(function () {
                var paisId = $(this).val();
                $.post("CargarPaisesServlet", {action: "getProvincias", paisId: paisId}, function (data) {
                    $("#provincia").html(data);
                });
            });
            
            $("#provincia").change(function () {
                var provinciaId = $(this).val();
                $.post("CargarPaisesServlet", {action: "getLocalidades", provinciaId: provinciaId}, function (data) {
                    $("#localidad").html(data);
                });
            });
            
            $("#user").on('input',function () {
                var user = $(this).val();
                $.get("ContactoClienteServlet", {action: "checkUser", user: user}, function (response) {
                    $("#respUsuario").html(response);
                });
            });
            $("#pass2").on('input',function () {
                var pass2 = $(this).val();
                var pass = $("#pass").val();
                $.get("ContactoClienteServlet", {action: "checkPass", pass2: pass2, pass:pass}, function (response) {
                    $("#respPass").html(response);
                });
            });
            $("#dni").on('input',function () {
                var dni = $(this).val();
                $.get("ContactoClienteServlet", {action: "checkDni", dni: dni}, function (response) {
                    $("#respDni").html(response);
                });         
        	});   
        }); 
        
    </script>
</head>
<body>
<div class="container">
        <form action="ContactoClienteServlet" method="post">
            <h2>Formulario de Registro</h2>
            
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" maxlength = "50" required>
            
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" maxlength = "50" required>
            
            <label for="dni">DNI:</label>
            <input type="number" id="dni" name="dni" max = "99999999" required>
            <label for="respDni" id="respDni"></label>
            
            <label for="cuil">CUIL:</label>
            <input type="number" id="cuil" max = "9999999999999" name="cuil" required>
            
            <label for="Sexo">Sexo:</label>
            <select id="Sexo" name="Sexo" required>
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
                <option value="X">Prefiero no decirlo</option>
            </select>
            
            
            <label for="pais">Pais:</label>
            <select id="pais" name="pais">
            </select>
            
            <label for="provincia">Provincia:</label>
            <select id="provincia" name="provincia" required>
            </select>
            
            <label for="localidad">Localidad:</label>
            <select id="localidad" name="localidad" required>
            </select>
            
            
            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" maxlength = "100" required>
            
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
            
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" max = "99999999999999999999" name="telefono" required>
            
            <label for="telefono">Teléfono secundario:</label>
            <input type="text" id="telefonoSec" max = "99999999999999999999" name="telefonoSec">
            
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" maxlength = "100" required>
            
            <label for="user">Nombre de Usuario:</label>
            <input type="text" id="user" name="user" maxlength = "25" required>
            <label for="respUsuario" id="respUsuario"></label>
            
            <label for="pass">Contraseña:</label>
            <input type="password" id="pass" name="pass" maxlength = "25" required>
            
            <label for="pass2">Repetetir Contraseña:</label>
            <input type="password" id="pass2" name="pass2" maxlength = "25" required>
            <label for="respPass" id="respPass"></label>
            
            <%
				String msj = " ";
				if(request.getAttribute("usuarioCreado")!=null)
					msj = request.getAttribute("usuarioCreado").toString();
            	boolean check=true;
			%>
            
            <input type="submit" value="Registrar" id="btnRegistrar" name="btnRegistrar" onclick="return checkBTN()">
        </form>
        
        <script>

        function checkBTN() {
          	var respPassValue = document.getElementById('respPass').innerText.trim();
            var respUsuarioValue = document.getElementById('respUsuario').innerText.trim();
            var respDniValue = document.getElementById('respDni').innerText.trim();

            if (respPassValue === "" && respUsuarioValue === "" && respDniValue === "") {
                return true;
            } else {
                alert("Por favor, verificar los mensajes de error para poder proceder.");
                return false;
        }
            }
    	</script>
    </div>
	    
	<%
		msj = " ";
		if(request.getAttribute("usuarioCreado")!=null)
			msj = request.getAttribute("usuarioCreado").toString();
	%>

	<% if(!msj.equals(" ")) { %>
	     <script>
	     	alert('<%=msj %>');
	     </script>
	<% } %>



</body>
</html>