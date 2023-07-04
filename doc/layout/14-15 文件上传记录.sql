-- 指定租户id --
SET @tenant_id = '11';

DELETE FROM `sys_layout` WHERE ID IN (14, 15);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (14, 15);

--  文件上传记录上部 --
INSERT INTO `sys_layout` VALUES ('14', @tenant_id, '文件上传记录上部');
INSERT INTO `sys_layout_d` VALUES('014001001', '14', @tenant_id, 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('014001002', '14', @tenant_id, 1, 'dateRange', '选择日期', 6, 'daterange', true, false, 'form', null, null, 1, null, true);

--  文件上传记录下部 --
INSERT INTO `sys_layout` VALUES ('15', @tenant_id, '文件上传记录下部');
INSERT INTO `sys_layout_d` VALUES('015001001', '15', @tenant_id, 0, 'fileName', '文件名称', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('015001002', '15', @tenant_id, 1, 'size', '文件大小', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('015001003', '15', @tenant_id, 2, 'uploadMan', '上传人', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('015001004', '15', @tenant_id, 3, 'uploadTime', '上传时间', 100, 'text', true, true, 'table', null, null, 1, null, true);
