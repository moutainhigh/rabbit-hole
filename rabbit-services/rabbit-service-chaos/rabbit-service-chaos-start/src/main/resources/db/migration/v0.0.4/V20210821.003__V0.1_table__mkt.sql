-- 营销活动表
# DROP TABLE IF EXISTS `mkt_activity`;
# CREATE TABLE `mkt_activity`
# (
#     id            BIGINT AUTO_INCREMENT,
#     title         VARCHAR(100) NOT NULL
#         COMMENT '活动名称',
#     remark        VARCHAR(100)
#         COMMENT '活动描述',
#     trigger_type  TINYINT(1)   NOT NULL
#         COMMENT '触发类型: [0:注册活动; 1:邀请有奖]',
#     activity_type TINYINT(1)   NOT NULL
#         COMMENT '营销活动类型: [0: 送优惠券]',
#     start_at      DATETIME(6)  NOT NULL
#         COMMENT '活动生效时间',
#     end_at        DATETIME(6)  NOT NULL
#         COMMENT '活动失效时间',
#     sort          INT          NOT NULL DEFAULT 1000
#         COMMENT '活动优先级',
# upper_limit           TINYINT(10)    DEFAULT 1
#         COMMENT '可同时拥有上限',
#     --
#     creator         BIGINT       NOT NULL,
#     created_at      DATETIME(6)  NOT NULL,
#     last_updater    BIGINT       NULL,
#     last_updated_at DATETIME(6)  NULL,
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#     COMMENT ='营销活动表';


-- 优惠券表
DROP TABLE IF EXISTS `mkt_coupon`;
CREATE TABLE `mkt_coupon`
(
    id                BIGINT AUTO_INCREMENT,
    title             VARCHAR(100)   NOT NULL
        COMMENT '优惠券名称',
    type              VARCHAR(32)    NOT NULL DEFAULT 'fixed_amt'
        COMMENT '折扣方式：[fixed_amt=>满减；scale_amt=>折扣]',
    use_instructions  VARCHAR(255)
        COMMENT '优惠券使用说明',
    remark            VARCHAR(255)
        COMMENT '优惠券备注',
    credit            DECIMAL(10, 2) NOT NULL
        COMMENT '满减金额/折扣率',
    ceiling_amt       DECIMAL(10, 2)          DEFAULT 10000
        COMMENT '优惠上限',
    --
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 优惠券表';

DROP TABLE IF EXISTS `mkt_stint_rule`;
CREATE TABLE `mkt_stint_rule`
(
    id                BIGINT AUTO_INCREMENT,
    title             VARCHAR(100) NOT NULL
        COMMENT '规则名称',
    type              VARCHAR(64)  NOT NULL
        COMMENT '限制类型: limit_platform=>可用平台; limit_category=>可用品类; limit_product=>可用商品; limit_lowest_amt=>可用最低金额',
    rule              JSON         NOT NULL
        COMMENT '限制规则',
    --
    `deleted_at`      DATETIME(6)           DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 限制规则表';

-- 优惠券账号拥有人表
DROP TABLE IF EXISTS `mkt_user_coupon`;
CREATE TABLE `mkt_user_coupon`
(
    id                BIGINT AUTO_INCREMENT,
    owner_user_id     BIGINT       NOT NULL
        COMMENT '拥有人',
    coupon_id         BIGINT       NOT NULL
        COMMENT '优惠券',
    encoding          VARCHAR(100) NOT NULL
        COMMENT '优惠券编号',
    status            VARCHAR(32)  NOT NULL
        COMMENT '优惠券状态',
    expired_flag      TINYINT(1)   NOT NULL DEFAULT 0
        COMMENT '过期状态',
    start_at          DATETIME(6)  NOT NULL
        COMMENT '优惠券生效时间',
    end_at            DATETIME(6)  NOT NULL
        COMMENT '优惠券失效时间',
    --
    used_at           DATETIME(6)
        COMMENT '使用时间',
    used_amt          DECIMAL(10, 2)
        COMMENT '实际抵扣金额',
    --
    `deleted_at`      DATETIME(6)           DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`encoding`, `deleted_at`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 用户优惠券表';

-- 优惠券x限制规则
DROP TABLE IF EXISTS `mkt_coupon_stint_rule_ref`;
CREATE TABLE `mkt_coupon_stint_rule_ref`
(
    id        BIGINT AUTO_INCREMENT,
    coupon_id BIGINT NOT NULL
        COMMENT '规则',
    rule_id   BIGINT NOT NULL
        COMMENT '商品',
    UNIQUE KEY (`rule_id`, `coupon_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 优惠券-限制规则表';

# 优惠券表 -> 多个限制规则
