DROP TABLE IF EXISTS `mina_post_node`;
CREATE TABLE `mina_post_node`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '节点编号',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '节点名称',
    `is_recommend`    TINYINT(1)  NOT NULL DEFAULT false
        COMMENT '是否推荐',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    UNIQUE KEY (`title`),
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[文章模块] 节点表';

DROP TABLE IF EXISTS `mina_post`;
CREATE TABLE `mina_post`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `post_node_id`    BIGINT      NOT NULL
        COMMENT '所属节点',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '文章编号',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '文章标题',
    `topic`           VARCHAR(128)
        COMMENT '标签',
    `original_url`    VARCHAR(256)
        COMMENT '原始链接',
    `cover_image`     VARCHAR(256)
        COMMENT '主图',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    UNIQUE KEY (`title`),
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[文章模块] 文章表';

