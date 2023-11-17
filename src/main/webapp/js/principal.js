document.getElementById("btnBuscar").addEventListener("click", function(e) {
    e.preventDefault();
    var buscarPelicula = document.getElementById("idPelicula").value;
    var buscarSucursal = document.getElementById("idSucursal").value;

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/principalControlador?accion=buscar&idPelicula=" + encodeURIComponent(buscarPelicula) + "&idSucursal=" + encodeURIComponent(buscarSucursal), true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var peliculaList = JSON.parse(xhr.responseText);

            // Limpia el contenido anterior
            var cardsContainer = document.getElementById("cardsContainer");
            cardsContainer.innerHTML = "";

            // Poblamos las tarjetas con la nueva data
            peliculaList.forEach(function(pelicula) {
                var card = `
                <div class="col-sm-3 mb-3 mb-sm-0">
                    <div class="card h-100" style="width: 18rem;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${pelicula.nombrePelicula}</h5>
                            <h6 class="card-subtitle mb-2 text-body-secondary">Sala ${pelicula.numeroSala}: ${pelicula.nombreSucursal}</h6>
                            <h6 class="card-subtitle mb-2 text-body-secondary">Fecha Emision: ${pelicula.fechaEmision}</h6>
                            <h6 class="card-subtitle mb-2 text-body-secondary">Hora de Inicio: ${pelicula.horaInicio}</h6>
                            <h6 class="card-subtitle mb-2 text-body-secondary">Duracion: ${pelicula.duracionPelicula} minutos</h6>
                            <p class="card-text mt-auto">${pelicula.descripcionPelicula}.</p>
                            <div class="mt-auto">
                                <a href="#" class="card-link">Card link</a>
                                <a href="#" class="card-link">Another link</a>
                            </div>
                        </div>
                    </div>
                </div>`;
                cardsContainer.innerHTML += card;
            });
        } else if (xhr.readyState === 4) {
            console.log("Error: " + xhr.status);
            alert("Ocurrió un error durante la búsqueda. Por favor, inténtelo de nuevo.");
        }
    };
    xhr.send();
});

document.getElementById("btnRefrescar").addEventListener("click", function(e) {
    e.preventDefault();
    window.location.reload();
});
