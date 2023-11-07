<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 01/11/2023
  Time: 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<br>
<h1 class="text-center">Salas</h1>
<div class="container">
    <form method="post">
        <input type="hidden" class="form-control" id="idSala" name="idSala">
        <div class="mb-3">
            <label for="numeroSala" class="form-label">Numero Sala</label>
            <input type="text" class="form-control" id="numeroSala" name="numeroSala">
        </div>
        <div class="mb-3">
            <label for="sucursal" class="form-label">Sucursal a la que pertenece</label>
            <select class="form-control" id="sucursal" name="sucursal" required>
                <c:forEach var="li" items="${listaSucursal}">
                    <option value="${li.id}">${li.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar Sala</button>
        </div>
    </form>
    <br>
    <form class="d-flex col-md-4 ms-auto">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="buscarSala">
        <button class="btn btn-outline-success" type="submit" id="btnBuscar">Search</button>
    </form>
    <br>
</div>

<!--Tabla-->
<div>
    <div class="">
        <table class="table table-striped table-hover table-dark">
            <thead class="table-dark table-striped">
            <tr>
                <th>Numero sala</th>
                <th>Sucursal</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody id="tablaSala">
            <c:forEach var="sa" items="${lista}">
                <tr>
                    <td>${sa.numeroSala}</td>
                    <td>${sa.getSucursal()}</td>
                    <td>
                        <a class="btn btn-primary" onclick="datos('${sa.id}','${sa.numeroSala}', '${sa.idSucursal}')">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarVenta(${sa.id})">Eliminar</a>
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
<!--SweetAlert Script-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<!--SweetAlert Script-->
<script src="js/salas.js"></script>
</body>

</html>
