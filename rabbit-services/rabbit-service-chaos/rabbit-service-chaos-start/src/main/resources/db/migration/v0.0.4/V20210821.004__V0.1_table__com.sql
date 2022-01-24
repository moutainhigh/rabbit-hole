DROP TABLE IF EXISTS `com_user_address`;
create table com_user_address
(
    id                BIGINT AUTO_INCREMENT,
    owner_user_id     BIGINT       NOT NULL
        COMMENT '所属用户',
    type              VARCHAR(64)  NOT NULL
        COMMENT '地址类型(receiver=>收货地址;delivery=>发货地址;refund=>退货地址)',
    name              VARCHAR(100) NULL
        COMMENT '姓名',
    tel               VARCHAR(32)  NULL
        COMMENT '电话',
    postcode          VARCHAR(32)  NULL
        COMMENT '邮编',
    adcode            VARCHAR(32)  NULL
        COMMENT '区域编码',
    province          VARCHAR(32)  NULL
        COMMENT '省份/直辖市',
    city              VARCHAR(32)  NULL
        COMMENT '城市',
    region            VARCHAR(32)  NULL
        COMMENT '区',
    address           VARCHAR(200) NULL
        COMMENT '详细地址',
    default_flag      tinyint(1)   NOT NULL DEFAULT 0
        COMMENT '是否默认地址',
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
    COMMENT ='[用户模块] 物流地址表';
