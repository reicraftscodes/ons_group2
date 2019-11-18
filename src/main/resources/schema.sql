USE ons_db;

DROP TABLE IF EXISTS `user_table`;

CREATE TABLE `user_table` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY(`user_id`),
    KEY `EMAIL_INDEX` (`email`)
);
