package cn.wintersun.baseweb.controller.user;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.Modules;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.service.intrer.user.RoleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
 * @className cn.wintersun.basebackend.controller.user.Rolecontroller
 * @create 2021-10-22-17:05
 * @Description TODO
 */
@RestController
@RequestMapping("/admin/user/role")
@PreAuthorize("hasAuthority('2')")
public class RoleController {

    @Autowired private RoleService roleService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Result<IPage<Roles>> list(@RequestBody Roles roles){
     return  roleService.list(roles);
    }

    @RequestMapping(value = "getModules",method = RequestMethod.GET)
    public Result<List<Modules>> getModules(){
        return roleService.getModules();
    }

    @RequestMapping(value = "checkRoleName",method = RequestMethod.POST)
    public Result<Object> checkRoleName(@RequestBody JSONObject jsonParam){
        if (StringUtils.isEmpty(jsonParam.getString("roleName"))){
            return Result.error("请求参数为空！");
        }
        return  roleService.checkRoleName(jsonParam.getString("roleName"), jsonParam.getInteger("roleId"));
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Roles role){
        return  roleService.save(role);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody String roleIds){
        try {
            return roleService.delete(roleIds);
        } catch (Exception e) {
            return Result.failure(500, e.getMessage());
        }
    }

}
