drop database if exists autobase;
#creating tables

create database autobase;
use autobase;

create table Cars
(car_id INT PRIMARY KEY,
 model VARCHAR(30) NOT NULL,
 number VARCHAR(10) UNIQUE NOT NULL);

create table User_Types
(user_type_id INT PRIMARY KEY auto_increment,
 type_name VARCHAR(20) UNIQUE NOT NULL);

create table Request_Types
(request_type_id INT PRIMARY KEY auto_increment,
 type_name VARCHAR(20) UNIQUE NOT NULL);

create table Request_Statuses
(request_status_id INT PRIMARY KEY auto_increment,
 status_name VARCHAR(20) UNIQUE NOT NULL);

create table Users
(user_id INT PRIMARY KEY,
 user_type_id INT NOT NULL,
 name VARCHAR(30) UNIQUE NOT NULL,
 password VARCHAR(30),
 suspended BOOL default 0,
 FOREIGN KEY(`user_type_id`) REFERENCES User_Types(user_type_id)
);

create table Requests
(request_id INT PRIMARY KEY,
 request_type_id INT NOT NULL,
 request_status_id INT NOT NULL,
 start_time timestamp,
 end_time timestamp,
 car_id INT NOT NULL,
 driver_id INT NOT NULL,
 car_state VARCHAR(50) NOT NULL,
 FOREIGN KEY(`driver_id`) REFERENCES Users(user_id),
 FOREIGN KEY(`car_id`) REFERENCES Cars(car_id),
 FOREIGN KEY(`driver_id`) REFERENCES Request_Statuses(request_status_id),
 FOREIGN KEY(`request_type_id`) REFERENCES Request_Types(request_type_id)
);

#inserting data

INSERT INTO Cars VALUES(1, 'zaporozec', '1113');
INSERT INTO Cars VALUES(2, 'ziguli', '2233');
INSERT INTO Cars VALUES(3, 'nissan almera', '1122');
INSERT INTO Cars VALUES(4, 'audi a5', '4422');
INSERT INTO Cars VALUES(5, 'ziguli', '1144');


INSERT INTO user_types VALUES(null, 'driver');
INSERT INTO user_types VALUES(null, 'dispatcher');
INSERT INTO user_types VALUES(null, 'admin');

INSERT INTO Request_Types VALUES(null, 'race');
INSERT INTO Request_Types VALUES(null, 'repair');


INSERT INTO Request_Statuses VALUES(null, 'COMPLETED');
INSERT INTO Request_Statuses VALUES(null, 'ACTIVE');
INSERT INTO Request_Statuses VALUES(null, 'CANCELED');

INSERT INTO Users VALUES (1, 3, 'root', 'root', 0);
INSERT INTO Users VALUES (2, 1, 'driver1', 'pass', 0);
INSERT INTO Users VALUES (3, 1, 'driver2', 'pass', 1);
INSERT INTO Users VALUES (4, 2, 'dispatcher1', 'pass', 1);
INSERT INTO Users VALUES (5, 2, 'dispatcher2', 'pass', 0);

INSERT INTO Requests VALUES (1, 1, 1,'2010-10-01 15:10:00', '2010-10-01 16:10:00', 1, 3, 'good');
INSERT INTO Requests VALUES (2, 1, 1,'2010-10-01 15:10:00', '2010-10-01 16:10:00', 2, 2, 'good');
INSERT INTO Requests VALUES (3, 2, 2,'2010-10-01 15:10:00', null,  4, 3, 'car crash, repair wheel');
INSERT INTO Requests VALUES (4, 2, 2,'2010-10-01 16:10:00', null, 3, 2, 'car crash, replace engine');
INSERT INTO Requests VALUES (5, 2, 3,'2010-10-01 17:10:00', null, 5, 3, 'good');