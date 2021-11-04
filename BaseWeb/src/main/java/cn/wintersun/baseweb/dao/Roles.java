package cn.wintersun.baseweb.dao;

import cn.wintersun.baseweb.common.RequestPage;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dao.Roles
 * @create 2021-10-14-9:52
 * @Description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "roles")
public class Roles extends RequestPage {
    //@TableId(value = "id", type = IdType.INPUT)
    @TableId
    private Integer id;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "role_desc")
    private String roleDesc;

    @TableField(value = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_DESC = "role_desc";

    public static final String COL_CREATE_TIME = "create_time";

    private transient List<Modules> modules;

    private transient List<String> moduleNames;

    private transient Integer[] moduleIds;

    private transient String startTime;

    private transient String endTime;
}