# Registro de consultas de inteligencia artificial

**Proyecto:** TwoVago  
**Integrante:** Emanuel Hernández Ochoa  
**Herramienta utilizada:** ChatGPT

## 1. Consultas realizadas

| Fecha | Herramienta | Prompt original | Respuesta IA |
|---|---|---|---|
| 12/11/2026 | ChatGPT | Necesito comenzar a desarrollar las entidades de mi proyecto TwoVago con Spring Boot y JPA. Ayúdame a crear la clase `Hotel.java` con sus atributos, identificador y anotaciones correspondientes para conectarla con MySQL. | Se generó la entidad Hotel utilizando anotaciones de JPA, definiendo sus atributos, identificador y correspondencia con la tabla de hoteles de la base de datos. |
| 12/11/2026 | ChatGPT | Ahora necesito crear la entidad `Habitacion.java`. Debe tener sus atributos correspondientes y relacionarse con Hotel, porque cada habitación pertenece a un hotel. | Se generó la entidad Habitacion con sus campos correspondientes y una relación `@ManyToOne` con Hotel, utilizando `@JoinColumn` para establecer la llave foránea. |
| 12/11/2026 | ChatGPT | Ayúdame a crear los Repository de Hotel y Habitacion para poder consultar y guardar los registros en MySQL utilizando Spring Data JPA. | Se generaron las interfaces `HotelRepository.java` y `HabitacionRepository.java`, extendiendo JpaRepository para facilitar las operaciones de persistencia y consulta. |
| 12/11/2026 | ChatGPT | Ahora ayúdame con los Service de hoteles y habitaciones. Quiero que utilicen los Repository que acabamos de crear y permitan trabajar con los registros de la base de datos. | Se generaron `HotelService.java` y `HabitacionService.java`, encargados de conectar los controladores con los repositorios y organizar las operaciones sobre las entidades. |
| 12/11/2026 | ChatGPT | Necesito crear los Controller de Hotel y Habitacion para exponer los endpoints REST de mi proyecto. Dame los archivos completos y explícame dónde colocarlos. | Se generaron `HotelController.java` y `HabitacionController.java`, utilizando anotaciones de Spring MVC para recibir solicitudes HTTP y comunicarse con los servicios. |
| 12/11/2026 | ChatGPT | Integra los controladores de hoteles y habitaciones con sus respectivos servicios. Necesito poder consultar los registros y registrar nuevos elementos mediante GET y POST. | Se proporcionó la implementación de los endpoints GET y POST para consultar y registrar hoteles y habitaciones. Inicialmente también se contemplaron operaciones PUT y DELETE, pero se acordó dejarlas para la siguiente entrega. |
| 12/11/2026 | ChatGPT | Estoy teniendo problemas con Maven en Visual Studio Code. Me aparecen muchos errores de dependencias faltantes y referencias a bibliotecas que no existen. ¿Cómo puedo solucionarlo? | Se explicó cómo identificar problemas con las dependencias de Maven, revisar el archivo `pom.xml`, comprobar el repositorio local y actualizar las dependencias del proyecto para conseguir una compilación correcta. |
| 12/11/2026 | ChatGPT | ¿Qué significa el error Whitelabel Error Page que aparece cuando ingreso a localhost? | Se explicó la diferencia entre los errores HTTP 404 y 500, cómo identificar si una ruta no tiene un controlador asociado y cómo comprobar que los endpoints estén correctamente definidos. |
| 13/11/2026 | ChatGPT | ¿Cómo puedo probar los endpoints utilizando CURL y comprobar que las operaciones GET y POST funcionan correctamente? | Se proporcionaron ejemplos de solicitudes CURL para consultar hoteles, filtrar por ciudad, registrar un hotel y consultar habitaciones. Se explicó cómo interpretar las respuestas HTTP 200 y 201 y verificar los resultados en formato JSON. |
| 22/11/2026 | ChatGPT | Este es mi R4, ocupa el mismo formato, pero ahora en R5 y dime la estructura para realizarlo. | Se utilizó el reporte R4 como referencia para elaborar el reporte de actividades R5, conservando su estructura y adaptando las secciones a las pruebas funcionales y evidencias de la quinta entrega. |
| 22/11/2026 | ChatGPT | Te comparto el archivo de Release R4 para conservar su formato. | Se elaboraron las notas de versión R5 tomando como referencia el documento R4, incluyendo la descripción de la versión, elementos incluidos, contribuciones, pruebas, limitaciones y próxima etapa. |

## 2. Uso de las respuestas

Las respuestas proporcionadas por ChatGPT se utilizaron como apoyo durante el desarrollo de la segunda y quinta entrega de TwoVago.

Durante R2, la herramienta ayudó a resolver problemas de configuración del entorno de desarrollo, identificar errores relacionados con Maven y Spring Boot, establecer la conexión con MySQL y comprobar el funcionamiento de los primeros endpoints del backend.

También se utilizaron las indicaciones para ejecutar pruebas mediante CURL, consultar registros directamente en la base de datos y verificar la persistencia de la información de hoteles y habitaciones.

En la parte de programación, las respuestas sirvieron como referencia para desarrollar las entidades Hotel y Habitacion, así como sus respectivos repositorios, servicios y controladores. Estos componentes permitieron establecer la comunicación entre Spring Boot y MySQL e implementar las primeras operaciones de consulta y registro mediante endpoints REST.

Durante R5, ChatGPT se utilizó principalmente como apoyo para elaborar y organizar la documentación de la entrega. Se tomó como referencia el reporte de actividades R4 para mantener el mismo formato en R5, además de organizar el historial de contribuciones a partir de los commits registrados en GitHub.

También se utilizó para actualizar el índice de evidencias, incorporando las 20 capturas correspondientes a las pruebas funcionales de R5 y conservando los registros de las entregas anteriores. Posteriormente, se elaboraron las notas de versión R5 tomando como referencia el formato utilizado en R4.

Los contenidos generados fueron revisados y ajustados tomando en cuenta los archivos del proyecto, las capturas de pantalla y la información de los commits disponibles. La integración de los documentos y su revisión final corresponden al trabajo realizado por el integrante.

## 3. Tipo de uso

- **Herramienta:** ChatGPT.
- **Uso:** orientación técnica, resolución de errores, generación de comandos, ejemplos de código y apoyo en documentación.
- **Generación de código:** Sí, durante R2, mediante ejemplos y fragmentos utilizados como referencia para las entidades, repositorios, servicios y controladores.
- **Modificación de código:** Sí, durante R2, mediante la aplicación y adaptación de las sugerencias al código del proyecto. Durante R5 no se utilizó para modificar el código de la aplicación.
- **Pruebas:** Durante R2 se utilizaron compilaciones con Maven, ejecución de Spring Boot, solicitudes CURL y consultas en MySQL. Durante R5 se utilizó como apoyo para organizar y documentar las pruebas funcionales realizadas por el equipo.
- **Documentación:** Elaboración del reporte de actividades R5, actualización del índice de evidencias y preparación de las notas de versión.
- **Aplicación:** Desarrollo del backend durante R2 y consolidación de la documentación técnica y funcional durante R5.