CREATE DATABASE IF NOT EXISTS seata_shop_user;

USE seata_shop_user;

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

SELECT * FROM tb_user;
