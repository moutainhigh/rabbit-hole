DROP TABLE IF EXISTS `aom_redeem_code_group`;
CREATE TABLE `aom_redeem_code_group`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `total_count`     INT(5)
        COMMENT '总生成数量',
    `redeemed_count`  INT(5)
        COMMENT '已兑换数量',
    --
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`     BIGINT      NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 兑换码组表';

DROP TABLE IF EXISTS `aom_redeem_code`;
CREATE TABLE `aom_redeem_code`
(
    `id`               BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `group_id`         BIGINT      NOT NULL
        COMMENT '兑换码组',
    `code`             VARCHAR(64) NOT NULL
        COMMENT '兑换码',
    `expire_at`        DATETIME(6)
        COMMENT '失效日期',
    `redeemed_at`      DATETIME(6)
        COMMENT '兑换日期',
    `redeemed_user_id` BIGINT
        COMMENT '兑换用户',
    `status`           VARCHAR(32) NOT NULL DEFAULT 'wait_redeemed'
        COMMENT '状态: wait_redeemed=>待兑换; redeemed=>已兑换;',
    --
    `tenant_id`        BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`      BIGINT      NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`       DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`          BIGINT
        COMMENT '创建者',
    `last_updated_at`  DATETIME(6) NULL     DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`     BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 兑换码表';

DROP TABLE IF EXISTS `aom_receive_gift`;
CREATE TABLE `aom_receive_gift`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `ref_type`        VARCHAR(32) NOT NULL
        COMMENT '活动类型',
    `ref_id`          BIGINT      NOT NULL
        COMMENT '活动',
    `gift_type`       VARCHAR(32) NOT NULL
        comment '礼物类型',
    `gift_id`         BIGINT      NOT NULL
        comment '礼物对象',
    `quantity`        INT(5)      NOT NULL DEFAULT 1
        comment '礼物数量',
    --
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`     BIGINT      NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 礼物表';
