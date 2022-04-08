DROP TABLE IF EXISTS `mina_recharge_order`;
CREATE TABLE `mina_recharge_order`
(
    `id`               BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    access_id          VARCHAR(32)    NOT NULL DEFAULT 'unknown'
        COMMENT '接入编号',
    out_order_no       VARCHAR(32)    NOT NULL
        COMMENT '外部订单号',
    order_no           VARCHAR(32)    NOT NULL
        COMMENT '内部订单号',
    product_id         VARCHAR(32)    NOT NULL
        COMMENT '产品编号',
    account            VARCHAR(32)    NOT NULL
        COMMENT '充值账号',
    max_cost_amt       DECIMAL(20, 2) NOT NULL
        COMMENT '可接受成本',
    notify_url         VARCHAR(128)
        COMMENT '通知地址',
    notify_recount     TINYINT(5)     NOT NULL DEFAULT 0
        COMMENT '已通知次数',
    last_notify_result VARCHAR(128)
        COMMENT '最后一次通知结果',
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
    UNIQUE KEY (`access_id`, `out_order_no`),
    UNIQUE KEY (`order_no`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[功能模块] 充值单据表';

