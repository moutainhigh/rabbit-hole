package in.hocg.rabbit.sso.service;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SocialService {

    /**
     * 社交登录授权成功
     *
     * @param socialType 社交登录类型
     * @param socialId   社交ID
     * @param username   当前登录用户名
     */
    void onAuthenticationSuccess(String socialType, String socialId, String username);
}
