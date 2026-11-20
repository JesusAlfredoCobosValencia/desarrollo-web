# Reporte de Avance del Proyecto

**Equipo:** TwoVago

**Fecha:** 19 de noviembre de 2026

**Número de reporte:** R4 — Frontend e integración del sistema

**Repositorio GitHub:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas

Durante esta cuarta entrega se desarrolló e integró el frontend de TwoVago con el backend implementado en las entregas anteriores. Se conectaron las interfaces web con los endpoints REST mediante JavaScript y Fetch, permitiendo realizar las operaciones principales de la plataforma desde el navegador.

Las actividades realizadas fueron las siguientes:

- Desarrollo de la estructura principal del frontend mediante HTML.
- Implementación de estilos CSS para el menú lateral, secciones, formularios, botones, tarjetas y tablas.
- Desarrollo de la navegación entre las diferentes secciones de la plataforma.
- Integración de la búsqueda de hoteles por ciudad.
- Implementación de la consulta de habitaciones asociadas a cada hotel.
- Integración de la consulta de disponibilidad según las fechas de entrada, salida y cantidad de huéspedes.
- Conexión del registro de usuarios y del inicio y cierre de sesión.
- Implementación de la consulta y actualización del perfil del cliente.
- Integración de la creación de reservaciones con el backend.
- Incorporación de la interfaz para consultar y confirmar pagos simulados.
- Implementación del contador de tiempo disponible para completar el pago.
- Desarrollo de la pantalla de confirmación de reservaciones.
- Integración de las consultas de reservaciones actuales, historial de estancias y cancelaciones.
- Implementación de las operaciones para cancelar reservaciones.
- Desarrollo del panel administrativo para registrar, consultar, editar y desactivar hoteles.
- Implementación de la administración de habitaciones.
- Integración de la consulta general de reservaciones desde el panel administrativo.
- Incorporación del manejo de sesiones mediante cookies y solicitudes Fetch con credenciales.
- Implementación de mensajes para informar resultados, errores y validaciones de las operaciones.

---

# 2. Evidencias de trabajo

Las actividades de esta entrega se respaldan mediante 18 capturas de pantalla que documentan la ejecución de las funciones principales de TwoVago desde el navegador.

Las imágenes se encuentran en `documentacion/reportes/evidencias/`.

## 2.1. Historial de contribuciones

Los cambios realizados durante R4 se identificaron mediante los commits registrados en la rama principal del repositorio.

| Actividad | Integrante responsable | Commit |
|---|---|---|
| Solución de errores y ajustes de la versión definitiva | Emanuel Hernández Ochoa | [0a06efd](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/0a06efd) |
| Corrección de detalles en la documentación | Francisco Báez Rodríguez | [ceb89fc](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/ceb89fc) |
| Desarrollo inicial de HTML, API.js y App.js | Francisco Báez Rodríguez | [3c76362](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/3c76362) |
| Incorporación de estilos CSS y registros de IA | Francisco Báez Rodríguez | [ffaee2b](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/ffaee2b) |
| Ampliación de las funciones del frontend y conexión con la API | Francisco Báez Rodríguez | [994422f](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/994422f) |
| Continuación de la integración de reservaciones y navegación | Francisco Báez Rodríguez | [cb88420](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cb88420) |
| Integración de reservaciones y panel administrativo | Emanuel Hernández Ochoa | [e29ad67](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/e29ad67) |
| Finalización del frontend | Emanuel Hernández Ochoa | [d478f4b](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d478f4b) |

## 2.2. Evidencias de funcionamiento

| Archivo | Descripción |
|---|---|
| `E4_01_Inicio_Sesion_Administrador.png` | Acceso a la plataforma con una cuenta administrativa |
| `E4_02_Panel_Administrativo_Hoteles.png` | Panel administrativo para gestionar hoteles |
| `E4_03_Administracion_Habitaciones.png` | Administración y consulta de habitaciones |
| `E4_04_Consulta_Reservaciones_Administrador.png` | Consulta general de reservaciones desde el panel administrativo |
| `E4_05_Registro_Cliente.png` | Formulario de registro de nuevos clientes |
| `E4_06_Inicio_Sesion_Cliente.png` | Inicio de sesión de un usuario cliente |
| `E4_07_Consulta_Habitaciones.png` | Consulta de habitaciones disponibles por hotel |
| `E4_08_Consulta_Disponibilidad.png` | Consulta de disponibilidad según fechas y huéspedes |
| `E4_09_Reservacion_Creada.png` | Creación de una nueva reservación |
| `E4_10_Mis_Reservaciones_Confirmada.png` | Consulta de una reservación confirmada |
| `E4_11_Cancelacion_Reservacion.png` | Proceso de cancelación de una reservación |
| `E4_12_Consulta_Cancelaciones_Cliente.png` | Consulta de reservaciones canceladas y expiradas |
| `E4_13_Historial_Estancias_Cliente.png` | Consulta del historial de estancias del cliente |
| `E4_14_Consulta_Perfil_Cliente.png` | Consulta de los datos del perfil del usuario |
| `E4_15_Actualizacion_Perfil_Cliente.png` | Actualización de los datos personales del cliente |
| `E4_16_Reservaciones_Expiradas.png` | Visualización de reservaciones que han expirado |
| `E4_17_Manejo_Errores_Login.png` | Validación de credenciales incorrectas durante el inicio de sesión |
| `E4_18_Cierre_Sesion.png` | Cierre de sesión del usuario |

## 2.3. Integración del frontend con el backend

Se desarrolló el archivo `frontend/js/api.js` para centralizar las solicitudes HTTP realizadas desde las interfaces.

Las operaciones utilizan Fetch y el intercambio de información en formato JSON. También se configuró el envío de credenciales mediante `credentials: "include"` en las solicitudes que requieren mantener la sesión del usuario.

El archivo `frontend/js/app.js` contiene las funciones encargadas de controlar la navegación, procesar las respuestas de la API y actualizar la información mostrada en las diferentes secciones.

## 2.4. Interfaz de usuario

Se desarrolló una interfaz con un menú lateral que permite acceder a las secciones principales:

- Inicio
- Mi cuenta
- Mis reservaciones
- Historial de estancias
- Cancelaciones
- Panel administrativo, visible para el usuario con rol ADMIN

Los estilos se concentraron en `frontend/css/styles.css`, donde se definieron los colores, distribución de los elementos, formularios, botones, tarjetas y tablas. También se incorporaron ajustes para adaptar la distribución a pantallas de menor tamaño

## 2.5. Panel administrativo

Se integraron las operaciones administrativas para gestionar la información de los hoteles y sus habitaciones

El administrador puede registrar nuevos hoteles, consultar los existentes, modificar sus datos y desactivarlos También puede administrar habitaciones y consultar las reservaciones registradas en la plataforma

Estas operaciones se conectan con los endpoints REST y requieren una sesión con permisos administrativos

## 2.6. Operaciones del cliente

Desde la interfaz del cliente se integraron las funciones de registro, autenticación, consulta y actualización del perfil, búsqueda de hoteles y consulta de habitaciones

También se conectó el proceso de reservación con el módulo de pagos simulados. Una vez confirmada la operación, el cliente puede consultar sus reservaciones, revisar su historial, cancelar reservaciones y consultar aquellas que hayan expirado

---

# 3. Problemas encontrados

### Versión desactualizada del frontend

Durante las primeras pruebas se detectó que la copia local del proyecto no contenía los últimos cambios del repositorio. Esto provocaba que se mostrara una interfaz anterior y que algunas funciones de navegación no estuvieran disponibles

**Solución:** se sincronizó el repositorio mediante `git pull` y se volvió a cargar la versión actualizada del frontend

### Errores de autenticación durante las pruebas

Se presentaron respuestas HTTP 401 al intentar iniciar sesión con credenciales incorrectas. También se obtuvo HTTP 409 al intentar registrar un correo electrónico que ya existía en la base de datos.

**Solución:** se verificaron los registros de usuarios mediante consultas MySQL y se utilizaron cuentas de prueba con las credenciales correspondientes. Las respuestas de error permitieron comprobar las validaciones de autenticación y duplicidad de registros

---

# 4. Avance funcional

Durante R4 se integraron las interfaces web con los servicios del backend, permitiendo ejecutar desde el navegador las operaciones principales de TwoVago

**Funcionalidades comprobadas:**

- Visualización del catálogo de hoteles.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones.
- Consulta de disponibilidad por fechas y huéspedes.
- Registro de clientes.
- Inicio y cierre de sesión.
- Acceso diferenciado para clientes y administradores.
- Consulta y actualización del perfil.
- Administración de hoteles.
- Administración de habitaciones.
- Consulta administrativa de reservaciones.
- Creación de reservaciones.
- Consulta y confirmación de pagos simulados.
- Visualización de la confirmación de reservación.
- Consulta de reservaciones propias.
- Cancelación de reservaciones.
- Consulta del historial de estancias.
- Consulta de cancelaciones y reservaciones expiradas.
- Presentación de mensajes de error y confirmación en las interfaces.

Las operaciones anteriores se comprobaron mediante pruebas manuales desde el navegador. Las capturas E4_01 a E4_18 respaldan los flujos documentados.

**Actividades de documentación pendientes:**

- Consolidación de la documentación de R4.
- Actualización del índice general de evidencias.
- Preparación de las notas de versión R4.
- Integración de los registros individuales de uso de IA.

---

# 5. Uso de IA generativa

Durante esta etapa se utilizó ChatGPT como herramienta de apoyo para comprender errores de integración, revisar problemas de autenticación, orientar la ejecución local del sistema y organizar la documentación y las evidencias.

También se registró el uso de IA para la generación de estilos CSS del frontend, actividad documentada por Francisco Báez Rodríguez en `documentacion/IAG/integrante-3-Francisco/r-ia-dev-r4.md`.


---

# 6. Participación individual

Las contribuciones de R4 se identificaron mediante el historial de GitHub y las actividades realizadas durante la integración y comprobación del sistema.

- **Jesús Alfredo Cobos Valencia:** realizó la ejecución local del sistema, verificó los flujos de funcionamiento como administrador y cliente, preparó las 18 capturas de evidencia E4 y trabaja en la actualización de los reportes y la documentación de esta entrega. Su documentación y evidencias están pendientes de incorporación mediante un commit propio.

- **Emanuel Hernández Ochoa:** participó en las correcciones de la versión definitiva, la integración de reservaciones y el panel administrativo, así como en la finalización del frontend. Sus contribuciones se identifican en los commits `0a06ef6`, `e29ad67` y `d478f4b`.

- **Francisco Báez Rodríguez:** participó en el desarrollo de la estructura HTML, las funciones JavaScript, la conexión con la API, los estilos CSS y los ajustes de documentación. Sus contribuciones se identifican en los commits `ceb89fc`, `3c76362`, `ffaee2b`, `994422f` y `cb88420`.

---

# 7. Próximas actividades

1. Integrar los registros individuales de uso de IA generativa.
2. Preparar el video de funcionamiento general de TwoVago.
3. Realizar una revisión final de la documentación técnica y funcional.
4. Organizar los archivos y evidencias para la entrega final.

---

## Observaciones adicionales

Con R4 se completó la integración de las interfaces web de TwoVago con el backend y la base de datos. La plataforma permite realizar las operaciones principales desde el navegador, diferenciando las funciones disponibles para clientes y administradores.

Las 18 evidencias incorporadas documentan los procesos de administración, registro, autenticación, consulta de habitaciones, reservaciones, cancelaciones, historial y gestión de perfiles.

El trabajo de esta etapa deja integrado el sistema y permite concentrar las actividades posteriores en la documentación y preparación de la entrega final.