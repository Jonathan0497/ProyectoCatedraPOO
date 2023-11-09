<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 01/11/2023
  Time: 21:54
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
    <title>Multimedia</title>
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
            </ul>
        </div>
        <a class="navbar-brand" href="usuarioControlador?accion=listar"><i class="fas fa-user"></i> Usuario</a>
    </div>
</nav>
<br>
<h1 class="text-center">Multimedia</h1>
<div class="container">
    <form method="post">
        <input type="hidden" class="form-control" id="idMultimedia" name="idMultimedia">
        <div class="col-md-12">
            <label for="idPelicula" class="form-label">Película</label>
            <select class="form-control" id="idPelicula" name="idPelicula" required>
                <c:forEach var="li" items="${listaPelicula}">
                    <option value="${li.idPelicula}">${li.nombrePelicula}</option>
                </c:forEach>
            </select>
        </div>
        <!-- Los inputs hidden deben ir fuera del select -->
        <c:forEach var="li" items="${listaPelicula}">
            <input type="hidden" id="${li.idPelicula}" value="${li.duracionPelicula}">
        </c:forEach>
        <br>
        <div class="row">
            <div class="col-md-12">
                <label for="fechaEmision" class="form-label">Hora de inicio</label>
                <input type="date" class="form-control" id="fechaEmision" name="fechaEmision">
            </div>
            <div class="col-md-6">
                <label for="horaInicio" class="form-label">Hora de inicio</label>
                <input type="time" class="form-control" id="horaInicio" name="horaInicio">
            </div>
            <!-- Adjust the column sizes so they add up to 12 -->
            <div class="col-md-3">
                <label for="horaFin" class="form-label">Hora de finalizacion</label>
                <input type="time" class="form-control" id="horaFin" name="horaFin">
            </div>
            <div class="col-md-3">
                <a class="btn btn-info" style="margin-top: 32px;" id="calcularFecha">Calcular Fecha</a>
            </div>
            <div class="col-md-6">
                <label for="idSala" class="form-label">Sala en la que se emitira</label>
                <select class="form-control" id="idSala" name="idSala" required>
                    <!-- Assuming this is server-side code for iterating through a list -->
                    <c:forEach var="li" items="${listaSalas}">
                        <option value="${li.idSala}">${li.numeroSala}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="idFormato" class="form-label">Formato de la función</label>
                <select class="form-control" id="idFormato" name="idFormato" required>
                    <!-- Assuming this is server-side code for iterating through a list -->
                    <c:forEach var="li" items="${listaFormato}">
                        <option value="${li.idFormato}">${li.nombreFormato}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar Multimedia</button>
        </div>
    </form>
    <br>
</div>
<br>

<!--Tabla-->
<div>
    <div class="">
        <table class="table table-striped table-hover table-dark">
            <thead class="table-dark table-striped">
            <tr>
                <th>Pelicula</th>
                <th>Fecha Emision</th>
                <th>Hora de Inicio - Fin</th>
                <th>Sala</th>
                <th>Formato</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody id="tablaMultimedia">
            <c:forEach var="ve" items="${lista}">
                <tr>
                    <td>${ve.nombrePelicula}</td>
                    <td>${ve.fechaEmision}</td>
                    <td>${ve.horaInicio}-${ve.horaFinal}</td>
                    <td>${ve.numeroSala}</td>
                    <td>${ve.nombreFormato}</td>
                    <td>
                        <a class="btn btn-primary"
                           onclick="datos('${ve.id}','${ve.horaInicio}', '${ve.horaFinal}', '${ve.idSala}', '${ve.idPelicula}', '${ve.idFormato}', '${ve.fechaEmision}')">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarMultimedia(${ve.id})">Eliminar</a>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="js/multimedia.js"></script>
</body>

</html>
