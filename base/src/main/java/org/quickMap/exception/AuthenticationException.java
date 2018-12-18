package org.quickMap.exception;

/**
 * 权限校验异常
 */
public class AuthenticationException extends RuntimeException{


    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException() {

    }
}
