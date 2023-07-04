-- 指定租户id --
SET @tenant_id = '11';

DELETE FROM `sys_layout` WHERE ID IN (7, 8, 9);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (7, 8, 9);

--  字典管理上部 --
INSERT INTO `sys_layout` VALUES ('7', @tenant_id, '字典管理上部');
INSERT INTO `sys_layout_d` VALUES('007001001', '7', @tenant_id, 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);

--  字典管理下部 --
INSERT INTO `sys_layout` VALUES ('8', @tenant_id, '字典管理下部');
INSERT INTO `sys_layout_d` VALUES('008001001', '8', @tenant_id, 0, 'typeDesc', '所属字典类型', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('008001002', '8', @tenant_id, 1, 'name', '字典名称', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('008001003', '8', @tenant_id, 2, 'sort', '排序', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('008001004', '8', @tenant_id, 3, 'gmtCreate', '创建时间', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('008001005', '8', @tenant_id, 4, 'status', '状态', 100, 'switch', true, false, 'table', null, null, 1, null, true);

--  字典管理详情 --
INSERT INTO `sys_layout` VALUES ('9', @tenant_id, '字典管理详情');
INSERT INTO `sys_layout_d` VALUES('009001001', '9', @tenant_id, 0, 'name', '字典名称', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('009001002', '9', @tenant_id, 1, 'sort', '排序', 100, 'input-number', true, false, 'table', null, null, 1, null, true);