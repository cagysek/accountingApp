# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.4.8-MariaDB-1:10.4.8+maria~bionic)
# Database: mydb
# Generation Time: 2020-01-05 21:13:31 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;

INSERT INTO `address` (`id`, `street`, `city`, `zip`)
VALUES
	(2,'Máchova 20','Plzeň',30100),
	(3,'Lobézská 39','Plzeň',32600),
	(4,'Lomská','Lom',43511),
	(12,'','',NULL),
	(14,'','',NULL),
	(15,'','',NULL),
	(16,'Máchova 20','Plzeň',30100),
	(18,'','',NULL);

/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `ic` int(11) DEFAULT NULL,
  `dic` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `account_number` varchar(45) DEFAULT NULL,
  `address_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_company_address1_idx` (`address_id`),
  CONSTRAINT `fk_company_address1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`id`, `name`, `ic`, `dic`, `phone`, `email`, `account_number`, `address_id`)
VALUES
	(6,'Společnost Miroslava Soukupa',11,'22','33','44','55',2),
	(7,'Rtsoft',1111,'2222','3333','4444','263361400/0300',3);

/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table invoice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `supplier_company_id` int(11) DEFAULT NULL,
  `bill_to_company_id` int(11) DEFAULT NULL,
  `date_publish` date DEFAULT NULL,
  `date_payment` date DEFAULT NULL,
  `date_taxable_supply` date DEFAULT NULL,
  `is_storno` tinyint(1) NOT NULL DEFAULT 0,
  `type` enum('ACCEPTED','PUBLISHED') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_company1_idx` (`bill_to_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;

INSERT INTO `invoice` (`id`, `supplier_company_id`, `bill_to_company_id`, `date_publish`, `date_payment`, `date_taxable_supply`, `is_storno`, `type`)
VALUES
	(3,7,6,'2019-05-05','2019-12-12','2020-02-02',0,'PUBLISHED'),
	(4,6,6,'2019-12-02','2019-12-18','2020-02-02',0,'ACCEPTED'),
	(6,7,6,'2020-01-02','2020-01-22','2020-01-31',0,'ACCEPTED'),
	(12,7,7,'2020-01-12','2020-01-13','2020-01-14',1,'PUBLISHED'),
	(13,7,7,'2020-01-12','2020-01-13','2020-01-14',1,'PUBLISHED'),
	(14,7,7,'2020-01-12','2020-01-13','2020-01-14',1,'PUBLISHED');

/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table invoice_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `invoice_item`;

CREATE TABLE `invoice_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT '',
  `quantity` int(11) NOT NULL DEFAULT 1,
  `price_vat` int(11) NOT NULL DEFAULT 0,
  `price` decimal(10,2) NOT NULL DEFAULT 0.00,
  `price_dph` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`),
  KEY `fk_invoice_item_product1_idx` (`name`),
  CONSTRAINT `fk_invoice_item_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;

INSERT INTO `invoice_item` (`id`, `invoice_id`, `name`, `quantity`, `price_vat`, `price`, `price_dph`)
VALUES
	(3,3,'Produkt 1',3,21,1000.00,1210.00),
	(4,3,'Produkt 2',1,21,432.00,522.72),
	(5,3,'Produkt 3',1,21,314.00,379.94),
	(12,12,'Test',2,21,220.00,1233.00),
	(13,12,'Test 2',1,21,220.00,222.00),
	(14,13,'Test',2,21,220.00,1233.00),
	(15,13,'Test 2',1,21,220.00,222.00),
	(16,14,'Test',2,21,220.00,1233.00),
	(17,14,'Test 2',1,21,220.00,222.00),
	(18,14,'test3',3,21,444.00,500.00),
	(19,6,'Test',1,21,222.00,333.00),
	(20,6,'AA',1,21,400.00,500.00);

/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`)
VALUES
	(1,'ADMIN'),
	(2,'PURSER');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `personal_identification_number` varchar(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `account_number` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address_id` int(10) unsigned DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_address1_idx` (`address_id`),
  KEY `fk_user_user_type1_idx` (`role_id`),
  CONSTRAINT `fk_user_address1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_type1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `firstname`, `lastname`, `personal_identification_number`, `email`, `phone`, `account_number`, `card_number`, `username`, `password`, `address_id`, `role_id`)
VALUES
	(3,'Honza','čagy','960827/9999','aa@b.cz','','263361400/0300','3421 1111 2222 3333','cagy','$2a$10$ct1VOnHj5Yo4V9hs9XX2v.H7OntRoTeTP5pWn3Ma3QL8P9C06GbJu',4,1),
	(6,'Admin1','Admin','123456/1234','admin@admin.cz','','111 222 333 / 4444','1111 2222 3333 4444','admin001','$2a$10$0Fzi4YkmfF89cCrmDjstjuw3.19RUkvwNnZtJRnvtmhBn4eaSUMB6',12,1),
	(8,'Účetní 1','Účetní','111111/1111','purser1@purser.com','','111 222 333 / 4444','1111222233334444','purser001','$2a$10$g/la6AHxjH2kFrKrCxlzs.LkEHD6ZCreyz1nE0bXnim.Ht4WWHcFu',15,2),
	(9,'Admin2','Admin','654321/1234','admin2@admin.cz','','333 222 111 / 4444','1111 2222 3333 4444','admin002','$2a$10$ixV9f8vdqj5aZM0lhEg4kOIetNUQ5LpMbFVtzmxQOwaXOqWOBOYXO',16,1),
	(11,'Účetní 2','money','960827/2980','purser2@money.com','+420 603989504','263351400/0300','5423 3421 6032 9465','purser002','$2a$10$TpBt08PQ8Mlsw7Y5FLzBUu.Zch6V5cQw6uS9jMaFPiz858p5gYvHO',18,2);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
