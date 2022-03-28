DROP TABLE IF EXISTS `com_short_url`;
CREATE TABLE `com_short_url`
(
    id                BIGINT AUTO_INCREMENT,
    code              VARCHAR(8)          NOT NULL UNIQUE
        COMMENT '短链码',
    original_url      VARCHAR(1024)       NOT NULL
        COMMENT '原链',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)         NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)         NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[基础模块] 短链接表';
