DROP SCHEMA IF EXISTS ons_db;

CREATE SCHEMA IF NOT EXISTS  ons_db;

USE ons_db;

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
    `id` int(20) NOT NULL,
    `email` varchar(100) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `img_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user_skills` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(20) NOT NULL,
      `title` varchar(255) DEFAULT NULL,
      `description` varchar(255) DEFAULT NULL,
      `confidence` tinyint DEFAULT NULL,
      `category_id` int(11),
      PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `categories` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    `parent_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ons_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(9),
(9);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
                        `id` int(20) NOT NULL,
                        `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `users_roles`
--

CREATE TABLE IF NOT EXISTS `users_roles` (
                               `user_id` int(20) NOT NULL,
                               `role_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
--
-- Indexes for table `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
    ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
    ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
    ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(8, 'ROLE_USER');

INSERT INTO `user` (id, email, first_name, last_name, password, img_url) VALUES
(1, 'somafet@gmail.com', 'Soma', 'Somorjai', '$2a$10$Kxz7iLyOQbpYAHK1W..lnu8eu6tHOWb6ILn83iAWtkPwPHH12l1wq', '/images/profile_page/default_profile.jpeg');

INSERT INTO `users_roles` (user_id, role_id) VALUES
(1, 8);

INSERT INTO `user_skills` (id, user_id, title, description, confidence, category_id) VALUES
(1, 1, 'Java', 'Good', 5, null);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

