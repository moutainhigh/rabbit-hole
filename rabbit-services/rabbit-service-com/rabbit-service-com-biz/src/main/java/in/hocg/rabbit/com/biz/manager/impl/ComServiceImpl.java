package in.hocg.rabbit.com.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import in.hocg.rabbit.com.biz.manager.ComService;
import in.hocg.rabbit.com.biz.utils.Avatars;
import in.hocg.rabbit.com.biz.utils.FaviconUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStream;

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
