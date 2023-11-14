function datos(id, nombre, telefono, direccion, idUsuario) {
    document.getElementById("idSucursal").value = id;
    document.getElementById("nombreSucursal").value = nombre;
    document.getElementById("telefonoSucursal").value = telefono;
    document.getElementById("direccionSucursal").value = direccion;
    document.getElementById("idUsuario").value = idUsuario;
}

const btnGuardar = document.getElementById("btnGuardar");

btnGuardar.addEventListener("click", function (event) {
    event.preventDefault();
    let idSucursal = document.getElementById("idSucursal").value;
    let nombreSucursal = document.getElementById("nombreSucursal").value;
    let telefonoSucursal = document.getElementById("telefonoSucursal").value;
    let direccionSucursal = document.getElementById("direccionSucursal").value;
    let idUsuario = document.getElementById("idUsuario").value;
    let mensaje;

    let accion = idSucursal ? "modificar" : "agregar";

    if (accion === "modificar") {
        mensaje = "Los datos de la sucursal se modificaron con exito"
    }

    if (accion === "agregar") {
        mensaje = "Los datos de la sucursal se agregar con exito"
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/sucursalesControlador", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: mensaje,
                icon: 'success'
            }).then((result) => {
                location.reload();  // Recarga la página después de cerrar el mensaje
            });
        } else if (this.readyState === 4) {  // Si la petición termina pero el status no es 200
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
    xhr.send("accion=" + accion + "&idSucursal=" + idSucursal + "&nombreSucursal=" + nombreSucursal + "&telefonoSucursal=" + telefonoSucursal + "&direccionSucursal=" + direccionSucursal + "&idUsuario=" + idUsuario)
})

function eliminarVenta(idSucursal) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede revertir. ¿Deseas eliminar la sucursal?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Si el usuario confirma, se realiza la petición AJAX para eliminar la venta.
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/sucursalesControlador', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function () {
                if (xhr.status === 200 && xhr.responseText.trim() !== '') {
                    Swal.fire(
                        'Eliminado!',
                        'La venta ha sido eliminada.',
                        'success'
                    ).then((result) => {
                        location.reload();  // Recargar la página para reflejar la eliminación.
                    });
                } else {
                    Swal.fire(
                        'Error!',
                        'Hubo un error al eliminar la venta.',
                        'error'
                    );
                }
            };
            xhr.send('accion=eliminar&id=' + idSucursal);
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {

    const SucursalForm = document.getElementById("SucursalForm");

    SucursalForm.addEventListener('input', function(event) {
        var target = event.target;
        switch (target.id) {
            case 'nombreSucursal':
                validarNombreSucursal(target);
                break;
            case 'telefonoSucursal':
                validarTelefonoSucursal(target);
                break;
            case 'direccionSucursal':
                validarDireccionSucursal(target);
                break;
        }
    })

    function validarNombreSucursal(input) {
        var regex = /^[a-zA-Z0-9- ]+$/;
        input.style.borderColor = regex.test(input.value) ? 'green' : 'red';
    }
    function validarTelefonoSucursal(input) {
        // Expresión regular para un número de teléfono de El Salvador con formato XXX-XXXX
        var regexTelefono = /^\d{4}-\d{4}$/;
        input.style.borderColor = regexTelefono.test(input.value) ? 'green' : 'red';
    }
    function validarDireccionSucursal(input) {
        // Expresión regular para un número de teléfono de El Salvador con formato XXX-XXXX
        var regexDireccion = /^[a-zA-Z0-9- ]{1,100}$/;
        input.style.borderColor = regexDireccion.test(input.value) ? 'green' : 'red';
    }

    document.getElementById("btnBuscar").addEventListener("click", function(e) {
        e.preventDefault();
        var buscarVenta = document.getElementById("buscarSucursal").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/sucursalesControlador?accion=buscar&buscarSucursal=" + buscarVenta, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var ventaList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaSucursal").innerHTML = "";

                // Populate the table with the new data
                ventaList.forEach(function(venta) {
                    var row = '<tr>' +
                        '<td>' + venta.fechaVenta + '</td>' +
                        '<td>' + venta.descripcion + '</td>' +
                        '<td>' + venta.linea + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + venta.id + '\',\'' + venta.fechaVenta + '\', \'' + venta.descripcion + '\', \'' + venta.lineaVenta + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarVenta(' + venta.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaSucursal").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });

    document.getElementById("buscarSucursal").addEventListener("keyup", function(e) {
        var buscarVenta = this.value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/sucursalesControlador?accion=buscar&buscarSucursal=" + buscarVenta, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var ventaList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaSucursal").innerHTML = "";

                // Populate the table with the new data
                ventaList.forEach(function(sucursal) {
                    var row = '<tr>' +
                        '<td>' + sucursal.nombre + '</td>' +
                        '<td>' + sucursal.telefono + '</td>' +
                        '<td>' + sucursal.direccion + '</td>' +
                        '<td>' + sucursal.Usuario + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + sucursal.id + '\',\'' + sucursal.nombre + '\', \'' + sucursal.telefono + '\', \'' + sucursal.direccion + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarVenta(' + sucursal.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaSucursal").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });
});