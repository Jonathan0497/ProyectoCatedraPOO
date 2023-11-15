-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 09-11-2023 a las 14:01:33
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `primecinema`
--

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `AgregarAsientos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarAsientos` (IN `sala_id` INT)   BEGIN
    DECLARE fila CHAR(1);
    DECLARE columna INT;
    DECLARE contador INT DEFAULT 0;
    DECLARE asiento_nombre VARCHAR(5);

    WHILE contador < 40 DO
        SET fila = CHAR(65 + FLOOR(contador / 5)); -- 65 es el código ASCII para 'A'
        SET columna = contador % 5 + 1;
        SET asiento_nombre = CONCAT(fila, '-', columna);

        INSERT INTO asientos (numero_asiento, id_salas)
        VALUES (asiento_nombre, sala_id);
        
        SET contador = contador + 1;
    END WHILE;
END$$

DROP PROCEDURE IF EXISTS `AgregarAsientosUltimaSala`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarAsientosUltimaSala` ()   BEGIN
    DECLARE last_sala_id INT;
    
    -- Obtener el último id_salas
    SELECT MAX(id_salas) INTO last_sala_id FROM salas;
    
    -- Llamar al procedimiento almacenado AgregarAsientos
    CALL AgregarAsientos(last_sala_id);
END$$

DROP PROCEDURE IF EXISTS `EliminarAsientos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarAsientos` (IN `sala_id` INT)   BEGIN
    DELETE FROM asientos WHERE id_salas = sala_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asientos`
--

DROP TABLE IF EXISTS `asientos`;
CREATE TABLE IF NOT EXISTS `asientos` (
  `id_asiento` int NOT NULL AUTO_INCREMENT,
  `numero_asiento` varchar(25) NOT NULL,
  `id_salas` int NOT NULL,
  PRIMARY KEY (`id_asiento`),
  KEY `id_salas` (`id_salas`)
) ;

--
-- Volcado de datos para la tabla `asientos`
--

INSERT INTO `asientos` (`id_asiento`, `numero_asiento`, `id_salas`) VALUES
(2, 'A-1', 1),
(3, 'A-2', 1),
(4, 'A-3', 1),
(5, 'A-4', 1),
(6, 'A-5', 1),
(7, 'B-1', 1),
(8, 'B-2', 1),
(9, 'B-3', 1),
(10, 'B-4', 1),
(11, 'B-5', 1),
(12, 'C-1', 1),
(13, 'C-2', 1),
(14, 'C-3', 1),
(15, 'C-4', 1),
(16, 'C-5', 1),
(17, 'D-1', 1),
(18, 'D-2', 1),
(19, 'D-3', 1),
(20, 'D-4', 1),
(21, 'D-5', 1),
(22, 'E-1', 1),
(23, 'E-2', 1),
(24, 'E-3', 1),
(25, 'E-4', 1),
(26, 'E-5', 1),
(27, 'F-1', 1),
(28, 'F-2', 1),
(29, 'F-3', 1),
(30, 'F-4', 1),
(31, 'F-5', 1),
(32, 'G-1', 1),
(33, 'G-2', 1),
(34, 'G-3', 1),
(35, 'G-4', 1),
(36, 'G-5', 1),
(37, 'H-1', 1),
(38, 'H-2', 1),
(39, 'H-3', 1),
(40, 'H-4', 1),
(41, 'H-5', 1),
(42, 'A-1', 2),
(43, 'A-2', 2),
(44, 'A-3', 2),
(45, 'A-4', 2),
(46, 'A-5', 2),
(47, 'B-1', 2),
(48, 'B-2', 2),
(49, 'B-3', 2),
(50, 'B-4', 2),
(51, 'B-5', 2),
(52, 'C-1', 2),
(53, 'C-2', 2),
(54, 'C-3', 2),
(55, 'C-4', 2),
(56, 'C-5', 2),
(57, 'D-1', 2),
(58, 'D-2', 2),
(59, 'D-3', 2),
(60, 'D-4', 2),
(61, 'D-5', 2),
(62, 'E-1', 2),
(63, 'E-2', 2),
(64, 'E-3', 2),
(65, 'E-4', 2),
(66, 'E-5', 2),
(67, 'F-1', 2),
(68, 'F-2', 2),
(69, 'F-3', 2),
(70, 'F-4', 2),
(71, 'F-5', 2),
(72, 'G-1', 2),
(73, 'G-2', 2),
(74, 'G-3', 2),
(75, 'G-4', 2),
(76, 'G-5', 2),
(77, 'H-1', 2),
(78, 'H-2', 2),
(79, 'H-3', 2),
(80, 'H-4', 2),
(81, 'H-5', 2),
(122, 'A-1', 4),
(123, 'A-2', 4),
(124, 'A-3', 4),
(125, 'A-4', 4),
(126, 'A-5', 4),
(127, 'B-1', 4),
(128, 'B-2', 4),
(129, 'B-3', 4),
(130, 'B-4', 4),
(131, 'B-5', 4),
(132, 'C-1', 4),
(133, 'C-2', 4),
(134, 'C-3', 4),
(135, 'C-4', 4),
(136, 'C-5', 4),
(137, 'D-1', 4),
(138, 'D-2', 4),
(139, 'D-3', 4),
(140, 'D-4', 4),
(141, 'D-5', 4),
(142, 'E-1', 4),
(143, 'E-2', 4),
(144, 'E-3', 4),
(145, 'E-4', 4),
(146, 'E-5', 4),
(147, 'F-1', 4),
(148, 'F-2', 4),
(149, 'F-3', 4),
(150, 'F-4', 4),
(151, 'F-5', 4),
(152, 'G-1', 4),
(153, 'G-2', 4),
(154, 'G-3', 4),
(155, 'G-4', 4),
(156, 'G-5', 4),
(157, 'H-1', 4),
(158, 'H-2', 4),
(159, 'H-3', 4),
(160, 'H-4', 4),
(161, 'H-5', 4),
(202, 'A-1', 5),
(203, 'A-2', 5),
(204, 'A-3', 5),
(205, 'A-4', 5),
(206, 'A-5', 5),
(207, 'B-1', 5),
(208, 'B-2', 5),
(209, 'B-3', 5),
(210, 'B-4', 5),
(211, 'B-5', 5),
(212, 'C-1', 5),
(213, 'C-2', 5),
(214, 'C-3', 5),
(215, 'C-4', 5),
(216, 'C-5', 5),
(217, 'D-1', 5),
(218, 'D-2', 5),
(219, 'D-3', 5),
(220, 'D-4', 5),
(221, 'D-5', 5),
(222, 'E-1', 5),
(223, 'E-2', 5),
(224, 'E-3', 5),
(225, 'E-4', 5),
(226, 'E-5', 5),
(227, 'F-1', 5),
(228, 'F-2', 5),
(229, 'F-3', 5),
(230, 'F-4', 5),
(231, 'F-5', 5),
(232, 'G-1', 5),
(233, 'G-2', 5),
(234, 'G-3', 5),
(235, 'G-4', 5),
(236, 'G-5', 5),
(237, 'H-1', 5),
(238, 'H-2', 5),
(239, 'H-3', 5),
(240, 'H-4', 5),
(241, 'H-5', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
CREATE TABLE IF NOT EXISTS `clasificacion` (
  `id_clasificacion` int NOT NULL AUTO_INCREMENT,
  `clasificacion` varchar(25) NOT NULL,
  PRIMARY KEY (`id_clasificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `clasificacion`
--

INSERT INTO `clasificacion` (`id_clasificacion`, `clasificacion`) VALUES
(1, 'General'),
(2, 'PG'),
(3, 'PG-13'),
(4, 'R'),
(5, 'NC-17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edad_cliente`
--

DROP TABLE IF EXISTS `edad_cliente`;
CREATE TABLE IF NOT EXISTS `edad_cliente` (
  `id_edadCliente` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_edadCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `edad_cliente`
--

INSERT INTO `edad_cliente` (`id_edadCliente`, `descripcion`) VALUES
(1, 'Tercera edad'),
(2, 'Adulto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_pelicula`
--

DROP TABLE IF EXISTS `estado_pelicula`;
CREATE TABLE IF NOT EXISTS `estado_pelicula` (
  `id_estadoPelicula` int NOT NULL AUTO_INCREMENT,
  `estadoPelicula` varchar(25) NOT NULL,
  PRIMARY KEY (`id_estadoPelicula`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `estado_pelicula`
--

INSERT INTO `estado_pelicula` (`id_estadoPelicula`, `estadoPelicula`) VALUES
(1, 'En Cartelera'),
(2, 'Próximamente'),
(3, 'Fuera de Cartelera');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_usuario`
--

DROP TABLE IF EXISTS `estado_usuario`;
CREATE TABLE IF NOT EXISTS `estado_usuario` (
  `id_estadoUsuario` int NOT NULL AUTO_INCREMENT,
  `estadoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`id_estadoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `estado_usuario`
--

INSERT INTO `estado_usuario` (`id_estadoUsuario`, `estadoUsuario`) VALUES
(1, 'Activo'),
(2, 'Inactivo'),
(3, 'Suspendido'),
(4, 'Verificado'),
(5, 'Pendiente de Verificación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacion`
--

DROP TABLE IF EXISTS `facturacion`;
CREATE TABLE IF NOT EXISTS `facturacion` (
  `id_facturacion` int NOT NULL AUTO_INCREMENT,
  `fecha_emision` datetime NOT NULL,
  `id_usuario` int NOT NULL,
  `metodo_pago` varchar(50) DEFAULT NULL,
  `total` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id_facturacion`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_ticket`
--

DROP TABLE IF EXISTS `factura_ticket`;
CREATE TABLE IF NOT EXISTS `factura_ticket` (
  `id_factura_ticket` int NOT NULL AUTO_INCREMENT,
  `id_facturacion` int NOT NULL,
  `id_ticket` int NOT NULL,
  `cantidad` int NOT NULL,
  `subtotal` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id_factura_ticket`),
  KEY `id_facturacion` (`id_facturacion`),
  KEY `id_ticket` (`id_ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formato`
--

DROP TABLE IF EXISTS `formato`;
CREATE TABLE IF NOT EXISTS `formato` (
  `id_formato` int NOT NULL AUTO_INCREMENT,
  `formato` varchar(25) NOT NULL,
  PRIMARY KEY (`id_formato`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `formato`
--

INSERT INTO `formato` (`id_formato`, `formato`) VALUES
(1, 'Tradicional'),
(2, '3D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `id_genero` int NOT NULL AUTO_INCREMENT,
  `genero` varchar(25) NOT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id_genero`, `genero`) VALUES
(1, 'Acción'),
(2, 'Comedia'),
(3, 'Drama'),
(4, 'Fantasía'),
(5, 'Horror'),
(6, 'Romance'),
(7, 'Ciencia Ficción'),
(8, 'Documental'),
(9, 'Animación'),
(10, 'Aventura');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multimedia`
--

DROP TABLE IF EXISTS `multimedia`;
CREATE TABLE IF NOT EXISTS `multimedia` (
  `id_multimedia` int NOT NULL AUTO_INCREMENT,
  `Fecha_emision` date NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `id_salas` int NOT NULL,
  `id_pelicula` int NOT NULL,
  `id_formato` int NOT NULL,
  PRIMARY KEY (`id_multimedia`),
  KEY `id_salas` (`id_salas`),
  KEY `id_pelicula` (`id_pelicula`),
  KEY `id_formato` (`id_formato`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `multimedia`
--

INSERT INTO `multimedia` (`id_multimedia`, `Fecha_emision`, `horaInicio`, `horaFin`, `id_salas`, `id_pelicula`, `id_formato`) VALUES
(1, '2023-11-09', '03:42:00', '06:42:00', 2, 1, 2),
(2, '2023-11-15', '01:51:00', '04:51:00', 1, 2, 1),
(3, '2023-11-17', '03:24:00', '05:24:00', 4, 4, 1),
(4, '2023-11-15', '01:40:00', '04:12:00', 5, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE IF NOT EXISTS `peliculas` (
  `id_pelicula` int NOT NULL AUTO_INCREMENT,
  `nombre_pelicula` varchar(100) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `anio_lanzamiento` varchar(25) NOT NULL,
  `id_genero` int NOT NULL,
  `duracion` varchar(10) NOT NULL,
  `id_estadoPelicula` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_pelicula`),
  KEY `id_genero` (`id_genero`),
  KEY `id_estadoPelicula` (`id_estadoPelicula`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id_pelicula`, `nombre_pelicula`, `descripcion`, `anio_lanzamiento`, `id_genero`, `duracion`, `id_estadoPelicula`) VALUES
(1, 'Harry Potter y la Piedra Filosofal', 'un niño mago con una piedrita', '2001', 4, '152', 1),
(2, 'Harry Potter y la cámara secreta', 'Un niño con su camarita secreta', '2003', 4, '161', 1),
(4, 'Cars', 'Un carrito quiere su copita bellaca', '2006', 9, '117', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `precio`
--

DROP TABLE IF EXISTS `precio`;
CREATE TABLE IF NOT EXISTS `precio` (
  `id_precio` int NOT NULL AUTO_INCREMENT,
  `precio` decimal(6,2) NOT NULL,
  `id_formato` int NOT NULL,
  `id_edadCliente` int NOT NULL,
  PRIMARY KEY (`id_precio`),
  KEY `id_formato` (`id_formato`),
  KEY `id_edadCliente` (`id_edadCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `precio`
--

INSERT INTO `precio` (`id_precio`, `precio`, `id_formato`, `id_edadCliente`) VALUES
(1, '3.90', 1, 1),
(2, '5.00', 1, 2),
(3, '5.60', 2, 1),
(4, '6.55', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

DROP TABLE IF EXISTS `salas`;
CREATE TABLE IF NOT EXISTS `salas` (
  `id_salas` int NOT NULL AUTO_INCREMENT,
  `numero_sala` int NOT NULL,
  `id_sucursales` int NOT NULL,
  PRIMARY KEY (`id_salas`),
  KEY `id_sucursales` (`id_sucursales`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`id_salas`, `numero_sala`, `id_sucursales`) VALUES
(1, 1, 1),
(2, 2, 1),
(4, 1, 2),
(5, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

DROP TABLE IF EXISTS `sucursales`;
CREATE TABLE IF NOT EXISTS `sucursales` (
  `id_sucursales` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `id_usuario` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_sucursales`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`id_sucursales`, `nombre`, `telefono`, `direccion`, `id_usuario`) VALUES
(1, 'PrimeCinema La Gran Via', '72022904', 'la Gran Via', 2),
(2, 'PrimeCinema Las Ramblas', '72022904', 'Las Ramblas', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `id_ticket` int NOT NULL AUTO_INCREMENT,
  `fecha_emision` datetime NOT NULL,
  `id_multimedia` int NOT NULL,
  `id_asiento` int NOT NULL,
  `id_usuario` int NOT NULL,
  `id_precio` int NOT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `id_multimedia` (`id_multimedia`),
  KEY `id_asiento` (`id_asiento`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_precio` (`id_precio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `id_tipoUsuario` int NOT NULL AUTO_INCREMENT,
  `tipoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipoUsuario`, `tipoUsuario`) VALUES
(1, 'Administrador'),
(2, 'Empleado'),
(3, 'Gerente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `dui` varchar(10) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `id_estadoUsuario` int NOT NULL DEFAULT '1',
  `id_tipoUsuario` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_estadoUsuario` (`id_estadoUsuario`),
  KEY `id_tipoUsuario` (`id_tipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `clave`, `dui`, `correo`, `telefono`, `id_estadoUsuario`, `id_tipoUsuario`) VALUES
(1, 'Jonathan', 'Mendoza', 'Bebo2001', '06305938-5', 'jonathanmendoza2001.jm@gmail.com', '72022904', 1, 1),
(2, 'Jonathan Alejandro', 'Mendoza Olano', 'Bebo2001', '06305938-6', 'jm@gmail.com', '72022904', 2, 3);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asientos`
--
ALTER TABLE `asientos`
  ADD CONSTRAINT `asientos_ibfk_1` FOREIGN KEY (`id_salas`) REFERENCES `salas` (`id_salas`);

--
-- Filtros para la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD CONSTRAINT `facturacion_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `factura_ticket`
--
ALTER TABLE `factura_ticket`
  ADD CONSTRAINT `factura_ticket_ibfk_1` FOREIGN KEY (`id_facturacion`) REFERENCES `facturacion` (`id_facturacion`),
  ADD CONSTRAINT `factura_ticket_ibfk_2` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`);

--
-- Filtros para la tabla `multimedia`
--
ALTER TABLE `multimedia`
  ADD CONSTRAINT `multimedia_ibfk_1` FOREIGN KEY (`id_salas`) REFERENCES `salas` (`id_salas`),
  ADD CONSTRAINT `multimedia_ibfk_2` FOREIGN KEY (`id_pelicula`) REFERENCES `peliculas` (`id_pelicula`),
  ADD CONSTRAINT `multimedia_ibfk_3` FOREIGN KEY (`id_formato`) REFERENCES `formato` (`id_formato`);

--
-- Filtros para la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD CONSTRAINT `peliculas_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id_genero`),
  ADD CONSTRAINT `peliculas_ibfk_2` FOREIGN KEY (`id_estadoPelicula`) REFERENCES `estado_pelicula` (`id_estadoPelicula`);

--
-- Filtros para la tabla `precio`
--
ALTER TABLE `precio`
  ADD CONSTRAINT `precio_ibfk_1` FOREIGN KEY (`id_formato`) REFERENCES `formato` (`id_formato`),
  ADD CONSTRAINT `precio_ibfk_2` FOREIGN KEY (`id_edadCliente`) REFERENCES `edad_cliente` (`id_edadCliente`);

--
-- Filtros para la tabla `salas`
--
ALTER TABLE `salas`
  ADD CONSTRAINT `salas_ibfk_1` FOREIGN KEY (`id_sucursales`) REFERENCES `sucursales` (`id_sucursales`);

--
-- Filtros para la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD CONSTRAINT `sucursales_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`id_multimedia`) REFERENCES `multimedia` (`id_multimedia`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`id_asiento`) REFERENCES `asientos` (`id_asiento`),
  ADD CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `ticket_ibfk_4` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id_precio`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_estadoUsuario`) REFERENCES `estado_usuario` (`id_estadoUsuario`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_tipoUsuario`) REFERENCES `tipo_usuario` (`id_tipoUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
