
# TwoVago — Diseño de la base de datos

## 1. Descripción

La base de datos de TwoVago permitirá almacenar y administrar la información de los usuarios, hoteles, habitaciones, reservaciones y pagos simulados.

Se utilizará MySQL como sistema gestor de base de datos y Hibernate/JPA para la persistencia de información desde Spring Boot.

## 2. Tablas del sistema

### 2.1. Usuarios

Almacena la información de los clientes y administradores.

| Campo | Tipo de dato | Restricciones |
|---|---|---|
| id_usuario | BIGINT | PK, AUTO_INCREMENT |
| nombre | VARCHAR(100) | NOT NULL |
| correo | VARCHAR(150) | UNIQUE, NOT NULL |
| password_hash | VARCHAR(255) | NOT NULL |
| telefono | VARCHAR(20) | NULL |
| rol | VARCHAR(20) | NOT NULL |
| fecha_registro | DATETIME | NOT NULL |

Roles: CLIENTE y ADMIN.

### 2.2. Hoteles

Almacena la información de los hoteles disponibles en la plataforma.

| Campo | Tipo de dato | Restricciones |
|---|---|---|
| id_hotel | BIGINT | PK, AUTO_INCREMENT |
| nombre | VARCHAR(120) | NOT NULL |
| ciudad | VARCHAR(100) | NOT NULL |
| direccion | VARCHAR(255) | NOT NULL |
| descripcion | TEXT | NULL |
| categoria | TINYINT | NULL |
| imagen_url | VARCHAR(500) | NULL |
| servicios | TEXT | NULL |
| activo | BOOLEAN | NOT NULL |

### 2.3. Habitaciones

Almacena las habitaciones físicas pertenecientes a cada hotel.

| Campo | Tipo de dato | Restricciones |
|---|---|---|
| id_habitacion | BIGINT | PK, AUTO_INCREMENT |
| id_hotel | BIGINT | FK, NOT NULL |
| numero | VARCHAR(20) | NOT NULL |
| tipo | VARCHAR(60) | NOT NULL |
| capacidad | SMALLINT | NOT NULL |
| precio_noche | DECIMAL(10,2) | NOT NULL |
| descripcion | TEXT | NULL |
| activa | BOOLEAN | NOT NULL |

Cada habitación pertenece a un hotel. La combinación de hotel y número de habitación será única.

### 2.4. Reservaciones

Almacena las reservaciones realizadas por los usuarios y permite consultar su estado e historial.

| Campo | Tipo de dato | Restricciones |
|---|---|---|
| id_reservacion | BIGINT | PK, AUTO_INCREMENT |
| folio | VARCHAR(30) | UNIQUE, NOT NULL |
| id_usuario | BIGINT | FK, NOT NULL |
| id_habitacion | BIGINT | FK, NOT NULL |
| fecha_entrada | DATE | NOT NULL |
| fecha_salida | DATE | NOT NULL |
| cantidad_huespedes | SMALLINT | NOT NULL |
| total | DECIMAL(10,2) | NOT NULL |
| estado | VARCHAR(20) | NOT NULL |
| fecha_creacion | DATETIME | NOT NULL |
| fecha_limite_pago | DATETIME | NOT NULL |
| fecha_cancelacion | DATETIME | NULL |

Estados posibles:

- PENDIENTE_PAGO
- CONFIRMADA
- CANCELADA
- EXPIRADA
- COMPLETADA

### 2.5. Pagos

Almacena los registros de los pagos simulados asociados a las reservaciones.

| Campo | Tipo de dato | Restricciones |
|---|---|---|
| id_pago | BIGINT | PK, AUTO_INCREMENT |
| id_reservacion | BIGINT | FK, UNIQUE, NOT NULL |
| folio_pago | VARCHAR(30) | UNIQUE, NOT NULL |
| monto | DECIMAL(10,2) | NOT NULL |
| metodo_simulado | VARCHAR(30) | NOT NULL |
| estado | VARCHAR(20) | NOT NULL |
| fecha_pago | DATETIME | NULL |

Estados posibles:

- PENDIENTE
- APROBADO
- RECHAZADO
- EXPIRADO

## 3. Relaciones entre tablas

1. Un usuario puede realizar muchas reservaciones.
2. Un hotel puede tener muchas habitaciones.
3. Una habitación puede estar asociada a muchas reservaciones a lo largo del tiempo.
4. Una reservación pertenece a un solo usuario.
5. Una reservación corresponde a una sola habitación.
6. Una reservación puede tener un registro de pago asociado.

## 4. Llaves foráneas

| Tabla | Campo | Referencia |
|---|---|---|
| habitaciones | id_hotel | hoteles.id_hotel |
| reservaciones | id_usuario | usuarios.id_usuario |
| reservaciones | id_habitacion | habitaciones.id_habitacion |
| pagos | id_reservacion | reservaciones.id_reservacion |

## 5. Reglas de integridad

- El correo electrónico de cada usuario debe ser único.
- Cada habitación debe pertenecer a un hotel existente.
- El número de habitación no debe repetirse dentro del mismo hotel.
- Las fechas de salida deben ser posteriores a las fechas de entrada.
- El número de huéspedes no debe superar la capacidad de la habitación.
- Una habitación no puede tener reservaciones activas con fechas superpuestas.
- El folio de cada reservación debe ser único.
- Una reservación tendrá como máximo un registro de pago.

## 6. Control de disponibilidad

La disponibilidad se determinará mediante la consulta de reservaciones de una habitación.

Se considerarán bloqueadas las fechas de las reservaciones que estén en estado PENDIENTE_PAGO o CONFIRMADA.

Cuando una reservación sea CANCELADA o EXPIRADA, dejará de bloquear la habitación.

Para evitar que dos usuarios reserven simultáneamente la misma habitación, el backend deberá validar nuevamente la disponibilidad al crear la reservación y controlar las operaciones concurrentes.

## 7. Historial y cancelaciones

No se crearán tablas independientes para el historial de estancias ni para las cancelaciones.

La información se obtendrá de la tabla reservaciones:

- Historial: reservaciones COMPLETADAS.
- Cancelaciones: reservaciones CANCELADAS.
- Vencidas: reservaciones EXPIRADAS.
- Actuales: reservaciones PENDIENTE_PAGO y CONFIRMADA.

## 8. Tecnología

- Gestor de base de datos: MySQL.
- ORM: Hibernate/JPA.
- Backend: Java con Spring Boot.
- Relaciones mediante llaves primarias y foráneas.
