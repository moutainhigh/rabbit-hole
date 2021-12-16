INSERT INTO com_data_dict(`title`, `code`, `remark`, `created_at`)
VALUES ('隧道类型', 'proxy_type', '隧道类型', now());
set @dict_id := last_insert_id();
INSERT INTO com_data_dict_item(dict_id, title, code, remark, created_at)
VALUES (@dict_id, 'HTTP协议', 'http', '隧道类型#HTTP协议', now());
