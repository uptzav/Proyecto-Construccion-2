-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para db_info_covid19
CREATE DATABASE IF NOT EXISTS `db_info_covid19` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_info_covid19`;

-- Volcando estructura para tabla db_info_covid19.tbl_donacion
CREATE TABLE IF NOT EXISTS `tbl_donacion` (
  `idDonacion` int(11) NOT NULL AUTO_INCREMENT,
  `tipoDonacion` varchar(100) DEFAULT NULL,
  `calidadAlimentos` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `nombreDonante` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idDonacion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_info_covid19.tbl_donacion: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_donacion` DISABLE KEYS */;
INSERT INTO `tbl_donacion` (`idDonacion`, `tipoDonacion`, `calidadAlimentos`, `cantidad`, `direccion`, `nombreDonante`) VALUES
	(1, 'Alimentos', 'Promedio', 12, 'Tacna', 'Chris'),
	(2, 'Alimentos', 'Promedio', 12, 'Tacna', 'Chris'),
	(3, 'Alimentos', 'Fresco', 5, 'Lima', 'Carlos'),
	(4, 'Desinfectantes', 'Fresco', 12, 'tacna', 'fiorella'),
	(5, 'Medicamentos', 'Fresco', 125, 'Urba Tacna 1290', 'Hilda Jimenez Llanque'),
	(6, 'Medicamentos', 'Bien', 20, 'San Martin 345', 'Fabiola');
/*!40000 ALTER TABLE `tbl_donacion` ENABLE KEYS */;

-- Volcando estructura para tabla db_info_covid19.tbl_reporte_caso
CREATE TABLE IF NOT EXISTS `tbl_reporte_caso` (
  `idReporteCaso` int(11) NOT NULL AUTO_INCREMENT,
  `tipoCaso` varchar(100) DEFAULT NULL,
  `apellidoPaciente` varchar(100) DEFAULT NULL,
  `nombrePaciente` varchar(100) DEFAULT NULL,
  `sexo` varchar(100) DEFAULT NULL,
  `edad` int(2) DEFAULT NULL,
  `celular` int(9) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `otrasObservaciones` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idReporteCaso`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_info_covid19.tbl_reporte_caso: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_reporte_caso` DISABLE KEYS */;
INSERT INTO `tbl_reporte_caso` (`idReporteCaso`, `tipoCaso`, `apellidoPaciente`, `nombrePaciente`, `sexo`, `edad`, `celular`, `distrito`, `direccion`, `otrasObservaciones`) VALUES
	(1, 'Familiar', 'Jimenes', 'Javier', 'Masculino', 55, 987657878, 'Pocollay', 'Urba Tacna A-12', 'URGENTE'),
	(2, 'Familiar', 'Jimenez', 'Julia', 'Femenino', 45, 123456789, 'Pocollay', 'Tacna', NULL),
	(3, 'Familiar', 'nkkkk', 'jdjdk', 'Femenino', 45, 976465, 'kdkdkd', 'jdjd', NULL),
	(4, 'Conocido', 'Pérez', 'Sonia', 'Femenino', 43, 123456789, 'Tacna', 'Tacna2', 'fiebre alta'),
	(5, 'Familiar', 'Yupanqui', 'Ruth', 'Femenino', 23, 946532810, 'Tacna', 'Leoncio Prado 1938', 'urgente fiebre alta'),
	(6, 'Familiar', 'Llanque', 'Sonia', 'Femenino', 26, 954326187, 'Pocollay', 'Asoc granados', 'fiebre alta');
/*!40000 ALTER TABLE `tbl_reporte_caso` ENABLE KEYS */;

-- Volcando estructura para tabla db_info_covid19.tbl_usuario
CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_info_covid19.tbl_usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` (`idUsuario`, `nombre`, `email`, `password`, `estado`) VALUES
	(1, 'Administrador', 'admin@gmail.com', '$2y$10$b633Ra.e5kJA8rxCFwX0a.fohz7LCCKkknuv5iWhLvNul4Dd3y.k2', 1);
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
