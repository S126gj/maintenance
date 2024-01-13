CREATE
DATABASE IF NOT EXISTS maintenance CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci;
USE
maintenance;

SET
@tenant_id = '11';

DROP TABLE IF EXISTS sys_layout;
CREATE TABLE `sys_layout`
(
    `id`        char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id layout_id',
    `tenant_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户id',
    `name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单据名称',
    PRIMARY KEY (`id`, `tenant_id`) USING BTREE,
    KEY         `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='单据布局表';

DROP TABLE IF EXISTS sys_layout_d;
CREATE TABLE `sys_layout_d`
(
    `id`             char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL COMMENT 'id',
    `layout_id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '单据id',
    `tenant_id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '租户id',
    `sort`           int                                                            DEFAULT NULL COMMENT '序号',
    `field_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '字段名称',
    `label`          varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '显示名称',
    `width`          int                                                            DEFAULT '0' COMMENT '显示宽度',
    `input_type`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '显示类型 text input dialog select filter date time button money',
    `visible`        bit(1)                                                         DEFAULT NULL COMMENT '是否显示',
    `readonly`       bit(1)                                                         DEFAULT NULL COMMENT '是否只读',
    `layout_type`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '表单类型 table/form',
    `data_source`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '数据源 作为 select 下拉的数据源 以及 按钮组对象',
    `validate_rules` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '表单验证',
    `filterable`     bit(1)                                                         DEFAULT NULL COMMENT '下拉搜索',
    `auth_config`    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户角色表ID',
    `sortable`       bit(1)                                                         DEFAULT b'0' COMMENT '是否前端排序',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `idx_id` (`id`) USING BTREE,
    KEY              `idx_layout_id` (`layout_id`) USING BTREE,
    KEY              `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='单据布局明细';

DROP TABLE IF EXISTS sys_layout_user;
CREATE TABLE `sys_layout_user`
(
    `id`           char(19) NOT NULL COMMENT 'id',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户id',
    `user_id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户id',
    `content`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
    `gmt_create`   datetime                                                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime                                                  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY            `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户布局表';

DROP TABLE IF EXISTS tenant;
CREATE TABLE `tenant`
(
    `id`            char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL COMMENT 'id',
    `name`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
    `begin_date`    datetime DEFAULT NULL COMMENT '开始日期',
    `end_date`      datetime DEFAULT NULL COMMENT '到期日期',
    `instance_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工厂实例编号',
    `license`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '授权信息',
    `enable`        bit(1)   DEFAULT b'1' COMMENT '启用:0->停用;1->启用;',
    `max_user`      int      DEFAULT '0' COMMENT '并发用户数',
    `gmt_create`    datetime DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`  datetime DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY             `idx_name` (`name`) USING BTREE,
    KEY             `idx_instance_code` (`instance_code`) USING BTREE,
    KEY             `idx_gmt_create` (`gmt_create`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='租户表';

INSERT INTO `tenant`
VALUES (@tenant_id, '测试', '2022-06-28 09:49:05', '2099-06-28 09:49:12', '123', NULL, 1, 0, now(), now());

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
    `email`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '邮箱',
    `icon`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
    `status`       int                                                           DEFAULT '1' COMMENT '帐号启用状态:0->禁用；1->启用',
    `gender`       int                                                           DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='用户表';

INSERT INTO `sys_user`
VALUES ('1', @tenant_id, 'admin', '$2a$10$QSyoNvrANb9dHlwwz0eWDuiySeKSLkqMM5FXqMfYYw/eo.6GMkjRm', '123123123123', NULL,
        NULL, NULL, 1, 1, now(),
        now());

DROP TABLE IF EXISTS sys_menu;
CREATE TABLE `sys_menu`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `pid`          char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '父级ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户id',
    `title`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '菜单名称',
    `sort`         int                                                                DEFAULT NULL COMMENT '菜单排序',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端名称',
    `path`         varchar(100)                                                       DEFAULT NULL COMMENT '前端路径',
    `icon`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端图标',
    `permission`   varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '权限标识',
    `create`       bit(1)                                                    NOT NULL DEFAULT b'0' COMMENT '是否外链：0->否；1->是；',
    `type`         int                                                       NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    `component`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端资源路径',
    `hidden`       int                                                       NOT NULL DEFAULT '0' COMMENT '是否隐藏 0-否 1-是',
    `gmt_create`   datetime                                                           DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                           DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`, `tenant_id`) USING BTREE,
    KEY            `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='系统菜单表';

INSERT INTO `sys_menu`
VALUES ('10', '8', @tenant_id, '设备销售', 9, 'EquipmentSales', 'EquipmentSales', 'el-icon-money', '设备销售', 0, 1,
        'BasicInformation/EquipmentSales/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('11', '8', @tenant_id, '经销商管理', 10, 'DealerManagement', 'DealerManagement', 'el-icon-coordinate',
        '经销商管理', 0, 1, 'BasicInformation/DealerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('12', '8', @tenant_id, '客户管理', 11, 'CustomerManagement', 'CustomerManagement', 'el-icon-user', '客户管理',
        0,
        1, 'BasicInformation/CustomerManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('14', '8', @tenant_id, '角色管理', 13, 'Roles', 'Roles', 'el-icon-s-custom', '角色管理', 0, 1,
        'BasicInformation/Roles/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('15', '8', @tenant_id, '用户管理', 14, 'UserManagement', 'UserManagement', 'el-icon-user-solid', '用户管理', 0,
        1,
        'BasicInformation/UserManagement/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('16', '8', @tenant_id, '系统日志', 15, 'SystemLog', 'SystemLog', 'el-icon-date', '系统日志', 0, 1,
        'BasicInformation/SystemLog/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('17', '8', @tenant_id, '系统设置', 16, 'SystemSettings', 'SystemSettings', 'el-icon-setting', '系统设置', 0, 1,
        'BasicInformation/SystemSettings/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('18', '8', '11', '文件信息', 17, 'FileInfo', 'FileInfo', 'el-icon-info', '文件信息', 0, 1,
        'BasicInformation/FileInfo/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('19', '8', @tenant_id, '配件管理', 18, 'AccessoryManagement', 'AccessoryManagement', 'el-icon-connection',
        '配件管理', 0, 1, 'BasicInformation/AccessoryManagement/index', 0, '2023-06-29 15:03:00',
        '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('2', '0', @tenant_id, '维修管理', 1, 'maintenance', '/maintenance', 'el-icon-s-help', '维修管理', 0, 0,
        'Layout',
        0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('20', '8', @tenant_id, '服务网点', 19, 'ServiceNetwork', 'ServiceNetwork', 'el-icon-position', '服务网点', 0, 1,
        'BasicInformation/ServiceNetwork/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('21', '8', @tenant_id, '服务人员', 20, 'ServiceStaff', 'ServiceStaff', 'el-icon-s-custom', '服务人员', 0, 1,
        'BasicInformation/ServiceStaff/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('22', '8', @tenant_id, '字典管理', 21, 'DictionaryManaged', 'DictionaryManaged', 'el-icon-folder', '字典管理',
        0,
        1, 'BasicInformation/DictionaryManaged/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('3', '2', @tenant_id, '远程诊断', 2, 'RemoteDiagnosis', 'RemoteDiagnosis', 'el-icon-bell', '远程诊断', 0, 1,
        'maintenance/RemoteDiagnosis/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('4', '2', @tenant_id, '报修管理', 3, 'RepairManagement', 'RepairManagement', 'table', '报修管理', 0, 1,
        'maintenance/RepairManagement/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('5', '2', @tenant_id, '服务单', 4, 'ServiceList', 'ServiceList', 'el-icon-document', '服务单', 0, 1,
        'maintenance/ServiceList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('6', '2', @tenant_id, '派工单', 5, 'DispatchList', 'DispatchList', 'el-icon-user', '派工单', 0, 1,
        'maintenance/DispatchList/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('7', '2', @tenant_id, '服务审核', 6, 'ServiceReview', 'ServiceReview', 'tree', '服务审核', 0, 1,
        'maintenance/ServiceReview/index', 0, '2023-06-29 09:35:01', '2023-06-29 09:35:01');
INSERT INTO `sys_menu`
VALUES ('8', '0', @tenant_id, '基础信息', 7, 'BasicInformation', '/BasicInformation', 'el-icon-reading', '基础信息', 0,
        0,
        'Layout', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');
INSERT INTO `sys_menu`
VALUES ('9', '8', @tenant_id, '设备型号', 8, 'EquipmentModel', 'EquipmentModel', 'el-icon-info', '设备型号', 0, 1,
        'BasicInformation/EquipmentModel/index', 0, '2023-06-29 15:03:00', '2023-06-29 15:03:00');


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

INSERT INTO `sys_role`
VALUES ('1', @tenant_id, '管理员', '拥有所有权限', 1, 0, now(), now());

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

INSERT INTO `sys_user_role`
VALUES ('1', '1', '1', @tenant_id);

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

INSERT INTO `sys_role_menu`
VALUES ('1', '1', '2', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('2', '1', '3', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('3', '1', '4', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('4', '1', '5', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('5', '1', '6', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('6', '1', '7', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('7', '1', '8', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('8', '1', '9', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('9', '1', '10', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('10', '1', '11', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('11', '1', '12', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('13', '1', '14', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('14', '1', '15', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('15', '1', '16', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('16', '1', '17', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('17', '1', '18', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('18', '1', '19', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('19', '1', '20', @tenant_id);
INSERT INTO `sys_role_menu`
VALUES ('20', '1', '21', @tenant_id);

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


DROP TABLE IF EXISTS sys_file;
CREATE TABLE `sys_file`
(
    `id`                  char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL COMMENT 'ID',
    `tenant_id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `resource_id`         char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '来源id',
    `resource_code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '来源单号',
    `resource_details_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '来源明细id',
    `resource_type`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '来源类型',
    `fileType`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
    `type`                varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '文件类型',
    `name`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件名称',
    `size`                double                                                        DEFAULT NULL COMMENT '文件大小(M)',
    `upload_man`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '上传人',
    `path`                varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
    `gmt_create`          datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified`        datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                   `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY                   `idx_resource_id` (`resource_id`) USING BTREE,
    KEY                   `idx_resource_code` (`resource_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文件信息表';

DROP TABLE IF EXISTS sys_machine;
CREATE TABLE `sys_machine`
(
    `id`            char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `tenant_id`     char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `name`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备名称',
    `type`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备型号',
    `weight`        int                                                           DEFAULT NULL COMMENT '设备重量(kg)',
    `max_length`    int                                                           DEFAULT NULL COMMENT '最大工作长度(mm)',
    `max_width`     int                                                           DEFAULT NULL COMMENT '最大工作宽度(mm)',
    `min_length`    int                                                           DEFAULT NULL COMMENT '最小工作长度(mm)',
    `min_width`     int                                                           DEFAULT NULL COMMENT '最小工作宽度(mm)',
    `rated_voltage` int                                                           DEFAULT NULL COMMENT '额定电压(V)',
    `size`          int                                                           DEFAULT NULL COMMENT '外型尺寸(mm)',
    `work_rate`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '工作速率',
    `power`         double                                                        DEFAULT NULL COMMENT '整机功率(KW)',
    `img_path`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备图片',
    `remark`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
    `gmt_create`    datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified`  datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY             `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='设备型号表';

DROP TABLE IF EXISTS sys_machine_error;
CREATE TABLE `sys_machine_error`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `tenant_id`    char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `machine_id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '设备id',
    `error_code`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '故障代码',
    `error_type`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '故障类型(字典)',
    `error_info`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '故障信息',
    `error_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '故障原因',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY            `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY            `idx_machine_id` (`machine_id`) USING BTREE,
    KEY            `idx_error_type` (`error_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='设备故障表';

DROP TABLE IF EXISTS sys_dealer;
CREATE TABLE `sys_dealer`
(
    `id`              char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `tenant_id`       char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '租户id',
    `name`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '经销商名称',
    `simple_name`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '经销商简称',
    `code`            varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '编号',
    `contacts_name`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '联系人姓名',
    `contacts_phone`  varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '联系人手机号',
    `contacts_email`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '联系人邮箱',
    `contacts_adress` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人地址',
    `longitude`       decimal(10, 7)                                                DEFAULT NULL COMMENT '地址经度',
    `latitude`        decimal(10, 7)                                                DEFAULT NULL COMMENT '地址纬度',
    `gmt_create`      datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified`    datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY               `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY               `idx_code` (`code`) USING BTREE,
    KEY               `idx_contacts_phone` (`contacts_phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='经销商表';
