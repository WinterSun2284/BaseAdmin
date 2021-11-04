package cn.wintersun.baseweb.service.intrer;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.User;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.Intf.LoginService
 * @create 2021-10-13-11:33
 * @Description TODO
 */
public interface LoginService {
    /**
     * 登录
     * @paam user 用户
     * @return cn.wintersun.basebackend.dao.User 用户信息，携带token
     **/
    Result<User> login(User user);
}
