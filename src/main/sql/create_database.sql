CREATE DATABASE mypersonnalbankweb CHARACTER SET 'utf8';

use mypersonnalbankweb;

CREATE TABLE IF NOT EXISTS Account (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  number varchar(200) NOT NULL,
  value int(11) NOT NULL,
  PRIMARY KEY (id)
) engine=innodb;

CREATE TABLE IF NOT EXISTS Transaction (
  id int(11) NOT NULL AUTO_INCREMENT,
  transactionDate DATE NOT NULL,
  label varchar(200) NOT NULL,
  transactionType varchar(200) NOT NULL,
  value int(11) NOT NULL,
  idAccount int(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_TRANS_ACC FOREIGN KEY(idAccount) REFERENCES Account(id)
) engine=inno