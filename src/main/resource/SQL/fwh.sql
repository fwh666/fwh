/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : fwh

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 09/05/2018 17:54:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baseTest
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `name` varchar(200) DEFAULT NULL,
  `age` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baseTest
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES ('aa', 11);
INSERT INTO `test` VALUES ('fuwenhao', 18);
COMMIT;

-- ----------------------------
-- Table structure for testName
-- ----------------------------
DROP TABLE IF EXISTS `testName`;
CREATE TABLE `testName` (
  `name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testName
-- ----------------------------
BEGIN;
INSERT INTO `testName` VALUES ('waha');
INSERT INTO `testName` VALUES ('fuwenhao');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
