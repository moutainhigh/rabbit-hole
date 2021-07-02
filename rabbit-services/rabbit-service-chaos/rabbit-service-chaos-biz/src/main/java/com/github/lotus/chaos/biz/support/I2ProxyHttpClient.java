package com.github.lotus.chaos.biz.support;

import com.github.lotus.chaos.biz.manager.LangManager;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpHost;

import java.util.function.Function;

/**
 * Created by hocgin on 2021/7/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class I2ProxyHttpClient {

    public static <T> T proxy(Function<HttpHost, T> function) {
        HttpHost httpHost = SpringContext.getBean(LangManager.class).getProxyIp().orElseThrow(UnsupportedOperationException::new);
        return function.apply(httpHost);
    }

}
