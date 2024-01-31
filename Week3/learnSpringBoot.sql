-- Active: 1705468269848@@127.0.0.1@3306@news
DROP DATABASE IF EXISTS learn_spring_boot;

CREATE DATABASE learn_spring_boot DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE learn_spring_boot;

CREATE TABLE `Press` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;