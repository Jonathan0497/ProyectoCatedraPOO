<%--
  Created by IntelliJ IDEA.
  User: jonat
  Date: 31/10/2023
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body class="container" >
<img style="width: 70%" src="">
<div id="login">
    <h1 class="text-center text-black pt-5">Tienda</h1>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="GeneraSession" method="post">
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Usuario:</label><br>
                            <input type="text" name="usuario" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <button type="submit" name="operacion" class="btn btn-info btn-md" value="logueo">Iniciar Sesion</button>
                            <input type="hidden" name="operacion" value="logueo">
                        </div>
                        <br>
                        <div id="register-link" class="text-right">
                            <a style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#exampleModal" class="text-info">Registrese aquí</a>
                        </div>
                    </form>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Registro personas</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">X</button>
                                </div>
                                <div class="modal-body">
                                    <form id="save-form" class="form" action="RegistrarPersona" method="post">
                                        <h3 class="text-center text-info">Registro</h3>
                                        <div class="mb-3">
                                            <label for="nombreUsuario" class="form-label">Ingrese su nombre</label>
                                            <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="password2" class="form-label">Ingrese su contraseña</label>
                                            <input type="password" class="form-control" id="password2" name="password2" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="usuario2" class="form-label">Ingrese un nick de usuario</label>
                                            <input type="text" class="form-control" id="usuario2" name="usuario2">
                                        </div>
                                        <br>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <input type="submit" name="submit" class="btn btn-info btn-md" form="save-form" value="Enviar">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>