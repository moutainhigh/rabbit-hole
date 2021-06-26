# 物流系统
# - 物流方式
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('物流方式', 'wl_shipping_methods', '物流|运输方式', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '直达', 'direct', '运输方式#直达', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '中转', 'transit', '运输方式#直达', now());
# - 单位
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('单位', 'wl_unit', '物流|单位', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '元/方', 'yuan_cube', '单位#元/方', now());

# 用户系统
# - API请求方式
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('请求方式', 'request_method', '请求方式', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'GET', 'get', 'GET', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'POST', 'post', 'POST', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'DELETE', 'delete', 'DELETE', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'PUT', 'put', 'PUT', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'ALL', '*', 'all', now());

# 消息系统
# - 消息类型
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('消息类型', 'mms_message_user_ref_type', '消息类型', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '系统消息', 'system_message', '消息类型::系统消息', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '私信消息', 'personal_message', '消息类型::私信消息', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '订阅消息', 'notice_message', '消息类型::订阅消息', now());

# 积分系统
# - 事件类型
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('事件类型', 'integral_flow_event_type', '事件类型', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '用户签到', 'user_sign', '触发事件::用户签到', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '视频观看', 'user_ad', '触发事件::视频观看', now());
# - 变更类型
INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('变更类型', 'integral_flow_change_type', '变更类型', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '新增', 'plus', '变更类型::新增', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '减少', 'subtract', '变更类型::减少', now());
