DROP TABLE IF EXISTS pms_product;
CREATE TABLE pms_product
(
    id                  BIGINT AUTO_INCREMENT,
    product_category_id BIGINT       NOT NULL
        COMMENT '商品品类(t_product_classify)',
    --
    title               VARCHAR(255) NOT NULL
        COMMENT '产品名',
    attrs               JSON         NOT NULL
        COMMENT '产品属性: {}',
    published_flag      TINYINT      NOT NULL DEFAULT 1
        COMMENT '上架状态',
    delete_flag         BIGINT       NOT NULL DEFAULT 0
        COMMENT '删除状态: != 0',
    video_url           VARCHAR(255)
        COMMENT 'video url',
    procurement         VARCHAR(255)
        COMMENT '采购地(中国,福建)',
    unit                VARCHAR(16)           DEFAULT '件'
        COMMENT '单位',
    weight              DECIMAL(10, 2)        DEFAULT NULL
        COMMENT '商品重量(克)',
    --
    creator             BIGINT,
    created_at          DATETIME(6)  NOT NULL,
    last_updater        BIGINT       NULL,
    last_updated_at     DATETIME(6)  NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[商品模块] 商品表';
--
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE pms_product_category
(
    id              bigint AUTO_INCREMENT,
    --
    title           varchar(255)        NOT NULL
        COMMENT '商品品类名称',
    remark          varchar(1024)
        COMMENT '商品品类描述',
    image_url       varchar(255)
        COMMENT '商品品类图片',
    priority        INT(10)             NOT NULL DEFAULT 1000
        COMMENT '排序, 从大到小降序',
    --
    `parent_id`     BIGINT
        COMMENT '父级ID, 顶级为 NULL',
    `tree_path`     VARCHAR(255)        NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    `enabled`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    creator         BIGINT,
    created_at      DATETIME(6)         NOT NULL,
    last_updater    BIGINT              NULL,
    last_updated_at DATETIME(6)         NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[商品模块] 商品品类表';
--
DROP TABLE IF EXISTS `pms_sku`;
CREATE TABLE `pms_sku`
(
    id         BIGINT AUTO_INCREMENT,
    product_id BIGINT,
    --
    encoding   VARCHAR(128)   NOT NULL
        COMMENT 'SKU 编码',
    unit_price DECIMAL(10, 2) NOT NULL
        COMMENT '单价(如: 12.00)',
    stock      INT            NOT NULL DEFAULT 0
        COMMENT '库存, 默认为0',
    sale       INT            NOT NULL DEFAULT 0
        COMMENT '销量, 默认为0',
    spec_data  JSON           NOT NULL
        COMMENT '规格属性(JSONArray, 如: [{"key":"颜色","value":"银色"}])',
    image_url  VARCHAR(255)
        COMMENT '特色商品图片',
    --
    UNIQUE KEY (encoding),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[商品模块] 商品SKU表';

