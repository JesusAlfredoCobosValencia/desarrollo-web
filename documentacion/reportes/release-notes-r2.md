# TwoVago — Release R2

**Versión:** R2 — Backend basico y base de datos 
**Fecha:** 13 de noviembre de 2026  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

## 1. Descripción de la versión

Esta segunda versión de TwoVago corresponde a la etapa de implementación inicial del backend y conexión con la base de datos.

A partir del análisis y diseño realizados durante la primera entrega, se comenzó el desarrollo de la estructura técnica de la aplicación utilizando Java, Spring Boot, Maven y MySQL.

Se creó la base de datos del proyecto, se configuró la conexión entre Spring Boot y MySQL y se implementaron las primeras entidades, repositorios, servicios y controladores REST.

También se desarrollaron y probaron operaciones GET y POST para la gestión inicial de hoteles y habitaciones, verificando las respuestas del servidor mediante CURL y el navegador.

Esta versión representa el inicio de la implementación funcional de TwoVago. Los módulos restantes continúan en desarrollo.

## 2. Elementos incluidos

### Backend
- Proyecto Spring Boot generado y configurado.
- Archivo `pom.xml` con las dependencias necesarias.
- Configuración de Java y Maven.
- Estructura inicial del backend.
- Implementación de la arquitectura por capas.
- Controladores REST.
- Servicios para la gestión de información.
- Repositorios mediante Spring Data JPA.

### Base de datos
- Creación de la base de datos MySQL `twovago`.
- Configuración del usuario de aplicación y permisos.
- Conexión entre Spring Boot y MySQL.
- Configuración de Hibernate y JPA.
- Creación de las entidades Hotel y Habitación.
- Generación de las tablas correspondientes.
- Definición de la relación entre hoteles y habitaciones.
- Verificación de registros almacenados.

### Endpoints REST
Se implementaron las primeras operaciones de consulta y registro:

- GET para consultar hoteles.
- POST para registrar hoteles.
- GET para consultar habitaciones.
- GET para consultar una habitación por identificador.
- Consulta de habitaciones relacionadas con un hotel mediante su identificador.

Las operaciones se probaron mediante solicitudes HTTP y CURL.

### Pruebas y ejecución
- Compilación del proyecto mediante Maven.
- Verificación de compilación correcta (`BUILD SUCCESS`).
- Inicio de Spring Boot.
- Ejecución del servidor Tomcat en el puerto 8082.
- Comprobación del endpoint `/api/health`.
- Pruebas de consulta de hoteles y habitaciones.
- Registro de hoteles mediante solicitudes POST.
- Verificación de respuestas JSON.
- Consultas directas a MySQL para comprobar la persistencia de los datos.

### Documentación y evidencias
- Actualización del reporte de avance R2.
- Carpeta de evidencias visuales de la segunda entrega.
- Capturas de configuración de Maven y Spring Boot.
- Evidencias de conexión con MySQL.
- Capturas de entidades, repositorios, servicios y controladores.
- Pruebas de endpoints mediante CURL.
- Evidencias de compilación y ejecución del backend.

## 3. Funcionalidades implementadas

Durante esta etapa se desarrollaron las primeras funcionalidades técnicas de TwoVago:

- Conexión funcional entre el backend y MySQL.
- Registro de información de hoteles.
- Consulta de hoteles registrados.
- Consulta de habitaciones.
- Consulta individual de habitaciones.
- Relación entre hoteles y habitaciones.
- Persistencia de información mediante Hibernate y JPA.
- Respuestas de los endpoints en formato JSON.

Estas funcionalidades corresponden a la implementación inicial del sistema.

## 4. Tecnologías utilizadas

- Java 17.
- Spring Boot 4.1.2.
- Maven.
- MySQL.
- Hibernate / JPA.
- Spring Data JPA.
- Spring Web.
- CURL.
- Visual Studio Code.
- WSL.
- Git y GitHub.

## 5. Contribuciones del equipo

### Jesús Alfredo Cobos Valencia
- Creación de la estructura inicial del repositorio.
- Análisis y definición de los requerimientos de TwoVago.
- Diseño del modelo de datos y diagrama entidad-relación.
- Definición de endpoints REST.
- Elaboración del diagrama de arquitectura de tres capas.
- Distribución de responsabilidades del equipo.
- Organización de la documentación de IA generativa.

### Emanuel Hernández Ochoa
- Desarrollo de la estructura inicial del backend.
- Configuración del proyecto Spring Boot y Maven.
- Configuración de la conexión con MySQL.
- Creación e implementación de las entidades Hotel y Habitación.
- Desarrollo de repositorios y servicios.
- Implementación de controladores REST.
- Desarrollo de operaciones GET y POST.
- Pruebas de funcionamiento mediante CURL.
- Verificación de la persistencia de información en MySQL.

### Francisco Báez Rodríguez
- Diseño y documentación de interfaces y navegación.
- Actualización de documentación general y diagramas.
- Participación en la organización de los entregables.
- Integración y seguimiento de los cambios del proyecto mediante Git.

### Trabajo colaborativo

Los tres integrantes continuaron trabajando sobre la estructura definida durante R1, dando seguimiento a sus respectivas responsabilidades y a la integración de los componentes del proyecto.

## 6. Limitaciones conocidas

En esta versión:

- El backend cuenta con una implementación inicial enfocada en hoteles y habitaciones.
- Todavía no se han implementado todas las operaciones CRUD.
- Las operaciones PUT y DELETE quedan pendientes para la siguiente entrega.
- El registro e inicio de sesión de usuarios todavía no están implementados.
- Los módulos de reservaciones y pagos simulados continúan pendientes de integración.
- No se ha implementado el temporizador de 10 minutos para completar los pagos.
- La cancelación y expiración automática de reservaciones todavía no están implementadas.
- El manejo completo de errores y validaciones requiere trabajo adicional.
- La integración completa con el frontend continúa pendiente.

## 7. Próxima versión

La siguiente etapa estará enfocada en ampliar la API REST y continuar con la integración de los módulos de TwoVago:

1. Implementar operaciones PUT y DELETE.
2. Incorporar validaciones de datos.
3. Mejorar el manejo de errores y respuestas HTTP.
4. Ampliar las consultas y filtros de hoteles y habitaciones.
5. Desarrollar los módulos de usuarios y autenticación.
6. Implementar las operaciones de reservaciones.
7. Comenzar la integración de pagos simulados.
8. Continuar con la conexión entre backend y frontend.
9. Realizar pruebas de funcionamiento e integración.

---

**Estado de la versión:** Backend inicial implementado. Base de datos MySQL conectada. Entidades, repositorios, servicios y controladores desarrollados. Operaciones GET y POST iniciales verificadas. Desarrollo de los módulos restantes en proceso.