DROP TABLE IF EXISTS `wl_company`;
CREATE TABLE `wl_company`
(
    id                BIGINT AUTO_INCREMENT,
    --
    title             VARCHAR(25) NOT NULL
        COMMENT '名称',
    tel               VARCHAR(25)
        COMMENT 'tel',
    remark            VARCHAR(255)
        COMMENT '备注',
    `province_adcode` VARCHAR(32)
        COMMENT '省区域编码',
    `city_adcode`     VARCHAR(32)
        COMMENT '市区区域编码',
    `district_adcode` VARCHAR(32)
        COMMENT '县区域编码',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[物流模块] 公司表';

DROP TABLE IF EXISTS `wl_warehouse`;
CREATE TABLE `wl_warehouse`
(
    id                BIGINT AUTO_INCREMENT,
    --
    `province_adcode` VARCHAR(32) NOT NULL
        COMMENT '省区域编码',
    `city_adcode`     VARCHAR(32) NOT NULL
        COMMENT '市区区域编码',
    `district_adcode` VARCHAR(32) NOT NULL
        COMMENT '县区域编码',
    company_id        BIGINT      NOT NULL
        COMMENT '公司ID',
    title             VARCHAR(25) NOT NULL
        COMMENT '名称',
    remark            VARCHAR(255)
        COMMENT '备注',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[物流模块] 仓库表';

DROP TABLE IF EXISTS `wl_logistics_line`;
CREATE TABLE `wl_logistics_line`
(
    id                BIGINT AUTO_INCREMENT,
    --
    warehouse_id      BIGINT      NOT NULL
        COMMENT '公司ID',
    unit_price        decimal(10, 2)
        COMMENT '单价',
    unit              VARCHAR(10)          DEFAULT 'unknown'
        COMMENT '单位(元/方)',
    aging             INT(5)
        COMMENT '时效(天)',
    shipping_methods  VARCHAR(10) NOT NULL DEFAULT 'unknown'
        COMMENT '物流方式',
    remark            VARCHAR(255)
        COMMENT '备注',
    `province_adcode` VARCHAR(32) NOT NULL
        COMMENT '[终点]省区域编码',
    `city_adcode`     VARCHAR(32) NOT NULL
        COMMENT '[终点]市区区域编码',
    `district_adcode` VARCHAR(32) NOT NULL
        COMMENT '[终点]县区域编码',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[物流模块] 物流线路表';

