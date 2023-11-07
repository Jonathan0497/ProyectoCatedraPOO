<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 31/10/2023
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    HttpSession session_actual = request.getSession(false);
    String usuario = (String) session_actual.getAttribute("USER");
    String nombres = (String) session_actual.getAttribute("NAME");
    int ID = (Integer) session_actual.getAttribute("ID");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sucursales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<br>
<h1 class="text-center">Sucursal</h1>
<div class="container">
    <form method="post" id="SucursalForm">
        <input type="hidden" class="form-control" id="idSucursal" name="idSucursal">
        <div class="mb-3">
            <label for="nombreSucursal" class="form-label">Nombre del sucursal</label>
            <input type="text" class="form-control" id="nombreSucursal" name="nombreSucursal">
        </div>
        <div class="mb-3">
            <label for="telefonoSucursal" class="form-label">Teléfono de la sucursal</label>
            <input type="text" class="form-control" id="telefonoSucursal" name="telefonoSucursal">
        </div>
        <div class="mb-3">
            <label for="direccionSucursal" class="form-label">Dirección de la sucursal</label>
            <input type="text" class="form-control" id="direccionSucursal" name="direccionSucursal">
        </div>
        <div class="mb-3">
            <label for="idUsuario" class="form-label">Gerente</label>
            <select class="form-control" id="idUsuario" name="idUsuario" required>
                <c:forEach var="li" items="${listaUsuario}">
                    <option value="${li.idUsuario}">${li.getUsuario()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar Sucursal</button>
        </div>
    </form>
    <br>
    <form class="d-flex col-md-4 ms-auto">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="buscarSucursal">
        <button class="btn btn-outline-success" type="submit" id="btnBuscar">Search</button>
    </form>
</div>
<br>

<!--Tabla-->
<div>
    <div class="">
        <table class="table table-striped table-hover table-dark">
            <thead class="table-dark table-striped">
            <tr>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Direccion</th>
                <th>Gerente</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody id="tablaSucursal">
            <c:forEach var="su" items="${lista}">
                <tr>
                    <td>${su.nombre}</td>
                    <td>${su.telefono}</td>
                    <td>${su.direccion}</td>
                    <td>${su.getUsuario()}</td>
                    <td>
                        <a class="btn btn-primary" onclick="datos('${su.id}','${su.nombre}', '${su.telefono}', '${su.direccion}', '${su.idUsuario}')">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" onclick="eliminarVenta(${su.id})">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!--Boostrap Script-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="js/main.js"></script>
</body>

</html>
