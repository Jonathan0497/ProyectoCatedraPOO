function seleccionAsiento(idAsiento) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/asientosControlador", true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: mensaje,
                icon: 'success'
            }).then((result) => {
                location.reload();
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

    xhr.send("accion=" + 'agregarAsiento' + "&idAsiento=" + idAsiento );
}

