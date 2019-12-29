-- 创建用户表
CREATE TABLE `user` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR ( 32 ) NOT NULL COMMENT '用户名称',
`birthday` DATETIME DEFAULT NULL COMMENT '生日',
`sex` CHAR ( 1 ) DEFAULT NULL COMMENT '性别',
`address` VARCHAR ( 256 ) DEFAULT NULL COMMENT '地址',
PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 插入用户表
INSERT INTO `user`(`id`,`username`,`birthday`,`sex`,`address`) VALUES(20,'小明','1996-01-01 15:00:00','男','北京'),
(21,'小红','1997-01-01 15:00:00','女','深圳'),
(22,'小李','1998-01-01 15:00:00','男','上海');
