function cargarHoteles() {
    obtenerHoteles()
        .then(hoteles => {
            mostrarHoteles(hoteles);
        })
        .catch(error => {
            console.log("Error:", error);
        });
}

function buscar() {
    let ciudad = document.getElementById("ciudad").value;

    if (ciudad == "") {
        cargarHoteles();
        return;
    }

    buscarHoteles(ciudad)
        .then(hoteles => {
            mostrarHoteles(hoteles);
        })
        .catch(error => {
            console.log("Error:", error);
        });
}

function mostrarHoteles(hoteles) {
    let resultado = document.getElementById("resultado");
    resultado.innerHTML = "";

    if (hoteles.length == 0) {
        resultado.innerHTML = "<p>No se encontraron hoteles.</p>";
        return;
    }

    hoteles.forEach(hotel => {
        resultado.innerHTML += `
            <div class="hotel">
                <h3>${hotel.nombre}</h3>
                <p>Ciudad: ${hotel.ciudad}</p>
                <p>Dirección: ${hotel.direccion}</p>
                <p>Categoría: ${hotel.categoria} estrellas</p>
            </div>
        `;
    });
}

function verHabitaciones(idHotel) {
    obtenerHabitaciones(idHotel)
        .then(habitaciones => {
            console.log(habitaciones);
        })
        .catch(error => {
            console.log("Error:", error);
        });
}