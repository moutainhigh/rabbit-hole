DROP TABLE IF EXISTS `mina_share_content`;
CREATE TABLE `mina_share_content`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    encoding          VARCHAR(32) NOT NULL
        COMMENT '编码',
    title             VARCHAR(64)          DEFAULT 'unknown'
        COMMENT '任务名称',
    content           TEXT        NOT NULL
        COMMENT '内容',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[功能模块] 分享内容表';
