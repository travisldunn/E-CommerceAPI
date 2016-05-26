-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mcdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mcdb` ;

-- -----------------------------------------------------
-- Schema mcdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mcdb` DEFAULT CHARACTER SET utf8 ;
USE `mcdb` ;

-- -----------------------------------------------------
-- Table `mcdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT 'home',
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_address_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_address_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mcdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`employees` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `bio` VARCHAR(200) NULL DEFAULT NULL,
  `admin` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `catagory` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(45,0) NULL DEFAULT NULL,
  `description` VARCHAR(95) NULL DEFAULT NULL,
  `brand` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`review` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `rating` INT(11) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `review` VARCHAR(95) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `product_id`, `user_id`),
  INDEX `fk_review_products1_idx` (`product_id` ASC),
  INDEX `fk_review_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_review_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `mcdb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mcdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`shoppingcart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`shoppingcart` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT 'shopping',
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_shoppingCart_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_shoppingCart_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mcdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`shoppingcartitems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mcdb`.`shoppingcartitems` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `shoppingCart_id` INT(11) NOT NULL,
  `products_id` INT(11) NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shoppingCart_has_products_products1_idx` (`products_id` ASC),
  INDEX `fk_shoppingCart_has_products_shoppingCart1_idx` (`shoppingCart_id` ASC),
  CONSTRAINT `fk_shoppingCart_has_products_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `mcdb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shoppingCart_has_products_shoppingCart1`
    FOREIGN KEY (`shoppingCart_id`)
    REFERENCES `mcdb`.`shoppingcart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
