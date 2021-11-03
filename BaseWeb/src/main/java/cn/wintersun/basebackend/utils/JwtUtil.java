package cn.wintersun.basebackend.utils;

import cn.wintersun.basebackend.dao.Modules;
import cn.wintersun.basebackend.dao.User;
import cn.wintersun.basebackend.exception.TokenVerificationFailedException;
import com.alibaba.fastjson.JSONArray;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.utils.JwtUtil
 * @create 2021-10-13-10:27
 * @Description TODO
 */
public class JwtUtil {


    public static final String TOKEN_HEADER = "token";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SUBJECT = "congge";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    public static final String APPSECRET_KEY = "congge_secret";

    private static final String ROLE_CLAIMS = "rol";

    public static String generateJsonWebToken(User user) {
        if (user.getId() == null || user.getUserName() == null || user.getUserAccount() == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, "rol");
        return Jwts
                .builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .claim("id", user.getId())
                .claim("name", user.getUserName())
                .claim("account", user.getUserAccount())
                .claim("modules", JSONArray.toJSONString(user.getModules()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS512, APPSECRET_KEY).compact();
    }

    public static User getUserForToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(APPSECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            String id = claims.get("id").toString();
            String userAccount = claims.get("account").toString();
            String userName = claims.get("name").toString();
            String moduleObj = claims.get("modules").toString();
            User user = new User();
            user.setId(Integer.valueOf(id));
            user.setUserAccount(userAccount);
            user.setUserName(userName);
            if (moduleObj != null) {
                List<Modules> modules = JSONArray.parseArray(moduleObj, Modules.class);
                user.setModules(modules);
            }
            return user;
        } catch (Exception e) {
            throw new TokenVerificationFailedException("token校验失败");
        }
    }

    /**
     * 生成token
     */
    public static String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        return Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
    }

    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     */
    public static String getUsername(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims.get("account").toString();
        } catch (Exception e) {
            throw new TokenVerificationFailedException("token校验失败");
        }
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            return !expiration.before(new Date());
        } catch (Exception e) {
            throw new TokenVerificationFailedException("token校验失败");
        }
    }
}
