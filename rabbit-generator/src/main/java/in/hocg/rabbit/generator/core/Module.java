package in.hocg.rabbit.generator.core;

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
    TPL(Lists.newArrayList("t_", "oms_", "mkt_"),
        "in.hocg.rabbit.tpl.biz",
        "rabbit-services/rabbit-service-tpl/rabbit-service-tpl-biz"),
    OWP(Lists.newArrayList("owp_"),
        "in.hocg.rabbit.owp.biz",
        "rabbit-services/rabbit-service-owp/rabbit-service-owp-biz"),
    WL(Lists.newArrayList("wl_"),
        "in.hocg.rabbit.wl.biz",
        "rabbit-services/rabbit-service-wl/rabbit-service-wl-biz"),
    MINA(Lists.newArrayList("mina_", "dl_", "ytb_"),
        "in.hocg.rabbit.mina.biz",
        "rabbit-services/rabbit-service-mina/rabbit-service-mina-biz"),
    COM(Lists.newArrayList("com_", "mms_", "boot_"),
        "in.hocg.rabbit.com.biz",
        "rabbit-services/rabbit-service-com/rabbit-service-com-biz"),
    UMS(Lists.newArrayList("ums_", "ams_"),
        "in.hocg.rabbit.ums.biz",
        "rabbit-services/rabbit-service-ums/rabbit-service-ums-biz"),
    BMW(Lists.newArrayList("bmw_"),
        "in.hocg.rabbit.pay.biz",
        "rabbit-services/rabbit-service-bmw/rabbit-service-bmw-biz"),
    RCM(Lists.newArrayList("rcm_"),
        "in.hocg.rabbit.rcm.biz",
        "rabbit-services/rabbit-service-rcm/rabbit-service-rcm-biz"),
    MALL(Lists.newArrayList("mkt_", "oms_", "pms_"),
        "in.hocg.rabbit.mall.biz",
        "rabbit-services/rabbit-service-mall/rabbit-service-mall-biz"),
    ;

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
