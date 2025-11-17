-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-11-2025 a las 16:09:25
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cinemacentro_g13`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compradores`
--

CREATE TABLE `compradores` (
  `idComprador` int(11) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fechaNac` date DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `medioPago` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compradores`
--

INSERT INTO `compradores` (`idComprador`, `dni`, `nombre`, `fechaNac`, `password`, `medioPago`) VALUES
(1, '40011222', 'Agustin Nicolas PEREZ FIGUEROA', '1990-05-12', '4f3d9ac8b12f77d94e38bb0fc52f1cf0a879d134ef5a7ce58f4a2a933cdccab1', 'Tarjeta'),
(2, '38999111', 'Mauricio Barca', '1988-09-23', '9b7c1a55cc24e91ab48e0ef8f5fbd7a390c6e9220cc1d2f8af33cc67b9e00172', 'Efectivo'),
(3, '40222333', 'Cesar Tomas Echenique', '1995-02-10', 'e1c54f8ad90b4a4c31f86c3ee6a39015afc2d88d953a8ef4bfc41eb542d7d890', 'Tarjeta'),
(4, '35555444', 'Joaquín Ezequiel Rodríguez', '1987-11-02', '7dc244a9bb1882e9e4b1c2d9703f28181f9cb4fd49a30833d4b724a19fcd1197', 'MercadoPago'),
(5, '38550276', 'Manuel Alexis Zuñiga Goddard', '2007-11-08', '22dc98234ffb7a66144a6ce5bce1d0d78e58d8896df4cb33ea0f6ce5df3caa0c', 'Efectivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_lugares`
--

CREATE TABLE `detalle_lugares` (
  `idDetalleLugar` int(11) NOT NULL,
  `idDetalle` int(11) NOT NULL,
  `idLugar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_lugares`
--

INSERT INTO `detalle_lugares` (`idDetalleLugar`, `idDetalle`, `idLugar`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 3, 5),
(5, 4, 7),
(6, 6, 11),
(7, 6, 12),
(8, 6, 13),
(9, 6, 14),
(10, 6, 15),
(11, 6, 16),
(12, 6, 17),
(13, 6, 18),
(14, 6, 19),
(15, 6, 20),
(16, 6, 21),
(17, 6, 22),
(18, 6, 23),
(19, 6, 24),
(20, 6, 25),
(21, 6, 26),
(22, 6, 27),
(23, 6, 28),
(24, 6, 29),
(25, 6, 30),
(26, 6, 31),
(27, 6, 32),
(28, 6, 33),
(29, 6, 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_tickets`
--

CREATE TABLE `detalle_tickets` (
  `idDetalle` int(11) NOT NULL,
  `idTicket` int(11) NOT NULL,
  `idProyeccion` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_tickets`
--

INSERT INTO `detalle_tickets` (`idDetalle`, `idTicket`, `idProyeccion`, `cantidad`, `subtotal`) VALUES
(1, 1, 1, 2, 5000.00),
(2, 2, 2, 1, 3000.00),
(3, 3, 3, 1, 2800.00),
(4, 4, 4, 2, 4000.00),
(5, 5, 5, 1, 2000.00),
(6, 6, 6, 24, 84000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `idLugar` int(11) NOT NULL,
  `fila` char(1) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `ocupado` tinyint(1) DEFAULT NULL,
  `idProyeccion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`idLugar`, `fila`, `numero`, `ocupado`, `idProyeccion`) VALUES
(1, 'A', 1, 1, 1),
(2, 'A', 2, 1, 1),
(3, 'B', 5, 0, 2),
(4, 'B', 6, 0, 2),
(5, 'C', 3, 1, 3),
(6, 'C', 4, 0, 3),
(7, 'D', 1, 0, 4),
(8, 'D', 2, 1, 4),
(9, 'E', 8, 0, 5),
(10, 'E', 9, 1, 5),
(11, 'D', 6, 1, 6),
(12, 'D', 5, 1, 6),
(13, 'D', 4, 1, 6),
(14, 'D', 3, 1, 6),
(15, 'D', 2, 1, 6),
(16, 'D', 1, 1, 6),
(17, 'C', 1, 1, 6),
(18, 'C', 2, 1, 6),
(19, 'C', 3, 1, 6),
(20, 'C', 4, 1, 6),
(21, 'C', 5, 1, 6),
(22, 'C', 6, 1, 6),
(23, 'B', 6, 1, 6),
(24, 'B', 5, 1, 6),
(25, 'B', 4, 1, 6),
(26, 'B', 3, 1, 6),
(27, 'B', 2, 1, 6),
(28, 'B', 1, 1, 6),
(29, 'A', 1, 1, 6),
(30, 'A', 2, 1, 6),
(31, 'A', 3, 1, 6),
(32, 'A', 4, 1, 6),
(33, 'A', 5, 1, 6),
(34, 'A', 6, 1, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `idPelicula` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `director` varchar(100) DEFAULT NULL,
  `actores` varchar(255) DEFAULT NULL,
  `origen` varchar(50) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `estreno` date DEFAULT NULL,
  `enCartelera` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`idPelicula`, `titulo`, `director`, `actores`, `origen`, `genero`, `estreno`, `enCartelera`) VALUES
(1, 'Inception', 'Christopher Nolan', 'Leonardo DiCaprio', 'USA', 'Ciencia Ficción', '2010-07-16', 1),
(2, 'Avatar 2', 'James Cameron', 'Sam Worthington', 'USA', 'Acción', '2022-12-16', 1),
(3, 'Interstellar', 'Christopher Nolan', 'Matthew McConaughey', 'USA', 'Ciencia Ficción', '2014-11-07', 1),
(4, 'Titanic', 'James Cameron', 'Kate Winslet', 'USA', 'Romance', '1997-12-19', 1),
(5, 'The Batman 2', 'Matt Reeves', 'Robert Pattinson', 'USA', 'Acción', '2022-03-04', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyeccion`
--

CREATE TABLE `proyeccion` (
  `idProyeccion` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idioma` varchar(30) DEFAULT NULL,
  `es3D` tinyint(1) DEFAULT NULL,
  `subtitulada` tinyint(1) DEFAULT NULL,
  `horaInicio` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyeccion`
--

INSERT INTO `proyeccion` (`idProyeccion`, `idPelicula`, `idSala`, `idioma`, `es3D`, `subtitulada`, `horaInicio`, `horaFin`, `fecha`, `precio`) VALUES
(1, 1, 1, 'Español', 1, 1, '18:00:00', '20:30:00', '2025-11-20', 2500.00),
(2, 2, 1, 'Español', 1, 0, '21:00:00', '23:30:00', '2025-11-20', 3000.00),
(3, 3, 2, 'Español', 0, 1, '19:00:00', '22:00:00', '2025-11-21', 2800.00),
(4, 4, 3, 'Inglés', 0, 1, '17:00:00', '20:00:00', '2025-11-21', 2000.00),
(5, 5, 2, 'Español', 0, 0, '22:30:00', '01:00:00', '2025-11-21', 2700.00),
(6, 4, 4, 'Español', 0, 1, '11:54:07', '14:54:00', '2025-11-28', 3500.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE `salas` (
  `idSala` int(11) NOT NULL,
  `nroSala` int(11) NOT NULL,
  `apta3D` tinyint(1) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`idSala`, `nroSala`, `apta3D`, `capacidad`, `estado`) VALUES
(1, 1, 1, 24, 1),
(2, 2, 0, 24, 1),
(3, 3, 1, 24, 1),
(4, 4, 1, 24, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `idTicket` int(11) NOT NULL,
  `codigoTicket` varchar(16) DEFAULT NULL,
  `fechaCompra` date DEFAULT NULL,
  `fechaFuncion` date DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `idComprador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`idTicket`, `codigoTicket`, `fechaCompra`, `fechaFuncion`, `monto`, `idComprador`) VALUES
(1, '6B2A2A0DACA8A6A0', '2025-11-17', '2025-11-20', 5000.00, 1),
(2, '9F0454B67C115078', '2025-11-17', '2025-11-20', 3000.00, 2),
(3, 'D99729EB665CA868', '2025-11-18', '2025-11-21', 2800.00, 3),
(4, '62E6D3F98B9B5170', '2025-11-18', '2025-11-21', 5400.00, 4),
(5, '61BCA6A186F29B48', '2025-11-19', '2025-11-21', 2000.00, 1),
(6, 'BFFAC5BEFFEB17B8', '2025-11-17', '2025-11-28', 84000.00, 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compradores`
--
ALTER TABLE `compradores`
  ADD PRIMARY KEY (`idComprador`);

--
-- Indices de la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  ADD PRIMARY KEY (`idDetalleLugar`),
  ADD KEY `idDetalle` (`idDetalle`),
  ADD KEY `idLugar` (`idLugar`);

--
-- Indices de la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `idTicket` (`idTicket`),
  ADD KEY `idProyeccion` (`idProyeccion`);

--
-- Indices de la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD PRIMARY KEY (`idLugar`),
  ADD KEY `idProyeccion` (`idProyeccion`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`idPelicula`);

--
-- Indices de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD PRIMARY KEY (`idProyeccion`),
  ADD KEY `idPelicula` (`idPelicula`),
  ADD KEY `idSala` (`idSala`);

--
-- Indices de la tabla `salas`
--
ALTER TABLE `salas`
  ADD PRIMARY KEY (`idSala`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`idTicket`),
  ADD KEY `idComprador` (`idComprador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compradores`
--
ALTER TABLE `compradores`
  MODIFY `idComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  MODIFY `idDetalleLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `idLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  MODIFY `idProyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `salas`
--
ALTER TABLE `salas`
  MODIFY `idSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `idTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  ADD CONSTRAINT `detalle_lugares_ibfk_1` FOREIGN KEY (`idDetalle`) REFERENCES `detalle_tickets` (`idDetalle`),
  ADD CONSTRAINT `detalle_lugares_ibfk_2` FOREIGN KEY (`idLugar`) REFERENCES `lugares` (`idLugar`);

--
-- Filtros para la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  ADD CONSTRAINT `detalle_tickets_ibfk_1` FOREIGN KEY (`idTicket`) REFERENCES `tickets` (`idTicket`),
  ADD CONSTRAINT `detalle_tickets_ibfk_2` FOREIGN KEY (`idProyeccion`) REFERENCES `proyeccion` (`idProyeccion`);

--
-- Filtros para la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD CONSTRAINT `lugares_ibfk_1` FOREIGN KEY (`idProyeccion`) REFERENCES `proyeccion` (`idProyeccion`);

--
-- Filtros para la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD CONSTRAINT `proyeccion_ibfk_1` FOREIGN KEY (`idPelicula`) REFERENCES `peliculas` (`idPelicula`),
  ADD CONSTRAINT `proyeccion_ibfk_2` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`idComprador`) REFERENCES `compradores` (`idComprador`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
