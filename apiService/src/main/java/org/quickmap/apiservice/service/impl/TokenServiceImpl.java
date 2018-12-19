package org.quickmap.apiservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.quickmap.apiservice.service.ITokenService;
import org.quickmap.dataService.dao.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

@Service
public class TokenServiceImpl implements ITokenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${token.expireSeconds}")
    private Integer expireSeconds;

    @Value("${token.secret}")
    private String secret;

    @Override
    public String generateToken(Map<String,Object>claims) {
        Assert.notNull(claims, "参数不能为空");
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireSeconds * 1000);
        return Jwts.builder().setClaims(claims).setIssuedAt(nowDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    @Override
    public UserInfo getUserByToken(String token) {
        Assert.hasText(token, "参数不能为空");
        try {
            return new JSONObject(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()).toJavaObject(UserInfo.class);
        } catch (Exception e) {
            logger.debug("token解密失败", e);
            return null;
        }
    }

}
