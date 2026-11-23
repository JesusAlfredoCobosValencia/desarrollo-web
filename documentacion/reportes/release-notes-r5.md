# TwoVago — Release R5

**Versión:** R5 — Pruebas funcionales y validación del sistema  
**Fecha:** 22 de noviembre de 2026  
**Repositorio:** https://github.com/JesusAlfredoCobosValencia/desarrollo-web

---

## 1. Descripción de la versión

Esta quinta versión de TwoVago se enfoca en la comprobación del funcionamiento general de la plataforma mediante pruebas funcionales realizadas desde el navegador.

Se verificaron los principales procesos disponibles para los clientes, incluyendo el registro, inicio de sesión, administración del perfil, búsqueda de hoteles, consulta de habitaciones, disponibilidad, creación de reservaciones, pagos simulados, cancelaciones e historial de estancias.

También se comprobaron las restricciones de acceso a las secciones privadas cuando el usuario no ha iniciado sesión y el manejo de situaciones como credenciales incorrectas y registros duplicados.

Durante esta entrega se incorporaron evidencias funcionales faltantes y se realizaron correcciones de detalles relacionados con la documentación y las pruebas de R5.

## 2. Elementos incluidos

### Pruebas funcionales de clientes

- Registro de nuevas cuentas.
- Inicio y cierre de sesión.
- Consulta del perfil.
- Actualización de información personal.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones.
- Consulta de disponibilidad según fechas y número de huéspedes.
- Creación de reservaciones.
- Confirmación de pagos simulados.
- Consulta de reservaciones propias.
- Cancelación de reservaciones.
- Consulta de cancelaciones.
- Consulta del historial de estancias.

### Validaciones y control de acceso

- Comprobación del manejo de errores durante el inicio de sesión.
- Validación de registros duplicados.
- Restricción de acceso al perfil sin una sesión iniciada.
- Restricción de acceso a las reservaciones sin una sesión iniciada.
- Restricción de acceso al historial sin una sesión iniciada.
- Restricción de acceso a las cancelaciones sin una sesión iniciada.

### Correcciones y evidencias

- Incorporación de evidencias funcionales faltantes.
- Corrección de detalles de la entrega R5.
- Organización de las capturas de acuerdo con los procesos comprobados.
- Actualización del registro de evidencias del proyecto.

## 3. Procesos verificados

Las pruebas de R5 comprueban las operaciones implementadas durante las entregas anteriores.

Se verificaron los siguientes módulos:

| Módulo | Operaciones comprobadas |
|---|---|
| Usuarios | Registro, inicio y cierre de sesión. |
| Perfil | Consulta y actualización de datos personales. |
| Hoteles | Búsqueda y consulta de hoteles. |
| Habitaciones | Consulta de habitaciones y disponibilidad. |
| Reservaciones | Creación y consulta de reservaciones. |
| Pagos | Consulta y confirmación de pagos simulados. |
| Cancelaciones | Cancelación y consulta de reservaciones canceladas. |
| Historial | Consulta de estancias registradas. |
| Seguridad | Restricciones de acceso a secciones privadas sin autenticación. |

## 4. Tecnologías utilizadas

- HTML5.
- CSS.
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

- Ejecución de pruebas funcionales correspondientes a R5.
- Preparación e incorporación de evidencias de funcionamiento.
- Registro de las pruebas realizadas en el repositorio.

Commit relacionado: `fe3c214` — evidencias: pruebas funcionales R5.

### Francisco Báez Rodríguez

- Incorporación de evidencias faltantes de las pruebas funcionales.
- Corrección de detalles correspondientes a la quinta entrega.
- Actualización de los archivos relacionados con las evidencias.

Commits relacionados:

- `658b4c4` — evidencias faltantes de pruebas funcionales R5.
- `77a8449` — corrección de detalles R5.

### Emanuel Hernández Ochoa

- Elaboración y actualización de la documentación correspondiente a R5.
- Consolidación de los registros de evidencias.
- Preparación de la documentación de cierre de la quinta entrega.



## 6. Pruebas y evidencias

Se realizaron pruebas funcionales desde el navegador para comprobar el comportamiento de los módulos principales de TwoVago.

Se verificaron los siguientes procesos:

- Registro e inicio de sesión de clientes.
- Consulta y actualización de perfiles.
- Búsqueda de hoteles por ciudad.
- Consulta de habitaciones y disponibilidad.
- Creación de reservaciones.
- Confirmación de pagos simulados.
- Consulta de reservaciones.
- Cancelación de reservaciones.
- Consulta del historial de estancias.
- Cierre de sesión.
- Manejo de errores de autenticación.
- Validación de registros duplicados.
- Restricción de acceso a secciones privadas sin iniciar sesión.

Las pruebas se documentaron mediante 20 capturas identificadas como `E5_01` a `E5_20`, almacenadas en:

`documentacion/reportes/evidencias/`

## 7. Limitaciones conocidas

- Los pagos son simulados y no procesan transacciones bancarias reales.
- La ejecución y configuración del sistema corresponden al entorno local de desarrollo.
- Las pruebas documentadas corresponden a los flujos funcionales comprobados durante esta entrega.
- Las evidencias visuales documentan los resultados obtenidos durante las pruebas realizadas desde el navegador.

## 8. Próxima etapa

Las actividades posteriores estarán enfocadas en:

1. Completar y revisar la documentación general del proyecto.
2. Consolidar los registros individuales de uso de IA.
3. Verificar las instrucciones de instalación y ejecución del README.
4. Preparar el video de funcionamiento general del sistema.
5. Organizar la entrega final y sus evidencias.

---

**Estado de la versión:** Sistema integrado con sus principales funcionalidades comprobadas mediante pruebas funcionales desde el navegador. Se incorporaron 20 evidencias correspondientes a la quinta entrega y se realizaron correcciones de detalles de documentación.