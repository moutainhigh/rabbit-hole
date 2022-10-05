# http://www.ccb.com/cn/OtherResource/bankroll/html/code_help.html
DROP TABLE IF EXISTS `com_bank_info`;
CREATE TABLE `com_bank_info`
(
    `id`                 BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    --
    `code`               VARCHAR(16)  NOT NULL
        COMMENT '联行号/超级网银号',
    `type`               VARCHAR(32)  NOT NULL
        COMMENT '类型: super_pay_bank_code => 超级网银号; bank_code => 联行号',
    `full_name`          VARCHAR(256) NOT NULL
        COMMENT '全称',
    `simple_name`        VARCHAR(256)
        COMMENT '简称',
    `city_code`          VARCHAR(16)
        COMMENT '城市代码',
    `bank_status`        VARCHAR(8)   NOT NULL
        COMMENT '行号状态',
    `bank_code`          VARCHAR(32)
        COMMENT '行别代码',
    `clearing_bank_code` VARCHAR(32)
        COMMENT '清算行号',
    --
    `created_at`         DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`            BIGINT
        COMMENT '创建者',
    `last_updated_at`    DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`       BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[通用模块] 银行信息表';
