INSERT INTO com_data_dict(`id`, `title`, `code`, `remark`, `created_at`)
VALUES (1, '物流方式', 'wl_shipping_methods', '物流|运输方式', now());

INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (1, '直达', 'direct', '运输方式#直达', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (1, '中转', 'transit', '运输方式#直达', now());

INSERT INTO com_data_dict(`id`, `title`, `code`, `remark`, `created_at`)
VALUES (2, '单位', 'wl_unit', '物流|单位', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (2, '元/方', 'yuan_cube', '单位#元/方', now());

# ums
INSERT INTO com_data_dict(`id`, `title`, `code`, `remark`, `created_at`)
VALUES (3, '请求方式', 'request_method', '请求方式', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (3, 'GET', 'get', 'GET', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (3, 'POST', 'post', 'POST', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (3, 'DELETE', 'delete', 'DELETE', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (3, 'PUT', 'put', 'PUT', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (3, 'ALL', '*', 'all', now());

