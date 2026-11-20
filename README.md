# TwoVago
Proyecto final de Desarrollo Web.

## Descripción
TwoVago es una plataforma web para la búsqueda y reservación de alojamientos. Permite consultar hoteles, visualizar habitaciones disponibles, registrar usuarios, administrar perfiles y realizar reservaciones. También incluye funciones de administración para gestionar hoteles, habitaciones y reservaciones.

## Integrantes
- Jesús Alfredo Cobos Valencia
- Emanuel Hernández Ochoa
- Francisco Báez Rodríguez

## Tecnologías
- Java 17.
- Spring Boot.
- Maven.
- MySQL.
- HTML5.
- CSS.
- JavaScript.
- API REST mediante Fetch.
- Git y GitHub.
- WSL.

## Estructura del proyecto
- `backend/`: API REST, controladores, servicios, entidades, configuración y lógica del sistema.
- `frontend/`: interfaces web, estilos y funciones JavaScript.
- `database/`: script SQL y archivos relacionados con la base de datos.
- `documentacion/`: análisis, diseño, diagramas, endpoints, reportes, evidencias y documentación de las entregas.
- `scripts/`: scripts auxiliares del proyecto.

## Funcionalidades implementadas

### Usuarios
- Registro de clientes.
- Inicio y cierre de sesión.
- Consulta y actualización del perfil.
- Validación de credenciales.
- Control de sesiones.

### Hoteles y habitaciones
- Consulta del catálogo de hoteles.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones por hotel.
- Visualización de disponibilidad.
- Administración de hoteles y habitaciones.

### Reservaciones
- Creación y confirmación de reservaciones.
- Consulta de reservaciones del usuario.
- Consulta del historial de estancias.
- Cancelación de reservaciones.
- Identificación de reservaciones expiradas.
- Cálculo del importe de las reservaciones.

### Administración
- Panel administrativo.
- Registro y edición de hoteles.
- Activación y desactivación de hoteles.
- Administración de habitaciones.
- Consulta de reservaciones de la plataforma.

## Base de datos
El sistema utiliza MySQL para almacenar la información de usuarios, hoteles, habitaciones y reservaciones.

La conexión se configura mediante `backend/src/main/resources/application.properties`.

La contraseña de MySQL se proporciona mediante la variable de entorno `TWOVAGO_DB_PASSWORD`, evitando almacenar credenciales directamente en el repositorio.

El script de la base de datos se encuentra en `database/twovago.sql`.

## Ejecución del proyecto

### Backend
El backend utiliza Spring Boot y se ejecuta en el puerto 8082.

Desde la carpeta `backend`, ejecutar:

```bash
mvn spring-boot:run
```

Es necesario tener MySQL iniciado y la variable de entorno `TWOVAGO_DB_PASSWORD` configurada.

### Frontend
El frontend se ejecuta mediante un servidor HTTP local en el puerto 3000.

La interfaz principal se encuentra en:

`frontend/index.html`

La aplicación se comunica con el backend mediante las peticiones HTTP a la API REST.

### Direcciones locales
- Frontend: `http://localhost:3000/frontend/index.html`
- Backend: `http://localhost:8082`
- API de hoteles: `http://localhost:8082/api/hoteles`

## Documentación y evidencias
La carpeta `documentacion/` contiene los materiales del desarrollo del proyecto:

- Análisis del sistema.
- Modelo de datos.
- Diagrama entidad-relación.
- Diseño de interfaces.
- Flujo de reservaciones.
- Documentación de endpoints.
- Arquitectura del sistema.
- Responsabilidades de los integrantes.
- Reportes de actividades.
- Evidencias de implementación y pruebas.

## Estado del proyecto
El proyecto cuenta con una API REST integrada con MySQL y un frontend funcional conectado al backend.

Se encuentran implementados los módulos de usuarios, hoteles, habitaciones, reservaciones y administración, documentados mediante reportes y evidencias correspondientes a las distintas entregas.