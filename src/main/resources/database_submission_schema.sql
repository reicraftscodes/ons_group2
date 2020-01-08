-- ---------------------------------------------------------------------------------------------------------------------
-- Competencies Demonstrated
-- ---------------------------------------------------------------------------------------------------------------------
/*

1. Transaction - line 23
2. Triggers - line 333
3. Stored procedure with error handling - line 302

 */

-- ---------------------------------------------------------------------------------------------------------------------
-- SCHEMA
-- ---------------------------------------------------------------------------------------------------------------------


CREATE SCHEMA IF NOT EXISTS  ons_db;

USE ons_db;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";



-- ---------------------------------------------------------------------------------------------------------------------
-- USER DATA TABLES
-- ---------------------------------------------------------------------------------------------------------------------

-- Contains basic user information and will be used for logging in/ fetching account details
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Skills are children of categories, a skill can be created under multiple categories eg the skill "python" can go
-- under the category back-end web development or also under data analysis as there can be a wide scope of application
-- for a skill
CREATE TABLE IF NOT EXISTS `user_skill`
(
  `id`    int(11) NOT NULL AUTO_INCREMENT,
  `user_id`     int(20) NOT NULL,
  `title`       varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `confidence`  tinyint      DEFAULT NULL,
  `category_id` int(11),
  `is_public` boolean NOT NULL,
  PRIMARY KEY (`id`)
);

-- Categories are used to organise skills and are a top level identifier for skills
CREATE TABLE IF NOT EXISTS `categories` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    `parent_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
-- ---------------------------------------------------------------------------------------------------------------------
-- OFFERS AND REQUESTS FOR HELP TABLES
-- ---------------------------------------------------------------------------------------------------------------------

-- Help offers are posts from a user offering their expertise to other users in a specific field
CREATE TABLE IF NOT EXISTS `help_offer`
(
  `help_offer_id`     int(11) NOT NULL AUTO_INCREMENT,
  `user_id`           int(11) NOT NULL,
  `date_posted`       date         DEFAULT NULL,
  `title`             VARCHAR(25)  DEFAULT NULL,
  `description`       VARCHAR(500) DEFAULT NULL, -- VALUE FOR THIS COULD CHANGE DEPENDING ON IF 500 CHARACTERS IS NOT ENOUGH
  `method_of_contact` VARCHAR(255) DEFAULT NULL, -- BY DEFAULT WILL BE THE USERS EMAIL TIED TO ACCOUNT
  PRIMARY KEY (`help_offer_id`)
);

-- Links skills the users can select upon creating a help offer to their post allowing for easier searching through help
-- offers
CREATE TABLE IF NOT EXISTS `help_offer_skill_link_table`
(
  `help_offer_skill_link_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id`                 int(11) NOT NULL,
  `help_offer_id`            int(11) NOT NULL,
  PRIMARY KEY (`help_offer_skill_link_id`)
);

-- Identical to help offers in terms of the required dataset, used instead to request help from users in a specific field
CREATE TABLE IF NOT EXISTS `help_request`
(
  `help_request_id`   int(11) NOT NULL AUTO_INCREMENT,
  `user_id`           int(11) NOT NULL,
  `date_posted`       date         DEFAULT NULL,
  `title`             VARCHAR(25)  DEFAULT NULL,
  `description`       VARCHAR(500) DEFAULT NULL, -- VALUE FOR THIS COULD CHANGE DEPENDING ON IF 500 CHARACTERS IS NOT ENOUGH
  `method_of_contact` VARCHAR(255) DEFAULT NULL,  -- BY DEFAULT WILL BE THE USERS EMAIL TIED TO ACCOUNT
  PRIMARY KEY (`help_request_id`)
);

-- Links skills the users can select upon creating a help request to their post allowing for easier searching through help
-- offers
CREATE TABLE IF NOT EXISTS `help_request_skill_link_table`
(
  `help_request_skill_link_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_id`                   int(11) NOT NULL,
  `help_request_id`            int(11) NOT NULL,
  PRIMARY KEY (`help_request_skill_link_id`)
);


-- ---------------------------------------------------------------------------------------------------------------------
-- Table structure for table `hibernate_sequence`
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ---------------------------------------------------------------------------------------------------------------------
-- Dumping data for table `hibernate_sequence`
-- ---------------------------------------------------------------------------------------------------------------------

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(9),
(9);


-- ---------------------------------------------------------------------------------------------------------------------
-- Table structure for table `role`
-- ---------------------------------------------------------------------------------------------------------------------

-- Used to identify the type of user eg. admin or base user
CREATE TABLE IF NOT EXISTS `role` (
                        `id` int(20) NOT NULL,
                        `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ---------------------------------------------------------------------------------------------------------------------
-- Table structure for table `users_roles`
-- ---------------------------------------------------------------------------------------------------------------------

-- Serves as a link table between users and roles
CREATE TABLE IF NOT EXISTS `users_roles` (
                               `user_id` int(20) NOT NULL,
                               `role_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ---------------------------------------------------------------------------------------------------------------------
-- Indexes for table `role`
-- ---------------------------------------------------------------------------------------------------------------------

ALTER TABLE `role`
    ADD PRIMARY KEY (`id`);


-- ---------------------------------------------------------------------------------------------------------------------
-- Indexes for table `user`
-- ---------------------------------------------------------------------------------------------------------------------

ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

-- ---------------------------------------------------------------------------------------------------------------------
-- Indexes for table `users_roles`
-- ---------------------------------------------------------------------------------------------------------------------

ALTER TABLE `users_roles`
    ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
    ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);


-- ---------------------------------------------------------------------------------------------------------------------
-- Constraints for table `users_roles`
-- ---------------------------------------------------------------------------------------------------------------------
ALTER TABLE `users_roles`
    ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);


-- ---------------------------------------------------------------------------------------------------------------------
-- CONSTRAINTS FOR USER_SKILL TABLE
-- ---------------------------------------------------------------------------------------------------------------------

-- Create relation between user.id and user_skill.user_id
ALTER TABLE `ons_db`.`user_skill`
ADD INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `ons_db`.`user_skill` 
ADD CONSTRAINT `user_id_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `ons_db`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


-- ---------------------------------------------------------------------------------------------------------------------
-- CONSTRAINTS FOR HELP_OFFER TABLE
-- ---------------------------------------------------------------------------------------------------------------------

-- Create relation between help_offer.user_id and user.id
ALTER TABLE `ons_db`.`help_offer` 
ADD CONSTRAINT `help_offer_user_id_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `ons_db`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  
-- ---------------------------------------------------------------------------------------------------------------------
-- CONSTRAINTS FOR HELP_OFFER_SKILL_LINK TABLE
-- ---------------------------------------------------------------------------------------------------------------------

-- Create relation between help_offer_skill_link_table.user_id and help_offer.help_offer_id
ALTER TABLE `ons_db`.`help_offer_skill_link_table` 
ADD INDEX `help_offer_skill_link_table_user_id_fk_idx` (`help_offer_id` ASC) VISIBLE;
;
ALTER TABLE `ons_db`.`help_offer_skill_link_table` 
ADD CONSTRAINT `help_offer_skill_link_table_user_id_fk`
  FOREIGN KEY (`help_offer_id`)
  REFERENCES `ons_db`.`help_offer` (`help_offer_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

-- Create relation between help_offer_skill_link_table.skill_id and user_skills.id
ALTER TABLE `ons_db`.`help_offer_skill_link_table` 
ADD INDEX `help_offer_skill_link_table_skill_id_fk_idx` (`skill_id` ASC) VISIBLE;
;
ALTER TABLE `ons_db`.`help_offer_skill_link_table` 
ADD CONSTRAINT `help_offer_skill_link_table_skill_id_fk`
  FOREIGN KEY (`skill_id`)
  REFERENCES `ons_db`.`user_skill` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  
-- ---------------------------------------------------------------------------------------------------------------------
-- CONSTRAINTS FOR HELP_REQUEST TABLE
-- ---------------------------------------------------------------------------------------------------------------------

-- Create relation between help_request.user_id and user.id
ALTER TABLE `ons_db`.`help_request` 
ADD INDEX `help_request_user_id_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `ons_db`.`help_request` 
ADD CONSTRAINT `help_request_user_id_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `ons_db`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
-- Create relation between help_request_skill_link_table.skill_id and user_skills.id

  
  
-- ---------------------------------------------------------------------------------------------------------------------
-- CONSTRAINTS FOR HELP_REQUEST_SKILL_LINK TABLE
-- ---------------------------------------------------------------------------------------------------------------------

-- Create relation between help_request_skill_link_table.user_id and help_request.help_request_id
ALTER TABLE `ons_db`.`help_request_skill_link_table` 
ADD INDEX `help_request_id_fk_idx` (`help_request_id` ASC) VISIBLE;
;
ALTER TABLE `ons_db`.`help_request_skill_link_table` 
ADD CONSTRAINT `help_request_id_fk`
  FOREIGN KEY (`help_request_id`)
  REFERENCES `ons_db`.`help_request` (`help_request_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;



-- ---------------------------------------------------------------------------------------------------------------------
-- Creating dummy data
-- ---------------------------------------------------------------------------------------------------------------------

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(8, 'ROLE_USER');

INSERT INTO `user` (id, email, first_name, last_name, password, img_url) VALUES
(1, 'dummy@email.com', 'Darth', 'Vader', '$2a$10$Kxz7iLyOQbpYAHK1W..lnu8eu6tHOWb6ILn83iAWtkPwPHH12l1wq', '/images/profile_page/default_profile.jpeg');

INSERT INTO `users_roles` (user_id, role_id) VALUES
(1, 8);

INSERT INTO `user_skill` (id, user_id, title, description, confidence, category_id) VALUES
(1, 1, 'Java', 'Spring Security', 5, null);

COMMIT;



-- ---------------------------------------------------------------------------------------------------------------------
-- STORED PROCEDURE WITH ERROR HANDLING 
-- ---------------------------------------------------------------------------------------------------------------------
DELIMITER //
 /*
 ensures that the selected skill description can be edited
  */
CREATE PROCEDURE editSkillDescriptionById(
    IN newDescription VARCHAR(255),
    IN skillId INT
    
)
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    ROLLBACK;
    SELECT 'An error has occurred, Stored procedure has been rolled back terminated';
    END;

    UPDATE user_skill
    SET description = newDescription
    WHERE id = skillId;
    
END //
 
DELIMITER ;

INSERT INTO user_skill VALUES (null,1,"test","test",1,1,1); -- TO ENSURE THERE IS A SKILL THAT CAN BE EDITED

CALL editSkillDescriptionById('pls work',1);  --  PRE-POPULATED TEST




-- ---------------------------------------------------------------------------------------------------------------------
-- TRIGGERS
-- ---------------------------------------------------------------------------------------------------------------------

-- Deletes skills that would be tied to a user upon the deletion of a user from the database
CREATE TRIGGER del_user_and_skills_trigger AFTER DELETE ON user
    FOR EACH ROW
    DELETE FROM user_skill WHERE user_id = OLD.id;
    
-- Test statements for trigger
INSERT INTO user VALUES(30,"email@gmail.com","test user first name","test user last name","test password",null);
SELECT * FROM user;

INSERT INTO user_skill VALUES(null,30,"test skill", "test description",1,1,1); -- create skill associated to test user
SELECT * FROM user_skill;

DELETE FROM user WHERE id = 30;



-- ---------------------------------------------------------------------------------------------------------------------
-- DUMMY HELP OFFERS
-- ---------------------------------------------------------------------------------------------------------------------

INSERT INTO help_offer VALUES(null,30,curdate(),"test title 1", "test description 1","email1@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 2", "test description 2","email2@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 3", "test description 3","email3@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 4", "test description 4","email4@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 5", "test description 5","email5@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 6", "test description 6","email6@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 7", "test description 7","email7@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 8", "test description 8","email8@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 9", "test description 9","email9@gmail.com");
INSERT INTO help_offer VALUES(null,30,curdate(),"test title 10", "test description 10","email10@gmail.com");



-- ---------------------------------------------------------------------------------------------------------------------
-- DUMMY HELP REQUESTS
-- ---------------------------------------------------------------------------------------------------------------------

INSERT INTO help_request VALUES(null,30,curdate(),"test title 1", "test description 1","email1@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 2", "test description 2","email2@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 3", "test description 3","email3@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 4", "test description 4","email4@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 5", "test description 5","email5@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 6", "test description 6","email6@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 7", "test description 7","email7@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 8", "test description 8","email8@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 9", "test description 9","email9@gmail.com");
INSERT INTO help_request VALUES(null,30,curdate(),"test title 10", "test description 10","email10@gmail.com");




-- ---------------------------------------------------------------------------------------------------------------------
-- DUMMY SKILL CATEGORIES
-- ---------------------------------------------------------------------------------------------------------------------

INSERT INTO categories VALUES(null, "Frontend web-dev", null);
INSERT INTO categories VALUES(null, "Backend web-dev", null);
INSERT INTO categories VALUES(null, "Machine learning", null);
INSERT INTO categories VALUES(null, "Computer vision", null);
INSERT INTO categories VALUES(null, "Neural networks", null);
INSERT INTO categories VALUES(null, "Deep learning", null);