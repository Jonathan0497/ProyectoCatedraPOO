<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 01/11/2023
  Time: 2:29
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
    <title>Salas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<!--Nav-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="principalControlador?accion=listar"><i class="fa-solid fa-clapperboard"></i> PrimeCinema</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="peliculasControlador?accion=listar"><i class="fa-solid fa-ticket"></i>
                        Peliculas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="sucursalesControlador?accion=listar"><i class="fas fa-building"></i>
                        Sucursales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="salasControlador?accion=listar"><i class="fas fa-door-open"></i>
                        Salas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="multimediaControlador?accion=listar"><i
                            class="fas fa-photo-video"></i> Multimedia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="usuarioControlador?accion=listar"><i class="fas fa-user"></i> Usuario</a>
                </li>
            </ul>
        </div>
        <a class="navbar-brand" href="GeneraSession?operacion=salir"><i class="fa-solid fa-right-from-bracket"></i> Cerrar Sesion</a>
    </div>
</nav>
<br>
<h1 class="text-center">Salas</h1>
<div class="container">
    <form method="post" id="SalasForm">
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
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>Numero sala</th>
                <th>Sucursal</th>
                <th>Entradas Vendidas</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody id="tablaSala">
            <c:forEach var="sa" items="${lista}">
                <tr>
                    <td>${sa.numeroSala}</td>
                    <td>${sa.getSucursal()}</td>
                    <td>${sa.getEntradasVendidas()}</td>
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

<footer class="bg-dark text-white text-center py-4">
    <div class="container">
        <div class="row mb-3">
            <div class="col-12">
                <p class="text-uppercase fw-bold mb-0">©Proyecto de Cátedra - 2023</p>
            </div>
        </div>
    </div>
</footer>

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
