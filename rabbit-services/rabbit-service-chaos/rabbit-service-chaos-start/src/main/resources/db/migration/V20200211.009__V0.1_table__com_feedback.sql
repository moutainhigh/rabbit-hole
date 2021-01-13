DROP TABLE IF EXISTS `com_project`;
create table com_project
(
    id                BIGINT auto_increment,
    encoding          VARCHAR(32) UNIQUE NOT NULL
        COMMENT '编码',
    `logo_url`        VARCHAR(64)
        COMMENT '图章地址',
    `title`           VARCHAR(64)        NOT NULL
        COMMENT '标题',
    `remark`          VARCHAR(255)
        COMMENT '备注',
--
    created_at        TIMESTAMP(6)       NOT NULL
        COMMENT '创建时间',
    creator           BIGINT             NOT NULL
        COMMENT '创建人',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    primary key (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 项目表';

DROP TABLE IF EXISTS `com_feedback`;
create table com_feedback
(
    id           BIGINT auto_increment,
    project_id   BIGINT       not null
        comment '项目',
    type         varchar(32)  not null
        comment '类型: issues=>问题; propose=>建议',
    subject      varchar(64)  not null
        comment '标题',
    content      varchar(255) not null
        comment '内容',
    page         varchar(125)
        comment '页面',
    contact_user varchar(32)
        comment '联系人',
    contact_info varchar(32)
        comment '联系方式',
    version      varchar(32)  not null default 'none'
        comment '版本号',
    expand       text
        comment '扩展信息',
--
    created_at   TIMESTAMP(6) NOT NULL
        COMMENT '创建时间',
    created_ip   varchar(64)
        COMMENT '创建者IP',
    creator      BIGINT
        COMMENT '创建人',
    primary key (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[通用模块] 用户反馈表';
