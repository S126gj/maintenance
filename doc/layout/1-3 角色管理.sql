-- 指定租户id --
SET @tenant_id = '11';

DELETE FROM `sys_layout` WHERE ID IN (1, 2, 3);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (1, 2, 3);

--  角色管理上部 --
INSERT INTO `sys_layout` VALUES ('1', @tenant_id, '角色管理上部');
INSERT INTO `sys_layout_d` VALUES('001001001', '1', @tenant_id, 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('001001002', '1', @tenant_id, 1, 'dateRange', '选择日期', 6, 'daterange', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('001001003', '1', @tenant_id, 2, 'enable', '是否启用', 6, 'select', true, false, 'form', null, null, 1, null, true);

--  角色管理下部 --
INSERT INTO `sys_layout` VALUES ('2', @tenant_id, '角色管理下部');
INSERT INTO `sys_layout_d` VALUES('002001001', '2', @tenant_id, 0, 'name', '名称', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('002001002', '2', @tenant_id, 1, 'description', '描述', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('002001003', '2', @tenant_id, 2, 'gmtCreate', '创建日期', 100, 'text', true, true, 'table', null, null, 1, null, true);

--  角色管理详情 --
INSERT INTO `sys_layout` VALUES ('3', @tenant_id, '角色管理详情');
INSERT INTO `sys_layout_d` VALUES('003001001', '3', @tenant_id, 0, 'name', '角色名称', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('003001002', '3', @tenant_id, 1, 'description', '描述信息', 100, 'input', true, false, 'table', null, null, 1, null, true);
