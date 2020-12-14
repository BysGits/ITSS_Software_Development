-- MySQL Script generated by MySQL Workbench
-- Tue Dec  8 20:19:11 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`BikeType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BikeType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pedals` INT NULL DEFAULT NULL,
  `saddles` INT NULL DEFAULT NULL,
  `rearSeats` INT NULL DEFAULT NULL,
  `rentingFee` INT NOT NULL,
  `depositFee` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Dock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Dock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `availableBikes` INT NOT NULL,
  `emptySlots` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bike` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `typeId` INT NOT NULL,
  `barcode` VARCHAR(45) NOT NULL,
  `batteryLife` INT NOT NULL,
  `state` TINYINT NOT NULL,
  `dockId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `biketype_idx` (`typeId` ASC) VISIBLE,
  INDEX `dock_idx` (`dockId` ASC) VISIBLE,
  CONSTRAINT `biketype`
    FOREIGN KEY (`typeId`)
    REFERENCES `mydb`.`BikeType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dock`
    FOREIGN KEY (`dockId`)
    REFERENCES `mydb`.`Dock` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cardCode` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `cvvCode` VARCHAR(3) NOT NULL,
  `dateExpired` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bikeId` INT NOT NULL,
  `rentTime` INT NOT NULL,
  `currentFee` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `bike_idx` (`bikeId` ASC) VISIBLE,
  CONSTRAINT `bike`
    FOREIGN KEY (`bikeId`)
    REFERENCES `mydb`.`Bike` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Invoice` (
  `id` INT NOT NULL,
  `rentId` INT NOT NULL,
  `totalAmount` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `rent_idx` (`rentId` ASC) VISIBLE,
  CONSTRAINT `rent`
    FOREIGN KEY (`rentId`)
    REFERENCES `mydb`.`Rent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PaymentTransaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PaymentTransaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cardId` INT NOT NULL,
  `invoiceId` INT NOT NULL,
  `command` VARCHAR(45) NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `content` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `invoice_idx` (`invoiceId` ASC) VISIBLE,
  INDEX `card_idx` (`cardId` ASC) VISIBLE,
  CONSTRAINT `invoice`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `mydb`.`Invoice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `card`
    FOREIGN KEY (`cardId`)
    REFERENCES `mydb`.`Card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;