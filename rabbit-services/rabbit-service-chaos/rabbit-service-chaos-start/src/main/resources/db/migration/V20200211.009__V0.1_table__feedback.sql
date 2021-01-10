create table com_feedback
(
    id           BIGINT auto_increment,
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
    expand       text
        comment '扩展信息',

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
