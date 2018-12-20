package org.quickmap.apiservice;

import com.netflix.client.ClientException;
import org.quickMap.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常处理
 */
@ControllerAdvice
public class ApiServiceExceptionHandler extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 权限令牌过期异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String credentialsExpiredHandler(Exception e) throws Exception {
        return failedRender("账号过期,请重新登陆");
    }

    /**
     * 权限异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String accessDeniedErrorHandler(Exception e) throws Exception {
        return failedRender("未登录,或已登录但权限不足");
    }
    /**
     * RPC异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String ClientErrorHandler(Exception e) throws Exception {
        logger.error("RPC异常",e);
        return failedRender("调用远程服务失败");
    }
}