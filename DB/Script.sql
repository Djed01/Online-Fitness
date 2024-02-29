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
-- Table `fitness`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`admin` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `PasswordHash` VARCHAR(256) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`advisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`advisor` (
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
-- Table `fitness`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`category` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`categoryattribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`categoryattribute` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Status` TINYINT NOT NULL,
  `CategoryID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CategoryAttribute_Category1_idx` (`CategoryID` ASC) VISIBLE,
  CONSTRAINT `fk_CategoryAttribute_Category1`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `fitness`.`category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`user` (
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
-- Table `fitness`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`program` (
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
    REFERENCES `fitness`.`category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Program_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`image` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ProgramID` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Size` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Image_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Image_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`participation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`participation` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NULL,
  `ProgramID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Participation_Program1_idx` (`ProgramID` ASC) VISIBLE,
  INDEX `fk_Participation_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Participation_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participation_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`comment` (
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
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`log` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(1000) NOT NULL,
  `Date` DATE NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`message` (
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
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_User2`
    FOREIGN KEY (`Receiver`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`token` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Token` VARCHAR(150) NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Token_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Token_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`subscription` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CategoryID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Subscription_Category1_idx` (`CategoryID` ASC) VISIBLE,
  INDEX `fk_Subscription_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_Subscription_Category1`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `fitness`.`category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscription_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`question` (
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
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Question_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`activity` (
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
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`bodyweight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`bodyweight` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Weight` DOUBLE NOT NULL,
  `Date` DATE NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_BodyWeight_User1_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_BodyWeight_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`avatar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`avatar` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Size` BIGINT NOT NULL,
  INDEX `fk_Avatar_User1_idx` (`UserID` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_Avatar_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `fitness`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness`.`program_has_categoryattribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness`.`program_has_categoryattribute` (
  `ProgramID` INT NOT NULL,
  `CategoryAttributeID` INT NOT NULL,
  PRIMARY KEY (`ProgramID`, `CategoryAttributeID`),
  INDEX `fk_Program_has_CategoryAttribute_CategoryAttribute1_idx` (`CategoryAttributeID` ASC) VISIBLE,
  INDEX `fk_Program_has_CategoryAttribute_Program1_idx` (`ProgramID` ASC) VISIBLE,
  CONSTRAINT `fk_Program_has_CategoryAttribute_Program1`
    FOREIGN KEY (`ProgramID`)
    REFERENCES `fitness`.`program` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Program_has_CategoryAttribute_CategoryAttribute1`
    FOREIGN KEY (`CategoryAttributeID`)
    REFERENCES `fitness`.`categoryattribute` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
