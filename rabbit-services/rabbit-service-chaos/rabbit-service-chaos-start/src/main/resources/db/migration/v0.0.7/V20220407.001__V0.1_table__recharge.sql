DROP TABLE IF EXISTS `mina_recharge_account`;
CREATE TABLE `mina_recharge_account`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    owner_user_id     BIGINT         NOT NULL
        COMMENT '所属用户',
    free_rate         DECIMAL(20, 2)
        COMMENT '手续费率(收手续费), NULL 代表使用默认值',
    avail_amt         DECIMAL(20, 2) NOT NULL DEFAULT 0
        COMMENT '可用额度(元)',
    apikey            VARCHAR(32)    NOT NULL
        COMMENT 'APIKEY',
    --
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    UNIQUE KEY (`owner_user_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[功能模块] 充值账户表';


DROP TABLE IF EXISTS `mina_recharge_order`;
CREATE TABLE `mina_recharge_order`
(
    `id`               BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    out_order_no       VARCHAR(32)    NOT NULL
        COMMENT '外部订单号',
    order_no           VARCHAR(32)    NOT NULL
        COMMENT '内部订单号',
    product_name       VARCHAR(128)   NOT NULL
        COMMENT '产品名称',
    product_id         VARCHAR(32)    NOT NULL
        COMMENT '产品编号',
    account            VARCHAR(32)    NOT NULL
        COMMENT '充值账号',
    notify_url         VARCHAR(128)
        COMMENT '通知地址',
    total_amt          DECIMAL(20, 2) NOT NULL
        COMMENT '扣费金额',

    notify_recount     TINYINT(5)     NOT NULL DEFAULT 0
        COMMENT '已通知次数',
    last_notify_result VARCHAR(1024)
        COMMENT '最后一次通知结果',
    last_notify_at     DATETIME(6)
        COMMENT '最后一次通知时间',
    next_notify_at     DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '下一次通知时间',
    finish_notify_at   DATETIME(6)
        COMMENT '完成通知时间',

    status             VARCHAR(32)    NOT NULL DEFAULT 'executing'
        COMMENT '任务状态: [executing=>执行中, success=>成功, failed=>失败]',
    fail_reason        VARCHAR(1024)  NULL     DEFAULT NULL
        COMMENT '失败原因',
    --
    `created_at`       DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`          BIGINT
        COMMENT '创建者',
    `last_updated_at`  DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`     BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`creator`, `out_order_no`),
    UNIQUE KEY (`order_no`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[功能模块] 充值单据表';

