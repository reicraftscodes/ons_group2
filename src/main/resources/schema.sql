USE ons_db;

DROP TABLE IF EXISTS `user_table`;
DROP TABLE IF EXISTS `user_skills`;
DROP TABLE IF EXISTS `help_offer`;
DROP TABLE IF EXISTS  `help_offer_skill_link_table`;

-- ---------------------------------------------------------------------------------------------------------------------
-- USER DATA TABLES
-- ---------------------------------------------------------------------------------------------------------------------
CREATE TABLE `user_table` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `img_url` varchar(255) DEFAULT NULL,
    PRIMARY KEY(`user_id`),
    KEY `EMAIL_INDEX` (`email`)
);

CREATE TABLE `user_skills` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) NOT NULL,
      `title` varchar(255) DEFAULT NULL,
      `description` varchar(255) DEFAULT NULL,
      `confidence` tinyint DEFAULT NULL,
      PRIMARY KEY(`id`)
);

-- ---------------------------------------------------------------------------------------------------------------------
-- OFFERS AND REQUESTS FOR HELP TABLES
-- ---------------------------------------------------------------------------------------------------------------------
CREATE TABLE `help_offer`(
  `help_offer_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `date_posted`date DEFAULT NULL,
  `title` VARCHAR(25) DEFAULT NULL,
  `description` VARCHAR(500) DEFAULT NULL, -- VALUE FOR THIS COULD CHANGE DEPENDING ON IF 500 CHARACTERS IS NOT ENOUGH
  `method_of_contact` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(`help_offer_id`)
);

CREATE TABLE `help_offer_skill_link_table`(
  `help_offer_skill_link_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id` int(11) NOT NULL,
  `help_offer_id` int(11) NOT NULL,
  PRIMARY KEY(`help_offer_skill_link_id`)
);
