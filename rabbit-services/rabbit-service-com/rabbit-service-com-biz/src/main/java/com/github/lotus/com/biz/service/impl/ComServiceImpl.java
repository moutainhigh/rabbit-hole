package com.github.lotus.com.biz.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.github.lotus.com.biz.service.ComService;
import com.github.lotus.com.biz.utils.Avatars;
import com.github.lotus.com.biz.utils.FaviconUtils;
import com.github.lotus.common.utils.CommonUtils;
import com.talanlabs.avatargenerator.utils.AvatarUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by hocgin on 2021/10/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ComServiceImpl implements ComService {

    @Override
    public InputStream getFavicon(String url, String defUrl) {
        String faviconUrl = ((ComService) AopContext.currentProxy()).getFaviconUrl(url, defUrl);
        if (StrUtil.isNotBlank(faviconUrl)) {
            return URLUtil.getStream(URLUtil.url(faviconUrl));
        }
        return Avatars.getAvatarAsStream(System.currentTimeMillis());
    }

    @Override
    public String getFaviconUrl(String url, String defUrl) {
        return FaviconUtils.getFaviconUrl(url).orElse(defUrl);
    }
}
