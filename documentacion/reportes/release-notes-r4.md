# TwoVago — Release R4

**Versión:** R4 — Frontend e integración del sistema  
**Fecha:** 19 de noviembre de 2026  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

## 1. Descripción de la versión

Esta cuarta versión de TwoVago incorpora el frontend y su integración con el backend desarrollado durante las entregas anteriores.

Se implementaron las interfaces para clientes y administradores, conectadas mediante solicitudes HTTP con Fetch y respuestas JSON. La plataforma permite realizar las operaciones principales desde el navegador, manteniendo las sesiones de usuario mediante cookies.

## 2. Elementos incluidos

### Frontend

- `frontend/index.html`: estructura principal de la aplicación.
- `frontend/css/styles.css`: estilos y distribución visual de las interfaces.
- `frontend/js/api.js`: funciones para realizar solicitudes a los endpoints REST.
- `frontend/js/app.js`: lógica de navegación, formularios, consultas y operaciones de la plataforma.

### Integración con el backend

- Consumo de endpoints mediante Fetch.
- Intercambio de información en formato JSON.
- Envío de credenciales mediante `credentials: "include"`.
- Manejo de sesiones HTTP.
- Validación de respuestas y presentación de mensajes al usuario.
- Diferenciación de funciones según el rol del usuario.

### Funcionalidades para clientes

- Registro de cuentas.
- Inicio y cierre de sesión.
- Consulta y actualización del perfil.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones.
- Consulta de disponibilidad por fechas y cantidad de huéspedes.
- Creación de reservaciones.
- Consulta y confirmación de pagos simulados.
- Consulta de reservaciones propias.
- Cancelación de reservaciones.
- Consulta del historial de estancias.
- Consulta de cancelaciones y reservaciones expiradas.

### Funcionalidades administrativas

- Acceso al panel administrativo.
- Registro de hoteles.
- Consulta de hoteles registrados.
- Edición y desactivación de hoteles.
- Registro y administración de habitaciones.
- Consulta general de reservaciones.

## 3. Endpoints consumidos

El frontend utiliza las siguientes operaciones del backend:

| Método | Endpoint | Operación |
|---|---|---|
| GET | `/api/hoteles` | Consultar hoteles. |
| GET | `/api/hoteles?ciudad=` | Buscar hoteles por ciudad. |
| GET | `/api/hoteles/{id}` | Consultar un hotel. |
| POST | `/api/hoteles` | Registrar hotel. |
| PUT | `/api/hoteles/{id}` | Actualizar hotel. |
| DELETE | `/api/hoteles/{id}` | Dar de baja hotel. |
| GET | `/api/habitaciones?hotelId={hotelId}` | Consultar habitaciones de un hotel. |
| GET | `/api/habitaciones/{id}` | Consultar una habitación. |
| GET | `/api/habitaciones/disponibles?hotelId={hotelId}&fechaEntrada={fechaEntrada}&fechaSalida={fechaSalida}&huespedes={huespedes}` | Consultar habitaciones disponibles según fechas y huéspedes. |
| POST | `/api/habitaciones?hotelId={hotelId}` | Registrar habitación en un hotel. |
| PUT | `/api/habitaciones/{id}` | Actualizar habitación. |
| DELETE | `/api/habitaciones/{id}` | Dar de baja habitación. |
| POST | `/api/auth/registro` | Registrar cliente. |
| POST | `/api/auth/login` | Iniciar sesión. |
| POST | `/api/auth/logout` | Cerrar sesión. |
| GET | `/api/usuarios/perfil` | Consultar perfil. |
| PUT | `/api/usuarios/perfil` | Actualizar perfil. |
| POST | `/api/reservaciones` | Crear reservación. |
| GET | `/api/reservaciones/mis-reservas` | Consultar reservaciones propias. |
| GET | `/api/reservaciones/{id}` | Consultar reservación. |
| PUT | `/api/reservaciones/{id}/cancelar` | Cancelar reservación. |
| GET | `/api/reservaciones/historial` | Consultar historial. |
| GET | `/api/reservaciones/cancelaciones` | Consultar cancelaciones. |
| GET | `/api/reservaciones/admin` | Consultar reservaciones administrativas. |
| GET | `/api/pagos/{id}` | Consultar pago. |
| POST | `/api/pagos/{id}/confirmar` | Confirmar pago simulado. |

## 4. Tecnologías utilizadas

- HTML5
- CSS
- JavaScript.
- Java 17.
- Spring Boot 4.1.2.
- Maven.
- MySQL.
- Fetch API.
- JSON.
- Visual Studio Code.
- WSL.
- Git y GitHub.

## 5. Contribuciones del equipo

### Jesús Alfredo Cobos Valencia

- Ejecución y comprobación local del sistema integrado.
- Pruebas de las funciones de administración y cliente.
- Preparación de 18 capturas de evidencia correspondientes a R4.
- Organización y actualización de la documentación de la entrega.

### Emanuel Hernández Ochoa

- Correcciones de la versión definitiva.
- Integración de reservaciones y panel administrativo.
- Finalización del frontend.

### Francisco Báez Rodríguez

- Desarrollo inicial de HTML, JavaScript y conexión con la API.
- Implementación de estilos CSS.
- Ampliación de las funciones del frontend.
- Ajustes de documentación.

Las contribuciones de desarrollo se encuentran respaldadas por el historial de commits de GitHub.

## 6. Pruebas y evidencias

Se realizaron pruebas manuales desde el navegador para comprobar la integración entre las interfaces, el backend y la base de datos.

Se verificaron los siguientes procesos:

- Acceso administrativo y de clientes.
- Registro de usuarios.
- Consulta y actualización de perfiles.
- Búsqueda de hoteles.
- Consulta de habitaciones y disponibilidad.
- Creación y confirmación de reservaciones.
- Cancelación de reservaciones.
- Consulta de historial y reservaciones expiradas.
- Administración de hoteles y habitaciones.
- Consulta administrativa de reservaciones.
- Manejo de errores de autenticación.
- Cierre de sesión.

Las pruebas se documentaron mediante 18 capturas identificadas como `E4_01` a `E4_18`, almacenadas en:

`documentacion/reportes/evidencias/`

## 7. Limitaciones conocidas

- Los pagos son simulados y no procesan transacciones bancarias reales.
- La ejecución y configuración del sistema corresponden al entorno local de desarrollo.
- Las pruebas documentadas corresponden a los flujos funcionales comprobados durante esta entrega.
- La documentación general y los registros individuales de uso de IA requieren su consolidación final.

## 8. Próxima etapa

Las actividades posteriores estarán enfocadas en:

1. Completar y revisar la documentación general.
2. Actualizar los registros individuales de uso de IA.
3. Verificar las instrucciones de instalación y ejecución del README.
4. Preparar el video de funcionamiento general del sistema.
5. Organizar la entrega final y sus evidencias.

---

**Estado de la versión:** Frontend integrado con el backend, con funciones para clientes y administradores comprobadas mediante pruebas manuales desde el navegador.