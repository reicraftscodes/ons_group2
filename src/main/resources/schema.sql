CREATE SCHEMA IF NOT EXISTS  ons_db;

USE ons_db;

CREATE TABLE IF NOT EXISTS `user_table` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `img_url` varchar(255) DEFAULT NULL,
    PRIMARY KEY(`user_id`),
    KEY `EMAIL_INDEX` (`email`)
);

CREATE TABLE IF NOT EXISTS `user_skills` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) NOT NULL,
      `title` varchar(255) DEFAULT NULL,
      `description` varchar(255) DEFAULT NULL,
      `confidence` tinyint DEFAULT NULL,
      PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `categories` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `parent_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
