# Registro de uso de IA generativa para desarrollo

## 1. Datos de la sesión

- **Proyecto:** TwoVago
- **Integrante responsable:** Jesús Alfredo Cobos Valencia
- **Fecha:** 15 y 16 de noviembre de 2026
- **Herramienta:** ChatGPT.
- **Modelo/temperatura:** GPT-6 / No aplica.

## 2. Prompt original

Durante el desarrollo del entregable 3 se realizaron las siguientes consultas a ChatGPT:

1. Revisa el avance de mi proyecto TwoVago y dime qué hace falta exclusivamente para completar el entregable 3, tomando en cuenta lo que ya se implementó en el entregable anterior.

2. Ayúdame a revisar la implementación de habitaciones en Spring Boot. Quiero comprobar que el modelo, controlador, servicio y repositorio estén relacionados correctamente y que funcionen las operaciones de registrar, consultar, actualizar y desactivar habitaciones.

3. Estoy trabajando con el registro de usuarios. Ayúdame a revisar cómo implementar BCrypt para guardar las contraseñas cifradas, evitar correos duplicados y validar los datos antes de registrar un usuario.

4. Necesito probar todas las funcionalidades del entregable 3, pero hacer cada petición con CURL me quita mucho tiempo. Ayúdame a preparar un script que ejecute las pruebas automáticamente y genere un archivo TXT con los resultados.

5. Revisa los resultados de mi archivo resultados-pruebas-e3.txt y dime cuáles pruebas están fallando, cuáles funcionan correctamente y qué errores debo corregir.

6. Ayúdame a revisar qué capturas de pantalla necesito para documentar el entregable 3, tomando en cuenta las evidencias que ya tengo guardadas en la carpeta de evidencias.

7. Necesito que adaptes el reporte del entregable 3 para que tenga la misma estructura y presentación que el reporte del entregable 2, agregando las nuevas funcionalidades, archivos implementados y rutas de las evidencias.

## 3. Respuesta de IA

ChatGPT proporcionó ejemplos de código y orientación para revisar e implementar los componentes del backend utilizando Java, Spring Boot y MySQL.

Se brindó apoyo en la implementación del módulo de usuarios, incluyendo entidades, repositorios, servicios, DTO, controladores y configuración de BCrypt.

También se proporcionaron indicaciones para revisar las operaciones de hoteles y habitaciones, implementar el manejo de sesiones, desarrollar los módulos de reservaciones y pagos simulados, y corregir problemas encontrados durante las pruebas.

Para comprobar el funcionamiento del sistema se recibió apoyo en la preparación y revisión de una batería de pruebas automatizadas mediante Python y Bash, que genera un archivo TXT con los resultados.

Finalmente, se proporcionó orientación para organizar las evidencias y actualizar la documentación correspondiente al entregable 3.

## 4. Código anterior

- **Commit:** `3340269`
- **Descripción:** Backend básico correspondiente a R2.
- **Integrante:** Jesús Alfredo Cobos Valencia.

## 5. Código posterior

- **Commit:** `cab9cbb`
- **Descripción:** Avance del backend correspondiente a R3.
- **Integrante:** Jesús Alfredo Cobos Valencia.

Como parte del desarrollo también se realizaron los siguientes commits:

- `826cbe4`: incorporación del módulo de usuarios y autenticación.
- `cab9cbb`: integración de reservaciones, pagos, sesiones y validaciones.

## 6. Diferencias entre versiones

Se utilizó el siguiente comando para comparar los cambios entre R2 y R3:

```bash
git diff --numstat 3340269 cab9cbb
```

Entre los cambios identificados se encuentran:

| Archivo | Líneas agregadas | Líneas eliminadas |
|---|---:|---:|
| Usuario.java | 104 | 0 |
| UsuarioService.java | 80 | 0 |
| ReservacionService.java | 215 | 0 |
| PagoService.java | 84 | 0 |
| ReservacionRepository.java | 109 | 0 |
| pruebas_entregable3.py | 481 | 0 |
| validar_entregable3.sh | 98 | 0 |
| documentacion/endpoints.md | 292 | 279 |

Los cambios también incluyeron controladores, DTO, entidades, repositorios, configuración de seguridad, archivos SQL y documentación.

El commit `826cbe4` registró 21 archivos modificados, con 430 inserciones y 1 eliminación.

El commit `cab9cbb` registró 45 archivos modificados, con 3042 inserciones y 872 eliminaciones.

## 7. Prueba de funcionamiento

- **Prueba realizada:** Ejecución de la batería de pruebas automatizadas del entregable 3 mediante el script `scripts/validar_entregable3.sh`, utilizando el backend en el puerto 8083.

- **Evidencia:** Archivo `documentacion/reportes/resultados-pruebas-e3.txt`.

Resultados obtenidos:

| Resultado | Cantidad |
|---|---:|
| Pruebas correctas | 55 |
| Pruebas fallidas | 0 |
| Pruebas omitidas | 0 |
| Total | 55 |

Las pruebas comprobaron el registro e inicio de sesión, consulta y actualización de perfiles, permisos de usuarios, disponibilidad de habitaciones, reservaciones, pagos simulados, cancelaciones, expiraciones y operaciones administrativas de hoteles y habitaciones.

## 8. Código de uso

Actividades correspondientes al uso de IA:

- **Generación:** Ejemplos de código para componentes del backend y automatización de pruebas.
- **Modificación:** Sugerencias para adaptar controladores, servicios y repositorios.
- **Depuración:** Identificación y corrección de errores de compilación, conexión y solicitudes HTTP.
- **Explicación:** Orientación sobre BCrypt, sesiones HTTP, roles y operaciones de reservación.
- **Diseño:** Organización de las evidencias y estructura de la documentación.
- **Revisión:** Comprobación de componentes y revisión de resultados.
- **Documentación:** Apoyo en la elaboración del reporte R3 y los registros de IA.
- **Pruebas:** Preparación y revisión de pruebas automatizadas para los endpoints.

Las respuestas proporcionadas fueron revisadas y adaptadas durante el desarrollo del proyecto. Los cambios se incorporaron y comprobaron mediante la ejecución del backend y las pruebas automatizadas.

## 9. Reflexión del estudiante

Durante el desarrollo del tercer entregable aprendí a trabajar con mayor profundidad en el backend utilizando Spring Boot y MySQL. Se presentaron dificultades con la conexión a la base de datos, la autenticación y la configuración de las pruebas, pero se fueron resolviendo mediante revisiones y ajustes en el código. El apoyo de ChatGPT me permitió comprender mejor algunos errores y encontrar alternativas para implementar las funcionalidades. Al finalizar, logré comprobar el funcionamiento de los módulos mediante 55 pruebas automatizadas, todas correctas. Este proceso me ayudó a comprender mejor la integración de los componentes, el manejo de sesiones, los permisos de usuario y la importancia de probar cada funcionalidad.