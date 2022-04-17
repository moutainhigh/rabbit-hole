DROP TABLE IF EXISTS `rcm_post_category`;
CREATE TABLE `rcm_post_category`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding`        VARCHAR(32)  NOT NULL
        COMMENT '编号',
    `title`           VARCHAR(256) NOT NULL
        COMMENT '标题',
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
    UNIQUE KEY (`encoding`),

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[内容模块] 帖文类目表';

DROP TABLE IF EXISTS `rcm_post`;
CREATE TABLE `rcm_post`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(256) NOT NULL
        COMMENT '标题',
    `summary`         VARCHAR(512)
        COMMENT '简介',
    `doc_text_id`     BIGINT
        COMMENT '内容',
    `tags`            VARCHAR(512)
        COMMENT '标签',
    `category_id`     BIGINT       NOT NULL
        COMMENT '类目',
    `thumbnail_url`   VARCHAR(256)
        COMMENT '展览图',
    `view_count`      INT(11)      NOT NULL DEFAULT 0
        COMMENT '观看次数',
    `like_count`      INT(11)      NOT NULL DEFAULT 0
        COMMENT '喜欢次数',
    `heat_idx`        INT(11)      NOT NULL DEFAULT 0
        COMMENT '热度指数',
    `original_link`   VARCHAR(256)
        COMMENT '原文链接',
    `drafted`         BOOLEAN      NOT NULL DEFAULT true
        COMMENT '草稿状态',
    `enabled`         BOOLEAN      NOT NULL DEFAULT true
        COMMENT '启用状态',
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
    COMMENT = '[内容模块] 帖文表';
