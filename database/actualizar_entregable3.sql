USE twovago;

CREATE TABLE IF NOT EXISTS reservaciones (
    id_reservacion BIGINT NOT NULL AUTO_INCREMENT,
    folio VARCHAR(30) NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_habitacion BIGINT NOT NULL,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    cantidad_huespedes INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_limite_pago DATETIME NOT NULL,
    fecha_cancelacion DATETIME NULL,
    PRIMARY KEY (id_reservacion),
    UNIQUE KEY uk_reservacion_folio (folio),
    KEY idx_reservacion_habitacion_fechas (id_habitacion, fecha_entrada, fecha_salida),
    KEY idx_reservacion_usuario_estado (id_usuario, estado),
    CONSTRAINT fk_reservacion_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    CONSTRAINT fk_reservacion_habitacion FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS pagos (
    id_pago BIGINT NOT NULL AUTO_INCREMENT,
    id_reservacion BIGINT NOT NULL,
    folio_pago VARCHAR(30) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo_simulado VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_pago DATETIME NULL,
    PRIMARY KEY (id_pago),
    UNIQUE KEY uk_pago_reservacion (id_reservacion),
    UNIQUE KEY uk_pago_folio (folio_pago),
    CONSTRAINT fk_pago_reservacion FOREIGN KEY (id_reservacion) REFERENCES reservaciones(id_reservacion)
) ENGINE=InnoDB;

SHOW TABLES;
