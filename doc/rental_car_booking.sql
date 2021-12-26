/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 50736
Source Host           : localhost:8989
Source Database       : rental_car_booking

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-26 22:45:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `car_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '汽车名称',
  `car_type` int(2) NOT NULL DEFAULT '0' COMMENT '汽车类型：1:BMW , 2:Toyota',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '汽车上下架状态：0:上架 , 1:下架',
  `car_stock` int(10) NOT NULL DEFAULT '0' COMMENT '汽车库存',
  `rent` bigint(20) NOT NULL DEFAULT '0' COMMENT '租金/天',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_car_name` (`car_name`) USING BTREE,
  KEY `idx_car_stock` (`car_stock`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='汽车信息表';

-- ----------------------------
-- Table structure for car_rental_order
-- ----------------------------
DROP TABLE IF EXISTS `car_rental_order`;
CREATE TABLE `car_rental_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `order_no` varchar(50) NOT NULL DEFAULT '0' COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `car_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '汽车id',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态：0:下单中，1:已下单，2:已取消，3:已结束，4:超时',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建订单时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '订单结束时间',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '订单过期时间',
  `count` int(5) NOT NULL DEFAULT '0' COMMENT '租车天数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_no` (`order_no`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_car_id` (`car_id`) USING BTREE,
  KEY `idx_start_time` (`start_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='租车订单表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `password` varchar(256) NOT NULL DEFAULT '' COMMENT '密码',
  `role` int(2) NOT NULL DEFAULT '0' COMMENT '用户角色：0:user , 1:admin',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_phone` (`phone`) USING BTREE,
  UNIQUE KEY `uniq_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
