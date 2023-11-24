<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar cuenta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<form action="" method="post" style=margin-top:15px;margin-left:10px;>
	<div class="row">
	  <div class="col">
	    <label for="nombre">DNI:</label>
        <input type="text" id="Dni" placeholder="Dni" class="form-control"><br>
        
        <label for="nombre">CUIL:</label>
        <input type="text" id="Cuil" placeholder="Cuil" class="form-control"><br>
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" placeholder="Nombre" class="form-control"><br>
        
        <label for="nombre">Apellido:</label>
        <input type="text" id="Apellido" placeholder="Apellido" class="form-control" ><br>
        
        <label for="nombre">Sexo:</label>
        <input type="text" id="Sexo" placeholder="Sexo" class="form-control" ><br>
        
        <label for="nombre">Email:</label>
        <input type="text" id="Email" placeholder="Email" class="form-control" ><br>
	  </div>
	  <div class="col">
 		<label for="nombre">Pais:</label>
        <input type="text" id="Pais" placeholder="Pais" class="form-control" ><br>
        
        <label for="nombre">Provincia:</label>
        <input type="text" id="Provincia" placeholder="Provincia" class="form-control" ><br>
        
        <label for="nombre">Localidad:</label>
        <input type="text" id="Localidad" placeholder="Localidad" class="form-control" ><br>
        
        <label for="nombre">Direccion:</label>
        <input type="text" id="Direccion" placeholder="Direccion" class="form-control" ><br>
       
        <label for="nombre">Fecha Nacimiento:</label>
        <input type="date" id="FechaNac" placeholder="" class="form-control" ><br>
        
        <label for="nombre">Telefono:</label>
        <input type="text" id="Telefono" placeholder="Telefono" class="form-control" ><br>
	  </div>
	</div>
	<input type="submit" name="enviar" value="Envia solicitud">
	<a Style=margin-left:20px; href="Login.jsp">Volver</a>
</form>
</body>
</html>