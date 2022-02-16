-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dental_clinic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dental_clinic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dental_clinic` DEFAULT CHARACTER SET utf8 ;
USE `dental_clinic` ;

-- -----------------------------------------------------
-- Table `dental_clinic`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(10) NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`client_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`client_details` (
  `client_user_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `data_birth` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  INDEX `fk_user_details_user_idx` (`client_user_id` ASC) VISIBLE,
  PRIMARY KEY (`client_user_id`),
  CONSTRAINT `fk_user_details_user`
    FOREIGN KEY (`client_user_id`)
    REFERENCES `dental_clinic`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`record` (
  `id_record` INT NOT NULL AUTO_INCREMENT,
  `doctor_type` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `time` TIME NULL,
  `status` VARCHAR(45) NULL,
  `user_user_id` INT NOT NULL,
  `doctor_id` INT NULL DEFAULT 0,
  PRIMARY KEY (`id_record`),
  INDEX `fk_record_user1_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_record_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `dental_clinic`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`reception`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`reception` (
  `id_reception` INT NOT NULL AUTO_INCREMENT,
  `record_id_record` INT NOT NULL,
  `client_id` VARCHAR(45) NULL,
  `doctor_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id_reception`),
  INDEX `fk_reception_record1_idx` (`record_id_record` ASC) VISIBLE,
  CONSTRAINT `fk_reception_record1`
    FOREIGN KEY (`record_id_record`)
    REFERENCES `dental_clinic`.`record` (`id_record`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`doctor_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`doctor_details` (
  `doctor_user_id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `doctor_type` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `fk_doctor_details_user1_idx` (`doctor_user_id` ASC) VISIBLE,
  PRIMARY KEY (`doctor_user_id`),
  CONSTRAINT `fk_doctor_details_user1`
    FOREIGN KEY (`doctor_user_id`)
    REFERENCES `dental_clinic`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`doctor_timetable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`doctor_timetable` (
  `doctor_timetable_id` INT NOT NULL AUTO_INCREMENT,
  `client_surname` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL,
  `time` TIME NULL,
  `doctor_details_doctor_user_id` INT NOT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`doctor_timetable_id`),
  INDEX `fk_doctor_timetable_doctor_details1_idx` (`doctor_details_doctor_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_timetable_doctor_details1`
    FOREIGN KEY (`doctor_details_doctor_user_id`)
    REFERENCES `dental_clinic`.`doctor_details` (`doctor_user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`client_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`client_record` (
  `client_record_id` INT NOT NULL AUTO_INCREMENT,
  `doctor_surname` VARCHAR(45) NULL DEFAULT NULL,
  `doctor_type` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `time` TIME NULL DEFAULT NULL,
  `client_details_client_user_id` INT NOT NULL,
  PRIMARY KEY (`client_record_id`),
  INDEX `fk_client_record_client_details1_idx` (`client_details_client_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_record_client_details1`
    FOREIGN KEY (`client_details_client_user_id`)
    REFERENCES `dental_clinic`.`client_details` (`client_user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dental_clinic`.`timetable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dental_clinic`.`timetable` (
  `idtimetable` INT NOT NULL AUTO_INCREMENT,
  `time` TIME NULL,
  PRIMARY KEY (`idtimetable`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
