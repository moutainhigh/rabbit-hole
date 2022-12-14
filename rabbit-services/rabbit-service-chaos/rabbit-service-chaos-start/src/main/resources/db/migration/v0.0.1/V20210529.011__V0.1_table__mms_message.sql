DROP TABLE IF EXISTS `mms_user_subscriber_config`;
CREATE TABLE `mms_user_subscriber_config`
(
    id                BIGINT AUTO_INCREMENT,
    event_type        VARCHAR(32) NOT NULL
        COMMENT '事件类型',
    ref_type          VARCHAR(32) NOT NULL
        comment '订阅对象类型',
    ref_id            BIGINT      NOT NULL
        comment '订阅对象',
    subscriber_user   BIGINT      NOT NULL
        comment '订阅人',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`event_type`, `ref_type`, `ref_id`, `subscriber_user`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[消息模块] 用户订阅配置表';

DROP TABLE IF EXISTS `mms_message_user_ref`;
CREATE TABLE `mms_message_user_ref`
(
    id                BIGINT AUTO_INCREMENT,
    receiver_user     BIGINT      not null
        comment '接收人',
    message_type      VARCHAR(32) NOT NULL
        comment '消息类型',
    message_id        BIGINT      NOT NULL
        comment '消息对象',
    read_at           DATETIME(6)
        comment '读取时间',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`receiver_user`, `message_type`, `message_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[消息模块] 用户接收的消息表';

DROP TABLE IF EXISTS `mms_system_message`;
CREATE TABLE `mms_system_message`
(
    id                BIGINT AUTO_INCREMENT,
    content           TEXT        NOT NULL
        comment '内容',
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
    COMMENT '[消息模块] 系统消息表';

DROP TABLE IF EXISTS `mms_personal_message`;
CREATE TABLE `mms_personal_message`
(
    id                BIGINT AUTO_INCREMENT,
    content           TEXT        NOT NULL
        comment '内容',
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
    COMMENT '[消息模块] 私信消息表';

DROP TABLE IF EXISTS `mms_notice_message`;
CREATE TABLE `mms_notice_message`
(
    id                BIGINT AUTO_INCREMENT,
    event_type        VARCHAR(32) NOT NULL
        COMMENT '事件类型',
    ref_type          VARCHAR(32) NOT NULL
        comment '触发对象类型',
    ref_id            BIGINT      NOT NULL
        comment '触发对象',
    content           TEXT        NOT NULL
        comment '内容',
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
    COMMENT '[消息模块] 通知消息表';

# 通知
# 私信
# 系统消息
