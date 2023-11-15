<%--
  Created by IntelliJ IDEA.
  User: jeffersonsolorzano
  Date: 14/11/23
  Time: 22:25
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
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet"  href="./css/butacasCine.css">
    <title>Seleccion de asientos de pelicula</title>
</head>

<body>

<div class="contenedor-seleccion">

    <div class="dropdown">
        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
            Tipos de precios
        </a>

        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <c:forEach var="precio" items="${listaTiposPrecios}">
                <li>
                    <a class="dropdown-item" href="asientosControlador?accion=seleccionarPrecio&idPrecio=${precio.getIdPrecio()}">${precio.getPrecio()} - ${precio.getNombreEdad()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <a class="btn btn-primary" href="asientosControlador?accion=comprarAsientos">Comprar</a>

</div>

<div class="cine-container">
    <c:forEach var="asiento" items="${listaAsientos}">
        <div class="asiento">
            <p class="numero">${asiento.getNumeroAsiento()}</p>

            <c:choose>
                <c:when test="${asiento.getEstado() == 'ocupado'}">
                    <p class="estado ocupado">Ocupado</p>
                </c:when>
                <c:when test="${asiento.getEstaSeleccionado()}">
                    <a class="btn eliminar" href="asientosControlador?accion=eliminarSeleccion&idAsiento=${asiento.getIdAsiento()}">Eliminar</a>
                </c:when>
                <c:otherwise>
                    <a class="btn agregar" href="asientosControlador?accion=agregarAsiento&idAsiento=${asiento.getIdAsiento()}">Agregar</a>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</div>

<!--Boostrap Script-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="js/asientos.js"></script>
</body>
</html>
