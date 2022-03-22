-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: mykanban
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
-- Table structure for table `kbuser`
--

DROP TABLE IF EXISTS `kbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kbuser` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f9g6syhguj2et35gf2e6f4dm9` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kbuser`
--

LOCK TABLES `kbuser` WRITE;
/*!40000 ALTER TABLE `kbuser` DISABLE KEYS */;
INSERT INTO `kbuser` VALUES (1,'2021-11-05 23:13:14.921000','Bruce Wayne','$2a$10$bdgmTqyXRI.me/pOQHMvXOSsq6yZ7jEOBEqjin6e32pyyLuyC.lsa','2021-11-05 23:13:14.921000','batman@test.com'),(2,'2021-11-05 23:16:39.043000','Tony Stark','$2a$10$7zbQuPQePn9fLcVRMGd.QOZOPw/ZEndH/1Y1G/CFCNGlrUod.HdVy','2021-11-05 23:16:39.043000','tstark@test.com'),(3,'2021-11-06 15:18:59.770000','Carol Danvers','$2a$10$LOJ0DJee4vzSJ7Z143uiaOztGX.BbLzbOP./20nHWkDYAmrmwBiFy','2021-11-06 15:18:59.770000','cdanvers@test.com'),(5,'2021-11-07 14:02:43.545000','test','$2a$10$JsF4YO2sAjnbwoRdgkn2M.4m8p9dpdpF4pItE7IQWe.IG9oedLase','2021-11-07 14:02:43.545000','test@test.com'),(6,'2021-11-10 23:53:13.164000','tk','$2a$10$.XkGbLLT64nSZrG4W7/uKuy8266Q2Lt19Dqk.JvGOm078yPlWgILa','2021-11-10 23:53:13.164000','tk@test.com');
/*!40000 ALTER TABLE `kbuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kbuser_project`
--

DROP TABLE IF EXISTS `kbuser_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kbuser_project` (
  `user_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `FKegryouk06fjeuk3jqg4ku10m9` (`project_id`),
  CONSTRAINT `FK3oent8xe7s99p1q8e19sb4bg5` FOREIGN KEY (`user_id`) REFERENCES `kbuser` (`id`),
  CONSTRAINT `FKegryouk06fjeuk3jqg4ku10m9` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kbuser_project`
--

LOCK TABLES `kbuser_project` WRITE;
/*!40000 ALTER TABLE `kbuser_project` DISABLE KEYS */;
INSERT INTO `kbuser_project` VALUES (2,6),(2,7),(2,8),(5,9),(5,10),(5,12),(5,13),(6,14),(6,15),(5,23),(5,24),(6,25);
/*!40000 ALTER TABLE `kbuser_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `project_identifier` varchar(255) DEFAULT NULL,
  `target_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `ticket_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nh7jg4qyw1dm5y0bn2vwoq6v2` (`project_identifier`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (6,'2021-11-06 15:24:25.233000','Development of the Iron Man mark II suit','MKII',NULL,'Mark II suit','2021-11-06 16:35:40.943000',2),(7,'2021-11-07 14:26:11.754000','Development of the Iron Man mk.III suit','MKIII',NULL,'Mark III suit','2021-11-07 14:26:11.754000',0),(8,'2021-11-07 14:54:11.298000','Develop a suit that can be stowed inside a briefcase','BCASE',NULL,'Briefcase Suit','2021-11-07 14:54:11.298000',0),(9,'2021-11-07 14:57:30.511000','see if adding new project works','TEST1',NULL,'test','2021-11-07 14:57:30.511000',0),(10,'2021-11-07 15:10:05.504000','another test','TEST2',NULL,'another test','2021-11-07 15:10:05.504000',0),(12,'2021-11-07 15:18:50.090000','test','TEST3',NULL,'test 3','2021-11-07 15:18:50.090000',0),(13,'2021-11-07 23:07:56.887000','Restore a Supermarine Spitfire aircraft','SFIRE',NULL,'Spitfire restoration','2021-12-27 17:28:00.610000',9),(14,'2021-11-11 00:00:06.755000','myKanBan web application','MKBAN',NULL,'myKanBan','2021-11-11 23:48:22.747000',5),(15,'2021-11-11 23:32:14.944000','Backend for myKanBan application','MKBBE',NULL,'myKanBan - BE','2021-11-11 23:37:32.876000',3),(23,'2021-12-27 17:11:50.608000','','TEST4',NULL,'test4','2021-12-27 17:11:50.608000',0),(24,'2021-12-27 17:13:47.876000','','TEST5',NULL,'test5','2021-12-27 17:13:47.876000',0),(25,'2022-03-21 23:38:37.451000','Ecommerce application for an online bakery','BAKE',NULL,'Toms Bakery','2022-03-22 01:30:20.483000',4);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `target_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `ticket_identifier` varchar(255) DEFAULT NULL,
  `project_identifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKon3g4edms44nvfti4skwns3vn` (`project_id`),
  CONSTRAINT `FKon3g4edms44nvfti4skwns3vn` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'2021-11-06 16:34:30.956000',NULL,'TODO',NULL,'Wireframe design','2021-11-06 16:34:30.956000',6,'MKII-1','MKII'),(2,'2021-11-06 16:35:40.935000','Design the power system. Estimate power requirements. Circuit schematics.','TODO',NULL,'Power system design','2021-11-06 16:35:40.935000',6,'MKII-2','MKII'),(3,'2021-11-07 16:00:00.000000','Remove the engine for ease of access','INPROGRESS',NULL,'Engine removal','2021-11-10 23:18:48.497000',NULL,'SFIRE-1','SFIRE'),(4,'2021-11-07 16:00:00.000000','Disassemble all the components to see whether the parts need to be replaced or not','TODO',NULL,'Disassemble engine','2021-12-27 00:43:48.375000',NULL,'SFIRE-2','SFIRE'),(5,'2021-11-07 16:00:00.000000','Clean all parts of the engine. Inspect each part to determine if they need to be replaced or not','TODO',NULL,'Part cleaning & inspection','2021-11-08 01:27:23.870000',NULL,'SFIRE-3','SFIRE'),(6,'2021-11-10 16:00:00.000000','Add dependency to decode JWT in FE so that we can detect expired tokens','DONE',NULL,'Add JWT decoder','2021-11-11 23:32:40.749000',NULL,'MKBAN-1','MKBAN'),(7,'2021-11-10 16:00:00.000000','Dispatch logout action when the JWT is expired','DONE',NULL,'Detect expired JWT','2021-11-11 23:33:56.578000',NULL,'MKBAN-2','MKBAN'),(8,'2021-11-11 00:03:05.562000','Remove bootstrap so I have more control of styling','TODO',NULL,'Remove bootstrap','2021-11-11 00:03:05.562000',14,'MKBAN-3','MKBAN'),(9,'2021-11-11 23:35:20.007000','Restore DELETE route for tickets','TODO',NULL,'DELETE ticket','2021-11-11 23:35:20.007000',15,'MKBBE-1','MKBBE'),(10,'2021-11-11 23:37:11.787000','Unit test for project service','TODO',NULL,'Project service UT','2021-11-11 23:37:11.787000',15,'MKBBE-2','MKBBE'),(11,'2021-11-11 23:37:32.872000','Unit test for ticket service','TODO',NULL,'Ticket service UT','2021-11-11 23:37:32.872000',15,'MKBBE-3','MKBBE'),(12,'2021-11-11 23:39:50.142000','Implement input data validation for the AddTicket component','TODO',NULL,'Add ticket form validation','2021-11-11 23:39:50.142000',14,'MKBAN-4','MKBAN'),(13,'2021-11-11 16:00:00.000000','Add a spinner component to indicate that certain part(s) of the page is loading','DONE',NULL,'Loading spinner component','2021-11-13 01:21:04.542000',NULL,'MKBAN-5','MKBAN'),(17,'2021-12-27 17:24:06.830000','','TODO',NULL,'test','2021-12-27 17:24:06.830000',13,'SFIRE-7','SFIRE'),(18,'2021-12-27 17:26:56.193000','','TODO',NULL,'moretest','2021-12-27 17:26:56.193000',13,'SFIRE-8','SFIRE'),(19,'2021-12-27 17:28:00.607000','','TODO',NULL,'anothertest','2021-12-27 17:28:00.607000',13,'SFIRE-9','SFIRE'),(20,'2022-03-21 23:45:24.733000','Shopping cart view: implement button to increment/decrement item quantity','TODO',NULL,'Update quantity','2022-03-21 23:45:24.733000',25,'BAKE-1','BAKE'),(21,'2022-03-21 23:49:23.584000','Temporarily disable the button to prevent creation of duplicate transactions','TODO',NULL,'Disable checkout button during transaction','2022-03-21 23:49:23.584000',25,'BAKE-2','BAKE'),(22,'2022-03-22 00:52:17.629000','Upon checkout, save order information to the database','TODO',NULL,'Save order details','2022-03-22 00:52:17.629000',25,'BAKE-3','BAKE'),(23,'2022-03-22 01:30:20.477000','Validate the cart cotents and prices. This is to make sure users pay full price for all the item(s) in an order.','TODO',NULL,'Cart validation (server-side)','2022-03-22 01:30:20.477000',25,'BAKE-4','BAKE');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-22  1:32:52
