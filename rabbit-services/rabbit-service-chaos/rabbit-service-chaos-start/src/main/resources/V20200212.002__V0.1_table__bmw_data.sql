INSERT INTO bmw_payment_platform (id, encoding, title, enabled)
VALUES (1, 'alipay', '支付宝', 1);
INSERT INTO bmw_payment_platform (id, encoding, title, enabled)
VALUES (2, 'wxpay', '微信支付', 1);

INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (1, 1, 'alipay.pc', '支付宝(PC)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (2, 1, 'alipay.native', '支付宝(Native)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (3, 1, 'alipay.app', '支付宝(APP)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (4, 1, 'alipay.qrcode', '支付宝(QrCode)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (5, 2, 'wxpay.jsapi', '微信支付(JSAPI)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (6, 2, 'wxpay.app', '微信支付(APP)', 1);
INSERT INTO bmw_payment_mode (id, payment_platform_id, encoding, title, enabled)
VALUES (7, 2, 'wxpay.native', '微信支付(Native)', 1);
