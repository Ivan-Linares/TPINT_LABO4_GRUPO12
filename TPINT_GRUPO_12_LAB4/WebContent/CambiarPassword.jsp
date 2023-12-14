<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar contraseña</title>
<link rel="stylesheet" type="text/css" href="CSS/ContactoCliente.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
 
    	
        $(document).ready(function () {
        	          
            $("#user").on('input',function () {
                var user = $(this).val();
                $.get("CambiarPasswordServlet", {action: "checkUser", user: user}, function (response) {
                    $("#respUsuario").html(response);
                });
            });
            $("#pass2").on('input',function () {
                var pass2 = $(this).val();
                var pass = $("#pass").val();
                $.get("CambiarPasswordServlet", {action: "checkPass", pass2: pass2, pass:pass}, function (response) {
                    $("#respPass").html(response);
                });
            });
            $("#dni").on('input',function () {
                var dni = $(this).val();
                $.get("CambiarPasswordServlet", {action: "checkDni", dni: dni}, function (response) {
                    $("#respDni").html(response);
                });         
        	});   
        }); 
        
    </script>
</head>
<body>
<div class="container">
        <form action="CambiarPasswordServlet" method="post">
            <h2>Completar los datos para cambiar la contraseña</h2>
            
             <label for="user">Nombre de Usuario:</label>
            <input type="text" id="user" name="user" maxlength = "25" required>
            <label for="respUsuario" id="respUsuario"></label>
                       
            <label for="dni">DNI:</label>
            <input type="number" id="dni" name="dni" max = "99999999" required>
            <label for="respDni" id="respDni"></label>
            
            <label for="cuil">CUIL:</label>
            <input type="number" id="cuil" max = "9999999999999" name="cuil" required>
 
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" maxlength = "100" required>
            
            <label for="pass">Nueva contraseña:</label>
            <input type="password" id="pass" name="pass" maxlength = "25" required>
            
            <label for="pass2">Repetetir Contraseña:</label>
            <input type="password" id="pass2" name="pass2" maxlength = "25" required>
            <label for="respPass" id="respPass"></label>
            
            
            <input type="submit" value="Cambiar Password" id="CambiarPassword" name="CambiarPassword" onclick="return checkBTN()">
        	<a Style=margin-left:20px; href="Login.jsp">Volver</a>
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
		String msj = " ";
		if(request.getAttribute("CambioRealizado")!=null)
			msj = request.getAttribute("CambioRealizado").toString();
	%>

	<% if(!msj.equals(" ")) { %>
	     <script>
	     	alert('<%=msj %>');
	     </script>
	<% } %>
	    

</body>
</html>