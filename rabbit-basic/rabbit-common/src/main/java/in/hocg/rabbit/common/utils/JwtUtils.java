package in.hocg.rabbit.common.utils;

import in.hocg.rabbit.common.constant.GlobalConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class JwtUtils {
    /**
     * 10年
     */
    private static final long EXPIRE_MILLIS = GlobalConstant.USER_TOKEN_TIME_EXPIRE_MILLIS;
    private static final String KEY = GlobalConstant.AUTHOR.toUpperCase(Locale.ROOT);

    /**
     * 编码 Token
     *
     * @param username 用户名
     * @return token
     */
    public String encode(String username) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long expirationTimeMillis = currentTimeMillis + EXPIRE_MILLIS;
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(username)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(expirationTimeMillis))
            .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /**
     * 解码 Token
     *
     * @param token
     * @return
     */
    public String decode(String token) {
        return Jwts.parser().setSigningKey(KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /**
     * 强制解析，忽略过期时间
     *
     * @param token
     * @return
     */
    public String decodeNoExpired(String token) {
        try {
            return Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
}
