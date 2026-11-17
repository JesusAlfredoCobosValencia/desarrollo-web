# Registro de consultas de IA generativa

**Proyecto:** TwoVago  
**Integrante:** Jesús Alfredo Cobos Valencia

## 1. Consultas realizadas

| Fecha | Herramienta | Prompt utilizado | Respuesta de IA |
|---|---|---|---|
| 09/11/2026 | ChatGPT | Estoy trabajando en un proyecto que es una plataforma web para buscar y reservar hoteles pero necesito que me des ideas de cómo podría organizar el diseño de las interfaces y la navegación del sistema, como distribuir las opciones para los usuarios y elementos en las pantallas. | Se propuso organizar en dos áreas principales, una para clientes y otra para administradores. Para los clientes se recomendaron secciones como una página de inicio con buscador de hoteles, un apartado para explorar alojamientos, filtros de búsqueda y pantallas con los detalles de cada hotel y sus habitaciones. También se sugirieron accesos al registro, inicio de sesión y consulta de reservaciones. |
| 16/11/2026 | ChatGPT | Revisa el avance de mi proyecto TwoVago y dime qué hace falta exclusivamente para completar el entregable 3, tomando en cuenta lo que ya se implementó en el entregable anterior. | Se revisaron los módulos del backend y se identificaron las funcionalidades relacionadas con usuarios, autenticación, reservaciones, pagos simulados, disponibilidad, cancelaciones y expiración de reservaciones. También se organizó una lista de actividades pendientes para completar la entrega. |
| 16/11/2026 | ChatGPT | Ayúdame a revisar la implementación de habitaciones en Spring Boot. Quiero comprobar que el modelo, controlador, servicio y repositorio estén relacionados correctamente y que funcionen las operaciones de registrar, consultar, actualizar y desactivar habitaciones. | Se revisó la estructura de las clases y la relación entre hoteles y habitaciones. Se orientó sobre las operaciones CRUD, las validaciones de datos, la asociación entre entidades y las respuestas HTTP que debían obtenerse durante las pruebas. |
| 16/11/2026 | ChatGPT | Estoy trabajando con el registro de usuarios. Ayúdame a revisar cómo implementar BCrypt para guardar las contraseñas cifradas, evitar correos duplicados y validar los datos antes de registrar un usuario. | Se explicó la configuración de BCrypt mediante PasswordEncoder, la validación de campos obligatorios y la comprobación de correos existentes. También se revisó que las respuestas del registro no expusieran las contraseñas almacenadas. |
| 16/11/2026 | ChatGPT | Necesito probar todas las funcionalidades del entregable 3, pero hacer cada petición con CURL me quita mucho tiempo. Ayúdame a preparar un script que ejecute las pruebas automáticamente y genere un archivo TXT con los resultados. | Se propuso una batería de pruebas automatizadas para comprobar los endpoints del backend. El script permite registrar usuarios, iniciar sesión, consultar hoteles y habitaciones, realizar reservaciones, simular pagos, cancelar y comprobar expiraciones. Los resultados se guardan en un archivo TXT con el estado de cada prueba. |
| 16/11/2026 | ChatGPT | Revisa los resultados de mi archivo resultados-pruebas-e3.txt y dime cuáles pruebas están fallando, cuáles funcionan correctamente y qué errores debo corregir. | Se analizaron los códigos HTTP y las respuestas del servidor para identificar rutas inexistentes, errores de configuración y funcionalidades que todavía no respondían correctamente. Se dieron indicaciones para corregir los problemas y volver a ejecutar las pruebas. |
| 16/11/2026 | ChatGPT | Ayúdame a revisar qué capturas de pantalla necesito para documentar el entregable 3, tomando en cuenta las evidencias que ya tengo guardadas en la carpeta de evidencias. | Se organizó una lista de evidencias para documentar las entidades, repositorios, servicios, controladores, configuración de seguridad, compilación y resultados de las pruebas del backend. |
| 16/11/2026 | ChatGPT | Necesito que adaptes el reporte del entregable 3 para que tenga la misma estructura y presentación que el reporte del entregable 2, agregando las nuevas funcionalidades, archivos implementados y rutas de las evidencias. | Se estructuró el reporte R3 siguiendo el formato del R2. Se incorporaron las nuevas funcionalidades de usuarios, reservaciones y pagos simulados, además de las pruebas realizadas y las referencias a las evidencias almacenadas en el repositorio. |

## 2. Uso de las respuestas

Las respuestas de ChatGPT se utilizaron como apoyo durante el desarrollo del proyecto TwoVago, principalmente para revisar la estructura del backend, resolver problemas de implementación y organizar las pruebas correspondientes al tercer entregable.

Las consultas permitieron orientar la revisión de las entidades, servicios, repositorios y controladores, así como comprender la implementación de la autenticación mediante BCrypt y el manejo de sesiones de usuario.

También se utilizó la herramienta para preparar un script de pruebas automatizadas que facilitara la comprobación de los endpoints y generara un archivo de resultados en formato TXT.

Finalmente, las respuestas sirvieron como referencia para organizar las evidencias y preparar la documentación del entregable 3. El código y las modificaciones fueron revisados e integrados en el proyecto de acuerdo con su funcionamiento.

## 3. Tipo de uso

- **Herramienta:** ChatGPT.
- **Uso:** orientación técnica, revisión de código, resolución de errores y documentación.
- **Generación de código:** Sí, fragmentos de código y apoyo para automatizar pruebas.
- **Modificación de código:** Sí, mediante sugerencias revisadas e integradas en el proyecto.
- **Aplicación:** desarrollo del backend, autenticación, gestión de reservaciones, pruebas HTTP y documentación del entregable 3.