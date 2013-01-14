-- DROP DATABASE IF EXISTS heroku_e314c3e5a6773c1;
-- CREATE DATABASE heroku_e314c3e5a6773c1;
-- USE heroku_e314c3e5a6773c1;
DROP TABLE IF EXISTS tour_programs;
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
) ;
create table clients (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	firstname varchar(30) not null,
   	lastname varchar(30) not null,
   	document1 varchar(80) not null,
   	document2 varchar(80) not null,
   	document3 varchar(80) not null,
   	document4 varchar(80) not null,
   	description text not null
) ;
create table discounts (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	threshold decimal(18,2) not null,
	percent int not null,	
	active boolean not null,

	CHECK (percent > 0),
	CHECK (threshold > 0)
) ;
create table tours (
	id INT AUTO_INCREMENT PRIMARY KEY,
   	name varchar(120) not null,
	transport_kind enum('BUS','AVIA','TRAIN'),
	travel_kind enum('REST','TOUR','SHOPPING'),
	description text not null,
	required_documents text not null,
	days_count int not null,
	
	CHECK(daysCount > 0) 
) ;
create table tourshedules (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_tour INT NOT NULL REFERENCES tours(id),
	date date NOT NULL,
	price decimal(18,2) NOT NULL,
	count int NOT NULL,

	CHECK (count > 0),
	CHECK (price > 0)
) ;
create table orders (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_client int not null REFERENCES clients(id),
	id_tourshedule int not null REFERENCES tourshedules(id),
	id_user int not null REFERENCES users(id), 
	date date not null,
	count int not null,
	total_price decimal(18,2) not null,
	description text not null, 
	finished boolean not null,
	finish_date date,

	CHECK (total_price > 0),
	CHECK (count > 0)
) ;

create table payments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_order int not null REFERENCES orders(id),
	amount decimal(18,2) not null,
	date date not null,

	CHECK (amount > 0)
) ;

create table tour_programs (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_tour int not null references tours(id),
	day_number int not null,
	description text not null,
	last_day_number int not null,

	CHECK (day_number > 0),
	CHECK (last_day_number >= 0),
	UNIQUE(id_tour,day_number)
) ;

describe users;
describe clients;
describe discounts;
describe tours;
describe tourshedules;
describe orders;
describe payments;
describe tour_programs;



