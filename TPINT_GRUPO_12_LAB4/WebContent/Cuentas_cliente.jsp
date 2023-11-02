<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cuentas</title>
    <script src="JS/misCuentas.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            margin: 0;
            padding: 20px;
        }

        h2, h3 {
            color: #333;
        }

        select, ul, #infoCuenta {
            width: 300px;
            box-sizing: border-box;
        }

        select {
            padding: 10px;
            border: 1px solid #d4d4d4;
            border-radius: 5px;
            font-size: 16px;
            outline: none;
            margin-bottom: 20px;
            cursor: pointer;
        }

        #infoCuenta {
            padding: 10px;
            border: 1px solid #d4d4d4;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        ul {
            list-style-type: none;
            padding: 0;
            border: 1px solid #d4d4d4;
            border-radius: 5px;
        }

        ul li {
            padding: 10px 15px;
            border-bottom: 1px solid #d4d4d4;
        }

        ul li:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>

<h2>Bienvenido, Juan Pérez</h2> 
<h3>Tus cuentas:</h3>

<select id="listaCuentas" onchange="cargarHistorial()">
    <%
       for(int i = 0; i < 2; i++) { // Simulamos 2 cuentas
    %>
    <option value="<%= i %>"><%= "Cuenta " + (i + 1)%></option>
    <% } %>
</select>

<h3>Información de la cuenta:</h3>
<div id="infoCuenta">
 
</div>

<h3>Historial de Movimientos:</h3>
<ul id="historialMovimientos"></ul>

</body>
</html>
