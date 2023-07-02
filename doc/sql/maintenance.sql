/*
 Navicat Premium Data Transfer

 Source Server         : localhost-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : maintenance

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 30/06/2023 10:08:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_layout
-- ----------------------------
CREATE TABLE `sys_layout`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id layout_id',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单据名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '单据布局表';

-- ----------------------------
-- Records of sys_layout
-- ----------------------------
BEGIN;
INSERT INTO `sys_layout` (`id`, `tenant_id`, `name`) VALUES ('1', '11', '角色管理上部'), ('2', '11', '角色管理下部'), ('3', '11', '角色管理详情'), ('4', '11', '用户管理上部'), ('5', '11', '用户管理下部'), ('6', '11', '用户管理详情');
COMMIT;

-- ----------------------------
-- Table structure for sys_layout_d
-- ----------------------------
CREATE TABLE `sys_layout_d`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `layout_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单据id',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  `sort` int NULL DEFAULT NULL COMMENT '序号',
  `field_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段名称',
  `label` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '显示名称',
  `width` int NULL DEFAULT 0 COMMENT '显示宽度',
  `input_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示类型 text input dialog select filter date time button money',
  `visible` bit(1) NULL DEFAULT NULL COMMENT '是否显示',
  `readonly` bit(1) NULL DEFAULT NULL COMMENT '是否只读',
  `layout_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表单类型 table/form',
  `data_source` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据源 作为 select 下拉的数据源 以及 按钮组对象',
  `validate_rules` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表单验证',
  `filterable` bit(1) NULL DEFAULT NULL COMMENT '下拉搜索',
  `auth_config` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户角色表ID',
  `sortable` bit(1) NULL DEFAULT b'0' COMMENT '是否前端排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id` ASC) USING BTREE,
  INDEX `idx_layout_id`(`layout_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '单据布局明细';

-- ----------------------------
-- Records of sys_layout_d
-- ----------------------------
BEGIN;
INSERT INTO `sys_layout_d` (`id`, `layout_id`, `tenant_id`, `sort`, `field_name`, `label`, `width`, `input_type`, `visible`, `readonly`, `layout_type`, `data_source`, `validate_rules`, `filterable`, `auth_config`, `sortable`) VALUES ('001001001', '1', '11', 0, 'blurry', '搜索框', 9, 'text', b'1', b'0', 'form', NULL, '[]', b'1', NULL, b'1'), ('001001002', '1', '11', 1, 'dateRange', '选择日期', 6, 'daterange', b'1', b'0', 'form', NULL, '[]', b'1', NULL, b'1'), ('002001001', '2', '11', 0, 'name', '名称', 200, 'text', b'1', b'1', 'table', NULL, '[]', b'1', NULL, b'1'), ('002001002', '2', '11', 1, 'description', '描述', 200, 'text', b'1', b'1', 'table', NULL, '[]', b'1', NULL, b'1'), ('002001003', '2', '11', 2, 'gmtCreate', '创建日期', 100, 'text', b'1', b'1', 'table', NULL, '[]', b'1', NULL, b'1'), ('003001001', '3', '11', 0, 'name', '角色名称', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('003001002', '3', '11', 1, 'description', '描述信息', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('004001001', '4', '11', 0, 'blurry', '搜索框', 6, 'text', b'1', b'0', 'form', NULL, NULL, b'1', NULL, b'1'), ('004001002', '4', '11', 1, 'dateRange', '选择日期', 6, 'daterange', b'1', b'0', 'form', NULL, NULL, b'1', NULL, b'1'), ('004001003', '4', '11', 2, 'enable', '是否启用', 6, 'select', b'1', b'0', 'form', NULL, NULL, b'1', NULL, b'1'), ('005001001', '5', '11', 0, 'username', '用户名', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001002', '5', '11', 1, 'roleList', '角色', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001003', '5', '11', 2, 'realName', '姓名', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001004', '5', '11', 3, 'phone', '手机号', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001005', '5', '11', 4, 'idCard', '身份证', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001006', '5', '11', 5, 'genderDesc', '性别', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('005001007', '5', '11', 6, 'statusDesc', '状态', 100, 'text', b'1', b'1', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001001', '6', '11', 0, 'username', '用户名', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001002', '6', '11', 1, 'phone', '手机号', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001003', '6', '11', 2, 'gender', '性别', 100, 'select', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001004', '6', '11', 3, 'idCard', '身份证', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001005', '6', '11', 4, 'realName', '真实姓名', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1'), ('006001006', '6', '11', 5, '头像', 'icon', 100, 'text', b'1', b'0', 'table', NULL, NULL, b'1', NULL, b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
CREATE TABLE `sys_menu`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `pid` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父级ID',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `sort` int NULL DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端名称',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路径',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端图标',
  `permission` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` int NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `hidden` int NOT NULL DEFAULT 0 COMMENT '是否隐藏 0-否 1-是',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE,
  INDEX `index_pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `pid`, `tenant_id`, `title`, `sort`, `name`, `path`, `icon`, `permission`, `type`, `component`, `hidden`, `gmt_create`, `gmt_modified`) VALUES ('10', '8', '11', '设备销售', 9, 'EquipmentSales', 'EquipmentSales', 'el-icon-money', '设备销售', 1, 'BasicInformation/EquipmentSales/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('11', '8', '11', '经销商管理', 10, 'DealerManagement', 'DealerManagement', 'el-icon-coordinate', '经销商管理', 1, 'BasicInformation/DealerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('12', '8', '11', '客户管理', 11, 'CustomerManagement', 'CustomerManagement', 'el-icon-user', '客户管理', 1, 'BasicInformation/CustomerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('13', '8', '11', '故障类型', 12, 'FaultType', 'FaultType', 'el-icon-suitcase', '故障类型', 1, 'BasicInformation/FaultType/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('14', '8', '11', '角色管理', 13, 'Roles', 'Roles', 'el-icon-s-custom', '角色管理', 1, 'BasicInformation/Roles/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('15', '8', '11', '用户管理', 14, 'UserManagement', 'UserManagement', 'el-icon-user-solid', '用户管理', 1, 'BasicInformation/UserManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('16', '8', '11', '系统日志', 15, 'SystemLog', 'SystemLog', 'el-icon-date', '系统日志', 1, 'BasicInformation/SystemLog/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('17', '8', '11', '系统设置', 16, 'SystemSettings', 'SystemSettings', 'el-icon-setting', '系统设置', 1, 'BasicInformation/SystemSettings/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('18', '8', '11', '帮助', 17, 'help', 'help', 'el-icon-info', '帮助', 1, 'BasicInformation/help/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('19', '8', '11', '配件管理', 18, 'AccessoryManagement', 'AccessoryManagement', 'el-icon-connection', '配件管理', 1, 'BasicInformation/AccessoryManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('2', '0', '11', '维修管理', 1, 'maintenance', '/maintenance', 'el-icon-s-help', '维修管理', 0, 'Layout', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('20', '8', '11', '服务网点', 19, 'ServiceNetwork', 'ServiceNetwork', 'el-icon-position', '服务网点', 1, 'BasicInformation/ServiceNetwork/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('21', '8', '11', '服务人员', 20, 'ServiceStaff', 'ServiceStaff', 'el-icon-s-custom', '服务人员', 1, 'BasicInformation/ServiceStaff/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('22', '8', '11', '字典管理', 21, 'DictionaryManaged', 'DictionaryManaged', 'el-icon-folder', '字典管理', 1, 'BasicInformation/DictionaryManaged/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('3', '2', '11', '远程诊断', 2, 'RemoteDiagnosis', 'RemoteDiagnosis', 'el-icon-bell', '远程诊断', 1, 'maintenance/RemoteDiagnosis/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('4', '2', '11', '报修管理', 3, 'RepairManagement', 'RepairManagement', 'table', '报修管理', 1, 'maintenance/RepairManagement/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('5', '2', '11', '服务单', 4, 'ServiceList', 'ServiceList', 'el-icon-document', '服务单', 1, 'maintenance/ServiceList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('6', '2', '11', '派工单', 5, 'DispatchList', 'DispatchList', 'el-icon-user', '派工单', 1, 'maintenance/DispatchList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('7', '2', '11', '服务审核', 6, 'ServiceReview', 'ServiceReview', 'tree', '服务审核', 1, 'maintenance/ServiceReview/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01'), ('8', '0', '11', '基础信息', 7, 'BasicInformation', '/BasicInformation', 'el-icon-reading', '基础信息', 0, 'Layout', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00'), ('9', '8', '11', '设备型号', 8, 'EquipmentModel', 'EquipmentModel', 'el-icon-info', '设备型号', 1, 'BasicInformation/EquipmentModel/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE `sys_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `tenant_id`, `name`, `description`, `status`, `sort`, `gmt_create`, `gmt_modified`) VALUES ('1', '11', '管理员', '拥有所有权限', 1, 0, '2023-06-28 09:53:26', '2023-06-28 09:53:26'), ('2', '11', '游客', '只有查看权限', 1, 1, '2023-06-28 09:56:44', '2023-06-28 09:56:44'), ('3', '22', 'test', 'test', 1, 2, '2023-06-29 12:48:17', '2023-06-29 12:48:17');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
CREATE TABLE `sys_role_menu`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
  `menu_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单id',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_role_id`(`role_id` ASC) USING BTREE,
  INDEX `index_menu_id`(`menu_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `tenant_id`) VALUES ('10', '1', '9', '11'), ('11', '1', '10', '11'), ('12', '1', '11', '11'), ('13', '1', '12', '11'), ('14', '1', '13', '11'), ('15', '1', '14', '11'), ('16', '1', '15', '11'), ('17', '1', '16', '11'), ('18', '1', '17', '11'), ('19', '1', '18', '11'), ('2', '1', '2', '11'), ('20', '1', '19', '11'), ('21', '1', '20', '11'), ('22', '1', '21', '11'), ('3', '2', '3', '11'), ('4', '1', '4', '11'), ('5', '1', '6', '11'), ('6', '1', '5', '11'), ('7', '1', '7', '11'), ('8', '1', '8', '11'), ('9', '1', '3', '11');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `real_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `status` int NULL DEFAULT 1 COMMENT '帐号启用状态:0->禁用；1->启用',
  `gender` int NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `tenant_id`, `username`, `password`, `phone`, `real_name`, `id_card`, `icon`, `status`, `gender`, `gmt_create`, `gmt_modified`) VALUES ('1', '11', '测试', 'e10adc3949ba59abbe56e057f20f883e', '15079865494', '沈国纪', NULL, 'http://123.60.49.164:9000/maintenance/321684994846_.pic.jpg', 1, 1, '2023-06-28 09:51:56', '2023-06-28 09:51:56'), ('2', '22', '测试2', 'e10adc3949ba59abbe56e057f20f883e', '18322826659', NULL, NULL, NULL, 1, 2, '2023-06-29 13:56:39', '2023-06-29 13:56:39');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
CREATE TABLE `sys_user_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id` ASC) USING BTREE,
  INDEX `index_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `tenant_id`) VALUES ('1', '1', '1', '11'), ('2', '1', '2', '11');
COMMIT;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
CREATE TABLE `tenant`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '名称',
  `begin_date` datetime NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime NULL DEFAULT NULL COMMENT '到期日期',
  `license` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权信息',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户表';

-- ----------------------------
-- Records of tenant
-- ----------------------------
BEGIN;
INSERT INTO `tenant` (`id`, `name`, `begin_date`, `end_date`, `license`, `gmt_create`, `gmt_modified`) VALUES ('11', '测试', '2022-06-28 09:49:05', '2033-06-28 09:49:12', NULL, '2022-06-28 09:49:05', '2022-06-28 09:49:05'), ('22', '测试2', '2022-06-29 13:53:36', '2033-06-29 13:53:36', NULL, '2022-06-28 09:49:05', '2022-06-28 09:49:05');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
