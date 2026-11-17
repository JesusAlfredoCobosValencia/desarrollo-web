# Reporte de Avance del Proyecto

**Equipo:** TwoVago

**Fecha:** 16 de noviembre de 2026

**Número de reporte:** R3 — Usuarios, reservaciones y pagos simulados

**Repositorio GitHub:** desarrollo-web — JesusAlfredoCobosValencia

---

# 1. Actividades realizadas

Durante esta tercera entrega se amplió el backend desarrollado en la entrega anterior. Se mantuvo la arquitectura de tres capas utilizando Java y Spring Boot, incorporando los módulos de usuarios, autenticación, reservaciones y pagos simulados.

Las actividades realizadas fueron las siguientes:

- Continuación del desarrollo del backend con Java, Spring Boot y Maven.
- Implementación de la entidad `Usuario.java` para almacenar los datos de los usuarios.
- Desarrollo del repositorio `UsuarioRepository.java` para consultar y registrar usuarios en MySQL.
- Implementación del servicio `UsuarioService.java` para gestionar el registro, validaciones y almacenamiento de contraseñas.
- Configuración de Spring Security y BCrypt para proteger las contraseñas mediante funciones de hash.
- Desarrollo de los DTO para recibir información de registro y generar respuestas sin exponer contraseñas.
- Implementación del controlador `UsuarioController.java` para gestionar las operaciones de autenticación.
- Implementación del registro de usuarios mediante `POST /api/auth/registro`.
- Implementación del inicio de sesión mediante sesiones HTTP.
- Implementación del cierre de sesión y control del acceso a rutas protegidas.
- Desarrollo de las operaciones para consultar y actualizar el perfil del usuario autenticado.
- Asignación automática del rol CLIENTE durante el registro.
- Implementación de restricciones para impedir que los usuarios clientes realicen operaciones administrativas sobre hoteles y habitaciones.
- Desarrollo del módulo de reservaciones mediante entidades, repositorios, servicios y controladores.
- Implementación de consultas de disponibilidad de habitaciones según las fechas y capacidad.
- Validación de fechas de entrada y salida para evitar reservaciones incorrectas.
- Implementación del cálculo automático del importe total de las reservaciones.
- Generación de reservaciones con estado PENDIENTE_PAGO y un plazo de 10 minutos para completar el pago.
- Desarrollo del módulo de pagos simulados asociado a las reservaciones.
- Implementación de la confirmación de pagos y actualización de estados.
- Desarrollo de procesos automáticos para expirar reservaciones y pagos cuyo plazo haya terminado.
- Implementación de cancelaciones e historial de reservaciones.
- Validación de permisos para impedir que un usuario consulte las reservaciones o pagos de otra cuenta.
- Preparación de una batería de pruebas automatizadas mediante Python para verificar los endpoints y las reglas de negocio.
- Generación de un archivo TXT con los resultados de las pruebas realizadas.

---

# 2. Evidencias de trabajo

Las actividades de esta tercera entrega se respaldan mediante capturas de pantalla del código fuente, configuración del backend, compilación de los componentes y ejecución de las pruebas automatizadas.

Las imágenes se encuentran almacenadas en `documentacion/reportes/evidencias/`.

## 2.1. Historial de contribuciones

| Actividad | Integrante responsable | Evidencia |
|---|---|---|
| Integración del backend y ejecución de pruebas R3 | Jesús Alfredo Cobos Valencia | Historial de commits de GitHub |
| Desarrollo del backend de hoteles y habitaciones | Emanuel Hernández Ochoa | Historial de commits de GitHub |
| Módulo de usuarios y autenticación | Francisco Báez Rodríguez | Historial de commits de GitHub |

Los commits específicos de esta entrega deben consultarse en el historial de la rama principal. No se repiten los identificadores de R2 porque corresponden a la entrega anterior.

## 2.2. Evidencias de implementación

| Archivo | Descripción |
|---|---|
| [E3_01_Entidad_Usuario.png](evidencias/E3_01_Entidad_Usuario.png) | Implementación de la entidad Usuario y definición de sus atributos para almacenar la información de las cuentas. |
| [E3_02_Configuracion_Usuario.png](evidencias/E3_02_Configuracion_Usuario.png) | Configuración relacionada con el módulo de usuarios dentro del backend. |
| [E3_03_UsuarioRepository.png](evidencias/E3_03_UsuarioRepository.png) | Desarrollo del repositorio de usuarios mediante JpaRepository y definición de consultas para localizar usuarios por correo. |
| [E3_04_01_Dependencia_BCrypt.png](evidencias/E3_04_01_Dependencia_BCrypt.png) | Incorporación de la dependencia necesaria para utilizar BCrypt en el almacenamiento seguro de contraseñas. |
| [E3_04_02_Compilacion_BCrypt.png](evidencias/E3_04_02_Compilacion_BCrypt.png) | Comprobación de la implementación y compilación del componente relacionado con BCrypt. |
| [E3_04_03_UsuarioService.png](evidencias/E3_04_03_UsuarioService.png) | Implementación del servicio de usuarios, incluyendo validaciones, normalización del correo, codificación de contraseñas y asignación del rol CLIENTE. |
| [E3_05_01_DTO_Registro.png](evidencias/E3_05_01_DTO_Registro.png) | Desarrollo del DTO utilizado para recibir los datos necesarios durante el registro de usuarios. |
| [E3_05_02_DTO_Respuesta.png](evidencias/E3_05_02_DTO_Respuesta.png) | Implementación del DTO de respuesta para devolver información del usuario sin exponer su contraseña. |
| [E3_05_03_Compilacion.png](evidencias/E3_05_03_Compilacion.png) | Evidencia del proceso de compilación de los componentes relacionados con el registro y respuesta de usuarios. |
| [E3_06_UsuarioController.png](evidencias/E3_06_UsuarioController.png) | Implementación del controlador REST para recibir solicitudes de registro y gestionar las respuestas HTTP correspondientes. |

## 2.3. Registro y autenticación de usuarios

Se implementó el registro de usuarios mediante el endpoint `POST /api/auth/registro`.

Durante las pruebas se comprobó que:

- El registro de un usuario nuevo devuelve HTTP 201.
- El sistema asigna automáticamente el rol CLIENTE.
- Las contraseñas se almacenan mediante BCrypt.
- La respuesta de registro no contiene la contraseña.
- El sistema rechaza correos electrónicos duplicados mediante HTTP 409.
- Los correos con formato inválido son rechazados mediante HTTP 400.

También se implementó el inicio de sesión mediante `POST /api/auth/login`.
Se realizaron pruebas con contraseñas incorrectas y correctas, obteniendo HTTP 401 y HTTP 200, respectivamente.
El sistema mantiene la sesión del usuario autenticado y permite cerrar sesión mediante el endpoint correspondiente.

## 2.4. Consulta y actualización del perfil

Se implementaron las operaciones para que cada usuario pueda consultar y modificar sus propios datos personales.
Las pruebas permitieron comprobar que:

- La consulta del perfil devuelve HTTP 200.
- La actualización de información personal devuelve HTTP 200.
- Un usuario puede consultar únicamente su propio perfil.
- Después de cerrar sesión, el acceso al perfil devuelve HTTP 401.

También se comprobó que un usuario con rol CLIENTE no puede registrar hoteles ni habitaciones, obteniendo respuestas HTTP 403.

## 2.5. Disponibilidad y reservaciones

Se desarrolló el módulo de reservaciones para permitir que los usuarios consulten habitaciones disponibles y generen reservaciones.
El servidor valida las fechas de entrada y salida, la capacidad de las habitaciones y los periodos ocupados por otras reservaciones.
Al generar una reservación:

- Se comprueba la disponibilidad de la habitación.
- Se calcula el importe total desde el backend.
- Se genera el registro de la reservación.
- Se establece el estado inicial PENDIENTE_PAGO.
- Se establece un plazo de 10 minutos para completar el pago.

Se realizaron pruebas para comprobar que dos usuarios no puedan reservar simultáneamente la misma habitación durante las mismas fechas.
También se verificó que los usuarios no puedan consultar reservaciones pertenecientes a otras cuentas.

## 2.6. Pagos, cancelaciones y expiraciones

Se incorporó el módulo de pagos simulados para completar el proceso de reservación.
Las pruebas permitieron comprobar el funcionamiento de las siguientes operaciones:

- Creación de reservaciones pendientes de pago.
- Cálculo del importe total.
- Confirmación de pagos simulados.
- Actualización del estado del pago a APROBADO.
- Consulta de reservaciones propias.
- Cancelación de reservaciones confirmadas antes de la fecha de entrada.
- Conservación de las reservaciones canceladas en el historial.
- Liberación de habitaciones después de una cancelación.
- Expiración automática de reservaciones cuando termina el plazo de pago.
- Expiración del pago asociado a una reservación vencida.
- Rechazo de pagos después de la fecha límite.

También se comprobó que las reservaciones canceladas y expiradas dejen de bloquear la disponibilidad de las habitaciones.

## 2.7. Pruebas automatizadas

Para reducir el tiempo de comprobación de los diferentes módulos se preparó una batería de pruebas automatizadas mediante Python.
La ejecución se realiza utilizando el script:
`scripts/validar_entregable3.sh`
Los resultados se almacenan automáticamente en:
`documentacion/reportes/resultados-pruebas-e3.txt`
La ejecución final se realizó contra el backend iniciado en el puerto 8083.
El reporte generado registró los siguientes resultados:

| Resultado | Cantidad |
|---|---:|
| Pruebas correctas | 47 |
| Pruebas fallidas | 0 |
| Pruebas omitidas | 1 |
| Total | 48 |

La única prueba omitida corresponde al CRUD positivo de administrador, debido a que todavía no se configuraron las variables de entorno `TWOVAGO_ADMIN_EMAIL` y `TWOVAGO_ADMIN_PASSWORD`. Las restricciones para usuarios CLIENTE sí fueron comprobadas.

---

# 3. Problemas encontrados

### Error de compilación en el manejo de sesiones

Durante la implementación del controlador de usuarios se presentó un error de compilación relacionado con la llamada al método `changeSessionId()` sobre un objeto de tipo `HttpSession`. Se revisó el manejo de las sesiones HTTP y se corrigió la implementación para permitir que el backend compilara correctamente. Posteriormente se comprobaron las operaciones de inicio y cierre de sesión mediante las pruebas automatizadas.

### Diferencias entre los endpoints y las solicitudes de prueba

Durante las primeras ejecuciones de la batería de pruebas se encontraron respuestas HTTP 404 y HTTP 400. Estos resultados se relacionaban con diferencias entre las rutas utilizadas por el script, los parámetros de las solicitudes y la configuración del servidor. Se revisaron las rutas y las solicitudes de prueba, ajustando la dirección de la API al puerto 8083 y los parámetros necesarios. Después de estos ajustes, las pruebas de disponibilidad, autenticación y reservaciones devolvieron las respuestas esperadas.

### Pruebas administrativas pendientes

La batería de pruebas contempla una comprobación adicional del CRUD positivo de administrador. Esta prueba no se ejecutó porque no estaban configuradas las credenciales administrativas mediante las variables de entorno correspondientes. Las restricciones para impedir que un cliente realice operaciones administrativas sí se comprobaron correctamente.

---

# 4. Avance funcional

Durante esta tercera entrega se amplió el backend de TwoVago, incorporando los módulos necesarios para administrar usuarios, sesiones, reservaciones y pagos simulados.

**Funcionalidades comprobadas:**

- Consulta del catálogo de hoteles.
- Consulta de habitaciones.
- Consulta de recursos inexistentes con respuesta HTTP 404.
- Registro de usuarios.
- Validación de correos electrónicos.
- Prevención de registros duplicados.
- Almacenamiento de contraseñas mediante BCrypt.
- Inicio y cierre de sesión.
- Consulta y actualización del perfil.
- Control de acceso mediante roles.
- Consulta de disponibilidad de habitaciones.
- Creación de reservaciones pendientes de pago.
- Cálculo automático de importes.
- Generación del plazo de 10 minutos para completar el pago.
- Confirmación de pagos simulados.
- Prevención de reservaciones duplicadas.
- Consulta de reservaciones propias.
- Cancelación de reservaciones.
- Consulta del historial.
- Expiración automática de reservaciones y pagos.
- Liberación de habitaciones después de cancelaciones y expiraciones.

**Resultados de las pruebas:**

| Endpoint | Operación | Resultado |
|---|---|---|
| `/api/health` | GET | HTTP 200 |
| `/api/hoteles` | GET | HTTP 200 |
| `/api/habitaciones` | GET | HTTP 200 |
| `/api/hoteles/999999999` | GET | HTTP 404 |
| `/api/auth/registro` | POST | HTTP 201 |
| `/api/auth/login` | POST | HTTP 200 |
| `/api/auth/login` con contraseña incorrecta | POST | HTTP 401 |
| `/api/usuarios/perfil` | GET | HTTP 200 |
| `/api/usuarios/perfil` | PUT | HTTP 200 |
| `/api/habitaciones/disponibles` | GET | HTTP 200 |
| `/api/reservaciones` | POST | HTTP 201 |
| `/api/auth/logout` | POST | HTTP 204 |

**Resultados generales:**

La batería de pruebas automatizadas registró 47 pruebas correctas, ninguna fallida y una omitida por falta de credenciales administrativas.

**Funcionalidades pendientes:**

- Desarrollo e integración de las interfaces funcionales del frontend.
- Conexión de las pantallas con los endpoints mediante Fetch.
- Configuración del envío de cookies de sesión desde el frontend.
- Pruebas completas del proceso de reservación desde el navegador.
- Configuración de las credenciales administrativas y ejecución de la prueba pendiente.
- Integración y validación final de los módulos desarrollados por los tres integrantes.

El avance de esta entrega corresponde principalmente a la ampliación del backend y a la comprobación de sus operaciones mediante pruebas automatizadas. La conexión completa con las interfaces del frontend todavía requiere desarrollo.

---

# 5. Uso de IA generativa

- **Herramienta utilizada:** ChatGPT.
- **Actividad para la que se utilizó:** Apoyo durante la implementación del módulo de usuarios, autenticación, reservaciones y pagos simulados. También se utilizó para comprender errores de compilación, revisar el funcionamiento de sesiones HTTP y preparar pruebas automatizadas.
- **Código o contenido generado:** Ejemplos de implementación en Java y Spring Boot, propuestas de corrección de errores, orientación para la integración de los módulos y apoyo en la elaboración de la batería de pruebas mediante Python.
- **Modificaciones realizadas por el equipo:** Se revisaron los componentes generados y las modificaciones propuestas, se adaptaron a la estructura existente del backend y se realizaron ajustes en los controladores, servicios y solicitudes de prueba.
- **Forma en que se verificó el resultado:** Se ejecutó el backend desde WSL y se realizaron pruebas automatizadas contra la API. Los resultados se almacenaron en el archivo `resultados-pruebas-e3.txt`, donde se registraron 47 pruebas correctas, 0 fallidas y 1 omitida.

Los registros individuales del uso de inteligencia artificial deben mantenerse en `documentacion/IAG/`, mediante los archivos correspondientes a cada integrante.

---

# 6. Participación individual

- **Jesús Alfredo Cobos Valencia:** Participó en la integración de los avances del backend, revisión de los componentes incorporados durante R3, ejecución de las pruebas automatizadas y comprobación de los resultados generados. También realizó el seguimiento de los cambios del repositorio.

- **Emanuel Hernández Ochoa:** Participó en el desarrollo del backend y en los componentes de hoteles y habitaciones que sirvieron como base para las siguientes etapas del proyecto. Su contribución individual de R3 debe identificarse mediante los commits correspondientes.

- **Francisco Báez Rodríguez:** Tenía asignado el módulo de usuarios y autenticación desde la distribución de responsabilidades de la entrega anterior. Durante R3 se incorporaron los componentes correspondientes al registro, autenticación y administración del perfil. La autoría de los cambios debe identificarse mediante el historial de commits.

Los commits individuales de esta entrega permiten identificar los cambios incorporados por cada integrante y dar seguimiento a su participación en el desarrollo.

---

# 7. Próximas actividades

1. Desarrollar las interfaces funcionales del frontend de TwoVago.
2. Conectar las pantallas de registro e inicio de sesión con el backend mediante Fetch.
3. Configurar el envío y mantenimiento de cookies para las sesiones HTTP.
4. Integrar las interfaces de búsqueda de hoteles y consulta de habitaciones.
5. Conectar el proceso de reservación con el módulo de pagos simulados.
6. Realizar pruebas completas desde el navegador.
7. Configurar las credenciales administrativas y ejecutar la prueba pendiente del CRUD de administrador.
8. Revisar el funcionamiento general del sistema y documentar los resultados de integración.

---

## Observaciones adicionales

Durante este tercer periodo se amplió el alcance funcional del proyecto TwoVago. El backend pasó de contar principalmente con operaciones de consulta y registro de hoteles y habitaciones a incorporar el registro de usuarios, autenticación, control de acceso, perfiles, reservaciones y pagos simulados.

Las pruebas automatizadas permitieron comprobar el funcionamiento de los principales procesos del sistema y verificar las respuestas HTTP esperadas.

Al cierre de R3, la batería de pruebas registró 47 resultados correctos, ninguna prueba fallida y una prueba omitida por falta de configuración administrativa. El siguiente periodo de trabajo se concentrará en desarrollar e integrar el frontend para completar el funcionamiento de TwoVago desde la interfaz de usuario.