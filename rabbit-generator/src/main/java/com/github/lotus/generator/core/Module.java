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
    TPL(Lists.newArrayList("T_"),
        "com.github.lotus.tpl.biz",
        "rabbit-services/rabbit-service-tpl/rabbit-service-tpl-biz"),
    WL(Lists.newArrayList("wl_"),
        "com.github.lotus.wl.biz",
        "rabbit-services/rabbit-service-wl/rabbit-service-wl-biz"),

    Chaos_UMS(Lists.newArrayList("ums_"),
        "com.github.lotus.chaos.biz.module.ums",
        "rabbit-services/rabbit-service-chaos/rabbit-service-chaos-biz"),
    Chaos_COM(Lists.newArrayList("com_"),
        "com.github.lotus.chaos.biz.module.com",
        "rabbit-services/rabbit-service-chaos/rabbit-service-chaos-biz");

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
