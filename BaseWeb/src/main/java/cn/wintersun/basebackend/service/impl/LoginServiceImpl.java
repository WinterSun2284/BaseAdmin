package cn.wintersun.basebackend.service.impl;


import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.User;
import cn.wintersun.basebackend.mapper.ModulesMapper;
import cn.wintersun.basebackend.mapper.RolesMapper;
import cn.wintersun.basebackend.mapper.UserMapper;
import cn.wintersun.basebackend.service.intrer.LoginService;
import cn.wintersun.basebackend.service.intrer.user.UserService;
import cn.wintersun.basebackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.LoginService
 * @create 2021-10-13-11:34
 * @Description TODO
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private ModulesMapper modulesMapper;

    @Autowired
    private UserService userService;


    @Override
    public Result<User> login(User userInfo) {
        User user = userMapper.findByUserAccountAndPassword(userInfo.getUserAccount(), userInfo.getPassword());
        if (user == null) {
            return Result.failure(500, "用户名或者密码错误！");
        }

        user = userService.getUserById(user.getId());

        String token = JwtUtil.generateJsonWebToken(user);

        user.setToken(token);

        return Result.ok(user);
    }
}
