function obtenerHTMLInicioSesion() {
    return `
    <div class="contenedor">
    <div class="formulario">
        <form id="login-form" action="GeneraSession" method="post">
            <h1 class="text-center text-white">PrimeCinema</h1>
            <h3 class="text-center text-white">Iniciar Sesion</h3>
            <div class="input-contenedor">
                <i class="fa-solid fa-envelope"></i>
                <input type="email" name="usuario" required>
                <label for="usuario">Email</label>
            </div>
            <div class="input-contenedor">
                <i class="fa-solid fa-lock"></i>
                <input type="password" name="password" required>
                <label for="password">Contraseña</label>
            </div>
            <button type="submit" name="operacion" value="logueo">Acceder</button>
            <div class="registrar">
                <p>No tengo Cuenta <a href="#" id="crear-cuenta-link">Crear una</a></p>
            </div>
        </form>
    </div>
</div>
    `;
}

function obtenerHTMLRegistro() {
    return `
    <div class="contenedor contenedor-registro">
         <div class="formulario">
             <form action="RegistrarPersona" method="post">
                 <h1 class="text-center text-white">PrimeCinema</h1>
                 <h3 class="text-center text-white">Crear Cuenta</h3>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-user"></i>
                     <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                     <label>Nombre</label>
                 </div>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-user"></i>
                     <input type="text" id="apellidoUsuario" name="apellidoUsuario" required>
                     <label>Apellido</label>
                 </div>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-id-card"></i>
                     <input type="text" id="duiUsuario" name="duiUsuario" required>
                     <label>DUI</label>
                 </div>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-phone"></i>
                     <input type="text" id="telefonoUsuario" name="telefonoUsuario" required>
                     <label>Telefono</label>
                 </div>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-envelope"></i>
                     <input type="email" id="correoUsuario" name="correoUsuario" required>
                     <label>Email</label>
                 </div>
                 <div class="input-contenedor">
                     <i class="fa-solid fa-lock"></i>
                     <input type="password" id="claveUsuario" name="claveUsuario" required>
                     <label>Contraseña</label>
                 </div>
                    <button type="submit" value="registrar" >Registrar</button>
                    <div class="registrar">
                        <p>Ya tienes Cuenta <a href="#" id="iniciar-sesion-link">Iniciar sesion</a></p>
                    </div>
                </form>
            </div>
        </div>
    `;
}

function mostrarFormularioInicioSesion() {
    var section = document.getElementById('formulario-section');
    section.innerHTML = obtenerHTMLInicioSesion();

    // Vuelve a vincular el evento para cambiar al formulario de registro
    document.getElementById('crear-cuenta-link').addEventListener('click', function(event){
        event.preventDefault();
        mostrarFormularioRegistro();
    });
}

function mostrarFormularioRegistro() {
    var section = document.getElementById('formulario-section');
    section.innerHTML = obtenerHTMLRegistro();

    // Vincula el evento para cambiar de vuelta al formulario de inicio de sesión
    document.getElementById('iniciar-sesion-link').addEventListener('click', function(event){
        event.preventDefault();
        mostrarFormularioInicioSesion();
    });
}

// Vincula el evento inicial para el enlace de crear cuenta
document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('crear-cuenta-link').addEventListener('click', function(event){
        event.preventDefault(); // Previene que el enlace lleve a otra página
        mostrarFormularioRegistro();
    });
});