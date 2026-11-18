# TwoVago — Release R3

**Versión:** R3 — Usuarios, reservaciones y pagos simulados  
**Fecha:** 17 de noviembre de 2026  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

## 1. Descripción de la versión

Esta tercera versión de TwoVago amplía el backend desarrollado durante R2. Se incorporan los módulos de usuarios, autenticación, perfiles, reservaciones y pagos simulados.

También se implementan restricciones de acceso por rol, consulta de disponibilidad, cancelación e historial de reservaciones y expiración automática de pagos pendientes. Las funciones se comprobaron mediante una batería de pruebas automatizadas.

## 2. Elementos incluidos

### Backend
- Entidad `Usuario` y su repositorio.
- Servicio para registro, autenticación y actualización de perfiles.
- DTO para las solicitudes y respuestas de usuarios.
- Controlador REST para autenticación y perfiles.
- Sesiones HTTP mediante `HttpSession`.
- Control de acceso para clientes y administradores.
- Servicios y controladores de reservaciones y pagos.
- Tarea programada para revisar reservaciones y pagos vencidos.
- Manejo de errores y respuestas HTTP.

### Base de datos
- Actualización del esquema `twovago`.
- Tablas para usuarios, reservaciones y pagos.
- Relaciones entre usuarios, hoteles, habitaciones, reservaciones y pagos.
- Scripts SQL para actualizar la base de datos y preparar datos de prueba.
- Respaldo de la base de datos en `database/twovago.sql`.

### Endpoints REST

Se incorporaron las siguientes operaciones:

- `POST /api/auth/registro`: registrar usuario.
- `POST /api/auth/login`: iniciar sesión.
- `POST /api/auth/logout`: cerrar sesión.
- `GET /api/usuarios/perfil`: consultar perfil propio.
- `PUT /api/usuarios/perfil`: actualizar perfil.
- `GET /api/habitaciones/disponibles`: consultar habitaciones disponibles.
- `POST /api/reservaciones`: crear reservación.
- `GET /api/reservaciones/{id}`: consultar reservación propia.
- `GET /api/reservaciones/mis-reservas`: consultar reservaciones actuales.
- `GET /api/reservaciones/historial`: consultar historial.
- `GET /api/reservaciones/cancelaciones`: consultar cancelaciones y expiraciones.
- `GET /api/reservaciones/admin`: consultar reservaciones como administrador.
- `PUT /api/reservaciones/{id}/cancelar`: cancelar reservación.
- `GET /api/pagos/{id}`: consultar pago propio.
- `POST /api/pagos/{id}/confirmar`: confirmar pago simulado.

### Pruebas y ejecución
- Compilación del backend mediante Maven.
- Ejecución del backend de pruebas en el puerto 8083.
- Pruebas de registro, autenticación y sesiones.
- Pruebas de perfiles y permisos.
- Pruebas de disponibilidad y reservaciones.
- Pruebas de pagos simulados, cancelaciones y expiraciones.
- Pruebas de operaciones administrativas.
- Generación del archivo `documentacion/reportes/resultados-pruebas-e3.txt`.

La ejecución final registró 55 pruebas correctas, 0 fallidas y 0 omitidas.

### Documentación y evidencias
- Actualización del reporte de avance R3.
- Actualización de las notas de versión.
- Capturas de implementación y pruebas E3_01 a E3_15.
- Registro individual de uso de IA.
- Actualización del README y de la documentación de evidencias.

## 3. Funcionalidades implementadas

Durante esta etapa se incorporaron las siguientes funciones:

- Registro de usuarios y validación de correos.
- Inicio y cierre de sesión.
- Asignación automática del rol `CLIENTE`.
- Consulta y actualización del perfil propio.
- Restricción de operaciones administrativas para clientes.
- Administración de hoteles y habitaciones mediante el rol `ADMIN`.
- Consulta de habitaciones disponibles por fechas y capacidad.
- Creación de reservaciones y cálculo del importe desde el backend.
- Plazo de 10 minutos para completar el pago simulado.
- Confirmación de pagos simulados.
- Consulta de reservaciones y pagos propios.
- Prevención de reservaciones duplicadas.
- Cancelación e historial de reservaciones.
- Liberación de habitaciones después de una cancelación.
- Expiración automática de reservaciones y pagos vencidos.

## 4. Tecnologías utilizadas

- Java 17.
- Spring Boot 4.1.2.
- Maven.
- MySQL.
- Hibernate / JPA.
- Spring Data JPA.
- Spring Web MVC.
- Python.
- Bash.
- CURL.
- Visual Studio Code.
- WSL.
- Git y GitHub.

## 5. Contribuciones del equipo

### Jesús Alfredo Cobos Valencia
- Incorporación de la entidad, repositorio, servicio, DTO y controlador inicial de usuarios.
- Desarrollo e integración de componentes de perfiles, sesiones, reservaciones y pagos.
- Ajustes de validaciones y controladores del backend.

### Emanuel Hernández Ochoa
- Actualización del reporte R3 y del registro individual de IA.
- Ajustes finales del servicio de usuarios y configuración del backend.
- Actualización del respaldo SQL y de los resultados de pruebas.

### Francisco Báez Rodríguez
- Integración de evidencias de R3.
- Actualización de documentación y resultados de pruebas.
- Actualización de los registros de IA.

### Trabajo colaborativo

Los tres integrantes dieron seguimiento a los cambios del repositorio y a la integración de los módulos del backend. Las contribuciones de esta versión se encuentran registradas en los commits de la rama principal.

## 6. Limitaciones conocidas

En esta versión:

- Las interfaces del frontend todavía no están integradas con el backend.
- Las pruebas de los flujos completos desde el navegador están pendientes.
- Los pagos son simulados y no procesan transacciones bancarias reales.
- La versión final conserva el valor recibido en el campo `password_hash` y lo compara directamente durante el inicio de sesión. No se utiliza BCrypt en esta versión.
- La cookie de sesión está configurada para el entorno local de desarrollo; su configuración debe revisarse al desplegar el sistema con HTTPS.

## 7. Próxima versión

La siguiente etapa estará enfocada en desarrollar e integrar el frontend de TwoVago:

1. Desarrollar las pantallas de registro e inicio de sesión.
2. Conectar el frontend con los endpoints mediante Fetch.
3. Configurar el envío de cookies para mantener las sesiones HTTP.
4. Integrar la búsqueda de hoteles y consulta de habitaciones.
5. Conectar el proceso de reservación con los pagos simulados.
6. Implementar las vistas de perfil, reservaciones e historial.
7. Probar el flujo completo desde el navegador.

---

**Estado de la versión:** Backend de usuarios, autenticación, perfiles, disponibilidad, reservaciones y pagos simulados implementado y probado. La integración del frontend queda pendiente.
