/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50718
Source Host           : cdb-crx87bqh.gz.tencentcdb.com:10009
Source Database       : lolsearchqq

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-08-14 23:51:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for summoner
-- ----------------------------
DROP TABLE IF EXISTS `summoner`;
CREATE TABLE `summoner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `query_id` bigint(20) unsigned NOT NULL COMMENT '查询码ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名称',
  `area_name` varchar(255) NOT NULL DEFAULT '' COMMENT '大区名称',
  `qq` varchar(255) NOT NULL COMMENT 'QQ号码',
  `gmt_create` datetime DEFAULT NULL COMMENT '表创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '表修改时间',
  PRIMARY KEY (`id`),
  KEY `外键_查询ID` (`query_id`),
  CONSTRAINT `外键_查询ID` FOREIGN KEY (`query_id`) REFERENCES `query_code` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
