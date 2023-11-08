function datos(id, nombrePelicula, descripcion, anioLanzamiento, idGenero, duracion, idEstado) {
    document.getElementById("idPelicula").value = id;
    document.getElementById("nombrePelicula").value = nombrePelicula;
    document.getElementById("descripcion").value = descripcion;
    document.getElementById("anioLanzamiento").value = anioLanzamiento;
    document.getElementById("idGenero").value = idGenero;
    document.getElementById("duracion").value = duracion;
    document.getElementById("idEstadoPelicula").value = idEstado;
}

const btnGuardar = document.getElementById("btnGuardar");

btnGuardar.addEventListener("click", function (event) {
    event.preventDefault();

    let idPelicula = document.getElementById("idPelicula").value;
    let nombrePelicula = document.getElementById("nombrePelicula").value;
    let descripcion = document.getElementById("descripcion").value;
    let anioLanzamiento = document.getElementById("anioLanzamiento").value;
    let idGenero = document.getElementById("idGenero").value;
    let duracion = document.getElementById("duracion").value;
    let idEstado = document.getElementById("idEstadoPelicula").value;

    let mensaje;
    let accion = idPelicula ? "modificar" : "agregar";

    if (accion === "modificar") {
        mensaje = "La película se modificó con éxito";
    } else if (accion === "agregar") {
        mensaje = "La película se agregó con éxito";
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/peliculasControlador", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: mensaje,
                icon: 'success'
            }).then((result) => {
                if(result.isConfirmed) {
                    location.reload(); // Recarga la página después de cerrar el mensaje
                }
            });
        } else if (this.readyState === 4) {
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
    xhr.send("accion=" + encodeURIComponent(accion) +
        "&idPelicula=" + encodeURIComponent(idPelicula) +
        "&nombrePelicula=" + encodeURIComponent(nombrePelicula) +
        "&descripcion=" + encodeURIComponent(descripcion) +
        "&anioLanzamiento=" + encodeURIComponent(anioLanzamiento) +
        "&idGenero=" + encodeURIComponent(idGenero) +
        "&duracion=" + encodeURIComponent(duracion) +
        "&idEstadoPelicula=" + encodeURIComponent(idEstado));
})

function eliminarPelicula(idPelicula) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "Esta acción no se puede revertir. ¿Deseas eliminar la película?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Si el usuario confirma, se realiza la petición AJAX para eliminar la película.
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/peliculasControlador', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function () {
                if (xhr.status === 200 && xhr.responseText.trim() !== '') {
                    Swal.fire(
                        'Eliminado!',
                        'La película ha sido eliminada.',
                        'success'
                    ).then((result) => {
                        if(result.isConfirmed) {
                            location.reload();  // Recargar la página para reflejar la eliminación.
                        }
                    });
                } else {
                    Swal.fire(
                        'Error!',
                        'Hubo un error al eliminar la película.',
                        'error'
                    );
                }
            };
            xhr.send('accion=eliminar&id=' + encodeURIComponent(idPelicula));
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelado',
                'La película está segura :)',
                'error'
            );
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {

    document.getElementById("btnBuscar").addEventListener("click", function(e) {
        e.preventDefault();
        var terminoBusqueda = document.getElementById("buscarPelicula").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/peliculasControlador?accion=buscar&buscarPelicula=" + encodeURIComponent(terminoBusqueda), true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var peliculasList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaPeliculas").innerHTML = "";

                // Populate the table with the new data
                peliculasList.forEach(function(peli) {
                    var row = '<tr>' +
                        '<td>' + peli.nombrePelicula + '</td>' +
                        '<td>' + peli.descripcion + '</td>' +
                        '<td>' + peli.anioLanzamiento + '</td>' +
                        '<td>' + peli.genero + '</td>' +
                        '<td>' + peli.duracion + '</td>' +
                        '<td>' + peli.estado + '</td>' +
                        '<td><button class="btn btn-primary" onclick="datos(' + peli.id + ',\'' + peli.nombrePelicula + '\', \'' + peli.descripcion + '\', \'' + peli.anioLanzamiento + '\', ' + peli.idGenero + ', \'' + peli.duracion + '\', ' + peli.idEstado + ')">Editar</button></td>' +
                        '<td><button class="btn btn-danger" onclick="eliminarPelicula(' + peli.id + ')">Eliminar</button></td>' +
                        '</tr>';
                    document.getElementById("tablaPeliculas").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for movies. Please try again.");
            }
        };
        xhr.send();
    });

    // Opcional: Búsqueda en tiempo real mientras escribe
    document.getElementById("buscarPelicula").addEventListener("keyup", function(e) {
        var buscarPelicula = this.value;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/peliculasControlador?accion=buscar&buscarPelicula=" + encodeURIComponent(buscarPelicula), true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var peliculasList = JSON.parse(xhr.responseText);

                // Clear the previous content
                document.getElementById("tablaPeliculas").innerHTML = "";

                // Populate the table with the new data
                peliculasList.forEach(function(peli) {
                    var row = '<tr>' +
                        '<td>' + peli.nombrePelicula + '</td>' +
                        '<td>' + peli.descripcion + '</td>' +
                        '<td>' + peli.anioLanzamiento + '</td>' +
                        '<td>' + peli.genero + '</td>' +
                        '<td>' + peli.duracion + '</td>' +
                        '<td>' + peli.estado + '</td>' +
                        '<td><button class="btn btn-primary" onclick="datos(' + peli.id + ',\'' + peli.nombrePelicula + '\', \'' + peli.descripcion + '\', \'' + peli.anioLanzamiento + '\', ' + peli.idGenero + ', \'' + peli.duracion + '\', ' + peli.idEstado + ')">Editar</button></td>' +
                        '<td><button class="btn btn-danger" onclick="eliminarPelicula(' + peli.id + ')">Eliminar</button></td>' +
                        '</tr>';
                    document.getElementById("tablaPeliculas").innerHTML += row;
                });
            } else if (xhr.readyState === 4) {
                console.log("Error: " + xhr.status);
                alert("An error occurred while searching for movies. Please try again.");
            }
        };
        xhr.send();
    });
});