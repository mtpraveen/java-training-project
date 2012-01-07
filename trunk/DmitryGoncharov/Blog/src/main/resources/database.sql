drop database IF EXISTS epam;
CREATE DATABASE epam;
USE epam;

CREATE TABLE blog(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE user(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_login varchar(45) NOT NULL,
    user_pass varchar(45),
    user_name varchar(45),
    blog_id int NOT NULL,
    FOREIGN KEY (blog_id)
        REFERENCES blog (id)
);

CREATE TABLE topic(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    topic_caption varchar(20) DEFAULT NULL,
    topic_text varchar(255),
    blog_id int NOT NULL,
    FOREIGN KEY (blog_id)
        REFERENCES blog (id)
);

CREATE TABLE comment(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    comment_name varchar(20) DEFAULT NULL,
    comment_text varchar(255),
    comment_date date NOT NULL,
    topic_id int NOT NULL,
    FOREIGN KEY (topic_id)
        REFERENCES topic (id)
);


INSERT INTO user
VALUES (NULL,
        'pret',
        '123456',
        'Dima',
        '1');

INSERT INTO user
VALUES (NULL,
        'test',
        'test',
        'test',
        '2');
INSERT INTO blog
VALUES (1);
INSERT INTO blog
VALUES (2);
INSERT INTO `epam`.`topic` (`id`, `topic_caption`, `topic_text`, `blog_id`) VALUES (1, 'topic_caption', 'topic_text', 1);

