# Reporte de Avance del Proyecto

**Equipo:** TwoVago

**Fecha:** 17 de noviembre de 2026

**Número de reporte:** R3 — Usuarios, reservaciones y pagos simulados

**Repositorio GitHub:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas

Durante esta tercera entrega se amplió el backend desarrollado en R2. Se incorporaron los módulos de usuarios, autenticación, perfiles, reservaciones y pagos simulados, manteniendo la arquitectura de tres capas con Java y Spring Boot.

Las actividades realizadas fueron las siguientes:

- Desarrollo de la entidad `Usuario.java`, su repositorio y su servicio.
- Implementación del registro de usuarios mediante `POST /api/auth/registro`.
- Implementación del inicio y cierre de sesión mediante sesiones HTTP.
- Validación de los datos de registro y asignación automática del rol `CLIENTE`.
- Desarrollo de DTO para recibir solicitudes y generar respuestas sin incluir la contraseña.
- Implementación de la consulta y actualización del perfil del usuario autenticado.
- Incorporación de restricciones para separar las operaciones de clientes y administradores.
- Ajustes a los controladores y servicios de hoteles y habitaciones.
- Implementación de la consulta de habitaciones disponibles según las fechas y la capacidad.
- Desarrollo de los módulos de reservaciones y pagos simulados.
- Validación de fechas, disponibilidad y prevención de reservaciones duplicadas.
- Cálculo del importe total de las reservaciones desde el backend.
- Generación de reservaciones con estado `PENDIENTE_PAGO` y un plazo de 10 minutos para realizar el pago.
- Implementación de la confirmación de pagos simulados.
- Desarrollo de la cancelación, consulta de historial y liberación de habitaciones.
- Implementación de la expiración automática de reservaciones y pagos vencidos.
- Preparación y ejecución de una batería de pruebas automatizadas mediante Python y Bash.
- Generación del archivo `resultados-pruebas-e3.txt` con los resultados de las pruebas.

---

# 2. Evidencias de trabajo

Las actividades de esta entrega se respaldan mediante capturas del código fuente, configuración del backend, compilación de los componentes y ejecución de las pruebas automatizadas. Las imágenes se encuentran en `documentacion/reportes/evidencias/`.

## 2.1. Historial de contribuciones

| Actividad | Integrante responsable | Commit |
|---|---|---|
| Incorporación de la entidad, repositorio, servicio, DTO y controlador inicial de usuarios | Jesús Alfredo Cobos Valencia | [826cbe4](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/826cbe4) |
| Integración de los módulos de reservaciones, pagos, perfiles, sesiones y validaciones | Jesús Alfredo Cobos Valencia | [cab9cbb](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cab9cbb) |
| Actualización del reporte R3 y del registro individual de IA | Emanuel Hernández Ochoa | [b324796](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/b324796) |
| Integración de evidencias y actualización de documentación y pruebas | Francisco Báez Rodríguez | [08a60da](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/08a60da) |
| Actualización de los registros de IA | Francisco Báez Rodríguez | [db878c6](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/db878c6) |
| Incorporación del registro de desarrollo con IA | Francisco Báez Rodríguez | [d0278be](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d0278be) |
| Ajustes finales del servicio de usuarios, configuración, respaldo SQL y resultados de pruebas | Emanuel Hernández Ochoa | [0a06efd](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/0a06efd) |

## 2.2. Evidencias de implementación

Las siguientes evidencias documentan la implementación de los módulos de R3 y las pruebas realizadas para comprobar su funcionamiento.

| Archivo | Descripción |
|---|---|
| [E3_01_Entidad_Usuario.png](evidencias/E3_01_Entidad_Usuario.png) | Implementación de la entidad Usuario y definición de sus atributos. |
| [E3_02_Compilacion_Usuario.png](evidencias/E3_02_Compilacion_Usuario.png) | Compilación de los componentes del módulo de usuarios. |
| [E3_03_UsuarioRepository.png](evidencias/E3_03_UsuarioRepository.png) | Implementación del repositorio de usuarios mediante JpaRepository. |
| [E3_04_01_Dependencia_BCrypt.png](evidencias/E3_04_01_Dependencia_BCrypt.png) | Configuración inicial de BCrypt durante el desarrollo. |
| [E3_04_02_Compilacion_BCrypt.png](evidencias/E3_04_02_Compilacion_BCrypt.png) | Compilación de la configuración inicial de BCrypt. |
| [E3_04_03_UsuarioService.png](evidencias/E3_04_03_UsuarioService.png) | Servicio de usuarios, validaciones y asignación de roles. |
| [E3_05_01_DTO_Registro.png](evidencias/E3_05_01_DTO_Registro.png) | DTO para recibir los datos del registro de usuarios. |
| [E3_05_02_DTO_Respuesta.png](evidencias/E3_05_02_DTO_Respuesta.png) | DTO de respuesta para no incluir la contraseña en el JSON. |
| [E3_05_03_Compilacion.png](evidencias/E3_05_03_Compilacion.png) | Compilación de los componentes relacionados con el registro. |
| [E3_06_01_UsuarioController.png](evidencias/E3_06_01_UsuarioController.png) | Implementación del controlador REST de usuarios. |
| [E3_07_Registro_Login_Cierre.png](evidencias/E3_07_Registro_Login_Cierre.png) | Pruebas de registro, inicio y cierre de sesión. |
| [E3_08_Perfil_Consulta_Actualizacion.png](evidencias/E3_08_Perfil_Consulta_Actualizacion.png) | Consulta y actualización del perfil del usuario autenticado. |
| [E3_09_Control_Acceso_Roles.png](evidencias/E3_09_Control_Acceso_Roles.png) | Comprobación de permisos y restricciones según el rol. |
| [E3_10_Consulta_Habitaciones_Disponibilidad.png](evidencias/E3_10_Consulta_Habitaciones_Disponibilidad.png) | Consulta de habitaciones disponibles y validación de fechas. |
| [E3_11_Creacion_Reservaciones.png](evidencias/E3_11_Creacion_Reservaciones.png) | Creación de reservaciones pendientes de pago y cálculo de importes. |
| [E3_12_Validacion_Procesamiento_Pagos.png](evidencias/E3_12_Validacion_Procesamiento_Pagos.png) | Validación y procesamiento de pagos simulados. |
| [E3_13_Expiracion_Reservacion.png](evidencias/E3_13_Expiracion_Reservacion.png) | Comprobación de la expiración automática de reservaciones y pagos. |
| [E3_14_Cancelacion_Historial.png](evidencias/E3_14_Cancelacion_Historial.png) | Cancelación de reservaciones, consulta del historial y liberación de habitaciones. |
| [E3_15_Resultados_Pruebas.png](evidencias/E3_15_Resultados_Pruebas.png) | Resultados finales de la batería de pruebas automatizadas de R3. |

## 2.3. Registro y autenticación de usuarios

Se implementó el registro de usuarios mediante `POST /api/auth/registro` y el inicio de sesión mediante `POST /api/auth/login`.

Durante las pruebas se comprobó que:

- El registro de un usuario nuevo devuelve HTTP 201.
- El sistema asigna automáticamente el rol `CLIENTE`.
- La respuesta de registro no incluye la contraseña.
- Los correos duplicados son rechazados mediante HTTP 409.
- Los correos con formato inválido son rechazados mediante HTTP 400.
- El inicio de sesión con contraseña incorrecta devuelve HTTP 401.
- El inicio de sesión correcto devuelve HTTP 200.
- El cierre de sesión devuelve HTTP 204.

Durante el desarrollo se probó una configuración con BCrypt y se generaron las evidencias correspondientes. Sin embargo, esa configuración se retiró en el commit final `0a06efd`. La versión actual guarda el valor recibido en el campo `password_hash` sin aplicar una función de hash. Esta es la implementación que se conserva en la versión R3.

## 2.4. Consulta y actualización del perfil

Se implementaron las operaciones para que cada usuario pueda consultar y actualizar sus propios datos personales. Las pruebas comprobaron que la consulta y la actualización devuelven HTTP 200.

También se verificó que, después de cerrar sesión, el acceso al perfil devuelve HTTP 401. Un usuario con rol `CLIENTE` no puede registrar hoteles ni habitaciones, por lo que ambas operaciones devuelven HTTP 403.

## 2.5. Disponibilidad y reservaciones

Se desarrolló el módulo de reservaciones para permitir que los usuarios consulten habitaciones disponibles y generen reservaciones.

El servidor valida las fechas de entrada y salida, la capacidad de las habitaciones y los periodos ocupados por otras reservaciones. Al generar una reservación:

- Se comprueba la disponibilidad de la habitación.
- Se calcula el importe total desde el backend.
- Se genera el registro de la reservación.
- Se establece el estado inicial `PENDIENTE_PAGO`.
- Se establece un plazo de 10 minutos para completar el pago.

Las pruebas comprobaron que dos usuarios no puedan reservar la misma habitación para el mismo periodo y que un usuario no pueda consultar reservaciones pertenecientes a otra cuenta.

## 2.6. Pagos, cancelaciones y expiraciones

Se incorporó el módulo de pagos simulados para completar el proceso de reservación. Las pruebas comprobaron las siguientes operaciones:

- Creación de reservaciones pendientes de pago.
- Cálculo del importe total.
- Confirmación de pagos simulados.
- Actualización del estado del pago a `APROBADO`.
- Consulta de reservaciones y pagos propios.
- Cancelación de reservaciones confirmadas antes de la fecha de entrada.
- Conservación de las reservaciones canceladas en el historial.
- Liberación de habitaciones después de una cancelación.
- Expiración automática de reservaciones cuando termina el plazo de pago.
- Expiración del pago asociado a una reservación vencida.
- Rechazo de pagos después de la fecha límite.

## 2.7. Pruebas automatizadas

Para comprobar los módulos desarrollados durante R3 se preparó una batería de pruebas automatizadas mediante Python y Bash.

La ejecución se realiza con el script `scripts/validar_entregable3.sh`. Los resultados se almacenan en `documentacion/reportes/resultados-pruebas-e3.txt`.

La ejecución final se realizó contra el backend iniciado en el puerto 8083 y registró los siguientes resultados:

| Resultado | Cantidad |
|---|---:|
| Pruebas correctas | 55 |
| Pruebas fallidas | 0 |
| Pruebas omitidas | 0 |
| **Total** | **55** |

Las pruebas comprobaron la disponibilidad de hoteles y habitaciones, el registro y acceso de usuarios, los perfiles, los permisos, las reservaciones, los pagos simulados, las cancelaciones, las expiraciones y las operaciones administrativas de hoteles y habitaciones.

Todas las pruebas finalizaron correctamente.

---

# 3. Problemas encontrados

### Error de compilación en el manejo de sesiones

Durante la implementación del controlador de usuarios se presentó un error relacionado con el manejo de sesiones HTTP. Se revisó la implementación y se ajustó el código para permitir que el backend compilara correctamente. Después se comprobaron las operaciones de inicio y cierre de sesión mediante las pruebas automatizadas.

### Diferencias entre los endpoints y las solicitudes de prueba

Durante las primeras ejecuciones de la batería se encontraron respuestas HTTP 404 y HTTP 400. Estos resultados se relacionaban con diferencias entre las rutas utilizadas por el script, los parámetros de las solicitudes y la configuración del servidor. Se revisaron las rutas y las solicitudes, y la ejecución final se realizó contra el backend iniciado en el puerto 8083.

### Configuración de las pruebas administrativas

En las primeras ejecuciones faltaban las credenciales de administrador necesarias para comprobar las operaciones administrativas. Después de configurar las credenciales correspondientes, se verificó que el administrador pudiera iniciar sesión, consultar reservaciones, registrar y actualizar hoteles y habitaciones, y desactivarlos.

### Almacenamiento de contraseñas

Durante el desarrollo se configuró BCrypt y se generaron evidencias de esa etapa. Sin embargo, la clase `PasswordConfig.java` se retiró en el commit `0a06efd` y el servicio actual compara directamente la contraseña recibida con el valor almacenado. En la versión final se decidió mantener esta implementación sin aplicar BCrypt.

---

# 4. Avance funcional

Durante esta tercera entrega se amplió el backend de TwoVago mediante la incorporación de los módulos de usuarios, autenticación, perfiles, reservaciones y pagos simulados.

**Funcionalidades comprobadas:**

- Consulta pública de hoteles y habitaciones.
- Registro de usuarios y validación de correos electrónicos.
- Prevención de registros duplicados.
- Inicio y cierre de sesión.
- Consulta y actualización del perfil.
- Control de acceso según el rol del usuario.
- Restricción de operaciones administrativas para clientes.
- Consulta de disponibilidad de habitaciones.
- Validación de fechas de reservación.
- Creación de reservaciones pendientes de pago.
- Cálculo automático del importe de las reservaciones.
- Generación de un plazo de 10 minutos para completar el pago.
- Confirmación de pagos simulados.
- Prevención de reservaciones duplicadas.
- Consulta de reservaciones y pagos propios.
- Cancelación de reservaciones.
- Consulta del historial de cancelaciones y expiraciones.
- Expiración automática de reservaciones y pagos.
- Liberación de habitaciones después de una cancelación.
- Operaciones administrativas para registrar, actualizar y desactivar hoteles y habitaciones.

**Resultados generales de las pruebas:**

| Resultado | Cantidad |
|---|---:|
| Pruebas correctas | 55 |
| Pruebas fallidas | 0 |
| Pruebas omitidas | 0 |
| **Total** | **55** |

Las pruebas se ejecutaron mediante `scripts/validar_entregable3.sh`, utilizando el backend en el puerto 8083.

**Funcionalidades pendientes:**

- Desarrollo e integración de las interfaces del frontend.
- Conexión de las pantallas con los endpoints mediante Fetch.
- Configuración del envío y mantenimiento de cookies para las sesiones HTTP.
- Pruebas completas desde el navegador.

El avance de esta entrega corresponde principalmente al desarrollo y comprobación del backend. La integración de las interfaces queda para la siguiente etapa.

---

# 5. Uso de IA generativa

Durante R3 se utilizó ChatGPT como apoyo para revisar componentes de Spring Boot, comprender errores de compilación y solicitudes HTTP, organizar las pruebas automatizadas y preparar la documentación del entregable.

Se recibieron ejemplos de implementación en Java y Spring Boot, propuestas para corregir errores y orientación para preparar una batería de pruebas mediante Python y Bash. Las respuestas se revisaron y adaptaron a la estructura existente del proyecto.

El funcionamiento se comprobó mediante la ejecución del backend, solicitudes HTTP y la batería automatizada de 55 pruebas. El registro individual de esta actividad se encuentra en `documentacion/IAG/integrante-1-Jesus/r-ia-dev-r3.md`.

Emanuel Hernández Ochoa y Francisco Báez Rodríguez no registraron uso de IA generativa durante R3.

---

# 6. Participación individual

Las contribuciones de esta entrega se identificaron mediante los commits registrados en la rama principal del repositorio.

- **Jesús Alfredo Cobos Valencia:** realizó los commits `826cbe4` y `cab9cbb`, en los que se incorporaron la entidad, repositorio, servicio, DTO y controlador inicial de usuarios, así como cambios en los módulos de perfiles, sesiones, reservaciones, pagos y validaciones.

- **Emanuel Hernández Ochoa:** realizó los commits `b324796` y `0a06efd`, relacionados con la actualización del reporte R3 y del registro de IA, además de ajustes finales en el servicio de usuarios, la configuración, el respaldo SQL y los resultados de las pruebas.

- **Francisco Báez Rodríguez:** realizó los commits `08a60da`, `db878c6` y `d0278be`, relacionados con la integración de evidencias, actualización de documentación, ajustes de los registros de IA y resultados de las pruebas.

Los tres integrantes dieron seguimiento a los cambios del repositorio y a la integración de los componentes correspondientes a R3.

---

# 7. Próximas actividades

1. Desarrollar las interfaces funcionales del frontend de TwoVago.
2. Conectar las pantallas de registro e inicio de sesión con el backend mediante Fetch.
3. Configurar el envío y mantenimiento de cookies para las sesiones HTTP.
4. Integrar las interfaces de búsqueda de hoteles y consulta de habitaciones.
5. Conectar el proceso de reservación con el módulo de pagos simulados.
6. Realizar pruebas completas desde el navegador.
7. Revisar la integración general de los módulos del sistema.

---

## Observaciones adicionales

Durante R3 se amplió el backend de TwoVago para incorporar el registro y acceso de usuarios, perfiles, control de roles, disponibilidad, reservaciones y pagos simulados. También se implementaron las operaciones de cancelación e expiración automática.

La ejecución final de la batería automatizada registró 55 pruebas correctas, 0 fallidas y 0 omitidas. Las operaciones administrativas también quedaron comprobadas.

El siguiente periodo de trabajo se concentrará en desarrollar e integrar el frontend y atender los puntos de seguridad pendientes.
