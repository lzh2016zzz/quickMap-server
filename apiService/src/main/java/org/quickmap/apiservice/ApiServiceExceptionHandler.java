package org.quickmap.apiservice;

import com.netflix.client.ClientException;
import org.quickMap.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiServiceExceptionHandler extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
        return failedRender("权限不足:" + e.getMessage());
    }

    /**
     * RPC异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ClientException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String ClientErrorHandler(Exception e) throws Exception {
        logger.error("RPC异常",e);
        return failedRender("调用服务失败");
    }
}