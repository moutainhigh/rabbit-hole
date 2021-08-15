package com.github.lotus.generator.core;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * Created by hocgin on 2020/5/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum Module {
    TPL(Lists.newArrayList("t_"),
        "com.github.lotus.tpl.biz",
        "rabbit-services/rabbit-service-tpl/rabbit-service-tpl-biz"),
    WL(Lists.newArrayList("wl_"),
        "com.github.lotus.wl.biz",
        "rabbit-services/rabbit-service-wl/rabbit-service-wl-biz"),
    MINA(Lists.newArrayList("mina_"),
        "com.github.lotus.mina.biz",
        "rabbit-services/rabbit-service-mina/rabbit-service-mina-biz"),
    COM(Lists.newArrayList("com_", "mms_"),
        "com.github.lotus.com.biz",
        "rabbit-services/rabbit-service-com/rabbit-service-com-biz"),
    UMS(Lists.newArrayList("ums_", "ams_"),
        "com.github.lotus.ums.biz",
        "rabbit-services/rabbit-service-ums/rabbit-service-ums-biz"),
    PAY(Lists.newArrayList("bmw_"),
        "com.github.lotus.pay.biz",
        "rabbit-services/rabbit-service-pay/rabbit-service-pay-biz"),
    BMW(Lists.newArrayList("bmw_"),
        "com.github.lotus.bmw.biz",
        "rabbit-services/rabbit-service-pay/rabbit-service-pay-biz");

    /**
     * 生成的 Entity 名称会忽略前缀
     * - 优先匹配原则
     */
    private final List<String> ignoreTablePrefix;
    /**
     * 模块上的根包名
     */
    private final String packageName;
    /**
     * 模块相对于根项目的位置
     */
    private final String relativePath;
}
