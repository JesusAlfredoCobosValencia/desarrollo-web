
# TwoVago — Diseño de API REST

## 1. Descripción

La API REST de TwoVago permitirá comunicar el frontend con el backend desarrollado en Java y Spring Boot.

Se utilizarán peticiones HTTP mediante Fetch y el formato JSON para intercambiar información.

La API permitirá administrar usuarios, hoteles, habitaciones, reservaciones y pagos simulados.

## 2. Configuración general

URL base: http://localhost:8080/api

Tecnologías:
- Java con Spring Boot.
- MySQL.
- Hibernate/JPA.
- JavaScript con Fetch.
- JSON.

Métodos HTTP:
- GET: consultar información.
- POST: registrar información o ejecutar acciones.
- PUT: actualizar información.
- DELETE: eliminar registros.

## 3. Endpoints de autenticación y usuarios

Responsable del módulo: Francisco.

| Método | Endpoint | Función | Acceso |
|---|---|---|---|
| POST | /api/auth/registro | Registrar usuario | Público |
| POST | /api/auth/login | Iniciar sesión | Público |
| POST | /api/auth/logout | Cerrar sesión | Autenticado |
| GET | /api/usuarios/perfil | Consultar perfil propio | Autenticado |
| PUT | /api/usuarios/perfil | Actualizar datos personales | Autenticado |

### Ejemplo: registrar usuario

POST /api/auth/registro

Solicitud JSON:

```json
{
  "nombre": "Juan Perez",
  "correo": "juan@example.com",
  "password": "EjemploSeguro123",
  "telefono": "2281234567"
}
```

Respuesta esperada: HTTP 201 Created

```json
{
  "id_usuario": 1,
  "nombre": "Juan Perez",
  "correo": "juan@example.com",
  "rol": "CLIENTE"
}
```

La contraseña nunca deberá devolverse en las respuestas.

La autenticación se realizará mediante sesiones administradas por Spring Security.

## 4. Endpoints de hoteles

Responsable del módulo: Jesús.

| Método | Endpoint | Función | Acceso |
|---|---|---|---|
| GET | /api/hoteles | Listar y buscar hoteles | Público |
| GET | /api/hoteles/{id} | Consultar un hotel | Público |
| POST | /api/hoteles | Registrar hotel | ADMIN |
| PUT | /api/hoteles/{id} | Actualizar hotel | ADMIN |
| DELETE | /api/hoteles/{id} | Eliminar o desactivar hotel | ADMIN |

### Ejemplo: consultar hoteles

GET /api/hoteles

Respuesta HTTP 200 OK:

```json
[
  {
    "id_hotel": 1,
    "nombre": "Hotel Costa Azul",
    "ciudad": "Veracruz",
    "direccion": "Av. Principal 100",
    "categoria": 4,
    "activo": true
  },
  {
    "id_hotel": 2,
    "nombre": "Hotel Las Palmas",
    "ciudad": "Xalapa",
    "direccion": "Calle Centro 25",
    "categoria": 3,
    "activo": true
  }
]
```

### Filtro por ciudad

GET /api/hoteles?ciudad=Veracruz

Devolverá los hoteles que coincidan con la ciudad solicitada.

## 5. Endpoints de habitaciones

Responsable del módulo: Jesús.

| Método | Endpoint | Función | Acceso |
|---|---|---|---|
| GET | /api/habitaciones | Consultar habitaciones | Público |
| GET | /api/habitaciones/{id} | Detalles de una habitación | Público |
| GET | /api/habitaciones/disponibles | Consultar disponibilidad | Público |
| POST | /api/habitaciones | Registrar habitación | ADMIN |
| PUT | /api/habitaciones/{id} | Modificar habitación | ADMIN |
| DELETE | /api/habitaciones/{id} | Eliminar o desactivar habitación | ADMIN |

### Ejemplo: habitaciones de un hotel

GET /api/habitaciones?hotelId=1

Respuesta HTTP 200 OK:

```json
[
  {
    "id_habitacion": 1,
    "id_hotel": 1,
    "numero": "101",
    "tipo": "Doble",
    "capacidad": 2,
    "precio_noche": 1200.00,
    "activa": true
  }
]
```

### Consultar disponibilidad

GET /api/habitaciones/disponibles?hotelId=1&fechaEntrada=2026-12-10&fechaSalida=2026-12-12&huespedes=2

El backend deberá consultar las reservaciones activas para identificar habitaciones disponibles durante las fechas solicitadas.

## 6. Endpoints de reservaciones

Responsable principal: Emanuel.

Consultas de historial y perfil: Francisco.

| Método | Endpoint | Función | Acceso |
|---|---|---|---|
| POST | /api/reservaciones | Crear reservación | CLIENTE |
| GET | /api/reservaciones/{id} | Consultar reservación propia | Propietario o ADMIN |
| GET | /api/reservaciones/mis-reservas | Reservaciones actuales | CLIENTE |
| GET | /api/reservaciones/historial | Historial de estancias | CLIENTE |
| GET | /api/reservaciones/cancelaciones | Cancelaciones y vencimientos | CLIENTE |
| PUT | /api/reservaciones/{id}/cancelar | Cancelar reservación | Propietario o ADMIN |

### Ejemplo: crear reservación

POST /api/reservaciones

Solicitud JSON:

```json
{
  "id_habitacion": 1,
  "fecha_entrada": "2026-12-10",
  "fecha_salida": "2026-12-12",
  "cantidad_huespedes": 2
}
```

El usuario se identificará mediante su sesión. El total y el estado serán calculados por el servidor.

Respuesta esperada: HTTP 201 Created

```json
{
  "id_reservacion": 15,
  "folio": "TV-000015",
  "id_pago": 8,
  "total": 2400.00,
  "estado": "PENDIENTE_PAGO",
  "fecha_limite_pago": "2026-12-01T14:10:00"
}
```

El ejemplo representa una reservación de dos noches a $1200 por noche. La fecha límite de pago dependerá del momento real en que se cree la reservación.

## 7. Endpoints de pagos simulados

Responsable: Emanuel.

| Método | Endpoint | Función | Acceso |
|---|---|---|---|
| GET | /api/pagos/{id} | Consultar pago propio | Propietario o ADMIN |
| POST | /api/pagos/{id}/confirmar | Confirmar pago simulado | Propietario |

### Ejemplo: confirmar pago

POST /api/pagos/8/confirmar

Solicitud JSON:

```json
{
  "metodo_simulado": "TARJETA_SIMULADA"
}
```

Respuesta HTTP 200 OK:

```json
{
  "id_pago": 8,
  "folio_pago": "PAGO-TV-000008",
  "estado": "APROBADO",
  "mensaje": "Pago simulado confirmado"
}
```

La confirmación deberá realizarse dentro del plazo de 10 minutos. Si el plazo vence, el sistema rechazará la confirmación y la reservación pasará a EXPIRADA.

No se procesarán pagos bancarios reales.

## 8. Reglas de la API

1. Los clientes deberán iniciar sesión para reservar.
2. Las operaciones administrativas requerirán el rol ADMIN.
3. Los usuarios solamente podrán consultar sus propias reservaciones y pagos.
4. El backend verificará la disponibilidad antes de crear una reservación.
5. Las reservas pendientes bloquearán temporalmente la habitación.
6. Las reservaciones expiradas y canceladas dejarán de bloquear disponibilidad.
7. El servidor calculará el precio total.
8. Las confirmaciones de pago posteriores al vencimiento serán rechazadas.
9. La API devolverá respuestas JSON.
10. Las validaciones de datos se realizarán en el backend.

## 9. Códigos HTTP esperados

| Código | Significado |
|---|---|
| 200 | Operación exitosa |
| 201 | Registro creado |
| 400 | Datos inválidos |
| 401 | Usuario no autenticado |
| 403 | Permisos insuficientes |
| 404 | Recurso no encontrado |
| 409 | Conflicto de disponibilidad o estado |
| 500 | Error interno del servidor |

## 10. Pruebas previstas

Durante el desarrollo se utilizará CURL para comprobar los endpoints.

Se probará:
- Consulta de hoteles.
- Registro y modificación de hoteles.
- Consulta de habitaciones.
- Registro de usuarios.
- Creación de reservaciones.
- Validación de disponibilidad.
- Confirmación de pagos simulados.
- Vencimiento de reservaciones.
- Consulta de historial y cancelaciones.

Las pruebas deberán documentar resultados exitosos y casos de error.
