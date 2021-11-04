package cn.wintersun.baseweb.service.intrer.user;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.dao.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.intrer.UserServier
 * @create 2021-10-15-11:52
 * @Description TODO
 */
public interface UserService {
    Result<IPage<User>> list(User param);

    Result<List<Roles>> getRoles();

    Result<User> save(User user);

    Result<Object> delete(String user) throws Exception;

    Result<Object> checkAccount(String account);

    /**
     * 根据用户id获取用户的详细信息，包括角色、可访问的module
     *
     * @param userId 用户id
     * @return cn.wintersun.basebackend.dao.User 用户
     **/
    User getUserById(Integer userId);

}
