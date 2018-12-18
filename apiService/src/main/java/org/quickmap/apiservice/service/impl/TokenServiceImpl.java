package org.quickmap.apiservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.quickMap.exception.AuthenticationException;
import org.quickmap.apiservice.service.ITokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements ITokenService {

    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${token.expireSeconds}")
    Integer expireSeconds;

    @Value("${token.secret}")
    String secret;

    public String getToken(String subject) throws AuthenticationException {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireSeconds * 1000);
        return Jwts.builder().setHeaderParam("type", "jwt").setSubject(subject).setIssuedAt(nowDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

}
