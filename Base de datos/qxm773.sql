-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2017 a las 14:33:56
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `qxm773`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` int(2) NOT NULL,
  `nombre` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `foto_categoria` varchar(800) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `nombre`, `foto_categoria`) VALUES
(1, 'Música', 'http://africadigna.org/wp-content/uploads/2015/05/453190_tolpa_lyudi_koncert_ruki_vverx_1680x1050_www.GdeFon.ru_.jpg'),
(2, 'Teatre', 'http://www.antonionania.com/es/wp-content/uploads/2016/12/Las-7-mejores-Obras-de-Teatro.jpg'),
(3, 'Exposicions', 'http://www.blogmuseupicassobcn.org/wp-content/uploads/2014/01/Catalegs-de-les-exposicions.jpg'),
(4, 'Fires i Convencions', 'http://www.consultorspolitics.com/wp-content/uploads/2015/07/trasera_atarceder.jpg'),
(5, 'Esports', 'http://statics.viralizalo.com/virs/2016/01/VIR_38220_3637_cuantos_sabes_de_los_estadios_de_futbol_edicion_premier_league.jpg?cb=80977'),
(6, 'Religiós', 'http://blog.ticketea.com/wp-content/uploads/2017/02/semana-santa-procesi%C3%B3n.jpg'),
(7, 'Infantil/Juvenil', 'http://www.barramedia.es/wp-content/uploads/2016/06/manualidades-y-pasatiempos-en-casa-636x303.jpg'),
(8, 'Cultura Popular', 'http://extension.unicen.edu.ar/blog/wp-content/uploads/2011/07/murga.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id_evento` int(8) NOT NULL,
  `nombre` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `hora_inicio` time NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `hora_fin` time NOT NULL,
  `descripcion` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `info_secundaria` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `foto_miniatura` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `foto_principal` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `idCategoria` int(2) NOT NULL,
  `idLugar` int(4) NOT NULL,
  `idOrganizador` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id_evento`, `nombre`, `fecha_inicio`, `hora_inicio`, `fecha_fin`, `hora_fin`, `descripcion`, `info_secundaria`, `foto_miniatura`, `foto_principal`, `idCategoria`, `idLugar`, `idOrganizador`) VALUES
(2, 'Concierto de Amaral', '2017-05-10', '20:00:00', '2017-05-10', '21:30:00', 'Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.', 'www.valenciaevents.net', 'http://img.rtve.es/i/?w=1180&i=1463737295753.jpg', 'http://www.zetaestaticos.com/aragon/img/noticias/1/089/1089332_1.jpg', 1, 2, 1),
(4, 'Valencia vs Osasuna', '2017-05-23', '12:00:00', '2017-05-23', '14:15:00', 'Partido de fútbol definitivo.', '+info', 'http://img1.meristation.com/files/imagenes/general/escudo-logo-valencia-cf.jpg', 'http://www.valenciacf.com/bd/archivos/archivo1899.jpg', 5, 3, 1),
(5, 'gdggc', '2017-05-18', '04:00:00', '2017-05-18', '05:00:00', 'fsrtrg', '+info', 'https://3cldv.files.wordpress.com/2015/09/musica.jpg', 'https://3cldv.files.wordpress.com/2015/09/musica.jpg', 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `id_lugar` int(4) NOT NULL,
  `nombreLugar` varchar(35) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `horario` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `coor_latitud` float DEFAULT NULL,
  `coor_longitud` float DEFAULT NULL,
  `informacion` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `imagen` text CHARACTER SET utf8 COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`id_lugar`, `nombreLugar`, `direccion`, `horario`, `coor_latitud`, `coor_longitud`, `informacion`, `imagen`) VALUES
(2, 'Plaza de Toros', NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Palacio de las artes', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `organizadores`
--

CREATE TABLE `organizadores` (
  `id_organizador` int(8) NOT NULL,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nif` varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `organizadores`
--

INSERT INTO `organizadores` (`id_organizador`, `username`, `password`, `nombre`, `nif`, `direccion`, `email`) VALUES
(1, 'valenciaevents', 'valenciaevents', 'València Events. Administradors de aplicacions', 'N/A', 'N/A', 'contacte@valenciaevents.net'),
(2, 'gestevents', 'Lesron1', 'Gestión d\'Events de València-Extramurs SAU', '84789631S', 'Gran Via Ferran el Católic 45, pta.2', 'gestevents@gestevents.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones`
--

CREATE TABLE `suscripciones` (
  `id_suscripcion` int(8) NOT NULL,
  `idUsuario` int(4) NOT NULL,
  `idEvento` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(8) NOT NULL,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `edad` int(3) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `username`, `password`, `nombre`, `apellidos`, `edad`, `email`) VALUES
(1, 'achafer', 'Lesron1', 'Ana Isabel', 'Chacón Fernández', 23, 'achafer@campusaula.com'),
(2, 'joromemar', 'Lesron1', 'José Benito', 'Romero Martínez', 19, 'joromemar@campusaula.com'),
(3, 'ggg', 'rff', 'fg', 'fgg', 25, 'fgg'),
(4, 'ggg', 'rff', 'fg', 'fgg', 25, 'fgg'),
(5, 'qq', 'qq', 'qq', 'qq', 21, 'qq');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id_evento`),
  ADD KEY `clave ajena categorias` (`idCategoria`),
  ADD KEY `clave ajena lugares` (`idLugar`),
  ADD KEY `clave ajena organizador` (`idOrganizador`);

--
-- Indices de la tabla `lugares`
--
ALTER TABLE `lugares`
  ADD PRIMARY KEY (`id_lugar`);

--
-- Indices de la tabla `organizadores`
--
ALTER TABLE `organizadores`
  ADD PRIMARY KEY (`id_organizador`);

--
-- Indices de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD PRIMARY KEY (`id_suscripcion`),
  ADD KEY `clave ajena usuarios` (`idUsuario`),
  ADD KEY `clave ajena eventos` (`idEvento`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id_evento` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `id_lugar` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `organizadores`
--
ALTER TABLE `organizadores`
  MODIFY `id_organizador` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `id_suscripcion` int(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `clave ajena categorias` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `clave ajena lugares` FOREIGN KEY (`idLugar`) REFERENCES `lugares` (`id_lugar`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `clave ajena organizador` FOREIGN KEY (`idOrganizador`) REFERENCES `organizadores` (`id_organizador`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD CONSTRAINT `clave ajena eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`id_evento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `clave ajena usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
