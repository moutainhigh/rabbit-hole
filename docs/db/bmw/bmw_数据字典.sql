INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('支付商户类型', 'payment_mch_type', '支付商户类型', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '支付宝', 'alipay', '支付商户类型#支付宝', now());
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, '微信支付', 'wxpay', '支付商户类型#微信支付', now());


