package org.quickmap.apiservice;

import org.quickMap.base.BaseController;
import org.quickMap.exception.AuthenticationException;
import org.quickmap.apiservice.model.AuthParam;
import org.quickmap.apiservice.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class WebLoginController extends BaseController {

    @Autowired
    ILoginService webLoginService;


    /**
     * 登陆操作
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/auth")
    public String auth(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            webLoginService.webLogin(AuthParam.Builder.Builder().userName(userName).password(password).build(),this.response);
            return successRender();
        }catch (AuthenticationException e){
            return failedRender("登录失败");
        }
    }
}
