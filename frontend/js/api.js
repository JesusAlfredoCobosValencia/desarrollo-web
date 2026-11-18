const url = "http://localhost:8082/api";

function obtenerHoteles() {
    return fetch(url + "/hoteles")
        .then(respuesta => respuesta.json());
}

function buscarHoteles(ciudad) {
    return fetch(url + "/hoteles?ciudad=" + encodeURIComponent(ciudad))
        .then(respuesta => respuesta.json());
}

function obtenerHotel(id) {
    return fetch(url + "/hoteles/" + id)
        .then(respuesta => respuesta.json());
}

function obtenerHabitaciones(hotelId) {
    return fetch(url + "/habitaciones?hotelId=" + hotelId)
        .then(respuesta => respuesta.json());
}

function obtenerHabitacion(id) {
    return fetch(url + "/habitaciones/" + id)
        .then(respuesta => respuesta.json());
}

function obtenerHabitacionesDisponibles(hotelId, fechaEntrada, fechaSalida, huespedes) {
    return fetch(
        url + "/habitaciones/disponibles?hotelId=" + hotelId +
        "&fechaEntrada=" + fechaEntrada +
        "&fechaSalida=" + fechaSalida +
        "&huespedes=" + huespedes
    )
        .then(respuesta => respuesta.json());
}

function registrarUsuario(nombre, correo, password, telefono) {
    return fetch(url + "/auth/registro", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nombre: nombre,
            correo: correo,
            password: password,
            telefono: telefono
        })
    })
        .then(respuesta => respuesta.json());
}

function iniciarSesion(correo, password) {
    return fetch(url + "/auth/login", {
        method: "POST",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            correo: correo,
            password: password
        })
    })
        .then(respuesta => respuesta.json());
}

function cerrarSesion() {
    return fetch(url + "/auth/logout", {
        method: "POST",
        credentials: "include"
    })
        .then(respuesta => {
            if (respuesta.status == 204) {
                return null;
            }
            return respuesta.json();
        });
}

function obtenerPerfil() {
    return fetch(url + "/usuarios/perfil", {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function actualizarPerfil(nombre, telefono) {
    return fetch(url + "/usuarios/perfil", {
        method: "PUT",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nombre: nombre,
            telefono: telefono
        })
    })
        .then(respuesta => respuesta.json());
}

function crearReservacion(idHabitacion, fechaEntrada, fechaSalida, cantidadHuespedes) {
    return fetch(url + "/reservaciones", {
        method: "POST",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id_habitacion: idHabitacion,
            fecha_entrada: fechaEntrada,
            fecha_salida: fechaSalida,
            cantidad_huespedes: cantidadHuespedes
        })
    })
        .then(respuesta => respuesta.json());
}

function obtenerMisReservas() {
    return fetch(url + "/reservaciones/mis-reservas", {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function obtenerReservacion(id) {
    return fetch(url + "/reservaciones/" + id, {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function cancelarReservacion(id) {
    return fetch(url + "/reservaciones/" + id + "/cancelar", {
        method: "PUT",
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function obtenerPago(id) {
    return fetch(url + "/pagos/" + id, {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function confirmarPago(id, metodo) {
    return fetch(url + "/pagos/" + id + "/confirmar", {
        method: "POST",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            metodo_simulado: metodo
        })
    })
        .then(respuesta => respuesta.json());
}

function obtenerHistorial() {
    return fetch(url + "/reservaciones/historial", {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}

function obtenerCancelaciones() {
    return fetch(url + "/reservaciones/cancelaciones", {
        credentials: "include"
    })
        .then(respuesta => respuesta.json());
}
