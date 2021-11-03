package cn.wintersun.basebackend.controller;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.User;
import cn.wintersun.basebackend.service.intrer.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.controller.TestController
 * @create 2021-10-13-9:54
 * @Description TODO
 */
@RestController
public class LoginController {

    @Autowired private LoginService loginService;

    @RequestMapping("login")
    public Result<User> login(@RequestBody User user){
        return loginService.login(user);
    }
}
