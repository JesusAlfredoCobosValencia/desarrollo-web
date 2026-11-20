# Registro de uso de IA generativa — R4
**Proyecto:** TwoVago  
**Integrante:** Jesús Alfredo Cobos Valencia  
**Entrega:** R4 — Frontend e integración del sistema  
**Fecha:** 19 de noviembre de 2026

## 1. Herramienta utilizada

- **Herramienta:** ChatGPT.
- **Modelo:** GPT-6.
- **Tipo de actividad:** Apoyo en pruebas, explicación 

## 2. Prompt original

Durante las pruebas de funcionamiento se realizaron consultas relacionadas con los problemas de autenticación y la comprobación de la base de datos.

prompts utilizados fueron:

- «tengo este error al entrar en la base de datos».
- «Entonces, ¿qué cambio? No entendí».

También se compartió el contenido de `frontend/js/api.js` para revisar que los endpoints documentados correspondieran con las funciones utilizadas por el frontend.

## 3. Respuesta de IA

ChatGPT proporcionó orientación para revisar la configuración de conexión, consultar los registros de usuarios mediante MySQL y analizar las respuestas HTTP obtenidas durante las pruebas.

Se revisaron los errores HTTP 401, relacionados con intentos de autenticación fallidos, y HTTP 409, correspondiente al intento de registrar un correo electrónico que ya estaba registrado.

Posteriormente, se comprobó que el acceso administrativo funcionaba utilizando la cuenta existente en la base de datos.

También se recibió apoyo para comparar los endpoints de `api.js` con los registrados en las Release Notes R4 y corregir las rutas y parámetros documentados.

## 4. Código anterior

**Commit base:** `d478f4b` — Frontend terminado.

Este commit corresponde a la versión disponible antes de incorporar las modificaciones propias de documentación y evidencias de Jesús.

## 5. Código posterior

Pendiente de generar el commit individual de R4.

En esta participación no se realizaron modificaciones al código fuente del sistema. Los cambios propios corresponden a documentación y evidencias de pruebas.

## 6. Diferencias entre versiones

No se reportan diferencias de código fuente atribuibles al uso de IA por parte de Jesús durante esta etapa.

Los archivos documentales y las capturas de evidencia se incorporarán mediante un commit propio. El registro de diferencias se actualizará con los datos reales de Git después de generar dicho commit.

## 7. Pruebas de funcionamiento

Se realizaron pruebas manuales desde el navegador para comprobar el funcionamiento del sistema como cliente y administrador.

Se verificaron los siguientes procesos:

- Inicio de sesión administrativo.
- Acceso al panel administrativo.
- Consulta y administración de hoteles.
- Administración de habitaciones.
- Consulta general de reservaciones.
- Registro de clientes.
- Inicio y cierre de sesión de clientes.
- Consulta de habitaciones y disponibilidad.
- Creación y confirmación de reservaciones.
- Cancelación de reservaciones.
- Consulta del historial de estancias.
- Consulta de cancelaciones y reservaciones expiradas.
- Consulta y actualización del perfil.
- Validación de errores de autenticación.

**Evidencias:** 18 capturas identificadas de `E4_01` a `E4_18`, almacenadas en `documentacion/reportes/evidencias/`.

## 8. Código de uso de IA

Las actividades en las que se utilizó ChatGPT fueron:

- Explicación: revisión de consultas MySQL y respuestas HTTP.
- Pruebas: apoyo para organizar y comprobar los flujos funcionales.

No se incorporó código generado por IA al frontend o backend como parte de estas actividades.

## 9. Reflexión del estudiante

El uso de ChatGPT permitió organizar las pruebas y comprender mejor algunos errores que aparecieron durante la ejecución del sistema. La revisión de los registros en MySQL ayudó a identificar que el problema de acceso se relacionaba con las credenciales utilizadas y no con la ausencia del usuario en la base de datos.

También se comprobó la importancia de contrastar la documentación con las funciones reales del frontend. Finalmente, las pruebas realizadas permitieron verificar los procesos principales de TwoVago y documentarlos mediante las 18 evidencias de R4.