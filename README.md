# payment-projects
> 文档

## 如何启动
1. 配置环境变量，参照`env.example`
2. 配置DNS映射，参照`host.example`
3. 启动对应服务

## 项目基础结构
```shell script
.
├── README.md
├── docs # 相关文档
├── env.example # 环境变量案例
├── host.example # DNS映射案例
├── payment-gateway # 系统网关
├── payment-generator # 代码生成器
├── payment-parent # 依赖管理
├── payment-services # 服务群
│   └── payment-service-tpl # 单体服务案例
│       ├── payment-service-tpl-api # 单体服务内部接口案例
│       └── payment-service-tpl-start # 单体服务业务功能案例
└── pom.xml
```

## 相关内容
### API 接口文档系列
> API 接口文档使用的是`knife`可以按照`swagger`在服务群里面进行使用，文档的地址为`http://{网关地址:端口}/doc.html`
