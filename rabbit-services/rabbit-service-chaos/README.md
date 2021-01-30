## 名词

- 支付网关, 即本项目
- 接入平台/第三方支付平台(access_platform)，如: 支付宝、微信支付、银联支付
- 支付渠道(payment_channel): 如: 支付宝、微信、
- 支付方式(payment_mode)，如: 支付宝h5支付、支付宝app支付
- 接入应用, 接入该支付网关的应用
- 接入平台, 接入应用接入多个第三方支付平台
- 交易账单(payment_trade), 


## 表

### bmw_payment_app

> 接入应用, 记录允许进行访问的应用

### bmw_payment_platform

> 第三方支付平台, 主要是为了读取配置
> 变更字段: platform_appid -> appid
> 变更字段: platform_type -> type

| 字段 | 描述 |
|---|---|
|platform_appid|-|
|platform_type|alipay/wxpay|

### bmw_request_platform_log

> 请求日志, 请求`第三方支付平台`的日志
> - 变更表名: bmw_request_platform_log -> bmw_platform_request_log
> - 变更字段: app_id -> payment_app_id

| 字段 | 描述 |
|---|---|
|app_id|接入应用|
|payment_platform_id|第三方支付平台|

### bmw_payment_trade

> 交易账单, 发起支付的账单(记录金额、通知地址等)
> - 变更字段: app_id -> payment_app_id
> - 变更字段: payment_way

| 字段 | 描述 |
|---|---|
|app_id|接入应用|
|trade_status|init/wait/success/closed/fail|

### bmw_payment_record

> 账单支付请求记录, 一个账单可以对应多个支付请求
> - 变更字段: trade_id -> payment_trade_id

### bmw_refund_record

> 账单退款请求记录, 一个账单可以对应多个退款请求

### bmw_notify_app

> 待通知事件列表, 接收到`第三方支付平台`的反馈并等待通知`接入应用`

### bmw_payment_way_rule

>

### bmw_payment_way_rule_item

> 
