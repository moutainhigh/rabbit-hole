DROP TABLE IF EXISTS `rcm_doc`;
CREATE TABLE `rcm_doc`
(
    id                BIGINT AUTO_INCREMENT,
    ref_type          VARCHAR(32) NOT NULL
        comment '引用类型',
    ref_id            BIGINT      NOT NULL
        comment '引用对象',
    owner_user_id     BIGINT      NOT NULL
        comment '拥有人',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY `idx_1` (`ref_type`, `ref_id`),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[内容模块] 文档对象表';

DROP TABLE IF EXISTS `rcm_doc_content`;
CREATE TABLE `rcm_doc_content`
(
    id                BIGINT AUTO_INCREMENT,
    --
    `doc_id`          BIGINT      NOT NULL
        comment '文档对象',
    `drop_flag`       DATETIME(6)          DEFAULT CURRENT_TIMESTAMP(6)
        comment '作废标记',
    `doctype`         VARCHAR(32) NOT NULL
        comment '文本类型: text=>普通文本;rich=>富文本;markdown=>.md',
    `content`         TEXT
        COMMENT '内容',
    `description`     TEXT
        COMMENT '概述',
    `keyword`         VARCHAR(512)
        COMMENT '关键词(;分割)',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY `idx_1` (`doc_id`, `drop_flag`),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[内容模块] 文档内容表';
# <body doctype="rich"></body>

DROP TABLE IF EXISTS `rcm_doc_version`;
CREATE TABLE `rcm_doc_version`
(
    id                BIGINT AUTO_INCREMENT,
    title             VARCHAR(64) NOT NULL
        comment '版本名称',
    doc_content_id    BIGINT      NOT NULL
        comment '文档内容对象',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY `idx_1` (`doc_content_id`),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[内容模块] 文档内容版本表';
