# TwoVago — Análisis y diseño

## 1. Descripción del proyecto

TwoVago es una plataforma web de búsqueda y reservación de hoteles. Permitirá consultar diferentes establecimientos registrados en una base de datos propia, comparar precios, conocer sus características y seleccionar habitaciones disponibles.

Los usuarios podrán crear una cuenta, realizar reservaciones y consultar su perfil, historial de estancias y cancelaciones. El sistema contará con un mecanismo de pago simulado con tiempo límite para confirmar las reservaciones.

También tendrá un módulo administrativo para gestionar hoteles, habitaciones y consultar las reservaciones realizadas.

## 2. Problemática

La búsqueda de alojamiento requiere consultar diferentes opciones de hospedaje, comparar precios y verificar la disponibilidad de habitaciones.

Además, las reservaciones necesitan un seguimiento que permita identificar cuáles están pendientes de pago, confirmadas, canceladas o vencidas.

TwoVago busca reunir estas operaciones en una sola plataforma para facilitar la búsqueda y administración de reservaciones.

## 3. Justificación

Se propone desarrollar TwoVago para facilitar la búsqueda y reservación de habitaciones de hotel mediante una plataforma que concentre información sobre establecimientos, precios, características y disponibilidad. El sistema permitirá gestionar las reservaciones desde una misma aplicación y dar seguimiento a sus estados mediante un proceso de pago simulado con tiempo límite. Además, su desarrollo permitirá aplicar los conocimientos adquiridos durante el curso sobre interfaces web, servicios REST, programación backend, bases de datos y comunicación entre aplicaciones.

## 4. Objetivo general

Desarrollar una aplicación web que permita buscar hoteles, consultar habitaciones y gestionar reservaciones mediante una arquitectura de tres capas, utilizando Java con Spring Boot, MySQL, Hibernate/JPA, HTML, CSS y JavaScript.

## 5. Actores del sistema

### Cliente

- Registrarse e iniciar sesión.
- Consultar hoteles y habitaciones.
- Buscar alojamiento por destino y fechas.
- Realizar reservaciones.
- Confirmar pagos simulados.
- Consultar sus reservaciones.
- Cancelar reservaciones.
- Consultar su historial de estancias y cancelaciones.

### Administrador

- Registrar hoteles.
- Modificar información de hoteles.
- Eliminar hoteles cuando no tengan reservaciones que lo impidan.
- Registrar y administrar habitaciones.
- Modificar precios y características.
- Consultar las reservaciones del sistema.

## 6. Requerimientos funcionales

- RF01. El sistema permitirá registrar usuarios.
- RF02. Permitirá iniciar y cerrar sesión.
- RF03. Permitirá consultar los hoteles registrados.
- RF04. Permitirá buscar hoteles por ciudad, fechas y número de huéspedes.
- RF05. Mostrará información, fotografías y servicios de cada hotel.
- RF06. Permitirá consultar las habitaciones disponibles.
- RF07. Permitirá crear reservaciones.
- RF08. Generará un folio para cada reservación.
- RF09. Implementará un pago simulado con un límite de 10 minutos.
- RF10. Confirmará las reservaciones cuando se complete el pago dentro del plazo.
- RF11. Expirará automáticamente las reservaciones que no se paguen a tiempo.
- RF12. Permitirá cancelar reservaciones confirmadas.
- RF13. Permitirá consultar las reservaciones pendientes y confirmadas.
- RF14. Permitirá consultar el historial de estancias completadas.
- RF15. Permitirá consultar las reservaciones canceladas y expiradas.
- RF16. Permitirá al administrador gestionar hoteles y habitaciones.

## 7. Requerimientos no funcionales

- El backend se desarrollará con Java y Spring Boot.
- El frontend utilizará HTML, CSS y JavaScript.
- La información se almacenará en MySQL mediante Hibernate/JPA.
- La comunicación entre frontend y backend se realizará mediante una API REST y Fetch.
- Las contraseñas se almacenarán de forma segura mediante hash.
- El sistema validará los datos antes de almacenarlos.
- Las operaciones administrativas estarán restringidas al administrador.
- El proyecto deberá ejecutarse desde WSL.
- El código fuente se mantendrá en GitHub.

## 8. Reglas de negocio

1. Para realizar una reservación, el usuario deberá iniciar sesión.
2. La fecha de salida deberá ser posterior a la fecha de entrada.
3. La capacidad de la habitación deberá ser suficiente para los huéspedes.
4. Una habitación no podrá tener dos reservaciones activas que coincidan en fechas.
5. Al crear una reservación, se generará un plazo de 10 minutos para confirmar el pago.
6. Si el plazo termina sin confirmación, la reservación cambiará a EXPIRADA.
7. Una reservación expirada dejará de bloquear la disponibilidad de la habitación.
8. Las reservaciones confirmadas podrán cancelarse y conservarán su registro.
9. El historial de estancias mostrará las reservaciones cuya estancia haya concluido.
10. Cada usuario solamente podrá consultar y administrar sus propias reservaciones.
11. Los pagos serán simulados y no se realizarán transacciones bancarias reales.

## 9. Alcance del proyecto

TwoVago contará inicialmente con un catálogo de hoteles de demostración almacenados en su propia base de datos.

El sistema permitirá gestionar usuarios, hoteles, habitaciones, reservaciones, cancelaciones y pagos simulados.

No se integrarán servicios externos de reservación ni pasarelas de pago reales.