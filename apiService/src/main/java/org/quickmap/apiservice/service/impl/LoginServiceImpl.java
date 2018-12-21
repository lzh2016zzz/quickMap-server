package org.quickmap.apiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.constant.ApiServiceConstant;
import org.quickMap.exception.AuthenticationException;
import org.quickmap.apiservice.model.AuthParam;
import org.quickmap.apiservice.model.CreateUserParam;
import org.quickmap.apiservice.service.ILoginService;
import org.quickmap.apiservice.service.ITokenService;
import org.quickmap.dataService.dao.UserInfoMapper;
import org.quickmap.dataService.dao.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public UserInfo webLogin(AuthParam authParam, HttpServletResponse response) throws AuthenticationException {

        final String userName = authParam.getLoginName();
        final String password = authParam.getPassword();

        UserInfo data = authLogin(userName, password);

        JSONObject json = (JSONObject) JSONObject.toJSON(data);
        String accessToken = tokenService.generateToken(json);
        response.addCookie(generateCookie(AUTH_TOKEN, accessToken, true, -1));
        return data;
    }


    @Override
    public String createUser(CreateUserParam param) {
        String salt = genSalt();
        String id = genId();
        UserInfo userInfo = UserInfo.Build()
                .id(id)//id
                .loginName(param.getLoginName())//登录名
                .nickName(param.getUserName())//昵称
                .resetPasswordAnswer(param.getResetPasswordAnswer())//密保问题
                .password(getEncryptedPassword(param.getPassword(),salt))//密码
                .salt(salt)//盐值
                .roles(ApiServiceConstant.RoleList.COMMON_USER)//角色
                .build();
        userInfoMapper.insertUserInfo(userInfo);
        return id;
    }


    @Override
    public UserInfo authLogin(String userName, String password) throws AuthenticationException {
        if(!StringUtils.hasText(userName) ||!StringUtils.hasText(password)){
            throw new AuthenticationException("用户名或密码不能为空");
        }
        UserInfo userInfo = userInfoMapper.queryUserInfoLimit1(UserInfo.QueryBuild().loginName(userName).isDel(COMMON).build());
        if (userInfo == null || !verifyPassword(password, userInfo)) {
            throw new AuthenticationException("账号不存在,或密码错误");
        }
        return userInfo;
    }

    protected Cookie generateCookie(String name, String val, boolean httpOnly, int maxAge) {
        Cookie cookie = new Cookie(name, val);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setSecure(false);
        return cookie;
    }

    /**
     * 验证密码
     * @param inputPw
     * @param userInfo
     * @return
     */
    protected boolean verifyPassword(String inputPw, UserInfo userInfo) {
        if (!StringUtils.hasText(inputPw)) {
            return false;
        }
        return userInfo.getPassword().equals(getEncryptedPassword(inputPw, userInfo.getSalt()));
    }

    /**
     * 生成加密盐值
     * @return
     */
    protected String genSalt() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成加密盐值
     * @return
     */
    protected String genId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成密钥
     * @param password 密码
     * @param salt 盐值
     * @return
     */
    protected String getEncryptedPassword(String password, String salt) {
        return DigestUtils.sha1Hex(password.concat(salt));
    }
}
