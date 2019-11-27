CREATE SCHEMA IF NOT EXISTS ons_db;

USE ons_db;

CREATE TABLE IF NOT EXISTS `user_table`
(
  `user_id`   int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `email`     varchar(255) DEFAULT NULL,
  `password`  varchar(255) DEFAULT NULL,
  `img_url`   varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `EMAIL_INDEX` (`email`)
);

CREATE TABLE IF NOT EXISTS `user_skills`
(
  `id`          int(11) NOT NULL AUTO_INCREMENT,
  `user_id`     int(11) NOT NULL,
  `title`       varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `confidence`  tinyint      DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `categories`
(
  `id`        int(11)      NOT NULL AUTO_INCREMENT,
  `name`      varchar(255) NOT NULL UNIQUE,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)

);

CREATE TABLE `help_request`
(
  `help_request_id`     int(11) NOT NULL AUTO_INCREMENT,
  `user_id`           int(11) NOT NULL,
  `date_posted`       date         DEFAULT NULL,
  `title`             VARCHAR(25)  DEFAULT NULL,
  `description`       VARCHAR(500) DEFAULT NULL, -- VALUE FOR THIS COULD CHANGE DEPENDING ON IF 500 CHARACTERS IS NOT ENOUGH
  `method_of_contact` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`help_request_id`)
);

CREATE TABLE `help_request_skill_link_table`
(
  `help_request_skill_link_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id`                 int(11) NOT NULL,
  `help_request_id`            int(11) NOT NULL,
  PRIMARY KEY (`help_request_skill_link_id`)
);
