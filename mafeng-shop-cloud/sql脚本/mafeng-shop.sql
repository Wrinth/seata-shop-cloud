CREATE DATABASE IF NOT EXISTS mafeng_shop;

USE mafeng_shop;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` CHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '赵一', '男');
INSERT INTO `tb_user` VALUES (2, '徐二', '男');
INSERT INTO `tb_user` VALUES (3, '张三', '男');
INSERT INTO `tb_user` VALUES (4, '李四', '男');
INSERT INTO `tb_user` VALUES (5, '王五', '男');
INSERT INTO `tb_user` VALUES (6, '陈六', '男');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` BIGINT(20) NOT NULL COMMENT '下单用户ID',
  `title` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `price` BIGINT(20) NOT NULL COMMENT '商品价格',
  `num` INT(10) NULL DEFAULT 0 COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`title`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (100, 1, '联想笔记本电脑 小新Pro16 2022款', 569900, 1);
INSERT INTO `tb_order` VALUES (101, 2, '小米 RedmiBookPro 14英寸 2.5K高色域视网膜屏', 399900, 1);
INSERT INTO `tb_order` VALUES (102, 3, '华硕（ASUS） 华硕无畏15笔记本电脑新款12代酷睿处理器', 578800, 1);
INSERT INTO `tb_order` VALUES (103, 4, 'Apple iPhone 13 (A2634) 256GB 星光色', 679900, 1);
INSERT INTO `tb_order` VALUES (104, 5, 'OPPO Reno7 8GB+256GB 星雨心愿', 269900, 1);
INSERT INTO `tb_order` VALUES (105, 6, '特步男鞋跑步鞋2022新款秋季潮流男士厚底 ', 12900, 1);
INSERT INTO `tb_order` VALUES (106, 2, '回力男鞋子男秋季网面网鞋运动鞋男飞织鞋跑步鞋', 7500, 1);
INSERT INTO `tb_order` VALUES (107, 3, '海信 Vidda 55V1F-R 55英寸 4K超高清 超薄电视', 129900, 1);
