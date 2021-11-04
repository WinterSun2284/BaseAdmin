package cn.wintersun.baseweb.component;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.common.ResultCode;
import cn.wintersun.baseweb.exception.TokenVerificationFailedException;
import cn.wintersun.baseweb.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.component.JwtAuthenticationEntryPoint
 * @create 2021-10-13-10:45
 * @Description TODO
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (authException instanceof TokenVerificationFailedException) {
            ResponseUtil.out(response, Result.failure(ResultCode.VALID_TOKEN));
        }else {
            ResponseUtil.out(response, Result.failure(401, authException.getMessage()));
        }
    }
}