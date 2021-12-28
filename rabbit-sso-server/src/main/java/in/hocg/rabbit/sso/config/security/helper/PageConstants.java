package in.hocg.rabbit.sso.config.security.helper;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class PageConstants {
    public static final String LOGIN_PAGE = "/login";
    public static final String SIGN_UP_PAGE = "/login?actionType=register";
    public static final String INDEX_PAGE = "/index";
    public static final String MOBILE_TOKEN_URL = "/mobile/token/*";
}
