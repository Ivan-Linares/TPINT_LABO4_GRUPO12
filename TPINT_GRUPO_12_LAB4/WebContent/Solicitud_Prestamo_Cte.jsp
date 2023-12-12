<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidad.Cuenta"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud nuevo prestamo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<%! boolean admin = false; %>
<!-- Navbar Admin -->
<%if (admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Lio Messi´s Bank</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Index.jsp">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Prestamos
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Prestamo_Aprobar.jsp">Aprobar Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamo_Ver.jsp">Ver Prestamos</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Cuentas_Asignar.jsp">Asignar Cuentas</a></li>
            <li><a class="dropdown-item" href="Cuentas_Ver.jsp">Ver Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Administrar
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Administrar_Clientes.jsp">Admin Clientes</a></li>
            <li><a class="dropdown-item" href="Administrar_Cuentas.jsp">Admin Cuentas</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Informes.jsp">Informes</a>
        </li>
        <li class="nav-item">
		    <form action="validarLoginServlet" method="get">
		        <input type="submit" value="Cerrar Sesión">
		    </form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%} %>
<!-- Navbar Cliente -->
<%if (!admin){ %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Lio Messi´s Bank</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Index.jsp">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Prestamos  
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Solicitud_Prestamo_Cte.jsp" >Pedir Prestamos</a></li>
            <li><a class="dropdown-item" href="Prestamos_Principal.jsp">Ver Prestamos</a></li>
            <li><a class="dropdown-item" href="Detalle_Prestamos_Cte.jsp">Pagar Cuotas</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="CuentasCliente.jsp" >Ver Cuentas</a></li>
            <li><a class="dropdown-item" href="#">Administrar</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Transferencias.jsp">Transferir</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Datos_Personales_cte.jsp">Datos Personales</a>
        </li>
        <li class="nav-item">
		    <form action="validarLoginServlet" method="get">
		        <input type="submit" value="Cerrar Sesión">
		    </form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<%} %>
<div class="alert">
    <h3>Solicitar Nuevo Prestamo:</h3>
    <br />
    <h4>Utilice esta sección para nuevas solicitudes de préstamos a acreditar en su cuenta personal. Tenga en cuenta que las solicitudes están sujetas a aprobación, dicho proceso puede demorar hasta 48 horas hábiles.</h4>
    <br />
</div>
<div>
    <form action="SolicitarPrestamoClienteServlet" method="post">
        <div class="alert border-dark">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <table>
                            <tr>
                                <th>Monto Solicitado:</th>
                                <th><input type="number" name="InputMontoPrueba" value="500000" min="500000" max="1000000" step="10000"></th>
                            </tr>
                            <tr>
                                <th>Cantidad de cuotas:</th>
                                <th class="row-cols-xxl-1">
                                    <select name="Cuotas" id="Cuota">
                                        <option value="6" selected>6</option>
                                        <option value="12">12</option>
                                        <option value="24">24</option>
                                    </select>
                                </th>
                            </tr>
                        </table> <br>
                        <input type="submit" class="btn btn-primary" name="btnSimular" value="Simular préstamo">
                    </div>
                    <div class="col">
                        <%
                            double montoPrueba = 0;
                            if (request.getAttribute("MontoSimulacion") != null) {
                                montoPrueba = (double) request.getAttribute("MontoSimulacion");
                            }
                            double montoCuotaPrueba = 0;
                            if (request.getAttribute("CuotaSimulacion") != null) {
                            	montoCuotaPrueba = (double) request.getAttribute("CuotaSimulacion");
                            }
                        %>
                        <table>
                            <tr>
                                <th>Monto a Pagar:</th>
                                <th><input type="number" name="MontoPrueba" value="<%=montoPrueba%>" disabled></th>
                            </tr>
                             <tr>
                                <th>Monto por Cuota:</th>
                                <th><input type="number" name="MontoPrueba" value="<%=montoCuotaPrueba%>" disabled></th>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <%
        boolean PrestamoSolicitado = false; 
        if (request.getAttribute("PrestamoSolicitado") != null) {
        	PrestamoSolicitado = (boolean) request.getAttribute("PrestamoSolicitado");
        }
        
        if(!PrestamoSolicitado){
        %>
        
        <div class="alert border-dark">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <table>
                            <tr>
                                <%
                                    String DNI = "";
                                    if (request.getAttribute("DNI") != null) {
                                        DNI = (String) request.getAttribute("DNI");
                                    }
                                %>
                                <th>DNI Cliente: </th>
                                <th><input name="dniCliente" value="<%=DNI%>" disabled></th>
                            </tr>
                            <tr>
                                <th>Nro. Cuenta Destino: </th>
                                <th class="row-cols-xxl-1">
                                    <select name="CuentaDest">
                                        <%
                                            ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
                                            for (Cuenta C : listaCuentas) {
                                        %>
                                        <option value="<%=C.getNumero()%>"><%=C.getNumero()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </th>
                            </tr>
                            <tr>
                                <th>Monto a Solicitar: </th>
                                <th><input type="number" name="MontoFinal" value="500000" min="500000" max="1000000" step="10000"></th>
                            </tr>
                            <tr>
                                <th>Cantidad de cuotas: </th>
                                <th class="row-cols-xxl-1">
                                    <select name="CuotasFinal" id="Cuota">
                                        <option value="6" selected>6</option>
                                        <option value="12">12</option>
                                        <option value="24">24</option>
                                    </select>
                                </th>
                            </tr>
                        </table>
                        <br />
                        <input type="submit" class="btn btn-success" name="btnConfirmar" value="Confirmar préstamo">
                        <br />
                    </div>
                    <%} else{
			        String msj = "";
			        if (request.getAttribute("msj") != null) {
			        	msj = (String) request.getAttribute("msj");
			        }
                    %>
                    <div class="col">
                    <h3> <%= msj %> </h3>
                    <input type="submit" class="btn btn-success" name="btnNuevoPrest" value="Pedir préstamo"> 
                    </div>
                    <%} %>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>