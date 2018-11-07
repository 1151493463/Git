/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.17-log : Database - tesdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tesdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tesdb`;

/*Table structure for table `t_activity` */

DROP TABLE IF EXISTS `t_activity`;

CREATE TABLE `t_activity` (
  `activity_id` char(36) NOT NULL,
  `activity_title` varchar(50) DEFAULT NULL,
  `activity_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `longitude` double DEFAULT '0',
  `latitude` double DEFAULT '0',
  `activity_location` varchar(50) DEFAULT NULL,
  `activity_persons` int(11) DEFAULT NULL,
  `activity_cost` double DEFAULT NULL,
  `activity_picture` varchar(50) NOT NULL,
  `activity_details` varchar(500) NOT NULL,
  `activity_ispass` char(1) DEFAULT NULL,
  `user_id` char(36) NOT NULL,
  `course_id` char(36) NOT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `FK_activity_user_id` (`user_id`),
  KEY `FK_activity_courseid` (`course_id`),
  CONSTRAINT `FK_activity_courseid` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`),
  CONSTRAINT `FK_activity_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_activity` */

insert  into `t_activity`(`activity_id`,`activity_title`,`activity_date`,`longitude`,`latitude`,`activity_location`,`activity_persons`,`activity_cost`,`activity_picture`,`activity_details`,`activity_ispass`,`user_id`,`course_id`) values ('1b1d513c-4c49-4534-b6b1-095d0eaca0a8','参观故宫','2018-10-07 17:04:40',100.000001,100.000001,'北京市东城区',58,15800,'logo.png','增加团队凝聚力',NULL,'a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('5ef4795b-1b3f-417a-9c5d-623e608ec844','八达岭长城','2018-10-07 17:04:41',100.000001,100.000001,'北京市昌平区',58,15800,'logo.png','增加团队凝聚力',NULL,'a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('62844eb3-5e5e-4d41-bdd6-1a78ffa56a41','十度漂流','2018-10-07 17:04:42',100.000001,100.000001,'北京市房山区',58,15800,'logo.png','增加团队凝聚力',NULL,'a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('adb9b458-aabc-478b-9d8e-0997b828edeb','常营公园摄影','2018-10-07 17:04:43',100.000001,100.000001,'北京市朝阳区',58,15800,'logo.png','增加团队凝聚力',NULL,'a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('b7e8404e-f749-4861-922c-5a6b40ccb675','郊野露宿','2018-10-07 17:04:44',100.000001,100.000001,'北京市密云区',58,15800,'logo.png','增加团队凝聚力',NULL,'a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('d9758edb-ba5c-4ee0-9e23-5bb4e035701f','户外远足','2015-12-08 00:00:00',100.000001,100.000001,'北京市房山区',58,15800,'logo.png','增加团队凝聚力','否','a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('f1a17e98-31aa-4056-b45b-88452cddb565','长盈天街购物','2015-12-08 00:00:00',100.000001,100.000001,'北京市朝阳区',58,15800,'logo.png','增加团队凝聚力','否','a28e34eb-ec9d-43cd-a297-42abb14afcf9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f');

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `comment_id` char(36) NOT NULL,
  `comment_content` varchar(100) NOT NULL,
  `comment_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` char(36) NOT NULL,
  `video_id` char(36) NOT NULL,
  `course_id` char(36) NOT NULL,
  `comment_ispass` char(3) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_user_id1` (`user_id`),
  KEY `FK_video_id1` (`video_id`),
  KEY `FK_course_id1` (`course_id`),
  CONSTRAINT `FK_course_id1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`),
  CONSTRAINT `FK_user_id1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `FK_video_id1` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

insert  into `t_comment`(`comment_id`,`comment_content`,`comment_timestamp`,`user_id`,`video_id`,`course_id`,`comment_ispass`) values ('322605af-3507-41be-835b-cc93950caa2d','java讲的差','2015-07-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','04ad0719-2b0f-478c-9b04-11d0b15144c6','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','通过'),('463c34da-cb4c-45fb-94b0-83799b9d6ebc','web前端讲的很好2','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','39f5e9a8-4410-491f-83a4-5bce8f77087f','218969b9-4e21-484a-90fa-e9e683fc082c','通过'),('79ea14ec-d79c-410b-8552-5f06c8577cb6','linux讲的很好1','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','25dd35e8-4678-4eec-a7ac-77eff3af3478','48fab777-479c-4e15-84bb-f9a3ebc730b1','未通过'),('899ae23c-fe88-4796-9bd9-bc036ba10dc5','web前端讲的很好2','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','3129dc4a-5861-476b-a971-feebe4ceebbd','218969b9-4e21-484a-90fa-e9e683fc082c','通过'),('9bf4ec1a-ea25-499c-a276-21fda7931b33','java讲的很好1','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','1f0f8d64-5aed-41c9-b102-fecab6e24cca','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','未通过'),('b4b802a6-0d45-49c6-85c1-6c6825722f57','UI设计讲的很好1','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','1d7b41a0-ae54-41e1-be8b-a29bc1bfe4cb','690346e0-6d99-4caa-86dc-7e4e981a88b4','未审核'),('cf3ad103-1277-4b78-bb81-e015b209eb61','java讲的很好','2015-08-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','10c93b23-7558-4eb9-945a-4ffaacd0c8e9','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','未审核'),('e17e6b63-85a3-44df-99cd-8ebe5b9c61b9','web前端讲的很好','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','0f69cf5d-682d-4d49-8b63-fd9086580e6a','218969b9-4e21-484a-90fa-e9e683fc082c','未审核'),('eb0fb881-655f-4527-968f-e270c8ea3557','java讲的很好2','2015-12-08 00:00:00','a28e34eb-ec9d-43cd-a297-42abb14afcf9','34d2614c-95c2-4adc-994a-9d3bfb887afc','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','未审核');

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `course_id` char(36) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `course_picture` varchar(2000) DEFAULT NULL,
  `course_status` varchar(10) DEFAULT '上线',
  `course_onlinetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `course_order` int(11) DEFAULT NULL,
  `course_desc` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

insert  into `t_course`(`course_id`,`course_name`,`course_picture`,`course_status`,`course_onlinetime`,`course_order`,`course_desc`) values ('0a7a81aa-b5f1-43f9-ba92-7fcd22c4ff81','Android','/upload/image/course/0a7a81aa-b5f1-43f9-ba92-7fcd22c4ff81/0a7a81aa-b5f1-43f9-ba92-7fcd22c4ff8120181030211320.jpg','上线','2018-11-03 23:20:47',1001,'Android好'),('218969b9-4e21-484a-90fa-e9e683fc082c','web前端','/upload/image/course/218969b9-4e21-484a-90fa-e9e683fc082c/218969b9-4e21-484a-90fa-e9e683fc082c20181031194145.jpg','上线','2018-11-01 21:52:54',10,'web前端好'),('48fab777-479c-4e15-84bb-f9a3ebc730b1','Linux','/upload/image/course/48fab777-479c-4e15-84bb-f9a3ebc730b1/48fab777-479c-4e15-84bb-f9a3ebc730b120181105191230.jpg','上线','2018-11-05 19:12:32',321,'Linux好'),('5e7f24ae-6f44-4421-a172-4515734f808d','IOS','images/Java-evn.png','上线','2018-11-03 23:20:47',10,'IOS好'),('60d188f6-d351-4c7f-8600-8a97ee3ca37e','网络营销','images/Java-evn.png','上线','2018-10-30 22:09:33',10,'网络营销好'),('690346e0-6d99-4caa-86dc-7e4e981a88b4','UID','images/Java-evn.png','上线','2018-10-30 21:40:03',10,'UID好'),('8c2ded0e-0455-4631-a3c4-b3c50aeda12f','java','images/Java-evn.png','上线','2018-11-04 17:53:32',10,'java好'),('9a139993-83c2-4468-9bc1-2aa51b1d0518','测试课程','images/Java-evn.png','上线','2018-10-30 21:40:03',10,'C++好'),('a2f1d6b1-4ead-4a58-90bb-29931366605a','php','images/Java-evn.png','上线','2018-11-03 23:20:47',10,'php好'),('d5c805bb-8c08-4dca-bab1-fd9645fa14f4','.NET','images/Java-evn.png','上线','2018-10-30 21:40:03',10,'.NET好'),('d7ea5cc9-5cc2-4e02-8f05-b01cdf8e7307','测试','images/Java-evn.png','上线','2018-11-03 23:20:47',10,'测试好'),('e06776f3-e992-4624-b52a-0d117206a7bb','C++','images/Java-evn.png','上线','2018-10-30 21:40:03',10,'C++好'),('e7e22bcd-dc47-11e8-81f8-10c37bc0aec1','游戏开发','/upload/image/course/e7e22bcd-dc47-11e8-81f8-10c37bc0aec1/e7e22bcd-dc47-11e8-81f8-10c37bc0aec120181030234953.jpg','上线','2018-11-03 23:20:47',122,'good'),('e90dc6f4-fd08-430d-a402-d523a200c872','嵌入式','images/Java-evn.png','上线','2018-11-03 23:20:47',10,'嵌入式好'),('ed623d34-dc19-11e8-81f8-10c37bc0aec1','游戏开发','/upload/image/course/ed623d34-dc19-11e8-81f8-10c37bc0aec1/ed623d34-dc19-11e8-81f8-10c37bc0aec120181030160053.jpg','上线','2018-10-30 21:40:03',32,'good---good---good'),('f2f10842-d22b-4c2e-bfbd-f78202c37207','Unity3D','images/Java-evn.png','上线','2018-10-30 21:40:03',10,'Unity3D好');

/*Table structure for table `t_courseclassify` */

DROP TABLE IF EXISTS `t_courseclassify`;

CREATE TABLE `t_courseclassify` (
  `courseclassify_id` char(36) NOT NULL,
  `courseclassify_name` varchar(50) DEFAULT NULL,
  `courseclassify_details` varchar(200) NOT NULL,
  `course_id` char(36) NOT NULL,
  PRIMARY KEY (`courseclassify_id`),
  KEY `FK_courseclassify_course_id` (`course_id`),
  CONSTRAINT `FK_courseclassify_course_id` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_courseclassify` */

insert  into `t_courseclassify`(`courseclassify_id`,`courseclassify_name`,`courseclassify_details`,`course_id`) values ('58b9dc9e-1be6-4924-8bc0-714c74b3f24c','java基础','java基础部分的变量定义','8c2ded0e-0455-4631-a3c4-b3c50aeda12f'),('ef31cab0-d3f5-4c82-a0b1-8be4db6af3cb','java基础','java基础部分的环境搭建','8c2ded0e-0455-4631-a3c4-b3c50aeda12f');

/*Table structure for table `t_friendlist` */

DROP TABLE IF EXISTS `t_friendlist`;

CREATE TABLE `t_friendlist` (
  `friendlist_id` char(36) NOT NULL,
  `friendlist_details` text,
  `user_id` char(36) NOT NULL,
  PRIMARY KEY (`friendlist_id`),
  KEY `FK_friendlist_user_id` (`user_id`),
  CONSTRAINT `FK_friendlist_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_friendlist` */

/*Table structure for table `t_history_cache_collection_purchased` */

DROP TABLE IF EXISTS `t_history_cache_collection_purchased`;

CREATE TABLE `t_history_cache_collection_purchased` (
  `hccp_id` char(36) NOT NULL,
  `user_id` char(36) NOT NULL,
  `video_id` char(36) NOT NULL,
  `is_history` char(1) NOT NULL DEFAULT '否',
  `is_cache` char(1) NOT NULL DEFAULT '否',
  `is_collection` char(1) NOT NULL DEFAULT '否',
  `is_purchased` char(1) NOT NULL DEFAULT '否',
  PRIMARY KEY (`hccp_id`),
  KEY `FK_hbc_user_id` (`user_id`),
  KEY `FK_hbc_video_id` (`video_id`),
  CONSTRAINT `FK_hbc_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `FK_hbc_video_id` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_history_cache_collection_purchased` */

insert  into `t_history_cache_collection_purchased`(`hccp_id`,`user_id`,`video_id`,`is_history`,`is_cache`,`is_collection`,`is_purchased`) values ('2f5e0a21-a60f-4dd9-9c33-618614455360','a28e34eb-ec9d-43cd-a297-42abb14afcf9','04ad0719-2b0f-478c-9b04-11d0b15144c6','是','是','是','是'),('3c71d28e-433d-4607-a5f8-3db21435707e','a28e34eb-ec9d-43cd-a297-42abb14afcf9','0f69cf5d-682d-4d49-8b63-fd9086580e6a','是','是','是','是'),('e41895a5-b20a-4816-8201-a02b6ad068ef','a28e34eb-ec9d-43cd-a297-42abb14afcf9','10c93b23-7558-4eb9-945a-4ffaacd0c8e9','是','是','是','是');

/*Table structure for table `t_module` */

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `module_id` char(36) NOT NULL,
  `module_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_module` */

insert  into `t_module`(`module_id`,`module_name`) values ('10509c6a-d396-4523-848b-39dffd117bc0','活动管理'),('3d343346-3dd0-4313-8765-e91e9127b361','角色管理'),('7e58a067-47fb-478c-b39c-75bac06d6344','视频管理'),('7effb19e-af56-4d26-86ef-f64ee742b927','课程管理'),('a30e7a79-f504-4608-a5ca-7e734a332882','用户管理'),('bb2d5d18-133a-40c6-9e34-edc6f10c460d','评论管理');

/*Table structure for table `t_opinion` */

DROP TABLE IF EXISTS `t_opinion`;

CREATE TABLE `t_opinion` (
  `opinion_id` char(36) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `name` varchar(4) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`opinion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_opinion` */

insert  into `t_opinion`(`opinion_id`,`phone`,`name`,`content`) values ('c75c6501-e0e2-4414-86a3-ed1e72d53391','13800138000','王涛','视频讲的很好');

/*Table structure for table `t_participation` */

DROP TABLE IF EXISTS `t_participation`;

CREATE TABLE `t_participation` (
  `ptct_id` char(36) NOT NULL,
  `user_id` char(36) NOT NULL,
  `activity_id` char(36) NOT NULL,
  PRIMARY KEY (`ptct_id`),
  KEY `FK_participation_user_id` (`user_id`),
  KEY `FK_participation_activity_id` (`activity_id`),
  CONSTRAINT `FK_participation_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`activity_id`),
  CONSTRAINT `FK_participation_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_participation` */

insert  into `t_participation`(`ptct_id`,`user_id`,`activity_id`) values ('2c06ac7e-28bf-4b69-98ea-9ce169b32f28','ecf1311d-de45-4851-a20f-4e6cf55073f0','d9758edb-ba5c-4ee0-9e23-5bb4e035701f'),('6164cb10-1dd3-4692-ad6a-f67c3080a3a2','a28e34eb-ec9d-43cd-a297-42abb14afcf9','d9758edb-ba5c-4ee0-9e23-5bb4e035701f'),('83002185-8139-47a7-bf58-ffc01559be60','a28e34eb-ec9d-43cd-a297-42abb14afcf9','b7e8404e-f749-4861-922c-5a6b40ccb675'),('f81f3a33-12a1-4964-875c-79d91c513984','ecf1311d-de45-4851-a20f-4e6cf55073f0','b7e8404e-f749-4861-922c-5a6b40ccb675');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` char(36) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`role_id`,`role_name`) values ('52ce347d-e0eb-11e8-87b1-10c37bc0aec1','New Role'),('7cc9a30c-e7d7-41c8-a1d4-00465f88bb92','讲师'),('8fa1e074-6719-48df-97e9-981741dc354e','评论管理员'),('d2cd243c-c670-4f69-98fd-292b41e37625','超级管理员'),('f4bb6184-6aa9-40eb-a37d-ff82a8769cce','学员'),('fbbb1722-7205-477c-9d9d-fe33293cd133','活动管理员');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` char(36) NOT NULL,
  `user_loginname` varchar(20) NOT NULL,
  `user_logintype` varchar(20) DEFAULT NULL,
  `user_nickname` varchar(50) DEFAULT NULL,
  `user_password` varchar(32) NOT NULL,
  `user_type` int(11) NOT NULL DEFAULT '0',
  `user_head` varchar(2000) DEFAULT 'images/boy-head.png',
  `user_score` int(11) NOT NULL DEFAULT '0',
  `user_islock` char(1) NOT NULL DEFAULT '否',
  `user_pwdstate` varchar(32) DEFAULT '',
  `user_regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_age` int(11) DEFAULT '0',
  `user_sex` char(1) DEFAULT '男',
  `user_activecode` varchar(4) DEFAULT NULL,
  `user_introduction` varchar(500) DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`user_loginname`,`user_logintype`,`user_nickname`,`user_password`,`user_type`,`user_head`,`user_score`,`user_islock`,`user_pwdstate`,`user_regdate`,`user_age`,`user_sex`,`user_activecode`,`user_introduction`) values ('065a89c3-ed45-44d2-ac97-b947c7ed4613','dfdddds@qq.com','邮箱','jjjj','ree',0,NULL,23,'是','','2018-11-01 01:13:27',23,'男',NULL,'yyyy'),('10bf2a84-4bf6-40b1-8c85-e625bdb1e938','cc@126.com','邮箱','知行ee','123',2,'images/boy-head.png',44,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('2f4d4388-da87-11e8-894d-10c37bc0aec1','hahhah@qq.com','邮箱','jjj','1123',0,'images/gril-head.png',0,'是','','2018-10-28 15:57:58',0,'女',NULL,''),('3ad1188d-1f98-4b92-aa34-c4d58105485f','18409417389','手机','知行1','123',2,'/upload/image/user/3ad1188d-1f98-4b92-aa34-c4d58105485f/3ad1188d-1f98-4b92-aa34-c4d58105485f20181030112210.jpg',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('46238b68-6bef-491b-b5af-a3e0f45d1462','bb@126.com','邮箱','知行','123',2,'/upload/image/user/46238b68-6bef-491b-b5af-a3e0f45d1462/46238b68-6bef-491b-b5af-a3e0f45d146220181105191019.jpg',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('56e414c8-2d11-499c-9183-3ff307789c40','dfds@qq.com','邮箱','jjjj','ree',0,NULL,23,'是','','2018-11-01 01:08:59',23,'男',NULL,'yyyy'),('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','wt_zss@126.com','邮箱','知行','123',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('72f3cd3d-1bcb-4713-9984-ff9c6b685d37','dfdsfff@qq.com','邮箱','jjjj','ree',0,NULL,23,'是','','2018-11-01 00:43:36',23,'男',NULL,'yyyy'),('76e17374-da88-11e8-894d-10c37bc0aec1','hah@.com','邮箱','jjj','1123',0,'images/gril-head.png',0,'否','','2018-10-28 16:07:07',32,'女',NULL,''),('848cb1bb-7d19-4efc-a127-9200f0fde25a','12373844475','手机','ds','ds',0,'images/boy-head.png',0,'否','','2018-10-09 19:14:52',32,'男',NULL,''),('84e23a78-da87-11e8-894d-10c37bc0aec1','hahhah@.com','邮箱','jjj','1123',0,'images/gril-head.png',0,'否','','2018-10-28 16:00:21',32,'女',NULL,''),('8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec1','erewff@qq.com','邮箱','hahh','321',0,'/upload/image/user/8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec1/8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec120181105191132.jpg',0,'否','','2018-11-05 19:11:32',21,'女',NULL,''),('983513c5-4460-44ca-afc7-7eabd6f7b35b','hh@126.com','邮箱','知行hh','123',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('98b6ef19-e101-4496-a7a7-434023a96f88','18238272765','手机','trt','yyy',0,'images/boy-head.png',0,'否','','2018-10-09 19:17:59',43,'男',NULL,''),('9e04f4de-b63b-45d2-b78a-4ec591f6c098','ewew@123.com','邮箱','eew','ew',0,'images/boy-head.png',0,'否','','2018-10-08 10:51:37',32,'男',NULL,''),('a1937dd1-39b7-4f87-9413-2c15a77708da','dfdjhiosd@qq.com','邮箱','ds','ds',0,'images/boy-head.png',0,'否','','2018-10-08 18:01:39',32,'男',NULL,''),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','admin@tedu.cn','邮箱','管理员','admin4mysql',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('a3c6c37f-3e7c-4bb9-ba8f-42c6d58f9224','eeedss@qq.com','邮箱','dsds1','fds',0,'images/boy-head.png',0,'否','','2018-10-04 00:04:24',22,'男',NULL,''),('a7f4af8f-e0da-11e8-87b1-10c37bc0aec1','123eurdxad@qq.com','邮箱','哈哈哈','123.0',0,NULL,0,'否','','2018-11-05 17:10:36',21,'男',NULL,''),('ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec1','18233983893','手机','yyyy','ree',0,'/upload/image/user/ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec1/ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec120181101140510.jpeg',0,'否','','2018-11-01 13:54:59',23,'男',NULL,''),('ad0e7678-39a4-46d5-9946-f3dd5100bc62','ff@126.com','邮箱','知行ff','123',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('be22feba-db16-11e8-894d-10c37bc0aec1','18409317836','手机','px','123',0,'/upload/image/user/be22feba-db16-11e8-894d-10c37bc0aec1/be22feba-db16-11e8-894d-10c37bc0aec120181029204159.jpg',0,'否','','2018-10-29 09:05:33',23,'男',NULL,''),('bf397f3e-dd9e-11e8-a8ec-10c37bc0aec1','helloword@123.com','邮箱','yyyy','ree',0,NULL,0,'否','','2018-11-01 14:24:09',23,'男',NULL,''),('c2fcfe86-6db7-46b2-bbbd-43690c7017b3','aa@126.com','邮箱','知行','123',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('c36af784-d33f-48f7-bcb4-737ec6fd4052','ee@126.com','邮箱','知行ee','123',2,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('ddc8ed1c-784e-4c62-a3dc-ccd21479cbe0','hahaha@126.com','邮箱','fd','ddd',0,'images/boy-head.png',0,'否','','2018-10-05 11:17:28',322,'男',NULL,''),('ecf1311d-de45-4851-a20f-4e6cf55073f0','13800138000','手机','行知','123',1,'images/boy-head.png',0,'否','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('f2ca3738-de69-4762-a9f0-7cb477e9ce21','gg@126.com','邮箱','知行gg','123',2,'images/boy-head.png',0,'是','','2018-09-27 13:42:19',30,'男',NULL,'金牌'),('f2ca3738-de69-4762-a9f0-7cb477e9ce22','1475819314@qq.com',NULL,'夜~星空','321',1,'images/boy-head.png',100,'否','','2018-11-05 11:18:05',23,'男','53PZ',''),('fce9ab46-e0eb-11e8-87b1-10c37bc0aec1','hdhs@qq.com','邮箱','哈哈哈','123.0',0,'images/boy-head.png',0,'否','','2018-11-05 19:14:39',21,'男',NULL,'');

/*Table structure for table `t_user_module` */

DROP TABLE IF EXISTS `t_user_module`;

CREATE TABLE `t_user_module` (
  `uid` char(36) NOT NULL,
  `mid` char(36) NOT NULL,
  PRIMARY KEY (`uid`,`mid`),
  KEY `FK_user_module_mid` (`mid`),
  CONSTRAINT `FK_user_module_mid` FOREIGN KEY (`mid`) REFERENCES `t_module` (`module_id`),
  CONSTRAINT `FK_user_module_uid` FOREIGN KEY (`uid`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_module` */

insert  into `t_user_module`(`uid`,`mid`) values ('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','10509c6a-d396-4523-848b-39dffd117bc0'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','10509c6a-d396-4523-848b-39dffd117bc0'),('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','3d343346-3dd0-4313-8765-e91e9127b361'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','3d343346-3dd0-4313-8765-e91e9127b361'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','7e58a067-47fb-478c-b39c-75bac06d6344'),('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','7effb19e-af56-4d26-86ef-f64ee742b927'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','7effb19e-af56-4d26-86ef-f64ee742b927'),('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','a30e7a79-f504-4608-a5ca-7e734a332882'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','a30e7a79-f504-4608-a5ca-7e734a332882'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','bb2d5d18-133a-40c6-9e34-edc6f10c460d');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `uid` char(36) NOT NULL,
  `rid` char(36) NOT NULL,
  PRIMARY KEY (`uid`,`rid`),
  KEY `FK_user_role_rid` (`rid`),
  CONSTRAINT `FK_user_role_rid` FOREIGN KEY (`rid`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `FK_user_role_uid` FOREIGN KEY (`uid`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`uid`,`rid`) values ('46238b68-6bef-491b-b5af-a3e0f45d1462','52ce347d-e0eb-11e8-87b1-10c37bc0aec1'),('10bf2a84-4bf6-40b1-8c85-e625bdb1e938','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('2f4d4388-da87-11e8-894d-10c37bc0aec1','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec1','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('a3c6c37f-3e7c-4bb9-ba8f-42c6d58f9224','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('be22feba-db16-11e8-894d-10c37bc0aec1','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('ddc8ed1c-784e-4c62-a3dc-ccd21479cbe0','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('ecf1311d-de45-4851-a20f-4e6cf55073f0','7cc9a30c-e7d7-41c8-a1d4-00465f88bb92'),('10bf2a84-4bf6-40b1-8c85-e625bdb1e938','8fa1e074-6719-48df-97e9-981741dc354e'),('2f4d4388-da87-11e8-894d-10c37bc0aec1','8fa1e074-6719-48df-97e9-981741dc354e'),('3ad1188d-1f98-4b92-aa34-c4d58105485f','8fa1e074-6719-48df-97e9-981741dc354e'),('848cb1bb-7d19-4efc-a127-9200f0fde25a','8fa1e074-6719-48df-97e9-981741dc354e'),('8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec1','8fa1e074-6719-48df-97e9-981741dc354e'),('a1937dd1-39b7-4f87-9413-2c15a77708da','8fa1e074-6719-48df-97e9-981741dc354e'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','8fa1e074-6719-48df-97e9-981741dc354e'),('ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec1','8fa1e074-6719-48df-97e9-981741dc354e'),('be22feba-db16-11e8-894d-10c37bc0aec1','8fa1e074-6719-48df-97e9-981741dc354e'),('bf397f3e-dd9e-11e8-a8ec-10c37bc0aec1','8fa1e074-6719-48df-97e9-981741dc354e'),('ddc8ed1c-784e-4c62-a3dc-ccd21479cbe0','8fa1e074-6719-48df-97e9-981741dc354e'),('2f4d4388-da87-11e8-894d-10c37bc0aec1','d2cd243c-c670-4f69-98fd-292b41e37625'),('3ad1188d-1f98-4b92-aa34-c4d58105485f','d2cd243c-c670-4f69-98fd-292b41e37625'),('8d6c8e5e-e0eb-11e8-87b1-10c37bc0aec1','d2cd243c-c670-4f69-98fd-292b41e37625'),('98b6ef19-e101-4496-a7a7-434023a96f88','d2cd243c-c670-4f69-98fd-292b41e37625'),('9e04f4de-b63b-45d2-b78a-4ec591f6c098','d2cd243c-c670-4f69-98fd-292b41e37625'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','d2cd243c-c670-4f69-98fd-292b41e37625'),('ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec1','d2cd243c-c670-4f69-98fd-292b41e37625'),('be22feba-db16-11e8-894d-10c37bc0aec1','d2cd243c-c670-4f69-98fd-292b41e37625'),('10bf2a84-4bf6-40b1-8c85-e625bdb1e938','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('3ad1188d-1f98-4b92-aa34-c4d58105485f','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('46238b68-6bef-491b-b5af-a3e0f45d1462','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('7008ffa6-e01d-48ed-a460-dbf2a4908bfa','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('983513c5-4460-44ca-afc7-7eabd6f7b35b','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('a7f4af8f-e0da-11e8-87b1-10c37bc0aec1','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('ad0e7678-39a4-46d5-9946-f3dd5100bc62','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('bf397f3e-dd9e-11e8-a8ec-10c37bc0aec1','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('c2fcfe86-6db7-46b2-bbbd-43690c7017b3','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('c36af784-d33f-48f7-bcb4-737ec6fd4052','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('f2ca3738-de69-4762-a9f0-7cb477e9ce21','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('fce9ab46-e0eb-11e8-87b1-10c37bc0aec1','f4bb6184-6aa9-40eb-a37d-ff82a8769cce'),('a28e34eb-ec9d-43cd-a297-42abb14afcf9','fbbb1722-7205-477c-9d9d-fe33293cd133'),('ac6b4b25-dd9a-11e8-a8ec-10c37bc0aec1','fbbb1722-7205-477c-9d9d-fe33293cd133'),('f2ca3738-de69-4762-a9f0-7cb477e9ce22','fbbb1722-7205-477c-9d9d-fe33293cd133');

/*Table structure for table `t_video` */

DROP TABLE IF EXISTS `t_video`;

CREATE TABLE `t_video` (
  `video_id` char(36) NOT NULL,
  `video_title` varchar(50) NOT NULL,
  `video_special` int(11) NOT NULL DEFAULT '1',
  `video_forsale` char(1) NOT NULL DEFAULT '否',
  `course_id` char(36) NOT NULL,
  `user_id` char(36) NOT NULL,
  `video_click_count` bigint(20) DEFAULT '0',
  `video_introduction` varchar(500) DEFAULT NULL,
  `video_picture` varchar(100) DEFAULT 'default.png',
  `video_picture_cc` varchar(200) DEFAULT NULL,
  `video_filename` varchar(200) DEFAULT NULL,
  `video_url_cc` varchar(500) DEFAULT NULL,
  `video_state` int(11) DEFAULT '0',
  `video_ontime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `video_difficulty` int(11) DEFAULT '0',
  `md5` varchar(50) DEFAULT NULL,
  `video_tag` varchar(50) DEFAULT NULL,
  `category_id` char(36) DEFAULT NULL,
  `video_filesize` bigint(20) DEFAULT NULL,
  `metaurl` varchar(80) DEFAULT NULL,
  `chunkurl` varchar(80) DEFAULT NULL,
  `ccvid` varchar(50) DEFAULT NULL,
  `servicetype` varchar(30) DEFAULT NULL,
  `video_ispass` varchar(10) DEFAULT '否',
  PRIMARY KEY (`video_id`),
  KEY `FK_course_id` (`course_id`),
  KEY `FK_user_id` (`user_id`),
  CONSTRAINT `FK_course_id` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_video` */

insert  into `t_video`(`video_id`,`video_title`,`video_special`,`video_forsale`,`course_id`,`user_id`,`video_click_count`,`video_introduction`,`video_picture`,`video_picture_cc`,`video_filename`,`video_url_cc`,`video_state`,`video_ontime`,`video_difficulty`,`md5`,`video_tag`,`category_id`,`video_filesize`,`metaurl`,`chunkurl`,`ccvid`,`servicetype`,`video_ispass`) values ('04ad0719-2b0f-478c-9b04-11d0b15144c6','java运算符和表达式代码演示',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',9,'讲解java运算符和表达式代码演示','images/video/java2.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/2F8572BDF3136F6C9C33DC5901307461-0.jpg','04ad0719-2b0f-478c-9b04-11d0b15144c6.mp4','resources/video/love.mp4',6,'2016-06-16 16:51:25',0,'ee119f491c56771ce2566188c44e14d5','Java基础',NULL,2682864,'http://1.18.vacombiner.bokecc.com/servlet/uploadmeta','http://1.18.vacombiner.bokecc.com/servlet/uploadchunk','2F8572BDF3136F6C9C33DC5901307461','DF0236B91AECD81C','通过'),('0f69cf5d-682d-4d49-8b63-fd9086580e6a','web前端jquery概念介绍1(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',75,'讲解web前端jquery概念介绍1','images/video/web1.jpg',NULL,'0f69cf5d-682d-4d49-8b63-fd9086580e6a.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:06:46',0,'bd5bed2cce0ce5ecdf1a264ec696887d','web前端',NULL,2114016,NULL,NULL,NULL,NULL,'未通过'),('10c93b23-7558-4eb9-945a-4ffaacd0c8e9','java开发环境(offline)',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java开发环境','images/video/java4.png',NULL,'10c93b23-7558-4eb9-945a-4ffaacd0c8e9.mp4','resources/video/love.mp4',2,'2016-06-17 11:32:45',0,'388bfb2ede611b20846c441354da14d7','java基础',NULL,3029579,NULL,NULL,NULL,NULL,'通过'),('1d7b41a0-ae54-41e1-be8b-a29bc1bfe4cb','UI服装设计演示过程2(offline)',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程2','images/video/ui.jpg',NULL,'1d7b41a0-ae54-41e1-be8b-a29bc1bfe4cb.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:13:31',0,'0c229273386a0f3213c600f2ca9f6e71','UI基础',NULL,2982470,NULL,NULL,NULL,NULL,'通过'),('1f0f8d64-5aed-41c9-b102-fecab6e24cca','java简介',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java简介','images/video/java3.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/BE8CF77C2F8AB6229C33DC5901307461-0.jpg','1f0f8d64-5aed-41c9-b102-fecab6e24cca.mp4','resources/video/love.mp4',6,'2016-06-16 15:34:16',0,'4c06b8ec142022cc4d772b48c866ffa0','Java基础',NULL,7234678,'http://2.18.vacombiner.bokecc.com/servlet/uploadmeta','http://2.18.vacombiner.bokecc.com/servlet/uploadchunk','BE8CF77C2F8AB6229C33DC5901307461','DF0236B91AECD81C','通过'),('25dd35e8-4678-4eec-a7ac-77eff3af3478','linux常见的软件封装包类型1',1,'否','48fab777-479c-4e15-84bb-f9a3ebc730b1','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解linux常见的软件封装包类型1','images/video/Linux1.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/CABD1130D5523D359C33DC5901307461-0.jpg','25dd35e8-4678-4eec-a7ac-77eff3af3478.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:19:08',0,'05c478aabcf88809fd95a2b7728d942d','UI基础',NULL,5271113,'http://5.15.vacombiner.bokecc.com/servlet/uploadmeta','http://5.15.vacombiner.bokecc.com/servlet/uploadchunk','CABD1130D5523D359C33DC5901307461','DF0236B91AECD81C','通过'),('3129dc4a-5861-476b-a971-feebe4ceebbd','web前端jquery概念介绍2',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',17,'讲解web前端jquery概念介绍2','images/video/web2.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/376304D2E5C8B81F9C33DC5901307461-0.jpg','3129dc4a-5861-476b-a971-feebe4ceebbd.mp4','resources/video/love.mp4',6,'2016-06-17 10:27:10',0,'bd9e68e5f21248d18d8aee07d4369112','web前端',NULL,2101115,'http://4.15.vacombiner.bokecc.com/servlet/uploadmeta','http://4.15.vacombiner.bokecc.com/servlet/uploadchunk','376304D2E5C8B81F9C33DC5901307461','DF0236B91AECD81C','通过'),('34d2614c-95c2-4adc-994a-9d3bfb887afc','java简介(offline)',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java简介','images/video/java1.png','fdfd','34d2614c-95c2-4adc-994a-9d3bfb887afc.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:34:04',0,'4c06b8ec142022cc4d772b48c866ffa0','java基础',NULL,7234678,NULL,NULL,NULL,NULL,'通过'),('39f5e9a8-4410-491f-83a4-5bce8f77087f','web前端什么是jquery(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',6,'讲解web前端什么是jquery','images/video/web3.png','fjdfdjfdjoi','39f5e9a8-4410-491f-83a4-5bce8f77087f.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:00:26',0,'cf2c3ed383082b41e4bc1aca3a4638dd','web前端',NULL,2110217,NULL,NULL,NULL,NULL,'通过'),('463f4abf-8edb-4cdd-9b82-77e7eb9ce051','java运算符和表达式',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',1,'讲解java运算符和表达式','images/video/java5.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/2CA324D65D9253129C33DC5901307461-0.jpg','463f4abf-8edb-4cdd-9b82-77e7eb9ce051.mp4','resources/video/love.mp4',6,'2016-06-16 16:50:29',0,'371ce6b11bf5bce3ffde0ce1d916cac2','Java基础',NULL,3851241,'http://5.15.vacombiner.bokecc.com/servlet/uploadmeta','http://5.15.vacombiner.bokecc.com/servlet/uploadchunk','2CA324D65D9253129C33DC5901307461','DF0236B91AECD81C','通过'),('56fd63cb-10b0-4f30-90d2-fbc32528f5a3','web前端jquery概念介绍1',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',3,'讲解web前端jquery概念介绍1','images/video/web4.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/3BA1F4972A978C8C9C33DC5901307461-0.jpg','56fd63cb-10b0-4f30-90d2-fbc32528f5a3.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:23:40',0,'bd5bed2cce0ce5ecdf1a264ec696887d','web前端',NULL,2114016,'http://1.18.vacombiner.bokecc.com/servlet/uploadmeta','http://1.18.vacombiner.bokecc.com/servlet/uploadchunk','3BA1F4972A978C8C9C33DC5901307461','DF0236B91AECD81C','通过'),('5d0d1555-7203-4989-ad11-0d8008c3998f','web前端jquery入门',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',5,'讲解web前端jquery入门','images/video/web5.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/64412F31495F67709C33DC5901307461-0.jpg','5d0d1555-7203-4989-ad11-0d8008c3998f.mp4','resources/video/love.mp4',6,'2016-06-17 10:31:06',0,'079e5ec1fc20018e7a4ec07ba229c85e','web前端',NULL,3657125,'http://1.18.vacombiner.bokecc.com/servlet/uploadmeta','http://1.18.vacombiner.bokecc.com/servlet/uploadchunk','64412F31495F67709C33DC5901307461','DF0236B91AECD81C','通过'),('5fdc2f3e-a054-4897-a41b-4b1d40fdfe18','web前段jquery发展史',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',2,'讲解web前段jquery发展史','images/video/web6.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/E7A198770BBB0CA09C33DC5901307461-0.jpg','5fdc2f3e-a054-4897-a41b-4b1d40fdfe18.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:33:52',0,'6118d8a95ab08141dea195e928d240bb','web前端',NULL,2248162,'http://2.15.vacombiner.bokecc.com/servlet/uploadmeta','http://2.15.vacombiner.bokecc.com/servlet/uploadchunk','E7A198770BBB0CA09C33DC5901307461','DF0236B91AECD81C','通过'),('6a85b9e3-99e5-4493-9a9b-bbf4a0a3e483','UI服装设计',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计','images/video/ui2.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/8ED09B0077C7AA4C9C33DC5901307461-0.jpg','6a85b9e3-99e5-4493-9a9b-bbf4a0a3e483.mp4','resources/video/love.mp4',6,'2016-06-16 17:34:17',0,'3b37a09f85dc14fbfcc860e43a8f3f71','UI基础',NULL,3051740,'http://3.15.vacombiner.bokecc.com/servlet/uploadmeta','http://3.15.vacombiner.bokecc.com/servlet/uploadchunk','8ED09B0077C7AA4C9C33DC5901307461','DF0236B91AECD81C','通过'),('6b38aff8-ddd7-4058-bfdf-032c5c8356a8','web前端什么是jquery',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',2,'讲解web前端什么是jquery','images/video/web7.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/2247E80CC9F42E9C9C33DC5901307461-0.jpg','6b38aff8-ddd7-4058-bfdf-032c5c8356a8.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:31:51',0,'cf2c3ed383082b41e4bc1aca3a4638dd','web前端',NULL,2110217,'http://5.15.vacombiner.bokecc.com/servlet/uploadmeta','http://5.15.vacombiner.bokecc.com/servlet/uploadchunk','2247E80CC9F42E9C9C33DC5901307461','DF0236B91AECD81C','通过'),('73550069-c53a-43ac-b721-50daabee6390','web前端jquery概念介绍3(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',1,'讲解web前端jquery概念介绍3','images/video/web8.png',NULL,'73550069-c53a-43ac-b721-50daabee6390.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:04:33',0,'81e7f8286c28cc79d11950b5f19a7ea5','web前端',NULL,2790112,NULL,NULL,NULL,NULL,'通过'),('78d5fa43-7640-4349-83e6-00127f1d6a2c','java运算符和表达式代码演示(offline)',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java运算符和表达式代码演示','images/video/java6.png',NULL,'78d5fa43-7640-4349-83e6-00127f1d6a2c.mp4','resources/video/love.mp4',2,'2016-06-17 11:29:27',0,'ee119f491c56771ce2566188c44e14d5','java基础',NULL,2682864,NULL,NULL,NULL,NULL,'通过'),('7cc8ced1-aed9-49d7-be5f-e45520c8e9a9','linux常见的软件封装包类型(offline)',1,'否','48fab777-479c-4e15-84bb-f9a3ebc730b1','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解linux常见的软件封装包类型','images/video/Linux2.png',NULL,'7cc8ced1-aed9-49d7-be5f-e45520c8e9a9.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:27:18',0,'27094eed595062d5f8474fc9e6337b1a','linux基础',NULL,2214380,NULL,NULL,NULL,NULL,'通过'),('8033bfc1-2dfc-4092-8720-8e9e704e1107','IOS通过图片集创建lable(offline)',1,'否','5e7f24ae-6f44-4421-a172-4515734f808d','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解IOS通过图片集创建lable','images/video/ios1.png',NULL,'8033bfc1-2dfc-4092-8720-8e9e704e1107.mp4','resources/video/love.mp4',2,'2016-06-17 11:42:04',0,'5db31028150a9a93882cffe0bb03550a','IOS基础',NULL,4906480,NULL,NULL,NULL,NULL,'通过'),('819f279c-752c-4583-a3d2-37bdf35f61c8','UI服装设计演示过程2',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程2','images/video/ui3.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/00ED2EF873DEAC539C33DC5901307461-0.jpg','819f279c-752c-4583-a3d2-37bdf35f61c8.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:12:18',0,'0c229273386a0f3213c600f2ca9f6e71','UI基础',NULL,2982470,'http://2.18.vacombiner.bokecc.com/servlet/uploadmeta','http://2.18.vacombiner.bokecc.com/servlet/uploadchunk','00ED2EF873DEAC539C33DC5901307461','DF0236B91AECD81C','通过'),('84c08f1b-fee9-4f2c-bf9a-421e14ad57b8','UI服装设计演示过程1(offline)',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程1','images/video/ui4.png',NULL,'84c08f1b-fee9-4f2c-bf9a-421e14ad57b8.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:14:30',0,'a091d82cc720ce3c51c8c706b7c57c1d','UI基础',NULL,3410310,NULL,NULL,NULL,NULL,'通过'),('9043b2f8-4117-4ee9-b2f1-3c2477826844','IOS通过图片集创建lable代码演示(offline)',1,'否','5e7f24ae-6f44-4421-a172-4515734f808d','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解IOS通过图片集创建lable代码演示','images/video/ios2.png',NULL,'9043b2f8-4117-4ee9-b2f1-3c2477826844.mp4','resources/video/love.mp4',2,'2016-06-17 11:39:56',0,'225eaa0035682b8e2e6c61d1eec011ba','IOS基础',NULL,7585161,NULL,NULL,NULL,NULL,'通过'),('95789b42-ee1e-4c2f-9c28-3231b143ea66','UI服装设计演示过程1',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程1','images/video/ui5.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/EB8690F36730EC9B9C33DC5901307461-0.jpg','95789b42-ee1e-4c2f-9c28-3231b143ea66.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-16 17:36:18',0,'a091d82cc720ce3c51c8c706b7c57c1d','UI基础',NULL,3410310,'http://2.15.vacombiner.bokecc.com/servlet/uploadmeta','http://2.15.vacombiner.bokecc.com/servlet/uploadchunk','EB8690F36730EC9B9C33DC5901307461','DF0236B91AECD81C','通过'),('a17497ef-2de8-401a-ba25-31ebd5dac0d4','UI服装设计演示过程3',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程3','images/video/ui6.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/4887369C391C21139C33DC5901307461-0.jpg','a17497ef-2de8-401a-ba25-31ebd5dac0d4.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-16 17:46:47',0,'76132be3718f29c97924ae7067a81944','UI基础',NULL,3076215,'http://1.18.vacombiner.bokecc.com/servlet/uploadmeta','http://1.18.vacombiner.bokecc.com/servlet/uploadchunk','4887369C391C21139C33DC5901307461','DF0236B91AECD81C','通过'),('a33aa29b-b6f7-489f-a921-b89bb9ac222e','IOS通过图片集创建lable代码演示',1,'否','5e7f24ae-6f44-4421-a172-4515734f808d','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解IOS通过图片集创建lable代码演示','images/video/ios3.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/408B299BB0ADB6DC9C33DC5901307461-0.jpg','a33aa29b-b6f7-489f-a921-b89bb9ac222e.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-16 17:05:24',0,'225eaa0035682b8e2e6c61d1eec011ba','IOS基础',NULL,7585161,'http://1.15.vacombiner.bokecc.com/servlet/uploadmeta','http://1.15.vacombiner.bokecc.com/servlet/uploadchunk','408B299BB0ADB6DC9C33DC5901307461','DF0236B91AECD81C','通过'),('a5f11b53-6561-40be-bc03-fdddc982a7e4','java开发环境',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','9e04f4de-b63b-45d2-b78a-4ec591f6c098',0,'讲解java开发环境','images/video/java7.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/02057F2F8A793F6C9C33DC5901307461-0.jpg','a5f11b53-6561-40be-bc03-fdddc982a7e4.mp4','resources/video/love.mp4',6,'2016-06-16 16:48:51',0,'388bfb2ede611b20846c441354da14d7','Java基础',NULL,3029579,'http://2.18.vacombiner.bokecc.com/servlet/uploadmeta','http://2.18.vacombiner.bokecc.com/servlet/uploadchunk','02057F2F8A793F6C9C33DC5901307461','DF0236B91AECD81C','通过'),('aa80ae56-f7b4-4b26-8e56-170687d998b0','UI服装设计演示过程3(offline)',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计演示过程3','images/video/ui7.png',NULL,'aa80ae56-f7b4-4b26-8e56-170687d998b0.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:11:50',0,'76132be3718f29c97924ae7067a81944','UI基础',NULL,3076215,NULL,NULL,NULL,NULL,'通过'),('b95a7733-494e-4573-bb72-19df992de092','IOS通过图片集创建lable',1,'否','5e7f24ae-6f44-4421-a172-4515734f808d','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解IOS通过图片集创建lable','images/video/ios4.png ','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/1AD1E291868D9C4F9C33DC5901307461-0.jpg','b95a7733-494e-4573-bb72-19df992de092.mp4','resources/video/love.mp4',6,'2016-06-16 16:53:45',0,'5db31028150a9a93882cffe0bb03550a','IOS基础',NULL,4906480,'http://1.18.vacombiner.bokecc.com/servlet/uploadmeta','http://1.18.vacombiner.bokecc.com/servlet/uploadchunk','1AD1E291868D9C4F9C33DC5901307461','DF0236B91AECD81C','通过'),('ba74ad10-4baf-4d0a-abae-76e9d1756412','web前端jquery概念介绍2(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',2,'讲解web前端jquery概念介绍2','images/video/web9.png',NULL,'ba74ad10-4baf-4d0a-abae-76e9d1756412.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:05:44',0,'bd9e68e5f21248d18d8aee07d4369112','web前端',NULL,2101115,NULL,NULL,NULL,NULL,'通过'),('be9d362c-8560-44c3-bd12-023d407c3aa5','java变量(offline)',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java变量','images/video/java8.png',NULL,'be9d362c-8560-44c3-bd12-023d407c3aa5.mp4','resources/video/love.mp4',2,'2016-06-17 11:35:22',0,'9ebcd7e28102b28a044d8432b6b47f7f','java基础',NULL,8001466,NULL,NULL,NULL,NULL,'通过'),('ca5a4e33-1a01-421f-b763-d950e3d85f46','web前段jquery发展史(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解web前段jquery发展史','images/video/web10.png',NULL,'ca5a4e33-1a01-421f-b763-d950e3d85f46.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 10:53:28',0,'6118d8a95ab08141dea195e928d240bb','web前端',NULL,2248162,NULL,NULL,NULL,NULL,'通过'),('d37dac3a-6003-4d98-8ea3-46d6600f0f28','linux常见的软件封装包类型1(offline)',1,'否','48fab777-479c-4e15-84bb-f9a3ebc730b1','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解linux常见的软件封装包类型1','images/video/Linux3.png',NULL,'d37dac3a-6003-4d98-8ea3-46d6600f0f28.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:25:15',0,'05c478aabcf88809fd95a2b7728d942d','linux基础',NULL,5271113,NULL,NULL,NULL,NULL,'通过'),('d4a090b9-d4e8-4737-8cfe-07c0bc83ae4c','web前端jquery入门(offline)',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解web前端jquery入门','images/video/web11.png',NULL,'d4a090b9-d4e8-4737-8cfe-07c0bc83ae4c.mp4','resources/video/love.mp4',2,'2016-06-17 11:02:41',0,'079e5ec1fc20018e7a4ec07ba229c85e','web前端',NULL,3657125,NULL,NULL,NULL,NULL,'通过'),('d5f9b7a2-acd2-49db-8ba2-5318f44ff9f5','web前端jquery概念介绍3',1,'否','218969b9-4e21-484a-90fa-e9e683fc082c','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解web前端jquery概念介绍3','images/video/web12.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-17/0384AC474ED494FF9C33DC5901307461-0.jpg','d5f9b7a2-acd2-49db-8ba2-5318f44ff9f5.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-17 10:29:43',0,'81e7f8286c28cc79d11950b5f19a7ea5','web前端',NULL,2790112,'http://3.15.vacombiner.bokecc.com/servlet/uploadmeta','http://3.15.vacombiner.bokecc.com/servlet/uploadchunk','0384AC474ED494FF9C33DC5901307461','DF0236B91AECD81C','通过'),('db9e0866-f9a2-467d-994c-9f30f1625496','java变量',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java变量','images/video/java9.jpg','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/A8431B4E352F0F9B9C33DC5901307461-0.jpg','db9e0866-f9a2-467d-994c-9f30f1625496.mp4','resources/video/夺魂之镰.mkv',6,'2016-06-16 16:45:23',0,'9ebcd7e28102b28a044d8432b6b47f7f','Java基础',NULL,8001466,'http://5.15.vacombiner.bokecc.com/servlet/uploadmeta','http://5.15.vacombiner.bokecc.com/servlet/uploadchunk','A8431B4E352F0F9B9C33DC5901307461','DF0236B91AECD81C','通过'),('dcdd965a-8561-45fb-93a5-79722a3a521a','UI服装设计(offline)',1,'否','690346e0-6d99-4caa-86dc-7e4e981a88b4','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解UI服装设计','images/video/ui8.png',NULL,'dcdd965a-8561-45fb-93a5-79722a3a521a.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:20:07',0,'3b37a09f85dc14fbfcc860e43a8f3f71','UI基础',NULL,3051740,NULL,NULL,NULL,NULL,'通过'),('dd85c336-69a2-420d-b955-2338e5be103d','linux常见的软件封装包类型',1,'否','48fab777-479c-4e15-84bb-f9a3ebc730b1','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解linux常见的软件封装包类型','images/video/Linux4.png','http://3.img.bokecc.com/comimage/0DD1F081022C163E/2016-06-16/B977D57E1C2071A59C33DC5901307461-0.jpg','dd85c336-69a2-420d-b955-2338e5be103d.mp4','resources/video/love.mp4',6,'2016-06-16 17:23:36',0,'27094eed595062d5f8474fc9e6337b1a','linux基础',NULL,2214380,'http://2.18.vacombiner.bokecc.com/servlet/uploadmeta','http://2.18.vacombiner.bokecc.com/servlet/uploadchunk','B977D57E1C2071A59C33DC5901307461','DF0236B91AECD81C','通过'),('e1b27864-e0df-41be-9f8c-dc7e5a42db16','java运算符和表达式(offline)',1,'否','8c2ded0e-0455-4631-a3c4-b3c50aeda12f','a28e34eb-ec9d-43cd-a297-42abb14afcf9',0,'讲解java运算符和表达式','images/video/java10.png',NULL,'e1b27864-e0df-41be-9f8c-dc7e5a42db16.mp4','resources/video/夺魂之镰.mkv',2,'2016-06-17 11:31:02',0,'371ce6b11bf5bce3ffde0ce1d916cac2','java基础',NULL,3851241,NULL,NULL,NULL,NULL,'通过');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
