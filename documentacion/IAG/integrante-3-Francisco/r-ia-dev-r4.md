# Registro de uso de IA generativa para desarrollo

## 1. Datos de la sesión

- **Proyecto:** TwoVago
- **Integrante responsable:** Francisco Báez Rodríguez
- **Fecha:** 18 de noviembre de 2026
- **Herramienta:** ChatGPT.
- **Modelo/temperatura:** GPT-6 / No aplica.

## 2. Prompt original

Durante el desarrollo del entregable 4 se realizó la siguiente consulta a ChatGPT:

> Necesito crear el archivo styles.css para la página principal de mi proyecto TwoVago, una plataforma web para buscar hoteles. Quiero un diseño sencillo, usando CSS básico y fácil de entender, similar a los ejercicios que hemos trabajado en clase. Debe tener estilos para el encabezado, el contenido principal, las secciones, el campo de búsqueda, los botones y las tarjetas donde se mostrarán los hoteles. No utilices técnicas avanzadas.

## 3. Respuesta de IA

ChatGPT proporcionó una propuesta de estilos CSS básicos para la página principal de TwoVago.

La propuesta incluyó estilos para el cuerpo de la página, encabezado, contenido principal, secciones, campos de entrada, botones y tarjetas de hoteles.

El código generado fue:

```css
body {
    font-family: Arial, sans-serif;
    margin: 0;
    background-color: #f2f2f2;
}

header {
    background-color: #333;
    color: white;
    padding: 20px;
    text-align: center;
}

main {
    width: 80%;
    margin: 30px auto;
}

section {
    background-color: white;
    padding: 20px;
    margin-bottom: 20px;
    border-radius: 8px;
}

input {
    padding: 10px;
    width: 250px;
}

button {
    padding: 10px 20px;
    cursor: pointer;
}

.hotel {
    border: 1px solid #ccc;
    padding: 15px;
    margin-top: 10px;
    border-radius: 5px;
}

.hotel h3 {
    margin-top: 0;
}
```

El código se mantuvo sencillo para utilizarlo con la estructura HTML del frontend y con los conocimientos trabajados durante el curso.

## 4. Código anterior

- **Commit:** La versión anterior corresponde al estado del proyecto antes de incorporar los cambios realizados en `frontend/css/styles.css`.
- **Archivo:** `frontend/css/styles.css`.
- **Integrante:** Francisco Báez Rodríguez.

## 5. Código posterior

El código generado por IA se incorporó al archivo:

`frontend/css/styles.css`

El commit correspondiente se registrará después de integrar y comprobar el cambio dentro del proyecto.

## 6. Diferencias entre versiones

La diferencia entre la versión anterior y la versión modificada se comprobará mediante:

```bash
git diff --numstat COMMIT_ANTERIOR COMMIT_POSTERIOR
```

El cambio corresponde principalmente a la incorporación de estilos para la página principal del frontend, incluyendo el encabezado, secciones, campo de búsqueda, botones y tarjetas de hoteles.

## 7. Prueba de funcionamiento

- **Prueba realizada:** Comprobación de la aplicación de los estilos CSS sobre la página principal del frontend de TwoVago.
- **Evidencia:** Captura de pantalla del frontend mostrando la estructura con los estilos aplicados.

La prueba permitirá comprobar que los estilos se carguen correctamente desde `frontend/css/styles.css` y que los elementos definidos en `index.html` reciban los estilos correspondientes.

## 8. Código de uso

Actividades correspondientes al uso de IA:

- **Generación:** Código CSS inicial para el diseño del frontend.
- **Modificación:** Incorporación del código generado al archivo `styles.css`.
- **Explicación:** Orientación sobre la aplicación de estilos a los elementos HTML.
- **Diseño:** Propuesta de la presentación visual de la página principal.
- **Revisión:** Comprobación de que los estilos correspondan con la estructura del frontend.

Las respuestas proporcionadas fueron revisadas y adaptadas durante el desarrollo del proyecto antes de incorporarse al código.

## 9. Reflexión del estudiante

Durante este desarrollo aprendí a relacionar los elementos HTML de la página con estilos CSS para mejorar la presentación del frontend. El apoyo de ChatGPT permitió obtener una base sencilla para comenzar el diseño, pero fue necesario revisar que los estilos fueran adecuados para la estructura que ya tenía el proyecto. Esto me ayudó a comprender mejor la función de las clases CSS y la forma en que se aplican a los elementos de la página.