DROP TABLE IF EXISTS `com_comment_report`;
CREATE TABLE `com_comment_report`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `comment_id`      BIGINT      NOT NULL
        COMMENT '评论',
    `user_id`         BIGINT      NOT NULL
        COMMENT '用户',
    `reason`          VARCHAR(255)
        COMMENT '举报原因',
    --
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
    COMMENT = '[通用模块] 评论举报表';
