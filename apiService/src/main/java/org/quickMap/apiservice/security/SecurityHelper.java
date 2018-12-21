package org.quickMap.apiservice.security;

import io.jsonwebtoken.Claims;
import org.quickMap.constant.ApiServiceConstant;
import org.springframework.security.authentication.CredentialsExpiredException;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public class SecurityHelper {

    /**
     * 获取token
     * @param cookies
     * @return
     */
    public static String getTokenFrom(Cookie[] cookies) {
        if(cookies == null){
            return null;
        }
        Optional<Cookie> optional = Arrays.stream(cookies).filter(cookie -> ApiServiceConstant.AUTH_TOKEN.equals(cookie.getName())).findFirst();
        if (optional.isPresent()) {
            return optional.get().getValue();
        }
        return null;
    }

    /**
     * 校验是否过期
     * @param claims 主体
     */
    public static void checkValidate(Claims claims) {
        Date expiration = claims.getExpiration();
        Date now = new Date();
        if (now.getTime() > expiration.getTime()) {
            throw new CredentialsExpiredException("");
        }
    }

}
