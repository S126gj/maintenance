/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : 127.0.0.1:3306
 Source Schema         : xxl_job

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 26/06/2023 08:23:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
CREATE TABLE `xxl_job_group`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (2, 'mall-xxl-job-executor', 'mall执行器', 0, NULL, '2023-06-26 08:22:50');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
CREATE TABLE `xxl_job_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `add_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (4, 2, '每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单', '2022-09-16 10:28:06', '2022-09-16 10:47:50', '沈国纪', '1261550408@qq.com', 'CRON', '* 0/10 * * * ?', 'DO_NOTHING', 'FIRST', 'cancelTimeOutOrder', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-09-16 10:28:06', '', 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_lock` (`lock_name`) VALUES ('schedule_lock');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
CREATE TABLE `xxl_job_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调度-日志',
  `handle_time` datetime NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time` ASC) USING BTREE,
  INDEX `I_handle_code`(`handle_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (27, 2, 4, 'http://sgj.asia:7010/', 'cancelTimeOutOrder', '', NULL, 0, '2023-06-25 10:28:55', 200, '任务触发类型：手动触发<br>调度机器：172.19.0.2<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://sgj.asia:7010/, http://sgj.asia:8010/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://sgj.asia:7010/<br>code：200<br>msg：null', '2023-06-25 10:28:55', 200, '', 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
CREATE TABLE `xxl_job_log_report`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (1, '2022-09-16 00:00:00', 0, 3, 9, NULL), (2, '2022-09-15 00:00:00', 0, 0, 0, NULL), (3, '2022-09-14 00:00:00', 0, 0, 0, NULL), (4, '2022-09-17 00:00:00', 0, 0, 0, NULL), (5, '2022-09-18 00:00:00', 0, 0, 0, NULL), (6, '2022-09-19 00:00:00', 0, 0, 0, NULL), (7, '2022-09-20 00:00:00', 0, 0, 0, NULL), (8, '2022-09-21 00:00:00', 0, 0, 0, NULL), (9, '2022-09-22 00:00:00', 0, 0, 0, NULL), (10, '2022-09-23 00:00:00', 0, 0, 0, NULL), (11, '2022-09-24 00:00:00', 0, 0, 0, NULL), (12, '2022-09-25 00:00:00', 0, 0, 0, NULL), (13, '2022-09-26 00:00:00', 0, 0, 0, NULL), (14, '2022-09-27 00:00:00', 0, 0, 0, NULL), (15, '2022-09-28 00:00:00', 0, 0, 0, NULL), (16, '2022-09-29 00:00:00', 0, 0, 0, NULL), (17, '2022-09-30 00:00:00', 0, 0, 0, NULL), (18, '2022-10-01 00:00:00', 0, 0, 0, NULL), (19, '2022-10-02 00:00:00', 0, 0, 0, NULL), (20, '2022-10-03 00:00:00', 0, 0, 0, NULL), (21, '2022-10-04 00:00:00', 0, 0, 0, NULL), (22, '2022-10-05 00:00:00', 0, 0, 0, NULL), (23, '2022-10-06 00:00:00', 0, 0, 0, NULL), (24, '2022-10-07 00:00:00', 0, 0, 0, NULL), (25, '2022-10-08 00:00:00', 0, 0, 0, NULL), (26, '2022-10-09 00:00:00', 0, 0, 0, NULL), (27, '2022-10-10 00:00:00', 0, 0, 0, NULL), (28, '2022-10-11 00:00:00', 0, 0, 0, NULL), (29, '2022-10-12 00:00:00', 0, 0, 0, NULL), (30, '2022-10-13 00:00:00', 0, 0, 0, NULL), (31, '2022-10-14 00:00:00', 0, 0, 0, NULL), (32, '2022-10-15 00:00:00', 0, 0, 0, NULL), (33, '2022-10-16 00:00:00', 0, 0, 0, NULL), (34, '2022-10-17 00:00:00', 0, 0, 0, NULL), (35, '2022-10-18 00:00:00', 0, 0, 0, NULL), (36, '2022-10-19 00:00:00', 0, 0, 0, NULL), (37, '2022-10-20 00:00:00', 0, 0, 0, NULL), (38, '2022-10-21 00:00:00', 0, 0, 0, NULL), (39, '2022-10-22 00:00:00', 0, 0, 0, NULL), (40, '2022-10-23 00:00:00', 0, 0, 0, NULL), (41, '2022-10-24 00:00:00', 0, 0, 0, NULL), (42, '2022-10-25 00:00:00', 0, 0, 0, NULL), (43, '2022-10-26 00:00:00', 0, 0, 0, NULL), (44, '2022-10-27 00:00:00', 0, 0, 0, NULL), (45, '2022-10-28 00:00:00', 0, 0, 0, NULL), (46, '2022-10-29 00:00:00', 0, 0, 0, NULL), (47, '2022-10-30 00:00:00', 0, 0, 0, NULL), (48, '2022-10-31 00:00:00', 0, 0, 0, NULL), (49, '2022-11-01 00:00:00', 0, 0, 0, NULL), (50, '2022-11-02 00:00:00', 0, 0, 0, NULL), (51, '2022-11-03 00:00:00', 0, 0, 0, NULL), (52, '2022-11-04 00:00:00', 0, 0, 0, NULL), (53, '2022-11-05 00:00:00', 0, 0, 0, NULL), (54, '2022-11-06 00:00:00', 0, 0, 0, NULL), (55, '2022-11-07 00:00:00', 0, 0, 0, NULL), (56, '2022-11-08 00:00:00', 0, 0, 0, NULL), (57, '2022-11-09 00:00:00', 0, 0, 0, NULL), (58, '2022-11-10 00:00:00', 0, 0, 0, NULL), (59, '2022-11-11 00:00:00', 0, 0, 0, NULL), (60, '2022-11-12 00:00:00', 0, 0, 0, NULL), (61, '2022-11-13 00:00:00', 0, 0, 0, NULL), (62, '2022-11-14 00:00:00', 0, 0, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
CREATE TABLE `xxl_job_logglue`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
CREATE TABLE `xxl_job_registry`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group` ASC, `registry_key` ASC, `registry_value` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
CREATE TABLE `xxl_job_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_user` (`id`, `username`, `password`, `role`, `permission`) VALUES (2, 'shengj', '0073b20a0359e12fd3a05ad6b43a11de', 1, '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
