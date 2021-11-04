package cn.wintersun.baseweb.service.impl.user;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.Modules;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.dao.RolesModules;
import cn.wintersun.baseweb.mapper.ModulesMapper;
import cn.wintersun.baseweb.mapper.RolesMapper;
import cn.wintersun.baseweb.mapper.RolesModulesMapper;
import cn.wintersun.baseweb.service.intrer.user.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.user.RoleServiceImpl
 * @create 2021-10-22-17:24
 * @Description TODO
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private ModulesMapper modulesMapper;

    @Autowired
    private RolesModulesMapper rolesModulesMapper;

    @Override
    public Result<IPage<Roles>> list(Roles roles) {
        QueryWrapper<Roles> queryWrapper = Wrappers.query();

        if (StringUtils.isNotEmpty(roles.getSortField()) && StringUtils.isNotEmpty(roles.getSortOrder())) {
            String fieldName;
            if ("createTime".equals(roles.getSortField())) {
                fieldName = Roles.COL_CREATE_TIME;
            } else {
                fieldName = "createTime";
            }
            queryWrapper.orderBy(true, roles.getSortOrder().contains("asc"), fieldName);
        }else {
            queryWrapper.orderBy(true, true, "id");
        }


        if (StringUtils.isNotEmpty(roles.getRoleName())) {
            queryWrapper.like(Roles.COL_ROLE_NAME, roles.getRoleName());
        }

        if (StringUtils.isNotEmpty(roles.getRoleDesc())) {
            queryWrapper.like(Roles.COL_ROLE_DESC, roles.getRoleDesc());
        }

        if (StringUtils.isNotEmpty(roles.getStartTime())) {
            queryWrapper.ge(Roles.COL_CREATE_TIME, roles.getStartTime());
        }

        if (StringUtils.isNotEmpty(roles.getEndTime())) {
            queryWrapper.le(Roles.COL_CREATE_TIME, roles.getEndTime());
        }


        Page<Roles> userPage = new Page<>(roles.getCurrent(), roles.getPageSize());
        IPage<Roles> userIPage = rolesMapper.selectPage(userPage, queryWrapper);

        List<Roles> records = userIPage.getRecords();
        for (Roles record : records) {
            List<Modules> modules = modulesMapper.findByRoleId(record.getId());
            List<String> moduleNames = modules.stream().map(Modules::getModuleName).collect(Collectors.toList());
            record.setModules(modules);
            record.setModuleNames(moduleNames);
        }


        return Result.ok(userIPage);
    }

    @Override
    public Result<List<Modules>> getModules() {

        List<Modules> modules = modulesMapper.selectList(null);

        return Result.ok(modules);
    }

    @Override
    public Result<Object> checkRoleName(String roleName, Integer roleId) {
        QueryWrapper<Roles> query = Wrappers.query();
        query.eq(Roles.COL_ROLE_NAME, roleName);
        if (roleId != null) {
            query.ne(Roles.COL_ID, roleId);
        }

        Roles roles = rolesMapper.selectOne(query);
        if (roles == null) {
            return Result.ok();
        } else {
            return Result.error("角色名不可用！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> save(Roles role) {
        if (role.getId() != null) {
            rolesMapper.updateById(role);
            //删除用户关联的角色
            QueryWrapper<RolesModules> queryWrapper = Wrappers.query();
            queryWrapper.eq(RolesModules.COL_ROLE_ID, role.getId());
            rolesModulesMapper.delete(queryWrapper);

            //新插入现有角色
            for (Integer moduleId : role.getModuleIds()) {
                RolesModules rolesModules = RolesModules.builder().roleId(role.getId()).moduleId(moduleId).build();
                rolesModulesMapper.insert(rolesModules);
            }

        } else {
            role.setCreateTime(new Date());
            rolesMapper.insert(role);

            for (Integer moduleId : role.getModuleIds()) {
                RolesModules rolesModules = RolesModules.builder().roleId(role.getId()).moduleId(moduleId).build();
                rolesModulesMapper.insert(rolesModules);
            }
        }


        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> delete(String roleIds) throws Exception {

        if (StringUtils.isBlank(roleIds)) {
            throw new Exception("被删除的id为空");
        }

        List<String> ids = Arrays.stream(roleIds.split(",")).collect(Collectors.toList());

        QueryWrapper<RolesModules> query = Wrappers.query();
        query.in("ROLE_ID", ids);
        rolesModulesMapper.delete(query);

        rolesMapper.deleteBatchIds(ids);

        return Result.failure(200, "删除成功！");
    }
}
