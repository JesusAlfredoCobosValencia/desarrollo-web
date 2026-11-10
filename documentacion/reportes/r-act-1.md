# Reporte de Avance del Proyecto

**Equipo:** TwoVago  
**Fecha:** 10/11/2026  
**Número de reporte:** 1  
**Integrantes:** Jesús Alfredo Cobos Valencia, Emanuel y Francisco Báez Rodríguez.  
**Repositorio GitHub:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

# 1. Actividades realizadas

Durante esta primera etapa se trabajó en la planificación y documentación inicial del proyecto TwoVago, una plataforma web para la búsqueda, comparación y reservación de habitaciones de hoteles.

Las actividades realizadas fueron:

- Creación y configuración del repositorio GitHub del proyecto.
- Organización inicial de las carpetas para backend, frontend, base de datos y documentación.
- Definición del objetivo, problemática y alcance general del sistema.
- Elaboración del análisis y diseño inicial del proyecto.
- Diseño del modelo de datos y del diagrama entidad-relación.
- Definición de los endpoints REST necesarios para la comunicación entre frontend y backend.
- Documentación del flujo de reservaciones, incluyendo el pago simulado con límite de tiempo, confirmaciones y vencimientos.
- Diseño de las interfaces y definición de la navegación entre las secciones de la plataforma.
- Configuración de Git en WSL para realizar commits y sincronizar los avances con GitHub.

# 2. Evidencias de trabajo

| Actividad | Integrante responsable | Enlace a commit |

| Estructura inicial, análisis y diseño del proyecto | Jesús | [7d2cc3f](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/7d2cc3f) |

| Modelo de datos y diagrama entidad-relación | Jesús | [cd8e639](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cd8e639) |

| Definición de endpoints REST | Jesús | [17707a1](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/17707a1) |

| README y diagrama de TwoVago | Jesús | [d279655](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/d279655) |

| Documentación del flujo de reservaciones | Emanuel | [cd256cb](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/cd256cb) |

| Diseño de interfaces y navegación | Francisco | [e12c460](https://github.com/JesusAlfredoCobosValencia/desarrollo-web/commit/e12c460) |

Las evidencias visuales se integrarán en la carpeta `documentacion/reportes/evidencias/`.

# 3. Problemas encontrados

Durante la configuración y sincronización del repositorio se presentaron algunos inconvenientes:

**Problema 1. Autenticación con GitHub**

Al realizar los primeros intentos de `git push`, GitHub rechazó las credenciales porque no permite utilizar la contraseña convencional de la cuenta para operaciones Git mediante HTTPS.

**Solución:** Se generaron Personal Access Tokens (PAT) con los permisos correspondientes y se utilizaron para autenticar las operaciones de sincronización.

**Problema 2. Rechazo de actualización por diferencias entre ramas**

En uno de los equipos se presentó el error `non-fast-forward`, debido a que la rama local contenía un commit duplicado mientras que GitHub tenía cambios más recientes.

**Solución:** Se actualizó la información remota mediante `git fetch`, se compararon los archivos modificados, se creó una rama de respaldo y se sincronizó la rama principal con `origin/main`, conservando los cambios originales del repositorio.

# 4. Avance funcional

En esta primera etapa se completó principalmente el análisis, diseño y documentación del sistema.

**Elementos desarrollados:**

- Repositorio GitHub organizado.
- Descripción y análisis del proyecto.
- Modelo de datos y diagrama entidad-relación.
- Definición inicial de los endpoints REST.
- Documentación del proceso de reservación.
- Propuesta de interfaces y navegación.

**Funcionalidades pendientes de implementación:**

- Registro e inicio de sesión de usuarios.
- Consulta y búsqueda de hoteles.
- Administración de habitaciones y disponibilidad.
- Creación y gestión de reservaciones.
- Temporizador de pago simulado de 10 minutos.
- Confirmación y cancelación de reservaciones.
- Historial de estancias.
- Panel administrativo.
- Conexión entre frontend, backend y base de datos.

En esta etapa todavía no se cuenta con una versión funcional integrada del sistema. Los avances corresponden a la documentación y definición de su estructura.

# 5. Uso de IA generativa

Durante esta etapa se utilizó ChatGPT como herramienta de apoyo para la organización y documentación del proyecto.

- **Herramienta utilizada:** ChatGPT.
- **Actividad para la que se utilizó:** Orientación sobre Git y GitHub, organización de documentación, definición de interfaces y redacción de documentos del proyecto.
- **Código o contenido generado:** Propuestas de estructura y contenido para la documentación de interfaces y navegación, además de explicaciones de comandos y procedimientos de Git.
- **Modificaciones realizadas por el equipo:** Revisión, adaptación e incorporación de la documentación al repositorio de TwoVago.
- **Forma en que se verificó el resultado:** Revisión de los archivos en VS Code, comprobación de los commits y sincronización con GitHub.

El uso de IA para consultas y documentación deberá registrarse también en los formatos individuales correspondientes. La generación e integración de pruebas se documentará cuando se realice durante las siguientes etapas.

# 6. Participación individual

**Jesús Alfredo Cobos Valencia:**

Responsable de la configuración inicial del repositorio, análisis y diseño del proyecto, modelo de datos, diagrama entidad-relación, definición de endpoints y documentación general inicial.

**Emanuel Hernández Ochoa:**

Responsable de documentar el flujo de reservaciones, incluyendo los estados de las operaciones, el proceso de pago simulado y el vencimiento de las reservaciones pendientes.

**Francisco Báez Rodríguez:**

Responsable de documentar el diseño de interfaces y la navegación de TwoVago, definiendo las pantallas principales, sus elementos, funcionalidades y relación con los servicios del backend.

# 7. Próximas actividades

- Completar la documentación de responsabilidades individuales del equipo.
- Revisar la consistencia entre el modelo de datos, los endpoints y el flujo de reservaciones.
- Preparar los registros individuales de uso de IA generativa.
- Configurar el entorno de desarrollo backend con Java y Spring Boot desde WSL.
- Preparar la conexión con MySQL mediante Hibernate/JPA.
- Iniciar la implementación y las pruebas de los primeros endpoints REST.
- Preparar el Release correspondiente al primer hito en GitHub.

---

## Observaciones adicionales

El equipo ha establecido una división de actividades para facilitar el desarrollo paralelo del proyecto. Cada integrante cuenta con contribuciones identificables mediante commits realizados desde su propia cuenta de GitHub.

Los documentos elaborados durante esta etapa servirán como referencia para la implementación del backend, frontend y base de datos.

El desarrollo continuará mediante integraciones periódicas entre los módulos para mantener la compatibilidad de sus componentes.