package cn.wintersun.baseweb.service.impl;



import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.User;
import cn.wintersun.baseweb.mapper.ModulesMapper;
import cn.wintersun.baseweb.mapper.RolesMapper;
import cn.wintersun.baseweb.mapper.UserMapper;
import cn.wintersun.baseweb.service.intrer.LoginService;
import cn.wintersun.baseweb.service.intrer.user.UserService;
import cn.wintersun.baseweb.utils.JwtUtil;
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
