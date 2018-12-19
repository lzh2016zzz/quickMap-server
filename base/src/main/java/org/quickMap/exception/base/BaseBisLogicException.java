package org.quickMap.exception.base;

/**
 * 业务逻辑异常
 */
public class BaseBisLogicException extends RuntimeException{

    public BaseBisLogicException(String msg) {
        super(msg);
    }

    public BaseBisLogicException() {
        super();
    }
}
