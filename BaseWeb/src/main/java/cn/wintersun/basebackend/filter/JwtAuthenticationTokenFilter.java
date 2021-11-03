package cn.wintersun.basebackend.filter;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.common.ResultCode;
import cn.wintersun.basebackend.dao.User;
import cn.wintersun.basebackend.dto.JwtUser;
import cn.wintersun.basebackend.exception.TokenVerificationFailedException;
import cn.wintersun.basebackend.utils.GetUserinfoUtil;
import cn.wintersun.basebackend.utils.JwtUtil;
import cn.wintersun.basebackend.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.filter.JWTAuthorizationFilter
 * @create 2021-10-13-10:40
 * @Description TODO
 */

/**
 * token校验,引用的stackoverflow一个答案里的处理方式
 * Author: JoeTao
 * createAt: 2018/9/14
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired private GetUserinfoUtil getUserinfoUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Headers", "*");


        String requestURI = request.getRequestURI();
        String auth_token = request.getHeader(JwtUtil.TOKEN_HEADER);


        if ("/login".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String username = JwtUtil.getUsername(auth_token);

            //SecurityContext context = SecurityContextHolder.getContext();
            //Authentication authentication1 = context.getAuthentication();
            if (username != null && getUserinfoUtil.getAuthentication() == null) {
                User user = JwtUtil.getUserForToken(auth_token);
                JwtUser jwtUser = new JwtUser(user);
                if (JwtUtil.isExpiration(auth_token)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //logger.info(String.format("Authenticated user %s, setting security context", username));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request, response);
        } catch (TokenVerificationFailedException e) {
            LOGGER.error(e.getMessage(), e);
            ResponseUtil.out(response, Result.failure(ResultCode.VALID_TOKEN));
        }
    }
}
