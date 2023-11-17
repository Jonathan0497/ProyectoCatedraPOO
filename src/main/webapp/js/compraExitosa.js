const btnCalcular = document.getElementById("calcularVuelto")
const costoTotal = document.getElementById("totalPagado");
const montoPagado = document.getElementById("clientePago");
const spanVuelto = document.getElementById("vuelto");


btnCalcular.addEventListener("click", function() {
    const total = parseFloat(costoTotal.value);
    const pagado = parseFloat(montoPagado.value);

    if (pagado < total) {
        alert("El monto pagado no puede ser menor al costo total.");
        return;
    }

    const vuelto = pagado - total;
    spanVuelto.textContent = vuelto.toFixed(2);
});