package org.quickMap.apiservice.service;

import org.quickMap.exception.AuthenticationException;
import org.quickMap.apiservice.model.AuthParam;
import org.quickMap.apiservice.model.CreateUserParam;
import org.quickMap.dataService.dao.model.UserInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 登陆服务
 */
public interface ILoginService {

    /**
     * 网页登陆
     * @param authParam
     * @param response
     * @throws AuthenticationException
     */
    UserInfo webLogin(AuthParam authParam, HttpServletResponse response)throws AuthenticationException;

    /**
     * 创建用户
     * @param param
     * @return id
     */
    String createUser(CreateUserParam param);

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     * @throws AuthenticationException
     */
    UserInfo authLogin(String userName, String password) throws AuthenticationException;
}
