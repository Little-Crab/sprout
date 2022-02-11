package com.pjf.server.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author pjf
 * @since 2022/2/10 15:06
 * 手机号未找到异常类
 **/
public class PhoneNotFoundException extends AuthenticationException {
    public PhoneNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PhoneNotFoundException(String msg) {
        super(msg);
    }
}
