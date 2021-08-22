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
    id               BIGINT AUTO_INCREMENT,
    title            VARCHAR(100)   NOT NULL
        COMMENT '优惠券名称',
    coupon_type      VARCHAR(32)    NOT NULL DEFAULT 'fixed_amt'
        COMMENT '折扣方式：[fixed_amt=>满减；scale_amt=>折扣]',
    use_stint        VARCHAR(32)    NOT NULL DEFAULT 'none'
        COMMENT '可用类型：[none=>全场通用；category=>指定品类；product=>指定商品]',
    use_platform     VARCHAR(32)    NOT NULL DEFAULT 'all'
        COMMENT '可用平台：[all=>全部；mobile=>移动；pc=>PC]',
    use_instructions VARCHAR(255)
        COMMENT '优惠券使用说明',
    remark           VARCHAR(255)
        COMMENT '优惠券备注',
    min_point        DECIMAL(10, 2)          DEFAULT 0
        COMMENT '订单最低启用金额',
    credit           DECIMAL(10, 2) NOT NULL
        COMMENT '满减金额/折扣率',
    ceiling          DECIMAL(10, 2)          DEFAULT 10000
        COMMENT '优惠上限',
    --
    creator          BIGINT,
    created_at       DATETIME(6)    NOT NULL,
    last_updater     BIGINT         NULL,
    last_updated_at  DATETIME(6)    NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 优惠券表';


-- 优惠券账号拥有人表
DROP TABLE IF EXISTS `mkt_user_coupon`;
CREATE TABLE `mkt_user_coupon`
(
    id              BIGINT AUTO_INCREMENT,
    user_id         BIGINT       NOT NULL
        COMMENT '拥有人',
    coupon_id       BIGINT       NOT NULL
        COMMENT 'mkt_COUPON ID',
    coupon_no       VARCHAR(100) NOT NULL
        COMMENT '优惠券编号',
    status          VARCHAR(32)  NOT NULL DEFAULT 0
        COMMENT '优惠券状态',
    expired_flag    TINYINT(1)   NOT NULL DEFAULT 0
        COMMENT '过期状态',
    start_at        DATETIME(6)  NOT NULL
        COMMENT '优惠券生效时间',
    end_at          DATETIME(6)  NOT NULL
        COMMENT '优惠券失效时间',
    --
    used_at         DATETIME(6)
        COMMENT '使用时间',
    used_amt        DECIMAL(10, 2)
        COMMENT '实际抵扣金额',
    --
    creator         BIGINT,
    created_at      DATETIME(6)  NOT NULL,
    last_updater    BIGINT       NULL,
    last_updated_at DATETIME(6)  NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 用户优惠券表';

-- 优惠券可用商品表
DROP TABLE IF EXISTS `mkt_coupon_product_ref`;
CREATE TABLE `mkt_coupon_product_ref`
(
    id         BIGINT AUTO_INCREMENT,
    coupon_id  BIGINT NOT NULL
        COMMENT '优惠券ID',
    product_id BIGINT NOT NULL
        COMMENT '产品ID',
    UNIQUE KEY (`coupon_id`, `product_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 优惠条件-可用商品表';


-- 优惠券可用商品品类表
DROP TABLE IF EXISTS `mkt_coupon_product_category_ref`;
CREATE TABLE `mkt_coupon_product_category_ref`
(
    id                  BIGINT AUTO_INCREMENT,
    coupon_id           BIGINT NOT NULL
        COMMENT '优惠券ID',
    product_category_id BIGINT NOT NULL
        COMMENT '产品ID',
    UNIQUE KEY (`coupon_id`, `product_category_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[促销模块] 优惠条件-可用商品品类表';
