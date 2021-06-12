DROP TABLE IF EXISTS `mina_game_room`;
create table `mina_game_room`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `encoding`        VARCHAR(32) NOT NULL
        COMMENT '房间号',
    `title`           VARCHAR(32) NOT NULL
        COMMENT '房间名',
    `type`            VARCHAR(32) NOT NULL
        COMMENT '房间类型',
    `password`        VARCHAR(64)
        COMMENT '房间密码',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    UNIQUE KEY (`encoding`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[小程序模块] 游戏房间表';

DROP TABLE IF EXISTS `mina_game_room_user`;
create table `mina_game_room_user`
(
    `id`          BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `room_id`     BIGINT      NOT NULL
        COMMENT '房间',
    `user_id`     BIGINT      NOT NULL
        COMMENT '玩家',
    `user_flag`   VARCHAR(32) NOT NULL
        COMMENT '玩家标记(P1,P2)',
    `signal_data` TEXT        NOT NULL
        COMMENT '信号标记',
    --
    `created_at`  DATETIME(6) NOT NULL
        COMMENT '创建时间',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[小程序模块] 游戏房间用户表';
