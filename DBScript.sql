-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecobike
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `owner` varchar(45) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `isAdmin` tinyint NOT NULL,
  `cardCode` varchar(45) NOT NULL,
  `isUsing` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','120914','admin',NULL,NULL,1,'admin',0),(2,'group2','1','Group 2',NULL,NULL,0,'121319_group2_2020',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike` (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeId` int NOT NULL,
  `barcode` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `battery` int NOT NULL,
  `state` tinyint NOT NULL,
  `dockId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `biketype_idx` (`typeId`),
  KEY `dock_idx` (`dockId`),
  CONSTRAINT `biketype` FOREIGN KEY (`typeId`) REFERENCES `biketype` (`id`),
  CONSTRAINT `dock` FOREIGN KEY (`dockId`) REFERENCES `dock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,1,'ST001',0,1,1),(2,1,'ST002',0,1,2),(3,1,'ST003',0,1,3),(4,1,'ST004',0,1,4),(5,1,'ST005',0,1,5),(6,1,'ST006',0,1,6),(7,1,'ST007',0,1,7),(8,1,'ST008',0,1,8),(9,1,'ST009',0,1,9),(10,1,'ST010',0,1,10),(11,1,'ST011',0,1,11),(12,1,'ST012',0,1,12),(13,1,'ST013',0,1,13),(14,1,'ST014',0,1,14),(15,1,'ST015',0,1,15),(16,1,'ST016',0,1,16),(17,1,'ST017',0,1,17),(18,1,'ST018',0,1,18),(19,1,'ST019',0,1,19),(20,1,'ST020',0,1,20),(21,1,'ST021',0,1,1),(22,1,'ST022',0,1,2),(23,1,'ST023',0,1,3),(24,1,'ST024',0,1,4),(25,1,'ST025',0,1,5),(26,2,'SE001',100,1,2),(27,2,'SE002',100,1,2),(28,2,'SE003',100,1,3),(29,2,'SE004',100,1,4),(30,2,'SE005',100,1,5),(31,2,'SE006',100,1,6),(32,2,'SE007',100,1,7),(33,2,'SE008',100,1,8),(34,2,'SE009',100,1,9),(35,2,'SE010',100,1,10),(36,2,'SE011',100,1,11),(37,2,'SE012',100,1,12),(38,2,'SE013',100,1,13),(39,2,'SE014',100,1,14),(40,2,'SE015',100,1,15),(41,2,'SE016',100,1,16),(42,2,'SE017',100,1,17),(43,2,'SE018',100,1,18),(44,2,'SE019',100,1,19),(45,2,'SE020',100,1,20),(46,2,'SE021',100,1,6),(47,2,'SE022',100,1,7),(48,2,'SE023',100,1,8),(49,2,'SE024',100,1,9),(50,2,'SE025',100,1,10),(51,3,'TB001',0,1,1),(52,3,'TB002',0,1,2),(53,3,'TB003',0,1,3),(54,3,'TB004',0,1,4),(55,3,'TB005',0,1,5),(56,3,'TB006',0,1,6),(57,3,'TB007',0,1,7),(58,3,'TB008',0,1,8),(59,3,'TB009',0,1,9),(60,3,'TB010',0,1,10),(61,3,'TB011',0,1,11),(62,3,'TB012',0,1,12),(63,3,'TB013',0,1,13),(64,3,'TB014',0,1,14),(65,3,'TB015',0,1,15),(66,3,'TB016',0,1,16),(67,3,'TB017',0,1,17),(68,3,'TB018',0,1,18),(69,3,'TB019',0,1,19),(70,3,'TB020',0,1,20),(71,3,'TB021',0,1,11),(72,3,'TB022',0,1,12),(73,3,'TB023',0,1,13),(74,3,'TB024',0,1,14),(75,3,'TB025',0,1,15),(76,4,'TE001',100,1,1),(77,4,'TE002',100,1,2),(78,4,'TE003',100,1,3),(79,4,'TE004',100,1,4),(80,4,'TE005',100,1,5),(81,4,'TE006',100,1,6),(82,4,'TE007',100,1,7),(83,4,'TE008',100,1,8),(84,4,'TE009',100,1,9),(85,4,'TE010',100,1,10),(86,4,'TE011',100,1,11),(87,4,'TE012',100,1,12),(88,4,'TE013',100,1,13),(89,4,'TE014',100,1,14),(90,4,'TE015',100,1,15),(91,4,'TE016',100,1,16),(92,4,'TE017',100,1,17),(93,4,'TE018',100,1,18),(94,4,'TE019',100,1,19),(95,4,'TE020',100,1,20),(96,4,'TE021',100,1,16),(97,4,'TE022',100,1,17),(98,4,'TE023',100,1,18),(99,4,'TE024',100,1,19),(100,4,'TE025',100,1,20);
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
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
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
-- Table structure for table `dock`
--

DROP TABLE IF EXISTS `dock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `availableBikes` int NOT NULL,
  `emptySlots` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dock`
--

LOCK TABLES `dock` WRITE;
/*!40000 ALTER TABLE `dock` DISABLE KEYS */;
INSERT INTO `dock` VALUES (1,'Unknown','Unknown',0,0),(2,'Pubgame Center','272 Khuong Dinh, Thanh Xuan',5,5),(3,'B52 Victory Museum','157 Doi Can, Ba Dinh',5,5),(4,'Lac Viet Audio','41 Dong Tac, Dong Da',5,5),(5,'Thuong Dinh Soccer Field','129 Nguyen Trai, Thanh Xuan',5,5),(6,'Hanoi University of Science & Technology','1 Dai Co Viet, Hai Ba Trung',5,5),(7,'Dai Thang Garage','243 Giai Phong, Dong Da',5,5),(8,'Kim Ma Bus station','241 Kim Ma, Hoan Kiem',5,5),(9,'Cau Giay People\'s Committee','36 Cau Giay, Cau Giay',5,5),(10,'Circus Central','69 Tran Nhan Tong, Hai Ba Trung',5,5),(11,'Temple of Literature','58 Quoc Tu Giam, Dong Da',5,5),(12,'Hanoi Department of Finance','38 Hai Ba Trung, Hoan Kiem',5,5),(13,'Hanoi Post office','75 Dinh Tien Hoang, Hoan Kiem',5,5),(14,'Ha Dong Post office','4 Quang Trung, Ha Dong',5,5),(15,'NEU Culture House','100 Tran Dai Nghia, Hai Ba Trung',5,5),(16,'Hanoi Opera House','1 Trang Tien, Hoan Kiem',5,5),(17,'Hanoi locomotive factory','2 Kham Thien, Dong Da',5,5),(18,'Giang Vo Church','766 De La Thanh, Ba Dinh',5,5),(19,'Tay Ho Viettel Post office','317 Au Co, Tay Ho',5,5),(20,'Hanoi Beer Factory','183 Hoang Hoa Tham, Ba Dinh',5,5),(21,'Center Post office','51 Vu Trong Phung, Thanh Xuan',5,5);
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
  `totalAmount` double NOT NULL,
  `startDockid` int DEFAULT NULL,
  `endDockId` int DEFAULT NULL,
  `totalTime` int DEFAULT NULL,
  `cardCode` varchar(45) COLLATE utf8_bin NOT NULL,
  `transactionId` varchar(45) COLLATE utf8_bin NOT NULL,
  `owner` varchar(45) COLLATE utf8_bin NOT NULL,
  `createdAt` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `accountId` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `bike1_idx` (`bikeId`),
  KEY `card1_idx` (`cardCode`),
  KEY `account_idx` (`accountId`),
  KEY `start_idx` (`startDockid`),
  KEY `end_idx` (`endDockId`),
  CONSTRAINT `account` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`),
  CONSTRAINT `bike1` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`id`),
  CONSTRAINT `end` FOREIGN KEY (`endDockId`) REFERENCES `dock` (`id`),
  CONSTRAINT `start` FOREIGN KEY (`startDockid`) REFERENCES `dock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-01 22:00:53
