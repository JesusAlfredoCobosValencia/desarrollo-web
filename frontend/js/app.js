let temporizadorPago;
let rolUsuarioActual = "";

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
                rolUsuarioActual = "";
                document.getElementById("botonAdmin").style.display = "none";
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            rolUsuarioActual = respuesta.rol;
            if (rolUsuarioActual == "ADMIN") {
                document.getElementById("botonAdmin").style.display = "inline-block";
            } else {
                document.getElementById("botonAdmin").style.display = "none";
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
            rolUsuarioActual = "";
            document.getElementById("botonAdmin").style.display = "none";
            document.getElementById("seccionAdmin").style.display = "none";
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

function abrirPanelAdmin() {
    if (rolUsuarioActual != "ADMIN") {
        alert("No tienes permisos para acceder.");
        return;
    }
    document.getElementById("seccionAdmin").style.display = "block";
    cargarHotelesAdmin();
}

function cargarHotelesAdmin() {
    let tabla = document.getElementById("tablaHotelesAdmin");
    let mensaje = document.getElementById("mensajeHotelesAdmin");
    tabla.innerHTML = "";
    mensaje.textContent = "Consultando hoteles...";
    obtenerHoteles()
        .then(hoteles => {
            if (!Array.isArray(hoteles)) {
                mensaje.textContent = hoteles.mensaje ||
                    "Error al consultar hoteles.";
                return;
            }
            if (hoteles.length == 0) {
                mensaje.textContent = "No hay hoteles registrados.";
                return;
            }
            mostrarHotelesAdmin(hoteles);
            cargarHotelesSelect(hoteles);
            mensaje.textContent = "Hoteles encontrados: " + hoteles.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar hoteles.";
        });
}

function mostrarHotelesAdmin(hoteles) {
    let tabla = document.getElementById("tablaHotelesAdmin");
    tabla.innerHTML = "";
    hoteles.forEach(hotel => {
        tabla.innerHTML += `
            <tr>
                <td>${hotel.idHotel}</td>
                <td>${hotel.nombre}</td>
                <td>${hotel.ciudad}</td>
                <td>${hotel.direccion}</td>
                <td>${hotel.categoria || "Sin categoría"}</td>
                <td>
                    <button type="button" onclick="editarHotel(${hotel.idHotel})">
                        Editar
                    </button>
                    <button type="button" onclick="desactivarHotel(${hotel.idHotel})">
                        Desactivar
                    </button>
                </td>
            </tr>
        `;
    });
}

function cargarHotelesSelect(hoteles) {
    let select = document.getElementById("hotelHabitacion");
    select.innerHTML = '<option value="">Selecciona un hotel</option>';
    hoteles.forEach(hotel => {
        select.innerHTML += `
            <option value="${hotel.idHotel}">
                ${hotel.nombre}
            </option>
        `;
    });
}

function limpiarHotel() {
    document.getElementById("hotelId").value = "";
    document.getElementById("hotelNombre").value = "";
    document.getElementById("hotelCiudad").value = "";
    document.getElementById("hotelDireccion").value = "";
    document.getElementById("hotelDescripcion").value = "";
    document.getElementById("hotelCategoria").value = "";
    document.getElementById("hotelImagenUrl").value = "";
    document.getElementById("hotelServicios").value = "";
}

function guardarHotel() {
    let id = document.getElementById("hotelId").value;
    let nombre = document.getElementById("hotelNombre").value;
    let ciudad = document.getElementById("hotelCiudad").value;
    let direccion = document.getElementById("hotelDireccion").value;
    let mensaje = document.getElementById("mensajeHotelesAdmin");
    if (nombre == "" || ciudad == "" || direccion == "") {
        mensaje.textContent = "Completa nombre, ciudad y dirección.";
        return;
    }
    let hotel = {
        nombre: nombre,
        ciudad: ciudad,
        direccion: direccion,
        descripcion: document.getElementById("hotelDescripcion").value,
        categoria: document.getElementById("hotelCategoria").value == "" ?
            null : parseInt(document.getElementById("hotelCategoria").value),
        imagenUrl: document.getElementById("hotelImagenUrl").value,
        servicios: document.getElementById("hotelServicios").value
    };
    let peticion;

    if (id == "") {
        peticion = registrarHotel(hotel);
    } else {
        peticion = actualizarHotel(id, hotel);
    }
    mensaje.textContent = "Guardando hotel...";
    peticion
        .then(respuesta => {
            if (respuesta.mensaje) {
                mensaje.textContent = respuesta.mensaje;
                return;
            }
            mensaje.textContent = id == "" ?
                "Hotel registrado correctamente." :
                "Hotel actualizado correctamente.";

            limpiarHotel();
            cargarHotelesAdmin();
            cargarHoteles();
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al guardar el hotel.";
        });
}

function editarHotel(id) {
    obtenerHotel(id)
        .then(hotel => {
            if (hotel.mensaje) {
                document.getElementById("mensajeHotelesAdmin").textContent =
                    hotel.mensaje;
                return;
            }
            document.getElementById("hotelId").value = hotel.idHotel;
            document.getElementById("hotelNombre").value = hotel.nombre;
            document.getElementById("hotelCiudad").value = hotel.ciudad;
            document.getElementById("hotelDireccion").value = hotel.direccion;
            document.getElementById("hotelDescripcion").value =
                hotel.descripcion || "";
            document.getElementById("hotelCategoria").value =
                hotel.categoria || "";
            document.getElementById("hotelImagenUrl").value =
                hotel.imagenUrl || "";
            document.getElementById("hotelServicios").value =
                hotel.servicios || "";

            document.getElementById("mensajeHotelesAdmin").textContent =
                "Modificando hotel: " + hotel.nombre;
        })
        .catch(error => {
            console.log("Error:", error);
            document.getElementById("mensajeHotelesAdmin").textContent =
                "Error al consultar el hotel.";
        });
}

function desactivarHotel(id) {
    if (!confirm("¿Deseas desactivar este hotel?")) {
        return;
    }
    eliminarHotel(id)
        .then(respuesta => {
            if (respuesta.mensaje) {
                document.getElementById("mensajeHotelesAdmin").textContent =
                    respuesta.mensaje;
                return;
            }
            document.getElementById("mensajeHotelesAdmin").textContent =
                "Hotel desactivado correctamente.";

            cargarHotelesAdmin();
            cargarHoteles();
        })
        .catch(error => {
            console.log("Error:", error);
            document.getElementById("mensajeHotelesAdmin").textContent =
                "Error al desactivar el hotel.";
        });
}

function cargarHabitacionesAdmin() {
    let hotelId = document.getElementById("hotelHabitacion").value;
    let tabla = document.getElementById("tablaHabitacionesAdmin");
    let mensaje = document.getElementById("mensajeHabitacionesAdmin");

    tabla.innerHTML = "";

    if (hotelId == "") {
        mensaje.textContent = "Selecciona un hotel para consultar sus habitaciones.";
        return;
    }

    mensaje.textContent = "Consultando habitaciones...";

    obtenerHabitaciones(hotelId)
        .then(habitaciones => {
            if (!Array.isArray(habitaciones)) {
                mensaje.textContent = "Error al consultar las habitaciones.";
                return;
            }

            if (habitaciones.length == 0) {
                mensaje.textContent = "Este hotel no tiene habitaciones registradas.";
                return;
            }

            mostrarHabitacionesAdmin(habitaciones);
            mensaje.textContent = "Habitaciones encontradas: " + habitaciones.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar las habitaciones.";
        });
}

function mostrarHabitacionesAdmin(habitaciones) {
    let tabla = document.getElementById("tablaHabitacionesAdmin");
    tabla.innerHTML = "";

    habitaciones.forEach(habitacion => {
        tabla.innerHTML += `
            <tr>
                <td>${habitacion.idHabitacion}</td>
                <td>${habitacion.numero}</td>
                <td>${habitacion.tipo}</td>
                <td>${habitacion.capacidad}</td>
                <td>$${habitacion.precioNoche}</td>
                <td>
                    <button type="button" onclick="editarHabitacion(${habitacion.idHabitacion})">
                        Editar
                    </button>
                    <button type="button" onclick="desactivarHabitacionAdmin(${habitacion.idHabitacion})">
                        Desactivar
                    </button>
                </td>
            </tr>
        `;
    });
}

function limpiarHabitacion() {
    document.getElementById("habitacionId").value = "";
    document.getElementById("habitacionNumero").value = "";
    document.getElementById("habitacionTipo").value = "";
    document.getElementById("habitacionCapacidad").value = "";
    document.getElementById("habitacionPrecio").value = "";
    document.getElementById("habitacionDescripcion").value = "";
}

function guardarHabitacion() {
    let id = document.getElementById("habitacionId").value;
    let hotelId = document.getElementById("hotelHabitacion").value;
    let numero = document.getElementById("habitacionNumero").value;
    let tipo = document.getElementById("habitacionTipo").value;
    let capacidad = document.getElementById("habitacionCapacidad").value;
    let precio = document.getElementById("habitacionPrecio").value;
    let descripcion = document.getElementById("habitacionDescripcion").value;
    let mensaje = document.getElementById("mensajeHabitacionesAdmin");

    if (hotelId == "" || numero == "" || tipo == "" ||
        capacidad == "" || precio == "") {
        mensaje.textContent = "Completa todos los campos obligatorios.";
        return;
    }

    if (parseInt(capacidad) <= 0 || parseFloat(precio) <= 0) {
        mensaje.textContent = "La capacidad y el precio deben ser mayores que cero.";
        return;
    }

    let habitacion = {
        numero: numero,
        tipo: tipo,
        capacidad: parseInt(capacidad),
        precioNoche: parseFloat(precio),
        descripcion: descripcion
    };

    let peticion;

    if (id == "") {
        peticion = registrarHabitacion(hotelId, habitacion);
    } else {
        peticion = actualizarHabitacion(id, habitacion);
    }

    mensaje.textContent = "Guardando habitación...";

    peticion
        .then(respuesta => {
            mensaje.textContent = id == "" ?
                "Habitación registrada correctamente." :
                "Habitación actualizada correctamente.";

            limpiarHabitacion();
            cargarHabitacionesAdmin();
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = error.message || "Error al guardar la habitación.";
        });
}

function editarHabitacion(id) {
    obtenerHabitacion(id)
        .then(habitacion => {
            if (habitacion.mensaje) {
                document.getElementById("mensajeHabitacionesAdmin").textContent =
                    habitacion.mensaje;
                return;
            }

            document.getElementById("habitacionId").value =
                habitacion.idHabitacion;

            document.getElementById("habitacionNumero").value =
                habitacion.numero;

            document.getElementById("habitacionTipo").value =
                habitacion.tipo;

            document.getElementById("habitacionCapacidad").value =
                habitacion.capacidad;

            document.getElementById("habitacionPrecio").value =
                habitacion.precioNoche;

            document.getElementById("habitacionDescripcion").value =
                habitacion.descripcion || "";

            document.getElementById("mensajeHabitacionesAdmin").textContent =
                "Modificando habitación " + habitacion.numero;
        })
        .catch(error => {
            console.log("Error:", error);
            document.getElementById("mensajeHabitacionesAdmin").textContent =
                "Error al consultar la habitación.";
        });
}

function desactivarHabitacionAdmin(id) {
    if (!confirm("¿Deseas desactivar esta habitación?")) {
        return;
    }

    desactivarHabitacion(id)
        .then(() => {
            document.getElementById("mensajeHabitacionesAdmin").textContent =
                "Habitación desactivada correctamente.";

            limpiarHabitacion();
            cargarHabitacionesAdmin();
        })
        .catch(error => {
            console.log("Error:", error);
            document.getElementById("mensajeHabitacionesAdmin").textContent =
                error.message || "Error al desactivar la habitación.";
        });
}

function consultarReservacionesAdmin() {
    let tabla = document.getElementById("tablaReservacionesAdmin");
    let mensaje = document.getElementById("mensajeReservacionesAdmin");

    if (rolUsuarioActual != "ADMIN") {
        mensaje.textContent = "No tienes permisos para consultar las reservaciones.";
        return;
    }

    tabla.innerHTML = "";
    mensaje.textContent = "Consultando reservaciones...";

    obtenerReservacionesAdmin()
        .then(reservaciones => {
            if (!Array.isArray(reservaciones)) {
                mensaje.textContent = reservaciones.mensaje ||
                    "Error al consultar las reservaciones.";
                return;
            }

            if (reservaciones.length == 0) {
                mensaje.textContent = "No hay reservaciones registradas.";
                return;
            }

            reservaciones.forEach(reservacion => {
                tabla.innerHTML += `
                    <tr>
                        <td>${reservacion.folio}</td>
                        <td>${reservacion.id_usuario}</td>
                        <td>${reservacion.nombre_hotel}</td>
                        <td>${reservacion.numero_habitacion} - ${reservacion.tipo_habitacion}</td>
                        <td>${reservacion.fecha_entrada}</td>
                        <td>${reservacion.fecha_salida}</td>
                        <td>$${reservacion.total}</td>
                        <td>${reservacion.estado}</td>
                    </tr>
                `;
            });

            mensaje.textContent = "Reservaciones encontradas: " + reservaciones.length;
        })
        .catch(error => {
            console.log("Error:", error);
            mensaje.textContent = "Error al consultar las reservaciones.";
        });
}