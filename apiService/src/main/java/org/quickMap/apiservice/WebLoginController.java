package org.quickMap.apiservice;

import org.quickMap.base.BaseController;
import org.quickMap.exception.AuthenticationException;
import org.quickMap.apiservice.model.AuthParam;
import org.quickMap.apiservice.model.CreateUserParam;
import org.quickMap.apiservice.security.SecurityHelper;
import org.quickMap.apiservice.service.ILoginService;
import org.quickMap.apiservice.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class WebLoginController extends BaseController {

    @Autowired
    ILoginService webLoginService;

    @Autowired
    ITokenService tokenService;

    /**
     * 登录操作
     *
     * @return
     */
    @RequestMapping(value = "/auth")
    public String auth(@RequestBody AuthParam authParam) {
        try {
            return jsonRender(webLoginService.webLogin(authParam, this.response));
        } catch (AuthenticationException e) {
            return failedRender("登录失败");
        }
    }

    /**
     * 登录操作
     *
     * @return
     */
    @RequestMapping(value = "/logOut")
    public String invalidate() {
        tokenService.invalidate(SecurityHelper.getTokenFrom(request.getCookies()));
        return successRender("成功");
    }

    /**
     * 登录操作
     *
     * @return
     */
    @RequestMapping(value = "/createUser")
    public String createUser(@RequestBody CreateUserParam param) {
        webLoginService.createUser(param);
        return successRender("成功");
    }
}
