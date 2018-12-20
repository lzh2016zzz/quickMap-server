package org.quickmap.apiservice.service;

import org.quickmap.dataService.dao.model.UserInfo;

import java.util.Map;

/**
 * 安全令牌服务
 */
public interface ITokenService {

    /**
     * 获取令牌
     * @param claims
     * @return
     */
    String generateToken(Map<String,Object>claims);

    /**
     * 解析令牌
     * @param token
     * @return
     */
    UserInfo getUserByToken(String token);

    /**
     * 拉黑令牌
     * @param token
     */
    void invalidate(String token);

}
