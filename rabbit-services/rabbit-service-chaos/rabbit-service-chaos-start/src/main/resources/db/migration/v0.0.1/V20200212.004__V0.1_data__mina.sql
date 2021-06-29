DROP TABLE IF EXISTS `mina_status_material`;
CREATE TABLE `mina_status_material`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '标题',
    `type`            VARCHAR(16) NOT NULL
        COMMENT '素材类型: video=>视频;image=>图片',
    `url`             text        NOT NULL
        COMMENT '素材地址',
    --
    `created_at`      DATETIME(6) NOT NULL
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
    COMMENT = '[小程序模块] 状态素材表';
