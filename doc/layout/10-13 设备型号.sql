-- 指定租户id --
SET @tenant_id = '11';

DELETE FROM `sys_layout` WHERE ID IN (10, 11, 12, 13);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (10, 11, 12, 13);

--  设备型号上部 --
INSERT INTO `sys_layout` VALUES ('10', @tenant_id, '设备型号上部');
INSERT INTO `sys_layout_d` VALUES('010001001', '10', @tenant_id, 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('010001002', '10', @tenant_id, 1, 'dateRange', '选择日期', 6, 'daterange', true, false, 'form', null, null, 1, null, true);

--  设备型号下部 --
INSERT INTO `sys_layout` VALUES ('11', @tenant_id, '设备型号下部');
INSERT INTO `sys_layout_d` VALUES('011001001', '11', @tenant_id, 0, 'type', '设备型号', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001002', '11', @tenant_id, 1, 'name', '设备名称', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001003', '11', @tenant_id, 2, 'weight', '设备重量(kg)', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001004', '11', @tenant_id, 3, 'maxLength', '最大工作长度(mm)', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001005', '11', @tenant_id, 4, 'maxWidth', '最大工作宽度(mm)', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001006', '11', @tenant_id, 5, 'minLength', '最小工作长度(mm)', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001007', '11', @tenant_id, 6, 'minWidth', '最小工作宽度(mm)', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001008', '11', @tenant_id, 7, 'ratedVoltage', '额定电压', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('011001009', '11', @tenant_id, 8, 'power', '整机功率', 100, 'text', true, true, 'table', null, null, 1, null, true);

--  设备型号详情上部 --
INSERT INTO `sys_layout` VALUES ('12', @tenant_id, '设备型号详情上部');
INSERT INTO `sys_layout_d` VALUES('012001001', '12', @tenant_id, 0, 'name', '设备名称', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001002', '12', @tenant_id, 1, 'type', '设备型号', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001003', '12', @tenant_id, 2, 'weight', '设备重量', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001004', '12', @tenant_id, 3, 'maxLength', '最大工作长度', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001005', '12', @tenant_id, 4, 'maxWidth', '最大工作宽度', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001006', '12', @tenant_id, 5, 'minLength', '最小工作长度', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001007', '12', @tenant_id, 6, 'minWidth', '最小工作宽度', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001008', '12', @tenant_id, 7, 'ratedVoltage', '额定电压', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001009', '12', @tenant_id, 8, 'size', '整机功率', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001010', '12', @tenant_id, 9, 'workRate', '外型尺寸', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001011', '12', @tenant_id, 10, 'power', '工作速率', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001012', '12', @tenant_id, 11, 'imgPath', '设备图片', 100, 'upload', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('012001013', '12', @tenant_id, 12, 'remark', '备注说明', 100, 'textarea', true, false, 'table', null, null, 1, null, true);

--  设备型号详情下部 --
INSERT INTO `sys_layout` VALUES ('13', @tenant_id, '设备型号详情下部');
INSERT INTO `sys_layout_d` VALUES('013001001', '13', @tenant_id, 0, 'errorCode', '故障代码', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('013001002', '13', @tenant_id, 1, 'errorType', '故障类型', 100, 'select', true, false, 'table', null, '/combo/layout/getErrorType', 1, null, true);
INSERT INTO `sys_layout_d` VALUES('013001003', '13', @tenant_id, 2, 'errorInfo', '故障信息', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES('013001004', '13', @tenant_id, 3, 'errorReason', '故障原因', 100, 'input', true, false, 'table', null, null, 1, null, true);