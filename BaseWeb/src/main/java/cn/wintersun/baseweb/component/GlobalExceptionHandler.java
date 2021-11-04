package cn.wintersun.baseweb.component;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.common.ResultCode;
import cn.wintersun.baseweb.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.component.GlableExceptionHandler
 * @create 2021-10-15-13:09
 * @Description TODO
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = AccessDeniedException.class)
    public void accessDeniedExceptionHandler(HttpServletResponse response, AccessDeniedException e) {
        ResponseUtil.out(response, Result.failure(ResultCode.NO_ACCESS));
    }
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) {
        LOGGER.error(e.getMessage(), e);
        ResponseUtil.out(response, Result.failure(500,e.getMessage()));
    }
}
