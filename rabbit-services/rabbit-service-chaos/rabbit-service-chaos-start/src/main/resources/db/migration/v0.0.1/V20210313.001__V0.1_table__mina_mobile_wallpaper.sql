DROP TABLE IF EXISTS `mina_mobile_wallpaper`;
CREATE TABLE `mina_mobile_wallpaper`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(64)         NOT NULL
        COMMENT '标题',
    `remark`          VARCHAR(255)
        COMMENT '描述',
    `color`           VARCHAR(32)
        COMMENT '主色调',
    full_url          VARCHAR(255)
        COMMENT '默认',
    file_hash         VARCHAR(64)
        COMMENT '文件hash',
    data_source       VARCHAR(32)
        COMMENT '数据来源',
    tags              VARCHAR(125)
        COMMENT '标签(暂用;分隔)',
    priority          INT(10)             NOT NULL DEFAULT 1000
        COMMENT '排序,默认:1000',
    enabled           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)         NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[模块] 手机壁纸表';
