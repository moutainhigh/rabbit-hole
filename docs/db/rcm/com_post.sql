DROP TABLE IF EXISTS `com_post_topic`;
CREATE TABLE `com_post_topic`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `title`           VARCHAR(256)        NOT NULL
        COMMENT '标题',
    --
    `parent_id`       BIGINT
        COMMENT '父级ID, 顶级为 NULL',
    `tree_path`       VARCHAR(255)        NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `deleted_at`      DATETIME(6)                  DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                       DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)         NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)         NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 帖文的主题表';

DROP TABLE IF EXISTS `com_post`;
CREATE TABLE `com_post`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(256)        NOT NULL
        COMMENT '标题',
    `summary`         VARCHAR(512)
        COMMENT '简介',
    `doc_text_id`     BIGINT
        COMMENT '文章内容',
    `type`            VARCHAR(64)         NOT NULL
        COMMENT '帖文类型',
    `topic`           BIGINT              NOT NULL
        COMMENT '主题',
    `image_url`       VARCHAR(256)
        COMMENT '展览图',
    `view_count`      INT(11)             NOT NULL DEFAULT 0
        COMMENT '观看次数',
    `like_count`      INT(11)             NOT NULL DEFAULT 0
        COMMENT '喜欢数量',
    `heat_idx`        INT(11)             NOT NULL DEFAULT 0
        COMMENT '热度指数',
    `original_link`   VARCHAR(256)
        COMMENT '原文链接',
    `drafted`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '草稿状态',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `deleted_at`      DATETIME(6)                  DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                       DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)         NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)         NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 帖文表';

DROP TABLE IF EXISTS `com_post_product`;
CREATE TABLE `com_post_product`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
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

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 帖文(产品)表';

DROP TABLE IF EXISTS `com_post_discussion`;
CREATE TABLE `com_post_discussion`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
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

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 帖文(讨论)表';

DROP TABLE IF EXISTS `com_post_link`;
CREATE TABLE `com_post_link`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `article_id`      BIGINT       NOT NULL
        COMMENT '文章',
    `title`           VARCHAR(256) NOT NULL
        COMMENT '标题',
    `image_url`       VARCHAR(256)
        COMMENT '展览图',
    `link_url`        VARCHAR(256)
        COMMENT '链接',
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
    COMMENT = '[通用模块] 帖文关联的链接表';

DROP TABLE IF EXISTS `com_post_like`;
CREATE TABLE `com_post_like`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `article_id`      BIGINT      NOT NULL
        COMMENT '文章',
    --
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

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 帖文喜欢清单表';
