-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-11-2025 a las 14:26:53
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
CREATE DATABASE IF NOT EXISTS `cinemacentro_g13` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cinemacentro_g13`;

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
(1, '40123456', 'Manuel Zuñiga', '1995-05-12', '1234', 'Débito'),
(2, '38999999', 'Lucía Pérez', '1997-09-20', 'abcd', 'Mercado Pago'),
(3, '42111111', 'Carlos Rojas', '1993-03-15', 'pass', 'Crédito'),
(4, '43123456', 'María López', '1998-11-02', 'maria123', 'Efectivo'),
(5, '34182684', 'Mauricio Barca', '1989-04-09', '123', 'Efectivo'),
(6, '32233444', 'Ana Ortega', '1990-02-10', 'aaa', 'Débito'),
(7, '39555111', 'Javier Torres', '1998-01-22', 'jt22', 'Mercado Pago'),
(8, '40222888', 'Sofía Ramírez', '2000-10-07', 'sofi07', 'Crédito'),
(9, '35566777', 'Tomás Medina', '1995-03-30', 'tomas95', 'Débito'),
(10, '36544321', 'Paula Gómez', '1996-12-01', 'pau96', 'Efectivo');

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
(4, 2, 4),
(5, 2, 5),
(6, 3, 7),
(7, 4, 12),
(8, 4, 13),
(9, 5, 16),
(10, 6, 19),
(11, 6, 20),
(12, 7, 21),
(13, 7, 22),
(14, 7, 23),
(15, 8, 24),
(16, 9, 24),
(17, 9, 25),
(18, 10, 26),
(19, 11, 6),
(20, 12, 8),
(21, 12, 9),
(22, 13, 10),
(23, 13, 11),
(24, 13, 14),
(25, 14, 10),
(26, 15, 12),
(27, 15, 15),
(28, 16, 19),
(29, 16, 20),
(30, 17, 28),
(31, 18, 29),
(32, 18, 30),
(33, 18, 31),
(34, 19, 26),
(35, 20, 12),
(36, 20, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_tickets`
--

CREATE TABLE `detalle_tickets` (
  `idDetalle` int(11) NOT NULL,
  `idTicket` int(11) NOT NULL,
  `idProyeccion` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_tickets`
--

INSERT INTO `detalle_tickets` (`idDetalle`, `idTicket`, `idProyeccion`, `cantidad`, `subtotal`) VALUES
(1, 1, 1, 2, 5600.00),
(2, 2, 1, 3, 8400.00),
(3, 3, 2, 1, 2800.00),
(4, 4, 3, 2, 5600.00),
(5, 5, 4, 1, 2800.00),
(6, 6, 5, 2, 5600.00),
(7, 7, 5, 3, 8400.00),
(8, 8, 5, 1, 2800.00),
(9, 9, 6, 2, 5600.00),
(10, 10, 6, 1, 2800.00),
(11, 11, 1, 1, 2800.00),
(12, 12, 2, 2, 5600.00),
(13, 13, 2, 3, 8400.00),
(14, 14, 2, 1, 2800.00),
(15, 15, 3, 2, 5600.00),
(16, 16, 5, 2, 5600.00),
(17, 17, 7, 1, 2800.00),
(18, 18, 7, 3, 8400.00),
(19, 19, 6, 1, 2800.00),
(20, 20, 3, 2, 5600.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `idLugar` int(11) NOT NULL,
  `fila` char(1) NOT NULL,
  `numero` int(11) NOT NULL,
  `ocupado` tinyint(1) DEFAULT 0,
  `idProyeccion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`idLugar`, `fila`, `numero`, `ocupado`, `idProyeccion`) VALUES
(1, 'A', 1, 1, 1),
(2, 'A', 2, 1, 1),
(3, 'B', 1, 1, 1),
(4, 'B', 2, 1, 1),
(5, 'A', 3, 1, 1),
(6, 'C', 1, 1, 1),
(7, 'A', 1, 1, 2),
(8, 'A', 2, 1, 2),
(9, 'B', 1, 1, 2),
(10, 'C', 2, 1, 2),
(11, 'D', 1, 1, 2),
(12, 'A', 1, 1, 3),
(13, 'A', 2, 1, 3),
(14, 'B', 3, 1, 3),
(15, 'C', 1, 1, 3),
(16, 'A', 1, 1, 4),
(17, 'B', 2, 1, 4),
(18, 'C', 3, 1, 4),
(19, 'A', 1, 1, 5),
(20, 'A', 2, 1, 5),
(21, 'B', 1, 1, 5),
(22, 'C', 1, 1, 5),
(23, 'D', 2, 1, 5),
(24, 'A', 1, 1, 6),
(25, 'B', 1, 1, 6),
(26, 'C', 2, 1, 6),
(27, 'D', 3, 1, 6),
(28, 'A', 1, 1, 7),
(29, 'A', 2, 1, 7),
(30, 'B', 1, 1, 7),
(31, 'C', 2, 1, 7),
(32, 'A', 1, 1, 8),
(33, 'B', 2, 1, 8),
(34, 'C', 3, 1, 8),
(35, 'D', 4, 1, 8);

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
(1, 'Dune: Parte Dos', 'Denis Villeneuve', 'Chalamet, Zendaya', 'USA', 'Sci-Fi', '2024-03-01', 0),
(2, 'Código Eclipse', 'James Carter', 'Michael Reeves, Anna Blake', 'USA', 'Acción', '2024-11-18', 1),
(3, 'Café en París', 'Louise Marchand', 'Sophie Laurent, Marc Duval', 'Francia', 'Romance', '2025-12-05', 0),
(4, 'El Último Horizonte', 'Mariano Ledesma', 'Martina Soto, Pablo Gómez', 'Argentina', 'Drama', '2025-01-10', 1),
(5, 'Sombras en la Nieve', 'Elena Kova', 'Irina Petrov', 'Rusia', 'Suspenso', '2024-12-05', 1),
(6, 'Universo Paralelo', 'Gustavo Romano', 'Carla Díaz', 'Argentina', 'Sci-Fi', '2026-02-22', 0),
(7, 'El Eco del Mar', 'Tomoko Sato', 'Hiro Tanaka', 'Japón', 'Drama', '2025-01-03', 1),
(8, 'Furia Urbana', 'Daniel Ortega', 'Juan Cruz', 'Argentina', 'Acción', '2024-10-10', 1),
(9, 'Cazadores del Pasado', 'Robert Mills', 'Chris Miller', 'USA', 'Aventura', '2026-04-20', 0),
(10, 'Pesadilla Digital', 'Hana Kim', 'Kim Soo', 'Corea', 'Terror', '2024-09-13', 0),
(11, 'Amor en Movimiento', 'Ana Rodríguez', 'Camila Torres', 'Argentina', 'RomCom', '2024-11-01', 1),
(12, 'Voces del Alma', 'Carmina Fuentes', 'Sergio Ramos', 'México', 'Drama', '2025-01-28', 1),
(13, 'Más Allá del Silencio', 'Marco Conti', 'Isabella Rossi', 'Italia', 'Drama', '2026-02-10', 0),
(14, 'Proyecto Fénix', 'Victor Huang', 'Chen Wei', 'China', 'Sci-Fi', '2026-03-02', 0),
(15, 'El Último Gol', 'Carlos Vega', 'Ramiro López', 'España', 'Deporte', '2024-08-20', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyeccion`
--

CREATE TABLE `proyeccion` (
  `idProyeccion` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idioma` varchar(30) DEFAULT NULL,
  `es3D` tinyint(1) DEFAULT 0,
  `subtitulada` tinyint(1) DEFAULT 0,
  `horaInicio` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `fecha` date NOT NULL DEFAULT curdate(),
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyeccion`
--

INSERT INTO `proyeccion` (`idProyeccion`, `idPelicula`, `idSala`, `idioma`, `es3D`, `subtitulada`, `horaInicio`, `horaFin`, `fecha`, `precio`) VALUES
(1, 2, 1, 'Español', 1, 0, '18:00:00', '20:20:00', '2025-11-20', 2800.00),
(2, 5, 1, 'Español', 1, 1, '21:00:00', '23:00:00', '2025-11-20', 2900.00),
(3, 4, 2, 'Español', 0, 0, '19:00:00', '21:30:00', '2025-11-21', 2500.00),
(4, 11, 2, 'Español', 0, 1, '22:00:00', '00:00:00', '2025-11-21', 2600.00),
(5, 7, 3, 'Inglés', 1, 1, '18:30:00', '20:40:00', '2025-11-22', 3000.00),
(6, 12, 3, 'Español', 1, 0, '21:30:00', '23:40:00', '2025-11-22', 3100.00),
(7, 8, 4, 'Español', 0, 0, '17:00:00', '19:10:00', '2025-11-23', 2300.00),
(8, 2, 4, 'Inglés', 0, 1, '20:00:00', '22:40:00', '2025-11-23', 2400.00),
(11, 12, 2, 'Inglés', 1, 1, '09:53:59', '11:53:00', '2025-11-22', 2500.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE `salas` (
  `idSala` int(11) NOT NULL,
  `nroSala` int(11) NOT NULL,
  `apta3D` tinyint(1) DEFAULT 0,
  `capacidad` int(11) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`idSala`, `nroSala`, `apta3D`, `capacidad`, `estado`) VALUES
(1, 1, 1, 24, 1),
(2, 2, 0, 24, 1),
(3, 3, 1, 24, 1),
(4, 4, 0, 24, 1),
(5, 5, 0, 24, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `idTicket` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaFuncion` date DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `idComprador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`idTicket`, `fechaCompra`, `fechaFuncion`, `monto`, `idComprador`) VALUES
(1, '2025-11-20', '2025-11-20', 5600.00, 1),
(2, '2025-11-20', '2025-11-20', 8400.00, 2),
(3, '2025-11-21', '2025-11-21', 2800.00, 3),
(4, '2025-11-22', '2025-11-22', 5600.00, 4),
(5, '2025-11-23', '2025-11-23', 2800.00, 5),
(6, '2025-11-24', '2025-11-22', 5600.00, 6),
(7, '2025-11-24', '2025-11-22', 8400.00, 7),
(8, '2025-11-24', '2025-11-22', 2800.00, 8),
(9, '2025-11-24', '2025-11-23', 5600.00, 9),
(10, '2025-11-24', '2025-11-23', 2800.00, 10),
(11, '2025-11-25', '2025-11-20', 2800.00, 1),
(12, '2025-11-25', '2025-11-21', 5600.00, 2),
(13, '2025-11-25', '2025-11-21', 8400.00, 3),
(14, '2025-11-25', '2025-11-21', 2800.00, 4),
(15, '2025-11-25', '2025-11-22', 5600.00, 5),
(16, '2025-11-26', '2025-11-22', 5600.00, 6),
(17, '2025-11-26', '2025-11-23', 2800.00, 7),
(18, '2025-11-26', '2025-11-23', 8400.00, 8),
(19, '2025-11-26', '2025-11-22', 2800.00, 9),
(20, '2025-11-26', '2025-11-21', 5600.00, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compradores`
--
ALTER TABLE `compradores`
  ADD PRIMARY KEY (`idComprador`),
  ADD UNIQUE KEY `dni` (`dni`);

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
  MODIFY `idComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  MODIFY `idDetalleLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `idLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  MODIFY `idProyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `salas`
--
ALTER TABLE `salas`
  MODIFY `idSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `idTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  ADD CONSTRAINT `detalle_lugares_ibfk_1` FOREIGN KEY (`idDetalle`) REFERENCES `detalle_tickets` (`idDetalle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_lugares_ibfk_2` FOREIGN KEY (`idLugar`) REFERENCES `lugares` (`idLugar`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  ADD CONSTRAINT `detalle_tickets_ibfk_1` FOREIGN KEY (`idTicket`) REFERENCES `tickets` (`idTicket`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_tickets_ibfk_2` FOREIGN KEY (`idProyeccion`) REFERENCES `proyeccion` (`idProyeccion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD CONSTRAINT `lugares_ibfk_1` FOREIGN KEY (`idProyeccion`) REFERENCES `proyeccion` (`idProyeccion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD CONSTRAINT `proyeccion_ibfk_1` FOREIGN KEY (`idPelicula`) REFERENCES `peliculas` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proyeccion_ibfk_2` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`idComprador`) REFERENCES `compradores` (`idComprador`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
