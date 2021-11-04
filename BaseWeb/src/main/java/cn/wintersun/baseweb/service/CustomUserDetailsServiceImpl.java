package cn.wintersun.baseweb.service;

import cn.wintersun.baseweb.dao.User;
import cn.wintersun.baseweb.dto.JwtUser;
import cn.wintersun.baseweb.mapper.UserMapper;
import cn.wintersun.baseweb.service.intrer.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登陆身份认证
 * @author: JoeTao
 * createAt: 2018/9/14
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUserAccount(username);

        return new JwtUser(user);

    }
}
