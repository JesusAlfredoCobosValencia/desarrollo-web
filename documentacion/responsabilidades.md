# TwoVago — Distribución de responsabilidades

## 1. Propósito

Este documento establece la distribución de trabajo del equipo para desarrollar TwoVago. Las responsabilidades se organizan por módulos para identificar quién coordina cada parte, facilitar la integración y permitir que cada integrante explique tanto su módulo como el funcionamiento general del sistema.

## 2. Integrantes y responsabilidades

### Jesús — coordinación y catálogo de hoteles

- Coordinar la organización del repositorio y la integración de avances.
- Mantener el análisis, los requerimientos y la documentación general del proyecto.
- Desarrollar el módulo de hoteles: registro administrativo, consulta, detalle y filtros de búsqueda.
- Desarrollar el módulo de habitaciones: consulta, administración y disponibilidad vinculada al catálogo.
- Revisar que el modelo de datos, los endpoints y las interfaces mantengan los mismos nombres y reglas.
- Integrar los cambios en la rama principal y mantener actualizado el README.

### Emanuel — reservaciones y pagos simulados

- Desarrollar el módulo de reservaciones.
- Implementar la validación de fechas, capacidad y disponibilidad de habitaciones.
- Calcular el importe total a partir del número de noches y el precio por noche.
- Generar el folio y el registro de pago simulado al crear una reservación.
- Implementar el plazo de 10 minutos, la confirmación del pago y la expiración automática.
- Implementar la cancelación y los estados del historial de estancias.
- Preparar y ejecutar pruebas de los escenarios de reservación, concurrencia, pago y vencimiento.

### Francisco — usuarios, autenticación e interfaces de cuenta

- Desarrollar el registro, inicio y cierre de sesión.
- Implementar la autenticación, autorización por roles y protección de datos personales.
- Desarrollar la consulta y actualización del perfil.
- Implementar las vistas de cuenta: perfil, mis reservaciones, historial, cancelaciones y expiraciones, coordinándose con el módulo de reservaciones.
- Mantener la navegación y los componentes compartidos del frontend en coordinación con Jesús y Emanuel.
- Preparar y ejecutar pruebas de registro, acceso, permisos y privacidad de los datos.

## 3. Responsabilidades compartidas

Los tres integrantes deberán:

- Conocer el flujo completo de búsqueda y reservación.
- Revisar las interfaces y los contratos de la API antes de integrar cambios.
- Realizar pruebas de integración entre sus módulos.
- Resolver conflictos de integración y documentar las decisiones técnicas.
- Registrar commits descriptivos desde sus propias cuentas de GitHub, correspondientes al trabajo que efectivamente realizaron.
- Participar en la preparación de los reportes, la documentación final y el video de demostración.

## 4. Evidencia de participación

Al corte mostrado en el historial de GitHub del 10 de noviembre de 2026, se observan contribuciones de los tres integrantes:

- Jesús: estructura inicial, análisis, modelo de datos y diagrama ER, README y definición de endpoints.
- Emanuel: actualización del README y diagrama, y documentación del flujo de reservaciones.
- Francisco: documentación del diseño de interfaces y navegación.

Esta distribución de módulos establece las responsabilidades para el desarrollo posterior. No modifica la autoría de los documentos ni de los commits anteriores. Cada integrante deberá continuar registrando sus avances con su propia cuenta de GitHub y enlazar los commits correspondientes en los reportes de cada hito.

## 5. Coordinación e integración

1. Antes de modificar archivos compartidos, cada integrante actualizará su copia local con `git pull`.
2. Los cambios de cada módulo se revisarán antes de integrarlos a `main`.
3. Si dos integrantes modifican el mismo archivo, coordinarán el cambio para evitar sobrescrituras.
4. Las pruebas de integración se realizarán cuando estén disponibles los módulos correspondientes.
5. Los reportes R1, R2, R3 y R4 documentarán las actividades, problemas, evidencias y participación de cada integrante.
