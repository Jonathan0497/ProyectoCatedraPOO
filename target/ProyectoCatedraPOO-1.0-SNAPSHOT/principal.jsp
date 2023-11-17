<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 08/11/2023
  Time: 21:33
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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!--Boostrap 5-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="principal.css">
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

<!-- Carousel -->
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                aria-label="Slide 4"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img/Cine.jpg" class="d-block w-100" alt="Ping Pong 1">
            <div class="carousel-caption d-none d-md-block">
                <h5>PrimeCinema</h5>
            </div>
        </div>
        <div class="carousel-item">
            <img src="img/city-1775878_1920.jpg" class="d-block w-100" alt="Ping Pong 2">
            <div class="carousel-caption d-none d-md-block">
                <h5>PrimeCinema</h5>
            </div>
        </div>
        <div class="carousel-item">
            <img src="img/marquee-102673_1920.jpg" class="d-block w-100" alt="Ping Pong 3">
            <div class="carousel-caption d-none d-md-block">
                <h5>PrimeCinema</h5>
            </div>
        </div> <!-- Este es el div que faltaba cerrar -->
        <div class="carousel-item">
            <img src="img/chicago-333599_1920.jpg" class="d-block w-100" alt="Ping Pong 4">
            <div class="carousel-caption d-none d-md-block">
                <h5>PrimeCinema</h5>
            </div>
        </div>

    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<!-- Tarjetas -->
<br>
<h1 class="text-center">Peliculas</h1>
<br>
<div class="container">
    <br>
    <div class="row">
        <div class="col-md-6">
            <label for="idPelicula" class="form-label">Peliculas:</label>
            <select class="form-control" id="idPelicula" name="idPelicula" required>
                <!-- Assuming this is server-side code for iterating through a list -->
                <c:forEach var="li" items="${listaPelicula}">
                    <option value="${li.idPelicula}">${li.nombrePelicula}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-6">
            <label for="idSucursal" class="form-label">Sucursales:</label>
            <select class="form-control" id="idSucursal" name="idSucursal" required>
                <!-- Assuming this is server-side code for iterating through a list -->
                <c:forEach var="li" items="${listaSucursal}">
                    <option value="${li.idSucursal}">${li.nombreSucursal}</option>
                </c:forEach>
            </select>
        </div>

    </div>
    <br>
    <div class="row">
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnBuscar">Buscar</button>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary" id="btnRefrescar">Refrescar Friltros</button>
        </div>
    </div>
    <br>
    <div class="row" id="cardsContainer">
        <c:forEach var="li" items="${lista}">
            <div class="col-sm-3 mb-3 mb-sm-0">
                <div class="card h-100" style="width: 18rem;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${li.nombrePelicula}</h5>
                        <h6 class="card-subtitle mb-2 text-body-secondary">Sala ${li.numeroSala}: ${li.nombreSucursal}</h6>
                        <h6 class="card-subtitle mb-2 text-body-secondary">Fecha Emision: ${li.fechaEmision}</h6>
                        <h6 class="card-subtitle mb-2 text-body-secondary">Hora de Inicio: ${li.horaInicio}</h6>
                        <h6 class="card-subtitle mb-2 text-body-secondary">Duracion: ${li.duracionPelicula} minutos</h6>
                        <p class="card-text mt-auto">${li.descripcionPelicula}.</p>
                        <div class="mt-auto">
                            <a href="asientosControlador?accion=enlistarAsientos&idSala=${li.getIdSala()}&idMultimedia=${li.getId()}" class="card-link">Reservar asientos</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<br>

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
<script src="js/principal.js"></script>
</body>

</html>