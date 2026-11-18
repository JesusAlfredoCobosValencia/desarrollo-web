let temporizadorPago;

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
                <button onclick="verHabitaciones(${hotel.idHotel})">
                    Ver habitaciones
                </button>
                <div id="habitaciones-${hotel.idHotel}"></div>
            </div>
        `;
    });
}

function verHabitaciones(idHotel) {
    let resultado = document.getElementById("habitaciones-" + idHotel);
    resultado.innerHTML = "<p>Cargando habitaciones...</p>";
    obtenerHabitaciones(idHotel)
        .then(habitaciones => {
            resultado.innerHTML = "<h4>Habitaciones del hotel</h4>";
            if (habitaciones.length == 0) {
                resultado.innerHTML += "<p>No hay habitaciones registradas.</p>";
            }
            habitaciones.forEach(habitacion => {
                resultado.innerHTML += `
                    <div class="habitacion">
                        <h4>Habitación ${habitacion.numero}</h4>
                        <p>Tipo: ${habitacion.tipo}</p>
                        <p>Capacidad: ${habitacion.capacidad} personas</p>
                        <p>Precio por noche: $${habitacion.precioNoche}</p>
                    </div>
                `;
            });
            resultado.innerHTML += `
                <h4>Consultar disponibilidad</h4>
                <p>Fecha de entrada:</p>
                <input type="date" id="entrada-${idHotel}">
                <p>Fecha de salida:</p>
                <input type="date" id="salida-${idHotel}">
                <p>Número de huéspedes:</p>
                <input type="number" id="huespedes-${idHotel}" value="2" min="1">
                <button onclick="buscarDisponibilidad(${idHotel})">
                    Buscar habitaciones disponibles
                </button>
                <div id="disponibles-${idHotel}"></div>
            `;
        })
        .catch(error => {
            console.log("Error:", error);
            resultado.innerHTML = "<p>Error al consultar habitaciones.</p>";
        });
}

function buscarDisponibilidad(idHotel) {
    let entrada = document.getElementById("entrada-" + idHotel).value;
    let salida = document.getElementById("salida-" + idHotel).value;
    let huespedes = document.getElementById("huespedes-" + idHotel).value;
    if (entrada == "" || salida == "" || huespedes == "") {
        alert("Completa todos los campos.");
        return;
    }
    if (salida <= entrada || huespedes <= 0) {
        alert("Revisa las fechas y el número de huéspedes.");
        return;
    }
    let resultado = document.getElementById("disponibles-" + idHotel);
    resultado.innerHTML = "<p>Consultando disponibilidad...</p>";
    obtenerHabitacionesDisponibles(idHotel, entrada, salida, huespedes)
        .then(habitaciones => {
            resultado.innerHTML = "<h4>Habitaciones disponibles</h4>";
            if (habitaciones.length == 0) {
                resultado.innerHTML += "<p>No hay habitaciones disponibles para esas fechas.</p>";
                return;
            }
            habitaciones.forEach(habitacion => {
                resultado.innerHTML += `
                    <div class="habitacion">
                        <h4>Habitación ${habitacion.numero}</h4>
                        <p>Tipo: ${habitacion.tipo}</p>
                        <p>Capacidad: ${habitacion.capacidad} personas</p>
                        <p>Precio por noche: $${habitacion.precioNoche}</p>
                        <button type="button" onclick="reservarHabitacion(${habitacion.idHabitacion}, '${entrada}', '${salida}', ${parseInt(huespedes)})">
                            Reservar esta habitación
                        </button>
                    </div>
                `;
            });
        })
        .catch(error => {
            console.log("Error:", error);
            resultado.innerHTML = "<p>Error al consultar disponibilidad.</p>";
        });
}

function reservarHabitacion(idHabitacion, entrada, salida, huespedes) {
    let mensaje = document.getElementById("mensajeUsuario");
    mensaje.textContent = "Procesando reservación...";
    crearReservacion(idHabitacion, entrada, salida, huespedes)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            document.getElementById("idPagoActual").value = respuesta.id_pago;
            document.getElementById("pagoFolio").textContent =
                "Folio: " + respuesta.folio;
            document.getElementById("pagoMonto").textContent =
                "Total a pagar: $" + respuesta.total;
            document.getElementById("seccionPago").style.display = "block";
            document.getElementById("mensajePago").textContent =
                "Tu reservación está pendiente de pago.";
            mensaje.textContent = "Reservación creada correctamente.";
            iniciarContador(respuesta.fecha_limite_pago);
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al crear la reservación.";
        });
}

function iniciarContador(fechaLimite) {
    if (temporizadorPago) {
        clearInterval(temporizadorPago);
    }
    let fechaFinal = new Date(fechaLimite).getTime();
    function actualizarContador() {
        let ahora = new Date().getTime();
        let diferencia = fechaFinal - ahora;
        if (diferencia <= 0) {
            clearInterval(temporizadorPago);
            document.getElementById("contadorPago").textContent = "00:00";
            document.getElementById("mensajePago").textContent =
                "El tiempo para realizar el pago terminó.";
            return;
        }
        let minutos = Math.floor(diferencia / 60000);
        let segundos = Math.floor((diferencia % 60000) / 1000);
        document.getElementById("contadorPago").textContent =
            minutos + ":" + (segundos < 10 ? "0" : "") + segundos;
    }
    actualizarContador();
    temporizadorPago = setInterval(actualizarContador, 1000);
}

function consultarPagoActual() {
    let idPago = document.getElementById("idPagoActual").value;
    let mensaje = document.getElementById("mensajePago");

    if (idPago == "") {
        mensaje.textContent = "No hay un pago para consultar.";
        return;
    }

    obtenerPago(idPago)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje +
                    (respuesta.estado ? ". Estado: " + respuesta.estado : "");
                return;
            }

            document.getElementById("pagoFolio").textContent =
                "Folio: " + respuesta.folio_pago;

            document.getElementById("pagoMonto").textContent =
                "Monto: $" + respuesta.monto;

            mensaje.textContent = "Estado del pago: " + respuesta.estado;

            if (respuesta.estado == "APROBADO" ||
                respuesta.estado == "EXPIRADO") {
                clearInterval(temporizadorPago);
            }
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar el pago.";
        });
}

function pagarReservacion() {
    let idPago = document.getElementById("idPagoActual").value;
    let metodo = document.getElementById("metodoPago").value;
    let mensaje = document.getElementById("mensajePago");

    if (idPago == "") {
        mensaje.textContent = "Primero debes realizar una reservación.";
        return;
    }

    mensaje.textContent = "Confirmando pago...";

    confirmarPago(idPago, metodo)
        .then(respuesta => {
            if (respuesta.estado == "APROBADO") {
                clearInterval(temporizadorPago);

                document.getElementById("contadorPago").textContent =
                    "Pago confirmado";

                mensaje.textContent = respuesta.mensaje;

                mostrarConfirmacion(respuesta.id_reservacion);
            } else {
                if (respuesta.estado == "EXPIRADO") {
                    clearInterval(temporizadorPago);
                }

                mensaje.textContent = respuesta.mensaje ||
                    "No fue posible confirmar el pago.";
            }
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al confirmar el pago.";
        });
}

function mostrarConfirmacion(idReservacion) {
    let mensaje = document.getElementById("mensajePago");

    obtenerReservacion(idReservacion)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }

            document.getElementById("confirmacionFolio").textContent =
                "Folio: " + respuesta.folio;

            document.getElementById("confirmacionHotel").textContent =
                "Hotel: " + respuesta.nombre_hotel;

            document.getElementById("confirmacionCiudad").textContent =
                "Ciudad: " + respuesta.ciudad;

            document.getElementById("confirmacionHabitacion").textContent =
                "Habitación: " + respuesta.numero_habitacion;

            document.getElementById("confirmacionTipo").textContent =
                "Tipo: " + respuesta.tipo_habitacion;

            document.getElementById("confirmacionEntrada").textContent =
                "Fecha de entrada: " + respuesta.fecha_entrada;

            document.getElementById("confirmacionSalida").textContent =
                "Fecha de salida: " + respuesta.fecha_salida;

            document.getElementById("confirmacionHuespedes").textContent =
                "Huéspedes: " + respuesta.cantidad_huespedes;

            document.getElementById("confirmacionTotal").textContent =
                "Total: $" + respuesta.total;

            document.getElementById("confirmacionEstado").textContent =
                "Estado: " + respuesta.estado;

            document.getElementById("seccionPago").style.display = "none";

            document.getElementById("seccionConfirmacion").style.display =
                "block";

            document.getElementById("mensajeUsuario").textContent =
                "Reservación confirmada correctamente.";
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent =
                "El pago fue aprobado, pero no se pudo consultar la reservación.";
        });
}

function registrar() {
    let nombre = document.getElementById("regNombre").value;
    let correo = document.getElementById("regCorreo").value;
    let password = document.getElementById("regPassword").value;
    let telefono = document.getElementById("regTelefono").value;
    let mensaje = document.getElementById("mensajeUsuario");
    if (nombre == "" || correo == "" || password == "") {
        mensaje.textContent = "Completa los campos obligatorios.";
        return;
    }
    if (password.length < 8) {
        mensaje.textContent = "La contraseña debe tener al menos 8 caracteres.";
        return;
    }
    registrarUsuario(nombre, correo, password, telefono)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            mensaje.textContent = "Cuenta creada correctamente. Ya puedes iniciar sesión.";
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al registrar el usuario.";
        });
}

function login() {
    let correo = document.getElementById("loginCorreo").value;
    let password = document.getElementById("loginPassword").value;
    let mensaje = document.getElementById("mensajeUsuario");
    if (correo == "" || password == "") {
        mensaje.textContent = "Completa tu correo y contraseña.";
        return;
    }
    iniciarSesion(correo, password)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            mensaje.textContent = "Bienvenido, " + respuesta.nombre;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al iniciar sesión.";
        });
}


function verPerfil() {
    let mensaje = document.getElementById("mensajeUsuario");
    obtenerPerfil()
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            document.getElementById("perfilNombre").value = respuesta.nombre;
            document.getElementById("perfilTelefono").value = respuesta.telefono || "";
            mensaje.textContent = "Perfil consultado correctamente.";
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar el perfil.";
        });
}

function guardarPerfil() {
    let nombre = document.getElementById("perfilNombre").value;
    let telefono = document.getElementById("perfilTelefono").value;
    let mensaje = document.getElementById("mensajeUsuario");
    if (nombre == "") {
        mensaje.textContent = "El nombre es obligatorio.";
        return;
    }
    actualizarPerfil(nombre, telefono)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            mensaje.textContent = "Perfil actualizado correctamente.";
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al actualizar el perfil.";
        });
}

function salir() {
    let mensaje = document.getElementById("mensajeUsuario");
    cerrarSesion()
        .then(respuesta => {
            if (respuesta && respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            document.getElementById("loginCorreo").value = "";
            document.getElementById("loginPassword").value = "";
            document.getElementById("perfilNombre").value = "";
            document.getElementById("perfilTelefono").value = "";
            mensaje.textContent = "Sesión cerrada correctamente.";
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al cerrar sesión.";
        });
}

function verMisReservaciones() {
    let tabla = document.getElementById("tablaReservaciones");
    let mensaje = document.getElementById("mensajeReservaciones");
    tabla.innerHTML = "";
    mensaje.textContent = "Consultando reservaciones...";
    obtenerMisReservas()
        .then(reservaciones => {
            if (!Array.isArray(reservaciones)) {
                mensaje.textContent = reservaciones.mensaje ||
                    "Error al consultar las reservaciones.";
                return;
            }
            if (reservaciones.length == 0) {
                mensaje.textContent = "No tienes reservaciones registradas.";
                return;
            }
            let fecha = new Date();
            let hoy = fecha.getFullYear() + "-" +
                String(fecha.getMonth() + 1).padStart(2, "0") + "-" +
                String(fecha.getDate()).padStart(2, "0");
            reservaciones.forEach(reservacion => {
                let acciones = "Sin acciones";
                if (reservacion.estado == "CONFIRMADA" &&
                    reservacion.fecha_entrada > hoy) {
                    acciones = `
                        <button type="button" onclick="cancelarMiReservacion(${reservacion.id_reservacion})">
                            Cancelar
                        </button>
                    `;
                }
                if (reservacion.estado == "PENDIENTE_PAGO") {
                    acciones = "Pendiente de pago";
                }
                tabla.innerHTML += `
                    <tr>
                        <td>${reservacion.folio}</td>
                        <td>${reservacion.nombre_hotel}</td>
                        <td>${reservacion.ciudad}</td>
                        <td>${reservacion.numero_habitacion}</td>
                        <td>${reservacion.fecha_entrada}</td>
                        <td>${reservacion.fecha_salida}</td>
                        <td>${reservacion.cantidad_huespedes}</td>
                        <td>$${reservacion.total}</td>
                        <td>${reservacion.estado}</td>
                        <td>${acciones}</td>
                    </tr>
                `;
            });
            mensaje.textContent = "Reservaciones encontradas: " +
                reservaciones.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar las reservaciones.";
        });
}

function cancelarMiReservacion(idReservacion) {
    let mensaje = document.getElementById("mensajeReservaciones");
    if (!confirm("¿Estás seguro de que deseas cancelar esta reservación?")) {
        return;
    }
    cancelarReservacion(idReservacion)
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            verMisReservaciones();
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al cancelar la reservación.";
        });
}

function verHistorial() {
    let tabla = document.getElementById("tablaHistorial");
    let mensaje = document.getElementById("mensajeHistorial");
    tabla.innerHTML = "";
    mensaje.textContent = "Consultando historial...";
    obtenerHistorial()
        .then(reservaciones => {
            if (!Array.isArray(reservaciones)) {
                mensaje.textContent = reservaciones.mensaje ||
                    "Error al consultar el historial.";
                return;
            }
            if (reservaciones.length == 0) {
                mensaje.textContent = "No tienes estancias completadas.";
                return;
            }
            reservaciones.forEach(reservacion => {
                tabla.innerHTML += `
                    <tr>
                        <td>${reservacion.nombre_hotel}</td>
                        <td>${reservacion.ciudad}</td>
                        <td>${reservacion.numero_habitacion}</td>
                        <td>${reservacion.fecha_entrada}</td>
                        <td>${reservacion.fecha_salida}</td>
                        <td>$${reservacion.total}</td>
                        <td>${reservacion.estado}</td>
                    </tr>
                `;
            });
            mensaje.textContent = "Estancias encontradas: " +
                reservaciones.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar el historial.";
        });
}

function verCancelaciones() {
    let tabla = document.getElementById("tablaCancelaciones");
    let mensaje = document.getElementById("mensajeCancelaciones");
    tabla.innerHTML = "";
    mensaje.textContent = "Consultando cancelaciones...";
    obtenerCancelaciones()
        .then(reservaciones => {
            if (!Array.isArray(reservaciones)) {
                mensaje.textContent = reservaciones.mensaje ||
                    "Error al consultar las cancelaciones.";
                return;
            }
            if (reservaciones.length == 0) {
                mensaje.textContent = "No tienes cancelaciones ni reservaciones expiradas.";
                return;
            }
            reservaciones.forEach(reservacion => {
                let fechaCancelacion = "No aplica";
                if (reservacion.fecha_cancelacion) {
                    fechaCancelacion = reservacion.fecha_cancelacion.replace("T", " ");
                }
                tabla.innerHTML += `
                    <tr>
                        <td>${reservacion.nombre_hotel}</td>
                        <td>${reservacion.numero_habitacion}</td>
                        <td>${reservacion.fecha_entrada}</td>
                        <td>${reservacion.fecha_salida}</td>
                        <td>${fechaCancelacion}</td>
                        <td>$${reservacion.total}</td>
                        <td>${reservacion.estado}</td>
                    </tr>
                `;
            });
            mensaje.textContent = "Registros encontrados: " +
                reservaciones.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar las cancelaciones.";
        });
}
