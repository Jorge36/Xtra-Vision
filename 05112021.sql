-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 52.50.23.197    Database: DB_XTRAVISION
-- ------------------------------------------------------
-- Server version	5.7.33-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id_customer` varchar(16) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `unlimited_account` tinyint(4) NOT NULL,
  `end_unlimited_account` date DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('1234563548796012','',0,NULL,'david matt'),('1234567891234567','nanogomez.it@gamil.com',1,'9999-05-07','juan lopez'),('1234567899283876','nanobu@gmail.com',0,'2021-06-04','paul dave'),('1234567906970695','',0,NULL,'Arnald Mall'),('1234598761234765','',0,'2021-05-02','john watson'),('1283748594939292','',0,NULL,'fernando lop'),('1621436578746296','',0,NULL,'paul haston'),('1863871628736182','',0,NULL,'brian moon'),('2987984372398471','roloxleon@gmail.com',1,'9999-05-06','paul matt'),('4197972974928379','roloxn@gmail.com',0,NULL,'rolando perez'),('4230982380352323','	juanit@gamil.com',0,NULL,'juan peres'),('4791234987632345','',0,NULL,'patrick connor'),('7129876543987654','',0,'2021-05-03','jhony b good'),('7498374982379472','',0,NULL,'claudia rod'),('7614821648761248','',0,NULL,'jhonny merkel'),('7983797237239237','nanobugozo@gmail.com',0,'2021-06-06','mike connor'),('8237948723897492','rolo@gmail.com',0,'2021-06-06','piter connor'),('9347654984858538','',0,'2021-06-05','jose cuevas'),('9479847389274923','',0,NULL,'paul melt'),('9876543217654321','fervar@gmail.com',0,NULL,'fernando varela');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail` (
  `id_rental` int(11) NOT NULL,
  `id_customer` varchar(60) NOT NULL,
  `eircode` varchar(8) NOT NULL,
  `id_detail` int(11) NOT NULL,
  `status` varchar(12) NOT NULL,
  `date_returned` date DEFAULT NULL,
  `price` decimal(15,2) NOT NULL,
  `late_fee` decimal(15,2) DEFAULT NULL,
  `final_price` decimal(15,2) NOT NULL,
  `electronic_tag` varchar(8) NOT NULL,
  `eircodeReturn` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_rental`,`id_customer`,`eircode`,`id_detail`),
  KEY `fk_Detail_Rental1_idx` (`id_rental`,`id_customer`,`eircode`),
  CONSTRAINT `fk_Detail_Rental1` FOREIGN KEY (`id_rental`, `id_customer`, `eircode`) REFERENCES `rental` (`id_rental`, `id_customer`, `eircode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES (11,'9876543217654321','N39R6T3',1,'returnedLate','2021-05-06',2.99,4.50,2.99,'AAA111','N39R6T3'),(11,'9876543217654321','N39R6T3',2,'returnedLate','2021-05-10',2.99,10.50,2.99,'AAA444','N39R6T3'),(12,'1234567891234567','N39R6T3',1,'returnedLate','2021-05-04',2.99,1.50,2.99,'AAA333','N37YA29'),(13,'1234598761234765','N39R6T3',1,'returned','2021-05-03',2.99,0.00,0.00,'AAA111','N37YA29'),(13,'1234598761234765','N39R6T3',2,'returnedLate','2021-05-04',2.99,1.50,0.00,'AAA555','N39R6T3'),(14,'7129876543987654','N39R6T3',1,'returnedLate','2021-05-05',2.99,1.50,0.00,'AAA222','H12R8P0'),(15,'9876543217654321','N37YA29',1,'returned','2021-05-03',2.99,0.00,2.99,'AAA666','N37YA29'),(15,'9876543217654321','N37YA29',2,'returnedLate','2021-05-10',2.99,6.00,2.99,'AAA777','N39R6T3'),(15,'9876543217654321','N37YA29',3,'returned','2021-05-06',2.99,0.00,2.99,'AAA888','N39R6T3'),(16,'4791234987632345','N91WPX9',1,'returnedLate','2021-05-11',2.99,9.00,0.00,'AAA122','N91WPX9'),(18,'9347654984858538','H12R8P0',1,'returned','2021-05-05',2.99,0.00,0.00,'BBB111','F52XA61'),(18,'9347654984858538','H12R8P0',2,'returned','2021-05-05',2.99,0.00,0.00,'BBB333','H12R8P0'),(19,'1234563548796012','H12R8P0',1,'returnedLate','2021-05-11',2.99,6.00,2.99,'BBB333','N91WPX9'),(19,'1234563548796012','H12R8P0',2,'returnedLate','2021-05-09',2.99,3.00,2.99,'BBB222','N39R6T3'),(20,'7983797237239237','H12R8P0',1,'returned','2021-05-06',2.99,0.00,0.00,'AAA222','H12R8P0'),(20,'7983797237239237','H12R8P0',2,'returned','2021-05-09',2.99,0.00,0.00,'BBB444','N39R6T3'),(21,'4197972974928379','N37YA29',1,'returnedLate','2021-05-11',2.99,4.50,2.99,'AAA333','N37YA29'),(21,'4197972974928379','N37YA29',2,'returnedLate','2021-05-10',2.99,3.00,2.99,'AAA999','N39R6T3'),(22,'1234567899283876','N37YA29',1,'returned','2021-05-06',2.99,0.00,0.00,'AAA000','N37YA29'),(23,'1234567891234567','N39R6T3',1,'returned','2021-05-11',2.99,0.00,0.00,'AAA111','N91WPX9'),(24,'1283748594939292','N37YA29',1,'returned','2021-05-09',2.99,0.00,0.00,'AAA000','N39R6T3'),(25,'4230982380352323','H12R8P0',1,'returnedLate','2021-05-11',2.99,3.00,2.99,'AAA222','N91WPX9'),(26,'7498374982379472','H12R8P0',1,'returnedLate','2021-05-11',2.99,3.00,2.99,'BBB555','N91WPX9'),(27,'9479847389274923','N39R6T3',1,'returnedLate','2021-05-11',2.99,1.50,0.00,'AAA888','N37YA29'),(28,'2987984372398471','N39R6T3',1,'returned','2021-05-09',2.99,0.00,0.00,'BBB444','N39R6T3'),(29,'7498374982379472','N39R6T3',1,'returnedLate','2021-05-11',2.99,1.50,2.99,'AAA555','N91WPX9'),(30,'1283748594939292','N39R6T3',1,'returnedLate','2021-05-11',2.99,1.50,2.99,'BBB444','N37YA29'),(31,'7498374982379472','N39R6T3',1,'returned','2021-05-11',2.99,0.00,2.99,'AAA444','N91WPX9'),(31,'7498374982379472','N39R6T3',2,'returned','2021-05-11',2.99,0.00,2.99,'BBB222','N91WPX9'),(32,'1621436578746296','N39R6T3',1,'returned','2021-05-11',2.99,0.00,0.00,'AAA000','N91WPX9'),(33,'1234567906970695','F52XA61',1,'returned','2021-05-11',2.99,0.00,2.99,'BBB111','N37YA29'),(34,'1863871628736182','N39R6T3',1,'returned','2021-05-11',2.99,0.00,2.99,'AAA777','N91WPX9'),(35,'2987984372398471','N91WPX9',1,'returned','2021-05-11',2.99,0.00,0.00,'AAA111','N91WPX9'),(37,'1234598761234765','N91WPX9',1,'returned','2021-05-11',2.99,0.00,2.99,'AAA111','N91WPX9'),(37,'1234598761234765','N91WPX9',2,'returned','2021-05-11',2.99,0.00,2.99,'AAA444','N91WPX9'),(37,'1234598761234765','N91WPX9',3,'returned','2021-05-11',2.99,0.00,2.99,'AAA000','N91WPX9'),(37,'1234598761234765','N91WPX9',4,'returned','2021-05-11',2.99,0.00,2.99,'BBB222','N91WPX9'),(38,'7614821648761248','N91WPX9',1,'returned','2021-05-11',2.99,0.00,0.00,'AAA111','N91WPX9'),(38,'7614821648761248','N91WPX9',2,'returned','2021-05-11',2.99,0.00,2.99,'BBB333','N91WPX9'),(39,'9347654984858538','N91WPX9',1,'returned','2021-05-11',2.99,0.00,0.00,'AAA111','N91WPX9'),(39,'9347654984858538','N91WPX9',2,'returned','2021-05-11',2.99,0.00,0.00,'BBB222','N91WPX9');
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disc`
--

DROP TABLE IF EXISTS `disc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disc` (
  `electronic_tag` varchar(15) NOT NULL,
  `status` varchar(16) NOT NULL,
  `eircode` varchar(8) NOT NULL,
  PRIMARY KEY (`electronic_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disc`
--

LOCK TABLES `disc` WRITE;
/*!40000 ALTER TABLE `disc` DISABLE KEYS */;
INSERT INTO `disc` VALUES ('AAA000','availableToRent','N91WPX9'),('AAA111','availableToRent','N91WPX9'),('AAA122','availableToRent','N91WPX9'),('AAA222','availableToRent','N91WPX9'),('AAA333','availableToRent','N37YA29'),('AAA444','availableToRent','N91WPX9'),('AAA555','availableToRent','N91WPX9'),('AAA666','availableToRent','N37YA29'),('AAA777','availableToRent','N91WPX9'),('AAA888','availableToRent','N37YA29'),('AAA999','availableToRent','N39R6T3'),('BBB111','availableToRent','N37YA29'),('BBB222','availableToRent','N91WPX9'),('BBB333','availableToRent','N91WPX9'),('BBB444','availableToRent','N37YA29'),('BBB555','availableToRent','N91WPX9');
/*!40000 ALTER TABLE `disc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental` (
  `id_rental` int(11) NOT NULL AUTO_INCREMENT,
  `id_customer` varchar(16) NOT NULL,
  `eircode` varchar(8) NOT NULL,
  `start_date` date NOT NULL,
  `return_date` date NOT NULL,
  `total_cost` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id_rental`,`id_customer`,`eircode`),
  KEY `fk_Rental_customer1_idx` (`id_customer`),
  CONSTRAINT `fk_Rental_customer1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (11,'9876543217654321','N39R6T3','2021-05-01','2021-05-03',5.98),(12,'1234567891234567','N39R6T3','2021-05-02','2021-05-03',2.99),(13,'1234598761234765','N39R6T3','2021-05-02','9999-12-31',0.00),(14,'7129876543987654','N39R6T3','2021-05-03','9999-12-31',0.00),(15,'9876543217654321','N37YA29','2021-05-03','2021-05-06',8.97),(16,'4791234987632345','N91WPX9','2021-05-04','2021-05-05',0.00),(18,'9347654984858538','H12R8P0','2021-05-05','9999-12-31',0.00),(19,'1234563548796012','H12R8P0','2021-05-05','2021-05-07',5.98),(20,'7983797237239237','H12R8P0','2021-05-06','9999-12-31',0.00),(21,'4197972974928379','N37YA29','2021-05-06','2021-05-08',5.98),(22,'1234567899283876','N37YA29','2021-05-06','9999-12-31',0.00),(23,'1234567891234567','N39R6T3','2021-05-07','9999-12-31',0.00),(24,'1283748594939292','N37YA29','2021-05-08','2021-05-09',0.00),(25,'4230982380352323','H12R8P0','2021-05-08','2021-05-09',2.99),(26,'7498374982379472','H12R8P0','2021-05-08','2021-05-09',2.99),(27,'9479847389274923','N39R6T3','2021-05-09','2021-05-10',0.00),(28,'2987984372398471','N39R6T3','2021-05-09','9999-12-31',0.00),(29,'7498374982379472','N39R6T3','2021-05-09','2021-05-10',2.99),(30,'1283748594939292','N39R6T3','2021-05-09','2021-05-10',2.99),(31,'7498374982379472','N39R6T3','2021-05-10','2021-05-12',5.98),(32,'1621436578746296','N39R6T3','2021-05-10','2021-05-11',0.00),(33,'1234567906970695','F52XA61','2021-05-10','2021-05-11',2.99),(34,'1863871628736182','N39R6T3','2021-05-11','2021-05-12',2.99),(35,'2987984372398471','N91WPX9','2021-05-11','9999-12-31',0.00),(37,'1234598761234765','N91WPX9','2021-05-11','2021-05-12',11.96),(38,'7614821648761248','N91WPX9','2021-05-11','2021-05-13',2.99),(39,'9347654984858538','N91WPX9','2021-05-11','9999-12-31',0.00);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-11 11:38:33
