## 名词

- 支付网关, 即本项目
- 第三方支付平台，如: 支付宝、微信支付
- 支付方式，如: 支付宝h5支付、支付宝app支付
- 接入应用, 接入该支付网关的应用

## 表

### bmw_payment_app

> 接入应用, 记录允许进行访问的应用

### bmw_payment_platform

> 第三方支付平台, 主要是为了读取配置

### bmw_request_platform_log

> 请求日志, 请求`第三方支付平台`的日志

### bmw_payment_trade

> 交易账单, 发起支付的账单(记录金额、通知地址等)

### bmw_payment_record

> 账单支付请求记录, 一个账单可以对应多个支付请求

### bmw_refund_record

> 账单退款请求记录, 一个账单可以对应多个退款请求

### bmw_notify_app

> 待通知事件列表, 接收到`第三方支付平台`的反馈并等待通知`接入应用`

### bmw_payment_way_rule

>

### bmw_payment_way_rule_item

> 
