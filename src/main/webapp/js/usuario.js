function datos(id, nombreUsuario, apellidoUsuario, correoUsuario, duiUsuario, telefonoUsuario, idEstado, idTipo) {
    document.getElementById("idUsuario").value = id;
    document.getElementById("nombreUsuario").value = nombreUsuario;
    document.getElementById("apellidoUsuario").value = apellidoUsuario;
    document.getElementById("correoUsuario").value = correoUsuario;
    document.getElementById("duiUsuario").value = duiUsuario;
    document.getElementById("telefonoUsuario").value = telefonoUsuario;
    document.getElementById("idEstado").value = idEstado;
    document.getElementById("idTipo").value = idTipo;
    const clave = document.getElementById("claveUsuario")
    clave.value = "";
    clave.disabled = true;
}
const btnGuardar = document.getElementById("btnGuardar");

btnGuardar.addEventListener("click", function (event) {
    event.preventDefault();

    let idUsuario = document.getElementById("idUsuario").value;
    let nombreUsuario = document.getElementById("nombreUsuario").value;
    let apellidoUsuario = document.getElementById("apellidoUsuario").value;
    let correoUsuario = document.getElementById("correoUsuario").value;
    let duiUsuario = document.getElementById("duiUsuario").value;
    let telefonoUsuario = document.getElementById("telefonoUsuario").value;
    let claveUsuario = document.getElementById("claveUsuario").value;
    let idEstado = document.getElementById("idEstado").value;
    let idTipo = document.getElementById("idTipo").value;
    let mensaje;

    // Determina si se está agregando o modificando un usuario
    let accion = idUsuario ? "modificar" : "agregar";

    if (accion === "modificar") {
        mensaje = "Los datos se modificaron con éxito";
    }

    if (accion === "agregar") {
        mensaje = "Los datos se agregaron con éxito";
    }

    // Configura la solicitud AJAX
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/usuarioControlador", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                Swal.fire({
                    title: '¡Éxito!',
                    text: mensaje,
                    icon: 'success'
                }).then((result) => {
                    location.reload();  // Recarga la página después de cerrar el mensaje
                });
            } else {
                Swal.fire({
                    title: 'Error',
                    text: 'Hubo un error al procesar la solicitud.',
                    icon: 'error'
                });
            }
        }
    };
    xhr.onerror = function () {
        Swal.fire({
            title: 'Error',
            text: 'Hubo un error al enviar la petición.',
            icon: 'error'
        });
    };

    // Envía los datos al servidor
    xhr.send("accion=" + encodeURIComponent(accion) +
        "&idUsuario=" + encodeURIComponent(idUsuario) +
        "&nombreUsuario=" + encodeURIComponent(nombreUsuario) +
        "&apellidoUsuario=" + encodeURIComponent(apellidoUsuario) +
        "&claveUsuario=" + encodeURIComponent(claveUsuario) +
        "&correoUsuario=" + encodeURIComponent(correoUsuario) +
        "&duiUsuario=" + encodeURIComponent(duiUsuario) +
        "&telefonoUsuario=" + encodeURIComponent(telefonoUsuario) +
        "&idEstado=" + encodeURIComponent(idEstado) +
        "&idTipo=" + encodeURIComponent(idTipo));
});

// Supongo que este evento se dispara al hacer clic en un botón "Eliminar" para un usuario
function eliminarUsuario(idUsuario) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará el usuario permanentemente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Si el usuario confirma la eliminación, se realiza la solicitud AJAX para eliminar el usuario
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/eliminarUsuario", true); // Ajusta la URL según corresponda
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    Swal.fire({
                        title: 'Eliminado',
                        text: 'El usuario ha sido eliminado.',
                        icon: 'success'
                    }).then((result) => {
                        location.reload(); // Recarga la página después de cerrar el mensaje
                    });
                } else if (this.readyState === 4) { // Si la petición termina pero el status no es 200
                    Swal.fire({
                        title: 'Error',
                        text: 'Hubo un error al procesar la solicitud.',
                        icon: 'error'
                    });
                }
            };
            xhr.onerror = function () {
                Swal.fire({
                    title: 'Error',
                    text: 'Hubo un error al enviar la petición.',
                    icon: 'error'
                });
            };
            xhr.send("idUsuario=" + encodeURIComponent(idUsuario));
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const UsuarioForm = document.getElementById('UsuarioForm');

    UsuarioForm.addEventListener('input', function(event) {
        var target = event.target;
        switch (target.id) {
            case 'nombreUsuario':
                validarNombreUsuario(target);
                break;
            case 'apellidoUsuario':
                validarApellidoUsuario(target);
                break;
            case 'correoUsuario':
                validarEmailUsuario(target);
                break;
            case 'duiUsuario':
                validarDuiUsuario(target);
                break;
            case 'telefonoUsuario':
                validarTelefonoUsuario(target);
                break;
            case 'claveUsuario':
                validarClaveUsuario(target);
                break;
        }
    })

    function validarNombreUsuario(input) {
        var regex = /^[a-zA-Z\s]+$/;
        input.style.borderColor = regex.test(input.value) ? 'green' : 'red';
    }
    function validarApellidoUsuario(input) {
        var regex = /^[a-zA-Z\s]+$/;
        input.style.borderColor = regex.test(input.value) ? 'green' : 'red';
    }
    function validarEmailUsuario(input) {
        var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        input.style.borderColor = regex.test(input.value) ? 'green' : 'red';
    }
    function validarDuiUsuario(input) {
        var regexDUI = /^\d{8}-\d$/;
        input.style.borderColor = regexDUI.test(input.value) ? 'green' : 'red';
    }
    function validarTelefonoUsuario(input) {
        // Expresión regular para un número de teléfono de El Salvador con formato XXX-XXXX
        var regexTelefono = /^\d{4}-\d{4}$/;
        input.style.borderColor = regexTelefono.test(input.value) ? 'green' : 'red';
    }
    function validarClaveUsuario(input) {
        // Expresión regular para un número de teléfono de El Salvador con formato XXX-XXXX
        var regexClave = /^[^'";]+$/;
        input.style.borderColor = regexClave.test(input.value) ? 'green' : 'red';
    }

    document.getElementById("btnBuscar").addEventListener("click", function(e) {
        e.preventDefault();
        var buscarVenta = document.getElementById("buscarUsuario").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/usuarioControlador?accion=buscar&buscarUsuario=" + buscarVenta, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var usuarioList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaUsuario").innerHTML = "";

                // Populate the table with the new data
                usuarioList.forEach(function(usu) {
                    var row = '<tr>' +
                        '<td>' + usu.nombreUsuario + ' ' + usu.apellidoUsuario + '</td>' +
                        '<td>' + usu.correoUsuario + '</td>' +
                        '<td>' + usu.duiUsuario + '</td>' +
                        '<td>' + usu.telefonoUsuario + '</td>' +
                        '<td>' + usu.nombreEstado + '</td>' +
                        '<td>' + usu.nombreTipo + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + usu.id + '\'\'' + usu.nombreUsuario + '\',\'' + usu.apellidoUsuario + '\', \'' + usu.correoUsuario + '\', \'' + usu.duiUsuario + '\', \'' + usu.telefonoUsuario + '\', \'' + usu.idEstado + '\', \'' + usu.idTipo + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarUsuario(' + usu.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaUsuario").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });

    document.getElementById("buscarUsuario").addEventListener("keyup", function(e) {
        var buscarVenta = this.value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/usuarioControlador?accion=buscar&buscarUsuario=" + buscarVenta, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var usuarioList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaUsuario").innerHTML = "";

                // Populate the table with the new data
                usuarioList.forEach(function(usu) {
                    var row = '<tr>' +
                        '<td>' + usu.nombreUsuario + ' ' + usu.apellidoUsuario + '</td>' +
                        '<td>' + usu.correoUsuario + '</td>' +
                        '<td>' + usu.duiUsuario + '</td>' +
                        '<td>' + usu.telefonoUsuario + '</td>' +
                        '<td>' + usu.nombreEstado + '</td>' +
                        '<td>' + usu.nombreTipo + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + usu.id + '\'\'' + usu.nombreUsuario + '\',\'' + usu.apellidoUsuario + '\', \'' + usu.correoUsuario + '\', \'' + usu.duiUsuario + '\', \'' + usu.telefonoUsuario + '\', \'' + usu.idEstado + '\', \'' + usu.idTipo + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarUsuario(' + usu.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaUsuario").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });
});
