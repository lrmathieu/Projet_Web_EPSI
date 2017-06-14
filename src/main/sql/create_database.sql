CREATE DATABASE mypersonnalbankweb CHARACTER SET 'utf8';

use mypersonnalbankweb;

CREATE TABLE IF NOT EXISTS Account (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  number VARCHAR(50) NOT NULL,
  value INT NOT NULL
) engine=innodb;

CREATE TABLE IF NOT EXISTS Transaction (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  dateTransaction DATE NOT NULL,
  label VARCHAR(50) NOT NULL,
  transactionType VARCHAR(200) NOT NULL,
  value INT NOT NULL,
  idAccount INT NOT NULL,
  CONSTRAINT FK_TRANS_ACC FOREIGN KEY(idAccount) REFERENCES Account(id)
) engine=innodb;