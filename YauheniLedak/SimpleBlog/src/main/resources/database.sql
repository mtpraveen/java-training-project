DROP database IF EXISTS DB_BLOG ;

CREATE database IF NOT EXISTS `DB_BLOG` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE DB_BLOG;


CREATE  TABLE IF NOT EXISTS `DB_BLOG`.`T_USER` (

  `id` INT(11) NOT NULL AUTO_INCREMENT ,

  `login` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `role` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`id`) ,

  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci;


CREATE  TABLE IF NOT EXISTS `DB_BLOG`.`T_TOPIC` (

  `id` INT(11) NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(100) NULL DEFAULT NULL ,

  `text` VARCHAR(500) NULL DEFAULT NULL ,

  `commentsCount` INT(10) UNSIGNED ZEROFILL NULL DEFAULT NULL ,

  `timeLastUpdated` DATETIME NOT NULL ,
  
  `topicTag` VARCHAR(45) NULL DEFAULT NULL ,
  
  USER_ID int not null,
  
  foreign key (USER_ID) references T_USER(id),

  PRIMARY KEY (`id`) ,

  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci;


CREATE  TABLE IF NOT EXISTS `DB_BLOG`.`T_COMMENT` (

  `id` INT(11) NOT NULL AUTO_INCREMENT ,

  `text` VARCHAR(500) NOT NULL ,

  `creationDate` DATETIME NOT NULL ,

  TOPIC_ID int not null,
  
  USER_ID int not null,
  
  foreign key (TOPIC_ID) references T_TOPIC(id),
  
  foreign key (USER_ID) references T_USER(id),

  PRIMARY KEY (`id`), 

  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_general_ci;