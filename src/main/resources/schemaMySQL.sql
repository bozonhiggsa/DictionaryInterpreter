-- CREATE SCHEMA `dictionary` DEFAULT CHARACTER SET cp1251 ;

DROP DATABASE IF EXISTS dictionary;
CREATE DATABASE dictionary CHARACTER SET cp1251;
USE dictionary;

CREATE TABLE `dictionary`.`english_words` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(150) NOT NULL,
  `mark` TINYINT(1) NOT NULL,
  `done` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `wordEng_UNIQUE` (`word` ASC))
  CHARSET cp1251 COLLATE cp1251_general_ci;

CREATE TABLE `dictionary`.`russian_words` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `wordRus_UNIQUE` (`word` ASC))
  CHARSET cp1251 COLLATE cp1251_general_ci;

CREATE TABLE `dictionary`.`third_table` (
  `id_eng` INT NOT NULL,
  `id_rus` INT NOT NULL)
  CHARSET cp1251 COLLATE cp1251_general_ci;

ALTER TABLE `dictionary`.`third_table`
ADD INDEX `key_eng_idx` (`id_eng` ASC),
ADD INDEX `key_rus_idx` (`id_rus` ASC);
ALTER TABLE `dictionary`.`third_table`
ADD CONSTRAINT `key_eng`
FOREIGN KEY (`id_eng`)
REFERENCES `dictionary`.`english_words` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `key_rus`
FOREIGN KEY (`id_rus`)
REFERENCES `dictionary`.`russian_words` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;