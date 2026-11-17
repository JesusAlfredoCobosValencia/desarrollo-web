-- MySQL dump 10.13  Distrib 8.4.13, for Linux (x86_64)
--
-- Host: localhost    Database: twovago
-- ------------------------------------------------------
-- Server version	8.4.13-0ubuntu0.26.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `twovago`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `twovago` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `twovago`;

--
-- Table structure for table `habitaciones`
--

DROP TABLE IF EXISTS `habitaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitaciones` (
  `id_habitacion` bigint NOT NULL AUTO_INCREMENT,
  `activa` bit(1) NOT NULL,
  `capacidad` int NOT NULL,
  `descripcion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `numero` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio_noche` decimal(10,2) NOT NULL,
  `tipo` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_hotel` bigint NOT NULL,
  PRIMARY KEY (`id_habitacion`),
  UNIQUE KEY `uk_habitacion_hotel_numero` (`id_hotel`,`numero`),
  CONSTRAINT `FKdt09g936qaeh78qp1130b8qnk` FOREIGN KEY (`id_hotel`) REFERENCES `hoteles` (`id_hotel`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitaciones`
--

LOCK TABLES `habitaciones` WRITE;
/*!40000 ALTER TABLE `habitaciones` DISABLE KEYS */;
INSERT INTO `habitaciones` VALUES (1,_binary '',2,'Habitación doble de demostración','101',850.00,'Doble',1),(2,_binary '',1,'Habitación sencilla de demostración','102',650.00,'Sencilla',1),(3,_binary '',2,'Habitación doble de demostración','201',950.00,'Doble',2),(4,_binary '',4,'Habitación familiar de demostración','301',1450.00,'Familiar',3),(5,_binary '\0',2,'Habitación temporal de pruebas','E3-01',750.00,'Doble',4);
/*!40000 ALTER TABLE `habitaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoteles` (
  `id_hotel` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `categoria` int DEFAULT NULL,
  `ciudad` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `direccion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `imagen_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `servicios` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_hotel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoteles`
--

LOCK TABLES `hoteles` WRITE;
/*!40000 ALTER TABLE `hoteles` DISABLE KEYS */;
INSERT INTO `hoteles` VALUES (1,_binary '',3,'Xalapa','Hotel de prueba ubicado en el centro de la ciudad.','Centro, Xalapa, Veracruz',NULL,'Hotel Central','WiFi, estacionamiento, restaurante'),(2,_binary '',4,'Veracruz','Hotel de prueba cercano a la zona costera.','Zona Centro, Veracruz, Veracruz',NULL,'Hotel del Puerto','WiFi, alberca, restaurante'),(3,_binary '',5,'Boca del Río','Hotel de prueba con instalaciones para vacaciones.','Boca del Río, Veracruz',NULL,'Hotel Costa Azul','WiFi, alberca, gimnasio'),(4,_binary '\0',3,'Xalapa','Registro temporal de pruebas E3','Dirección de prueba 123',NULL,'Hotel Prueba Admin 0014678113 Actualizado','WiFi');
/*!40000 ALTER TABLE `hoteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `id_pago` bigint NOT NULL AUTO_INCREMENT,
  `estado` enum('APROBADO','EXPIRADO','PENDIENTE','RECHAZADO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_pago` datetime(6) DEFAULT NULL,
  `folio_pago` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `metodo_simulado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `id_reservacion` bigint NOT NULL,
  PRIMARY KEY (`id_pago`),
  UNIQUE KEY `uk_pago_reservacion` (`id_reservacion`),
  UNIQUE KEY `uk_pago_folio` (`folio_pago`),
  CONSTRAINT `FK8dajdcqx7a457aabrvsue7bwc` FOREIGN KEY (`id_reservacion`) REFERENCES `reservaciones` (`id_reservacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,'APROBADO','2026-11-17 04:06:10.596039','PAGO-670075E5','TARJETA_SIMULADA',1700.00,1),(2,'EXPIRADO',NULL,'PAGO-D4F2732A','SIN_SELECCIONAR',1700.00,2);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservaciones`
--

DROP TABLE IF EXISTS `reservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservaciones` (
  `id_reservacion` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_huespedes` int NOT NULL,
  `estado` enum('CANCELADA','COMPLETADA','CONFIRMADA','EXPIRADA','PENDIENTE_PAGO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_cancelacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_limite_pago` datetime(6) NOT NULL,
  `fecha_salida` date NOT NULL,
  `folio` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `id_habitacion` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_reservacion`),
  UNIQUE KEY `uk_reservacion_folio` (`folio`),
  KEY `idx_reservacion_habitacion_fechas` (`id_habitacion`,`fecha_entrada`,`fecha_salida`),
  KEY `idx_reservacion_usuario_estado` (`id_usuario`,`estado`),
  CONSTRAINT `FK9xt1omb5kxe4ti37n6r80po8g` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FKq7tmoy77gnttejykawp1talqv` FOREIGN KEY (`id_habitacion`) REFERENCES `habitaciones` (`id_habitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservaciones`
--

LOCK TABLES `reservaciones` WRITE;
/*!40000 ALTER TABLE `reservaciones` DISABLE KEYS */;
INSERT INTO `reservaciones` VALUES (1,1,'CANCELADA','2026-11-17 04:06:10.636987','2026-11-17 04:06:10.489139','2026-12-31','2026-11-17 04:16:10.489139','2027-01-02','TV-F5D3EA74',1700.00,1,2),(2,1,'EXPIRADA',NULL,'2026-11-17 04:06:10.682877','2026-12-31','2026-11-16 22:05:10.000000','2027-01-02','TV-7DAED996',1700.00,1,2);
/*!40000 ALTER TABLE `reservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `correo` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_registro` datetime(6) NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` enum('ADMIN','CLIENTE') COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UKcdmw5hxlfj78uf4997i3qyyw5` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin@gmail.com','2026-11-17 04:01:37.193874','Administrador TwoVago','admin123','ADMIN','2281234567'),(2,'cliente1@gmail.com','2026-11-17 04:06:10.100783','Usuario E3 Actualizado','cliente123','CLIENTE','2287654321'),(3,'cliente2@gmail.com','2026-11-17 04:06:10.381685','Segundo usuario E3','cliente123','CLIENTE','2281234568');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'twovago'
--

--
-- Dumping routines for database 'twovago'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-17 17:06:04
