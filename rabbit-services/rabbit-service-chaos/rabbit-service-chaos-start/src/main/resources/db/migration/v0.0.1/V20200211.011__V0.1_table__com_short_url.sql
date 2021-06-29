DROP TABLE IF EXISTS `com_short_url`;
CREATE TABLE `com_short_url`
(
    id              BIGINT AUTO_INCREMENT,
    code            VARCHAR(8)    NOT NULL UNIQUE
        COMMENT '短链码',
    original_url    VARCHAR(1024) NOT NULL
        COMMENT '原链',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    creator         BIGINT        not null
        comment '创建人',
    created_at      DATETIME(6)   not null,
    last_updater    BIGINT        null,
    last_updated_at DATETIME(6)   null,

    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[基础模块] 短链接表';
