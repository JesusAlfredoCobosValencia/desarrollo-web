USE twovago;

-- Primero registra la cuenta desde POST /api/auth/registro.
-- Sustituye el correo del ejemplo por el correo exacto que registraste.
UPDATE usuarios
SET rol = 'ADMIN'
WHERE correo = 'admin@twovago.local';

SELECT id_usuario, nombre, correo, rol
FROM usuarios
WHERE correo = 'admin@twovago.local';
