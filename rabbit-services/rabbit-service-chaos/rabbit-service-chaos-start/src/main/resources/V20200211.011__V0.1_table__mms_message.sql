-- # 订阅-通知设计
DROP TABLE IF EXISTS `mms_notification`;
CREATE TABLE mms_notification
(
    id              BIGINT AUTO_INCREMENT,
    notify_type     varchar(32) not null
        comment '通知类型: mention=>提及; like_article=>点赞文章; like_comment=>点赞评论',
    actor_type      varchar(32) not null
        comment '触发者类型: user=>用户;',
    actor_id        BIGINT      not null
        comment '触发者ID',
    subject_type    varchar(32) null
        comment '订阅对象类型: article=>文章; user=>用户',
    subject_id      BIGINT      null
        comment '订阅对象ID',
    --
    creator         BIGINT      not null,
    created_at      DATETIME(6) not null,
    last_updater    BIGINT      null,
    last_updated_at DATETIME(6) null,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 通知表';

DROP TABLE IF EXISTS `mms_notification_user`;
CREATE TABLE mms_notification_user
(
    notification_id bigint      not null comment '通知ID',
    receiver_id     bigint      not null comment '接收人ID',
    read_at         datetime(6) null comment '已读时间',
    PRIMARY KEY (notification_id, receiver_id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 通知-接收人表';

-- 谁订阅了哪篇文章的什么操作
DROP TABLE IF EXISTS `mms_subscription`;
CREATE TABLE mms_subscription
(
    id              bigint AUTO_INCREMENT,
    notify_type     varchar(32) not null
        comment '通知类型: mention=>提及; like_article=>点赞文章; like_comment=>点赞评论',
    subscriber_id   bigint      not null
        comment '订阅人ID',
    subject_type    varchar(32) not null
        comment '订阅对象类型: article=>文章; user=>用户',
    subject_id      bigint      not null
        comment '订阅对象ID',
    --
    creator         bigint      not null,
    created_at      datetime(6) not null,
    last_updated_at datetime(6) null,
    last_updater    bigint      null,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 订阅列表';

-- # 消息
DROP TABLE IF EXISTS `mms_message`;
CREATE TABLE mms_message
(
    id              BIGINT AUTO_INCREMENT,
    sender_id       bigint       not null
        comment '发送人ID',
    receiver_id     bigint       not null
        comment '接收人ID',
    content_type    varchar(32)  not null default 'text'
        comment '内容类型: text=>文本',
    content         varchar(255) not null
        comment '内容',
    --
    creator         BIGINT       not null,
    created_at      DATETIME(6)  not null,
    last_updater    BIGINT       null,
    last_updated_at DATETIME(6)  null,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 私信表';

-- # 公告
DROP TABLE IF EXISTS `mms_notice`;
CREATE TABLE mms_notice
(
    id              BIGINT AUTO_INCREMENT,
    project_id      bigint       not null
        comment '项目ID',
    title           varchar(255) not null
        comment '标题',
    content_type    varchar(32)  not null default 'text'
        comment '内容类型: text=>文本',
    content         varchar(255) not null
        comment '内容',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    creator         BIGINT       not null,
    created_at      DATETIME(6)  not null,
    last_updater    BIGINT       null,
    last_updated_at DATETIME(6)  null,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 系统通知表';
