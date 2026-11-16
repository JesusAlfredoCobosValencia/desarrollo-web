# Reporte de avance del proyecto — R3

**Equipo:** TwoVago  
**Versión:** R3 — Usuarios, reservaciones y pagos simulados  
**Fecha de preparación:** 15 de noviembre de 2026  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

## 1. Actividades de desarrollo

Se amplió el backend desarrollado en la entrega anterior. Se mantuvo la estructura de tres capas y se añadieron servicios, entidades, repositorios, DTO y controladores para los módulos de usuarios, reservaciones y pagos simulados.

Las actividades incluidas en esta versión son:

- Implementación del inicio y cierre de sesión mediante sesiones HTTP.
- Registro de usuarios con validación y almacenamiento de contraseñas mediante BCrypt.
- Consulta y actualización del perfil del usuario autenticado.
- Control de acceso para las operaciones administrativas de hoteles y habitaciones.
- Consulta de habitaciones disponibles según hotel, fechas y cantidad de huéspedes.
- Implementación de la entidad y repositorio de reservaciones.
- Validación de fechas, capacidad y disponibilidad antes de crear una reservación.
- Cálculo del importe total en el backend y generación de un folio.
- Creación de un pago simulado asociado a cada reservación.
- Implementación del plazo de 10 minutos para confirmar el pago.
- Cancelación de reservaciones confirmadas antes de la fecha de entrada.
- Consultas de reservaciones actuales, historial, cancelaciones y expiraciones.
- Actualización automática de reservaciones vencidas y estancias completadas.
- Manejo centralizado de errores y respuestas HTTP.
- Preparación de una batería de pruebas HTTP que genera un reporte TXT.

## 2. Archivos principales modificados o incorporados

- `model/Reservacion.java`
- `model/Pago.java`
- `model/EstadoReservacion.java`
- `model/EstadoPago.java`
- `repository/ReservacionRepository.java`
- `repository/PagoRepository.java`
- `service/ReservacionService.java`
- `service/PagoService.java`
- `service/SesionService.java`
- `service/ReservacionScheduler.java`
- `controller/ReservacionController.java`
- `controller/PagoController.java`
- `controller/UsuarioController.java`
- `exception/ApiExceptionHandler.java`
- `dto/ReservacionRequest.java`
- `dto/ReservacionResponse.java`
- `dto/PagoResponse.java`
- `scripts/pruebas_entregable3.py`

## 3. Reglas implementadas

El servidor valida que la salida sea posterior a la entrada, que las fechas no estén en el pasado y que la capacidad de la habitación sea suficiente. Las fechas se comparan con las reservaciones pendientes cuyo plazo siga vigente y las reservaciones confirmadas.

Al crear una reservación, el servidor calcula el total, genera el folio y establece un plazo de 10 minutos. Si el pago se confirma a tiempo, la reservación pasa a `CONFIRMADA`. Si el plazo termina, cambia a `EXPIRADA` y deja de bloquear la disponibilidad.

Las cancelaciones conservan el registro y liberan las fechas para nuevas reservaciones. Cada usuario puede consultar únicamente sus propios pagos y reservaciones, salvo las consultas administrativas autorizadas.

## 4. Pruebas y evidencias

Se incorporó una herramienta para ejecutar las pruebas HTTP desde una terminal. El reporte se genera en:

`documentacion/reportes/resultados-pruebas-e3.txt`

**El reporte de resultados debe generarse después de ejecutar el backend y la batería de pruebas en el entorno local.** No se registran aquí resultados de ejecución que todavía no hayan sido comprobados.

Evidencias que se deberán agregar a la carpeta `documentacion/reportes/evidencias/`:

- Compilación Maven del backend R3.
- Registro e inicio de sesión.
- Consulta y actualización del perfil.
- Bloqueo de rutas administrativas para un cliente.
- Consulta de disponibilidad por fechas.
- Creación de reservación y folio.
- Confirmación del pago simulado.
- Expiración de una reservación pendiente.
- Cancelación e historial.
- Archivo TXT de resultados de pruebas.

## 5. Integración pendiente

La carpeta `frontend` no contiene todavía las interfaces funcionales. Por lo tanto, queda pendiente conectar las pantallas con los endpoints mediante Fetch, configurar el envío de cookies de sesión y probar el flujo completo desde el navegador.

La participación individual deberá registrarse por cada integrante en los commits y en su documento de uso de IA correspondiente.
