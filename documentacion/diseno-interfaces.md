# TwoVago — Diseño de interfaces y navegación

## 1. Introducción

El documento describe como es la estructura de las interfaces de TwoVago.

El diseño se basa en ser sencilla e intuitiva para que los usuarios puedan encontrar hoteles, revisar sus características, hacer reservaciones y consultar sus movimientos.

La plataforma tiene clientes y administradores. Cada uno con acceso a funciones correspondendientes a su rol.

## 2. Objetivo del diseño

Definir como se van a organizar las pantallas de TwoVago, sus elementos y las acciones que pueden realizar los usuarios.

La idea es hacer más facil la búsqueda de hoteles, recortar el tiempo de ese proceso de reservación y permitir que los clientes consulten el estado de sus operaciones.

## 3. Estructura general de navegación

La plataforma tendrá las siguientes secciones principales:

- Inicio.
- Resultados de búsqueda.
- Detalles del hotel.
- Registro e inicio de sesión.
- Resumen de reservación.
- Pago simulado.
- Perfil del usuario.
- Historial de estancias.
- Cancelaciones y reservaciones expiradas.
- Panel administrativo.

La navegación estará organizada mediante un menú principal y opciones contextuales que permitirán avanzar o regresar durante el proceso de reservación.

## 4. Descripción de las interfaces

### 4.1. Página de inicio

Será la pantalla principal de TwoVago.

Elementos:
- Logotipo y nombre de la plataforma.
- Menú de navegación.
- Botones de registro e inicio de sesión.
- Buscador de hoteles.
- Campo para ingresar ciudad o destino.
- Fecha de entrada y salida.
- Número de huéspedes.
- Botón para buscar hoteles.

Acciones:
- Buscar alojamientos.
- Acceder al registro.
- Iniciar sesión.
- Consultar las secciones públicas.

### 4.2. Resultados de búsqueda

Mostrará los hoteles disponibles de acuerdo con los criterios de búsqueda.

Elementos:
- Nombre del hotel.
- Imagen representativa.
- Ciudad y ubicación.
- Categoría del establecimiento.
- Descripción breve.
- Servicios principales.
- Precio por noche.
- Botón para consultar detalles.

Filtros previstos:
- Ciudad.
- Precio.
- Categoría del hotel.
- Capacidad de las habitaciones.
- Disponibilidad para las fechas seleccionadas.

Acciones:
- Aplicar filtros.
- Consultar información del hotel.
- Seleccionar un alojamiento.

### 4.3. Detalles del hotel

Presentará la información completa del establecimiento seleccionado.

Elementos:
- Nombre e imágenes del hotel.
- Dirección y ciudad.
- Descripción.
- Categoría.
- Servicios disponibles.
- Listado de habitaciones.
- Precio por noche.
- Capacidad de cada habitación.
- Botón para seleccionar una habitación.

Acciones:
- Consultar habitaciones.
- Revisar precios y características.
- Seleccionar una habitación disponible.

### 4.4. Registro e inicio de sesión

Permitirá que los usuarios creen una cuenta o accedan a una existente.

Registro:
- Nombre completo.
- Correo electrónico.
- Contraseña.
- Número telefónico.
- Botón para crear cuenta.

Inicio de sesión:
- Correo electrónico.
- Contraseña.
- Botón para ingresar.

Acciones:
- Registrar una cuenta.
- Iniciar sesión.
- Mostrar mensajes de validación.
- Acceder al perfil del usuario.

### 4.5. Resumen de reservación

Antes de confirmar la reservación, el sistema mostrará un resumen de los datos seleccionados.

Elementos:
- Nombre del hotel.
- Habitación seleccionada.
- Fecha de entrada.
- Fecha de salida.
- Número de huéspedes.
- Precio por noche.
- Número de noches.
- Importe total.
- Botón para continuar al pago.

Acciones:
- Revisar los datos.
- Regresar a los detalles del hotel.
- Continuar con el proceso de pago.

El usuario deberá iniciar sesión para completar la reservación.

### 4.6. Pantalla de pago simulado

Esta pantalla permitirá confirmar una reservación mediante un proceso de pago ficticio.

Elementos:
- Folio de reservación.
- Nombre del hotel.
- Habitación seleccionada.
- Importe total.
- Métodos de pago simulados.
- Contador regresivo de 10 minutos.
- Botón para confirmar el pago.
- Mensaje con el tiempo restante.

Funcionamiento:

1. Al crearse la reservación, se mostrará el contador de 10 minutos.
2. El usuario seleccionará un método de pago simulado.
3. Podrá confirmar el pago mientras el plazo permanezca vigente.
4. Si el pago se aprueba, se mostrará la confirmación de la reservación.
5. Si el tiempo termina, se notificará que la reservación expiró.

El contador visual deberá mantenerse sincronizado con la fecha límite registrada en el backend. La validación definitiva del vencimiento corresponderá al servidor.

No se procesarán pagos bancarios reales.

### 4.7. Confirmación de reservación

Después de aprobar el pago simulado, se mostrará una pantalla con los datos de la reservación confirmada.

Elementos:
- Mensaje de confirmación.
- Folio de reservación.
- Nombre del hotel.
- Habitación.
- Fechas de estancia.
- Número de huéspedes.
- Importe total.
- Estado de la reservación.
- Botón para consultar mis reservaciones.

### 4.8. Perfil del usuario

Permitirá consultar y administrar la información personal del cliente.

Elementos:
- Nombre.
- Correo electrónico.
- Número telefónico.
- Información de la cuenta.
- Opción para editar datos.
- Acceso a reservaciones.
- Opción para cerrar sesión.

### 4.9. Historial de estancias

Mostrará las reservaciones que hayan finalizado correctamente.

Elementos:
- Nombre del hotel.
- Ciudad.
- Habitación.
- Fecha de entrada.
- Fecha de salida.
- Importe de la reservación.
- Estado COMPLETADA.

El usuario podrá consultar sus estancias anteriores sin que estas aparezcan mezcladas con sus reservaciones actuales.

### 4.10. Cancelaciones y reservaciones expiradas

Esta sección permitirá consultar por separado las operaciones que no finalizaron como una estancia confirmada.

Cancelaciones:
- Nombre del hotel.
- Habitación.
- Fechas de la reservación.
- Fecha de cancelación.
- Estado CANCELADA.

Reservaciones expiradas:
- Hotel seleccionado.
- Fechas solicitadas.
- Importe.
- Estado EXPIRADA.
- Motivo del vencimiento.

Las reservaciones canceladas o expiradas permanecerán disponibles para consulta en el historial del usuario.

### 4.11. Panel administrativo

Será una sección de acceso exclusivo para usuarios con rol ADMIN.

Elementos:
- Menú administrativo.
- Listado de hoteles.
- Registro de nuevos hoteles.
- Edición de información.
- Activación y desactivación de hoteles.
- Administración de habitaciones.
- Consulta de reservaciones.
- Visualización de estados de las operaciones.

El administrador podrá gestionar los establecimientos y consultar las reservaciones registradas en la plataforma.

## 5. Organización del menú de navegación

### Menú público

- Inicio.
- Buscar hoteles.
- Iniciar sesión.
- Registrarse.

### Menú del cliente

- Inicio.
- Buscar hoteles.
- Mi perfil.
- Mis reservaciones.
- Historial de estancias.
- Cancelaciones.
- Cerrar sesión.

### Menú administrativo

- Panel principal.
- Hoteles.
- Habitaciones.
- Reservaciones.
- Cerrar sesión.

Las opciones disponibles dependerán del rol del usuario autenticado.

## 6. Flujo de navegación

El recorrido principal del cliente será:

Inicio → Búsqueda de hoteles → Resultados → Detalles del hotel → Selección de habitación → Inicio de sesión o registro → Resumen de reservación → Pago simulado → Confirmación.

Si el usuario no confirma el pago dentro de los 10 minutos establecidos:

Pago simulado → Vencimiento del tiempo → Reservación EXPIRADA → Liberación de habitación.

Para consultar operaciones anteriores:

Perfil → Historial de estancias.

Perfil → Cancelaciones.

Perfil → Reservaciones expiradas.

El administrador tendrá un recorrido independiente:

Inicio de sesión → Panel administrativo → Gestión de hoteles → Gestión de habitaciones → Consulta de reservaciones.

## 7. Relación de las interfaces con el backend

Las pantallas intercambiarán información con el backend mediante peticiones HTTP y respuestas en formato JSON.

| Interfaz | Endpoint relacionado |
|---|---|
| Registro | POST /api/auth/registro |
| Inicio de sesión | POST /api/auth/login |
| Perfil | GET /api/usuarios/perfil |
| Búsqueda de hoteles | GET /api/hoteles |
| Detalles del hotel | GET /api/hoteles/{id} |
| Habitaciones | GET /api/habitaciones |
| Disponibilidad | GET /api/habitaciones/disponibles |
| Crear reservación | POST /api/reservaciones |
| Consultar reservaciones | GET /api/reservaciones/mis-reservas |
| Confirmar pago | POST /api/pagos/{id}/confirmar |
| Historial | GET /api/reservaciones/historial |
| Cancelaciones | GET /api/reservaciones/cancelaciones |
| Cancelar reservación | PUT /api/reservaciones/{id}/cancelar |

Las interfaces deberán mostrar mensajes de confirmación, validación o error según las respuestas recibidas del servidor.

## 8. Diseño adaptable y accesibilidad

La plataforma deberá adaptarse a diferentes tamaños de pantalla.

Se contemplan las siguientes características:

- Diseño adaptable a computadoras, tabletas y teléfonos.
- Menús sencillos y organizados.
- Botones con etiquetas claras.
- Campos de formulario identificados.
- Contraste suficiente entre texto y fondo.
- Mensajes de error comprensibles.
- Indicadores de carga durante las consultas.
- Confirmaciones antes de cancelar reservaciones.

## 9. Estados de las interfaces

Las pantallas deberán contemplar diferentes situaciones durante su funcionamiento:

- Cargando: cuando se solicite información al servidor.
- Correcto: cuando una operación se complete.
- Sin resultados: cuando no existan hoteles o habitaciones disponibles.
- Error: cuando ocurra un problema durante una operación.
- Pendiente de pago: cuando exista una reservación temporal.
- Confirmada: cuando el pago simulado haya sido aprobado.
- Expirada: cuando termine el tiempo establecido.
- Cancelada: cuando el usuario haya cancelado una reservación.

## 10. Tecnologías previstas

- HTML para la estructura de las páginas.
- CSS para los estilos y la adaptación de las interfaces.
- JavaScript para las interacciones.
- Fetch API para la comunicación con el backend.
- Java y Spring Boot para los servicios REST.
- MySQL para el almacenamiento de la información.

## 11. Alcance del diseño

Este documento establece la propuesta de interfaces, navegación y funcionalidades de TwoVago.

La implementación visual y funcional de las pantallas se realizará durante la etapa de desarrollo frontend, tomando como referencia las características y los endpoints definidos en la documentación del proyecto.