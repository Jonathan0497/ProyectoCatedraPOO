function datos(id, horaInicio, horaFin, idSala, idPelicula, idFormato, fechaEmision) {
    document.getElementById("idMultimedia").value = id;
    document.getElementById("idPelicula").value = idPelicula;
    document.getElementById("idFormato").value = idFormato;
    document.getElementById("idSala").value = idSala;
    document.getElementById("horaInicio").value = horaInicio;
    document.getElementById("horaFin").value = horaFin;
    document.getElementById("fechaEmision").value = fechaEmision;
}

const btnGuardar = document.getElementById("btnGuardar");

btnGuardar.addEventListener("click", function (event) {
    event.preventDefault();

    let idMultimedia = document.getElementById("idMultimedia").value
    let fechaEmision = document.getElementById("fechaEmision").value
    let idPelicula = document.getElementById("idPelicula").value
    let idFormato = document.getElementById("idFormato").value
    let idSala = document.getElementById("idSala").value
    let horaInicio = document.getElementById("horaInicio").value
    let horaFin = document.getElementById("horaFin").value
    let mensaje;
    // Verifica si idVentas está vacío
    let accion = idMultimedia ? "modificar" : "agregar";

    if (accion === "modificar") {
        mensaje = "Los datos se modificaron con exito"
    }

    if (accion === "agregar") {
        mensaje = "Los datos se agregar con exito"
    }
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/multimediaControlador", true);
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
    xhr.send("accion=" + encodeURIComponent(accion) +
        "&idMultimedia=" + encodeURIComponent(idMultimedia) +  // Asumiendo que este es el nombre correcto del parámetro
        "&idPelicula=" + encodeURIComponent(idPelicula) +
        "&fechaEmision=" + encodeURIComponent(fechaEmision) +
        "&horaInicio=" + encodeURIComponent(horaInicio) +
        "&horaFin=" + encodeURIComponent(horaFin) +
        "&idSala=" + encodeURIComponent(idSala) +
        "&idFormato=" + encodeURIComponent(idFormato));
})

function eliminarMultimedia(idMultimedia) {
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
            xhr.open('POST', '/multimediaControlador', true);
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
            xhr.send('accion=eliminar&id=' + idMultimedia);
        }
    });
}

document.getElementById("calcularFecha").addEventListener("click", function() {
    event.preventDefault()
    // Obtener la hora de inicio y la película seleccionada
    var horaInicio = document.getElementById("horaInicio").value;
    var peliculaSeleccionada = document.getElementById("idPelicula").value;

    // Obtener la duración de la película seleccionada a partir del input oculto correspondiente
    var duracionPelicula = document.getElementById(peliculaSeleccionada).value;

    // Calcular la hora de finalización si la hora de inicio y la duración están disponibles
    if (horaInicio && duracionPelicula) {
        var inicio = moment(horaInicio, "HH:mm");
        var duracion = moment.duration(parseInt(duracionPelicula), 'minutes');
        var fin = inicio.add(duracion);

        // Formatear la hora de finalización al formato deseado y establecer el valor del input correspondiente
        document.getElementById("horaFin").value = fin.format("HH:mm");
    } else {
        // Mostrar un mensaje de error si falta alguna información
        alert("Por favor, asegúrate de haber seleccionado una película y de haber ingresado una hora de inicio.");
    }
});

// No olvides incluir la biblioteca de Moment.js para facilitar el manejo de fechas y horas
// Puedes incluirla en tu HTML con la siguiente etiqueta script:
// <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
