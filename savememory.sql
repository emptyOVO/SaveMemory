/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 8.0.31 : Database - savememory
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`savememory` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

/*Table structure for table `addresslist` */

CREATE TABLE `addresslist` (
  `aid` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `realname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `profile` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `addresslist` */

insert  into `addresslist`(`aid`,`uid`,`realname`,`nickname`,`phone`,`profile`) values (1,1,'gyf','ggg','132545654','sdzfasgr'),(170802916870219362,1708029168702193665,'hhh','jjj','1225426654','https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709554190516228097d7663986fe683e002fc85dc48b6aa35.png'),(1708771996826955777,1708029168702193665,'己土是','郑秀英','13543443526','https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp');

/*Table structure for table `adrtimeline` */

CREATE TABLE `adrtimeline` (
  `adrtlid` bigint NOT NULL,
  `aid` bigint DEFAULT NULL COMMENT '与uid不能同时为空',
  `uid` bigint DEFAULT NULL COMMENT '与aid不能同时为空',
  `update_at` datetime NOT NULL,
  `remark` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`adrtlid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `adrtimeline` */

insert  into `adrtimeline`(`adrtlid`,`aid`,`uid`,`update_at`,`remark`) values (1709560272856813569,1708771996826955777,NULL,'2023-10-04 13:25:06','commodo cillum eiusmod do'),(1709560723979374594,1708771996826955777,NULL,'2023-10-04 15:22:16','DSAFFA'),(1709590487565864962,NULL,1708029168702193666,'2023-10-04 15:25:10','minim nisi cillum ex');

/*Table structure for table `binding` */

CREATE TABLE `binding` (
  `bid` bigint NOT NULL,
  `child_id` bigint NOT NULL,
  `parent_id` bigint NOT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `binding` */

insert  into `binding`(`bid`,`child_id`,`parent_id`) values (1709192788190445570,1708029168702193666,1708029168702193665);

/*Table structure for table `diary` */

CREATE TABLE `diary` (
  `did` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `title` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `text` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `diary` */

insert  into `diary`(`did`,`uid`,`title`,`text`,`datetime`) values (1709230804388814850,1708029168702193665,'hha','sdadaw','2023-10-03 17:07:45');

/*Table structure for table `gameimage` */

CREATE TABLE `gameimage` (
  `gid` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `realname` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `gimage` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `gameimage` */

insert  into `gameimage`(`gid`,`uid`,`realname`,`nickname`,`gimage`) values (1710204607541567489,1708029168702193665,'dsafa','四大w','https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710204607541567490d7663986fe683e002fc85dc48b6aa35.png'),(1710204608397205506,1708029168702193665,'dsafa','四大w','https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710204608397205507lab1.png'),(1710204673538940929,1708029168702193665,'dsadfsdfd','dfsddd','https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710204673538940930d7663986fe683e002fc85dc48b6aa35.png'),(1710204674465882114,1708029168702193665,'dsadfsdfd','dfsddd','https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710204674465882115lab1.png');

/*Table structure for table `image` */

CREATE TABLE `image` (
  `imid` bigint NOT NULL,
  `belongs` enum('1','2','3','4','5','6') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '枚举值',
  `uid` bigint NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `datetime` datetime NOT NULL,
  `memory_id` bigint DEFAULT NULL COMMENT '可以为空',
  `diary_id` bigint DEFAULT NULL COMMENT '可以为空',
  `adrsl_id` bigint DEFAULT NULL COMMENT '可以为空',
  PRIMARY KEY (`imid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `image` */

insert  into `image`(`imid`,`belongs`,`uid`,`image`,`datetime`,`memory_id`,`diary_id`,`adrsl_id`) values (1708871970835222529,'6',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/17088719698495610906E427C52D0DA2268BAC1464B238ED95B.jpg','2023-10-02 15:50:02',NULL,NULL,NULL),(1709206480290332673,'6',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709206478482587649d7663986fe683e002fc85dc48b6aa35.png','2023-10-03 13:59:15',NULL,NULL,NULL),(1709229903997575169,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709229902223384577lab1.png','2023-10-03 15:32:20',NULL,1709229901959143425,NULL),(1709229904530251778,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709229903997575170lab1.png','2023-10-03 15:32:20',NULL,1709229901959143425,NULL),(1709229905192951809,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709229904597360642d7663986fe683e002fc85dc48b6aa35.png','2023-10-03 15:32:20',NULL,1709229901959143425,NULL),(1709253918392852482,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709253916950011905lab1.png','2023-10-03 17:07:45',NULL,1709230804388814850,NULL),(1709253918975860737,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709253918392852483lab1.png','2023-10-03 17:07:45',NULL,1709230804388814850,NULL),(1709253919697281026,'1',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709253918975860738d7663986fe683e002fc85dc48b6aa35.png','2023-10-03 17:07:46',NULL,1709230804388814850,NULL),(1709553942444118017,'5',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709553940913197058','2023-10-04 12:59:57',NULL,NULL,170802916870219362),(1709554191313145857,'5',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709554190516228097d7663986fe683e002fc85dc48b6aa35.png','2023-10-04 13:00:56',NULL,NULL,170802916870219362),(1710176416470933506,'6',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com1710176415279751169d7663986fe683e002fc85dc48b6aa35.png','2023-10-06 06:13:26',NULL,NULL,NULL),(1710176994206949377,'4',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710176993221287937lab1.png','2023-10-06 06:15:44',1709611264688263169,NULL,NULL),(1710176994794151937,'4',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710176994269863938lab1.png','2023-10-06 06:15:44',1709611264688263169,NULL,NULL),(1710176995641401345,'4',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710176994794151938d7663986fe683e002fc85dc48b6aa35.png','2023-10-06 06:15:44',1709611264688263169,NULL,NULL),(1710186384238583810,'3',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710186383382945793d7663986fe683e002fc85dc48b6aa35.png','2023-10-06 06:53:02',1709891277740412929,NULL,NULL),(1710186384775454721,'3',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710186384305692674lab1.png','2023-10-06 06:53:03',1709891277740412929,NULL,NULL),(1710186385358462978,'3',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1710186384838369282lab1.png','2023-10-06 06:53:03',1709891277740412929,NULL,NULL);

/*Table structure for table `memory` */

CREATE TABLE `memory` (
  `meid` bigint NOT NULL,
  `pid` bigint NOT NULL,
  `datetime` datetime NOT NULL,
  `uid` bigint NOT NULL,
  `profile` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `detail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`meid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `memory` */

insert  into `memory`(`meid`,`pid`,`datetime`,`uid`,`profile`,`create_time`,`detail`) values (1709611264688263169,1708029168702193665,'1997-05-16 22:20:42',1708029168702193665,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/1709206478482587649d7663986fe683e002fc85dc48b6aa35.png','2023-10-04 16:47:43','h9xbXYU'),(1709889788879933442,1708029168702193665,'2022-04-08 23:20:14',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/17088719698495610906E427C52D0DA2268BAC1464B238ED95B.jpg','2023-10-05 11:14:29','Fjzm'),(1709891277740412929,1708029168702193665,'1999-06-18 10:21:41',1708029168702193666,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/17088719698495610906E427C52D0DA2268BAC1464B238ED95B.jpg','2023-10-05 11:20:24','@string())pp');

/*Table structure for table `message` */

CREATE TABLE `message` (
  `msg_id` bigint NOT NULL,
  `from_user_id` bigint NOT NULL,
  `to_user_id` bigint NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `message` */

insert  into `message`(`msg_id`,`from_user_id`,`to_user_id`,`content`,`create_time`) values (1709449815068254209,1708029168702193665,1708029168702193666,'minim labore dolor','2023-10-04 06:06:11');

/*Table structure for table `todo` */

CREATE TABLE `todo` (
  `tid` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `todo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `done` tinyint(1) NOT NULL DEFAULT '0',
  `create_at` datetime NOT NULL,
  `title` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `todo` */

insert  into `todo`(`tid`,`uid`,`todo`,`done`,`create_at`,`title`) values (1708479500788031490,1708029168702193665,'不知道',1,'2023-10-01 13:50:30','dsadawd'),(1709571982888013826,1708029168702193665,'est aliquip',1,'2023-10-04 14:11:38','系己委眼');

/*Table structure for table `user` */

CREATE TABLE `user` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `realname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `id_number` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `blocked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '封号状态',
  `profile` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像图片',
  `identity` enum('0','1') COLLATE utf8mb4_general_ci NOT NULL COMMENT '0为父母1为子女',
  `bindphone` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '绑定联系人电话',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`nickname`,`password`,`phone`,`realname`,`id_number`,`register_time`,`blocked`,`profile`,`identity`,`bindphone`) values (1708029168702193665,'gaoyifei','feifei','$2a$10$U6dzmZnM2d6HfTgDA.QaXebcZlADKmPFbLrT3kOsNWBUOwZaEoDWS','15159524989','会它具器','350924200402100052','2023-09-30 08:01:02',0,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com1710176415279751169d7663986fe683e002fc85dc48b6aa35.png','0',''),(1708029168702193666,'gaogao','gaogao2','$2a$10$U6dzmZnM2d6HfTgDA.QaXebcZlADKmPFbLrT3kOsNWBUOwZaEoDWS','17750825182','高高','350924200402100053','2023-10-02 00:34:04',0,'https://savememory-1320078852.cos.ap-nanjing.myqcloud.com/images/17088719698495610906E427C52D0DA2268BAC1464B238ED95B.jpg','1','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
