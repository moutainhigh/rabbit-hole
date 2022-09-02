package in.hocg.rabbit.com.biz.utils;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.*;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import in.hocg.rabbit.com.biz.pojo.dto.LinkDetailDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocgin on 2021/10/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class FaviconUtils {
    private static int TIMEOUT = 1000;

    /**
     * 获取一个网站的 favicon
     *
     * @param originUrl
     * @return
     */
    public Optional<String> getFaviconUrl(String originUrl) {
        HttpRequest request = HttpUtil.createRequest(Method.GET, originUrl);
        request.timeout(TIMEOUT);
        HttpResponse response = request.execute();

        int status = response.getStatus();
        if (Lists.newArrayList(HttpStatus.HTTP_MOVED_TEMP, 308).contains(status)) {
            return FaviconUtils.getFaviconUrl(response.header(Header.LOCATION));
        }

        String body = response.body();
        if (HttpStatus.HTTP_OK != status && StrUtil.isBlank(body)) {
            return Optional.empty();
        }

        Optional<String> htmlFaviconUrlOpt = FaviconUtils.getHtmlFaviconUrl(body, originUrl);
        if (htmlFaviconUrlOpt.isPresent()) {
            return htmlFaviconUrlOpt;
        }

        // 默认的 icon
        String defFaviconUrl = UrlBuilder.create().setHost(URLUtil.url(originUrl).getHost()).addPath("/favicon.ico").build();
        if (HttpUtil.createGet(defFaviconUrl).timeout(TIMEOUT).execute().getStatus() == HttpStatus.HTTP_OK) {
            return Optional.of(defFaviconUrl);
        }

        return Optional.empty();
    }

    /**
     * 从 HTML 中提取 favicon
     *
     * @param html
     * @param baseUri
     * @return
     */
    public Optional<String> getHtmlFaviconUrl(String html, String baseUri) {
        if (Objects.isNull(html)) {
            return Optional.empty();
        }
        Document document = Jsoup.parse(html, baseUri);
        String linkHref = document.select("link[rel~=(icon|shortcut icon|alternate icon|apple-touch-icon)][href]").first()
            .attr("abs:href");

        if (HttpUtil.isHttp(linkHref) || HttpUtil.isHttps(linkHref)) {
            return Optional.of(linkHref);
        }

        return Optional.empty();
    }

    public LinkDetailDto getLinkDetail(String link) {
        String responseBody = HttpUtil.get(StrUtil.format("https://www.yuque.com/api/editor/link_detail?url={}&ctoken=IYPdHkTh_CkPdQHe_uOAacx4", link));
        String dataStr = JSONUtil.parseObj(responseBody).getStr("data");
        return JSONUtil.toBean(dataStr, LinkDetailDto.class);
    }

}
