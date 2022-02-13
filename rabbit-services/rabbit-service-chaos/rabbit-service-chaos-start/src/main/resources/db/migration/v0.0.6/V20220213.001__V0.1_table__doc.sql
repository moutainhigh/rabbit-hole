DROP TABLE IF EXISTS `com_doc_text`;
CREATE TABLE `com_doc_text`
(
    id                BIGINT AUTO_INCREMENT,
    --
    ref_type          VARCHAR(32) NOT NULL
        comment '对象类型',
    ref_id            BIGINT      NOT NULL
        comment '对象',
    doctype           VARCHAR(32) NOT NULL
        comment '文本类型: text=>普通文本;rich=>富文本;markdown=>.md',
    text              TEXT
        COMMENT '文本',
    `keyword`         VARCHAR(512)
        COMMENT '关键词(;分割)',
    priority          INT         NOT NULL DEFAULT 1000
        COMMENT '排序,默认:1000',
    --
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0
        COMMENT '租户',
    `deleted_at`      DATETIME(6)          DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT               DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[内容模块] 富文本内容表';
# <body doctype="rich"></body>

