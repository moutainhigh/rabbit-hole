DROP TABLE IF EXISTS `com_comment_user_action`;
CREATE TABLE `com_comment_user_action`
(
    `id`            BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `comment_id`    BIGINT      NOT NULL
        COMMENT '评论',
    `user_id`       BIGINT      NOT NULL
        COMMENT '用户',
    `action`        VARCHAR(8)  NOT NULL DEFAULT 'none'
        COMMENT '行为',
    --
    creator         BIGINT,
    created_at      DATETIME(6) NOT NULL,
    last_updater    BIGINT      NULL,
    last_updated_at DATETIME(6) NULL,
    UNIQUE KEY (`comment_id`, `user_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 用户的评论行为表';

alter table com_comment
    add dislikes_count bigint default 0 not null comment '倒赞';

