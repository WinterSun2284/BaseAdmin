package cn.wintersun.baseweb.controller.user;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.dao.User;
import cn.wintersun.baseweb.service.intrer.user.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.controller.user.UserController
 * @create 2021-10-15-11:28
 * @Description TODO
 */
@RestController
@RequestMapping("/admin/user/user")
@PreAuthorize("hasAuthority('2')")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping("list")
    public Result<IPage<User>> list(@RequestBody User param) {
        return userService.list(param);
    }


    @RequestMapping(value = "getRoles",method = RequestMethod.GET)
    public Result<List<Roles>> getRoles(){
        return userService.getRoles();
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result<User> save(@RequestBody User user){
        try {
            return  userService.save(user);
        } catch (Exception e) {
            return Result.failure(500, e.getMessage());
        }
    }

    @RequestMapping(value="delete",method=RequestMethod.POST)
    public Result<Object> delete(@RequestBody String userIds){
        try {
            return  userService.delete(userIds);
        } catch (Exception e) {
            return Result.failure(500, e.getMessage());
        }
    }


    @RequestMapping(value="checkAccount",method=RequestMethod.POST)
    public Result<Object> checkAccount(@RequestBody String account){

        return userService.checkAccount(account);
    }

}
