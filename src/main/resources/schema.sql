USE ons_db;

DROP TABLE IF EXISTS `user_table`;
DROP TABLE IF EXISTS `user_skills`;

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
