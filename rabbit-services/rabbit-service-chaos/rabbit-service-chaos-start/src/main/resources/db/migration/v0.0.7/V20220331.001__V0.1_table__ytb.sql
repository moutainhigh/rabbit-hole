DROP TABLE IF EXISTS `ytb_y2b_channel`;
CREATE TABLE `ytb_y2b_channel`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `client_id`       VARCHAR(256)
        COMMENT '授权服务',
    `channel_id`      VARCHAR(32)
        COMMENT '频道ID',
    `title`           VARCHAR(16)
        COMMENT '频道名称',
    `url`             VARCHAR(128)
        COMMENT '频道地址',
    `image_url`       VARCHAR(128)
        COMMENT '频道图片',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`client_id`, `channel_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[YouTube] YouTube频道表';
