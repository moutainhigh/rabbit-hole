DROP TABLE IF EXISTS `com_doc_content`;
CREATE TABLE `com_doc_content`
(
    id              BIGINT AUTO_INCREMENT,
    --
    ref_type        VARCHAR(32) NOT NULL
        comment '对象类型',
    ref_id          BIGINT      NOT NULL
        comment '对象',
    doctype         VARCHAR(32) NOT NULL
        comment '文本类型: text=>普通文本;rich=>富文本;markdown=>.md',
    body            TEXT
        COMMENT '富文本',
    --
    creator         BIGINT,
    created_at      DATETIME(6) NOT NULL,
    last_updater    BIGINT,
    last_updated_at DATETIME(6),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[内容模块] 富文本内容表';
# <body doctype="rich"></body>
