# TwoVago — Release R3

**Versión:** R3 — Backend de usuarios, reservaciones y pagos simulados  
**Fecha de desarrollo:** 15 de noviembre de 2026

## 1. Alcance

Esta versión amplía el backend inicial de hoteles y habitaciones. Se incorporan sesiones de usuario, perfiles, validación de disponibilidad, reservaciones y pagos simulados con límite de 10 minutos.

## 2. Cambios incluidos

### Usuarios y sesiones
- Registro con validación de datos y hash de contraseña BCrypt.
- Inicio y cierre de sesión mediante `HttpSession` y cookie `JSESSIONID`.
- Consulta y actualización del perfil propio.
- Respuestas de usuario sin exponer la contraseña ni su hash.
- Verificación de acceso por usuario y rol.

### Hoteles y habitaciones
- Se conservan las consultas públicas y las operaciones CRUD.
- Las operaciones de alta, modificación y desactivación requieren rol `ADMIN`.
- Se agrega la consulta de habitaciones disponibles por hotel, fechas y huéspedes.
- Se validan datos básicos de hoteles y habitaciones.

### Reservaciones
- Creación de reservaciones para usuarios autenticados.
- Validación de fechas, capacidad y disponibilidad.
- Bloqueo de la habitación durante la transacción para reducir conflictos por solicitudes simultáneas.
- Cálculo del total en el backend a partir de noches y precio por noche.
- Generación de folio y fecha límite de pago de 10 minutos.
- Consulta de reservaciones propias, historial, cancelaciones y expiraciones.
- Cancelación de reservaciones confirmadas antes de la fecha de entrada.
- Consulta administrativa de todas las reservaciones.

### Pagos simulados
- Creación automática de un pago pendiente al generar una reservación.
- Confirmación con métodos ficticios permitidos.
- Rechazo de confirmaciones posteriores al vencimiento.
- Actualización del pago y de la reservación al confirmar o expirar.

### Automatización y errores
- Tarea programada para expirar pagos pendientes y completar estancias cuya fecha de salida ya pasó.
- Respuestas JSON para errores de validación, autenticación, permisos, recursos inexistentes y conflictos.
- Configuración CORS para el desarrollo del frontend.

## 3. Endpoints incorporados

- `POST /api/auth/login`
- `POST /api/auth/logout`
- `GET /api/usuarios/perfil`
- `PUT /api/usuarios/perfil`
- `GET /api/habitaciones/disponibles`
- `POST /api/reservaciones`
- `GET /api/reservaciones/{id}`
- `GET /api/reservaciones/mis-reservas`
- `GET /api/reservaciones/historial`
- `GET /api/reservaciones/cancelaciones`
- `GET /api/reservaciones/admin`
- `PUT /api/reservaciones/{id}/cancelar`
- `GET /api/pagos/{id}`
- `POST /api/pagos/{id}/confirmar`

## 4. Base de datos

Hibernate/JPA crea las tablas `reservaciones` y `pagos` al iniciar el backend cuando `spring.jpa.hibernate.ddl-auto=update`. También se agregó `database/actualizar_entregable3.sql` para crear las tablas manualmente.

## 5. Pruebas

Se agregó `scripts/pruebas_entregable3.py`, que ejecuta pruebas HTTP de registro, inicio y cierre de sesión, perfiles, permisos, disponibilidad, reservaciones, pagos, cancelación y privacidad de datos. El resultado se guarda en `documentacion/reportes/resultados-pruebas-e3.txt`.

Para compilar y ejecutar la batería se utiliza desde la raíz del repositorio:

```bash
bash scripts/validar_entregable3.sh
```

El script limpia y recompila el backend, lo inicia temporalmente en el puerto 8083 y ejecuta las pruebas contra esa instancia para evitar conectarse por error a un servidor anterior en el puerto 8082. Si `TWOVAGO_DB_PASSWORD` no está definida, solicita la contraseña de MySQL sin mostrarla. La prueba acelerada de expiración requiere el cliente `mysql`. Las pruebas administrativas requieren `TWOVAGO_ADMIN_EMAIL` y `TWOVAGO_ADMIN_PASSWORD` de una cuenta con rol `ADMIN`.

## 6. Pendientes de integración

- Conectar las interfaces del frontend con los endpoints.
- Probar el flujo completo desde navegador con sesiones y cookies.
- Ejecutar la batería automatizada en el entorno local del equipo y conservar el TXT generado.
- Capturar evidencias de las operaciones del Entregable 3.
