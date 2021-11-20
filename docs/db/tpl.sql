DROP TABLE IF EXISTS `mdl_tpl`;
CREATE TABLE `mdl_tpl`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`     BIGINT      NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[mdl模块] 模版表';
