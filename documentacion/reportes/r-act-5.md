# Reporte de Avance del Proyecto

**Equipo:** TwoVago

**Fecha:** 22 de noviembre de 2026

**Número de reporte:** R5 — Pruebas funcionales y validación del sistema

**Repositorio GitHub:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas

Durante esta quinta entrega se realizaron pruebas funcionales de la plataforma TwoVago para comprobar el funcionamiento de los módulos desarrollados e integrados durante las entregas anteriores. Las pruebas se ejecutaron desde el navegador, verificando la comunicación entre el frontend, el backend y la base de datos MySQL.

Las actividades realizadas fueron las siguientes:

- Prueba del registro de nuevos clientes y comprobación de la información almacenada en MySQL.
- Verificación del inicio de sesión con una cuenta registrada.
- Consulta de los datos del perfil del cliente.
- Actualización de la información personal del usuario.
- Prueba de búsqueda de hoteles mediante el filtro por ciudad.
- Consulta de las habitaciones asociadas a cada hotel.
- Verificación de disponibilidad de habitaciones según las fechas de entrada, salida y cantidad de huéspedes.
- Comprobación de la creación de reservaciones.
- Verificación de la pantalla de pago y del estado pendiente de una reservación.
- Prueba de confirmación de pagos simulados.
- Visualización de los datos de una reservación confirmada.
- Consulta de las reservaciones registradas por el cliente.
- Verificación del procedimiento de cancelación de reservaciones.
- Consulta de los registros de reservaciones canceladas.
- Consulta del historial de estancias.
- Verificación del control de cierre de sesión.
- Prueba de inicio de sesión utilizando credenciales incorrectas.
- Validación del registro de usuarios utilizando un correo electrónico previamente registrado.
- Comprobación del acceso al perfil sin iniciar sesión.
- Comprobación del acceso a las reservaciones sin iniciar sesión.
- Comprobación del acceso al historial de estancias sin iniciar sesión.
- Comprobación del acceso a las cancelaciones sin iniciar sesión.

---

# 2. Evidencias de trabajo

Esta entrega se respaldan mediante 20 capturas de pantalla que documentan las pruebas funcionales realizadas desde el navegador.

Las imágenes se encuentran en `documentacion/reportes/evidencias/`.

## 2.1. Historial de contribuciones

Los cambios realizados durante R5 se identificaron mediante los commits registrados en la rama principal del repositorio.

| Actividad | Integrante responsable | Commit |
|---|---|---|
| Incorporación de evidencias de pruebas funcionales R5 | Jesús Alfredo Cobos Valencia | [fe3c214](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/fe3c214) |
| Incorporación de evidencias faltantes de pruebas funcionales R5 | Francisco Báez Rodríguez | [658b4c4](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/658b4c4) |
| Corrección de detalles R5 | Francisco Báez Rodríguez | [77a8449](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/77a8449) |
| Documentación final y cierre de entrega R5 | Emanuel Hernández Ochoa | [dc284e6](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/dc284e6) |

## 2.2. Evidencias de funcionamiento

| Archivo | Descripción |
|---|---|
| `E5_01_Registro_Cliente.png` | Registro exitoso de un nuevo cliente y comprobación de su existencia en la base de datos MySQL. |
| `E5_02_Inicio_Sesion_Cliente.png` | Inicio de sesión de un usuario cliente con una cuenta registrada. |
| `E5_03_Consulta_Perfil_Cliente.png` | Consulta correcta de los datos personales del cliente. |
| `E5_04_Actualizacion_Perfil_Cliente.png` | Actualización de los datos personales del usuario. |
| `E5_05_Busqueda_Hoteles_Ciudad.png` | Búsqueda de hoteles mediante el filtro por ciudad. |
| `E5_06_Consulta_Habitaciones.png` | Consulta de las habitaciones asociadas a un hotel, mostrando tipo, capacidad y precio por noche. |
| `E5_07_Consulta_Disponibilidad.png` | Consulta de habitaciones disponibles según fechas de entrada, salida y cantidad de huéspedes. |
| `E5_08_Reservacion_Creada.png` | Creación de una reservación y visualización de la pantalla de pago con estado pendiente. |
| `E5_09_Pago_Confirmado.png` | Confirmación del pago simulado y presentación de los datos de la reservación confirmada. |
| `E5_10_Mis_Reservaciones.png` | Consulta de las reservaciones registradas por el cliente. |
| `E5_11_Cancelacion_Reservacion.png` | Presentación del mensaje de confirmación previo a cancelar una reservación. |
| `E5_12_Consulta_Cancelaciones.png` | Consulta de los registros de reservaciones canceladas. |
| `E5_13_Historial_Estancias.png` | Consulta del historial de estancias y visualización del mensaje cuando no existen estancias completadas. |
| `E5_14_Cierre_Sesion.png` | Visualización de la interfaz principal y del control para cerrar sesión. |
| `E5_15_Error_Inicio_Sesion.png` | Validación de credenciales incorrectas durante el inicio de sesión. |
| `E5_16_Error_Registro_Duplicado.png` | Validación del registro cuando el correo electrónico ya se encuentra registrado. |
| `E5_17_Acceso_Perfil_Sin_Sesion.png` | Comprobación del aviso de inicio de sesión requerido al consultar el perfil sin autenticación. |
| `E5_18_Acceso_Reservaciones_Sin_Sesion.png` | Comprobación del acceso a reservaciones sin una sesión iniciada. |
| `E5_19_Acceso_Historial_Sin_Sesion.png` | Comprobación del acceso al historial de estancias sin una sesión iniciada. |
| `E5_20_Acceso_Cancelaciones_Sin_Sesion.png` | Comprobación del acceso a cancelaciones sin una sesión iniciada. |

## 2.3. Registro, autenticación y perfil de usuario

Se realizaron pruebas para comprobar el funcionamiento de las operaciones relacionadas con las cuentas de clientes.

Se verificó el registro de un nuevo usuario desde la interfaz y posteriormente se comprobó mediante una consulta en MySQL que la información se encontraba almacenada en la tabla `usuarios`.

También se comprobó el inicio de sesión utilizando una cuenta registrada, así como la consulta y actualización de los datos personales del cliente.

Las capturas E5_01 a E5_04 documentan estas operaciones.

## 2.4. Búsqueda de hoteles y disponibilidad

Se comprobó el funcionamiento del buscador de hoteles mediante la introducción de una ciudad como criterio de búsqueda.

Después se consultaron las habitaciones asociadas al hotel encontrado, verificando la información correspondiente a cada una, incluyendo su tipo, capacidad y precio por noche.

También se realizó una consulta de disponibilidad indicando las fechas de entrada y salida y el número de huéspedes. La interfaz mostró las habitaciones que cumplían con los criterios establecidos.

Estas operaciones se documentaron mediante las capturas E5_05, E5_06 y E5_07.

## 2.5. Reservaciones, pagos y cancelaciones

Se realizaron pruebas del proceso de reservación desde la selección de una habitación hasta la presentación de la pantalla de pago.

Se verificó que una reservación recién creada apareciera con estado pendiente de pago y que posteriormente pudiera realizarse la confirmación del pago simulado.

También se comprobó la presentación de los datos de la reservación confirmada y la consulta de las reservaciones registradas por el cliente.

En el proceso de cancelación se verificó la presentación del mensaje de confirmación antes de ejecutar la operación. Posteriormente se consultaron los registros de cancelaciones, comprobando que aparecieran las reservaciones con estado CANCELADA.

Las capturas E5_08 a E5_12 documentan estas operaciones.

## 2.6. Historial, cierre de sesión y validaciones de acceso

Se comprobó el funcionamiento de la consulta del historial de estancias. Durante esta prueba, el sistema mostró el mensaje indicando que no existían estancias completadas para el usuario.

También se revisó la ubicación del control de cierre de sesión en la interfaz principal.

Adicionalmente, se realizaron pruebas de validación utilizando credenciales incorrectas y tratando de registrar una cuenta con un correo electrónico previamente existente.

Finalmente, se comprobó el comportamiento de las secciones protegidas cuando el usuario no ha iniciado sesión. En las cuatro pruebas se mostró el mensaje «Debes iniciar sesión» y no se presentaron registros personales.

Las capturas E5_13 a E5_20 respaldan estas comprobaciones.

---

# 3. Problemas encontrados

### Persistencia de mensajes entre secciones

Durante las pruebas se observó que algunos mensajes de operaciones anteriores permanecen visibles al navegar entre diferentes secciones de la plataforma.

Por ejemplo, el mensaje «Reservación confirmada correctamente» continúa apareciendo en algunas pantallas posteriores a la confirmación del pago.

**Observación:** aunque esto no impide consultar las diferentes secciones, puede generar confusión al usuario porque el mensaje no necesariamente corresponde a la operación que está realizando en ese momento.

### Visualización de secciones sin iniciar sesión

Al ingresar a las secciones de perfil, reservaciones, historial y cancelaciones sin una sesión iniciada, el sistema muestra el mensaje «Debes iniciar sesión».

Sin embargo, algunos elementos de la interfaz, como botones y encabezados de tablas, permanecen visibles.

**Observación:** las capturas muestran que no se presentan registros personales y que se informa al usuario sobre la necesidad de autenticarse. Se identifica como un aspecto de presentación que puede revisarse para mejorar el comportamiento de las secciones protegidas.

### Evidencia del cierre de sesión

La captura E5_14 muestra la interfaz principal y el botón «Cerrar sesión», pero no presenta una pantalla posterior a la ejecución de esa operación.

**Observación:** la imagen permite identificar el control disponible, aunque por sí sola no demuestra el resultado del cierre de sesión.

---

# 4. Avance funcional

Durante R5 se realizaron pruebas manuales para comprobar el funcionamiento de los módulos principales de TwoVago desde el navegador.

**Funcionalidades comprobadas:**

- Registro de nuevos clientes.
- Comprobación de registros en MySQL.
- Inicio de sesión de clientes.
- Consulta del perfil.
- Actualización de datos personales.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones.
- Visualización de precios y capacidades.
- Consulta de disponibilidad por fechas y huéspedes.
- Creación de reservaciones.
- Consulta del estado de pago.
- Confirmación de pagos simulados.
- Visualización de reservaciones confirmadas.
- Consulta de reservaciones propias.
- Inicio del proceso de cancelación.
- Consulta de reservaciones canceladas.
- Consulta del historial de estancias.
- Validación de credenciales incorrectas.
- Validación de correos electrónicos duplicados.
- Comprobación de restricciones de acceso sin sesión iniciada.

Las operaciones anteriores se comprobaron mediante pruebas manuales desde el navegador. Las capturas E5_01 a E5_20 respaldan los flujos documentados.

---

# 5. Uso de IA generativa
Se utilizó chatGPT como apoyo para identificar la estructura de la tabla de usuarios, comprender las respuestas de autenticación. Las pruebas fueron ejecutadas y comprobadas manualmente desde el navegador y mediante consultas en MySQL.

El registro individual de las consultas realizadas durante esta etapa deberá incorporarse a los archivos correspondientes de `documentacion/IAG/`.

---

# 6. Participación individual

Las contribuciones de R5 se identificaron mediante el historial de GitHub y las actividades relacionadas con las pruebas funcionales y la documentación de la entrega.

- **Jesús Alfredo Cobos Valencia:** realizó pruebas funcionales de los módulos de registro, autenticación, perfiles, búsqueda de hoteles, habitaciones, disponibilidad, reservaciones, pagos, cancelaciones y validaciones. Incorporó las primeras 16 capturas de evidencia E5 mediante el commit `fe3c214`.

- **Emanuel Hernández Ochoa:** se encargo de la elaboración del reporte de actividades R5 y de la integración, revisión y organización de la documentación correspondiente a esta entrega, commit `dc284e6`.

- **Francisco Báez Rodríguez:** realizó pruebas adicionales de acceso a las secciones protegidas sin una sesión iniciada e incorporó las evidencias E5_17 a E5_20 mediante el commit `658b4c4`.

---

# 7. Próximas actividades

1. Realizar una revisión final de la documentación técnica y funcional.
2. Preparar el video de funcionamiento general de TwoVago y organizar los archivos para la entrega final.

---

## Observaciones adicionales

Con R5 se documentaron las pruebas funcionales de la plataforma TwoVago, verificando los principales procesos disponibles para los clientes, desde el registro y autenticación hasta la creación, confirmación y cancelación de reservaciones.

Las 20 evidencias incorporadas permiten identificar los resultados de las operaciones realizadas y el comportamiento de la aplicación ante diferentes situaciones, incluyendo errores de autenticación y accesos sin una sesión iniciada.

Esta entrega complementa el trabajo de integración realizado durante R4 y permite concentrar las actividades posteriores en la consolidación de la documentación y preparación de la entrega final.