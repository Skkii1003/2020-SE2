drop database if exists courselearning;
create database if not exists `courselearning` default charset utf8;

use `courselearning`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `delete_time` datetime DEFAULT NULL,
  `cost` int NOT NULL,
  `teacher_id` int NOT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_course` (`teacher_id`),
  CONSTRAINT `fk_user_course` FOREIGN KEY (`teacher_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '软件工程与计算 I', '中级', '通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式', 'course1.png', '南京大学', '2020-12-20 10:00:00', null, '1', 1, '刘钦');
INSERT INTO `course` VALUES ('2', '软件工程与计算 II', '中级', '基于DevOps培养团队开发中小规模软件系统的能力', 'course2.png', '南京大学', '2020-12-20 10:00:00', null, '1', 1, '刘钦');
INSERT INTO `course` VALUES ('3', '程序猿与攻城狮', '初级', '我们将由5位老师从不同的角度来说说他们对软件工程的理解。我们并非试图去覆盖所有软件工程的知识点，我们只是希望去让对软件工程有兴趣的你们了解到“我要学什么”“我应如何学”“我今后能做什么”这样一些基本的问题的部分答案。\r\n\r\n   刘钦老师会介绍软件工程的历史和软件工程建模；丁二玉老师会谈谈软件工程师和软件工程团队；冯桂焕老师会通过案例让大家体验人机交互的魅力；陈振宇老师会从概率统计讲到软件测试；刘嘉老师会娓娓道来他对最热门的移动互联网发展的本质的理解。', 'course3.png', '南京大学', '2020-12-20 10:00:00', null, '1', 1, '刘钦');
INSERT INTO `course` VALUES ('4', '基于Java的面向对象编程范式', '初级', '本课程完成了编程思维、结构化编程范式、面向对象编程方式的介绍，并且着重强调了面向对象编程中封装、协作和可修改性三个重要概念。通过客观题和6道编程题的训练，进一步加深对面向对象编程范式的理解，初步领略面向对象编程范式的风采。', 'course4.png', '南京大学', '2020-12-20 10:00:00', null, '1', 1, '刘钦');
INSERT INTO `course` VALUES ('5', '移动互联网软件工程', '高级', '本课程结合软件工程方法学，从移动互联网需求出发，结合现有的互联网企业产品架构分析，探讨移动互联网软件开发的各种关注点、应用架构基础。', 'no.png', '南京大学', '2020-12-20 10:00:00', null, '1', 1, '刘钦');

-- ----------------------------
-- Table structure for `course_order`
-- ----------------------------
DROP TABLE IF EXISTS `course_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cost` int NOT NULL,
  `course_id` int NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_order` (`course_id`),
  CONSTRAINT `fk_course_order` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  KEY `fk_user_order` (`user_id`),
  CONSTRAINT `fk_user_order` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `course_ware`
-- ----------------------------
DROP TABLE IF EXISTS `course_ware`;
CREATE TABLE `course_ware` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `number` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `file_size` varchar(255) DEFAULT NULL,
  `free_flag` tinyint(1) NOT NULL DEFAULT '0',
  `download_flag` tinyint(1) NOT NULL DEFAULT '1',
  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_course_ware` (`course_id`),
  CONSTRAINT `fk_course_ware` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------
INSERT INTO `course_ware` VALUES ('2', '1', '1', '00 - Overview', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('3', '1', '2', '01 - 编程语言概述', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('4', '1', '3', '02 - 编程基础 I-分解与抽象', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('5', '1', '4', '03 - 编程基础 II-可计算性', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('6', '1', '5', '04 - 编程基础 III-Lambda演算', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('7', '1', '6', '05 - 编程基础 IV-函数式编程范式', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('8', '1', '7', '06 - 编程基础 V-习题课', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('9', '1', '8', '07 - 结构化编程 I - 思想', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('10', '1', '9', '08 - 结构化编程 II - 变量', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('11', '1', '10', '09 - 结构化编程 III - 方法', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('12', '1', '11', '10 - 面向对象编程 I-思想', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('13', '1', '12', '11 - 面向对象编程 II-封装', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('14', '1', '13', '12 - 面向对象编程 III-协作', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('15', '1', '14', '13 - 软件工程建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('16', '1', '15', '14 - JVM和字节码基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('17', '1', '16', '15 - 面向对象编程III-继承与多态', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('18', '1', '17', '16 - 面向对象编程 IV-可修改性', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('19', '1', '18', '17 - 面向对象编程 V-接口', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('20', '1', '19', '18 - 异常', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('21', '1', '20', '19 - Network and Thread', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('22', '1', '21', '20 - Graphical User Interface', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('23', '1', '22', 'Git介绍', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('24', '1', '23', 'Java编程基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('25', '1', '24', 'Lab01-环境配置', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('26', '1', '25', 'Lab02-Seec系统', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('27', '1', '26', 'Lab03-简单分支和循环的训练', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('28', '1', '27', 'Lab04-Anagram', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('29', '1', '28', 'Lab05-arrival_of_general', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('30', '1', '29', 'Lab06-Taxi', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('31', '1', '30', 'Programming-01-Basics', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('32', '1', '31', 'Programming-02-Variable & Type', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('33', '1', '32', 'Programming-03-Control Flow', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('34', '1', '33', 'Programming-04-Method', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('35', '1', '34', 'Programming-05-Module', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('36', '1', '35', 'Programming-06-Composite Data', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('37', '1', '36', 'Programming-07-Input & Output', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('38', '1', '37', 'Programming-08-Lambda and Functional Programming', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('39', '1', '38', 'Programming-09-Simulation and Calculation', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('40', '1', '39', 'Python-01-HelloWorld和Variable', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('41', '1', '40', 'Python-02-控制流', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('42', '1', '41', 'Python-03-函数', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('43', '1', '42', 'Python-04-模块', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('44', '1', '43', 'Python-05-复合数据', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('45', '1', '44', 'Python-06-输入输出', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('46', '1', '45', 'Python-07-Lambda and Functional Programming', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('47', '1', '46', 'Reference-01-Byte-of-Python', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('48', '1', '47', 'Reference-02-Turing, His Machine and Computability', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('49', '1', '48', 'Reference-03-Register Machines are Turing Machines', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('50', '1', '49', 'Reference-04-Lambda', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('51', '1', '50', 'Z总结', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('52', '1', '51', '谷歌软件工程', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('53', '1', '52', '软件工程守破离', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('54', '2', '1', '00-Overview', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('55', '2', '2', '01-软件工程基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('56', '2', '3', '02-软件工程的发展', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('57', '2', '4', '04-项目启动', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('58', '2', '5', '05-需求基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('59', '2', '6', '06-需求分析方法', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('60', '2', '7', '07-需求文档化与验证', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('61', '2', '8', '08-软件设计基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('62', '2', '9', '09-软件体系结构基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('63', '2', '10', '10-软件体系结构设计与构建', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('64', '2', '11', '11-人机交互', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('65', '2', '12', '12-详细设计', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('66', '2', '13', '13-模块化与信息隐藏', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('67', '2', '14', '14-面向对象的模块化', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('68', '2', '15', '15-面向对象的信息隐藏', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('69', '2', '16', '16-设计模式', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('70', '2', '17', '16工厂设计模式', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('71', '2', '18', '16设计模式练习', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('72', '2', '19', '17-软件构造', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('73', '2', '20', '18-代码设计', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('74', '2', '21', '19-软件测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('75', '2', '22', '20-软件交付', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('76', '2', '23', '21-软件维护与演化', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('77', '2', '24', '22-软件开发过程模型', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('78', '2', '25', '23-软件工程职业基础', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('79', '2', '26', 'Homework01-软工二大作业迭代一功能需求简述', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('80', '2', '27', 'Ref-01-SEEC系统课程与小组说明', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('81', '2', '28', 'Ref-02-代码作业使用说明', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('82', '2', '29', 'Review01-用例文档点评', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('83', '2', '30', 'Review02-需求规格点评', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('84', '2', '31', 'Review03-体系结构', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('85', '2', '32', 'Web开发-01-浏览器的渲染原理', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('86', '2', '33', 'Web开发-02-HTTP协议原理', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('87', '2', '34', 'Web开发-03-Tomcat和Servlet原理', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('88', '2', '35', 'Web开发-04-REST和JSON', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('89', '2', '36', 'Web开发-05-Springboot入门', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('90', '2', '37', 'Web开发-06-GUI入门', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('91', '2', '38', 'Web开发-07-数据设计', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('92', '2', '39', 'Web开发-08-MVC-MVP-MVVM', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('93', '2', '40', 'Web开发-09-Vue实战--前端', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('94', '2', '41', 'Web开发-10-Springboot+Mybatis--后端', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('95', '2', '42', '复习提纲', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('96', '3', '1', '软件工程历史和软件工程建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('97', '3', '2', '软件工程历史', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('98', '3', '3', '数学和计算机建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('99', '3', '4', '软件工程建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('100', '3', '5', '编程思想', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('101', '3', '6', '测试一', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('102', '3', '7', '软件工程师和软件工程团队', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('103', '3', '8', '软件工程师是做什么的 上集', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('104', '3', '9', '软件工程师是做什么的 下集', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('105', '3', '10', '软件工程团队的三驾马车', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('106', '3', '11', '软件工程团队的其他重要角色', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('107', '3', '12', '测试二', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('108', '3', '13', '人机交互', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('109', '3', '14', '人机交互概述', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('110', '3', '15', '启发式设计原则', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('111', '3', '16', '人机交互的软件工程方法—— 启发式评估', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('112', '3', '17', '测试三', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('113', '3', '18', '概率统计到软件测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('114', '3', '19', '数字与计算', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('115', '3', '20', '计算与概率', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('116', '3', '21', '概率与测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('117', '3', '22', '测试与生活', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('118', '3', '23', '生活与数字', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('119', '3', '24', '测试四', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('120', '3', '25', '移动互联网本质', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('121', '3', '26', '连接', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('122', '3', '27', '时间', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('123', '3', '28', '未来', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('124', '3', '29', '本质', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('125', '4', '1', '1 - 编程之前', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('126', '4', '2', '1-01-科学思维vs工程思维', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('127', '4', '3', '1-02-数学建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('128', '4', '4', '1-03-计算机建模 1', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('129', '4', '5', '1-04-计算机建模 2', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('130', '4', '6', '1-05-软件工程建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('131', '4', '7', '1-06-软件开发生命周期模型', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('132', '4', '8', '1-07-分解与抽象', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('133', '4', '9', '1-08-层次性', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('134', '4', '10', '1-09-HelloWorld', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('135', '4', '11', '1-10-代码是用来读的', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('136', '4', '12', '1-11-有代码就得有测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('137', '4', '13', '1-12-用例来给需求建模', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('138', '4', '14', '第一单元 单元测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('139', '4', '15', '2 - 结构化编程范式', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('140', '4', '16', '2-01-结构化编程思想', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('141', '4', '17', '2-02-数据流图', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('142', '4', '18', '2-03-结构图', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('143', '4', '19', '2-04-数据流图向结构图的转变', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('144', '4', '20', '2-05-顺序、循环、分支', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('145', '4', '21', '2-06-MatrixCalculation', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('146', '4', '22', '2-07-避免重复', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('147', '4', '23', '2-08-测试分支和循环', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('148', '4', '24', '第二单元 单元测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('149', '4', '25', '3 - 面向对象编程范式', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('150', '4', '26', '3-01-变更时不可以避免的', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('151', '4', '27', '3-02-大范围的修改vs有限范围', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('152', '4', '28', '3-03-面向对象的世界观', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('153', '4', '29', '3-04-类和对象', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('154', '4', '30', '3-05-BadMatrix', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('155', '4', '31', '3-06-重用', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('156', '4', '32', '3-07-测试单个类', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('157', '4', '33', '第三单元 单元测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('158', '4', '34', '4 - 类的封装', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('159', '4', '35', '4-01-在一起', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('160', '4', '36', '4-02-封装', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('161', '4', '37', '4-03-MyMatrix', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('162', '4', '38', '4-04-寻找类和对象', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('163', '4', '39', '4-05-用类图表达类的职责', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('164', '4', '40', '4-06-防御式编程', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('165', '4', '41', '4-07-静态变量和静态方法', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('166', '4', '42', '4-08-简单类的初始化', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('167', '4', '43', '第四单元 单元测试', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('168', '4', '44', '5 - 类的协作', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('169', '4', '45', '5-01-职责', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('170', '4', '46', '5-02-协作', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('171', '4', '47', '5-03-类之间的关系', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('172', '4', '48', '5-04-TicTacToe', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('173', '4', '49', '5-05-用顺序图表达类的协作', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('174', '4', '50', '5-06-用Mock Object来辅助测试协作', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('175', '4', '51', '第五单元 单元测验', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('176', '4', '52', '6-类的可重用和可修改', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('177', '4', '53', '6-01-可修改性', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('178', '4', '54', '6-02-继承', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('179', '4', '55', '6-03-多态', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('180', '4', '56', '6-04-抽象类和抽象方法', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('181', '4', '57', '6-05-接口', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('182', '4', '58', '6-06-NewTicTacToe', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('183', '4', '59', '6-07-继承和组合', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('184', '4', '60', '6-08-按接口编程', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('185', '4', '61', '6-09-实现可修改性', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('186', '4', '62', '6-10-复杂类的初始化', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('187', '5', '1', '00 - 关于课程', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('188', '5', '2', '01 - 软件工程方法学', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('189', '5', '3', '02 - 移动互联网需求分析', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('190', '5', '4', '03 - 移动互联网公司架构分析I - 架构视角', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('191', '5', '5', '04 - 移动互联网架构分析II - 关注点', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('192', '5', '6', '05 - 移动互联网应用App体系结构设计', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('193', '5', '7', '06 - 移动互联网商业视角', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('194', '5', '8', '07 - UI设计', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('195', '5', '9', '08 - 秒杀系统', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('196', '5', '10', '09 - 敏捷软件开发', null, null, null, '0', '1', '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES ('197', '5', '11', '10 - 微服务', null, null, null, '0', '1', '2020-12-18 10:00:00');


-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `balance` int NOT NULL,
  `user_role` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '刘钦', '10112345678', '123456', null, 0, 'TEACHER', '2020-12-18 10:00:00');
INSERT INTO `user_info` VALUES ('2', '小明', '10212345678', '123456', null, 0, 'STUDENT', '2020-12-18 10:00:00');

-- ----------------------------
-- Table structure for `recharge_order`
-- ----------------------------

DROP TABLE IF EXISTS `recharge_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recharge_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `value` int NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_user_recharge` (`user_id`),
  CONSTRAINT `fk_user_recharge` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;