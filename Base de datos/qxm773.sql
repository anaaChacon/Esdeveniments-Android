-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2017 a las 19:45:03
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
  `nombre_categoria` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `foto_categoria` varchar(800) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `nombre_categoria`, `foto_categoria`) VALUES
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
(6, 'Concierto de Amaral', '2017-06-27', '20:00:00', '2017-06-27', '22:00:00', 'Amaral es un grupo musical español de pop rock  y rock originario de Zaragoza formado por Eva Amaral y Juan Aguirre. Por el momento, su discografía se compone de seis discos de estudio, una edición especial y dos DVD en directo, desde 1998 hasta la actualidad.\nEstrella de mar, su álbum más vendido, ocupa el vigésimo cuarto puesto en la lista de \"Los 50 mejores discos del rock español\", confeccionada por la revista Rolling Stone, considerando que \"supuso el despegue definitivo de Amaral, el disco que les llevó a la primera línea del rock español\". En otra lista de esa misma publicación, titulada \"Las 200 mejores canciones del pop-rock español\", que engloba canciones de géneros muy dispares pero que forman parte de la memoria colectiva de España, aparecen \"Sin ti no soy nada\" en el puesto 67 y \"Cómo hablar\" en el puesto 128. Además, Eva Amaral está considerada una de las mejores vocalistas rock españolas.', '+info', 'http://img.rtve.es/i/?w=1180&i=1463737295753.jpg', 'http://ocio.laopinioncoruna.es/img_contenido/noticias/2011/12/34655/2011_10_17_IMG_2011_10_17_180433_amaral.jpg', 1, 7, 1),
(8, 'Concierto de Dani Martín', '2017-06-27', '22:00:00', '2017-06-27', '23:30:00', 'Daniel Martín García (Madrid, 19 de febrero de 1977), conocido artísticamente como Dani Martín, es un cantante y actor español, conocido por ser compositor, líder y exvocalista del grupo de pop rock El Canto del Loco.\nDani Martín nació en Madrid el 19 de febrero de 1977. Se crió con sus padres y su hermana en Alcobendas, a las afueras de Madrid. Desde pequeño le gustaba imitar a sus artistas favoritos en el salón de su casa, lo que le llevó a soñar con formar su propio grupo algún día. Consciente de que el instituto no le atraía, comenzó a trabajar con su padre a la vez que desarrollaba sus estudios de Arte Dramático en la escuela de Cristina Rota, quien le facilitó sus primeros trabajos como actor teatral y cómico.', '+info', 'https://sergioferrandezjaen.files.wordpress.com/2015/02/fer5269.jpg', 'http://www.lacronica.net/imagenes/fotosdeldia/27660_la_puesta_en_escena_y_el_despliegue_del_escenario_no_defraudo_a_los_seguidores_de_dani_martin_.jpg', 1, 8, 1),
(14, 'Real Madrid vs Valencia', '2017-06-29', '16:30:00', '2017-06-29', '18:00:00', 'Los días de lluvia son días mudos. Y más cuando son inesperados. Son días en los que parece que haya un acuerdo universal para que el único sonido que se oiga es el de las gotas impactando contra el cristal de la ventana. Un falso silencio que sólo es capaz de romper un grito desgarrador como el de Marcelo celebrando su gol, el del Bernabéu chillando un \"Otra vez\" por detrás de ese \"Gooool\" que se escuchaba bajo las nubes, el sonido de la Liga.', '+info', 'https://okdiario.com/img/2016/05/real-madrid-vs-valencia.jpg', 'https://s-media-cache-ak0.pinimg.com/originals/7e/10/74/7e10747cec612e93acab96576cffcd04.jpg', 5, 11, 1),
(15, 'David Bowie', '2017-06-29', '12:15:00', '2017-06-30', '14:25:00', 'El Museu del Disseny de Barcelona acoge la exposición David Bowie is. La muestra, una producción del Victoria and Albert Museum de Londres, ha recibido ya más de un millón y medio de visitantes en las ocho ciudades que la han acogido hasta ahora, como la capital inglesa, Berlín, París o Chicago, lo que la convierte en la muestra más visitada en los 164 años de historia del museo británico.', '+info', 'http://blog.ticketea.com/wp-content/uploads/2016/11/sold-out-1024x672.jpg', 'http://www.lavanguardia.com/r/GODO/LV/p3/WebSite/2016/09/12/Recortada/img_lgomez_20160913-094231_imagenes_lv_otras_fuentes_david_bowie-kPDH--992x558@LaVanguardia-Web.jpg', 3, 12, 1),
(16, 'Feria del automóvil', '2017-06-30', '10:00:00', '2017-07-02', '20:30:00', 'La Feria del Automóvil de Valencia se ha consolidado en sus 19 ediciones de historia como la plataforma comercial más rentable y más efectiva para las marcas y sus concesionarios, por encima, incluso, de los grandes salones especializados del automóvil de nuestro país. En su última edición, celebrada del 2 al 6 de diciembre en Feria Valencia, el salón alcanzó la cifra de 2.876 coches vendidos en apenas cinco días. Una cifra a la que hay que incorporar las ventas formalizadas en los días posteriores y que suponen haber superado los 3.000 vehículos vendidos directamente.', '+info', 'http://www.guiatourracing.com/imgs/articulos/salon_eco_madrid/images/23.jpg', 'http://www.feriaautomovil.es/wp-content/uploads/2017/01/las-ventas-de-coches-usados-crecieron-un-123-en-2016.jpg', 4, 13, 1),
(17, 'Concerts de la fundació', '2017-10-05', '19:00:00', '2017-10-12', '22:00:00', 'El otoño en Valencia se vive en clave musical gracias a la cantidad de conciertos en Valencia de los que puedes disfrutar. Pero esto no queda aquí porque del 14 de octubre al 9 de junio de 2017 los intérpretes del Berklee College of Music de Valencia ofrecen el ciclo “Concerts a la Fundació” que tendrá lugar en Centro Cultural Bancaja. Echa un vistazo a nuestra agenda en Valencia para estar al tanto de todas las novedades.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2016/10/concerts.gif', 'http://www.lovevalencia.com/wp-content/uploads/2012/10/Conciertos-Valencia3-750x400.jpg', 1, 15, 1),
(18, 'David Bisbal', '2017-06-28', '15:00:00', '2017-06-30', '18:00:00', 'Uno de nuestros artistas más internacionales, David Bisbal, viene a Valencia en concierto el día 9 de junio. El almeriense hace parada en la ciudad con su gira Hijos del Mar Tour 2017. Descubre en la agenda de conciertos en Valencia los artistas que tocarán durante estos meses venideros. Además, en la agenda mensual puedes encontrar los planes en Valencia perfectos.\n\nEl concierto tendrá lugar en la Plaza de Toros de Valencia a las 22:00. El cantante hará un repaso a los temas de su último disco Hijos del Mar y recordará los éxitos más sonados de su trayectoria musical como “Ave María” o “Bulería”. Hijos del mar salió a la venta en diciembre de 2016 y tan sólo en dos semanas se convirtió en Disco de Platino en nuestro país.', '+info', 'http://smhttp.62077.nexcesscdn.net/804D224/ievenncdn/wp-content/uploads/2016/12/david-bisbal-1-1014x586.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/03/Concierto-de-David-Bisbal-en-Valencia-1.jpg', 1, 16, 1),
(19, 'Exposición solidaria', '2017-06-30', '12:00:00', '2017-06-30', '19:30:00', 'El Centre del Carme acoge, la exposición ‘Refugiar-te’ en la que participan 187 artistas, la mayoría de la Comunitat Valenciana y que une arte y solidaridad en beneficio de las ONG’S de ayuda al refugiado. Consulta toda la agenda para este mes de junio y visita todas las exposiciones que hay en marcha en la ciudad de Valencia.\n\nEsta iniciativa, en la que también colabora el Consorci de Museus de la Comunitat Valenciana, podrá ser visitada hasta el 10 de junio en la sala Cofrentes del Centre del Carme. Como adelantábamos, está compuesta por más de 200 obras de 187 artistas y pretende recaudar fondos para las ONG’S de ayuda al refugiado. El objetivo principal de esta acción es intercambiar las obras cedidas por parte de los artistas visuales por donaciones para OXFAM Intermón y CEAR PV.', '+info', 'http://tietarteve.com/wp-content/uploads/2016/08/RefugiArte-Arenas-en-Arenas-de-San-Pedro.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/05/bbb.jpg', 3, 17, 1),
(20, 'Talleres para niños en inglés', '2017-07-04', '11:45:00', '2017-07-05', '18:00:00', 'HiFun Activity Centre organiza talleres en inglés para niños donde aprenderán el idioma mientras se divierten y juegan con otros niños. ¿No sabes qué hacer en Valencia? Encuentra los mejores planes de ocio y actividades para niños que ofrece la ciudad en nuestra agenda de Valencia.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2016/11/talleres-en-ingles-hi-fun-center.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2016/11/hi5-science-1.jpg', 7, 18, 1),
(21, 'Jarabe de Palo', '2017-08-09', '12:00:00', '2017-08-09', '19:56:00', 'Pau Donés y su banda se suben a los escenarios tras dos años de parón. La cita en Valencia será el 10 de junio en el Palau de la Música. Decenas de eventos llenarán tu agenda este verano en Valencia, no dejes pasar la oportunidad de ver a Jarabe de Palo en Valencia.\n\nDentro del ciclo de Rock, pop…al Palau de la Música de València llega, el sábado 10 de junio, la mítica banda Jarabe de Palo que cumple 20 años sobre los escenarios. Para celebrarlo, y celebrar también los 50 años que cumple su vocalista, la gira 50 Palos recorrerá el país repasando sus grandes temas a ritmo de Jazz; con una puesta en escena compuesta por un cuarteto (piano, voz, violonchelo y contrabajo) acompañado de proyecciones y algún que otro monologo por parte de Pau Donés.', '+info', 'http://www.jarabedepalo.com/img/2017/50palos.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/05/Jarabe-de-Palo-en-Valencia.jpg', 1, 19, 1),
(22, 'Crazy Cross', '2017-07-20', '12:00:00', '2017-07-21', '22:30:00', 'Crazy Cross es la 1ª carrera de obstáculos hinchables de Europa, donde diversión y acción social corren de la mano. La loca prueba llega en su primera edición valenciana a Paterna, el próximo 10 de junio a partir de les 16:00 horas, con el único objetivo de que familias y amigos vuelvan a la infancia, aunque sea solo por un rato.\n\nAl participar, un pequeño porcentaje de la entrada será destinado a la investigación de la Esclerosis Múltiple y es necesario, al menos, donar 1kg de comida para los bancos de alimentos del municipio en el que tiene lugar la carrera.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2017/04/17492444_1682581998435850_5687355059493755322_o.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/04/portadainscripcionesvalencia-750x400.jpg', 7, 20, 1),
(23, '15K Nocturna', '2017-07-20', '22:00:00', '2017-07-26', '22:30:00', 'La noche de sábado 10 de junio de 2017 se celebra una de las citas runner más destacadas de la ciudad de Valencia. Correr por la noche en Valencia es posible gracias a la 15K Nocturna Valencia 2017. Una carrera popular en Valencia que destaca por lo extraordinario de sus horario.\n\nLos corredores tendrán un recorrido 100% urbano y de asfalto, plano y céntrico que seguro que dejan imágenes inolvidables. ¿Todavía no sabes qué hacer en Valencia? Consulta la agenda de eventos y podrás conocer los planes más destacados de la ciudad.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2016/05/15k-nocturna.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/3799638968_a3141a52f5.jpg', 8, 21, 1),
(25, 'Festival en Valencia', '2017-07-20', '20:00:00', '2017-07-21', '12:30:00', 'El Festival de les Arts vuelve con su tercera edición el 9 y 10 de junio de 2017 en la Ciudad de las Artes y las Ciencias de Valencia. Si no sabes qué hacer en Valencia, consulta la agenda de Valencia con todos los eventos en Valencia.\n\nLas dos ediciones anteriores del Festival de les Arts en Valencia fueron todo un éxito y contaron con un cartel de artistas nacionales e internacionales excepcional. Todo esto combinado con una variada oferta de food trucks en el Foodies Merkat y muchas más actividades, ya que, como ellos mismos se definen, el festival nace “de la conexión entre la música y las disciplinas artísticas como el diseño, la ilustración, la moda o la gastronomía”.', '+info', 'http://www.elperfildelatostada.com/wp-content/uploads/2014/12/festival-de-les-arts-2015-cartel.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2015/06/festival-de-les-arts-2017-valencia.jpg', 4, 22, 1),
(26, 'Fira Modernista', '2017-07-26', '18:00:00', '2017-07-26', '21:30:00', 'La Fira Modernista celebra su segunda edición en Carcaixent los días 9, 10 y 11 de junio. Si no sabes qué hacer en Valencia y quieres escapar de la rutina, puedes otros planes de ocio en la agenda de Valencia.\n\nUn acontecimiento único que te permitirá viajar al 1900 en un fin de semana lleno de interesantes propuestas para volver a vivir el esplendor modernista de finales de s.XIX y principios del s.XX\n\nEsta iniciativa promovida por el Ayuntamiento cuenta con degustaciones gastronómicas, visitas a los edificios y a los huertos monumentales, recreaciones históricas, concursos y un completo programa de actividades.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2015/06/Carcaixent.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2016/05/fira-modernista-carcaixent-2017.jpg', 4, 23, 1),
(27, 'Feria alternativa', '2017-07-29', '15:20:00', '2017-07-29', '18:30:00', '¡La Feria Alternativa de Valencia está de aniversario! En 2017 cumple 30 años y promete una celebración por todo lo alto. ¿No sabes qué hacer en Valencia? No lo dudes y consulta nuestra agenda de Valencia con toda la información sobre eventos en Valencia.\n\nLa 30ª Feria Alternativa de Valencia se celebra en el cauce del río Turia, entre el puente de las Flores y el puente de la Exposición (la popular peineta de Calatrava). Del 9 al 11 de junio llega la nueva edición de esta feria en Valencia con la que se pretende demostrar que otro mundo es posible. Una visión alternativa del mundo en el que nos hayamos inmersos.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2017/05/CARTEL_FIRA_ALTERNATIVA_FINAL_A4-1.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/05/Feria-Alternativa-de-Valencia.jpg', 4, 24, 1),
(28, 'Corpus Christi', '2017-08-17', '12:00:00', '2017-08-17', '23:30:00', 'Del 16 al 17 de junio de 2017 se celebra el Corpus Christi en Valencia, considerada una de las mayores festividades de la ciudad.\n\nNo obstante, previamente a estos días grandes hay previsto una serie de actividades que ayudarán a divulgar el patrimonio festivo de esta histórica fiesta; dentro del programa ”De la festa, la vespra” del 1 al 10 de junio. Echa un vistazo al programa del Corpus Christi Valencia 2017 y conoce todos los horarios y recorridos. Cabalgatas, procesiones, desfiles y muchas más actividades te están esperando.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2012/03/CORPUS-CHRISTI-VALENCIA-2017.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2012/03/CORPUS-CHRISTI-VALENCIA-2017.jpg', 6, 25, 1),
(29, 'Noche de San Juan', '2017-06-23', '22:00:00', '2017-06-24', '00:00:00', 'La Noche de San Juan se celebra por todo lo alto en Valencia. Las playas de la ciudad se llenan de fiesteros y supersticiosos, que apuran hasta bien entrada la madrugada para realizar los rituales de San Juan que vaticinan buena suerte. Disfruta de las verbenas de San Juan 2017  y entérate de toda la info del reparto de leña 2017 en Valencia.\n\nEn la Noche de San Juan 2017, el agua también se convierte en un elemento imprescindible en los rituales que se llevan a cabo en esta noche mágica de 23 al 24 de junio.\n\nLa tradición dice que saltar las olas del mar, aseguran un año de salud, eso sí, según marca el ritual se deben saltar de espaldas.\n\nLa playa se convierte en un escenario de fuego. La gente se sienta alrededor de hogueras para conmemorar la Noche de San Juan 2017. Consulta las playas en las que no se pueden encender hogueras en Valencia.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2014/04/noche-san-juan-Valencia.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2016/04/san-juan-2016-valencia.jpg', 8, 26, 1),
(30, 'Festival ETNOMUSIC', '2017-07-27', '12:30:00', '2017-07-29', '20:55:00', 'El Museu Valencià d’Etnologia lanza una nueva edición de su Festival Etnomusic que, al igual que en la pasada entrega, acogerá todos los jueves conciertos durante los meses de abril, mayo y junio. Este ciclo contará con ocho conciertos, dos más respecto al año anterior, en los que se mezclará la música tradicional valenciana, con planteamientos modernos y actuales, junto con estilos de orígenes culturales totalmente diferentes.\n\nDescubre, además, otros posibles planes visitando la agenda de eventos en Valencia y así podrás conocer los demás planes en Valencia.', '+info', 'http://www.lovevalencia.com/wp-content/uploads/2012/03/Museu-Valenci%C3%A0-de-Etnologia-.jpg', 'http://www.lovevalencia.com/wp-content/uploads/2017/04/web-portada-et2017-750x383.jpg', 1, 27, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

CREATE TABLE `lugares` (
  `id_lugar` int(4) NOT NULL,
  `nombreLugar` varchar(35) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `horario` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `coor_latitud` double DEFAULT NULL,
  `coor_longitud` double DEFAULT NULL,
  `informacion` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `imagen` text CHARACTER SET utf8 COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`id_lugar`, `nombreLugar`, `direccion`, `horario`, `coor_latitud`, `coor_longitud`, `informacion`, `imagen`) VALUES
(7, 'Plaza de toros', ' Carrer de Xàtiva, 28', NULL, 39.46662550000001, -0.3761197999999999, 'La plaza de toros de Valencia fue construida entre los años 1850 y 1860 en el solar de una plaza anterior que por problemas de presupuesto nunca llegó a terminarse. Es neoclásica, concretamente dórico sencillo, inspirada en la arquitectura civil romana, el anfiteatro Flavio (Coliseo), o el anfiteatro de Nimes (Francia). Fue construida por el arquitecto valenciano Sebastián Monleón Estellés. Su estructura la forma un polígono de 48 lados, con 384 arcos al exterior, realizados en ladrillo siguiendo el estilo neomudéjar.', 'http://www.jdiezarnal.com/valenciaplazadetorosvista01.jpg'),
(8, 'Palacio de la artes', 'Avenida del Professor López Piñero, 1', NULL, 39.45845219999999, -0.3565385, 'El Palau de les Arts Reina Sofía es el teatro de la ópera de Valencia (España), y sede de la Orquesta de la Comunidad Valenciana. Es obra de Santiago Calatrava y forma parte del complejo arquitectónico de la Ciudad de las Artes y las Ciencias.\nFue inaugurado el 8 de octubre de 2005. La primera ópera se interpretó el 25 de octubre de 2006, eligiéndose para la ocasión Fidelio, de Beethoven. Desde sus comienzos hasta 2015, fue su intendente Helga Schmidt quien contrató a Lorin Maazel como director musical, quien a su vez tuvo la tarea de formar una nueva orquesta estable para el teatro, la Orquesta de la Comunitat Valenciana. Por su escenario han pasado voces de primera línea y también batutas de nivel como la de Zubin Mehta quien hasta el 2014 dirigió el Festival de Mediterrani, una mini temporada de ópera donde se fue representando, entre otras óperas, la tetralogía del El Oro del Rin de Wagner, la primera producción española de esta obra.', 'http://vacarquitectura.es/wp-content/uploads/2012/07/palacio-las-artes.jpg'),
(11, 'Estadio Santiago Bernabéu', 'Av. de Concha Espina, 1', NULL, 40.4530541, -3.6883445, 'El Estadio Santiago Bernabéu es un recinto deportivo propiedad del Real Madrid Club de Fútbol, situado en pleno Paseo de la Castellana, en el distrito de Chamartín de Madrid, España. Se inauguró el 14 de diciembre de 1947 y su aforo actualmente es de 81.044 espectadores.1 El estadio está catalogado por la UEFA con la máxima distinción, «estadio élite».', 'http://as00.epimg.net/en/imagenes/2017/04/27/football/1493315049_173974_1493315209_noticia_normal.jpg'),
(12, ' Museu del Disseny de Barcelona ', 'Plaça de les Glòries Catalanes, 37', NULL, 41.402451, 2.1880918, 'El Museo del Diseño de Barcelona (oficialmente y en catalán Museu del Disseny de Barcelona) es un museo dependiente del Instituto de Cultura de Barcelona que se origina a partir de la integración de las colecciones del Museo de las Artes Decorativas, el Museo de Cerámica, el Museo Textil y de Indumentaria y el Gabinete de las Artes Gráficas. El museo está ubicado en el edificio del Disseny Hub Barcelona, en la Plaça de les Glòries, compartiendo sede con el Foment de les Arts i el Disseny (FAD) y el Barcelona Centro de Diseño (BCD), dos instituciones pioneras en la promoción y el desarrollo del diseño en Cataluña.', 'http://graffica.info/wp-content/uploads/2014/02/00_Premios-Graffica-Museu-del-Disseny-Barcelona.jpg'),
(13, 'Feria de Muestras de Valencia', 'Carrer de Cullera, 67', NULL, 39.50379839999999, -0.4235273, 'Feria Muestrario Internacional de Valencia o, como marca comercial, Feria Valencia es la institución organizadora de certámenes feriales más antigua de España, siendo su año de fundación en 1917. Recientemente remodelada y ampliada, obra del arquitecto José María Tomás Llavador, se constituye una de las más importantes dentro del circuito europeo.', 'http://grupolasser.com/wp-content/uploads/2015/07/feria-puertas-automaticas-valencia-2017.jpg'),
(14, 'Centro Cultural Bancaja', 'Plaza de tetuán, 23', NULL, 39.4737676, -0.3700764, 'El Centro Cultural Bancaja se trata de un espacio multidisciplinar, en el que podemos encontrar exposiciones temporales, asistir a conferencias y talleres o leer en la sala de estudios universitaria.\n\nEl Centro Cultural Bancaja es un edificio emblemático ubicado en pleno centro de la ciudad de Valencia. Es el resultado de la unión de dos edificios históricos: la sede de la antigua Caja de Ahorros y Monte de Piedad de Valencia, de principios del siglo XX, y de la antigua residencia de Manuel Gómez Fos, de finales del siglo XIX.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/Centro-Bancaja2.jpg'),
(15, 'Centro Cultural Bancaja', 'Plaza de Tetuán, 23', NULL, 39.4737676, -0.3700764, 'El Centro Cultural Bancaja se trata de un espacio multidisciplinar, en el que podemos encontrar exposiciones temporales, asistir a conferencias y talleres o leer en la sala de estudios universitaria.\n\nEl Centro Cultural Bancaja es un edificio emblemático ubicado en pleno centro de la ciudad de Valencia. Es el resultado de la unión de dos edificios históricos: la sede de la antigua Caja de Ahorros y Monte de Piedad de Valencia, de principios del siglo XX, y de la antigua residencia de Manuel Gómez Fos, de finales del siglo XIX.\n\nLa rehabilitación, llevada a cabo en 2007 por los arquitectos Pablo Martínez Montesa, José Ricardo Martínez Montesa y José Alberto Jordá Albiñana, supuso la unión de ambos edificios y permitió crear el actual Centro Cultural Bancaja con una superficie de 12.000 metros cuadrados.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/Centro-Bancaja2.jpg'),
(16, 'Plaza de Toros de Valencia', 'C/ Xátiva, 28', NULL, 39.46662550000001, -0.3761197999999999, 'La Plaza de Toros, es uno de los principales monumentos de corte neoclásico más impresionantes de la ciudad de Valencia, con su forma poligonal de 48 lados, cuenta con más de 52 metros de diámetro en el ruedo y una altura de 17 metros. Es una de las plazas de toros mayor tamaño de España.\n\nLas obras de construcción de la Plaza de Toros de Valencia a manos del arquitecto valenciano Sebastián Monleón Estellés, duró diez años, de 1850 a 1860, sobre una plaza anterior inacabada. Se inspiró en la arquitectura civil romana, presente en edificios como el anfiteatro de Nimes (Francia) o el Coliseo de Flavio Marcelo (Roma).', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/torooos.jpg'),
(17, 'Centre del Carme', 'C/Museo, 2 - 4', NULL, 39.47956300000001, -0.37912, 'El antiguo Convento del Carmen está situado en la calle Museo de la ciudad de Valencia (España). El conjunto monumental de este antiguo convento ilustra desde su complejidad cronológica y diversidad de estilos artísticos, importantes etapas de la historia de la arquitectura en la ciudad de Valencia.', 'http://www.lovevalencia.com/wp-content/uploads/2010/11/convento-carmen-valencia.jpg'),
(18, 'HI5 ENGLISH SCHOOL', 'CALLE XIPRERS 13 BAJO', NULL, 39.4952047, -0.4052373, 'Un equipo muy profesional y muy bien preparado. Excelente centro para adultos y niños, que aprenden inglés mientras se divierten con las actividades organizadas. Y además, se pueden celebrar cumples, tienen parque de bolas para los niños y zona de relax para los papis. Para mi, sin duda, un 10.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/3799638968_a3141a52f5.jpg'),
(19, 'Palau de la Música', 'Pº Alameda, 30', NULL, 39.466272, -0.3601279000000001, 'El Palau de la Música, se encuentra en el antiguo cauce del río Turia y fue inaugurado el 25 de abril de 1987. El Palau de la Música es uno de los auditorios musicales más importantes de España y sede de la Orquesta de Valencia\n\nEl edificio, diseñado por José María García de Paredes, está formado por dos fachadas: una que da al jardín del Turia, cubierta por una bóveda de cristal y la fachada posterior, que da al Paseo de la Alameda contiene varias salas para audiciones musicales, congresos, exposiciones y eventos varios.', 'http://www.lovevalencia.com/wp-content/uploads/2012/07/palaumusica.jpg'),
(20, 'Paterna', 'Paterna', NULL, 39.5037093, -0.4431618, 'Paterna es un municipio de la Comunidad Valenciana, España. Perteneciente a la provincia de Valencia, está situado al noroeste de la comarca de la Huerta de Valencia (en la actual comarca administrativa de la Huerta Oeste) a 5 km al noroeste de Valencia, y en la ribera izquierda del río Turia. Su población, a 1 de enero de 2015, era de 67.340 habitantes, siendo la décima ciudad por número de habitantes de la Comunidad Valenciana y la segunda del área metropolitana de Valencia.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Paterna._Ajuntament.JPG/800px-Paterna._Ajuntament.JPG'),
(21, 'Valencia', 'Valencia', NULL, 39.4699075, -0.3762881, 'El circuito dará el pistoletazo de salida en la Avenida del Puerto y el recorrido de la carrera culminará en el Edificio del Reloj, situado en el Tinglado 2 del Puerto de Valencia.\n\nTe indicamos cómo llegar a la 15k Nocturna 2017 de la manera más cómoda y directa,\n\nPodrás hacer uso del transporte público y en el caso de que tengas que llegar con vehículo propio, la organización de la 15K Nocturna 2017 ha previsto un parking para facilitar el acceso.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/3799638968_a3141a52f5.jpg'),
(22, 'Las artes y las ciencias', 'Avda. autopista del saler, 7', NULL, 39.45427, -0.3513899999999999, 'La Ciudad de las Artes y las Ciencias es uno de los principales monumentos escogidos por los visitantes cuando deciden qué ver en Valencia.\n\nLa Ciudad de las Ciencias y las Artes es un conjunto arquitectónico y cultural ubicado en el antiguo cauce del río Turia. Fue diseñado por el famoso arquitecto Santiago Calatrava, con la colaboración de Félix Candela. Fue inaugurada oficialmente el 16 de abril de 1998 con la apertura de L’Hemisfèric.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/Eventos-Valencia.jpg'),
(23, 'Carcaixent', 'Carcaixent', NULL, 39.10156239999999, -0.4219443999999999, 'Carcagente (en valenciano y oficialmente, Carcaixent) es un municipio español de la provincia de Valencia, Comunidad Valenciana, perteneciente a la comarca de la Ribera Alta. Tiene una población de 20.479 habitantes (INE 2015). La conurbación que forma en la actualidad con los municipios de Alcira y Algemesí constituye, con sus 95.000 habitantes, el segundo núcleo de población de la provincia, tras la capital y su área metropolitana.', 'http://www.riberaexpress.es/wp-content/uploads/2014/11/CARCAIXENT-Pla%C3%A7a-Major.jpg'),
(24, 'Cauce del Turia', 'Alameda', NULL, 39.4699075, -0.3762881, 'Feria Muestrario Internacional de Valencia o, como marca comercial, Feria Valencia es la institución organizadora de certámenes feriales más antigua de España, siendo su año de fundación en 1917. Recientemente remodelada y ampliada, obra del arquitecto José María Tomás Llavador, se constituye una de las más importantes dentro del circuito europeo.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/Cauce-del-Rio-Turia.jpg'),
(25, 'Valencia', 'Valencia', NULL, 39.4699075, -0.3762881, 'Valencia (en valenciano y oficialmente València, AFI: [va?lensia]) es un municipio y una ciudad de España, capital de la provincia homónima y de la Comunidad Valenciana. Anteriormente, Valencia fue también capital de la extinta comarca de la Huerta de Valencia, comarca que en 1989 se disgregó para formar las comarcas de la Huerta Norte, Huerta Sur, Huerta Oeste y ciudad de Valencia, quedando así constituida como la única ciudad-comarca de la Comunidad Valenciana.', 'http://www.lovevalencia.com/wp-content/uploads/2012/01/3799638968_a3141a52f5.jpg'),
(26, 'Paseo Marítimo', 'Paseo de Neptú, 3', NULL, 39.4699075, -0.3762881, 'El Paseo Marítimo de Valencia fue diseñado y construido en la década de 1990, para completar el acercamiento de la ciudad al mar, un acercamiento que se inició en el siglo XVIII con la construcción de la avenida del Puerto.\n\nEl Paseo Marítimo arranca en la avenida de Neptuno, cerca del puerto de Valencia y termina en Alboraia. Mide varios kilómetros longitud y los establecimientos instalados en él, se dividen en dos tramos. Los primeros, más antiguos, construidos al inicio de la avenida de Neptuno, son restaurantes con terrazas orientadas a la playa y pequeños hoteles. Los segundos, más cercanos a la Patacona y Alboraia, nacieron con el paseo marítimo y se construyeron en su interior, con terrazas que ocupan el propio paseo.', 'http://www.lovevalencia.com/wp-content/uploads/2012/03/p%C2%BA-mar%C3%ADtimo.jpg'),
(27, 'Museu Valencia de entnologia', 'C/Corona, 36', NULL, 39.4699075, -0.3762881, 'El Museu Valencià d’Etnologia de la ciudad de Valencia, fue creado en 1982 e inaugurado sólo un año después. El Museu Valencià d’Etnologia presenta tres exposiciones permanentes: La ciudad vivida, Ciudades valencianas en tránsito 1800-1940, Huerta y marjal y Secano y montaña, que tratan respectivamente de los ámbitos urbanos, de huerta y marjal, y de secano y montaña del territorio valenciano.\n\nEl Museu Valencià d’Etnologia busca poner al alcance del público un espacio dinámico de conocimiento y reflexión sobre la diversidad cultural, se trata por tanto de uno de los principales museos qué ver en Valencia.', 'https://www.arteinformado.com/documentos/espacios/114/Exterior_del_Museo_de_Prehistoria.jpg'),
(28, 'dczdc', 'dsddsc', NULL, 55.7575875, -24.578544, 'xsdxsdx', 'https://cdn.pixabay.com/photo/2015/03/12/12/43/test-670091_960_720.png');

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
(2, 'gestevents', 'Lesron1', 'Gestión Events de València Extramurs SAU', '84789631S', 'Gran Via Ferran el Católic 45, pta.2', 'gestevents@gestevents.com'),
(22, 'Anabel', '1111', 'Anabel', '1234', '1234', '123@123.com'),
(23, 'gestion', '1234', 'Valencia Ayuntamiento', '52166045G', 'C/La plaza la Reina', 'ayunta@homtail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones`
--

CREATE TABLE `suscripciones` (
  `id_suscripcion` int(8) NOT NULL,
  `idUsuario` int(4) NOT NULL,
  `idEvento` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `suscripciones`
--

INSERT INTO `suscripciones` (`id_suscripcion`, `idUsuario`, `idEvento`) VALUES
(7, 5, 8),
(8, 5, 15),
(9, 5, 16),
(10, 5, 14),
(11, 5, 6);

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
(1, 'ana', '1234', 'Ana Isabel', 'Chacón Fernández', 23, '123@gmail.com'),
(2, 'joromemar', 'Lesron1', 'José Benito', 'Romero Martínez', 19, 'joromemar@campusaula.com'),
(3, 'ggg', 'rff', 'fg', 'fgg', 25, 'fgg'),
(4, 'prueba', 'rff', 'fg', 'fgg', 25, 'fgg'),
(5, 'qq', '123', 'qq', 'qq', 21, 'qq'),
(6, 'aa', 'aa', 'a', 'a', 26, 'dfx@gcv.com'),
(7, 'd', 'd', 'd', 'd', 25, 'shd@djdj.com'),
(8, 'f', '', 'f', 'f', 23, 'dhxh@ff.com'),
(9, 'pablo', '123', 'Pablo', 'Marco Loro', 26, 'pablo@hotmail.com');

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
  MODIFY `id_evento` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `lugares`
--
ALTER TABLE `lugares`
  MODIFY `id_lugar` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `organizadores`
--
ALTER TABLE `organizadores`
  MODIFY `id_organizador` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `id_suscripcion` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
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
