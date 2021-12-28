package in.hocg.rabbit.sso.service.impl;

import in.hocg.rabbit.sso.config.security.helper.PageConstants;
import in.hocg.rabbit.sso.config.security.helper.SecurityContext;
import in.hocg.rabbit.sso.service.SocialService;
import in.hocg.rabbit.ums.api.SocialServiceApi;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SocialServiceImpl implements SocialService {
    private final SocialServiceApi socialServiceApi;
    private final UserServiceApi userServiceApi;

    @Override
    public void onAuthenticationSuccess(String socialType, String socialId, String username) {
        UserDetailVo userDetail = socialServiceApi.getAccountBySocialTypeAndSocialId(socialType, socialId);
        // 1. 该社交账户已经绑定，并且当前没有人登录 -> 登录
        // 2. 该社交账户已经绑定，并且当前登录人不是本人 -> 注销原来的登录，登录新的
        // 2. 该社交账户已经绑定，并且当前登录人是本人 -> 登录
        // 3. 该社交账户没有绑定，并且当前没有人登录 -> 跳到注册
        // 4. 该社交账户没有绑定，并且当前有登录人 -> 绑定


        // 社交账号未被绑定
        if (Objects.isNull(userDetail)) {
            // 已有用户登陆
            if (Objects.nonNull(username)) {
                // todo 这个用户未绑定该类型的社交账号，少了一个判断
                log.debug("==> 绑定社交账号 [{}] 到 [{}]", socialType + socialId, username);
                UserDetailVo user = userServiceApi.getUserByUsername(username);
                // 绑定用户账户
                socialServiceApi.insertOne(new InsertSocialRo()
                    .setSocialType(socialType)
                    .setSocialId(socialId)
                    .setUserId(user.getId()));
            }
            // 未有用户登陆
            else {
                log.debug("==> 使用社交账号 [{}] 去注册 or 跳转注册页面", socialType + socialId);
                // 退出社交账号登陆
                SecurityContext.signOut();
                try {
                    SpringServletContext.getResponse().orElseThrow(() -> ServiceException.wrap("系统繁忙"))
                        .sendRedirect(PageConstants.SIGN_UP_PAGE);
                } catch (IOException e) {
                    throw ServiceException.wrap(e);
                }
                return;
            }
        }
        // 社交账号已被绑定
        else {
            log.debug("==> 登陆社交账户(替换为Username登陆): [{}]", socialType + socialId);
        }

        SecurityContext.signIn(username);
    }

}
