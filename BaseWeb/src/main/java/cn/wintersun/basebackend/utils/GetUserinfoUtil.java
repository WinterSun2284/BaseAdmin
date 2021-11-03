package cn.wintersun.basebackend.utils;

import cn.wintersun.basebackend.dto.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.utils.GetUserinfoUtil
 * @create 2021-10-20-16:58
 * @Description TODO
 */
@Component
public class GetUserinfoUtil {
    public JwtUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object obj = authentication.getPrincipal();
        if (obj instanceof JwtUser) {
            return (JwtUser) obj;
        }
        return null;
    }

    public Authentication getAuthentication(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }
}
