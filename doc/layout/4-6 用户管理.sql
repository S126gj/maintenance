-- 指定租户id --
SET @tenant_id = '11';

DELETE FROM `sys_layout` WHERE ID IN (4, 5, 6);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (4, 5, 6);

--  用户管理上部 --
INSERT INTO `sys_layout` VALUES ('4', @tenant_id, '用户管理上部');
INSERT INTO `sys_layout_d` VALUES('004001001', '4', @tenant_id, 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('004001002', '4', @tenant_id, 1, 'dateRange', '选择日期', 6, 'daterange', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('004001003', '4', @tenant_id, 2, 'enable', '是否启用', 6, 'select', true, false, 'form', null, null, 1, null, true);

--  用户管理下部 --
INSERT INTO `sys_layout` VALUES ('5', @tenant_id, '用户管理下部');
INSERT INTO `sys_layout_d` VALUES('005001001', '5', @tenant_id, 0, 'username', '用户名', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001002', '5', @tenant_id, 1, 'roleList', '角色', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001003', '5', @tenant_id, 2, 'realName', '姓名', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001004', '5', @tenant_id, 3, 'phone', '手机号', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001005', '5', @tenant_id, 4, 'idCard', '身份证', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001006', '5', @tenant_id, 5, 'genderDesc', '性别', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('005001007', '5', @tenant_id, 6, 'statusDesc', '状态', 100, 'text', true, true, 'table', null, null, 1, null, true);

--  用户管理详情 --
INSERT INTO `sys_layout` VALUES ('6', @tenant_id, '用户管理详情');
INSERT INTO `sys_layout_d` VALUES('006001001', '6', @tenant_id, 0, 'username', '用户名', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('006001002', '6', @tenant_id, 1, 'phone', '手机号', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('006001003', '6', @tenant_id, 2, 'gender', '性别', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('006001004', '6', @tenant_id, 3, 'idCard', '身份证', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('006001005', '6', @tenant_id, 4, 'realName', '真实姓名', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('006001006', '6', @tenant_id, 5, 'icon', '头像', 100, 'upload', true, false, 'table', null, null, 1, null, true);
