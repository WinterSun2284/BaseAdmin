package cn.wintersun.basebackend.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.exception.sadsadsad
 * @create 2021-10-14-15:08
 * @Description TODO
 */
public class TokenVerificationFailedException extends AuthenticationException {
    public TokenVerificationFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TokenVerificationFailedException(String msg) {
        super(msg);
    }
}
