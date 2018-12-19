package org.quickmap.apiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.exception.AuthenticationException;
import org.quickmap.apiservice.model.AuthParam;
import org.quickmap.apiservice.service.ILoginService;
import org.quickmap.apiservice.service.ITokenService;
import org.quickmap.dataService.dao.UserInfoMapper;
import org.quickmap.dataService.dao.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.quickMap.constant.ApiServiceConstant.AUTH_TOKEN;
import static org.quickMap.constant.ApiServiceConstant.DelStatus.COMMON;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    ITokenService tokenService;


    @Override
    public void webLogin(AuthParam authParam, HttpServletResponse response) throws AuthenticationException {

        final String userName = authParam.getUserName();
        final String password = authParam.getPassword();

        Assert.hasText(userName, "用户名不能为空");
        Assert.hasText(password, "密码不能为空");

        UserInfo data = authLogin(userName, password);

        JSONObject json = (JSONObject) JSONObject.toJSON(data);
        String accessToken = tokenService.generateToken(json);
        //response.addCookie(generateCookie(USER_INFO, , false, -1));
        response.addCookie(generateCookie(AUTH_TOKEN, accessToken, true, -1));
    }


    @Override
    public String createUser(UserInfo param) {
        return null;
    }


    @Override
    public UserInfo authLogin(String userName, String password) throws AuthenticationException {
        UserInfo userInfo = userInfoMapper.queryUserInfoLimit1(UserInfo.QueryBuild().loginName(userName).isDel(COMMON).build());
        if (!verifyPassword(password, userInfo)) {
            throw new AuthenticationException("账号不存在,或密码错误");
        }
        return userInfo;
    }

    protected Cookie generateCookie(String name, String val, boolean httpOnly, int maxAge) {
        Cookie cookie = new Cookie(name, val);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        return cookie;
    }

    protected boolean verifyPassword(String inputPw, UserInfo userInfo) {
        if (!StringUtils.hasText(inputPw)) {
            return false;
        }
        return userInfo.getPassword().equals(getEncryptedPassword(inputPw, userInfo.getSalt()));
    }

    protected String genSalt() {
        return UUID.randomUUID().toString();
    }

    protected String getEncryptedPassword(String password, String salt) {
        return DigestUtils.sha1Hex(password.concat(salt));
    }
}
