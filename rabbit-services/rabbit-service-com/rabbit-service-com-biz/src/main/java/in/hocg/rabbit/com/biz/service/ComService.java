package in.hocg.rabbit.com.biz.service;

import java.io.InputStream;

/**
 * Created by hocgin on 2021/10/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ComService {

    InputStream getFavicon(String url, String defUrl);

    String getFaviconUrl(String url, String defUrl);
}
