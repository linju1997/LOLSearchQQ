/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50718
Source Host           : cdb-crx87bqh.gz.tencentcdb.com:10009
Source Database       : lolsearchqq

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-08-14 23:51:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for query_code
-- ----------------------------
DROP TABLE IF EXISTS `query_code`;
CREATE TABLE `query_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(10) NOT NULL COMMENT '查询码',
  `times` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '次数',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `note` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '表创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '表更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='查询码';
SET FOREIGN_KEY_CHECKS=1;
