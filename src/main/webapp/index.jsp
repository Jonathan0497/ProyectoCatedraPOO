<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        body {
            background-image: url('img/theater-603076_1920.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>

<section id="formulario-section">
    <div class="contenedor">
        <div class="formulario">
            <form id="login-form" action="GeneraSession" method="post">
                <h1 class="text-center text-white">PrimeCinema</h1>
                <h3 class="text-center text-white">Iniciar Sesion</h3>
                <div class="input-contenedor">
                    <i class="fa-solid fa-envelope"></i>
                    <input type="email" name="usuario" required>
                    <label>Email</label>
                </div>
                <div class="input-contenedor">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="password" required>
                    <label>Contraseña</label>
                </div>
                <button type="submit" name="operacion" value="logueo">Acceder</button>
                <div class="registrar">
                    <p>No tengo Cuenta <a href="#" id="crear-cuenta-link">Crear una</a></p>
                </div>
            </form>
        </div>
    </div>
</section>
<script src="js/index.js"></script>
</body>

</html>