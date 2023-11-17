<%--
  Created by IntelliJ IDEA.
  User: jeffersonsolorzano
  Date: 15/11/23
  Time: 02:58
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
    <title>Compra exitosa</title>
  <link rel="stylesheet"  href="./css/compraExitosa.css">
</head>
<body>

<div class="container">
  <h1>Compra Exitosa</h1>
  <p>¡Gracias por tu compra! Tu pedido ha sido procesado con éxito.</p>
  <p>Total: <strong>${totalPagado}</strong></p>
  <a href="principalControlador?accion=listar" class="btn">Volver a la Tienda</a>

</div>

<script src="js/compraExitosa.js"></script>
</body>
</html>
