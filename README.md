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
- Spring Boot 4.1.2.
- Maven.
- MySQL 8.
- Hibernate / JPA.
- HTML5.
- CSS.
- JavaScript.
- Fetch API.
- JSON.
- Python 3 para el servidor local del frontend.
- Git y GitHub.
- WSL.
## Estructura del proyecto
- `backend/`: API REST, controladores, servicios, entidades, repositorios, configuración y lógica del sistema.
- `frontend/`: interfaces web, estilos y funciones JavaScript.
- `database/`: scripts SQL, estructura de la base de datos y datos de prueba.
- `documentacion/`: análisis, diseño, diagramas, endpoints, reportes, evidencias y documentación de las entregas.
- `scripts/`: scripts auxiliares para validaciones y pruebas.
## Funcionalidades implementadas
### Usuarios
- Registro de clientes.
- Inicio y cierre de sesión.
- Consulta y actualización del perfil.
- Validación de credenciales.
- Control de sesiones mediante cookies HTTP.
- Restricción de acceso según el rol del usuario.
### Hoteles y habitaciones
- Consulta del catálogo de hoteles.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones por hotel.
- Consulta de disponibilidad según fechas y huéspedes.
- Registro y edición de hoteles.
- Activación y desactivación de hoteles.
- Administración de habitaciones.
### Reservaciones
- Creación de reservaciones.
- Generación de folios.
- Cálculo del importe según las noches y el precio de la habitación.
- Consulta de reservaciones del usuario.
- Consulta del historial de estancias.
- Cancelación de reservaciones.
- Identificación de reservaciones expiradas.
- Liberación de habitaciones cuando una reservación se cancela o expira.
### Pagos
- Consulta del pago asociado a una reservación.
- Confirmación de pagos simulados.
- Selección de métodos de pago simulados.
- Contador de tiempo para realizar el pago.
- Expiración automática de reservaciones cuando vence el plazo.
### Administración
- Panel administrativo.
- Registro y edición de hoteles.
- Activación y desactivación de hoteles.
- Administración de habitaciones.
- Consulta general de reservaciones de la plataforma.
## Requisitos previos
Para ejecutar el proyecto se necesita:
- WSL con Ubuntu.
- Java JDK 17.
- Maven.
- MySQL 8.
- Python 3.
- Git.
## Base de datos
El sistema utiliza MySQL para almacenar la información de usuarios, hoteles, habitaciones, reservaciones y pagos.
La configuración de conexión se encuentra en:
`backend/src/main/resources/application.properties`
Los parámetros configurados son:
- Base de datos: `twovago`.
- Host: `127.0.0.1`.
- Puerto: `3306`.
- Usuario: `twovago_app`.
- Contraseña: `tv12345`.
La contraseña también se proporciona al backend mediante la variable de entorno `TWOVAGO_DB_PASSWORD`.
### Configuración inicial de MySQL
Iniciar el servicio:
```bash
sudo service mysql start
```
Ingresar a MySQL como administrador:
```bash
sudo mysql
```
Crear la base de datos y configurar el usuario de aplicación:
```sql
CREATE DATABASE IF NOT EXISTS twovago
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'twovago_app'@'localhost'
IDENTIFIED BY 'tv12345';

GRANT ALL PRIVILEGES ON twovago.*
TO 'twovago_app'@'localhost';

CREATE USER IF NOT EXISTS 'twovago_app'@'%'
IDENTIFIED WITH caching_sha2_password
BY 'tv12345';

ALTER USER 'twovago_app'@'%'
IDENTIFIED WITH caching_sha2_password
BY 'tv12345';

GRANT ALL PRIVILEGES ON twovago.*
TO 'twovago_app'@'%';

FLUSH PRIVILEGES;
EXIT;
```
### Importación de la base de datos
Desde la carpeta principal del proyecto ejecutar:
```bash
sudo mysql < database/twovago.sql
```
Este archivo contiene la estructura de las tablas y registros de prueba.
**Importante:** el script elimina y vuelve a crear las tablas existentes. Debe utilizarse durante la configuración inicial, ya que su ejecución puede reemplazar los datos actuales.
## Ejecución del proyecto
El backend y el frontend deben ejecutarse en terminales independientes de WSL.
### Backend
Desde la carpeta principal del proyecto:
```bash
cd backend
```
Configurar la variable de entorno:
```bash
export TWOVAGO_DB_PASSWORD=tv12345
```
Iniciar Spring Boot:
```bash
mvn spring-boot:run
```
El backend estará disponible en el puerto `8082`.
Para comprobar su funcionamiento:
```bash
curl http://localhost:8082/api/health
```
La respuesta debe indicar que el estado de la aplicación es `OK`.
### Frontend
Abrir una segunda terminal de WSL y acceder a la carpeta del frontend:
```bash
cd frontend
```
Iniciar el servidor HTTP local:
```bash
python3 -m http.server 3000
```
Abrir en el navegador:
`http://localhost:3000`
El frontend se comunica con el backend mediante solicitudes Fetch a la API REST.
### Direcciones locales
- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8082`
- Estado del backend: `http://localhost:8082/api/health`
- API de hoteles: `http://localhost:8082/api/hoteles`
## Cuentas de prueba
La base de datos incluye las siguientes cuentas para comprobar el funcionamiento del sistema:
| Rol | Correo | Contraseña |
|---|---|---|
| Administrador | admin@gmail.com | admin123 |
| Cliente | cliente1@gmail.com | cliente123 |
| Cliente | cliente2@gmail.com | cliente123 |
Las cuentas anteriores corresponden a los registros incluidos en `database/twovago.sql`.
En esta versión, las contraseñas de los usuarios se almacenan y comparan en texto plano. Este comportamiento se conserva en la implementación actual.
## Documentación y evidencias
La carpeta `documentacion/` contiene los materiales correspondientes al desarrollo de TwoVago:
- Análisis del sistema.
- Modelo de datos.
- Diagrama entidad-relación.
- Diseño de interfaces.
- Diagrama de arquitectura.
- Flujo de reservaciones y pagos.
- Documentación de endpoints.
- Responsabilidades de los integrantes.
- Reportes de actividades.
- Notas de versión.
- Registros de uso de inteligencia artificial generativa.
- Evidencias de implementación y pruebas.
Las capturas de funcionamiento se encuentran en:
`documentacion/reportes/evidencias/`
## Estado del proyecto
TwoVago cuenta con un backend desarrollado en Spring Boot, conectado a MySQL, y un frontend desarrollado con HTML, CSS y JavaScript.
Los módulos de usuarios, hoteles, habitaciones, reservaciones, pagos simulados y administración se encuentran integrados y cuentan con evidencias de funcionamiento.
El proyecto se desarrolla y ejecuta localmente desde WSL, utilizando Git y GitHub para el control de versiones y la colaboración del equipo.