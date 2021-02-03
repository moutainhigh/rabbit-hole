DROP TABLE IF EXISTS `com_comment_target`;
CREATE TABLE com_comment_target
(
    id       BIGINT AUTO_INCREMENT,
    ref_id   BIGINT      NOT NULL
        COMMENT '评论对象ID',
    ref_type VARCHAR(32) NOT NULL
        COMMENT '评论对象类型',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    UNIQUE KEY (ref_id, ref_type),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 评论对象表';

DROP TABLE IF EXISTS `com_comment`;
CREATE TABLE com_comment
(
    id              BIGINT AUTO_INCREMENT,
    parent_id       BIGINT,
    tree_path       VARCHAR(255) NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    target_id       BIGINT       NOT NULL
        COMMENT '评论对象ID',
    --
    content         VARCHAR(255) NOT NULL
        COMMENT '评论内容',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    likes_count     BIGINT       NOT NULL DEFAULT 0
        comment '点赞数量',
    --
    creator         BIGINT       not null
        comment '评论人',
    created_at      DATETIME(6)  not null,
    last_updater    BIGINT       null,
    last_updated_at DATETIME(6)  null,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 评论表';


