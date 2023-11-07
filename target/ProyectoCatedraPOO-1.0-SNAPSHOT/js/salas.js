function datos(id, numero, sucursal) {
    document.getElementById("idSala").value = id;
    document.getElementById("numeroSala").value = numero;
    document.getElementById("sucursal").value = sucursal;
}

const btnGuardar = document.getElementById("btnGuardar");

btnGuardar.addEventListener("click", function (event) {
    event.preventDefault();

    let idSala = document.getElementById("idSala").value;
    let numeroSala = document.getElementById("numeroSala").value;
    let sucursal = document.getElementById("sucursal").value;
    console.log(numeroSala+ "y"+ sucursal)
    let mensaje;
    // Verifica si idVentas está vacío
    let accion = idSala ? "modificar" : "agregar";

    if (accion === "modificar") {
        mensaje = "Los datos se modificaron con exito"
    }

    if (accion === "agregar") {
        mensaje = "Los datos se agregar con exito"
    }
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/salasControlador", true);
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
    xhr.send("accion=" + accion + "&idSala=" + idSala + "&numeroSala=" + numeroSala + "&sucursal=" + sucursal);
})

function eliminarVenta(idSala) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede revertir. ¿Deseas eliminar la sala?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Si el usuario confirma, se realiza la petición AJAX para eliminar la venta.
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/salasControlador', true);
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
            xhr.send('accion=eliminar&id=' + idSala);
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {

    document.getElementById("btnBuscar").addEventListener("click", function(e) {
        e.preventDefault();
        var buscarSala = document.getElementById("buscarSala").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/salasControlador?accion=buscar&buscarSala=" + buscarSala, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var salasList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaSala").innerHTML = "";

                // Populate the table with the new data
                salasList.forEach(function(sala) {
                    var row = '<tr>' +
                        '<td>' + sala.numeroSala + '</td>' +
                        '<td>' + sala.Sucursal + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + sala.id + '\',\'' + sala.numeroSala + '\', \'' + sala.idSucursal + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarVenta(' + sala.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaSala").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });

    document.getElementById("buscarSala").addEventListener("keyup", function(e) {
        var buscarSala = this.value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/salasControlador?accion=buscar&buscarSala=" + buscarSala, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var salasList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaSala").innerHTML = "";

                // Populate the table with the new data
                salasList.forEach(function(sala) {
                    var row = '<tr>' +
                        '<td>' + sala.numeroSala + '</td>' +
                        '<td>' + sala.Sucursal + '</td>' +
                        '<td><a class="btn btn-primary" onclick="datos(\'' + sala.id + '\',\'' + sala.numeroSala + '\', \'' + sala.Sucursal + '\')">Editar</a></td>' +
                        '<td><a class="btn btn-danger" name="accion" value="eliminar" onclick="eliminarVenta(' + sala.id + ')">Eliminar</a></td>' +
                        '</tr>';
                    document.getElementById("tablaSala").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for sales. Please try again.");
            }
        };
        xhr.send();
    });
});