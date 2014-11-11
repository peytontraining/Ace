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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Machine`
--

LOCK TABLES `Machine` WRITE;
/*!40000 ALTER TABLE `Machine` DISABLE KEYS */;
INSERT INTO `Machine` VALUES (1,3,'Camera Test'),(15,3,'Mobile Phone'),(60,3,'UNKNOWN'),(61,3,'Camera 1'),(62,3,'Camera 1'),(63,3,'Camera 2');
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
INSERT INTO `deviceTemplates` VALUES (1,2,'Axis - Non-PTZ Model','2013-08-12 15:38:00','Axis','Non-PTZ Model','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Function'),(2,2,'Axis - Non-PTZ Model','2013-08-12 15:38:00','Axis','Non-PTZ Models','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Functions'),(3,2,'Axis - PTZ Models (Device)','2013-09-12 15:38:00','Axis','Non-PTZ Models','2.0.1','Camera*','Model:215PTZ, 212PTZ Ipad App does not support PTZ Functions');
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
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,1,'slare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0','Climate','2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(2,1,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(3,1,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0','Climate','2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(267,120,'slare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0',NULL,'2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(268,120,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(269,120,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(270,123,'Fireplace - On/Off Switch','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Function','2014-09-26 17:00:00'),(271,123,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-27 17:00:00'),(272,123,'slare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0',NULL,'2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-28 17:00:00'),(273,128,'slare Vision 8 Channel NVR (device)','CCTV','DVR/NVR','Clare Controls','',NULL,'\0',NULL,'2.0.13',NULL,'TW Series','Humicity Functions with Z-Wave Thermostats','2014-09-25 17:00:00'),(274,128,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00'),(275,128,'Fireplace - On/Off Switch (device)','Fireplace','Fireplace Controller','Generic','',NULL,'\0',NULL,'2.0.25',NULL,'PTZ Model','Model: 215PTZ, 212PTZ iPad app does not : support PTZ Functions','2014-09-25 17:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devicesTableDetail`
--

LOCK TABLES `devicesTableDetail` WRITE;
/*!40000 ALTER TABLE `devicesTableDetail` DISABLE KEYS */;
INSERT INTO `devicesTableDetail` VALUES (1,1,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(2,1,'adress','127.0.0.1','\0','Adress of the camera'),(3,1,'camera.type','ip','\0','Type of camera. Maybe IP or USB'),(4,1,'user','anonymous','\0','User of the camera'),(5,1,'PassWord','anonymous','\0','User password of the camera'),(6,10,'PassWord','anonymous','\0','User password of the camera'),(7,10,'adress','127.0.0.1','\0','Adress of the camera'),(8,10,'driver','Axis(VAPX V2)','','Represent the driver info needed to create a camera.'),(9,10,'user','anonymous','\0','User of the camera');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'1 Touch Solutions LKN Inc'),(2,'3105 GMD'),(3,'Acoustic Interiors'),(4,'Adapt AV Solutions'),(5,'Advanced Light & Sound'),(6,'AECustom'),(7,'Affinity AV'),(8,'Aim High Audio'),(9,'ALR'),(10,'ANM Loewen Construction'),(11,'Arthur Retenburg Homes');
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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versions`
--

LOCK TABLES `versions` WRITE;
/*!40000 ALTER TABLE `versions` DISABLE KEYS */;
INSERT INTO `versions` VALUES (1,1,'1.0.13','2014-11-10 06:11:41',' ','2014-11-10 06:11:41','2.x'),(2,45,'1.0.11','2014-11-10 03:36:34',' ','2014-11-10 03:36:34','2.x'),(4,60,'1.0.1','2014-11-10 04:36:52','','2014-11-10 04:36:52','2.x'),(116,65,'1.0.1','2014-11-10 06:39:17','','2014-11-10 06:39:17','2.x'),(123,1,'1.0.14','2014-11-10 08:28:09','','2014-11-10 08:28:09','2.x'),(124,61,'1.0.0 *','2014-11-11 02:05:56','','2014-11-11 02:05:56','2.x'),(125,62,'1.0.12','2014-11-11 02:06:12','','2014-11-11 02:06:12','2.x'),(126,63,'1.0.1','2014-11-11 02:09:12','','2014-11-11 02:09:12','2.x'),(127,1,'1.0.15','2014-11-11 02:09:44','','2014-11-11 02:09:44','2.x'),(128,1,'1.0.16','2014-11-11 02:10:01','','2014-11-11 02:10:01','2.x');
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

-- Dump completed on 2014-11-11  9:12:09
