DROP DATABASE `pro_tests`;
/*
/*
DROP TABLE `user_answer`;
DROP TABLE `user_resultat`;
DROP TABLE `user`;

DROP TABLE `answer`;
DROP TABLE `question`;
DROP TABLE `test`;
DROP TABLE `subject`;
/**/

-- -----------------------------------------------------
-- Database pro_tests
-- -----------------------------------------------------

CREATE DATABASE IF NOT EXISTS `pro_tests` DEFAULT CHARACTER SET utf8;
USE `pro_tests` ;

-- -----------------------------------------------------
-- Table `pro_tests`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `number` VARCHAR(20) NULL,
  `e_mail` VARCHAR(40) NOT NULL,
  `photo` VARCHAR(45) NULL DEFAULT '/pages/img/icon/default.jpg',
  `date` DATETIME NOT NULL,
   `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  `isBlocked` TINYINT(1) NOT NULL DEFAULT 0,
  `isActivated` TINYINT(1) NOT NULL DEFAULT 0,
  `last_name` VARCHAR(20) NULL,
  `role` VARCHAR(20) NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `e_mail_UNIQUE` (`e_mail` ASC));
  -- -----------------------------------------------------
-- Table `pro_tests`.`Subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `icon` VARCHAR(150) NOT NULL,
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `pro_tests`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `subject_id` INT NOT NULL,
  `test_time` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  `type` VARCHAR(10) NOT NULL,
  `icon` VARCHAR(20) NOT NULL,
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `test_subject_idx` (`subject_id` ASC),
  CONSTRAINT `test_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`));

-- -----------------------------------------------------
-- Table `pro_tests`.`user_resultat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_resultat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `result_field` INT NOT NULL,
  `date_start` DATETIME NOT NULL,
  `date_finish` DATETIME NOT NULL,
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `resultat_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
    CONSTRAINT `resultat_test`
    FOREIGN KEY (`test_id`)
    REFERENCES `test` (`id`));







-- -----------------------------------------------------
-- Table `pro_tests`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` TEXT NOT NULL,
  `code` MEDIUMTEXT,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `question_idx` (`test_id` ASC),
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  CONSTRAINT `question`
    FOREIGN KEY (`test_id`)
    REFERENCES `test` (`id`))
     ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table `pro_tests`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NOT NULL,
  `answer` VARCHAR(255) NOT NULL,
  `truthful` BOOL NOT NULL,
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `answer_question_idx` (`question_id` ASC),
  CONSTRAINT `answer_question`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`));


-- -----------------------------------------------------
-- Table `pro_tests`.`user_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_answer` (
  `answer_id` INT NOT NULL,
  `user_resultat_id` INT NOT NULL,
  PRIMARY KEY (`answer_id`, `user_resultat_id`),
  INDEX `answer_user_resultat_idx` (`user_resultat_id` ASC),
  `isDelete` TINYINT(1) NOT NULL DEFAULT 0,
  CONSTRAINT `answer_user_answer`
    FOREIGN KEY (`answer_id`)
    REFERENCES `answer` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `answer_user_resultat`
    FOREIGN KEY (`user_resultat_id`)
    REFERENCES `user_resultat` (`id`));

