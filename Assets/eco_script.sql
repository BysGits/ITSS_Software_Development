-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: ecobike
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike` (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeId` int NOT NULL,
  `barcode` varchar(45) NOT NULL,
  `battery` int NOT NULL,
  `state` tinyint NOT NULL,
  `dockId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `biketype_idx` (`typeId`),
  KEY `dock_idx` (`dockId`),
  CONSTRAINT `biketype` FOREIGN KEY (`typeId`) REFERENCES `biketype` (`id`),
  CONSTRAINT `dock` FOREIGN KEY (`dockId`) REFERENCES `dock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,1,'ST001',100,0,1),(2,1,'ST002',100,0,2),(3,1,'ST003',100,0,3),(4,1,'ST004',100,0,4),(5,1,'ST005',100,0,5),(6,1,'ST006',100,0,6),(7,1,'ST007',100,0,7),(8,1,'ST008',100,0,8),(9,1,'ST009',100,0,9),(10,1,'ST010',100,0,10),(11,1,'ST011',100,0,11),(12,1,'ST012',100,0,12),(13,1,'ST013',100,0,13),(14,1,'ST014',100,0,14),(15,1,'ST015',100,0,15),(16,1,'ST016',100,0,16),(17,1,'ST017',100,0,17),(18,1,'ST018',100,0,18),(19,1,'ST019',100,0,19),(20,1,'ST020',100,0,20),(21,1,'ST021',100,0,1),(22,1,'ST022',100,0,2),(23,1,'ST023',100,0,3),(24,1,'ST024',100,0,4),(25,1,'ST025',100,0,5),(26,2,'SE001',100,0,1),(27,2,'SE002',100,0,2),(28,2,'SE003',100,0,3),(29,2,'SE004',100,0,4),(30,2,'SE005',100,0,5),(31,2,'SE006',100,0,6),(32,2,'SE007',100,0,7),(33,2,'SE008',100,0,8),(34,2,'SE009',100,0,9),(35,2,'SE010',100,0,10),(36,2,'SE011',100,0,11),(37,2,'SE012',100,0,12),(38,2,'SE013',100,0,13),(39,2,'SE014',100,0,14),(40,2,'SE015',100,0,15),(41,2,'SE016',100,0,16),(42,2,'SE017',100,0,17),(43,2,'SE018',100,0,18),(44,2,'SE019',100,0,19),(45,2,'SE020',100,0,20),(46,2,'SE021',100,0,6),(47,2,'SE022',100,0,7),(48,2,'SE023',100,0,8),(49,2,'SE024',100,0,9),(50,2,'SE025',100,0,10),(51,3,'TB001',100,0,1),(52,3,'TB002',100,0,2),(53,3,'TB003',100,0,3),(54,3,'TB004',100,0,4),(55,3,'TB005',100,0,5),(56,3,'TB006',100,0,6),(57,3,'TB007',100,0,7),(58,3,'TB008',100,0,8),(59,3,'TB009',100,0,9),(60,3,'TB010',100,0,10),(61,3,'TB011',100,0,11),(62,3,'TB012',100,0,12),(63,3,'TB013',100,0,13),(64,3,'TB014',100,0,14),(65,3,'TB015',100,0,15),(66,3,'TB016',100,0,16),(67,3,'TB017',100,0,17),(68,3,'TB018',100,0,18),(69,3,'TB019',100,0,19),(70,3,'TB020',100,0,20),(71,3,'TB021',100,0,11),(72,3,'TB022',100,0,12),(73,3,'TB023',100,0,13),(74,3,'TB024',100,0,14),(75,3,'TB025',100,0,15),(76,4,'TE001',100,0,1),(77,4,'TE002',100,0,2),(78,4,'TE003',100,0,3),(79,4,'TE004',100,0,4),(80,4,'TE005',100,0,5),(81,4,'TE006',100,0,6),(82,4,'TE007',100,0,7),(83,4,'TE008',100,0,8),(84,4,'TE009',100,0,9),(85,4,'TE010',100,0,10),(86,4,'TE011',100,0,11),(87,4,'TE012',100,0,12),(88,4,'TE013',100,0,13),(89,4,'TE014',100,0,14),(90,4,'TE015',100,0,15),(91,4,'TE016',100,0,16),(92,4,'TE017',100,0,17),(93,4,'TE018',100,0,18),(94,4,'TE019',100,0,19),(95,4,'TE020',100,0,20),(96,4,'TE021',100,0,16),(97,4,'TE022',100,0,17),(98,4,'TE023',100,0,18),(99,4,'TE024',100,0,19),(100,4,'TE025',100,0,20);
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biketype`
--

DROP TABLE IF EXISTS `biketype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biketype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pedals` int NOT NULL,
  `saddles` int NOT NULL,
  `rearSeats` int NOT NULL,
  `rentingFee` int NOT NULL,
  `depositFee` int NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biketype`
--

LOCK TABLES `biketype` WRITE;
/*!40000 ALTER TABLE `biketype` DISABLE KEYS */;
INSERT INTO `biketype` VALUES (1,1,1,1,200000,400000,'Standard Bike'),(2,1,1,1,200000,700000,'Standard E-Bike'),(3,2,2,1,200000,550000,'Twin Bike'),(4,2,2,1,200000,850000,'Twin E-Bike');
/*!40000 ALTER TABLE `biketype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cardCode` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `cvvCode` int NOT NULL,
  `dateExpired` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'121319_group2_2020','Group 2',228,'1125');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dock`
--

DROP TABLE IF EXISTS `dock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `availableBikes` int NOT NULL,
  `emptySlots` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dock`
--

LOCK TABLES `dock` WRITE;
/*!40000 ALTER TABLE `dock` DISABLE KEYS */;
INSERT INTO `dock` VALUES (1,'D01','69 Vu Trong Phung',5,5),(2,'D02','420 Khuong Dinh',5,5),(3,'D03','135 Ngoc Ha',5,5),(4,'D04','41 Dong Tac',5,5),(5,'D05','204 Nguyen Trai',5,5),(6,'D06','1 Dai Co Viet',5,5),(7,'D07','100 Giai Phong',5,5),(8,'D08','30 Kim Ma',5,5),(9,'D09','96 Nguyen Khanh Toan',5,5),(10,'D10','6 Nguyen Du',5,5),(11,'D11','1 Quoc Tu Giam',5,5),(12,'D12','2 Hai Ba Trung',5,5),(13,'D13','241 Xuan Thuy',5,5),(14,'D14','6 Quang Trung',5,5),(15,'D15','200 Tran Dai Nghia',5,5),(16,'D16','5 Trang Tien',5,5),(17,'D17','18 Kham Thien',5,5),(18,'D18','520 De La Thanh',5,5),(19,'D19','73 Au Co',5,5),(20,'D20','89 Hoang Hoa Tham',5,5),(99,'D99','N/A',0,0);
/*!40000 ALTER TABLE `dock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bikeId` int NOT NULL,
  `totalAmount` int NOT NULL,
  `startDockid` int DEFAULT NULL,
  `endDockId` int DEFAULT NULL,
  `totalTime` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bike1_idx` (`bikeId`),
  CONSTRAINT `bike1` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymenttransaction`
--

DROP TABLE IF EXISTS `paymenttransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymenttransaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cardId` int NOT NULL,
  `invoiceId` int NOT NULL,
  `command` varchar(45) NOT NULL,
  `createdAt` datetime NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_idx` (`cardId`),
  KEY `invoice_idx` (`invoiceId`),
  CONSTRAINT `card` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`),
  CONSTRAINT `invoice` FOREIGN KEY (`invoiceId`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymenttransaction`
--

LOCK TABLES `paymenttransaction` WRITE;
/*!40000 ALTER TABLE `paymenttransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymenttransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bikeId` int NOT NULL,
  `rentTime` int NOT NULL,
  `currentFee` int NOT NULL,
  `type` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-21 19:31:50
