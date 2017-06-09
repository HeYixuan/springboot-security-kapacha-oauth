package org.springframe.common.wxpay.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: HeYixuan
 * @create: 2017-06-07 9:44
 */
public class CaptchaException extends AuthenticationException {

    private String message;

    public CaptchaException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
