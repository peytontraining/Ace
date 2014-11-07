CREATE DATABASE  IF NOT EXISTS `Demo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Demo`;
-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: Demo
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Machine`
--

DROP TABLE IF EXISTS `Machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idType` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Machine`
--

LOCK TABLES `Machine` WRITE;
/*!40000 ALTER TABLE `Machine` DISABLE KEYS */;
INSERT INTO `Machine` VALUES (1,3,'Camera Test'),(15,3,'Mobile Phone'),(16,12,'Mobile Phone'),(17,12,'Camera Test');
/*!40000 ALTER TABLE `Machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deviceTemplates`
--

DROP TABLE IF EXISTS `deviceTemplates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deviceTemplates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTypeDevice` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `lastModifield` timestamp NULL DEFAULT NULL,
  `manufacturer` varchar(45) DEFAULT NULL,
  `modelNumber` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `deviceDriver` varchar(45) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deviceTemplates`
--

LOCK TABLES `deviceTemplates` WRITE;
/*!40000 ALTER TABLE `deviceTemplates` DISABLE KEYS */;
INSERT INTO `deviceTemplates` VALUES (1,2,'Axis - Non-PTZ Models','2013-08-12 15:38:00','Axis','Non-PTZ Models','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Functions'),(2,2,'Axis - Non-PTZ Models','2013-08-12 15:38:00','Axis','Non-PTZ Models','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Functions'),(3,2,'Axis - PTZ Models (Device)','2013-09-12 15:38:00','Axis','Non-PTZ Models','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Functions');
/*!40000 ALTER TABLE `deviceTemplates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idVersion` int(11) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `appModule` varchar(500) DEFAULT NULL,
  `deviceType` varchar(500) DEFAULT NULL,
  `manufacture` varchar(500) DEFAULT NULL,
  `physicalLocation` varchar(500) DEFAULT NULL,
  `rooms` varchar(500) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `controlType` varchar(45) DEFAULT NULL,
  `versionContent` varchar(45) DEFAULT NULL,
  `icon` longblob,
  `modelNumber` varchar(45) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `lastModifield` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (11,5,'slare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0','Climate','2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(14,5,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(17,5,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(18,6,'Clare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0','Climate','2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(19,5,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.14',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(20,5,'Clare Controls - PTZ Models (IP) (device)','CCTV','IP Cameras','Clare Controls','',NULL,'\0','Climate','1.0.12',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(186,58,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.14',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(187,58,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(188,58,'Clare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0','Climate','2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(189,58,'Clare Controls - PTZ Models (IP) (device)','CCTV','IP Cameras','Clare Controls','',NULL,'\0','Climate','1.0.12',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(190,58,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devicesTableDetail`
--

DROP TABLE IF EXISTS `devicesTableDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devicesTableDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idDevices` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `mandatory` bit(1) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devicesTableDetail`
--

LOCK TABLES `devicesTableDetail` WRITE;
/*!40000 ALTER TABLE `devicesTableDetail` DISABLE KEYS */;
INSERT INTO `devicesTableDetail` VALUES (1,1,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(2,1,'adress','127.0.0.1','\0','Adress of the camera'),(3,1,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(4,1,'user','anonymous','\0','User of the camera'),(5,1,'PassWord','anonymous','\0','User password of the camera'),(6,10,'PassWord','anonymous','\0','User password of the camera'),(7,10,'adress','127.0.0.1','\0','Adress of the camera'),(8,10,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(9,10,'user','anonymous','\0','User of the camera'),(10,10,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(11,12,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(12,20,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(13,12,'PassWord','anonymous','\0','User password of the camera'),(14,12,'adress','127.0.0.1','\0','Adress of the camera'),(15,20,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(16,20,'user','anonymous','\0','User of the camera'),(17,20,'PassWord','anonymous','\0','User password of the camera'),(18,12,'user','anonymous','\0','User of the camera'),(19,20,'adress','127.0.0.1','\0','Adress of the camera'),(20,12,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(21,21,'PassWord','anonymous','\0','User password of the camera'),(22,27,'PassWord','anonymous','\0','User password of the camera'),(23,21,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(24,27,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(25,27,'adress','127.0.0.1','\0','Adress of the camera'),(26,21,'adress','127.0.0.1','\0','Adress of the camera'),(27,27,'user','anonymous','\0','User of the camera'),(28,21,'user','anonymous','\0','User of the camera'),(29,27,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(30,21,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(31,47,'PassWord','anonymous','\0','User password of the camera'),(32,43,'user','anonymous','\0','User of the camera'),(33,43,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(34,40,'PassWord','anonymous','\0','User password of the camera'),(35,48,'adress','127.0.0.1','\0','Adress of the camera'),(36,47,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(37,47,'user','anonymous','\0','User of the camera'),(38,43,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(39,40,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(40,43,'PassWord','anonymous','\0','User password of the camera'),(41,48,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(42,47,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(43,47,'adress','127.0.0.1','\0','Adress of the camera'),(44,40,'user','anonymous','\0','User of the camera'),(45,40,'adress','127.0.0.1','\0','Adress of the camera'),(46,48,'PassWord','anonymous','\0','User password of the camera'),(47,48,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(48,40,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(49,48,'user','anonymous','\0','User of the camera'),(50,43,'adress','127.0.0.1','\0','Adress of the camera'),(51,59,'user','anonymous','\0','User of the camera'),(52,59,'adress','127.0.0.1','\0','Adress of the camera'),(53,59,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(54,54,'adress','127.0.0.1','\0','Adress of the camera'),(55,54,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(56,59,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(57,54,'user','anonymous','\0','User of the camera'),(58,54,'PassWord','anonymous','\0','User password of the camera'),(59,59,'PassWord','anonymous','\0','User password of the camera'),(60,54,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(61,67,'user','anonymous','\0','User of the camera'),(62,67,'adress','127.0.0.1','\0','Adress of the camera'),(63,61,'user','anonymous','\0','User of the camera'),(64,61,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(65,61,'adress','127.0.0.1','\0','Adress of the camera'),(66,67,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(67,61,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(68,67,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(69,61,'PassWord','anonymous','\0','User password of the camera'),(70,67,'PassWord','anonymous','\0','User password of the camera'),(71,77,'PassWord','anonymous','\0','User password of the camera'),(72,80,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(73,80,'user','anonymous','\0','User of the camera'),(74,77,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(75,77,'adress','127.0.0.1','\0','Adress of the camera'),(76,77,'user','anonymous','\0','User of the camera'),(77,80,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(78,80,'adress','127.0.0.1','\0','Adress of the camera'),(79,80,'PassWord','anonymous','\0','User password of the camera'),(80,77,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(81,81,'adress','127.0.0.1','\0','Adress of the camera'),(82,90,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(83,90,'user','anonymous','\0','User of the camera'),(84,90,'adress','127.0.0.1','\0','Adress of the camera'),(85,90,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(86,81,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(87,81,'PassWord','anonymous','\0','User password of the camera'),(88,81,'user','anonymous','\0','User of the camera'),(89,90,'PassWord','anonymous','\0','User password of the camera'),(90,81,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(91,98,'PassWord','anonymous','\0','User password of the camera'),(92,98,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(93,94,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(94,94,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(95,94,'user','anonymous','\0','User of the camera'),(96,98,'adress','127.0.0.1','\0','Adress of the camera'),(97,98,'user','anonymous','\0','User of the camera'),(98,98,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(99,94,'adress','127.0.0.1','\0','Adress of the camera'),(100,94,'PassWord','anonymous','\0','User password of the camera'),(101,107,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(102,103,'user','anonymous','\0','User of the camera'),(103,107,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(104,107,'PassWord','anonymous','\0','User password of the camera'),(105,103,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(106,103,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(107,107,'user','anonymous','\0','User of the camera'),(108,107,'adress','127.0.0.1','\0','Adress of the camera'),(109,103,'PassWord','anonymous','\0','User password of the camera'),(110,103,'adress','127.0.0.1','\0','Adress of the camera'),(111,119,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(112,119,'user','anonymous','\0','User of the camera'),(113,114,'PassWord','anonymous','\0','User password of the camera'),(114,115,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(115,119,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(116,119,'PassWord','anonymous','\0','User password of the camera'),(117,114,'adress','127.0.0.1','\0','Adress of the camera'),(118,115,'PassWord','anonymous','\0','User password of the camera'),(119,115,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(120,114,'user','anonymous','\0','User of the camera'),(121,115,'adress','127.0.0.1','\0','Adress of the camera'),(122,114,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(123,114,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(124,115,'user','anonymous','\0','User of the camera'),(125,119,'adress','127.0.0.1','\0','Adress of the camera'),(126,134,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(127,133,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(128,134,'user','anonymous','\0','User of the camera'),(129,134,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(130,133,'adress','127.0.0.1','\0','Adress of the camera'),(131,133,'user','anonymous','\0','User of the camera'),(132,133,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(133,133,'PassWord','anonymous','\0','User password of the camera'),(134,134,'PassWord','anonymous','\0','User password of the camera'),(135,134,'adress','127.0.0.1','\0','Adress of the camera'),(136,136,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(137,136,'PassWord','anonymous','\0','User password of the camera'),(138,137,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(139,137,'user','anonymous','\0','User of the camera'),(140,136,'adress','127.0.0.1','\0','Adress of the camera'),(141,136,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(142,137,'adress','127.0.0.1','\0','Adress of the camera'),(143,137,'PassWord','anonymous','\0','User password of the camera'),(144,137,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(145,136,'user','anonymous','\0','User of the camera'),(146,153,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(147,153,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(148,152,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(149,152,'PassWord','anonymous','\0','User password of the camera'),(150,153,'user','anonymous','\0','User of the camera'),(151,153,'adress','127.0.0.1','\0','Adress of the camera'),(152,152,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(153,152,'adress','127.0.0.1','\0','Adress of the camera'),(154,153,'PassWord','anonymous','\0','User password of the camera'),(155,152,'user','anonymous','\0','User of the camera'),(156,161,'PassWord','anonymous','\0','User password of the camera'),(157,157,'PassWord','anonymous','\0','User password of the camera'),(158,157,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(159,161,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(160,161,'user','anonymous','\0','User of the camera'),(161,157,'user','anonymous','\0','User of the camera'),(162,157,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(163,161,'adress','127.0.0.1','\0','Adress of the camera'),(164,157,'adress','127.0.0.1','\0','Adress of the camera'),(165,161,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(166,171,'adress','127.0.0.1','\0','Adress of the camera'),(167,167,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(168,167,'adress','127.0.0.1','\0','Adress of the camera'),(169,171,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(170,167,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(171,167,'PassWord','anonymous','\0','User password of the camera'),(172,171,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(173,171,'user','anonymous','\0','User of the camera'),(174,171,'PassWord','anonymous','\0','User password of the camera'),(175,167,'user','anonymous','\0','User of the camera'),(176,179,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(177,179,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(178,180,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(179,179,'user','anonymous','\0','User of the camera'),(180,180,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(181,179,'adress','127.0.0.1','\0','Adress of the camera'),(182,179,'PassWord','anonymous','\0','User password of the camera'),(183,180,'PassWord','anonymous','\0','User password of the camera'),(184,180,'user','anonymous','\0','User of the camera'),(185,180,'adress','127.0.0.1','\0','Adress of the camera'),(186,189,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(187,189,'user','anonymous','\0','User of the camera'),(188,189,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(189,189,'adress','127.0.0.1','\0','Adress of the camera'),(190,189,'PassWord','anonymous','\0','User password of the camera');
/*!40000 ALTER TABLE `devicesTableDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treeColumnParent`
--

DROP TABLE IF EXISTS `treeColumnParent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treeColumnParent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treeColumnParent`
--

LOCK TABLES `treeColumnParent` WRITE;
/*!40000 ALTER TABLE `treeColumnParent` DISABLE KEYS */;
INSERT INTO `treeColumnParent` VALUES (1,'CCTV');
/*!40000 ALTER TABLE `treeColumnParent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeDeviceTemplate`
--

DROP TABLE IF EXISTS `typeDeviceTemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeDeviceTemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTreeColumnParent` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeDeviceTemplate`
--

LOCK TABLES `typeDeviceTemplate` WRITE;
/*!40000 ALTER TABLE `typeDeviceTemplate` DISABLE KEYS */;
INSERT INTO `typeDeviceTemplate` VALUES (1,1,'DVR/NVR'),(2,1,'IP Cameras');
/*!40000 ALTER TABLE `typeDeviceTemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'1 Touch Solutions LKN Inc'),(2,'3105 GMD'),(3,'Acoustic Interiors'),(4,'Adapt AV Solutions'),(5,'Advanced Light & Sound'),(6,'AECustom'),(7,'Affinity AV'),(8,'Aim High Audio'),(9,'ALR'),(10,'ANM Loewen Construction'),(11,'Arthur Retenburg Homes'),(12,'Acoustic Interiors');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versions`
--

DROP TABLE IF EXISTS `versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMachine` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `deployTime` timestamp NULL DEFAULT NULL,
  `deploySource` varchar(45) DEFAULT NULL,
  `saveTime` timestamp NULL DEFAULT NULL,
  `targetVersion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versions`
--

LOCK TABLES `versions` WRITE;
/*!40000 ALTER TABLE `versions` DISABLE KEYS */;
INSERT INTO `versions` VALUES (5,1,'1.0.26','2014-11-06 07:41:53',' ','2014-11-06 07:41:53','2.x'),(57,1,'1.0.24','2014-11-07 06:43:41',' ','2014-11-07 06:43:41','2.x'),(58,17,'1.0.29','2014-11-07 07:24:38',' ','2014-11-07 07:24:38','2.x');
/*!40000 ALTER TABLE `versions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-07 16:11:28
