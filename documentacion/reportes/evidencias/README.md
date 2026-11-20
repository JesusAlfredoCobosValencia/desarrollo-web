# Evidencias del proyecto TwoVago

**Entregables:** R2 — Backend básico y base de datos; R3 — Usuarios, reservaciones y pagos simulados; R4 — Frontend e integración del sistema. 
**Equipo:** TwoVago  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

## 1. Objetivo

Esta carpeta contiene las evidencias visuales de las actividades realizadas durante los entregables R2, R3 y R4 del proyecto TwoVago.

Las capturas permiten comprobar la configuración de Spring Boot, la conexión con MySQL, la creación de entidades y componentes de acceso a datos, la implementación de usuarios y reservaciones, así como las pruebas realizadas mediante Maven, CURL y los scripts automatizados.

## 2. Relación de evidencias

### Evidencias R2

| Archivo | Descripción |
|---|---|
| `E2_01_Creacion_BD_usuario_permisos.jpg` | Creación de la base de datos MySQL `twovago`, configuración del usuario de aplicación y asignación de permisos. |
| `E2_02_Hibernate_creacion_tablas.png` | Generación de las tablas de hoteles y habitaciones mediante Hibernate y JPA. |
| `E2_03_CURL_GET_hoteles_y_POST_registro_201.png` | Pruebas CURL de consulta GET y registro POST de hoteles. |
| `E2_04_GET_hoteles_JSON_navegador.png` | Consulta del catálogo de hoteles y visualización de respuestas JSON. |
| `E2_05_GET_habitaciones_por_hotel.png` | Consulta de habitaciones asociadas a un hotel. |
| `E2_06_GET_habitacion_por_id.png` | Consulta individual de una habitación. |
| `E2_07_CURL_filtro_ciudad_y_correccion_habitacion.png` | Pruebas del filtro de hoteles por ciudad y consulta de habitaciones. |
| `E2_08_Consulta_recurso_inexistente_HTTP500.png` | Incidencia inicial al consultar un hotel inexistente. |
| `E2_09_Archivo Pom.png` | Configuración del archivo `pom.xml`. |
| `E2_10_ConexionMySQL_y_puerto_8082.png` | Configuración de la conexión con MySQL y del puerto 8082. |
| `E2_11_Entidad_Hotel_y_habitaciones.png` | Entidades Hotel y Habitación. |
| `E2_12_Repository_Hotel_y_habitaciones.png` | Repositorios de hoteles y habitaciones. |
| `E2_13_Service_Hotel_y_habitaciones.png` | Servicios de hoteles y habitaciones. |
| `E2_14_Controller_Hotel_y_habitaciones.png` | Controladores REST y endpoints GET y POST. |
| `E2_15_Compilacion_maven.png` | Compilación del backend mediante Maven. |
| `E2_16_Spring_Boot.png` | Ejecución del servidor Spring Boot en el puerto 8082. |
| `E2_17_Dase_Datos.png` | Consulta de tablas y registros mediante MySQL. |

### Evidencias R3

| Archivo | Descripción |
|---|---|
| `E3_01_Entidad_Usuario.png` | Entidad Usuario y sus atributos. |
| `E3_02_Compilacion_Usuario.png` | Compilación de los componentes del módulo de usuarios. |
| `E3_03_UsuarioRepository.png` | Repositorio de usuarios. |
| `E3_04_01_Dependencia_BCrypt.png` | Configuración inicial de BCrypt durante el desarrollo. |
| `E3_04_02_Compilacion_BCrypt.png` | Compilación de la configuración inicial de BCrypt. |
| `E3_04_03_UsuarioService.png` | Servicio de usuarios, validaciones y asignación de roles. |
| `E3_05_01_DTO_Registro.png` | DTO para recibir los datos de registro. |
| `E3_05_02_DTO_Respuesta.png` | DTO de respuesta de la API. |
| `E3_05_03_Compilacion.png` | Compilación de los componentes de registro. |
| `E3_06_01_UsuarioController.png` | Controlador REST de usuarios. |
| `E3_07_Registro_Login_Cierre.png` | Registro, inicio y cierre de sesión. |
| `E3_08_Perfil_Consulta_Actualizacion.png` | Consulta y actualización del perfil. |
| `E3_09_Control_Acceso_Roles.png` | Permisos y restricciones según el rol. |
| `E3_10_Consulta_Habitaciones_Disponibilidad.png` | Consulta de habitaciones disponibles y validación de fechas. |
| `E3_11_Creacion_Reservaciones.png` | Creación de reservaciones y cálculo de importes. |
| `E3_12_Validacion_Procesamiento_Pagos.png` | Validación y confirmación de pagos simulados. |
| `E3_13_Expiracion_Reservacion.png` | Expiración automática de reservaciones y pagos. |
| `E3_14_Cancelacion_Historial.png` | Cancelación, historial y liberación de habitaciones. |
| `E3_15_Resultados_Pruebas.png` | Resumen final de las pruebas automatizadas. |

## 3. Organización

Las capturas se encuentran almacenadas en esta carpeta y utilizan la nomenclatura E2 y E3 para identificar el entregable al que pertenecen.

Cada imagen respalda una actividad específica del desarrollo del proyecto. Las evidencias E3_13 y E3_14 corresponden, respectivamente, a la expiración automática y a la cancelación e historial de reservaciones.

## 4. Tecnologías utilizadas

- Java 17.
- Spring Boot.
- Maven.
- MySQL.
- Hibernate / JPA.
- CURL.
- Python y Bash.
- Visual Studio Code.
- WSL.
- Git y GitHub.

## 5. Observaciones

Las evidencias E2 corresponden al desarrollo inicial del backend y la conexión con MySQL. Las evidencias E3 documentan el registro y autenticación de usuarios, perfiles, permisos, disponibilidad, reservaciones, pagos simulados, cancelaciones y expiraciones.

Las capturas E3_04_01 y E3_04_02 muestran una configuración inicial de BCrypt que se probó durante el desarrollo y se retiró en la versión final.

La ejecución final de las pruebas automatizadas registró 55 pruebas correctas, 0 fallidas y 0 omitidas. El reporte se encuentra en `../resultados-pruebas-e3.txt`.

## 6. Reporte relacionado

Los reportes correspondientes a las evidencias se encuentran en:

- `../r-act-2.md`
- `../r-act-3.md`

Las notas de versión se encuentran en:

- `../release-notes-r2.md`
- `../release-notes-r3.md`

---

Proyecto: TwoVago  
Versión: R2 — Backend básico y base de datos; R3 — Usuarios, reservaciones y pagos simulados

## 7. Evidencias R3 incorporadas

- E3_07: registro, inicio y cierre de sesión.
- E3_08: consulta y actualización del perfil.
- E3_09: control de acceso por rol.
- E3_10: consulta de habitaciones disponibles por fechas.
- E3_11: creación de reservaciones y cálculo de importes.
- E3_12: validación y procesamiento de pagos simulados.
- E3_13: expiración automática de reservaciones y pagos.
- E3_14: cancelación, historial y liberación de habitaciones.
- E3_15: resultados de las pruebas automatizadas.


## 8. Evidencias R4 — Frontend e integración

Las siguientes capturas documentan las pruebas funcionales realizadas desde el navegador durante la integración del frontend con el backend.

| Archivo | Descripción |
|---|---|
| `E4_01_Inicio_Sesion_Administrador.png` | Inicio de sesión con cuenta administrativa. |
| `E4_02_Panel_Administrativo_Hoteles.png` | Panel de administración de hoteles. |
| `E4_03_Administracion_Habitaciones.png` | Administración de habitaciones. |
| `E4_04_Consulta_Reservaciones_Administrador.png` | Consulta de reservaciones desde el panel administrativo. |
| `E4_05_Registro_Cliente.png` | Registro de una cuenta de cliente. |
| `E4_06_Inicio_Sesion_Cliente.png` | Inicio de sesión de un cliente. |
| `E4_07_Consulta_Habitaciones.png` | Consulta de habitaciones disponibles. |
| `E4_08_Consulta_Disponibilidad.png` | Consulta de disponibilidad según fechas y huéspedes. |
| `E4_09_Reservacion_Creada.png` | Creación de una reservación. |
| `E4_10_Mis_Reservaciones_Confirmada.png` | Consulta de una reservación confirmada. |
| `E4_11_Cancelacion_Reservacion.png` | Cancelación de una reservación. |
| `E4_12_Consulta_Cancelaciones_Cliente.png` | Consulta de cancelaciones del cliente. |
| `E4_13_Historial_Estancias_Cliente.png` | Historial de estancias del cliente. |
| `E4_14_Consulta_Perfil_Cliente.png` | Consulta de información del perfil. |
| `E4_15_Actualizacion_Perfil_Cliente.png` | Actualización de datos personales. |
| `E4_16_Reservaciones_Expiradas.png` | Consulta de reservaciones expiradas. |
| `E4_17_Manejo_Errores_Login.png` | Validación de errores de autenticación. |
| `E4_18_Cierre_Sesion.png` | Cierre de sesión del cliente. |

## 9. Reporte relacionado

El reporte correspondiente a esta entrega se encuentra en:

- `../r-act-4.md`

Las evidencias R4 complementan los registros de las entregas anteriores y documentan la integración y funcionamiento de las interfaces de TwoVago