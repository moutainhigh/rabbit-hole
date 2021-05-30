DROP TABLE IF EXISTS `mina_game_card`;
CREATE TABLE `mina_game_card`
(
    id                BIGINT AUTO_INCREMENT,
    title             VARCHAR(128)
        COMMENT '名称',
    logo_url          VARCHAR(255)
        COMMENT 'LOGO',
    game_url          VARCHAR(255)
        COMMENT 'ROM链接',
    remark            VARCHAR(255)
        COMMENT '备注',
    tags              VARCHAR(125)
        COMMENT '标签(暂用;分隔)',
    priority          INT(10)             NOT NULL DEFAULT 1000
        COMMENT '排序,默认:1000',
    created_at        TIMESTAMP(6)        NOT NULL
        COMMENT '创建时间',
    creator           BIGINT              NOT NULL
        COMMENT '创建人',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[小程序模块] 游戏表';


DROP TABLE IF EXISTS `mina_app_card`;
CREATE TABLE `mina_app_card`
(
    id                BIGINT AUTO_INCREMENT,
    title             VARCHAR(32)
        COMMENT '名称',
    logo_url          VARCHAR(255)
        COMMENT 'LOGO',
    page_url          VARCHAR(255)
        COMMENT '小程序链接(跳转)',
    appid             VARCHAR(32)
        COMMENT '小程序appid(跳转)',
    remark            VARCHAR(255)
        COMMENT '备注',
    tags              VARCHAR(125)
        COMMENT '标签(暂用;分隔)',
    priority          INT(10)             NOT NULL DEFAULT 1000
        COMMENT '排序,默认:1000',
    created_at        TIMESTAMP(6)        NOT NULL
        COMMENT '创建时间',
    creator           BIGINT              NOT NULL
        COMMENT '创建人',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    `is_top`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0
        COMMENT '是否置顶',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[小程序模块] 游戏表';
