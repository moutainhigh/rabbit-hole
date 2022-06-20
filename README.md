# rabbit-hole

> 文档

## 如何使用

### 项目启动

1. 配置环境变量，参照`env.example`
2. 配置DNS映射，参照`host.example`
3. 启动对应服务

### 查看接口文档

API 接口文档使用的是`knife`可以按照`swagger`在服务群里面进行使用，文档的地址为`http://{网关地址:端口}/doc.html`

## 项目

### 项目基础结构

```shell script
.
├── README.md
├── docs # 相关文档
├── env.example # 环境变量案例
├── host.example # DNS映射案例
├── rabbit-gateway # 系统网关
├── rabbit-generator # 代码生成器
├── rabbit-parent # 依赖管理
├── rabbit-services # 服务群
│   └── rabbit-service-tpl # 单体服务案例
│       ├── rabbit-service-tpl-api # 单体服务内部接口案例
│       ├── rabbit-service-tpl-biz # 单体服务业务代码
│       │   ├── apiimpl    #
│       │   ├── basic      #
│       │   ├── cache      # 缓存
│       │   ├── constant   #
│       │   ├── controller #
│       │   ├── convert    # 业务关联(含有操作service)
│       │   ├── entity     #
│       │   ├── manager    # 无实体表的service
│       │   ├── mapper     #
│       │   ├── mapstruct  # 纯实体映射
│       │   ├── message    # 消费消息
│       │   ├── pojo       # ro、vo、dto
│       │   ├── schedule   # 定时任务
│       │   ├── service    #
│       │   ├── support    #
│       │   └── utils      # 
│       └── rabbit-service-tpl-start # 单体服务业务功能案例
└── pom.xml
```

### 服务群

|定位| 项目名                     | 服务名     | 备注              | 界定边界        | 实际边界 |
|---|-------------------------|---------|:----------------|-------------|-----|
|✅| rabbit-service-ums      | ums     | 用户中心            | 用户、权限       | -   |
|✅| rabbit-service-com      | com     | 通用支撑            | 短链接、短号池、单号池 | -   |
|✳️| rabbit-service-bmw      | bmw     | 支付中心            | 支付能力        | -   |
|✳️| rabbit-service-docking  | docking | 对外(微信公众号、微信小程序) | -           | -   |
|✳️| rabbit-service-mall     | mall    | 商城              | 商品、订单       | -   |
|✳️| rabbit-service-mina     | mina    | 小项目             | -           | -   |
|✳️| rabbit-service-rcm      | rcm     | 文本系统            | 富文本、评论、文章   | -   |
|✳️| rabbit-service-aom      | aom     | 活动运营系统          | 优惠券、积分、兑换码  | -   |
|✳️| rabbit-service-cv       | cv      | JavaCV          | 视频、音频处理     | -   |
|✳️| rabbit-service-webmagic | webmagic      | 爬虫              | 网络爬虫、数据抓取   | -   |
|⚠️| rabbit-service-chaos    | chaos   | 混合[闲置状态]        | -           | -   |
|⚠️| rabbit-service-wl       | wl      | 物流项目[暂停状态]      | -           | -   |

## 设计规范

- [基础设施保留端口](./docs/design/基础设施.md)
- [服务端口分配](./docs/design/服务设施.md)
- [规范设计](./docs/design/规范设计.md)
