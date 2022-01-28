-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: i_do_weddings
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `wedding`
--

DROP TABLE IF EXISTS `wedding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wedding` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hall_id` int NOT NULL,
  `car` int DEFAULT NULL,
  `main_dish` int DEFAULT NULL,
  `date` date NOT NULL,
  `dj_id` int DEFAULT NULL,
  `num_guests` int NOT NULL,
  `invitation` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `person_dj_idx` (`dj_id`),
  KEY `car_wedding_idx` (`car`),
  KEY `main_dish_wedding_idx` (`main_dish`),
  KEY `invitation_wedding_idx` (`invitation`),
  KEY `wedding_hall_idx` (`hall_id`),
  CONSTRAINT `car_wedding` FOREIGN KEY (`car`) REFERENCES `decorated_cars` (`id`),
  CONSTRAINT `invitation_wedding` FOREIGN KEY (`invitation`) REFERENCES `invitation_send_type` (`id`),
  CONSTRAINT `main_dish_wedding` FOREIGN KEY (`main_dish`) REFERENCES `decorated_dishes` (`id`),
  CONSTRAINT `person_dj` FOREIGN KEY (`dj_id`) REFERENCES `dj` (`id`),
  CONSTRAINT `wedding_hall` FOREIGN KEY (`hall_id`) REFERENCES `wedding_hall` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wedding`
--

LOCK TABLES `wedding` WRITE;
/*!40000 ALTER TABLE `wedding` DISABLE KEYS */;
INSERT INTO `wedding` VALUES (1,1,1,1,'2022-07-04',63,26,3),(2,2,2,2,'2022-02-02',68,78,2),(3,5,3,3,'2022-02-04',63,999,2),(4,4,4,4,'2022-02-05',68,88,2),(5,5,5,5,'2022-02-05',66,875,NULL),(6,1,6,6,'2022-04-01',64,300,1),(7,1,7,7,'2022-02-04',68,56,1),(8,1,8,NULL,'2022-01-31',65,50,3),(9,1,NULL,NULL,'2022-04-27',NULL,300,NULL),(10,1,10,10,'2022-03-11',64,54,1);
/*!40000 ALTER TABLE `wedding` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 17:52:44
