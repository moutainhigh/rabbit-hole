## 表

- 开发者用户表(用户中心)
- 开发者应用表(一个开发者对应多个应用)
- 权限x接口(1:n)
- 权限x应用(n:n)
- 开放接口表
    - 接口编号
    - 接口名称
    - 接口文档地址
    - 映射请求地址
    - 映射请求方式

## 接口例子

> POST https://openapi.hocgin.top/gateway/接口编号

### 请求体

```json
{
    "method": "接口编号",
    "appid": "appid",
    "signType": "md5",
    "sign": "signstr",
    "timestamp": "2014-07-24 03:07:50",
    "bizContent": {}
}
```

### 响应体

```json
{
    "接口编号": {
        "code": 200,
        "success": true,
        "message": "ok",
        "data": {}
    },
    "sign": "signstr"
}
```
