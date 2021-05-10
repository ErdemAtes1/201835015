-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.6.0-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 201835015 için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `201835015` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */;
USE `201835015`;

-- tablo yapısı dökülüyor 201835015.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 NOT NULL,
  `type` enum('management','doctor','patient') CHARACTER SET utf8 NOT NULL DEFAULT 'patient',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- 201835015.user: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `type`) VALUES
	(1, 'Ahmet', '123', 'management'),
	(2, 'Ali', '144', 'doctor'),
	(8, 'Ayşe', '122', 'doctor'),
	(9, 'Mustafa', '159', 'patient'),
	(10, 'erdem', '1233', 'patient');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- tablo yapısı dökülüyor 201835015.whour
CREATE TABLE IF NOT EXISTS `whour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_username` varchar(200) NOT NULL DEFAULT '',
  `wdate` varchar(200) NOT NULL DEFAULT '',
  `status` enum('a','p') NOT NULL DEFAULT 'a',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 201835015.whour: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `whour` DISABLE KEYS */;
INSERT INTO `whour` (`id`, `doctor_id`, `doctor_username`, `wdate`, `status`) VALUES
	(1, 4, 'Ayhan', '2021-05-11 10.00:00', 'a'),
	(2, 2, 'Ali', '2021-05-28 11.30:00', 'a');
/*!40000 ALTER TABLE `whour` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
