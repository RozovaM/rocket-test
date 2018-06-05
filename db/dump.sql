-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 192.168.12.50    Database: homestead
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `param` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `actions_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

# Dump of table actions
# ------------------------------------------------------------

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;

INSERT INTO `actions` (`id`, `name`, `key`, `title`, `param`)
VALUES
  (1,'remind','remind','Påminn',NULL),
  (2,'VOCExpireAgreement.readMore','open_web_view','Läs mer','http://support.volvocars.com/se/Pages/article.aspx?article=088b4f0af650815ec0a801516931c388'),
  (3,'contactWorkshop','open_screen','Kontakta verkstad','contact_workshop'),
  (4,'renewOnline','open_web_view','Förläng online','https://store.volvocars.com'),
  (5,'openVOC','open_voc','Öppna Volvo On Call',NULL),
  (6,'VolvoAssistanceExpire.readMore','open_web_view','Läs mer','https://minvolvo.volvocars.se/ownership/assistance'),
  (7,'ServiceAgreement.viewDetails','open_web_view','Visa detaljer','https://minvolvo.volvocars.se/service/service#volvoServiceAgreement'),
  (8,'VolviaInsurance.viewDetails','open_web_view','Visa detaljer','https://minvolvo.volvocars.se/insurance/insurance'),
  (9,'Warranty.readMore','open_web_view','Läs mer','https://www.volvocars.com/se/kop/finansiering/garantier'),
  (10,'ServiceRepairAgreement.viewDetails','open_screen','Visa detaljer','service_repair_agreement'),
  (11,'DeliveryPhase.viewDetails','open_screen','Följ din leverans','delivery_phase'),
  (12,'ContactDeliveryPerson.viewDetails','open_screen','Kontakta handlare','dealer_contact_info'),
  (13,'ContactSalesPerson.viewDetails','open_screen','Kontakta säljare','sales_person_contact_info'),
  (14,'TireShiftBook.webView','open_web_view','Boka hjulskifte','https://minvolvo.volvocars.se/tires/tire'),
  (15,'ServiceBook.webView','open_web_view','Boka Service','https://minvolvo.volvocars.se/service/service'),
  (16,'Tax.readMore','open_web_view','Läs mer','https://www.transportstyrelsen.se/sv/vagtrafik/Fordon/Fordonsskatt/Hur-bestams-skattens-storlek/'),
  (17,'Tax.adjustAmount','open_screen','Justera belopp','tax_details_view'),
  (18,'Tax.remind','remind','Påminn mig',NULL);

/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `cache`
--

DROP TABLE IF EXISTS `cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache` (
  `key` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `expiration` int(11) NOT NULL,
  UNIQUE KEY `cache_key_unique` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `id` int(10) unsigned NOT NULL,
  `regNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modelYear` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modelName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `assistanceFrom` datetime DEFAULT NULL,
  `assistanceTo` datetime DEFAULT NULL,
  `privateOwned` int(11) DEFAULT NULL,
  `hasVolvoOnCall` int(11) DEFAULT NULL,
  `constructed` int(11) DEFAULT NULL,
  `deliveryStatus` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deliveryStep` int(11) DEFAULT NULL,
  `deliveryTotalSteps` int(11) DEFAULT NULL,
  `userId` int(10) unsigned NOT NULL,
  `personalServiceTechnician` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `co2emission` int(11) NOT NULL DEFAULT '0',
  `fuelType` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `registrationDate` datetime DEFAULT NULL,
  `servicingDealerId` int(10) unsigned DEFAULT NULL,
  `pstContactIdentityKey` int(10) unsigned DEFAULT NULL,
  `salesPersonContactIdentityKey` int(10) unsigned DEFAULT NULL,
  `sellingDealerId` int(10) unsigned DEFAULT NULL,
  UNIQUE KEY `cars_id_unique` (`id`),
  KEY `cars_userid_foreign` (`userId`),
  CONSTRAINT `cars_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `dealerContacts`
--

DROP TABLE IF EXISTS `dealerContacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealerContacts` (
  `id` int(10) COLLATE utf8mb4_unicode_ci DEFAULT 0,
  `dealerId` int(10) unsigned NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `externalContactId` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `districtNumber` int(11) DEFAULT NULL,
  `telephone` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `identityKey` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`identityKey`),
  UNIQUE KEY `dealercontacts_id_externalcontactid_unique` (`id`,`externalContactId`),
  KEY `dealercontacts_dealerid_foreign` (`dealerId`),
  CONSTRAINT `dealercontacts_dealerid_foreign` FOREIGN KEY (`dealerId`) REFERENCES `dealers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dealerTypes`
--

DROP TABLE IF EXISTS `dealerTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealerTypes` (
  `dealerId` int(10) unsigned NOT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `dealertypes_dealerid_type_unique` (`dealerId`,`type`),
  CONSTRAINT `dealertypes_dealerid_foreign` FOREIGN KEY (`dealerId`) REFERENCES `dealers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dealers`
--

DROP TABLE IF EXISTS `dealers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealers` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `visitAdress` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `visitPostal` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `districtNumber` int(11) DEFAULT NULL,
  `latitude` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `longitude` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enableVCRSWheelchange` tinyint(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vcrsWorkshopId` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  UNIQUE KEY `dealers_id_unique` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `agreements`
--

DROP TABLE IF EXISTS `agreements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agreements` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expireDate` datetime DEFAULT NULL,
  `contractMonthlyCostWithVAT` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `yearlyPremieWithVAT` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `validToday` tinyint(1) DEFAULT NULL,
  `carId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `agreements_carid_foreign` (`carId`),
  CONSTRAINT `agreements_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warranties`
--

DROP TABLE IF EXISTS `warranties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranties` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expireDate` datetime NOT NULL,
  `information` TEXT COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `validToday` tinyint(1) DEFAULT NULL,
  `carId` int(10) unsigned DEFAULT NULL,
  UNIQUE KEY `warranties_id_unique` (`id`),
  KEY `warranties_carid_foreign` (`carId`),
  CONSTRAINT `warranties_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `eventActions`
--

DROP TABLE IF EXISTS `eventActions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventActions` (
  `eventId` int(10) unsigned NOT NULL,
  `actionId` int(10) unsigned NOT NULL,
  KEY `eventactions_eventid_foreign` (`eventId`),
  KEY `eventactions_actionid_foreign` (`actionId`),
  CONSTRAINT `eventactions_actionid_foreign` FOREIGN KEY (`actionId`) REFERENCES `actions` (`id`),
  CONSTRAINT `eventactions_eventid_foreign` FOREIGN KEY (`eventId`) REFERENCES `events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL,
  `deliveryWeek` int(11) DEFAULT NULL,
  `weeksUntilDelivery` int(11) DEFAULT NULL,
  `latestDayOfChange` datetime DEFAULT NULL,
  `carId` int(10) unsigned DEFAULT NULL,
  `factory` varchar(255) DEFAULT '',
  `orderNumber` varchar(50) DEFAULT NULL,
  UNIQUE KEY `order_id_unique` (`id`),
  KEY `order_carid_foreign` (`carId`),
  CONSTRAINT `order_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `status` enum('pending','done','autochange') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'autochange',
  `carId` int(10) unsigned NOT NULL,
  `date` datetime NOT NULL,
  `endDateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `events_userid_foreign` (`userId`),
  KEY `events_carid_foreign` (`carId`),
  CONSTRAINT `events_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`),
  CONSTRAINT `events_userid_foreign` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `failed_jobs`
--

DROP TABLE IF EXISTS `failed_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `failed_jobs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `volvoContactDetails`
--

DROP TABLE IF EXISTS `volvoContactDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volvoContactDetails` (
  `contactId` int(10) unsigned NOT NULL,
  `url` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `volvocontactdetails_contactid_foreign` (`contactId`),
  CONSTRAINT `volvocontactdetails_contactid_foreign` FOREIGN KEY (`contactId`) REFERENCES `volvoContacts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `volvoContacts`
--

DROP TABLE IF EXISTS `volvoContacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volvoContacts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `trip_statistics`
--

DROP TABLE IF EXISTS `trip_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip_statistics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `volvo_user_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  `start_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time_duration` int(11) NOT NULL,
  `fuel_consumption` int(11) NOT NULL,
  `avg_speed` decimal(8,2) NOT NULL,
  `start_odometer` int(11) NOT NULL,
  `end_odometer` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `year_month` int(11) NOT NULL,
  `created_at` int(11) NOT NULL,
  `volvo_trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `trip_statistics_volvo_user_id_index` (`volvo_user_id`),
  KEY `trip_statistics_car_id_index` (`car_id`),
  KEY `trip_statistics_year_month_index` (`year_month`)
) ENGINE=InnoDB AUTO_INCREMENT=2817 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userTokens`
--

DROP TABLE IF EXISTS `userTokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userTokens` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expirationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `assistantQuestions`
--

DROP TABLE IF EXISTS `assistantQuestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assistantQuestions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titleOne` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `titleMultiple` text COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `type` enum('nextEvent','customSupport','personalTechnician','carInspection','deliveryDate','tireShift','serviceDate', 'carManual') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `assistantAnswers`
--

DROP TABLE IF EXISTS `assistantAnswers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assistantAnswers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `questionId` int(10) unsigned DEFAULT NULL,
  `template` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `assistantanswers_questionid_foreign` (`questionId`),
  CONSTRAINT `assistantanswers_questionid_foreign` FOREIGN KEY (`questionId`) REFERENCES `assistantQuestions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carProperties`
--

DROP TABLE IF EXISTS `carProperties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carProperties` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carId` int(10) unsigned NOT NULL,
  `property` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `carproperties_carid_property_unique` (`carId`,`property`),
  CONSTRAINT `carproperties_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `carPropertyItems`
--

DROP TABLE IF EXISTS `carPropertyItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carPropertyItems` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `carPropertyId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `carpropertyitems_carpropertyid_foreign` (`carPropertyId`),
  CONSTRAINT `carpropertyitems_carpropertyid_foreign` FOREIGN KEY (`carPropertyId`) REFERENCES `carProperties` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `manuals`
--

DROP TABLE IF EXISTS `manuals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manuals` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carId` int(10) unsigned NOT NULL,
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `resourceName` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `manuals_carid_foreign` (`carId`),
  CONSTRAINT `manuals_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `specifications`
--

DROP TABLE IF EXISTS `specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specifications` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carId` int(10) unsigned NOT NULL,
  `upholstery` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `color` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `engine` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `steering` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gearbox` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modelCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `salesVersionCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `salesVersion` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bodyCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `colorCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `engineCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gearboxCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `marketCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sMessageCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stearingCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upholsteryCode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `specifications_carid_foreign` (`carId`),
  CONSTRAINT `specifications_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carServices`
--

DROP TABLE IF EXISTS `carServices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carServices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dealerName` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `carId` int(11) NOT NULL,
  `type` enum('HistoryService','NextService') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `carServiceInfo`
--

DROP TABLE IF EXISTS `carServiceInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carServiceInfo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carServiceId` int(10) unsigned NOT NULL,
  `info` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `carserviceinfo_carserviceid_foreign` (`carServiceId`),
  CONSTRAINT `carserviceinfo_carserviceid_foreign` FOREIGN KEY (`carServiceId`) REFERENCES `carServices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carId` int(10) unsigned NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `options_carid_foreign` (`carId`),
  CONSTRAINT `options_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carAttributes`
--

DROP TABLE IF EXISTS `carAttributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carAttributes` (
  `vin` varchar(17) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fuelType` enum('Gasoline','Diesel','FlexiFuel','HEV') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`vin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carId` int(10) unsigned NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `key` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `information_carid_foreign` (`carId`),
  CONSTRAINT `information_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tireShiftBookings`
--

DROP TABLE IF EXISTS `tireShiftBookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tireShiftBookings` (
  `id` int(10) unsigned NOT NULL,
  `dealerId` int(10) unsigned NOT NULL,
  `carId` int(10) unsigned NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `workshopId` int(10) unsigned DEFAULT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `tireshiftbookings_carid_foreign` (`carId`),
  CONSTRAINT `tireshiftbookings_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userFCMDevices`
--

DROP TABLE IF EXISTS `userFCMDevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userFCMDevices` (
  `userTokenId` int(10) unsigned NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('android','ios','web') COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `userfcmdevices_token_unique` (`token`),
  KEY `userfcmdevices_usertokenid_foreign` (`userTokenId`),
  CONSTRAINT `userfcmdevices_usertokenid_foreign` FOREIGN KEY (`userTokenId`) REFERENCES `userTokens` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `tireShiftInternalOrder`
--

DROP TABLE IF EXISTS `tireShiftInternalOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tireShiftInternalOrder` (
  `status` enum('created','pending','failed','succeed') COLLATE utf8mb4_unicode_ci NOT NULL,
  `carId` int(10) unsigned DEFAULT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `branchId` int(11) DEFAULT NULL,
  `sessionId` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `tireshiftinternalorder_sessionid_unique` (`sessionId`),
  KEY `tireshiftinternalorder_carid_foreign` (`carId`),
  CONSTRAINT `tireshiftinternalorder_carid_foreign` FOREIGN KEY (`carId`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'homestead'
--

--
-- Dumping routines for database 'homestead'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 16:32:20
