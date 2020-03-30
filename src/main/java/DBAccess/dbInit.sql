-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

CREATE DATABASE  IF NOT EXISTS `useradmin`;

USE `useradmin`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(90) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES 
(1,'jens@somewhere.com','jensen','customer'),
(2,'ken@somewhere.com','kensen','customer'),
(3,'robin@somewhere.com','batman','employee');
UNLOCK TABLES;

--
-- Creating "sport" table
--

CREATE TABLE `useradmin`.`sport` (
  `sport_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sport_id`));

# LOCK TABLES `sport` WRITE;
# INSERT INTO `sport` VALUES
# UNLOCK TABLES;

--
-- Creating "info" table
--
CREATE TABLE `useradmin`.`info` (
  `info_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`info_id`));


-- insert table values --
INSERT INTO `useradmin`.`info` (`name`) VALUES ('Jeg går op i sund kost');
INSERT INTO `useradmin`.`info` (`name`) VALUES ('Jeg har et sommerhus');
INSERT INTO `useradmin`.`info` (`name`) VALUES ('Jeg har et kældedyr');

--
-- Creating "bmi_entry" table
--
CREATE TABLE `useradmin`.`bmi_entry` (
  `bmi_entry_id` INT NOT NULL AUTO_INCREMENT,
  `height` DOUBLE NOT NULL,
  `weight` DOUBLE NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `bmi` DOUBLE NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `sport_id` INT NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bmi_entry_id`));

# LOCK TABLES `bmi_entry` WRITE;
-- insert table values --
# INSERT INTO `bmi_entry` VALUES
# UNLOCK TABLES;

--
-- Creating "link_bmi_info" table
--
CREATE TABLE `useradmin`.`link_bmi_info` (
  `bmi_id` INT NOT NULL,
  `info_id` INT NOT NULL,
  PRIMARY KEY (`bmi_id`, `info_id`));


# LOCK TABLES `link_bmi_info` WRITE;
-- insert table values --
# INSERT INTO `link_bmi_info` VALUES
# UNLOCK TABLES;

--
-- Linking foreign key to link_bmi_info table
--
ALTER TABLE `useradmin`.`link_bmi_info`
ADD INDEX `fk_bmi_info_idx` (`info_id` ASC) VISIBLE;
;
ALTER TABLE `useradmin`.`link_bmi_info`
ADD CONSTRAINT `fk_bmi_link`
  FOREIGN KEY (`bmi_id`)
  REFERENCES `useradmin`.`bmi_entry` (`bmi_entry_id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
ADD CONSTRAINT `fk_bmi_info`
  FOREIGN KEY (`info_id`)
  REFERENCES `useradmin`.`info` (`info_id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

--
-- Linking foreign key to bmi_entry table
--
  ALTER TABLE `useradmin`.`bmi_entry`
ADD INDEX `fk_bmi_sport_idx` (`sport_id` ASC) VISIBLE;
;
ALTER TABLE `useradmin`.`bmi_entry`
ADD CONSTRAINT `fk_bmi_sport`
  FOREIGN KEY (`sport_id`)
  REFERENCES `useradmin`.`sport` (`sport_id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;
