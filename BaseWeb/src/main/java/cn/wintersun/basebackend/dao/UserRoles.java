package cn.wintersun.basebackend.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dao.UserRoles
 * @create 2021-10-14-9:52
 * @Description TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {
    private Integer id;

    private Integer userId;

    private Integer roleId;

}