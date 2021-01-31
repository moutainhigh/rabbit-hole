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

insert into bmw_access_app(id, encoding, title, created_at)
    value (1, 'test', '测试应用', now());
insert into bmw_access_platform(access_app_id, ref_type, ref_id, created_at)
    value (1, 'alipay', 1, now());
insert into bmw_access_platform(access_app_id, ref_type, ref_id, created_at)
    value (1, 'wxpay', 1, now());
insert into bmw_platform_alipay_config(id, appid, public_key, private_key)
    value (1, '2016080300154586',
           'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh+PU20M0Q4g6GEzM4o3OYfeZFUX3r0p+IxMlTbUal1zIOPCLOm1jemZk+QUaBfRCFsjUA18BTOosBXkXI7LzdUlAGIx9P8q/W2PHqfx53yRdoqKVr6bYu4gH2ryM7baE7D/R7BVp2/CD0j6vUEwSiR8GpSSbEAua5Apc8FvZ8F3OHh/gLUYz0W+VEyrcDrquR2+Xz3R5Rd9Yob1CAwNtzjAgpALkTRTKcQ5EG8p0+UsjxjRoM69xNjtK8gXr61R5LxlyvcCCHSOIHomcyeRnSwUvSmrr3tQ0bSanzBiyOs4sFt+uCcAansMvrn2CxVFYD0JJBQG/Is9AOdeF/f4NCQIDAQAB',
           'MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2X977LaTocs9QuSleAx1gibGvMhAFW3bBAf1wr3YP26ckOIqa+FIcET4tiBlCleq+Ci4sRBBdcKPtU0c/f6SlDPP9OqnqRXUEuXsiUMJ7OPrstoIddLCFqHHmaRpXINfZjmgGtKvruDf78bX2FnYa2uRGNhw3ckIOlF4A7NEIiJZl3fum1/qA6oEGTjn1QiB5//8EN3ERYCDBkhgS1g2T0kuL6Ank+0+SWuE0Fm08vU4uP4icb5LRdRHfoerfHcEIxhbVtihWHSmLgGibNEI7dxFiAz3Wotp8zsonwF9Q5cEbpYPpHVpx/i7RpF7yFUm5M61uzonQah7UNrV1Ww4JAgMBAAECggEAf8bJ9tgzCz2tbRReHGU4RvQSTvpXaTl9CZt4U2RL5q5x+5m12wASn2GhW8tYT2O0NXPyh8ckZCNQZy4K5D1tQMrDg+9/Lwl9BFNkJ1XH/QeeHw18OmEQcITlUJbhApybPu1cix44ug23A8mFQKbaFtS4TU0KFfrytz5SYnmJt1y0oOMN+dmYxnbh+w2Av/N09hj61a47WocOQDW8ytBoDD4nZseFUSQ+vSNK3ialvZQ7zXTvyLads6yONcbOCdk8u4+1UUnex3PX+ySNSjQ1JuTQot+FloAZyywOzXFeVkCtZuQpe/gj9ok8i7iuT/UK/5ouDbJJ9PTJxY14t6650QKBgQDZ8TsBzeYcnZsDQC74YVzEsZhT9MdwAOj9LlSy17g3IRT/zHAy4L3alzOjOT8eLYDw3G1oZLo7zniM1L3kePjE2hhnA0yiPBJcOJzslr40nWdTRc8E4ag3Q5Mv0oQXwU9udLnUNak5CXFsV+cx6VcdcGhHzggxY9+rdyrE7K8x3QKBgQDWOKMjbxhPdLt8zd54+Jy2kUstzW0FbO9FTGw3cwHyAj2I6icjIdMug1RKUi+gnQgu8jkVSVRfUqIlJVtCR4dyYomIJIeHk87wGMv84VKugKylCjYxjYgR0DCaXRWiCHQb+7esJ4WXt7Nww7dPfziZylRbB7WPZ+/jhPhHl0mIHQKBgEBqJhCQdJS8mFZLoBZVYH/aJbWawV9/RV2fVfVOAOp6YqSAHiFLf5Gd4us5PkiDFnsaC1QxgUGv8r1dG4rtnklAVLoNpZbFvn93VBoxK6KNaz6XgWpl77v1wwj9ZYFH51w0L8Bi49Mx0U4+ZNzBpLfUw12FrbI7XJ5nKELv2ZAZAoGBAIbRXDJnr3gJ8hjIg3PEmvP3GsY3m54ngaouP4jiE15YdJufKYRdvEdwlXK0qI6/ZTAOd0hjPvtCyRLxoK5kz+R4CTAqNTVpG3pVUMPUlrGF/6FafOLQvMrhKEVtwbiY82HNGDn7IYNrND4Knmokmd2HzXEAuA4Jjpq0y4BawQctAoGBAJZauDM0SONibPROwobeQZPdv5YW4EuQnMBoittmjsqjv2V5csw0K9FJVjmtcOURqKpBw+MJWuxEpV5MNiSqBmBmnyGk6ayRKVFVhkmj8OOzHhdGe+6xJa+L59DyHyqSY45Yu6DBLop37/VzWwqBitcKWRmyQzfF9BPObV1xR7lD');

INSERT INTO bmw_platform_wxpay_config (id, appid, mch_id, key_str, cert_str) VALUES (1, 'wxffc9e352055986fb', '1576760381', 'quanqiugouwechatpay15951onyidali', '-----BEGIN CERTIFICATE-----
              MIID/DCCAuSgAwIBAgIUImd/AAo/TD0SBEykRc77xvYSO0kwDQYJKoZIhvcNAQEL
              BQAwXjELMAkGA1UEBhMCQ04xEzARBgNVBAoTClRlbnBheS5jb20xHTAbBgNVBAsT
              FFRlbnBheS5jb20gQ0EgQ2VudGVyMRswGQYDVQQDExJUZW5wYXkuY29tIFJvb3Qg
              Q0EwHhcNMjAwMzAzMDQwNDM4WhcNMjUwMzAyMDQwNDM4WjCBjTETMBEGA1UEAwwK
              MTU3Njc2MDM4MTEbMBkGA1UECgwS5b6u5L+h5ZWG5oi357O757ufMTkwNwYDVQQL
              DDDkvZvlsbHluILpobrlvrfljLrlsJrnroDlsYXlrrblsYXnlKjlk4Hnu4/okKXl
              upcxCzAJBgNVBAYMAkNOMREwDwYDVQQHDAhTaGVuWmhlbjCCASIwDQYJKoZIhvcN
              AQEBBQADggEPADCCAQoCggEBAK+RLKdqhtkEEtphqw1N0TRCvjPmFJgXfifaILdT
              Bf7hj4cgAnZkhxy1rA7gcP7iG/A85KR5LTOkstYejGB1eNejVujVE+XpVn9b13CG
              7cY2hAY/olES6fN+ssUJkNF3g1QJcJ4M6E1Y90St9BtTtPUknhgtYXLIZZ+RlfEB
              93MXIvFczm+/EcjbRmDDznOGf+pcmNEV+HojRttwzPkC6uI3cOqiRHh6qHBtgnPn
              tTmtH5oL6fxYJ4Zyc4XZa2SBPJB6yPeM/WqYWqmR+8CcXs5OwZ/D2gNVH7fJc8LQ
              AH/e3Agkent7zx/E88rAKXmYHFTcEjX9DYe50ThNA+VZfO0CAwEAAaOBgTB/MAkG
              A1UdEwQCMAAwCwYDVR0PBAQDAgTwMGUGA1UdHwReMFwwWqBYoFaGVGh0dHA6Ly9l
              dmNhLml0cnVzLmNvbS5jbi9wdWJsaWMvaXRydXNjcmw/Q0E9MUJENDIyMEU1MERC
              QzA0QjA2QUQzOTc1NDk4NDZDMDFDM0U4RUJEMjANBgkqhkiG9w0BAQsFAAOCAQEA
              XaTNWZ6FXDVBOzLl4mIE4Jwc+PiqTe+au49e/lZzpcJ8zKj8w7rjynOqnqKLBSt3
              h96Qxc6UFDaQYO+mnCsw3UmaXpjH8h8q8HrRDhve4JBuHr/ktVdbDBDk8ZznfMH8
              gmmc45kyx4ky7xIDNn5C88NeWnrhlMnw77ntuWWqRADFERIS7ad7IYKuUMioI3xW
              Kp5soi24Fi0fk3+wNKXbpUvkxTgCcrPYPB08lFocI5u241vv807fpiqWE7kaLED1
              f4MjuW7qcNmjT6kFKxU7cyX2GnJCMM+mddBtZAtP8la9LluT87nJ16/A75sLMO6I
              9IfN3hO4YYmc265WHD3mLw==
              -----END CERTIFICATE-----')

