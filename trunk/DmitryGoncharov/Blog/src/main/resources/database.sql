DROP DATABASE IF EXISTS epam;
CREATE DATABASE epam;
USE epam;

CREATE TABLE `user`
(
   `id`           int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `USER_LOGIN`   varchar(45) NOT NULL,
   `USER_PASS`    varchar(45),
   `USER_NAME`    varchar(45)
);

CREATE TABLE `blog`
(
   `id`        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `USER_ID`   int NOT NULL,
   FOREIGN KEY(`USER_ID`) REFERENCES `user`(`id`)
);

CREATE TABLE `topic`
(
   `id`              int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `TOPIC_CAPTION`   varchar(20) DEFAULT NULL,
   `TOPIC_TEXT`      varchar(255)
);

INSERT INTO user
VALUES (NULL,
        'pret',
        '123456',
        'Dima');

INSERT INTO user
VALUES (NULL,
        'test',
        'test',
        'test');

SELECT * FROM user;