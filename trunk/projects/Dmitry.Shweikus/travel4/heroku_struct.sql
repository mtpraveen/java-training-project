-- DROP DATABASE IF EXISTS heroku_e314c3e5a6773c1;
-- CREATE DATABASE heroku_e314c3e5a6773c1;
-- USE heroku_e314c3e5a6773c1;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS tourshedules;
DROP TABLE IF EXISTS tours;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS discounts;
DROP TABLE IF EXISTS clients;
create table users (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	name varchar(80) not null,
   	login varchar(80) not null,
   	password varchar(80) not null,
	admin boolean not null
) charset = "UTF8" engine = innodb;
create table clients (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	firstname varchar(30) not null,
   	lastname varchar(30) not null,
   	document1 varchar(80) not null,
   	document2 varchar(80) not null,
   	document3 varchar(80) not null,
   	document4 varchar(80) not null,
   	description text not null
) charset = "UTF8" engine = innodb;
create table discounts (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	threshold decimal(18,2) not null,
	percent int not null,	
	active boolean not null,

	CHECK (percent > 0),
	CHECK (threshold > 0)
) charset = "UTF8" engine = innodb;
create table tours (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	name varchar(120) not null,
	transportKind enum('BUS','AVIA','TRAIN'),
	travelKind enum('REST','TOUR','SHOPPING'),
	description text not null,
	requiredDocuments text not null,
	daysCount int not null,
	
	CHECK(daysCount > 0) 
) charset = "UTF8" engine = innodb;
create table tourshedules (
	id INT AUTO_INCREMENT PRIMARY KEY,
	tourId INT NOT NULL REFERENCES tours(id),
	date date NOT NULL,
	price decimal(18,2) NOT NULL,
	count int NOT NULL,

	CHECK (count > 0),
	CHECK (price > 0)
) charset = "UTF8" engine = innodb;
create table orders (
	id INT AUTO_INCREMENT PRIMARY KEY,
	clientId int not null REFERENCES clients(id),
	tourSheduleId int not null REFERENCES tourshedules(id),
	userId int not null REFERENCES users(id), 
	date date not null,
	count int not null,
	totalPrice decimal(18,2) not null,
	description text not null, 
	finished boolean not null,
	finishDate date,

	CHECK (total_price > 0),
	CHECK (count > 0)
) charset = "UTF8" engine = innodb;

create table payments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	orderId int not null REFERENCES orders(id),
	amount decimal(18,2) not null,
	date date not null,

	CHECK (amount > 0)
) charset = "UTF8" engine = innodb;

describe users;
describe clients;
describe discounts;
describe tours;
describe tourshedules;
describe orders;
describe payments;



