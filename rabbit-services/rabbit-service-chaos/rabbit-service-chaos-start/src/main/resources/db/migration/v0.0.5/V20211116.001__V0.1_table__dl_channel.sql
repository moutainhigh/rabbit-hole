DROP TABLE IF EXISTS `dl_proxy_channel`;
CREATE TABLE `dl_proxy_channel`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `user_id`         BIGINT              NOT NULL
        COMMENT '拥有人',
    `channel_id`      VARCHAR(64)         NOT NULL
        COMMENT '隧道唯一标识',
    type              VARCHAR(32)         NOT NULL
        comment '隧道类型',
    local_port        INTEGER(6)
        comment '本地端口',
    local_ip          INTEGER(6)
        comment '本地IP',
    custom_domains    VARCHAR(64)
        comment '自定义域名',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `tenant_id`       BIGINT              NOT NULL DEFAULT 0
        COMMENT '租户',
    `delete_flag`     BIGINT              NOT NULL DEFAULT 0
        COMMENT '删除状态(未删除 != 0)',
    `created_at`      DATETIME(6)         NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)         NULL
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    CONSTRAINT idx_1 UNIQUE (`channel_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[代理] 用户隧道表';
