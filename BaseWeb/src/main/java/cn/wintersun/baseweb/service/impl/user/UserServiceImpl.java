package cn.wintersun.baseweb.service.impl.user;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.common.ResultCode;
import cn.wintersun.baseweb.dao.Modules;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.dao.User;
import cn.wintersun.baseweb.dao.UserRoles;
import cn.wintersun.baseweb.dto.JwtUser;
import cn.wintersun.baseweb.mapper.ModulesMapper;
import cn.wintersun.baseweb.mapper.RolesMapper;
import cn.wintersun.baseweb.mapper.UserMapper;
import cn.wintersun.baseweb.mapper.UserRolesMapper;
import cn.wintersun.baseweb.service.intrer.user.UserService;
import cn.wintersun.baseweb.utils.GetUserinfoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.user.UserServiceImpl
 * @create 2021-10-15-11:52
 * @Description TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired private ModulesMapper modulesMapper;

    @Autowired
    private GetUserinfoUtil getUserAccount;

    @Override
    public Result<IPage<User>> list(User param) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        if (StringUtils.isNotEmpty(param.getSortField()) && StringUtils.isNotEmpty(param.getSortOrder())) {
            String fieldName;
            switch (param.getSortField()) {
                case "userAccount":
                    fieldName = "USER_ACCOUNT";
                    break;
                case "userName":
                    fieldName = "USER_NAME";
                    break;
                default:
                    fieldName = "ID";
            }
            queryWrapper.orderBy(true, param.getSortOrder().contains("asc"), fieldName);
        }else {
            queryWrapper.orderBy(true, true, "id");
        }

        if (StringUtils.isNotEmpty(param.getUserAccount())) {
            queryWrapper.like("USER_ACCOUNT", param.getUserAccount());
        }


        if (StringUtils.isNotEmpty(param.getUserName())) {
            queryWrapper.like("USER_NAME", param.getUserName());
        }

        Page<User> userPage = new Page<>(param.getCurrent(), param.getPageSize());
        IPage<User> iPage = userMapper.selectPage(userPage, queryWrapper);
        List<User> records = iPage.getRecords();
        for (User record : records) {
            List<Roles> roles = rolesMapper.findByUserAccount(record.getUserAccount());
            List<String> roleNames = roles.stream().map(Roles::getRoleName).collect(Collectors.toList());
            record.setRoleNames(roleNames);
            record.setRoles(roles);
        }
        iPage.setRecords(records);

        return Result.ok(iPage);
    }

    @Override
    public Result<List<Roles>> getRoles() {

        List<Roles> roles = rolesMapper.selectList(null);

        return Result.ok(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<User> save(User user) {

        if (user.getId() != null) {
            //先更新所有的用户信息
            userMapper.updateById(user);
            //删除用户关联的角色
            QueryWrapper<UserRoles> queryWrapper = Wrappers.query();
            queryWrapper.eq("user_id", user.getId());
            userRolesMapper.delete(queryWrapper);

            //新插入现有角色
            for (Integer roleId : user.getRoleIds()) {
                UserRoles userRoles = UserRoles.builder().userId(user.getId()).roleId(roleId).build();
                userRolesMapper.insert(userRoles);
            }

            JwtUser currentUser = getUserAccount.getUser();

            if (currentUser != null && StringUtils.isNotEmpty(user.getPassword()) && currentUser.getUsername().equals(user.getUserAccount())) {
                return Result.failure(ResultCode.CHANGE_PASSWORD);
            }
        } else {
            userMapper.insert(user);
            //插入角色
            for (Integer roleId : user.getRoleIds()) {
                UserRoles userRoles = UserRoles.builder().userId(user.getId()).roleId(roleId).build();
                userRolesMapper.insert(userRoles);
            }
        }

        return Result.ok("修改成功！", getUserById(user.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> delete(String userIds) throws Exception {

        if (StringUtils.isBlank(userIds)) {
            throw new Exception("被删除的id为空");
        }

        List<String> ids = Arrays.stream(userIds.split(",")).collect(Collectors.toList());
        QueryWrapper<UserRoles> query = Wrappers.query();
        query.in("user_id", ids);
        userRolesMapper.delete(query);

        userMapper.deleteBatchIds(ids);


        return Result.failure(200, "删除成功！");
    }

    @Override
    public Result<Object> checkAccount(String account) {
        QueryWrapper<User> query = Wrappers.query();
        query.eq("USER_ACCOUNT", account);

        User user = userMapper.selectOne(query);
        if (user == null) {
            return Result.ok();
        } else {
            return Result.error("账号已存在！");
        }
    }

    @Override
    public User getUserById(Integer userId){
        QueryWrapper<User> query = Wrappers.query();
        query.eq("id", userId);
        User user = userMapper.selectOne(query);
        List<Roles> roles = rolesMapper.findByUserAccount(user.getUserAccount());
        if (roles!=null){
            user.setRoles(roles);
            Set<Modules> modulesSet=  new HashSet<>();
            for (Roles role : roles) {
                List<Modules> modules = modulesMapper.findByRoleId(role.getId());
                modulesSet.addAll(modules);
            }

            List<Modules> modulesList = new ArrayList<>(modulesSet);

            user.setModules(modulesList);
        }

        return user;
    }

}
