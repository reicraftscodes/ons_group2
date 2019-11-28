CREATE SCHEMA IF NOT EXISTS  ons_db;

USE ons_db;


--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user`
(
  `id`   int(20) NOT NULL,
    `email` varchar(100) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name`     varchar(255) DEFAULT NULL,
  `password`  varchar(255) DEFAULT NULL,
  `img_url`   varchar(255) DEFAULT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user_skills`
(
  `skill_id`    int(11) NOT NULL AUTO_INCREMENT,
  `user_id`     int(20) NOT NULL,
  `title`       varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `confidence`  tinyint      DEFAULT NULL,
  `category_id` int(11),
  PRIMARY KEY (`skill_id`)
);

CREATE TABLE IF NOT EXISTS `categories` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    `parent_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
