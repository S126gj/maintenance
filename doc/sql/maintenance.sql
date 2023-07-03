CREATE DATABASE IF NOT EXISTS maintenance CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci;
USE maintenance;

SET @tenant_id = '11';

DROP TABLE IF EXISTS sys_layout;
CREATE TABLE `sys_layout`
(
    `id`        char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id layout_id',
    `tenant_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单据名称',
    PRIMARY KEY (`id`) USING BTREE,
    KEY         `idx_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='单据布局表';

DROP TABLE IF EXISTS sys_layout_d;
CREATE TABLE `sys_layout_d`
(
    `id`        char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
    `layout_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单据id',
    `tenant_id` char(19)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户id',
  `sort` int DEFAULT NULL COMMENT '序号',
  `field_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字段名称',
  `label` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '显示名称',
  `width` int DEFAULT '0' COMMENT '显示宽度',
  `input_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '显示类型 text input dialog select filter date time button money',
  `visible` bit(1) DEFAULT NULL COMMENT '是否显示',
  `readonly` bit(1) DEFAULT NULL COMMENT '是否只读',
  `layout_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '表单类型 table/form',
  `data_source` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据源 作为 select 下拉的数据源 以及 按钮组对象',
  `validate_rules` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '表单验证',
  `filterable` bit(1) DEFAULT NULL COMMENT '下拉搜索',
  `auth_config` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户角色表ID',
  `sortable` bit(1) DEFAULT b'0' COMMENT '是否前端排序',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_id` (`id`) USING BTREE,
  KEY `idx_layout_id` (`layout_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='单据布局明细';

DROP TABLE IF EXISTS tenant;
CREATE TABLE `tenant`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
    `name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '名称',
    `begin_date`   datetime                                                      DEFAULT NULL COMMENT '开始日期',
    `end_date`     datetime                                                      DEFAULT NULL COMMENT '到期日期',
    `license`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '授权信息',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建日期',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_name` (`name`) USING BTREE,
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='租户表';

INSERT INTO `tenant` VALUES (@tenant_id, '测试', '2022-06-28 09:49:05', '2033-06-28 09:49:12', NULL, now(), now());

DROP TABLE IF EXISTS sys_user;
CREATE TABLE `sys_user`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `username`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '用户名',
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '密码',
    `phone`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '手机号码',
    `real_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '真实姓名',
    `id_card`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '身份证号',
    `icon`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
    `status`       int                                                           DEFAULT '1' COMMENT '帐号启用状态:0->禁用；1->启用',
    `gender`       int                                                           DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='用户表';

INSERT INTO `sys_user` VALUES ('1', @tenant_id, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '123123123123', NULL, NULL, NULL, 1, 1, now(), now());

DROP TABLE IF EXISTS sys_menu;
CREATE TABLE `sys_menu`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `pid`          char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '父级ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '租户id',
    `title`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '菜单名称',
    `sort`         int                                                                DEFAULT NULL COMMENT '菜单排序',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端名称',
    `path`         varchar(100)                                                       DEFAULT NULL COMMENT '前端路径',
    `icon`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端图标',
    `permission`   varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '权限标识',
    `type`         int                                                       NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    `uri`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端资源路径',
    `hidden`       int                                                       NOT NULL DEFAULT '0' COMMENT '是否隐藏 0-否 1-是',
    `gmt_create`   datetime                                                           DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                           DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY            `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='系统菜单表';

INSERT INTO `sys_menu` VALUES ('10', '8', @tenant_id, '设备销售', 9, 'EquipmentSales', 'EquipmentSales', 'el-icon-money', '设备销售', 1, 'BasicInformation/EquipmentSales/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('11', '8', @tenant_id, '经销商管理', 10, 'DealerManagement', 'DealerManagement', 'el-icon-coordinate', '经销商管理', 1, 'BasicInformation/DealerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('12', '8', @tenant_id, '客户管理', 11, 'CustomerManagement', 'CustomerManagement', 'el-icon-user', '客户管理', 1, 'BasicInformation/CustomerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('13', '8', @tenant_id, '故障类型', 12, 'FaultType', 'FaultType', 'el-icon-suitcase', '故障类型', 1, 'BasicInformation/FaultType/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('14', '8', @tenant_id, '角色管理', 13, 'Roles', 'Roles', 'el-icon-s-custom', '角色管理', 1, 'BasicInformation/Roles/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('15', '8', @tenant_id, '用户管理', 14, 'UserManagement', 'UserManagement', 'el-icon-user-solid', '用户管理', 1, 'BasicInformation/UserManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('16', '8', @tenant_id, '系统日志', 15, 'SystemLog', 'SystemLog', 'el-icon-date', '系统日志', 1, 'BasicInformation/SystemLog/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('17', '8', @tenant_id, '系统设置', 16, 'SystemSettings', 'SystemSettings', 'el-icon-setting', '系统设置', 1, 'BasicInformation/SystemSettings/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('18', '8', @tenant_id, '帮助', 17, 'help', 'help', 'el-icon-info', '帮助', 1, 'BasicInformation/help/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('19', '8', @tenant_id, '配件管理', 18, 'AccessoryManagement', 'AccessoryManagement', 'el-icon-connection', '配件管理', 1, 'BasicInformation/AccessoryManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('2', '0', @tenant_id, '维修管理', 1, 'maintenance', '/maintenance', 'el-icon-s-help', '维修管理', 0, 'Layout', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('20', '8', @tenant_id, '服务网点', 19, 'ServiceNetwork', 'ServiceNetwork', 'el-icon-position', '服务网点', 1, 'BasicInformation/ServiceNetwork/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('21', '8', @tenant_id, '服务人员', 20, 'ServiceStaff', 'ServiceStaff', 'el-icon-s-custom', '服务人员', 1, 'BasicInformation/ServiceStaff/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('22', '8', @tenant_id, '字典管理', 21, 'DictionaryManaged', 'DictionaryManaged', 'el-icon-folder', '字典管理', 1, 'BasicInformation/DictionaryManaged/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('3', '2', @tenant_id, '远程诊断', 2, 'RemoteDiagnosis', 'RemoteDiagnosis', 'el-icon-bell', '远程诊断', 1, 'maintenance/RemoteDiagnosis/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('4', '2', @tenant_id, '报修管理', 3, 'RepairManagement', 'RepairManagement', 'table', '报修管理', 1, 'maintenance/RepairManagement/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('5', '2', @tenant_id, '服务单', 4, 'ServiceList', 'ServiceList', 'el-icon-document', '服务单', 1, 'maintenance/ServiceList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('6', '2', @tenant_id, '派工单', 5, 'DispatchList', 'DispatchList', 'el-icon-user', '派工单', 1, 'maintenance/DispatchList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('7', '2', @tenant_id, '服务审核', 6, 'ServiceReview', 'ServiceReview', 'tree', '服务审核', 1, 'maintenance/ServiceReview/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu` VALUES ('8', '0', @tenant_id, '基础信息', 7, 'BasicInformation', '/BasicInformation', 'el-icon-reading', '基础信息', 0, 'Layout', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu` VALUES ('9', '8', @tenant_id, '设备型号', 8, 'EquipmentModel', 'EquipmentModel', 'el-icon-info', '设备型号', 1, 'BasicInformation/EquipmentModel/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');


DROP TABLE IF EXISTS sys_role;
CREATE TABLE `sys_role`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `description`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
    `status`       int                                                           DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `sort`         int                                                           DEFAULT '0' COMMENT '排序',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='角色表';

INSERT INTO `sys_role` VALUES ('1', @tenant_id, '管理员', '拥有所有权限', 1, 0, now(), now());

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE `sys_user_role`
(
    `id`        char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `user_id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
    `role_id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
    `tenant_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY         `index_user_id` (`user_id`) USING BTREE,
    KEY         `index_role_id` (`role_id`) USING BTREE,
    KEY         `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='用户角色关系表';

INSERT INTO `sys_user_role` VALUES ('1', '1', '1', @tenant_id);

DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE `sys_role_menu`
(
    `id`        char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `role_id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
    `menu_id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单id',
    `tenant_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY         `index_role_id` (`role_id`) USING BTREE,
    KEY         `index_menu_id` (`menu_id`) USING BTREE,
    KEY         `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='角色菜单关系表';

INSERT INTO `sys_role_menu` VALUES ('1', '1', '2', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('2', '1', '3', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('3', '1', '4', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('4', '1', '5', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('5', '1', '6', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('6', '1', '7', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('7', '1', '8', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('8', '1', '9', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('9', '1', '10', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('10', '1', '11', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('11', '1', '12', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('12', '1', '13', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('13', '1', '14', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('14', '1', '15', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('15', '1', '16', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('16', '1', '17', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('17', '1', '18', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('18', '1', '19', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('19', '1', '20', @tenant_id);
INSERT INTO `sys_role_menu` VALUES ('20', '1', '21', @tenant_id);

DROP TABLE IF EXISTS sys_dict;
CREATE TABLE `sys_dict`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL COMMENT 'ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户id',
    `name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
    `type`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典枚举',
    `sort`         int                                                       DEFAULT '0' COMMENT '排序',
    `status`       int                                                       DEFAULT '1' COMMENT '启用状态:0->禁用；1->启用',
    `gmt_create`   datetime                                                  DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='数据字典';