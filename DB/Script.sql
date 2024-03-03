-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fitness
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fitness
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fitness` DEFAULT CHARACTER SET utf8 ;
USE `fitness` ;

-- -----------------------------------------------------
-- Table `fitness`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Admin` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `PasswordHash` VARCHAR(256) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Advisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Advisor` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `PasswordHash` VARCHAR(256) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Category` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`CategoryAttribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`CategoryAttribute` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  `CategoryID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CategoryAttribute_Category1_idx` (`CategoryID` ASC) VISIBLE,
  CONSTRAINT `fk_CategoryAttribute_Category1`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `fitness`.`Category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `PasswordHash` VARCHAR(256) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  `ActivationStatus` TINYINT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Program` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(1000) NOT NULL,
  `CategoryID` INT NOT NULL,
  `Price` DECIMAL NOT NULL,
  `Difficulty` VARCHAR(45) NOT NULL,
  `Duration` INT NOT NULL,
  `Location` VARCHAR(100) NOT NULL,
  `InstructorName` VARCHAR(45) NOT NULL,
  `InstructorSurname` VARCHAR(45) NOT NULL,
  `InstructorContact` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  `CreationDate` DATE NOT NULL,
  `UserID` INT NOT NULL,
  `Link` VARCHAR(256) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Program_Category1_idx` (`CategoryID` ASC) VISIBLE,
  INDEX `fk_Program_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Program_Category1`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `fitness`.`Category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Program_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Image` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ProgramID` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Size` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Image_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Image_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`Program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Participation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Participation` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NULL,
  `ProgramID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Participation_Program1_idx` (`ProgramID` ASC) VISIBLE,
  INDEX `fk_Participation_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Participation_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`Program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participation_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Comment` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Comment` VARCHAR(1000) NOT NULL,
  `Date` DATE NOT NULL,
  `UserID` INT NOT NULL,
  `ProgramID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Comment_User1_idx` (`UserID` ASC) VISIBLE,
  INDEX `fk_Comment_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`Program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Log` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(1000) NOT NULL,
  `Date` DATE NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Message` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Content` VARCHAR(1000) NOT NULL,
  `Date` DATE NULL,
  `Sender` INT NOT NULL,
  `Receiver` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Message_User1_idx` (`Sender` ASC) VISIBLE,
  INDEX `fk_Message_User2_idx` (`Receiver` ASC) VISIBLE,
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`Sender`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_User2`
    FOREIGN KEY (`Receiver`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Token` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Token` VARCHAR(150) NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Token_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Token_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Subscription` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CategoryID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Subscription_Category1_idx` (`CategoryID` ASC) VISIBLE,
  INDEX `fk_Subscription_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Subscription_Category1`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `fitness`.`Category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscription_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Question` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Content` VARCHAR(1000) NOT NULL,
  `Date` DATE NOT NULL,
  `UserID` INT NOT NULL,
  `ProgramID` INT NOT NULL,
  `Seen` TINYINT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Question_User1_idx` (`UserID` ASC) VISIBLE,
  INDEX `fk_Question_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Question_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Question_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`Program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Activity` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Sets` INT NOT NULL,
  `Reps` INT NOT NULL,
  `Weight` INT NOT NULL,
  `Status` TINYINT NOT NULL,
  `Date` DATE NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Activity_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Activity_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`BodyWeight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`BodyWeight` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Weight` DOUBLE NOT NULL,
  `Date` DATE NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_BodyWeight_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_BodyWeight_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Avatar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Avatar` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Size` BIGINT NOT NULL,
  INDEX `fk_Avatar_User1_idx` (`UserID` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_Avatar_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`Program_has_CategoryAttribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`Program_has_CategoryAttribute` (
  `ID` INT NOT NULL,
  `ProgramID` INT NOT NULL,
  `CategoryAttributeID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Program_has_CategoryAttribute_CategoryAttribute1_idx` (`CategoryAttributeID` ASC) VISIBLE,
  INDEX `fk_Program_has_CategoryAttribute_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Program_has_CategoryAttribute_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`Program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Program_has_CategoryAttribute_CategoryAttribute1`
    FOREIGN KEY (`CategoryAttributeID`)
    REFERENCES `fitness`.`CategoryAttribute` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
