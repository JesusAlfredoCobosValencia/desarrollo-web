# Reporte de Avance del Proyecto
Equipo: TwoVago

Fecha: 13 de noviembre de 2026

Número de reporte: R2 — Backend basico y base de datos  

Repositorio GitHub: https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas
Durante esta segunda entrega se inició la implementación del backend de TwoVago utilizando Java y Spring Boot. Se comenzó a trasladar el diseño definido en el primer reporte a una aplicación funcional, desarrollando los primeros servicios y controladores para administrar hoteles y habitaciones.

Las actividades realizadas fueron las siguientes:
- Configuración inicial del backend con Spring Boot, Maven y Java, estableciendo el puerto 8082 para ejecutar y realizar las pruebas de la API.
- Configuración de las propiedades de conexión con la base de datos y preparación del entorno de trabajo desde WSL.
- Desarrollo de los controladores `HotelController.java` y `HabitacionController.java`, encargados de recibir las solicitudes HTTP relacionadas con hoteles y habitaciones.
- Desarrollo de los servicios `HotelService.java` y `HabitacionService.java`, para gestionar las operaciones y consultas de los datos.
- Implementación de consultas para obtener el catálogo de hoteles mediante el endpoint `GET /api/hoteles`.
- Implementación del registro de hoteles mediante el método `POST /api/hoteles`, utilizando solicitudes con contenido JSON.
- Implementación de la consulta de hoteles por ciudad mediante el parámetro `ciudad`.
- Implementación de consultas de habitaciones relacionadas con un hotel mediante el parámetro `hotelId`.
- Implementación de la consulta individual de habitaciones mediante su identificador.
- Realización de pruebas utilizando CURL desde la terminal de WSL para comprobar las respuestas HTTP de los endpoints.
- Realización de pruebas desde el navegador para visualizar las respuestas JSON generadas por el backend.
- Revisión de errores relacionados con Hibernate y las relaciones entre las entidades Hotel y Habitación.
- Organización de la documentación relacionada con el uso de inteligencia artificial generativa.

---

# 2. Evidencias de trabajo
Las actividades de esta segunda entrega se respaldan mediante capturas de pantalla de Visual Studio Code, terminal WSL, CURL y navegador. También se cuenta con evidencia de los commits con el repositorio de GitHub.

## 2.1. Historial de contribuciones

| Actividad | Integrante responsable | Commit |
|---|---|---|
| Diagrama de reservaciones y entrega 1 | Emanuel Hernández Ochoa | [76419a6](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/76419a6) |
| Backend | Emanuel Hernández Ochoa | [52f2a95](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/52f2a95) |
| Controller, entidades, repositorio y servicios | Emanuel Hernández Ochoa | [b430124](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/b430124) |
| GET y POST funcionales | Emanuel Hernández Ochoa | [7a2b446](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/7a2b446) |

## 2.2. Evidencias de implementación y pruebas
| Archivo | Descripción |
|---|---|
| [E2_01_Creacion_BD_usuario_permisos.jpg](evidencias/E2_01_Creacion_BD_usuario_permisos.jpg) | Creación de la base de datos MySQL `twovago`, configuración del usuario de aplicación y asignación de permisos. |
| [E2_02_Hibernate_creacion_tablas.png](evidencias/E2_02_Hibernate_creacion_tablas.png) | Generación automática de las tablas hoteles y habitaciones mediante Hibernate y JPA. |
| [E2_03_CURL_GET_hoteles_y_POST_registro_201.png](evidencias/E2_03_CURL_GET_hoteles_y_POST_registro_201.png) | Pruebas mediante CURL de consulta GET y registro POST de hoteles, con respuestas HTTP 200 y 201. |
| [E2_04_GET_hoteles_JSON_navegador.png](evidencias/E2_04_GET_hoteles_JSON_navegador.png) | Consulta del catálogo de hoteles mediante GET y visualización de los datos en formato JSON. |
| [E2_05_GET_habitaciones_por_hotel.png](evidencias/E2_05_GET_habitaciones_por_hotel.png) | Consulta de habitaciones asociadas a un hotel específico mediante el parámetro `hotelId`. |
| [E2_06_GET_habitacion_por_id.png](evidencias/E2_06_GET_habitacion_por_id.png) | Consulta individual de una habitación mediante su identificador. |
| [E2_07_CURL_filtro_ciudad_y_correccion_habitacion.png](evidencias/E2_07_CURL_filtro_ciudad_y_correccion_habitacion.png) | Pruebas CURL del filtro de hoteles por ciudad y verificación de la corrección del endpoint de consulta de habitaciones. |
| [E2_08_Consulta_recurso_inexistente_HTTP500.png](evidencias/E2_08_Consulta_recurso_inexistente_HTTP500.png) | Registro de una incidencia detectada al consultar un hotel inexistente, que generó una respuesta HTTP 500. |
| [E2_09_Archivo Pom.png](evidencias/E2_09_Archivo%20Pom.png) | Configuración del archivo `pom.xml`, versión de Spring Boot, versión de Java y dependencias del proyecto. |
| [E2_10_ConexionMySQL_y_puerto_8082.png](evidencias/E2_10_ConexionMySQL_y_puerto_8082.png) | Configuración de la conexión a MySQL mediante `application.properties` y asignación del puerto 8082. |
| [E2_11_Entidad_Hotel_y_habitaciones.png](evidencias/E2_11_Entidad_Hotel_y_habitaciones.png) | Implementación de las entidades Hotel y Habitación y definición de sus atributos y relaciones. |
| [E2_12_Repository_Hotel_y_habitaciones.png](evidencias/E2_12_Repository_Hotel_y_habitaciones.png) | Implementación de los repositorios para la persistencia y consulta de hoteles y habitaciones mediante JPA. |
| [E2_13_Service_Hotel_y_habitaciones.png](evidencias/E2_13_Service_Hotel_y_habitaciones.png) | Desarrollo de la capa de servicios encargada de gestionar las operaciones de hoteles y habitaciones. |
| [E2_14_Controller_Hotel_y_habitaciones.png](evidencias/E2_14_Controller_Hotel_y_habitaciones.png) | Implementación de los controladores REST y definición de los endpoints GET y POST para hoteles y habitaciones. |
| [E2_15_Compilacion_maven.png](evidencias/E2_15_Compilacion_maven.png) | Compilación del backend mediante Maven y verificación del resultado BUILD SUCCESS. |
| [E2_16_Spring_Boot.png](evidencias/E2_16_Spring_Boot.png) | Inicio correcto de la aplicación Spring Boot, inicialización de JPA y ejecución del servidor Tomcat en el puerto 8082. |
| [E2_17_Dase_Datos.png](evidencias/E2_17_Dase_Datos.png) | Verificación de las tablas existentes y consulta de los registros almacenados en hoteles y habitaciones mediante MySQL. |

Las evidencias originales se encuentran almacenadas en `documentacion/reportes/evidencias/`.
## 2.3. Registro de hoteles
Se realizó una prueba mediante el método POST para registrar un hotel con la siguiente información:
- Nombre: Hotel Costa Azul.
- Ciudad: Veracruz.
- Dirección: Av. Principal 100.
- Descripción: Hotel ubicado cerca de la playa.
- Categoría: 4.
- Servicios: WiFi, estacionamiento y alberca.
- Estado: activo.
La solicitud devolvió una respuesta HTTP 201, indicando que el registro fue creado correctamente. Posteriormente, mediante una solicitud GET, se comprobó que el hotel aparecía en el catálogo.
## 2.4. Consulta de habitaciones
Se realizaron pruebas para consultar las habitaciones asociadas al hotel registrado.
Se obtuvo información de la habitación con identificador 1, con las siguientes características:
- Número: 101.
- Tipo: Doble.
- Capacidad: 2 personas.
- Precio por noche: $1,200.00.
- Descripción: Habitación doble con vista al mar.
- Estado: Activa.
La información también incluye los datos del Hotel Costa Azul al que pertenece la habitación.
---
# 3. Problemas encontrados
### Error de conexión con MySQL
Durante uno de los intentos de ejecución del backend se presentó un error de autenticación al establecer la conexión con MySQL. El mensaje indicaba que el usuario `twovago_app` no tenía acceso con las credenciales proporcionadas.
Esto provocó que Hibernate no pudiera obtener los metadatos de la base de datos y que Spring Boot interrumpiera el proceso de inicialización.
### Error de carga de relaciones en Hibernate
Al realizar la consulta individual de una habitación mediante `GET /api/habitaciones/1`, el servidor devolvió una respuesta HTTP 500.
Al revisar los registros de Hibernate se identificó el siguiente problema:
`Could not initialize proxy [com.twovago.backend.model.Hotel#1] - no session`
El error se relacionaba con la carga diferida de la entidad Hotel cuando se intentaba consultar la información relacionada con una habitación.
Se revisó el manejo de la relación entre ambas entidades y se realizaron ajustes en la implementación. Después de estos cambios, se volvió a ejecutar la solicitud y dio una respuesta HTTP 200 con los datos completos de la habitación y su hotel asociado.
### Error al consultar un hotel inexistente
Durante las pruebas se realizó una solicitud a `GET /api/hoteles/9999`, utilizando un identificador que no corresponde a un hotel registrado.
El servidor devolvió una página de error interno HTTP 500.
Se identificó que es necesario mejorar el manejo de excepciones para que, cuando un recurso no exista, el backend devuelva una respuesta HTTP 404 con un mensaje descriptivo.
Este comportamiento se implementara en la siguiente entrega.
---
# 4. Avance funcional
Durante este periodo se logró avanzar en la implementación del backend y se realizaron las primeras pruebas funcionales de la API REST.
**Funcionalidades comprobadas:**
- Consulta del catálogo de hoteles mediante GET.
- Registro de hoteles mediante POST.
- Consulta de hoteles filtrados por ciudad.
- Consulta de habitaciones asociadas a un hotel.
- Consulta individual de habitaciones por identificador.
- Respuestas JSON generadas por el backend.
- Comunicación con la API mediante CURL y navegador.
**Resultados de las pruebas:**
| Endpoint | Operación | Resultado |
|---|---|---|
| `/api/hoteles` | GET | HTTP 200 |
| `/api/hoteles` | POST | HTTP 201 |
| `/api/hoteles?ciudad=Veracruz` | GET | HTTP 200 |
| `/api/habitaciones?hotelId=1` | GET | Respuesta JSON visualizada |
| `/api/habitaciones/1` | GET | HTTP 200 después de los ajustes |
| `/api/hoteles/9999` | GET | HTTP 500, pendiente de corrección |
**Funcionalidades pendientes:**
- Implementación completa de las operaciones de actualización y eliminación de hoteles y habitaciones.
- Manejo de excepciones y validaciones de recursos inexistentes.
- Desarrollo de autenticación y administración de usuarios.
- Implementación del módulo de reservaciones.
- Validación de disponibilidad de habitaciones.
- Implementación del pago simulado y temporizador de 10 minutos.
- Desarrollo de las interfaces del frontend y conexión mediante Fetch.
- Integración completa de los módulos de los tres integrantes.
El avance actual corresponde principalmente al backend de hoteles y habitaciones. Los módulos de reservaciones, pagos y usuarios todavía requieren desarrollo e integración, es un backend sencillo.
---
# 5. Uso de IA generativa
- **Herramienta utilizada:** ChatGPT.
- **Actividad para la que se utilizó:** Apoyo durante la implementación del backend, comprensión de errores de Spring Boot y Hibernate, revisión de respuestas HTTP y orientación para realizar pruebas de los endpoints mediante CURL.
- **Código o contenido generado:** Explicaciones técnicas, propuestas de corrección de errores y ejemplos de solicitudes HTTP para comprobar el funcionamiento de la API.
- **Modificaciones realizadas por el equipo:** Se revisaron los controladores y servicios del módulo de hoteles y habitaciones, se realizaron ajustes en el manejo de las relaciones entre entidades y se ejecutaron nuevamente las pruebas para comprobar los resultados.
- **Forma en que se verificó el resultado:** Se ejecutó el backend desde WSL y se realizaron solicitudes mediante CURL y navegador. Se verificaron respuestas HTTP 200 y 201, además de revisar los registros de Hibernate para identificar los errores presentados.
Los registros individuales del uso de IA deberán mantenerse en la carpeta `documentacion/IAG/`, mediante los archivos correspondientes a cada integrante. Cada integrante debe registrar las consultas que realizó y las modificaciones que aplicó al proyecto.
---
# 6. Participación individual
- **Jesús Alfredo Cobos Valencia:** Continuó con el seguimiento general del repositorio TwoVago, la organización del proyecto y la integración de los avances con la estructura previamente documentada. Los commits individuales de este periodo deben identificarse mediante el historial de GitHub para acreditar su autoría.
- **Emanuel Hernandez Ochoa:** En las evidencias se observa una sesión de trabajo desde WSL identificada como `emanuel@3nl`, en la que se ejecutaron pruebas del backend mediante CURL, se realizaron consultas y registros de hoteles, se probaron los endpoints de habitaciones y se efectuó una sincronización de cambios con GitHub. La autoría individual del commit deberá confirmarse en el historial.
- **Francisco Baez Rodriguez:** De acuerdo con la distribución de responsabilidades establecida en el primer reporte, tiene asignado el módulo de usuarios y autenticación, incluyendo el registro, inicio de sesión y administración de perfiles. En las evidencias disponibles para este segundo periodo no se identifica todavía un commit individual o una prueba funcional de dicho módulo.
---
# 7. Próximas actividades
1. Corregir el manejo de excepciones para devolver HTTP 404 cuando se consulte un hotel o habitación inexistente.
2. Completar las operaciones de consulta, registro, actualización y eliminación de hoteles y habitaciones.
3. Continuar con el desarrollo del módulo de usuarios y autenticación.
4. Implementar el módulo de reservaciones y la validación de disponibilidad de habitaciones.
5. Desarrollar el sistema de pago simulado y el temporizador de 10 minutos para confirmar o expirar las reservaciones.
6. Integrar los módulos desarrollados por los tres integrantes.
7. Conectar las interfaces del frontend con el backend mediante Fetch.
8. Realizar pruebas de funcionamiento de los diferentes módulos y documentar sus resultados.
---
## Observaciones adicionales
Durante este segundo periodo se pasó de la etapa de análisis y diseño a la implementación de los primeros componentes funcionales de TwoVago. Se logró registrar y consultar hoteles, consultar habitaciones y comprobar la relación entre ambas entidades.
Las pruebas permitieron identificar errores relacionados con la conexión a MySQL y la carga de relaciones mediante Hibernate. También se detectó que todavía es necesario mejorar el manejo de recursos inexistentes.