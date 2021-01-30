DROP TABLE IF EXISTS `bmw_access_app`;
CREATE TABLE `bmw_access_app`
(
    id         bigint auto_increment,
    encoding   varchar(32)         not null
        comment '应用编号',
    title      VARCHAR(255)        NOT NULL
        COMMENT '标题',
    enabled    TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    created_at datetime(6)         not null
        comment '创建时间',
    created_ip varchar(32)
        comment '创建ip',
    UNIQUE KEY (encoding),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 接入应用表';

DROP TABLE IF EXISTS `bmw_access_platform`;
CREATE TABLE `bmw_access_platform`
(
    id            bigint auto_increment,
    access_app_id bigint              NOT NULL
        comment '接入应用',
    ref_type      varchar(32)         NOT NULL
        comment '第三方支付平台类型',
    ref_id        bigint              NOT NULL
        comment '第三方支付平台配置',
    enabled       TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    created_at    datetime(6)         not null
        comment '创建时间',
    created_ip    varchar(32)
        comment '创建ip',
    UNIQUE KEY (access_app_id, ref_id, ref_type),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 接入平台表';

DROP TABLE IF EXISTS `bmw_platform_alipay_config`;
CREATE TABLE `bmw_platform_alipay_config`
(
    id          bigint auto_increment,
    appid       varchar(255)        not null
        COMMENT 'appid',
    public_key  text                not null
        COMMENT 'public key',
    private_key text                not null
        COMMENT 'private key',
    is_dev      TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '是否测试模式',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 支付宝配置表';

DROP TABLE IF EXISTS `bmw_platform_wxpay_config`;
CREATE TABLE `bmw_platform_wxpay_config`
(
    id bigint auto_increment,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 微信支付配置表';

# ------------------------------------------------
DROP TABLE IF EXISTS `bmw_notify_access_app`;
CREATE TABLE `bmw_notify_access_app`
(
    id            bigint auto_increment,
    request_sn    varchar(64)                      not null
        comment '应用单号(网关): [退款单号/交易单号]',
    trade_sn      varchar(64)                      not null
        comment '交易单号(网关)',
    sign_type     varchar(10)      default 'RSA'   not null
        comment '采用的签名方式: MD5 RSA RSA2 HASH-MAC等',
    notify_type   varchar(32)                      not null
        comment '通知事件类型，pay=>支付事件; refund=>退款事件',
    notify_status varchar(32)      default 'init'  not null
        comment '通知事件状态: init=>初始化; pending=>进行中; success=>成功; fail=>失败; close=>关闭',
    version       tinyint unsigned default 1       not null
        comment '版本',
    input_charset varchar(8)       default 'UTF-8' not null
        comment '字符编码',
    --
    finish_at     datetime(6)
        comment '完成通知时间',
    created_at    datetime(6)                      not null,
    updated_at    datetime(6),
    UNIQUE KEY (`request_sn`),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 事件通知列表';

DROP TABLE IF EXISTS `bmw_notify_access_app_log`;
CREATE TABLE `bmw_notify_access_app_log`
(
    id                   bigint auto_increment,
    notify_access_app_id bigint                     not null
        comment '通知编号ID',
    notify_result        varchar(16) default 'init' not null
        comment '通知调用结果: init=>初始化; success=>响应成功; fail=>响应失败; timeout=>超时失败',
    notify_body          JSON                       not null
        comment '通知内容',
    --
    created_at           datetime(6)                not null,
    updated_at           datetime(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 所有通知应用方日志表';
# ------------------------------------------------

DROP TABLE IF EXISTS `bmw_request_platform_log`;
CREATE TABLE `bmw_request_platform_log`
(
    id                 bigint auto_increment,
    access_platform_id bigint      not null
        comment '接入平台',
    out_request_sn     varchar(64) not null
        comment '应用单号(接入方): [退款单号/交易单号]',
    request_sn         varchar(64) not null
        comment '应用单号(网关): [退款单号/交易单号]',
    trade_sn           varchar(64) not null
        comment '交易单号(网关)',
    request_header     text        not null
        comment '请求头',
    request_params     text        not null
        comment '请求参数',
    log_type           varchar(32) not null
        comment '日志类型: pay=>支付; refund=>退款; async_notify=>异步通知; sync_notify=>同步通知; query=>查询',
    --
    created_at         datetime(6) not null
        comment '创建时间',
    created_ip         varchar(32)
        comment '创建ip',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 所有和第三方支付交易日志表';

# ------------------------------------------------
DROP TABLE IF EXISTS `bmw_trade`;
CREATE TABLE `bmw_trade`
(
    id                 bigint auto_increment,
    access_app_id      bigint                        not null
        comment '接入方应用',
    access_platform_id bigint
        comment '最终接入平台(第三方回调时填充)',
    out_trade_sn       varchar(64)                   not null
        comment '交易单号(接入方)',
    --
    trade_sn           varchar(64)                   not null
        comment '交易单号(网关)',
    total_fee          decimal(10, 2) default '0'    not null
        comment '交易总金额',
    trade_status       varchar(32)    default 'init' not null
        comment '交易状态: init=>等待支付; wait_pay=>待付款; success=>完成支付; close=>交易已关闭; fail=>支付失败',
    notify_url         varchar(255)
        comment '通知接入应用的地址',
    --
    buyer_pay_fee      decimal(10, 2)
        comment '最终买家实际支付金额(第三方回调时填充)',
    payment_mode       varchar(32)
        comment '最终支付方式(第三方回调时填充)',
    trade_no           varchar(64)
        comment '最终第三方的交易单号(第三方回调填充)',
    payment_at         datetime(6)
        comment '最终第三方支付成功的时间(第三方回调填充)',
    notify_at          datetime(6)
        comment '接收到第三方支付通知的时间',
    --
    created_at         datetime(6)                   not null
        comment '创建时间',
    created_ip         varchar(32)
        comment '创建的IP',
    updated_at         datetime(6)
        comment '更新时间',
    updated_ip         varchar(32)
        comment '更新的IP',
    finish_at          datetime(6)
        comment '通知接入应用并完成交易的时间',
    expire_at          datetime(6)
        comment '账单过期时间',
    UNIQUE KEY (`access_app_id`, `out_trade_sn`),
    UNIQUE KEY (trade_sn),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 交易账单表';

DROP TABLE IF EXISTS `bmw_pay_record`;
CREATE TABLE `bmw_pay_record`
(
    id                 bigint auto_increment,
    trade_id           bigint      not null
        comment '交易账单',
    access_platform_id bigint      not null
        comment '接入平台',
    payment_mode       varchar(32)
        comment '支付方式',
    --
    created_at         datetime(6) not null
        comment '创建时间',
    created_ip         varchar(32)
        comment '创建的IP',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 支付记录表';

DROP TABLE IF EXISTS `bmw_refund_record`;
CREATE TABLE `bmw_refund_record`
(
    id                    bigint auto_increment,
    trade_id              bigint                       not null
        comment '交易账单',
    out_refund_sn         varchar(64)                  not null
        comment '退款单号(接入方)',
    --
    refund_sn             varchar(64)                  not null
        comment '退款单号(网关)',
    refund_trade_no       varchar(64)                  not null
        comment '退款单号(第三方)',
    refund_fee            decimal(10, 2)   default '0' not null
        comment '申请退款金额',
    refund_reason         varchar(255)
        comment '退款理由',
    refund_status         tinyint unsigned default '0' not null
        comment '退款状态: init=>未退款; pending=>退款处理中; success=>退款成功; fail=>退款失败',
    notify_url            varchar(255)
        comment '通知接入应用的地址',
    --
    settlement_refund_fee decimal(10, 2)   default '0' not null
        comment '实际退款金额',
    refund_at             datetime(6)
        comment '最终第三方退款成功的时间(第三方回调填充)',
    notify_at             datetime(6)
        comment '接收到第三方支付通知的时间',
    --
    created_at            datetime(6)                  not null
        comment '创建时间',
    created_ip            varchar(32)
        comment '创建ip',
    updated_at            datetime(6)
        comment '更新时间',
    update_ip             varchar(32)
        comment '更新ip',
    UNIQUE KEY (`out_refund_sn`),
    UNIQUE KEY (`refund_sn`),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 退款记录表';

# ------------------------------------------------
CREATE TABLE `bmw_payment_platform`
(
    id       bigint auto_increment,
    encoding varchar(32)         NOT NULL
        comment '编码',
    title    VARCHAR(255)        NOT NULL
        COMMENT '标题',
    enabled  TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    UNIQUE KEY (encoding),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 第三方支付平台表';

CREATE TABLE `bmw_payment_mode`
(
    id                  bigint auto_increment,
    payment_platform_id bigint              NOT NULL
        COMMENT '第三方支付平台',
    encoding            varchar(32)         NOT NULL
        comment '编码',
    title               VARCHAR(255)        NOT NULL
        COMMENT '标题',
    enabled             TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    UNIQUE KEY (encoding),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付网关] 第三方支付平台对应的支付方式表';


