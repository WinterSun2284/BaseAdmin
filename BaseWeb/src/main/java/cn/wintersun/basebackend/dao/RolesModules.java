package cn.wintersun.basebackend.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**  
 * 
 * 
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dao.RolesModules
 * @create 2021-10-22-17:54
 * @Description TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "roles_modules")
public class RolesModules {
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "ROLE_ID")
    private Integer roleId;

    @TableField(value = "MODULE_ID")
    private Integer moduleId;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    public static final String COL_ID = "ID";

    public static final String COL_ROLE_ID = "ROLE_ID";

    public static final String COL_MODULE_ID = "MODULE_ID";

    public static final String COL_CREATE_TIME = "CREATE_TIME";
}