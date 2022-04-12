DROP TABLE IF EXISTS `owp_developer`;
CREATE TABLE `owp_developer`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID(所属用户)',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
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
    COMMENT = '[开放平台] 开发者档案表';

DROP TABLE IF EXISTS `owp_developer_app`;
CREATE TABLE `owp_developer_app`
(
    `id`                BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`             VARCHAR(16)         NOT NULL
        COMMENT '应用名称',
    `encoding`          VARCHAR(64)         NOT NULL
        COMMENT '应用编号',
    `secret_key`        VARCHAR(64)         NOT NULL
        COMMENT '应用密钥',
    `developer_user_id` BIGINT              NOT NULL
        COMMENT '开发者',
    `enabled`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`        DATETIME(6)         NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`           BIGINT
        COMMENT '创建者',
    `last_updated_at`   DATETIME(6)         NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`      BIGINT
        COMMENT '更新者',

    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[开放平台] 开发者应用表';

DROP TABLE IF EXISTS `owp_api`;
CREATE TABLE `owp_api`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding`        VARCHAR(16)         NOT NULL
        COMMENT '接口编号',
    `title`           VARCHAR(16)         NOT NULL
        COMMENT '接口名称',
    `map_uri`         VARCHAR(32)         NOT NULL
        COMMENT '映射服务名/域名',
    `map_path`        VARCHAR(128)        NOT NULL
        COMMENT '映射接口地址',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)         NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)         NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[开放平台] 接口表';

DROP TABLE IF EXISTS `owp_authority`;
CREATE TABLE `owp_authority`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(16)         NOT NULL
        COMMENT '权限名称',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
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
    COMMENT = '[开放平台] 权限表';

DROP TABLE IF EXISTS `owp_authority_api_ref`;
CREATE TABLE `owp_authority_api_ref`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `authority_id`    BIGINT      NOT NULL
        COMMENT '权限',
    `api_id`          BIGINT      NOT NULL
        COMMENT '接口',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`authority_id`, `api_id`),

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[开放平台] 权限x接口表';

DROP TABLE IF EXISTS `owp_authority_app_ref`;
CREATE TABLE `owp_authority_app_ref`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `authority_id`    BIGINT      NOT NULL
        COMMENT '权限',
    `app_id`          BIGINT      NOT NULL
        COMMENT '应用',
    --
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`authority_id`, `app_id`),

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[开放平台] 权限x应用表';
