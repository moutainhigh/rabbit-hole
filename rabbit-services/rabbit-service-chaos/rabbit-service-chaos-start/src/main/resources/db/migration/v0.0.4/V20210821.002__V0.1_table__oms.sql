DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`
(
    id                BIGINT AUTO_INCREMENT,
    --
    encoding          VARCHAR(64)    NOT NULL
        COMMENT '订单编号',
    owner_user_id     BIGINT         NOT NULL
        COMMENT '所属用户',
    trade_no          VARCHAR(64)
        COMMENT '交易号(最终)',
    pay_way           VARCHAR(32)
        COMMENT '支付方式(最终)',
    remark            VARCHAR(500)   NULL
        COMMENT '订单备注',
    -- 金额
    express_amt       DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '运费金额',
    total_sale_amt    DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '订单销售总额',
    discount_amt      DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '优惠金额',
    adjustment_amt    DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '调价优惠金额',
    total_real_amt    DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '实际订单总额 = 销售总额 - 优惠金额 - 调价优惠金额',
    total_pay_amt     DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '实际付款总额 = 订单总额 + 运费金额',
    -- 状态
    trade_status      VARCHAR(32)    NOT NULL DEFAULT 'wait_pay' COMMENT '交易状态',
    order_status      VARCHAR(32)    NOT NULL DEFAULT 'create' COMMENT '订单状态:create=>已创建;success=>交易成功;close=>交易关闭',
    pay_status        VARCHAR(32)    NOT NULL DEFAULT 'not_payed' COMMENT '支付状态:not_payed=>未支付; payed=>已支付; timeout=>支付超时; fail=>支付失败',
    delivery_status   VARCHAR(32)    NOT NULL DEFAULT 'not_shipped' COMMENT '发货状态:not_shipped=>未发货;shipped=>已发货',
    receive_status    VARCHAR(32)    NOT NULL DEFAULT 'not_received' COMMENT '收货状态:not_received=>未收货;received=>已收货',
    refund_status     VARCHAR(32)    NOT NULL DEFAULT 'not_returned' COMMENT '退款状态:not_returned=>未退款;refunding=>退款中;returned=>退款',
    -- 时间
    plan_close_at     DATETIME(6)    NULL
        COMMENT '计划关单时间',
    plan_receive_at   DATETIME(6)    NULL
        COMMENT '计划确认收货时间',
    pay_at            DATETIME(6)    NULL
        COMMENT '支付时间',
    delivery_at       DATETIME(6)    NULL
        COMMENT '发货时间',
    receive_at        DATETIME(6)    NULL
        COMMENT '收货时间',
    finish_at         DATETIME(6)    NULL
        COMMENT '交易完成时间',
    -- 收货信息
    receiver_name     VARCHAR(100)   NULL
        COMMENT '收货人姓名',
    receiver_tel      VARCHAR(32)    NULL
        COMMENT '收货人电话',
    receiver_postcode VARCHAR(32)    NULL
        COMMENT '收货人邮编',
    receiver_adcode   VARCHAR(32)    NULL
        COMMENT '收货人区域编码',
    receiver_province VARCHAR(32)    NULL
        COMMENT '收货人省份/直辖市',
    receiver_city     VARCHAR(32)    NULL
        COMMENT '收货人城市',
    receiver_region   VARCHAR(32)    NULL
        COMMENT '收货人区',
    receiver_address  VARCHAR(200)   NULL
        COMMENT '收货人详细地址',
    --
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`deleted_at`, `encoding`),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单主表';
--
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`
(
    id                BIGINT AUTO_INCREMENT,
    --
    order_id          BIGINT         NOT NULL
        COMMENT '订单ID',
    product_type      VARCHAR(32)    NOT NULL
        COMMENT '商品类型: sku=>商品',
    product_id        BIGINT         NOT NULL
        COMMENT '商品ID',
    title             VARCHAR(200)   NOT NULL
        COMMENT '商品名称',
    quantity          INT            NOT NULL DEFAULT 1
        COMMENT '购买数量',
    sale_price        DECIMAL(10, 2) NOT NULL
        COMMENT '销售价',
    total_amt         DECIMAL(10, 2) NOT NULL
        COMMENT '[计算型]原总价=销售价格x购买数量',
    discount_amt      DECIMAL(10, 2) NOT NULL DEFAULT 0
        COMMENT '优惠金额',
    real_amt          DECIMAL(10, 2) NOT NULL
        COMMENT '[计算型]优惠后金额 = 原总价 - 优惠金额',
    maintain_status   VARCHAR(32)    NOT NULL DEFAULT 'wait_pay'
        COMMENT '售后状态: not_started=>未开始;starting=>允许售后;processing=>处理中;expired=>已到期;maintained=>已售后',
    comment_status    VARCHAR(32)    NOT NULL DEFAULT 'not_commented'
        COMMENT '评价状态:not_commented=>未评价;commented=>已评价',
    plan_maintain_at  DATETIME(6)    NOT NULL
        COMMENT '售后截止时间',
    commented_at      DATETIME(6)    NULL
        COMMENT '评价时间',
    --
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单详情表';
--
DROP TABLE IF EXISTS `oms_order_item_sku`;
CREATE TABLE `oms_order_item_sku`
(
    id                BIGINT       NOT NULL
        COMMENT 'ID 同oms_order_item',
    order_item_id     BIGINT       NOT NULL
        COMMENT '订单详情ID',
    image_url         VARCHAR(500) NULL
        COMMENT '商品主图',
    sku_id            BIGINT       NULL
        COMMENT '商品SKU ID',
    sku_code          VARCHAR(50)  NULL
        COMMENT '商品SKU条码',
    sku_spec_data     JSON         NULL
        COMMENT '商品规格:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    --
    `deleted_at`      DATETIME(6)           DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单详情(商品)表';
--
DROP TABLE IF EXISTS `oms_order_discount`;
CREATE TABLE `oms_order_discount`
(
    id                BIGINT AUTO_INCREMENT,
    --
    order_id          BIGINT         NOT NULL
        COMMENT '订单',
    type              VARCHAR(32)    NOT NULL
        COMMENT '类型: coupon_discount=>优惠券优惠; adjustment=>后台调价;',
    title             VARCHAR(64)    NOT NULL DEFAULT 'unknown'
        COMMENT '优惠标题',
    discount_amt      decimal(10, 2) NOT NULL DEFAULT 0
        COMMENT '优惠金额',
    ref_id            BIGINT         NOT NULL
        COMMENT '优惠对象',
    ref_type          VARCHAR(32)    NOT NULL
        COMMENT '对象类型',
    --
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单优惠详项表';
--
DROP TABLE IF EXISTS `oms_order_pay_record`;
CREATE TABLE `oms_order_pay_record`
(
    id                BIGINT AUTO_INCREMENT,
    --
    order_id          BIGINT      NOT NULL
        COMMENT '订单',
    --
    `deleted_at`      DATETIME(6)          DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT               DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6) NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 订单支付记录表';
--
DROP TABLE IF EXISTS `oms_order_maintain`;
CREATE TABLE `oms_order_maintain`
(
    `id`              bigint(20)     NOT NULL AUTO_INCREMENT,
    `encoding`        VARCHAR(64)    NOT NULL
        COMMENT '退款申请编号',
    `type`            VARCHAR(32)    NOT NULL DEFAULT 'full_refund'
        COMMENT '售后类型：full_refund=>退货退款 ',
    `status`          VARCHAR(32)    NOT NULL DEFAULT 'processing'
        COMMENT '申请状态：[processing=>进行中; wait_comment=>待评价; success=>成功; fail=>已拒绝]',
    `comment_status`  VARCHAR(32)    NOT NULL DEFAULT 'not_commented'
        COMMENT '评价状态:not_commented=>未评价;commented=>已评价',
    `owner_user_id`   BIGINT         NOT NULL
        COMMENT '退款用户',
    -- #退货信息
    `order_item_id`   bigint(20)     NOT NULL
        COMMENT '订单详情',
    `refund_quantity` int(11)        NOT NULL
        COMMENT '退货数量',
    `refund_amt`      decimal(10, 2) NOT NULL
        COMMENT '退款金额',
    `refund_reason`   varchar(200)            DEFAULT NULL
        COMMENT '退货原因',
    `refund_remark`   varchar(200)            DEFAULT NULL
        COMMENT '退货备注',
    -- #处理信息
    handler_user_id   BIGINT         NULL
        COMMENT '处理人',
    handle_at         DATETIME(6)    NULL
        COMMENT '处理时间',
    handle_remark     VARCHAR(512)   NULL
        COMMENT '处理备注',
    --
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (`deleted_at`, `encoding`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[订单模块] 退货申请';
--
DROP TABLE IF EXISTS `oms_order_delivery`;
CREATE TABLE `oms_order_delivery`
(
    `id`              BIGINT(20)   NOT NULL AUTO_INCREMENT,
    order_id          BIGINT(20)   NOT NULL
        COMMENT '订单',
    status            VARCHAR(32)  NOT NULL DEFAULT 'create'
        COMMENT '物流状态:create=>已下单; =>仓库处理中;=>已揽收; =>运输中; =>派送中; =>完成配送',
    -- 发货人信息
    delivery_name     VARCHAR(100) NULL
        COMMENT '发货人姓名',
    delivery_tel      VARCHAR(32)  NULL
        COMMENT '发货人电话',
    delivery_postcode VARCHAR(32)  NULL
        COMMENT '发货人邮编',
    delivery_adcode   VARCHAR(32)  NULL
        COMMENT '发货人区域编码',
    delivery_province VARCHAR(32)  NULL
        COMMENT '发货人省份/直辖市',
    delivery_city     VARCHAR(32)  NULL
        COMMENT '发货人城市',
    delivery_region   VARCHAR(32)  NULL
        COMMENT '发货人区',
    delivery_address  VARCHAR(200) NULL
        COMMENT '发货人详细地址',
    --
    `deleted_at`      DATETIME(6)           DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)  NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='[订单模块] 配送单';
--
DROP TABLE IF EXISTS `oms_shopcart`;
CREATE TABLE `oms_shopcart`
(
    id                BIGINT AUTO_INCREMENT,
    --
    owner_user_id     BIGINT         NOT NULL
        COMMENT '所属用户',
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
    `deleted_at`      DATETIME(6)             DEFAULT NULL
        COMMENT '删除时间',
    `deleter`         BIGINT                  DEFAULT NULL
        COMMENT '删除者',
    `created_at`      DATETIME(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (owner_user_id, add_sku_id),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[订单模块] 购物车表';


# 一个订单 -> 多个支付记录
# 一个订单 -> 一个发货记录
# 一个订单详情 -> 多个退款记录

