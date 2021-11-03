package cn.wintersun.basebackend.dao;

import cn.wintersun.basebackend.common.RequestPage;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dto.User
 * @create 2021-10-13-10:15
 * @Description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User extends RequestPage {

    @TableId
    private Integer id;

    @TableField(value = "USER_ACCOUNT")
    private String userAccount;

    private String userName;

    private String password;

    private transient  String token;

    private transient List<Modules> modules;

    private transient List<Roles> roles;

    private transient List<String> roleNames;

    private transient Integer[] roleIds;

}
