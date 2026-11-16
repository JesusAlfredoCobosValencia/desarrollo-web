USE twovago;

-- Habitaciones de demostración para los tres hoteles de ejemplo.
-- El WHERE NOT EXISTS evita duplicarlas si el script se ejecuta más de una vez.

INSERT INTO habitaciones
    (id_hotel, numero, tipo, capacidad, precio_noche, descripcion, activa)
SELECT h.id_hotel, '101', 'Doble', 2, 850.00,
       'Habitación doble de demostración', 1
FROM hoteles h
WHERE h.id_hotel = 1
  AND NOT EXISTS (
      SELECT 1 FROM habitaciones x
      WHERE x.id_hotel = h.id_hotel AND x.numero = '101'
  );

INSERT INTO habitaciones
    (id_hotel, numero, tipo, capacidad, precio_noche, descripcion, activa)
SELECT h.id_hotel, '102', 'Sencilla', 1, 650.00,
       'Habitación sencilla de demostración', 1
FROM hoteles h
WHERE h.id_hotel = 1
  AND NOT EXISTS (
      SELECT 1 FROM habitaciones x
      WHERE x.id_hotel = h.id_hotel AND x.numero = '102'
  );

INSERT INTO habitaciones
    (id_hotel, numero, tipo, capacidad, precio_noche, descripcion, activa)
SELECT h.id_hotel, '201', 'Doble', 2, 950.00,
       'Habitación doble de demostración', 1
FROM hoteles h
WHERE h.id_hotel = 2
  AND NOT EXISTS (
      SELECT 1 FROM habitaciones x
      WHERE x.id_hotel = h.id_hotel AND x.numero = '201'
  );

INSERT INTO habitaciones
    (id_hotel, numero, tipo, capacidad, precio_noche, descripcion, activa)
SELECT h.id_hotel, '301', 'Familiar', 4, 1450.00,
       'Habitación familiar de demostración', 1
FROM hoteles h
WHERE h.id_hotel = 3
  AND NOT EXISTS (
      SELECT 1 FROM habitaciones x
      WHERE x.id_hotel = h.id_hotel AND x.numero = '301'
  );

SELECT id_habitacion, id_hotel, numero, tipo, capacidad, precio_noche, activa
FROM habitaciones
ORDER BY id_hotel, numero;
