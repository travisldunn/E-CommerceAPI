-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema companydb
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
DROP TABLE IF EXISTS `mcdb`.`users` ;

CREATE TABLE IF NOT EXISTS `mcdb`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mcdb`.`address` ;

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
DROP TABLE IF EXISTS `mcdb`.`employees` ;

CREATE TABLE IF NOT EXISTS `mcdb`.`employees` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `bio` VARCHAR(200) NULL DEFAULT NULL,
  `pictures` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mcdb`.`products` ;

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
DROP TABLE IF EXISTS `mcdb`.`review` ;

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
DROP TABLE IF EXISTS `mcdb`.`shoppingcart` ;

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
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mcdb`.`shoppingcartitems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mcdb`.`shoppingcartitems` ;

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
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mcdb`.`employees`
-- -----------------------------------------------------
START TRANSACTION;
USE `mcdb`;
INSERT INTO `mcdb`.`employees` (`id`, `name`, `title`, `email`, `bio`, `pictures`) VALUES (1, 'Erika Miller', 'CFO', 'ErikaM@gmail.com', 'Erika graduated form Harvard in 2000 with a B.S in business and majoring in accounting. She began her career in the accounting department of  Umbrella Corporation. After years of experience she was promoted to a Director role.', '../Pictures/Alex.jpg');
INSERT INTO `mcdb`.`employees` (`id`, `name`, `title`, `email`, `bio`, `pictures`) VALUES (2, 'Peter Chestnut', 'CEO', 'PChestnut@gmail.com', 'As CEO Peter is responsible for running all facets of the business.His experience as CEO and CFO of SwiftOlo and WhiteCloud respectively, lend him a proven executive track record.  Peter also served as President of Globex Inc.\'s e-commerce division and was recognized by Internet World as one of the Top 25 \'Click and Mortar\' executives in the country in June of 2000.', '../Pictures/David.jpg');
INSERT INTO `mcdb`.`employees` (`id`, `name`, `title`, `email`, `bio`, `pictures`) VALUES (3, 'Michael James', 'Director of Sales', 'MJames@gmail.com', 'After attending the University of Texas, Michael excelled in a sales role for Initech. He then ascended into a managerial role and increasing sales in his division drastically. In 2013 he was given the role of Director of Sales and was nominated for Forbes ‘Top 25 Under 25’.', '../Pictures/Erika.jpg');
INSERT INTO `mcdb`.`employees` (`id`, `name`, `title`, `email`, `bio`, `pictures`) VALUES (4, 'Alexandra Bennett', 'Marketing Director', 'AlexandraB@gmail.com', 'With over 20 years experience in international product sales, and applying a strong expertise in data-driven, long-term strategic planning, Alexandra excels in her role as Marketing director. Previous to this, Alexandra held various positions as Director of International Sales & Marketing, Manager of Business Development, and Trade Sales Manager for St. Anky Beer.', '../Pictures/Michael.jpg');
INSERT INTO `mcdb`.`employees` (`id`, `name`, `title`, `email`, `bio`, `pictures`) VALUES (5, 'David Silver', 'Founder', 'silverfox@gmail.com', 'A native of New York, David graduated from Yale University with an honors degree in  economic studies. David is a lifelong entrepreneur, adventurer, and tech aficionado. In 2014 he accepted the award for Most Innovative Startup, by the Inc 500. David is focused on bringing quality products as well as a streamlined experience to his customers. He is an avid traveler and aviation enthusiast, and has lived in Chili as well as Peru.', '../Pictures/Peter.jpg');

COMMIT;

