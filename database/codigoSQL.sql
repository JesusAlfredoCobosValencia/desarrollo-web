##parte del ROOT
CREATE DATABASE IF NOT EXISTS twovago
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'twovago_app'@'localhost'
IDENTIFIED BY 'tv12345';

GRANT ALL PRIVILEGES ON twovago.*
TO 'twovago_app'@'localhost';

FLUSH PRIVILEGES;

SHOW DATABASES;

SELECT user, host, plugin
FROM mysql.user
WHERE user = 'twovago_app';

CREATE USER IF NOT EXISTS 'twovago_app'@'%'
IDENTIFIED WITH caching_sha2_password
BY 'tv12345';

ALTER USER 'twovago_app'@'%'
IDENTIFIED WITH caching_sha2_password
BY 'tv12345';

GRANT ALL PRIVILEGES ON twovago.*
TO 'twovago_app'@'%';

FLUSH PRIVILEGES;


##parte de users
USE twovago;
SHOW TABLES;
SELECT * FROM hoteles;
DESCRIBE hoteles;

USE twovago;

INSERT INTO hoteles
(activo, categoria, ciudad, descripcion, direccion, imagen_url, nombre, servicios)
VALUES
(1, 3, 'Xalapa',
 'Hotel de prueba ubicado en el centro de la ciudad.',
 'Centro, Xalapa, Veracruz',
 NULL,
 'Hotel Central',
 'WiFi, estacionamiento, restaurante'),

(1, 4, 'Veracruz',
 'Hotel de prueba cercano a la zona costera.',
 'Zona Centro, Veracruz, Veracruz',
 NULL,
 'Hotel del Puerto',
 'WiFi, alberca, restaurante'),

(1, 5, 'Boca del Río',
 'Hotel de prueba con instalaciones para vacaciones.',
 'Boca del Río, Veracruz',
 NULL,
 'Hotel Costa Azul',
 'WiFi, alberca, gimnasio');
 
 SELECT * FROM hoteles;