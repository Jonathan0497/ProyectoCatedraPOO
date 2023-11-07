<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 06/11/2023
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<br>
<h1 class="text-center">Usuarios</h1>
<br>
<div class="container">
    <form method="post" autocomplete="off" id="UsuarioForm">
        <input type="hidden" class="form-control" id="idUsuario" name="idUsuario">
        <div class="row">
            <div class="col-md-6">
                <label for="nombreUsuario" class="form-label">Nombre de usuario</label>
                <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="apellidoUsuario" class="form-label">Apellido de usuario</label>
                <input type="text" class="form-control" id="apellidoUsuario" name="apellidoUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="duiUsuario" class="form-label">DUI de usuario</label>
                <input type="text" class="form-control" id="duiUsuario" name="duiUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="telefonoUsuario" class="form-label">Telefono de usuario</label>
                <input type="text" class="form-control" id="telefonoUsuario" name="telefonoUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="correoUsuario" class="form-label">Correo de usuario</label>
                <input type="email" class="form-control" id="correoUsuario" name="correoUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="claveUsuario" class="form-label">Clave de usuario</label>
                <input type="password" class="form-control" id="claveUsuario" name="claveUsuario" required>
            </div>
            <div class="col-md-6">
                <label for="idEstado" class="form-label">Estado de usuario</label>
                <select class="form-control" id="idEstado" name="idEstado" required>
                    <c:forEach var="li" items="${listaEstado}">
                        <option value="${li.idEstado}">${li.nombreEstado}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="idTipo" class="form-label">Tipo de usuario</label>
                <select class="form-control" id="idTipo" name="idTipo" required>
                    <c:forEach var="li" items="${listaTipo}">
                        <option value="${li.idTipo}">${li.nombreTipo}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-grid gap-2 col-6 mx-auto">
                <br>
                <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar Usuario</button>
            </div>
        </div>
    </form>
    <br>
    <form class="d-flex col-md-4 ms-auto">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="buscarUsuario">
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
                <th>Nombre Completo</th>
                <th>Correo</th>
                <th>DUI</th>
                <th>Telefono</th>
                <th>Estado</th>
                <th>Cargo</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody id="tablaUsuario">
            <c:forEach var="ve" items="${lista}">
                <tr>
                    <td>${ve.nombreUsuario} ${ve.apellidoUsuario}</td>
                    <td>${ve.correoUsuario}</td>
                    <td>${ve.duiUsuario}</td>
                    <td>${ve.telefonoUsuario}</td>
                    <td>${ve.nombreEstado}</td>
                    <td>${ve.nombreTipo}</td>
                    <td>
                        <a class="btn btn-primary" onclick="datos('${ve.id}','${ve.nombreUsuario}', '${ve.apellidoUsuario}', '${ve.correoUsuario}', '${ve.duiUsuario}', '${ve.telefonoUsuario}', '${ve.idEstado}', '${ve.idTipo}')">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarUsuario(${ve.id})">Eliminar</a>
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
<script src="js/usuario.js"></script>
</body>

</html>
