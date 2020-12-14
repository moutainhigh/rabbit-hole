package com.github.lotus.chaos.biz.modules.ums.utils;

import com.github.lotus.chaos.biz.modules.ums.cache.TokenCacheService;
import in.hocg.boot.web.SpringContext;
import in.hocg.boot.web.result.ResultCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import javax.security.auth.login.AccountExpiredException;
import java.util.Collections;
import java.util.Date;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class JwtUtils {
    private static final long EXPIRE_MILLIS = 1000 * 60 * 60 * 10L;
    private static final String KEY = "HOCGIN";

    /**
     * 编码 Token
     *
     * @param username
     * @return
     */
    public String encode(String username) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long expirationTimeMillis = currentTimeMillis + EXPIRE_MILLIS;
        final String token = Jwts.builder().setClaims(Collections.emptyMap()).setSubject(username)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(expirationTimeMillis))
            .signWith(SignatureAlgorithm.HS256, KEY).compact();
        final TokenCacheService tokenManager = SpringContext.getBean(TokenCacheService.class);
        tokenManager.setToken(username, token, EXPIRE_MILLIS);
        return token;
    }

    /**
     * 解码 Token
     *
     * @param token
     * @return
     * @throws AccountExpiredException
     */
    public String decode(String token) throws AccountExpiredException {
        final String username = Jwts.parser().setSigningKey(KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
        final TokenCacheService tokenManager = SpringContext.getBean(TokenCacheService.class);
        final String serverToken = tokenManager.getToken(username);
        if (token.equals(serverToken)) {
            return username;
        }
        throw new AccountExpiredException(ResultCode.AUTHENTICATION_ERROR.getMessage());
    }
}
