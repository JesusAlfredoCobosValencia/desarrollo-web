# TwoVago — Flujo de reservaciones y pagos simulados

## 1. Descripción

Este documento describe el funcionamiento del módulo de reservaciones de TwoVago, desde la selección de una habitación hasta la confirmación, cancelación o vencimiento de una reserva.

El sistema permitirá reservar habitaciones de distintos hoteles y contará con un proceso de pago simulado con un tiempo límite de 10 minutos.

El objetivo es controlar la disponibilidad de las habitaciones y evitar que dos usuarios reserven la misma habitación durante fechas que coincidan.

## 2. Proceso de reservación

El flujo de reservación se realizará de la siguiente manera:

1. El usuario inicia sesión en TwoVago.
2. Busca un hotel por ciudad, fechas de entrada y salida, y número de huéspedes.
3. El sistema muestra los hoteles y habitaciones que cumplen con los criterios.
4. El usuario selecciona una habitación.
5. El backend verifica nuevamente la disponibilidad.
6. Se calcula el precio total según el número de noches.
7. Se crea la reservación con estado `PENDIENTE_PAGO`.
8. Se genera un folio y un registro de pago simulado.
9. El usuario dispone de 10 minutos para confirmar el pago.
10. Si confirma dentro del plazo, la reservación cambia a `CONFIRMADA`.
11. Si no confirma a tiempo, cambia a `EXPIRADA` y la habitación se libera para nuevas reservaciones.

## 3. Estados de una reservación

| Estado | Descripción |
| PENDIENTE_PAGO | La habitación está apartada temporalmente mientras se espera el pago. |
| CONFIRMADA | El pago simulado fue aprobado dentro del plazo. |
| CANCELADA | El usuario canceló una reservación confirmada. |
| EXPIRADA | Se agotó el tiempo de pago sin confirmación. |
| COMPLETADA | La estancia finalizó y se registró como concluida. |

### Transiciones de estado

- `PENDIENTE_PAGO` → `CONFIRMADA`: pago confirmado a tiempo.
- `PENDIENTE_PAGO` → `EXPIRADA`: vencimiento del plazo.
- `CONFIRMADA` → `CANCELADA`: cancelación antes de la fecha de entrada.
- `CONFIRMADA` → `COMPLETADA`: finalización de la estancia.

Una reservación expirada o cancelada no podrá volver directamente al estado confirmado. El usuario deberá crear una nueva reservación.

## 4. Sistema de pago simulado

Cuando se crea una reservación, el sistema genera un pago con estado `PENDIENTE` y un folio único.

Inicialmente, el campo `metodo_simulado` tendrá el valor `SIN_SELECCIONAR`. Cuando el usuario confirme el pago, se actualizará con el método ficticio seleccionado.

El plazo será de 10 minutos a partir de la creación de la reservación.

La fecha de vencimiento se guardará en `reservaciones.fecha_limite_pago`, para que el control no dependa únicamente del temporizador de JavaScript.

El backend comprobará el tiempo restante antes de aprobar cualquier pago.

### Confirmación correcta

Si el pago se confirma antes del vencimiento:

- El pago cambia a `APROBADO`.
- La reservación cambia a `CONFIRMADA`.
- La habitación permanece ocupada durante las fechas reservadas.
- El usuario recibe un mensaje de confirmación con su folio.

### Vencimiento

Si termina el plazo sin confirmar el pago:

- La reservación cambia a `EXPIRADA`.
- El pago cambia a `EXPIRADO`.
- La habitación deja de estar apartada.
- El usuario puede iniciar una nueva reservación.

Spring Boot realizará una revisión periódica de las reservaciones vencidas. Además, comprobará la fecha límite cuando se intente confirmar un pago o consultar disponibilidad, para impedir confirmaciones tardías.

No se procesarán tarjetas bancarias ni pagos reales.

## 5. Validación de disponibilidad

Cada registro de la tabla `habitaciones` representa una habitación física de un hotel.

Para reservar, se comprobará que:

- La habitación esté activa.
- La fecha de entrada sea anterior a la fecha de salida.
- La fecha de entrada no sea anterior a la fecha actual.
- La capacidad de la habitación permita alojar a los huéspedes.
- No existan reservaciones que bloqueen las fechas solicitadas.

Una habitación estará ocupada si tiene una reservación `CONFIRMADA` cuyas fechas coincidan con la nueva solicitud.

También estará bloqueada si tiene una reservación `PENDIENTE_PAGO` cuyo plazo de pago todavía no haya terminado.

Las reservaciones `CANCELADA` y `EXPIRADA` no bloquearán fechas.

Dos periodos se consideran superpuestos cuando:

`nuevaEntrada < salidaExistente` y `nuevaSalida > entradaExistente`.

Por ejemplo, una habitación reservada del 10 al 12 de diciembre podrá recibir otra reservación a partir del 12 de diciembre, siempre que cumpla las demás reglas.

Para evitar reservas simultáneas de dos clientes, la verificación de disponibilidad y la creación de la reservación se ejecutarán dentro de una transacción en el backend, utilizando un bloqueo de la habitación durante esa operación.

## 6. Cálculo del precio

El precio total se calculará multiplicando el número de noches por el precio por noche de la habitación.

**Fórmula:**

Total = Número de noches × Precio por noche

Ejemplo:

- Precio por noche: $1,200 MXN.
- Fecha de entrada: 10 de diciembre.
- Fecha de salida: 13 de diciembre.
- Número de noches: 3.
- Total: $3,600 MXN.

El total se calculará en el backend y se guardará al crear la reservación, de forma que no cambie si posteriormente se modifica el precio de la habitación.

En esta versión no se aplicarán impuestos ni comisiones adicionales.

## 7. Cancelaciones

El usuario podrá cancelar una reservación confirmada antes de su fecha de entrada.

El sistema deberá verificar que la reservación pertenece al usuario autenticado y que su estado permite cancelarla.

Cuando se cancele:

1. Se actualizará su estado a `CANCELADA`.
2. Se registrará la fecha de cancelación.
3. La habitación quedará disponible para esas fechas.
4. El registro permanecerá en el historial de cancelaciones.

Como los pagos son simulados, no se desarrollará un sistema de reembolsos bancarios.

## 8. Historial del usuario

El perfil contará con apartados separados.

### Mis reservaciones

Mostrará las reservaciones pendientes de pago y confirmadas, incluyendo hotel, habitación, fechas, precio y estado.

### Historial de estancias

Mostrará las reservaciones con estado `COMPLETADA`.

Incluirá el hotel, ciudad, fechas de entrada y salida, habitación y monto pagado.

Para esta versión, el sistema podrá marcar una estancia como completada cuando haya pasado su fecha de salida. Este registro será un historial de reservas concluidas, no una comprobación física de asistencia al hotel.

### Cancelaciones

Mostrará las reservaciones con estado `CANCELADA`, con sus fechas y datos del hotel.

### Reservaciones expiradas

Mostrará las reservaciones que no fueron pagadas durante los 10 minutos establecidos.

## 9. Endpoints relacionados

| Método | Endpoint | Función |
|---|---|---|
| POST | /api/reservaciones | Crear reservación |
| GET | /api/reservaciones/{id} | Consultar reservación |
| GET | /api/reservaciones/mis-reservas | Consultar reservas actuales |
| GET | /api/reservaciones/historial | Consultar estancias completadas |
| GET | /api/reservaciones/cancelaciones | Consultar cancelaciones y expiraciones |
| PUT | /api/reservaciones/{id}/cancelar | Cancelar reservación |
| GET | /api/pagos/{id} | Consultar pago simulado |
| POST | /api/pagos/{id}/confirmar | Confirmar pago simulado |

## 10. Casos de prueba previstos

| Prueba | Resultado esperado |
|---|---|
| Reservar habitación disponible | Se crea una reservación PENDIENTE_PAGO. |
| Reservar habitación ocupada | Se rechaza por conflicto de disponibilidad. |
| Confirmar pago dentro de 10 minutos | La reservación pasa a CONFIRMADA. |
| Confirmar pago después del vencimiento | Se rechaza y la reserva queda EXPIRADA. |
| Cancelar reserva confirmada antes de la entrada | Cambia a CANCELADA. |
| Consultar historial | Se muestran las reservaciones COMPLETADAS. |
| Consultar reservaciones de otro usuario | Se rechaza el acceso. |
| Dos usuarios reservan simultáneamente la misma habitación | Solamente una operación puede apartarla. |

## 11. Tecnologías previstas

- Java y Spring Boot para la lógica de reservaciones.
- Spring Data JPA e Hibernate para persistencia.
- MySQL para almacenamiento.
- JavaScript y Fetch para la comunicación con el backend.
- Transacciones para controlar la concurrencia.
- Tareas programadas de Spring Boot para actualizar reservaciones vencidas.

El desarrollo y las pruebas funcionales de este módulo se realizarán en los siguientes entregables.