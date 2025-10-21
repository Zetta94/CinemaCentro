-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-10-2025 a las 18:31:35
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
(1, '40123456', 'Manuel Zuñiga', '1995-05-12', '1234', 'Tarjeta Débito'),
(2, '38999999', 'Lucía Pérez', '1997-09-20', 'abcd', 'Mercado Pago'),
(3, '42111111', 'Carlos Rojas', '1993-03-15', 'pass', 'Crédito'),
(4, '43123456', 'María López', '1998-11-02', 'maria123', 'Efectivo');

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
(5, 3, 5),
(6, 3, 6),
(7, 4, 7),
(8, 4, 8);

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
(1, 1, 1, 2, 5000.00),
(2, 2, 2, 2, 4600.00),
(3, 3, 3, 2, 4400.00),
(4, 4, 4, 2, 4200.00);

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
(1, 'A', 1, 0, 1),
(2, 'A', 2, 1, 1),
(3, 'B', 1, 0, 2),
(4, 'B', 2, 0, 2),
(5, 'C', 1, 1, 3),
(6, 'C', 2, 0, 3),
(7, 'D', 1, 0, 4),
(8, 'D', 2, 0, 4);

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
(1, 'Duna: Parte Dos', 'Denis Villeneuve', 'Timothée Chalamet, Zendaya, Rebecca Ferguson', 'EE.UU.', 'Ciencia Ficción', '2024-03-01', 1),
(2, 'Oppenheimer', 'Christopher Nolan', 'Cillian Murphy, Emily Blunt, Matt Damon', 'EE.UU.', 'Drama / Biográfico', '2023-07-21', 0),
(3, 'El Justiciero: Capítulo Final', 'Antoine Fuqua', 'Denzel Washington, Dakota Fanning', 'EE.UU.', 'Acción', '2023-09-01', 1),
(4, 'Super Mario Bros: La Película', 'Aaron Horvath, Michael Jelenic', 'Chris Pratt, Anya Taylor-Joy, Jack Black', 'EE.UU.', 'Animación / Aventura', '2023-04-05', 0),
(5, 'Elementos', 'Peter Sohn', 'Leah Lewis, Mamoudou Athie', 'EE.UU.', 'Animación / Fantasía', '2023-06-16', 0),
(6, 'Argentina, 1985', 'Santiago Mitre', 'Ricardo Darín, Peter Lanzani, Alejandra Flechner', 'Argentina', 'Drama / Histórico', '2022-09-29', 0);

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
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyeccion`
--

INSERT INTO `proyeccion` (`idProyeccion`, `idPelicula`, `idSala`, `idioma`, `es3D`, `subtitulada`, `horaInicio`, `horaFin`, `precio`) VALUES
(1, 1, 1, 'Español', 1, 0, '18:00:00', '20:30:00', 2500.00),
(2, 2, 2, 'Inglés', 0, 1, '21:00:00', '23:45:00', 2300.00),
(3, 3, 3, 'Español', 0, 0, '19:00:00', '21:20:00', 2200.00),
(4, 4, 4, 'Español', 0, 1, '17:00:00', '18:50:00', 2100.00);

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
(1, 1, 1, 180, 0),
(2, 2, 0, 200, 0),
(3, 3, 1, 150, 0),
(4, 4, 0, 220, 0);

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
(1, '2025-10-21', '2025-10-21', 5000.00, 1),
(2, '2025-10-21', '2025-10-21', 4600.00, 2),
(3, '2025-10-21', '2025-10-22', 4400.00, 3),
(4, '2025-10-21', '2025-10-22', 4200.00, 4);

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
  MODIFY `idComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalle_lugares`
--
ALTER TABLE `detalle_lugares`
  MODIFY `idDetalleLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `detalle_tickets`
--
ALTER TABLE `detalle_tickets`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `idLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  MODIFY `idProyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `salas`
--
ALTER TABLE `salas`
  MODIFY `idSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `idTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
