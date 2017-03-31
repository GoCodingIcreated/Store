-- MySQL Script generated by MySQL Workbench
-- Mon Feb 27 19:30:25 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Product` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Timestore` TIME(6) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `About` TEXT(300) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Store` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Store` (
  `id` INT NOT NULL,
  `adres` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Room` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Room` (
  `id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `number` INT NOT NULL,
  `capacity` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `store_idx` (`store_id` ASC),
  CONSTRAINT `store`
    FOREIGN KEY (`store_id`)
    REFERENCES `mydb`.`Store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Stored_place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Stored_place` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Stored_place` (
  `id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `count` INT NOT NULL,
  `time_arrived` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_idx` (`room_id` ASC),
  INDEX `product_idx` (`product_id` ASC),
  CONSTRAINT `room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`Room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `id` INT NOT NULL,
  `phone` VARCHAR(15) NULL,
  `adres` VARCHAR(45) NULL,
  `name` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `type` TINYINT NOT NULL,
  `customer_id` INT NOT NULL,
  `count` INT UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_id_idx` (`product_id` ASC),
  INDEX `customer_idx` (`customer_id` ASC),
  CONSTRAINT `product_key`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_key`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


