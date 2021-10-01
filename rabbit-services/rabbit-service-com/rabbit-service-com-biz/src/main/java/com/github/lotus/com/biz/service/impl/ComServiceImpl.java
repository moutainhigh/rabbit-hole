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

    @SneakyThrows
    @Override
    public InputStream getFavicon(String url, String defUrl) {
        Optional<String> faviconUrl = FaviconUtils.getFaviconUrl(url);
        if (faviconUrl.isPresent()) {
            return URLUtil.getStream(URLUtil.url(faviconUrl.get()));
        }
        if (StrUtil.isNotBlank(defUrl)) {
            return URLUtil.getStream(URLUtil.url(defUrl));
        }
        return Avatars.getAvatarAsStream(System.currentTimeMillis());
    }
}
