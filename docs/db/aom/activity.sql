DROP TABLE IF EXISTS `aom_activity`;
CREATE TABLE `aom_activity`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(64) NOT NULL
        COMMENT '活动标题',
    `type`            VARCHAR(32) NOT NULL
        COMMENT '活动类型: receive=>领取活动',

    --
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`     BIGINT      NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 活动表';

DROP TABLE IF EXISTS `aom_activity_rule`;
CREATE TABLE `aom_activity_rule`
(
    `id` BIGINT AUTO_INCREMENT
        COMMENT 'ID',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 活动规则表';

DROP TABLE IF EXISTS `activity_user_record`;
CREATE TABLE `activity_user_record`
(
    `id` BIGINT AUTO_INCREMENT
        COMMENT 'ID',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[活动模块] 活动参与记录表';


# 活动         1 activity
# 活动参与规则  n activity_rule
# 活动礼物     n activity_gift
# 用户参与记录  n activity_user_record
# 活动 -> 领取 -> 优惠券。关联优惠券模版
# 活动 -> 领取 -> 兑换码。关联兑换码模版

# 优惠券模版 coupon
# 兑换码组 redeem_code_group
# 兑换码   redeem_code
# 兑换礼物 redeem_code_gift
