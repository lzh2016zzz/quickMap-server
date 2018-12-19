package org.quickMap.exception;

import org.quickMap.exception.base.BaseBisLogicException;

/**
 * 权限校验异常
 */
public class AuthenticationException extends BaseBisLogicException {


    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException() {
        super();
    }
}
