DROP TABLE IF EXISTS `com_user_integral`;
CREATE TABLE `com_user_integral`
(
    id                BIGINT AUTO_INCREMENT,
    --
    user_id           BIGINT         NOT NULL
        COMMENT '用户',
    avl_integral      decimal(20, 2) NOT NULL DEFAULT 0
        COMMENT '可用积分',
    used_integral     decimal(20, 2) NOT NULL DEFAULT 0
        COMMENT '已用积分',
    --
    `created_at`      DATETIME(6)    NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (user_id),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '[通用] 用户积分表';

DROP TABLE IF EXISTS `com_user_integral_flow`;
CREATE TABLE `com_user_integral_flow`
(
    id                BIGINT AUTO_INCREMENT,
    --
    user_id           BIGINT         NOT NULL
        COMMENT '用户',
    event_type        VARCHAR(32)    NOT NULL DEFAULT 'unknown'
        COMMENT '触发事件',
    change_type       VARCHAR(16)    NOT NULL
        COMMENT '变更类型',
    change_value      decimal(20, 2) NOT NULL DEFAULT 0
        COMMENT '变更值',
    remark            VARCHAR(255)   NOT NULL
        COMMENT '变更备注',
    expire_at         DATETIME(6)    NOT NULL
        COMMENT '过期时间',
    --
    `created_at`      DATETIME(6)    NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (user_id),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '[通用] 用户积分流水表';
