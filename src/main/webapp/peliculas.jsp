<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<br>
<h1 class="text-center">Gestión de Películas</h1>
<div class="container">
    <form method="post">
        <input type="hidden" class="form-control" id="idPelicula" name="idPelicula">
        <div class="row">
            <div class="col-md-12">
                <label for="nombrePelicula" class="form-label">Nombre de la Película</label>
                <input type="text" class="form-control" id="nombrePelicula" name="nombrePelicula" required>
            </div>
            <div class="col-md-6">
                <label for="anioLanzamiento" class="form-label">Año de Lanzamiento</label>
                <input type="text" class="form-control" id="anioLanzamiento" name="anioLanzamiento" required>
            </div>
            <div class="col-md-6">
                <label for="duracion" class="form-label">Duración</label>
                <input type="text" class="form-control" id="duracion" name="duracion" required>
            </div>
            <div class="col-md-6">
                <label for="idGenero" class="form-label">Género</label>
                <select class="form-control" id="idGenero" name="idGenero" required>
                    <c:forEach var="gen" items="${listaGeneros}">
                        <option value="${gen.idGenero}">${gen.genero}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="idEstadoPelicula" class="form-label">Estado de la Película</label>
                <select class="form-control" id="idEstadoPelicula" name="idEstadoPelicula" required>
                    <c:forEach var="est" items="${listaEstados}">
                        <option value="${est.idEstadoPelicula}">${est.estadoPelicula}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-12">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
            </div>
        </div>
        <br>
        <div class="d-grid gap-2 col-12 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar Película</button>
        </div>
    </form>
    <br>
</div>
<div class="container">
    <form class="d-flex col-md-4 ms-auto">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="buscarPelicula">
        <button class="btn btn-outline-success" type="submit" id="btnBuscar">Search</button>
    </form>
</div>
<br>

<!--Tabla de películas-->

<table class="table table-striped table-hover">
    <thead class="table-dark">
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Año Lanzamiento</th>
        <th>Género</th>
        <th>Duración</th>
        <th>Estado</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    </thead>
    <tbody id="tablaPeliculas">
    <c:forEach var="peli" items="${lista}">
        <tr>
            <td>${peli.nombrePelicula}</td>
            <td>${peli.descripcion}</td>
            <td>${peli.anioLanzamiento}</td>
            <td>${peli.genero}</td>
            <td>${peli.duracion}</td>
            <td>${peli.estado}</td>
            <td>
                <!-- Botón para activar el modo de edición -->
                <button type="button" class="btn btn-primary"
                        onclick="datos('${peli.id}', '${peli.nombrePelicula}', '${peli.descripcion}', '${peli.anioLanzamiento}', '${peli.idGenero}', '${peli.duracion}', '${peli.idEstado}')">
                    Editar
                </button>
            </td>
            <td>
                <!-- Botón para eliminar -->
                <button type="button" class="btn btn-danger" onclick="eliminarPelicula('${peli.id}')">Eliminar</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="js/peliculas.js"></script>

</body>
</html>