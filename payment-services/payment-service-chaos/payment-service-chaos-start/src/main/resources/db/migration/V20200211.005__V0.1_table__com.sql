DROP TABLE IF EXISTS `com_district`;
CREATE TABLE `com_district`
(
    id        BIGINT AUTO_INCREMENT,
    parent_id bigint,
    --
    tree_path varchar(255)        NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    enabled   TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态[0:为禁用状态;1:为正常状态]',
    ad_code   VARCHAR(32)         NOT NULL
        COMMENT '区域编码',
    city_code VARCHAR(32)
        COMMENT '城市编码',
    level     VARCHAR(32)         NOT NULL
        COMMENT '城市规划级别',
    lat       decimal(10, 6)
        COMMENT '中心(纬度)',
    lng       decimal(10, 6)
        COMMENT '中心(经度)',
    title     VARCHAR(32)         NOT NULL
        COMMENT '名称',
    --
    UNIQUE KEY (tree_path),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[基础模块] 城市规划表';
