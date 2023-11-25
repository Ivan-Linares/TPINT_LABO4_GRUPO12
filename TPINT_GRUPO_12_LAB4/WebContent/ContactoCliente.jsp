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
            $("#pais").change(function () {
                var paisId = $(this).val();
                $.post("cargarPaisesServlet", {action: "getProvincias", paisId: paisId}, function (data) {
                    $("#provincia").html(data);
                });
            });
        });    
    </script>
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
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
                <option value="X">Prefiero no decirlo</option>
            </select>
            
            <!-- <label for="lblPais">País:</label>
            <input type="text" id="pais" name="txtPais" required>  -->
            <label for="lblPais">Pais:</label>
            <select id="pais" name="selPais">
            	<option value="0">Seleccione una opcion</option>
                <%PaisNegocio pNeg = new PaisNegocioImpl();
				for (Pais p : pNeg.listar()) {
				%>
				<option value=<%=p.getCode() %>> <%=p.getName()%></option>			
				<% } %>
            </select>
           	
            <label for="lblProvincia">Provincia:</label>
            <select id="provincia" name="selProvincia" required>
            	<!-- Comentado porque se carga desde el servlet -->
            	
                <%-- <%ProvinciaNegocio prNeg = new ProvinciaNegocioImpl(); 
                for (Provincia pr : prNeg.listar()){
                %>
                <option><%=pr.getNombreProvincia()%><option>
                <%} %> --%>
            </select>
            
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
	String msj = " ";
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