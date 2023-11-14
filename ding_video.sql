/*
 Navicat Premium Data Transfer

 Source Server         : 随机学习mysql数据库
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 101.132.188.137:3306
 Source Schema         : ding_video

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 14/11/2023 12:20:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `remark` int NULL DEFAULT 0,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `follows` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_jhib4legehrm4yscx9t3lirqi`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (4, '2023-11-04 16:20:26.263000', NULL, '2023-11-04 16:20:26.263000', NULL, 'https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg', '51255903096@stu.ecnu.edu.cn', NULL, '好大一头牛', '$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC', '18256537198', '111', NULL);
INSERT INTO `t_user` VALUES (5, '2023-11-05 17:32:25.874000', NULL, '2023-11-05 17:32:25.874000', NULL, 'https://img2.baidu.com/it/u=1557854410,1907805549&fm=253&fmt=auto&app=138&f=JPEG?w=220&h=220', '13@13.com', NULL, '七只松鼠', '$2a$10$5P7LtxYqRkN6Zab5faiWHeNsEm1uFyHlFTmqMob3XQ7ST3yUd5Jia', '13818232832', '123', NULL);
INSERT INTO `t_user` VALUES (9, '2023-11-07 21:29:23.263000', NULL, '2023-11-07 21:29:23.263000', NULL, 'https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg', 'heroding77@qq.com', NULL, '七七', '$2a$10$.UGaGYEjrFOZsqkIghrvL.qe4/6noJa3JHPViQfpIyOqZ59W97u.m', '13399596382', '12345', NULL);
INSERT INTO `t_user` VALUES (10, '2023-11-14 09:31:10.397000', NULL, '2023-11-14 09:31:10.397000', NULL, 'https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg', '1362419827@qq.com', NULL, 'chd', '$2a$10$6jYD.SwEr7GxgGQ7wWzYCeNvYTY5xMEyFxh.NbiQTzz4yDCUw4LzG', '18122310817', '陈浩弟', NULL);

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `video_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `collections` bigint NULL DEFAULT 0,
  `comments` bigint NULL DEFAULT 0,
  `cover_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `likes` bigint NULL DEFAULT 0,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `id` bigint NULL DEFAULT NULL,
  `video_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`video_id`) USING BTREE,
  INDEX `FK16gjgbg1ghohaxgra2p3jto1f`(`id`) USING BTREE,
  CONSTRAINT `FK16gjgbg1ghohaxgra2p3jto1f` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('\r\nlhMdAdg2Z4QTO-7E0RZnT-OjzRS9', 'food', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/food/lhMdAdg2Z4QTO-7E0RZnT-OjzRS9~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:25:57.000000', '#外国人在中国 #外国人吃中国美食 #南京', 0, '意大利老外第一次挑战南京夜市', '2023-11-06 22:26:01.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/food/lhMdAdg2Z4QTO-7E0RZnT-OjzRS9.mp4');
INSERT INTO `t_video` VALUES ('FkZk3Yl3ksN2cYTXYSihMaju5Yv4', 'sports', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/sports/FkZk3Yl3ksN2cYTXYSihMaju5Yv4~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:11.000000', '#欧冠 #足球 #足球的魅力', 0, '这球能吹一辈子！', '2023-11-06 12:18:11.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/sports/FkZk3Yl3ksN2cYTXYSihMaju5Yv4.mp4');
INSERT INTO `t_video` VALUES ('FlSPGcHsrwQ58OUOxRus7tQD4_zF', 'hot', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/hot/FlSPGcHsrwQ58OUOxRus7tQD4_zF~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:40.000000', '主打一个新鲜，现炸现吃', 0, '小伙钓鱼真讲究', '2023-11-06 23:14:49.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/hot/FlSPGcHsrwQ58OUOxRus7tQD4_zF.mp4');
INSERT INTO `t_video` VALUES ('FmAG2AfbmDX6L2h8H-iF4BhL8x2z', 'hot', 1, 0, 'http://s32rmapm1.bkt.clouddn.com/hot/FmAG2AfbmDX6L2h8H-iF4BhL8x2z~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:38.000000', '温馨画面', 1, '隔辈亲', '2023-11-07 19:32:59.342000', 4, 'http://s32rmapm1.bkt.clouddn.com/hot/FmAG2AfbmDX6L2h8H-iF4BhL8x2z.mp4');
INSERT INTO `t_video` VALUES ('FmWsQWfXQWUYaJCCI5h_vBrT3rEs', 'hot', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/hot/FmWsQWfXQWUYaJCCI5h_vBrT3rEs~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:42.000000', '#机器人', 0, '给机器人小姐姐画个妆', '2023-11-06 23:14:51.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/hot/FmWsQWfXQWUYaJCCI5h_vBrT3rEs.mp4');
INSERT INTO `t_video` VALUES ('Fphuspx-dQOr_cN_ggsgP-xv6vW8', 'hot', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/hot/Fphuspx-dQOr_cN_ggsgP-xv6vW8~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:17:20.000000', '#人类幼崽 #男人带娃系列 #花式带娃', 0, '在爸爸手里怎么都不哭', '2023-11-06 23:18:19.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/hot/Fphuspx-dQOr_cN_ggsgP-xv6vW8.mp4');
INSERT INTO `t_video` VALUES ('lghCEN4rTYorX-YlZHflIS_ZgceK', 'knowledge', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lghCEN4rTYorX-YlZHflIS_ZgceK~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:11.000000', '#放射性同位素 #超重水 #知识分享', 0, '氚到底是什么？它会蒸发形成降雨吗？', '2023-11-06 22:26:19.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lghCEN4rTYorX-YlZHflIS_ZgceK.mp4');
INSERT INTO `t_video` VALUES ('lhd2YoskmOs1-P249PmPGSBZibBR', 'animation', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/animation/lhd2YoskmOs1-P249PmPGSBZibBR~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:21:18.000000', '#海贼王', 0, '黑胡子放弃甜甜果实不是怕雷利', '2023-11-07 10:21:27.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/animation/lhd2YoskmOs1-P249PmPGSBZibBR.mp4');
INSERT INTO `t_video` VALUES ('lhniDrRw40UwVlowSjGNYsTG8s4s', 'fashion', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/fashion/lhniDrRw40UwVlowSjGNYsTG8s4s~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:21:21.000000', '#TGC时装秀 #t台走秀', 0, 'TGC时装秀中出演过假面骑士的演员，有你认识的吗？', '2023-11-07 10:21:31.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/fashion/lhniDrRw40UwVlowSjGNYsTG8s4s.mp4');
INSERT INTO `t_video` VALUES ('lhxtmp_N3k53c5X0tu1DML9cPhJf', 'fashion', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/fashion/lhxtmp_N3k53c5X0tu1DML9cPhJf~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:21:23.000000', '#测评 #护手霜 #护手精华液', 0, '一入秋手就特别容易干燥起皮！这款香氛护手霜它真的好用吗？', '2023-11-07 10:21:33.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/fashion/lhxtmp_N3k53c5X0tu1DML9cPhJf.mp4');
INSERT INTO `t_video` VALUES ('lietZwF6Qi3Dc3qwKvElvn-PSMIb', 'animation', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/animation/lietZwF6Qi3Dc3qwKvElvn-PSMIb~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:26.000000', '#治愈短片 #尽管我们手中空无一物 #母爱', 0, '爱从来不是身外之物', '2023-11-07 10:20:46.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/animation/lietZwF6Qi3Dc3qwKvElvn-PSMIb.mp4');
INSERT INTO `t_video` VALUES ('liiHU0IF9ONK7ROVyyyIkN983U7k', 'animation', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/animation/liiHU0IF9ONK7ROVyyyIkN983U7k~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:29.000000', '#怀旧动画 #童年动画 #猫和老鼠 #奥斯卡', 0, '带你了解猫和老鼠获得过的7座奥斯卡金像奖', '2023-11-07 10:20:49.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/animation/liiHU0IF9ONK7ROVyyyIkN983U7k.mp4');
INSERT INTO `t_video` VALUES ('lio7SKvaj7T0w-wFV0xdZFsY42iP', 'game', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/game/lio7SKvaj7T0w-wFV0xdZFsY42iP~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:15.000000', '一种很新的车技！', 0, '旋转飞车太酷啦！', '2023-11-06 22:26:21.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/game/lio7SKvaj7T0w-wFV0xdZFsY42iP.mp4');
INSERT INTO `t_video` VALUES ('lj49ST4nkDcaVy32Kp6xwQwBznHN', 'knowledge', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lj49ST4nkDcaVy32Kp6xwQwBznHN~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:17.000000', '#物理 #趣味科普', 0, '可视化声学实验演示', '2023-11-06 22:26:23.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lj49ST4nkDcaVy32Kp6xwQwBznHN.mp4');
INSERT INTO `t_video` VALUES ('ljjDyUyCNJPO5gfnebMkFDgMC0-p', 'sports', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/sports/ljjDyUyCNJPO5gfnebMkFDgMC0-p~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:13.000000', '#羽毛球教学 #羽毛球精彩片段 #羽毛球反手', 0, '十种必学羽毛球发力技巧', '2023-11-06 12:18:13.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/sports/ljjDyUyCNJPO5gfnebMkFDgMC0-p.mp4');
INSERT INTO `t_video` VALUES ('lk6T_ZjYOIKZquyOo_sWPg366Gom', 'food', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/food/lk6T_ZjYOIKZquyOo_sWPg366Gom~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:05.000000', '你们感觉贵不贵？', 0, '居民楼里12一份的东北老式麻辣烫', '2023-11-06 22:26:03.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/food/lk6T_ZjYOIKZquyOo_sWPg366Gom.mp4');
INSERT INTO `t_video` VALUES ('llg0akCoZoN2of7gkIitPJJio2xf', 'pet', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/pet/llg0akCoZoN2of7gkIitPJJio2xf~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:22.000000', '萌宠出道计划', 0, '看你吃零食真的很着急！！！', '2023-11-06 22:48:29.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/pet/llg0akCoZoN2of7gkIitPJJio2xf.mp4');
INSERT INTO `t_video` VALUES ('llJuc3mea2uIXlDuCECKTlb5f6xm', 'animation', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/animation/llJuc3mea2uIXlDuCECKTlb5f6xm~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:31.000000', '#鬼灭之刃锻刀村篇 #祢豆子 #鬼灭之刃', 0, '弥豆子打算牺牲自己，让哥哥去拯救别人，没想到弥豆子竟然克服了阳光！', '2023-11-07 10:20:51.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/animation/llJuc3mea2uIXlDuCECKTlb5f6xm.mp4');
INSERT INTO `t_video` VALUES ('llo5t-lBHnfrkffR5AH60aNKGKCf', 'entertainment', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/entertainment/llo5t-lBHnfrkffR5AH60aNKGKCf~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:14.000000', '聊聊人生不必每时每刻都有意义这件事', 0, '在爱丁堡下了火车闲逛', '2023-11-06 12:18:14.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/entertainment/llo5t-lBHnfrkffR5AH60aNKGKCf.mp4');
INSERT INTO `t_video` VALUES ('lm0DPNRFzGnOEUDR6dT249Eg3rWs', 'entertainment', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/entertainment/lm0DPNRFzGnOEUDR6dT249Eg3rWs~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:15.000000', '#外国人反应 #歪果仁 #中国明星 #海外 #音乐 #ins', 0, '23年国外最火的中国明星竟然是他? 你猜不到!', '2023-11-06 12:18:15.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/entertainment/lm0DPNRFzGnOEUDR6dT249Eg3rWs.mp4');
INSERT INTO `t_video` VALUES ('lmgcUGBgN0udNVL6Vv-SLjBNj58a', 'animation', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/animation/lmgcUGBgN0udNVL6Vv-SLjBNj58a~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:34.000000', '#cos #二次元 #cosplay', 0, '你拨打电话已关机', '2023-11-07 10:20:55.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/animation/lmgcUGBgN0udNVL6Vv-SLjBNj58a.mp4');
INSERT INTO `t_video` VALUES ('lmIjouB_jfPz6McZOLxBPOmTVDwJ', 'entertainment', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/entertainment/lmIjouB_jfPz6McZOLxBPOmTVDwJ~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:16.000000', '#海默肖恩 #良医 #颜值变化 #影视盘点', 0, '一分钟带你看完弗莱迪·海默的颜值变化', '2023-11-06 12:18:16.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/entertainment/%20lmIjouB_jfPz6McZOLxBPOmTVDwJ.mp4');
INSERT INTO `t_video` VALUES ('lmqyqPk8HoiBQtxrv4DPGiz3VjQ4', 'food', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/food/lmqyqPk8HoiBQtxrv4DPGiz3VjQ4~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:27.000000', '装满购物车', 0, '沉浸式采购|又去Costco囤货啦', '2023-11-06 22:26:25.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/food/lmqyqPk8HoiBQtxrv4DPGiz3VjQ4.mp4');
INSERT INTO `t_video` VALUES ('lnCzbP5hLsUx6fa1wRkH8oD9DBFA', 'entertainment', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/entertainment/lnCzbP5hLsUx6fa1wRkH8oD9DBFA~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:17.000000', '#旅行大玩家', 0, '因为一部奥黛丽赫本的《罗马假日》，我去许愿池扔出了硬币！', '2023-11-06 12:18:17.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/entertainment/lnCzbP5hLsUx6fa1wRkH8oD9DBFA.mp4');
INSERT INTO `t_video` VALUES ('lnG7TuD2lGq5IFGmHwcfXcd7zp1i', 'game', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/game/lnG7TuD2lGq5IFGmHwcfXcd7zp1i~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:28.000000', '是时候展现真正的牌技了', 0, '藏2骗王炸', '2023-11-06 22:26:32.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/game/lnG7TuD2lGq5IFGmHwcfXcd7zp1i.mp4');
INSERT INTO `t_video` VALUES ('lngodGw90oKxmE5TrF-5FLCVgxth', 'food', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/food/lngodGw90oKxmE5TrF-5FLCVgxth~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 22:26:31.000000', '#鸡胸肉 #减脂餐 #鸡胸肉的神仙吃法 #低脂低卡', 0, '低脂高蛋白的西兰花口蘑炒鸡胸肉！', '2023-11-06 22:26:34.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/food/lngodGw90oKxmE5TrF-5FLCVgxth.mp4');
INSERT INTO `t_video` VALUES ('lnKo9b2wIB0mNgkjG7rydNym5h-F', 'pet', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/pet/lnKo9b2wIB0mNgkjG7rydNym5h-F~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:20.000000', '猫猫进行曲合拍挑战', 0, '汪汪；谁还不是个小可爱呀～', '2023-11-06 22:48:29.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/pet/lnKo9b2wIB0mNgkjG7rydNym5h-F.mp4');
INSERT INTO `t_video` VALUES ('lnn-77wkWTFSzDCZJQW0e6B7tKSC', 'sports', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/sports/lnn-77wkWTFSzDCZJQW0e6B7tKSC~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:19.000000', '土耳其女篮超级联赛，贝西克塔斯对阵加拉塔萨雷，李月汝轰下22分11篮板的大号两双数据，但在第三节遭到对手恶意犯规伤到膝盖，被搀扶出场', 0, '李月汝受伤', '2023-11-06 12:18:19.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/sports/lnn-77wkWTFSzDCZJQW0e6B7tKSC.mp4');
INSERT INTO `t_video` VALUES ('lnz-5jZacOyg0o6NiPIF-1BFehfv', 'knowledge', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lnz-5jZacOyg0o6NiPIF-1BFehfv~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:17:56.000000', '#小米 #雷军 #企业家 #人物故事', 0, '小米科技创始人的合影，你知道他们都是谁吗', '2023-11-06 21:18:17.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lnz-5jZacOyg0o6NiPIF-1BFehfv.mp4');
INSERT INTO `t_video` VALUES ('loGHAzvdIybdN2joV8dMo9MM9hlB', 'fashion', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/fashion/loGHAzvdIybdN2joV8dMo9MM9hlB~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:37.000000', '#现场实拍 #精工细作', 0, '金条打手镯制作过程', '2023-11-07 10:20:57.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/fashion/loGHAzvdIybdN2joV8dMo9MM9hlB.mp4');
INSERT INTO `t_video` VALUES ('lowApcg58UASkcg9iUgiz0hj42fU', 'game', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/game/lowApcg58UASkcg9iUgiz0hj42fU~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', '#和平精英天文漫游', 0, '现在决赛圈全是飞机，真的很离谱！', '2023-11-06 21:18:17.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/game/lowApcg58UASkcg9iUgiz0hj42fU.mp4');
INSERT INTO `t_video` VALUES ('lo_9J5fxrLsBNoYKwihas5efwrLF', 'hot', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/hot/lo_9J5fxrLsBNoYKwihas5efwrLF~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:18:51.000000', '#街头健身 #引体向上', 0, '大学一小伙体测时狂拉22个引体向上！', '2023-11-06 23:19:30.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/hot/lo_9J5fxrLsBNoYKwihas5efwrLF.mp4');
INSERT INTO `t_video` VALUES ('lp4CtIHYX9z2lavzlL92oF-d4rtp', 'food', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/food/lp4CtIHYX9z2lavzlL92oF-d4rtp~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', '大厨分享正确吃法，收藏了', 0, '螃蟹身上有6个部位不能吃', '2023-11-06 21:18:17.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/food/lp4CtIHYX9z2lavzlL92oF-d4rtp.mp4');
INSERT INTO `t_video` VALUES ('lp_XTa8DZNLXIOToBrDaSsef3rx2', 'knowledge', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lp_XTa8DZNLXIOToBrDaSsef3rx2~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', '幂的运算这样解，学霸说真妙啊！', 0, '山东省中考数学题，非常经典', '2023-11-06 21:18:17.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lp_XTa8DZNLXIOToBrDaSsef3rx2.mp4');
INSERT INTO `t_video` VALUES ('lqGgsVA1tyWDWuXhjmWsCP9swEG8', 'pet', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/pet/lqGgsVA1tyWDWuXhjmWsCP9swEG8~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:24.000000', '萌宠的日常', 0, '每天一遍，快乐无限', '2023-11-06 23:14:30.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/pet/lqGgsVA1tyWDWuXhjmWsCP9swEG8.mp4');
INSERT INTO `t_video` VALUES ('lrF7hm4UiUc2k_WVNdBxLSea7Gc7', 'knowledge', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lrF7hm4UiUc2k_WVNdBxLSea7Gc7~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', '#东北f4 #沈阳 #大连 #长春 #哈尔滨', 0, '希望东北F4能够再次盛放！', '2023-11-06 21:18:17.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/knowledge/lrF7hm4UiUc2k_WVNdBxLSea7Gc7.mp4');
INSERT INTO `t_video` VALUES ('lrkbLZiRqcsGyDQ-cRw724OqPBGS', 'fashion', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/fashion/lrkbLZiRqcsGyDQ-cRw724OqPBGS~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 09:52:49.000000', '#美甲教程 #美拉德风 #美出高级感 #空间法式美甲', 0, '空间法式美甲教程款式', '2023-11-07 09:53:40.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/fashion/lrkbLZiRqcsGyDQ-cRw724OqPBGS.mp4');
INSERT INTO `t_video` VALUES ('lrkIwC-xGYqBgRT8sNZEgpLEilyJ', 'fashion', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/fashion/lrkIwC-xGYqBgRT8sNZEgpLEilyJ~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-07 10:20:40.000000', '#跑鞋推荐 #竞速跑鞋', 0, '你们认为哪双最好穿？', '2023-11-07 10:20:59.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/fashion/lrkIwC-xGYqBgRT8sNZEgpLEilyJ.mp4');
INSERT INTO `t_video` VALUES ('lrrJX1unJtEvQkS6g3dF-VSHQcrx', 'game', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/game/lrrJX1unJtEvQkS6g3dF-VSHQcrx~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', '王者荣耀', 0, '真正的猎手往往都以猎物形式出现！', '2023-11-06 21:18:17.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/game/lrrJX1unJtEvQkS6g3dF-VSHQcrx.mp4');
INSERT INTO `t_video` VALUES ('lrscuW0qeiJf4OXdb0d3jcVUhwEC', 'sports', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/sports/lrscuW0qeiJf4OXdb0d3jcVUhwEC~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:20.000000', '#wtt法兰克福冠军赛2023 #国乒 #马龙 #奥恰洛夫 #马龙3比0奥恰洛夫', 0, 'WTT法兰克福站马龙VS奥恰洛夫，3:0获胜，达成对其20连胜成就', '2023-11-06 12:18:20.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/sports/lrscuW0qeiJf4OXdb0d3jcVUhwEC.mp4');
INSERT INTO `t_video` VALUES ('lsCik9BQ7edgzMNDO-LVZiOS106q', 'pet', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/pet/lsCik9BQ7edgzMNDO-LVZiOS106q~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:34.000000', '#拉布拉多 #萌宠出道计划 #傻狗的日常 #狗狗', 0, '给大家表演一个一秒嗦面条', '2023-11-06 22:48:29.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/pet/lsCik9BQ7edgzMNDO-LVZiOS106q.mp4');
INSERT INTO `t_video` VALUES ('ltVEkBJTRSsyCbx76li4iOLA6mom', 'pet', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/pet/ltVEkBJTRSsyCbx76li4iOLA6mom~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 23:14:12.000000', '#修勾 #比熊 #萌宠出道计划', 0, '好像很不服气', '2023-11-06 22:48:29.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/pet/ltVEkBJTRSsyCbx76li4iOLA6mom.mp4');
INSERT INTO `t_video` VALUES ('ltvs0j-mX0mz1FJgvQ94hREiS-Ux', 'sports', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/sports/ltvs0j-mX0mz1FJgvQ94hREiS-Ux~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:21.000000', '控球2揉球，足球基本功', 0, '谁见过梅西C罗揉球？', '2023-11-06 12:18:21.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/sports/ltvs0j-mX0mz1FJgvQ94hREiS-Ux.mp4');
INSERT INTO `t_video` VALUES ('ltVWpIAjp_4InYTTuQqt6l6aHPaW', 'entertainment', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/entertainment/ltVWpIAjp_4InYTTuQqt6l6aHPaW~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 12:18:22.000000', '#港风 #盛世美颜 #港星女神 #一眼沦陷 #混剪', 0, '港圈女神颜值有多绝', '2023-11-06 12:18:22.000000', 5, 'http://s32rmapm1.bkt.clouddn.com/entertainment/ltVWpIAjp_4InYTTuQqt6l6aHPaW.mp4');
INSERT INTO `t_video` VALUES ('luEc-Jt3gzCTi-AnMH254PQDAlSP', 'game', 0, 0, 'http://s32rmapm1.bkt.clouddn.com/game/luEc-Jt3gzCTi-AnMH254PQDAlSP~tplv-dy-resize-origshort-autoq-75_330.jpeg', '2023-11-06 21:18:17.000000', ' #地平线5 #STEAM游戏', 0, '地平线5：在游戏里最贵的车竟然不是柯尼塞格而是它？', '2023-11-06 21:18:17.000000', 4, 'http://s32rmapm1.bkt.clouddn.com/game/luEc-Jt3gzCTi-AnMH254PQDAlSP.mp4');

-- ----------------------------
-- Table structure for user_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `remark` int NULL DEFAULT 0,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `postId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `post_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collect
-- ----------------------------
INSERT INTO `user_collect` VALUES (1, '2023-11-07 19:32:59.216000', NULL, '2023-11-07 19:32:59.216000', NULL, 1, NULL, 'FmAG2AfbmDX6L2h8H-iF4BhL8x2z', '111');
INSERT INTO `user_collect` VALUES (2, '2023-11-07 19:33:59.211000', NULL, '2023-11-07 19:33:59.211000', NULL, 1, NULL, '111', 'fan');

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `remark` int NULL DEFAULT 0,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `followId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `follow_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------
INSERT INTO `user_follow` VALUES (1, '2023-11-07 19:31:59.218000', NULL, '2023-11-07 19:31:59.218000', NULL, 1, NULL, '111', '123');

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `remark` int NULL DEFAULT 0,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `post_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `video_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_like
-- ----------------------------
INSERT INTO `user_like` VALUES (1, '2023-11-07 19:29:59.370000', NULL, '2023-11-07 19:29:59.370000', 'FmAG2AfbmDX6L2h8H-iF4BhL8x2z', 1, '0', '111', '');

SET FOREIGN_KEY_CHECKS = 1;
