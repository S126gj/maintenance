CREATE TABLE `box_run_0`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_1`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_2`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_3`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_4`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_5`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_6`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';

CREATE TABLE `box_run_7`
(
    `id`                   bigint                                                       NOT NULL COMMENT 'id',
    `last_query_time`      datetime                                                    DEFAULT NULL COMMENT '查询时间',
    `box_code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盒子序列号',
    `product_number_begin` decimal(30, 2)                                              DEFAULT '0.00' COMMENT '开始生产数量',
    `product_number`       decimal(30, 2)                                              DEFAULT '0.00' COMMENT '生产数量',
    `avg_speed`            decimal(10, 2)                                              DEFAULT '0.00' COMMENT '平均速率',
    `begin_time`           datetime                                                    DEFAULT NULL COMMENT '开始时间',
    `end_time`             datetime                                                    DEFAULT NULL COMMENT '结束时间',
    `duration`             bigint                                                      DEFAULT '0' COMMENT '时长(分钟)',
    `machine_state`        varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备状态（关机,待机,运行）',
    `gmt_create`           datetime                                                    DEFAULT NULL COMMENT '创建日期',
    `gmt_modified`         datetime                                                    DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                    `idx_box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='设备实时运行表';