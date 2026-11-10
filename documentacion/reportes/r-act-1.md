# Reporte de Avance del Proyecto

Equipo: TwoVago

Fecha: 10 de noviembre de 2026

Número de reporte: R1 — Análisis y diseño inicial

Repositorio GitHub: https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas

Durante el primer periodo de trabajo se establecieron las bases del proyecto TwoVago, una plataforma web de búsqueda y reservación de hoteles. Se realizaron las siguientes actividades:

- Creación del repositorio `desarrollo-web` en GitHub y organización inicial de las carpetas para backend, frontend, base de datos y documentación.
- Elaboración del archivo README con la descripción general del proyecto, sus integrantes y las tecnologías previstas.
- Análisis de la problemática, definición de objetivos, alcance, actores y requerimientos funcionales y no funcionales.
- Diseño del modelo de datos considerando las entidades principales: usuarios, hoteles, habitaciones, reservaciones y pagos.
- Elaboración del diagrama entidad-relación para representar las entidades y sus relaciones.
- Definición de los endpoints REST necesarios para gestionar usuarios, autenticación, hoteles, habitaciones, reservaciones y pagos simulados.
- Diseño de interfaces y navegación del sistema para clientes y administradores.
- Definición del flujo de reservaciones, incluyendo la validación de disponibilidad, generación de reservaciones pendientes de pago, plazo de 10 minutos para completar el pago simulado y expiración automática.
- Definición de los estados de las reservaciones para distinguir las confirmadas, pendientes de pago, completadas, canceladas y expiradas.
- Elaboración del diagrama de arquitectura de tres capas.
- Distribución de responsabilidades entre los tres integrantes del equipo.
- Organización de la documentación relacionada con el uso de inteligencia artificial generativa.

# 2. Evidencias de trabajo

Las siguientes contribuciones se identifican en el historial de GitHub del proyecto.

| Actividad | Integrante responsable | Commit |
|---|---|---|
| Estructura inicial del repositorio | Jesús Alfredo Cobos Valencia | [d7590f3](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d7590f3) |
| Análisis de TwoVago | Jesús Alfredo Cobos Valencia | [7d2cc3f](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/7d2cc3f) |
| Diagrama ER y modelo de datos | Jesús Alfredo Cobos Valencia | [cd8e639](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cd8e639) |
| Modificación del README | Jesús Alfredo Cobos Valencia | [025ea37](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/025ea37) |
| Definición de endpoints | Jesús Alfredo Cobos Valencia | [17707a1](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/17707a1) |
| README y diagrama | Emanuel | [d279655](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d279655) |
| Documentación del flujo de reservaciones | Emanuel | [cd256cb](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cd256cb) |
| Diseño de interfaces y navegación | Francisco | [e12c460](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/e12c460) |
| Distribución de responsabilidades | Jesús Alfredo Cobos Valencia | [d24e204](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d24e204) |
| Diagrama de arquitectura de tres capas | Jesús Alfredo Cobos Valencia | [f1dcdae](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/f1dcdae) |
| Documentación y estructura de uso de IA | Jesús Alfredo Cobos Valencia | [7fe85cc](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/7fe85cc) |
| Actualización del repositorio | Francisco Baez Rodriguez | [975254e](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/975254e) |
| Integración de cambios a la rama principal | Francisco Baez Rodriguez | [4ceb0ad](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/4ceb0ad) |

Las capturas de pantalla complementarias se organizarán en `documentacion/reportes/evidencias/`, incluyendo el repositorio, los commits, los documentos de diseño y los diagramas.

# 3. Problemas encontrados

### Autenticación y permisos de GitHub

Durante los primeros intentos de subir cambios al repositorio se presentaron errores de autenticación y posteriormente un error 403 por permisos insuficientes del token de acceso personal.

Se revisaron los permisos del token y se autorizó el acceso al repositorio correspondiente. Después de realizar los ajustes, los cambios pudieron subirse correctamente mediante `git push`.

### Visualización del diagrama Draw.io

Al abrir el archivo `.drawio` directamente desde Visual Studio Code se mostraba su contenido en formato XML, lo que generó confusión sobre su visualización.

Se comprobó que el archivo estaba correctamente guardado y que podía abrirse desde diagrams.net (Draw.io) para visualizar y editar el diagrama.

### Organización de ramas e integración

Durante la organización del repositorio se identificó una rama secundaria de respaldo de Francisco. Sus cambios se integraron a `main` mediante un merge y posteriormente se eliminó la rama secundaria.

Se acordó continuar el trabajo utilizando únicamente la rama principal `main`, coordinando las actualizaciones entre los integrantes.

# 4. Avance funcional

El avance correspondiente a este primer reporte se concentra en el análisis, diseño y documentación del sistema. Todavía no se cuenta con una aplicación funcional ejecutándose.

Elementos definidos en el diseño:

- Catálogo de hoteles y habitaciones.
- Registro, autenticación y perfiles de usuarios.
- Consulta y búsqueda de establecimientos.
- Consulta de disponibilidad de habitaciones.
- Creación y seguimiento de reservaciones.
- Generación de pagos simulados con límite de tiempo.
- Confirmación y expiración automática de reservaciones.
- Cancelaciones e historial de estancias.

Elementos pendientes de implementación:

- Configuración del proyecto backend con Spring Boot.
- Creación y conexión de la base de datos MySQL.
- Implementación de entidades y repositorios mediante JPA.
- Desarrollo de controladores y servicios REST.
- Implementación de validaciones de usuarios, habitaciones y reservaciones.
- Desarrollo del mecanismo de expiración de pagos.
- Construcción de las interfaces frontend.
- Integración del frontend con los endpoints mediante Fetch.
- Pruebas unitarias y de integración.

# 5. Uso de IA generativa

Durante las actividades de análisis y documentación se utilizó inteligencia artificial generativa como herramienta de apoyo.

- Herramienta utilizada: ChatGPT.
- Actividad para la que se utilizó: análisis de los requisitos del proyecto, selección y delimitación de la propuesta, organización del trabajo, diseño del modelo de datos, definición de endpoints, revisión de documentación y diseño del flujo de reservaciones.
- Código o contenido generado: propuestas de documentación en Markdown, estructuras de diseño, explicaciones técnicas y apoyo en la elaboración de diagramas.
- Modificaciones realizadas por el equipo: se revisaron las propuestas, se ajustaron al alcance de TwoVago y se establecieron las reglas de negocio, entidades, relaciones y responsabilidades del proyecto.
- Forma en que se verificó el resultado: comparación con los requisitos establecidos por el profesor, revisión de los documentos relacionados y comprobación visual de los diagramas en Draw.io.

Los registros individuales de consultas se encuentran organizados en `documentacion/IAG/`. Cada integrante deberá mantener actualizado su propio registro de acuerdo con las consultas que haya realizado.

# 6. Participación individual

Jesús Alfredo Cobos Valencia

- Creación y organización inicial del repositorio.
- Análisis de la problemática y definición de los requerimientos.
- Diseño del modelo de datos y elaboración del diagrama entidad-relación.
- Definición de los endpoints REST.
- Coordinación de la documentación general y la integración del proyecto.

Emanuel Hernandez Ochoa

- Colaboración en la actualización del README y los diagramas.
- Documentación del flujo de reservaciones.
- Definición de los procesos relacionados con pagos simulados, confirmaciones y expiraciones.

Francisco Baez Rodriguez

- Elaboración de la documentación del diseño de interfaces y navegación.
- Definición de las vistas y organización de la navegación del sistema.
- Planeación del módulo de usuarios, autenticación y perfiles.

Los tres integrantes tienen responsabilidades asignadas para la siguiente etapa de desarrollo y deberán continuar registrando sus contribuciones mediante commits realizados desde sus propias cuentas de GitHub.

# 7. Próximas actividades

1. Revisar y completar la documentación del primer entregable.
2. Incorporar las capturas de pantalla correspondientes a las evidencias de trabajo.
3. Verificar los autores y enlaces directos de los commits del repositorio.
4. Finalizar los registros individuales de uso de IA generativa.
5. Preparar y publicar el Release 1 en GitHub.
6. Comenzar la configuración del backend con Spring Boot y la conexión a MySQL.
7. Iniciar la implementación de los módulos asignados a cada integrante.

---

## Observaciones adicionales

El equipo acordó utilizar exclusivamente la rama principal `main` para continuar el desarrollo del proyecto. Los cambios realizados por los integrantes deberán integrarse de manera coordinada, manteniendo commits descriptivos y contribuciones identificables.

El primer reporte representa el avance de análisis y diseño de TwoVago. La implementación del sistema y las pruebas de funcionamiento se desarrollarán en los siguientes periodos de trabajo.