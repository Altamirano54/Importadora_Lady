-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-07-2025 a las 06:46:17
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `importadora_lady`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`id`, `nombre`, `fecha_creacion`, `estado`) VALUES
(1, 'Administrador', '2025-06-10 20:51:00', 1),
(2, 'Vendedor', '2025-06-10 20:51:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `id_tipoDocumento` int(11) NOT NULL,
  `nro_documento` varchar(16) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_modificacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `id_tipoDocumento`, `nro_documento`, `nombre`, `telefono`, `direccion`, `fecha_creacion`, `fecha_modificacion`, `estado`) VALUES
(1, 2, '87654321', 'Campos', '987654321', 'Bagua', '2025-07-02 13:20:52', '2025-07-02 13:20:52', 1),
(2, 2, '45678912', 'María Gonzales Rojas', '987654321', 'Jr. Amazonas 345, Bagua', '2025-07-16 12:45:47', '2025-07-16 12:45:47', 1),
(3, 2, '78912345', 'Juan Perez Quispe', '912345678', 'Calle Grau 123, Bagua', '2025-07-16 12:47:25', '2025-07-16 12:47:25', 1),
(4, 3, '012345678', 'Ana Flores Mendoza', '999888777', 'Av. La Peruanidad 567, Bagua Grande', '2025-07-16 12:48:10', '2025-07-16 12:48:10', 1),
(5, 2, ' 12345678', 'Carlos Ruiz Soto', '965432109', 'Psje. Los Libertadores 89, Bagua', '2025-07-16 12:48:45', '2025-07-16 12:48:45', 1),
(6, 5, 'PA9876543', 'Laura Castro Ramos', '932109876', 'Jr. Bolivar 210, Bagua Grande', '2025-07-16 12:49:10', '2025-07-16 12:49:10', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `id_estadoSolicitud` int(11) NOT NULL,
  `Total` float NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_Actualizacion` timestamp NULL DEFAULT NULL,
  `fecha_estadoEnProseso` timestamp NULL DEFAULT NULL,
  `fecha_estadoCompletado` timestamp NULL DEFAULT NULL,
  `fecha_cancelacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id`, `id_empleado`, `id_proveedor`, `id_estadoSolicitud`, `Total`, `fecha`, `fecha_Actualizacion`, `fecha_estadoEnProseso`, `fecha_estadoCompletado`, `fecha_cancelacion`) VALUES
(1, 1, 1, 4, 7, '2025-07-10 04:51:20', NULL, '2025-07-17 02:41:18', NULL, NULL),
(2, 1, 2, 4, 15, '2025-07-10 22:43:18', NULL, NULL, NULL, NULL),
(3, 1, 4, 4, 24, '2025-07-16 19:53:54', NULL, NULL, NULL, NULL),
(4, 1, 4, 4, 24, '2025-07-16 20:53:02', '2025-07-16 20:53:43', '2025-07-16 20:53:43', NULL, NULL),
(5, 1, 2, 4, 0, '2025-07-17 02:41:18', '2025-07-17 04:29:53', NULL, '2025-07-17 04:29:53', NULL),
(6, 1, 1, 2, 0, '2025-07-17 02:49:25', NULL, NULL, NULL, NULL),
(7, 1, 2, 2, 0, '2025-07-17 02:54:29', NULL, '2025-07-17 02:54:29', NULL, NULL),
(8, 1, 3, 2, 0, '2025-07-17 02:56:29', NULL, '2025-07-17 02:56:29', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compradetalles`
--

CREATE TABLE `compradetalles` (
  `id` int(11) NOT NULL,
  `id_compra` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `precioTotal` float NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `compradetalles`
--

INSERT INTO `compradetalles` (`id`, `id_compra`, `id_producto`, `Cantidad`, `precioTotal`, `fecha`) VALUES
(1, 1, 1, 2, 7, '2025-07-10 04:04:41'),
(2, 1, 4, 1, 15, '2025-07-10 22:39:38'),
(3, 1, 6, 3, 24, '2025-07-16 19:53:32'),
(4, 1, 20, 1, 60, '2025-07-16 19:53:32'),
(5, 1, 6, 3, 24, '2025-07-16 20:53:02'),
(6, 8, 5, 1, 50, '2025-07-17 02:56:29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `id_tipo_document` int(11) DEFAULT NULL,
  `nro_documento` varchar(16) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `genero` char(1) DEFAULT NULL,
  `contraseña` varchar(100) NOT NULL,
  `id_cargo` int(11) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `fecha_contratacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_ultima_actualizacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` tinyint(4) NOT NULL DEFAULT 1,
  `fecha_terminacion` timestamp NULL DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `nombre`, `usuario`, `id_tipo_document`, `nro_documento`, `fecha_nacimiento`, `genero`, `contraseña`, `id_cargo`, `direccion`, `telefono`, `correo`, `fecha_contratacion`, `fecha_ultima_actualizacion`, `estado`, `fecha_terminacion`, `url`) VALUES
(1, 'Amir', 'Amir', 2, '60730269', '2006-07-11', 'M', '123456', 1, 'bagua', '980679884', 'amiraltamirano54@gmail.com', '2025-06-18 15:42:32', '2025-07-16 20:23:29', 1, '2006-07-11 05:00:00', NULL),
(2, 'Leon', 'Leon', 2, '87654321', '2004-08-01', 'M', '123456', 1, 'bagua', '987654321', 'jhefferson@gmail.com', '2025-07-15 19:56:50', '2025-07-15 19:56:50', 1, NULL, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_Empleados\\img_20250715_145650.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosolicitud`
--

CREATE TABLE `estadosolicitud` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `estadosolicitud`
--

INSERT INTO `estadosolicitud` (`id`, `nombre`, `fecha_creacion`) VALUES
(1, 'Cancelado', '2025-07-04 20:25:56'),
(2, 'En proceso', '2025-07-04 20:25:56'),
(3, 'En espera', '2025-07-04 20:25:56'),
(4, 'Completado', '2025-07-04 20:25:56'),
(5, 'Recibido', '2025-07-04 20:25:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio_venta` float NOT NULL,
  `precio_compra` float NOT NULL,
  `stock` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `descripcion` varchar(260) DEFAULT NULL,
  `fecha_modificacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio_venta`, `precio_compra`, `stock`, `id_proveedor`, `descripcion`, `fecha_modificacion`, `fecha_creacion`, `estado`, `url`) VALUES
(1, 'Producto 1', 5, 3.5, 6, 1, NULL, '2025-07-16 02:26:25', '2025-07-02 05:35:38', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250702_003538.png'),
(2, 'cama', 6, 4, 1, 1, NULL, '2025-07-10 00:51:10', '2025-07-10 00:51:10', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250709_195110.png'),
(3, 'colchon', 400, 300, 1, 2, NULL, '2025-07-10 00:51:44', '2025-07-10 00:51:44', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250709_195144.png'),
(4, 'Peluche y caja de regalo', 20, 15, 4, 2, NULL, '2025-07-16 19:52:11', '2025-07-10 20:41:53', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250710_154153.png'),
(5, 'Carteras', 80, 50, 5, 3, NULL, '2025-07-10 20:45:04', '2025-07-10 20:45:04', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250710_154504.png'),
(6, 'Cartuchera de carro', 10, 8, 10, 4, NULL, '2025-07-17 04:30:24', '2025-07-10 20:46:29', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250710_154629.png'),
(7, 'Lipo Crem', 35, 20, 1, 2, NULL, '2025-07-10 20:47:42', '2025-07-10 20:47:42', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250710_154742.png'),
(8, 'abc', 3, 2.5, 1, 3, NULL, '2025-07-10 22:25:39', '2025-07-10 22:25:39', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250710_172539.png'),
(9, 'Laptop Gamer ASUS ROG Strix', 4200, 3500, 13, 2, NULL, '2025-07-16 21:04:51', '2025-07-16 12:52:09', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075209.png'),
(10, 'Smartphone Samsung Galaxy A55', 1500, 1200, 50, 3, NULL, '2025-07-16 12:53:38', '2025-07-16 12:53:38', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075338.png'),
(11, 'Audífonos Bluetooth Sony WH-1000XM5', 1050, 800, 24, 1, NULL, '2025-07-16 20:58:18', '2025-07-16 12:54:34', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075434.png'),
(12, 'Cuaderno Espiral A4 Rayado 100 Hojas Standford', 6.9, 4.5, 200, 1, NULL, '2025-07-16 12:57:32', '2025-07-16 12:57:32', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075732.png'),
(13, 'Set de Lapiceros Faber-Castell Colores Surtidos x12', 22.5, 15, 78, 1, NULL, '2025-07-16 20:58:18', '2025-07-16 12:58:29', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075828.png'),
(14, 'Mochila Escolar Totto Renglones', 110, 75, 30, 1, NULL, '2025-07-16 12:59:38', '2025-07-16 12:59:38', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_075938.png'),
(15, 'Jarrón Cerámico Decorativo Diseño Abstracto 25cm', 55, 35, 15, 3, NULL, '2025-07-16 13:02:02', '2025-07-16 13:02:02', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080202.png'),
(16, 'Cojín Decorativo con Borlas 45x45cm Beige', 39.9, 25, 40, 4, NULL, '2025-07-16 13:03:35', '2025-07-16 13:03:35', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080335.png'),
(17, 'Marco de Fotos Triple Estilo Rústico 10x15cm', 29.9, 18, 25, 4, NULL, '2025-07-16 13:04:52', '2025-07-16 13:04:52', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080452.png'),
(18, 'Colcha Frazada Sherpa King Size Gris', 135, 90, 20, 5, NULL, '2025-07-16 13:06:17', '2025-07-16 13:06:17', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080617.png'),
(19, 'Edredón Plumón Twin Size Blanco', 180, 120, 10, 4, NULL, '2025-07-16 13:08:03', '2025-07-16 13:08:03', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080803.png'),
(20, 'Cartera de Hombro Mujer Cuero Sintético Negra', 95, 60, 30, 4, NULL, '2025-07-16 20:54:13', '2025-07-16 13:09:50', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_080950.png'),
(21, 'Billetera de Dama Compacta con Cierre y Compartimentos', 45, 25, 50, 4, NULL, '2025-07-16 13:10:56', '2025-07-16 13:10:56', 1, 'C:\\Users\\Amir Altamirano\\Documents\\software\\importadora_lady\\Importadora_Lady\\imagenes_guardadas\\img_20250716_081056.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `ruc` varchar(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono_contacto` varchar(100) DEFAULT NULL,
  `fecha_modificacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `ruc`, `nombre`, `direccion`, `correo`, `telefono_contacto`, `fecha_modificacion`, `fecha_creacion`, `estado`) VALUES
(1, '', 'proveedor general', 'Lima', 'xxx@gmail.com', '900000000', '2025-06-10 20:56:57', '2025-06-10 20:56:57', 1),
(2, '98765432112', 'Leon', 'bagua', 'Leon@gmail.com', 'Leon@gmail.com', '2025-07-02 13:28:05', '2025-07-02 13:28:05', 1),
(3, '12345678921', 'Temu', 'Lima', 'temu@gmail.com', '987654321', '2025-07-16 02:24:55', '2025-07-10 20:42:38', 1),
(4, '98765432121', 'importadora Lima', 'Lima', 'asd@gmail.com', '912345678', '2025-07-10 20:43:49', '2025-07-10 20:43:49', 1),
(5, '12345678912', 'jenny', 'bagu', 'abc@gmail.com', '987654321', '2025-07-10 22:24:48', '2025-07-10 22:24:48', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipo_documento`
--

INSERT INTO `tipo_documento` (`id`, `nombre`, `estado`) VALUES
(1, 'OTROS TIPOS DE DOCUMENTOS', 1),
(2, 'DNI', 1),
(3, 'CARNET DE EXTRANJERIA', 1),
(4, 'REGISTRO ÚNICO DE CONTRIBUYENTES', 1),
(5, 'PASAPORTE', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_estadosolicitud` int(11) NOT NULL,
  `Total` float NOT NULL,
  `fech` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_Actualizacion` timestamp NULL DEFAULT NULL,
  `fecha_estadoEnProseso` timestamp NULL DEFAULT NULL,
  `fecha_estadoCompletado` timestamp NULL DEFAULT NULL,
  `fecha_cancelacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `id_empleado`, `id_cliente`, `id_estadosolicitud`, `Total`, `fech`, `fecha_Actualizacion`, `fecha_estadoEnProseso`, `fecha_estadoCompletado`, `fecha_cancelacion`) VALUES
(1, 1, 1, 4, 30, '2025-07-10 01:44:16', NULL, NULL, NULL, NULL),
(2, 1, 1, 4, 10, '2025-07-16 02:26:28', NULL, NULL, NULL, NULL),
(3, 1, 1, 4, 20, '2025-07-10 22:44:17', NULL, NULL, NULL, NULL),
(7, 1, 2, 4, 95, '2025-07-16 20:07:27', '2025-07-16 20:49:40', NULL, NULL, NULL),
(8, 1, 5, 4, 1125, '2025-07-16 13:31:39', '2025-07-16 20:58:19', NULL, NULL, NULL),
(9, 1, 5, 4, 125, '2025-07-16 19:52:22', NULL, NULL, NULL, NULL),
(10, 1, 6, 1, 4220, '2025-07-16 21:00:18', NULL, NULL, NULL, '2025-07-17 03:11:45'),
(11, 1, 6, 1, 4220, '2025-07-16 21:00:21', NULL, NULL, NULL, '2025-07-17 04:01:37'),
(12, 1, 6, 1, 4220, '2025-07-16 21:00:23', NULL, NULL, NULL, '2025-07-17 04:01:34'),
(13, 1, 6, 1, 4220, '2025-07-16 21:00:28', NULL, NULL, NULL, '2025-07-17 04:01:40'),
(14, 1, 6, 1, 4220, '2025-07-16 21:00:29', NULL, NULL, NULL, '2025-07-17 04:01:45'),
(15, 1, 6, 1, 4220, '2025-07-16 21:00:29', NULL, NULL, NULL, '2025-07-17 04:01:29'),
(16, 1, 1, 1, 16800, '2025-07-16 21:02:35', NULL, NULL, NULL, '2025-07-17 04:01:21'),
(17, 1, 5, 4, 8400, '2025-07-16 21:04:07', '2025-07-16 21:04:53', '2025-07-16 21:04:40', '2025-07-16 21:04:53', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventadetalles`
--

CREATE TABLE `ventadetalles` (
  `id` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `precioTotal` float NOT NULL,
  `fecha_modificacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ventadetalles`
--

INSERT INTO `ventadetalles` (`id`, `id_venta`, `id_producto`, `Cantidad`, `precioTotal`, `fecha_modificacion`) VALUES
(1, 1, 1, 6, 30, '2025-07-05 00:42:39'),
(2, 2, 1, 2, 10, '2025-07-10 01:51:38'),
(3, 3, 4, 1, 20, '2025-07-10 22:38:29'),
(4, 7, 20, 1, 95, '2025-07-16 13:30:30'),
(5, 8, 13, 2, 45, '2025-07-16 13:31:39'),
(6, 8, 6, 3, 30, '2025-07-16 13:31:39'),
(7, 8, 11, 1, 1050, '2025-07-16 13:31:39'),
(8, 9, 20, 1, 95, '2025-07-16 19:51:15'),
(9, 9, 4, 1, 20, '2025-07-16 19:51:15'),
(10, 9, 6, 1, 10, '2025-07-16 19:51:15'),
(11, 17, 9, 2, 8400, '2025-07-16 21:04:07');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tipoDocumento` (`id_tipoDocumento`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`),
  ADD KEY `id_proveedor` (`id_proveedor`),
  ADD KEY `id_estadoSolicitud` (`id_estadoSolicitud`);

--
-- Indices de la tabla `compradetalles`
--
ALTER TABLE `compradetalles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_compra` (`id_compra`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cargo` (`id_cargo`),
  ADD KEY `id_tipo_document` (`id_tipo_document`);

--
-- Indices de la tabla `estadosolicitud`
--
ALTER TABLE `estadosolicitud`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_estadosolicitud` (`id_estadosolicitud`);

--
-- Indices de la tabla `ventadetalles`
--
ALTER TABLE `ventadetalles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `compradetalles`
--
ALTER TABLE `compradetalles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estadosolicitud`
--
ALTER TABLE `estadosolicitud`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `ventadetalles`
--
ALTER TABLE `ventadetalles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_tipoDocumento_cliente` FOREIGN KEY (`id_tipoDocumento`) REFERENCES `tipo_documento` (`id`);

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_Proveedor_Compra` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `Fk_Empleado_Compra` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`),
  ADD CONSTRAINT `Fk_EstadoSolicitud_Compra` FOREIGN KEY (`id_estadoSolicitud`) REFERENCES `estadosolicitud` (`id`);

--
-- Filtros para la tabla `compradetalles`
--
ALTER TABLE `compradetalles`
  ADD CONSTRAINT `FK_Compra_CompraDetalles` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id`),
  ADD CONSTRAINT `Fk_Producto_CompraDetalles` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK_Cargo_Empleado` FOREIGN KEY (`id_cargo`) REFERENCES `cargo` (`id`),
  ADD CONSTRAINT `FK_tipo_documento_empleado` FOREIGN KEY (`id_tipo_document`) REFERENCES `tipo_documento` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_Proveedor_Producto` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_Cliente_Venta` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_EstadoSolicitus_venta` FOREIGN KEY (`id_estadosolicitud`) REFERENCES `estadosolicitud` (`id`),
  ADD CONSTRAINT `Fk_Empleado_venta` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`);

--
-- Filtros para la tabla `ventadetalles`
--
ALTER TABLE `ventadetalles`
  ADD CONSTRAINT `FK_Venta_VentaDellates` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`),
  ADD CONSTRAINT `Fk_Producto_VentaDetalles` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
