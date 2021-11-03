package cn.wintersun.basebackend.service.intrer.user;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.Modules;
import cn.wintersun.basebackend.dao.Roles;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.intrer.user.RoleServiceImpl
 * @create 2021-10-22-17:24
 * @Description TODO
 */
public interface RoleService {
    Result<IPage<Roles>> list(Roles roles);

    Result<List<Modules>> getModules();

    Result<Object> checkRoleName(String roleName, Integer roleId);

    Result<Object> save(Roles role);

    Result<Object> delete(String roleIds) throws Exception;
}
