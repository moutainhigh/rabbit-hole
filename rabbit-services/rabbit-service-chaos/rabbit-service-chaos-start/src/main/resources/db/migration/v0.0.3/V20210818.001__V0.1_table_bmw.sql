DROP TABLE IF EXISTS `bmw_trade_order`;
CREATE TABLE `bmw_trade_order`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `access_mch_id`   BIGINT         NOT NULL
        COMMENT '接入商户',
    `trade_no`        VARCHAR(64)    NOT NULL
        COMMENT '交易单号',
    `out_trade_no`    VARCHAR(64)    NOT NULL
        COMMENT '交易单号(接入商户)',
    `guarantee_flag`  TINYINT(1)     NOT NULL DEFAULT 0
        COMMENT '是否担保交易',
    `trade_amt`       DECIMAL(20, 2) NOT NULL DEFAULT 0
        COMMENT '交易金额',
    `refund_amt`      DECIMAL(20, 2) NOT NULL DEFAULT 0
        COMMENT '退款金额',
    `status`          VARCHAR(32)    NOT NULL DEFAULT 'processing'
        COMMENT '交易状态: processing=>进行中; payed=>已支付; success=>成功; fail=>失败; cancelled=>已取消; closed=>已关闭',
    `plan_close_at`   DATETIME(6)    NOT NULL
        COMMENT '计划关单时间',
    `order_desc`      VARCHAR(256)   NULL
        COMMENT '订单描述',
    `attach`          VARCHAR(512)   NULL
        COMMENT '附加参数',
    `reason`          VARCHAR(512)   NULL
        COMMENT '关单原因',
    `notify_url`      VARCHAR(512)   NULL
        COMMENT '完结通知地址',
    `front_jump_url`  VARCHAR(256)   NULL
        COMMENT '支付后前端回跳地址',
    --
    `finished_at`     DATETIME(6)    NULL
        COMMENT '完成时间',
    `payment_mch_id`  BIGINT         NULL
        COMMENT '支付商户(最终)',
    `real_pay_amt`    DECIMAL(20, 2) NULL     DEFAULT NULL
        COMMENT '用户实际支付金额(最终)',
    `pay_type`        VARCHAR(32)    NULL     DEFAULT NULL
        COMMENT '支付类型(最终)',
    `pay_act_id`      BIGINT         NULL     DEFAULT NULL
        COMMENT '支付账户(最终)',
    `pay_record_id`   BIGINT         NULL     DEFAULT NULL
        COMMENT '支付记录(最终)',
    --
    `created_at`      DATETIME(6)    NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`trade_no`),
    UNIQUE KEY (`access_mch_id`, `out_trade_no`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 交易单表';

DROP TABLE IF EXISTS `bmw_refund_record`;
CREATE TABLE `bmw_refund_record`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `access_mch_id`   BIGINT         NOT NULL
        COMMENT '接入商户',
    `trade_order_id`  BIGINT         NOT NULL
        COMMENT '交易单',
    `refund_no`       VARCHAR(64)    NOT NULL
        COMMENT '退款单号',
    `out_refund_no`   VARCHAR(64)    NOT NULL
        COMMENT '退款单号(接入商户)',
    `refund_amt`      DECIMAL(20, 2) NOT NULL DEFAULT 0
        COMMENT '退款金额',
    `refund_act_id`   BIGINT         NOT NULL
        COMMENT '退款账户',
    `status`          VARCHAR(32)    NOT NULL DEFAULT 'processing'
        COMMENT '退款状态: processing=>处理中; success=>成功; fail=>失败;',
    `finished_at`     DATETIME(6)    NULL
        COMMENT '完成时间',
    `notify_url`      VARCHAR(512)   NULL
        COMMENT '完结通知地址',
    `attach`          VARCHAR(512)   NULL
        COMMENT '附加参数',
    `reason`          VARCHAR(512)   NULL
        COMMENT '退款原因',
    --
    `created_at`      DATETIME(6)    NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`refund_no`),
    UNIQUE KEY (`access_mch_id`, `out_refund_no`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 退款记录表';

DROP TABLE IF EXISTS `bmw_pay_record`;
CREATE TABLE `bmw_pay_record`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `trade_order_id`  BIGINT      NOT NULL
        COMMENT '交易单',
    `pay_type`        VARCHAR(32) NOT NULL
        COMMENT '支付类型',
    `payment_mch_id`  BIGINT      NOT NULL
        COMMENT '支付商户',
    `pay_act_id`      BIGINT
        COMMENT '支付账户',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付记录表';

DROP TABLE IF EXISTS `bmw_account`;
CREATE TABLE `bmw_account`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `user_id`         BIGINT      NOT NULL
        COMMENT '所属用户',
    `access_mch_id`   BIGINT      NOT NULL
        COMMENT '接入商户',
    `use_scenes`      VARCHAR(32) NOT NULL DEFAULT 'none'
        COMMENT '使用场景',
    `account`         VARCHAR(32) NOT NULL
        COMMENT '账号',
    `mch_account`     VARCHAR(32) NOT NULL
        COMMENT '账号(支付商户)',
    `payment_mch_id`  BIGINT      NOT NULL
        COMMENT '支付商户',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`user_id`, `access_mch_id`, `payment_mch_id`, `use_scenes`),
    UNIQUE KEY (`account`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付账户表';

DROP TABLE IF EXISTS `bmw_account_flow`;
CREATE TABLE `bmw_account_flow`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `ref_type`        VARCHAR(32)    NOT NULL
        comment '业务类型',
    `ref_id`          BIGINT         NOT NULL
        comment '业务对象',
    `type`            VARCHAR(32)    NOT NULL
        COMMENT '记账类型: refund=>退款; trade=>交易; 提现; 分账',
    `dire`            VARCHAR(32)    NOT NULL
        COMMENT '记账方向: in=>入账; out=>出账',
    `booking_act_id`  BIGINT         NOT NULL
        COMMENT '记账账户',
    `booking_amt`     DECIMAL(20, 2) NOT NULL DEFAULT 0
        COMMENT '记账金额',
    `booking_at`      DATETIME(6)    NOT NULL
        comment '记账时间',
    --
    `created_at`      DATETIME(6)    NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 账户流水表';

DROP TABLE IF EXISTS `bmw_payment_mch`;
CREATE TABLE `bmw_payment_mch`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '编码',
    `type`            VARCHAR(32) NOT NULL
        COMMENT '类型: 支付宝、微信',
    `config`          JSON
        COMMENT '商户配置',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户表';


DROP TABLE IF EXISTS `bmw_payment_mch_config_alipay`;
CREATE TABLE `bmw_payment_mch_config_alipay`
(
    id          bigint auto_increment,
    appid       varchar(255)        NOT NULL
        COMMENT 'appid',
    public_key  text                NOT NULL
        COMMENT 'public key',
    private_key text                NOT NULL
        COMMENT 'private key',
    is_dev      TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '是否测试模式',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付模块] 支付宝配置表';

DROP TABLE IF EXISTS `bmw_payment_mch_config_wxpay`;
CREATE TABLE `bmw_payment_mch_config_wxpay`
(
    id       bigint auto_increment,
    appid    varchar(255) not null
        COMMENT 'appid',
    mch_id   varchar(255) not null
        COMMENT 'mch_id',
    key_str  varchar(255) not null
        COMMENT 'key',
    cert_str text         not null
        COMMENT 'cert file text',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '[支付模块] 微信支付配置表';

DROP TABLE IF EXISTS `bmw_access_mch`;
CREATE TABLE `bmw_access_mch`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '标题',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '商户编码',
    `config`          JSON
        COMMENT '商户配置',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 接入商户表';

-- 接入商户支持支付类型 [start]
DROP TABLE IF EXISTS `bmw_payment_access_ref`;
CREATE TABLE `bmw_payment_access_ref`
(
    `id`             BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `payment_mch_id` BIGINT NOT NULL
        COMMENT '支付商户',
    `access_mch_id`  BIGINT NOT NULL
        COMMENT '接入商户',
    UNIQUE KEY (payment_mch_id, access_mch_id),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户 x 接入商户表';

DROP TABLE IF EXISTS `bmw_payment_support`;
CREATE TABLE `bmw_payment_support`
(
    `id`             BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `payment_mch_id` BIGINT      NOT NULL
        COMMENT '支付商户',
    `pay_type`       VARCHAR(32) NOT NULL
        COMMENT '支付类型',
    UNIQUE KEY (payment_mch_id, pay_type),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户支持的支付类型表';
-- 接入商户支持支付类型 [end]

-- 基础数据 [start]
DROP TABLE IF EXISTS `bmw_payment_mch_type`;
CREATE TABLE `bmw_payment_mch_type`
(
    `id`       BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding` VARCHAR(32)                  NOT NULL
        COMMENT '支付商户编号',
    `title`    VARCHAR(32)                  NOT NULL
        COMMENT '支付商户名称',
    `enabled`  tinyint unsigned default '1' not null
        comment '启用状态',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户的商户类型表';

DROP TABLE IF EXISTS `bmw_payment_mch_pay_type`;
CREATE TABLE `bmw_payment_mch_pay_type`
(
    `id`                  BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `payment_mch_type_id` BIGINT                       NOT NULL
        COMMENT '商户类型',
    `encoding`            VARCHAR(32)                  NOT NULL
        COMMENT '支付类型编号',
    `title`               VARCHAR(32)                  NOT NULL
        COMMENT '支付类型',
    `enabled`             tinyint unsigned default '1' not null
        comment '启用状态',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户的支付类型表';
-- 基础数据 [end]

-- 支付场景 [start]
DROP TABLE IF EXISTS `bmw_pay_scene`;
CREATE TABLE `bmw_pay_scene`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `access_mch_id`   BIGINT      NOT NULL
        COMMENT '接入商户',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '场景编号',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '标题',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (access_mch_id, encoding),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付场景表';

DROP TABLE IF EXISTS `bmw_pay_scene_support`;
CREATE TABLE `bmw_pay_scene_support`
(
    `id`             BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `pay_scene_id`   BIGINT      NOT NULL
        COMMENT '支付场景',
    `payment_mch_id` BIGINT      NOT NULL
        COMMENT '支付商户',
    `pay_type`       VARCHAR(32) NOT NULL
        COMMENT '支付类型',
    `title`          VARCHAR(32) NOT NULL
        COMMENT '支付类型名称',
    UNIQUE KEY (pay_scene_id, pay_type),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付场景支持的支付类型';
-- 支付场景 [end]

DROP TABLE IF EXISTS `bmw_payment_mch_record`;
CREATE TABLE `bmw_payment_mch_record`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `ref_type`        VARCHAR(32)  NOT NULL
        comment '业务类型',
    `ref_id`          BIGINT       NOT NULL
        comment '业务对象',
    `biz_type`        VARCHAR(32)  NOT NULL
        comment '行为类型',
    `payment_mch_id`  BIGINT       NOT NULL
        COMMENT '支付商户',
    `attach`          VARCHAR(512) NULL
        COMMENT '附加参数',
    `log_id`          BIGINT       NULL
        comment '请求日志记录',
    --
    `created_at`      DATETIME(6)  NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 支付商户业务记录表';

DROP TABLE IF EXISTS `bmw_sync_access_mch_task`;
CREATE TABLE `bmw_sync_access_mch_task`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `ref_type`        VARCHAR(32)  NOT NULL
        comment '业务类型',
    `ref_id`          BIGINT       NOT NULL
        comment '业务对象',
    `task_type`       VARCHAR(32)  NOT NULL
        comment '任务类型',
    `notify_count`    INT          NOT NULL DEFAULT 1
        comment '通知轮次',
    `plan_notify_at`  DATETIME(6)  NOT NULL
        COMMENT '计划通知时间',
    `notify_url`      VARCHAR(512) NULL
        comment '通知地址',
    `notify_body`     TEXT         NULL
        COMMENT '通知内容',
    `return_body`     TEXT         NULL
        COMMENT '响应内容',
    `finished_at`     DATETIME(6)  NULL
        COMMENT '完成通知时间',
    `status`          VARCHAR(32)  NOT NULL DEFAULT 'init'
        comment '通知状态: init=>初始化; processing=>进行中; done=>完成',
    --
    `created_at`      DATETIME(6)  NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[支付模块] 通知接入商户任务表';
