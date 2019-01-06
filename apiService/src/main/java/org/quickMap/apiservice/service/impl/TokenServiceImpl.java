package org.quickMap.apiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.quickMap.apiservice.security.SecurityHelper;
import org.quickMap.apiservice.service.ITokenService;
import org.quickMap.dataService.dao.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements ITokenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${token.expireSeconds}")
    private Integer expireSeconds;

    @Value("${token.secret}")
    private String secret;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void invalidate(String token) {
        Claims claims = getClaims(token);
        if (claims.getExpiration().getTime() < new Date().getTime()) {
            return;
        }
        UserInfo userInfo = getUserInfoByClaims(claims);
        String key = getBlockKey(userInfo.getLoginName());

        Long expire = redisTemplate.getExpire(key);
        redisTemplate.opsForSet().add(key, token);
        redisTemplate.expire(key, expire > 0 && expire > expireSeconds ? expire: expireSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String generateToken(Map<String, Object> claims) {
        Assert.notNull(claims, "参数不能为空");
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireSeconds * 1000);
        return Jwts.builder().setClaims(claims).setIssuedAt(nowDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    @Override
    public UserInfo getUserByToken(String token) {
        try {
            Claims claims = getClaims(token);
            SecurityHelper.checkValidate(claims);
            UserInfo userInfo = getUserInfoByClaims(claims);
            if (tokenIsBlocked(userInfo, token)) {
                return null;
            }
            return getUserInfoByClaims(claims);
        } catch (Exception e) {
            logger.debug("token解密失败", e);
            return null;
        }
    }

    /**
     * 获取用户
     *
     * @param claims
     * @return
     */
    protected UserInfo getUserInfoByClaims(Claims claims) {
        if(claims == null){
            return null;
        }
        return new JSONObject(claims).toJavaObject(UserInfo.class);
    }

    /**
     * token是否被拉黑
     *
     * @param userInfo
     * @param token
     * @return
     */
    protected boolean tokenIsBlocked(UserInfo userInfo, String token) {
        if(token == null || userInfo == null){
            return false;
        }
        return redisTemplate.opsForSet().isMember(getBlockKey(userInfo.getLoginName()), token);
    }

    /**
     * 获取黑名单key
     *
     * @param loginName
     * @return
     */
    protected String getBlockKey(String loginName) {
        return "block_".concat(loginName);
    }


    /**
     * 获取主体
     *
     * @param token
     * @return
     */
    protected Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
