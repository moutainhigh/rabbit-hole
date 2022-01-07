DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`
(
    id                      BIGINT AUTO_INCREMENT,
    --
    user_id                 BIGINT         NOT NULL
        COMMENT '账号ID',
    order_no                VARCHAR(64)    NOT NULL
        COMMENT '订单编号',
    trade_no                VARCHAR(64)
        COMMENT '交易流水号',

    activity_discount_amt   DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '活动抵扣金额',
    coupon_discount_amt     DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '优惠券抵扣金额',
    adjustment_discount_amt DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '管理员后台调整订单使用的折扣金额',
    freight_amt             DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '运费金额',
    total_amt               DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '[计算型]订单总金额',
    user_pay_amt            DECIMAL(10, 2) NOT NULL
        COMMENT '[计算型]应付金额（实际支付金额）',

    plan_close_at           DATETIME(6)    NULL
        COMMENT '计划关单时间',
    plan_confirm_at         DATETIME(6)    NULL
        COMMENT '计划确认收货时间',
    pay_type                VARCHAR(32)
        COMMENT '支付方式：[支付宝;微信]',
    order_status            VARCHAR(32)    NOT NULL DEFAULT 'wait_pay'
        COMMENT '订单状态：[wait_pay=>待付款；wait_ship=>待发货；wait_receipt=>待收货；received=>已收货；closed=>已关闭;]',
    confirm_flag            TINYINT(1)     NOT NULL DEFAULT 0
        COMMENT '是否确认收货',
    remark                  VARCHAR(500)   NULL
        COMMENT '订单备注',
    pay_at                  DATETIME(6)    NULL
        COMMENT '支付时间',
    delivery_at             DATETIME(6)    NULL
        COMMENT '发货时间',
    receive_at              DATETIME(6)    NULL
        COMMENT '确认收货时间',
    commented_at            DATETIME(6)    NULL
        COMMENT '评价时间',
    --
    receiver_name           VARCHAR(100)   NULL
        COMMENT '收货人姓名',
    receiver_tel            VARCHAR(32)    NULL
        COMMENT '收货人电话',
    receiver_postcode       VARCHAR(32)    NULL
        COMMENT '收货人邮编',
    receiver_adcode         VARCHAR(32)    NULL
        COMMENT '收货人区域编码',
    receiver_province       VARCHAR(32)    NULL
        COMMENT '收货人省份/直辖市',
    receiver_city           VARCHAR(32)    NULL
        COMMENT '收货人城市',
    receiver_region         VARCHAR(32)    NULL
        COMMENT '收货人区',
    receiver_address        VARCHAR(200)   NULL
        COMMENT '收货人详细地址',
    --
    `deleted_at`        DATETIME(6)           DEFAULT NULL
        COMMENT '删除时间',
    `deleter`           BIGINT                DEFAULT NULL
        COMMENT '删除者',
    `created_at`        DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`           BIGINT
        COMMENT '创建者',
    `last_updated_at`   DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`      BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单主表';

--
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`
(
    id                      BIGINT AUTO_INCREMENT,
    --
    order_id                BIGINT         NOT NULL
        COMMENT '订单ID',
    product_id              BIGINT         NOT NULL
        COMMENT '商品ID',
    title                   VARCHAR(200)   NOT NULL
        COMMENT '商品名称',
    unit_price              DECIMAL(10, 2) NOT NULL
        COMMENT '销售单价',
    quantity                INT            NOT NULL DEFAULT 1
        COMMENT '购买数量',
    image_url               VARCHAR(500)   NULL
        COMMENT '商品主图',
    sku_id                  BIGINT         NULL
        COMMENT '商品SKU ID',
    sku_code                VARCHAR(50)    NULL
        COMMENT '商品SKU条码',
    sku_spec_data           JSON           NULL
        COMMENT '商品规格:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    discount_amt            DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '优惠分解金额(不含后台调整)',
    adjustment_discount_amt DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '后台调整优惠',
    total_amt               DECIMAL(10, 2) NOT NULL
        COMMENT '[计算型]原总价=销售价格x购买数量',
    user_pay_amt            DECIMAL(10, 2) NOT NULL
        COMMENT '[计算型]该商品经过优惠后的分解金额(实际支付金额)=原总价-后台调整优惠-优惠分解金额',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单商品表';

DROP TABLE IF EXISTS `oms_order_discount`;
CREATE TABLE `oms_order_discount`
(
    id           BIGINT AUTO_INCREMENT,
    --
    order_id     BIGINT         NOT NULL
        COMMENT '订单',
    title        VARCHAR(64)    NOT NULL DEFAULT 'unknown'
        COMMENT '优惠标题',
    discount_amt decimal(10, 2) NOT NULL DEFAULT 0
        COMMENT '优惠金额',
    ref_id       BIGINT         NOT NULL
        COMMENT '对象(优惠)',
    ref_type     VARCHAR(32)    NOT NULL
        COMMENT '对象类型',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单优惠详项表';

--
DROP TABLE IF EXISTS `oms_order_refund_apply`;
CREATE TABLE `oms_order_refund_apply`
(
    `id`              bigint(20)     NOT NULL AUTO_INCREMENT,
    `refund_apply_no` VARCHAR(64)    NOT NULL
        COMMENT '退款申请编号',
    `apply_status`    VARCHAR(32)    NOT NULL DEFAULT '0'
        COMMENT '申请状态：[init=>初始化; processing=>进行中; success=>成功; fail=>已拒绝]',
    -- #退货信息
    `order_item_id`   bigint(20)     NOT NULL
        COMMENT '订单商品ID',
    `refund_quantity` int(11)        NOT NULL
        COMMENT '退货数量',
    `refund_amt`      decimal(10, 2) NOT NULL
        COMMENT '退款金额',
    `refund_reason`   varchar(200)            DEFAULT NULL
        COMMENT '退货原因',
    `refund_remark`   varchar(200)            DEFAULT NULL
        COMMENT '退货备注',
    -- #处理信息
    handler_id        BIGINT         NULL
        COMMENT '处理人',
    handle_at         DATETIME(6)    NULL
        COMMENT '处理时间',
    handle_remark     VARCHAR(512)   NULL
        COMMENT '处理备注',
    receiver_id       BIGINT         NULL
        COMMENT '收货人',
    receive_at        DATETIME(6)    NULL
        COMMENT '收货时间',
    receive_remark    varchar(512)   NULL
        COMMENT '收货备注',
    --
    creator           BIGINT,
    created_at        DATETIME(6)    NOT NULL,
    last_updater      BIGINT         NULL,
    last_updated_at   DATETIME(6)    NULL,
    UNIQUE KEY (`refund_apply_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[订单模块] 退货申请';
--
DROP TABLE IF EXISTS `oms_shopcart`;
CREATE TABLE `oms_shopcart`
(
    id                BIGINT AUTO_INCREMENT,
    --
    user_id           BIGINT         NOT NULL
        COMMENT '用户',
    product_id        BIGINT         NOT NULL
        COMMENT '商品',
    add_unit_price    DECIMAL(10, 2) NOT NULL
        COMMENT '加入时，商品单价',
    add_title         VARCHAR(128)   NOT NULL
        COMMENT '加入时，商品标题',
    add_image_url     VARCHAR(255)
        COMMENT '加入时，商品图片',
    add_sku_id        BIGINT         NOT NULL
        COMMENT 'SKU ID',
    add_sku_spec_data JSON           NOT NULL
        COMMENT '加入的规格属性',
    add_quantity      INT(8)         NOT NULL DEFAULT 1
        COMMENT '加入的数量',
    --
    creator           BIGINT,
    created_at        DATETIME(6)    NOT NULL,
    last_updater      BIGINT         NULL,
    last_updated_at   DATETIME(6)    NULL,
    UNIQUE KEY (user_id, add_sku_id),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 购物车表';
