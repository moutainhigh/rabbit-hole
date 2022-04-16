DROP TABLE IF EXISTS `com_link`;
CREATE TABLE `com_link`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `title`           VARCHAR(256) NOT NULL
        COMMENT '标题',
    `image_url`       VARCHAR(256)
        COMMENT '展览图',
    `link_url`        VARCHAR(256)
        COMMENT '链接',
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
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 链接表';
