DROP TABLE IF EXISTS `ums_user_address`;
CREATE TABLE `ums_user_address`
(
    `id`           BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `user_id`      BIGINT       NOT NULL
        COMMENT 'ID',
    `name`         VARCHAR(8)   NOT NULL
        COMMENT '收件人姓名',
    `phone`        VARCHAR(16)  NOT NULL
        COMMENT '收件人手机号',
    `post_code`    VARCHAR(100)
        COMMENT '邮政编码',
    `province`     VARCHAR(100) NOT NULL
        COMMENT '省份',
    `city`         VARCHAR(100) NOT NULL
        COMMENT '城市',
    `region`       VARCHAR(100) NOT NULL
        COMMENT '区',
    `address`      VARCHAR(128)
        COMMENT '详细地址(街道)',
    `adcode`       VARCHAR(32)  NOT NULL
        COMMENT '区域编码',
    `default_flag` TINYINT(1)   NOT NULL
        DEFAULT 0,
    --
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[用户模块] 收货地址表';
