package cn.wintersun.baseweb.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**  
 * 
 * 
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.dao.ClassRealm
 * @create 2021-11-10-9:11
 * @Description TODO
 */
/**
    * 领域分类表
    */
@Data
@Builder
@AllArgsConstructor
@TableName(value = "class_realm")
public class ClassRealm {
    @TableField(value = "ID")
    private Integer id;

    /**
     * 代码
     */
    @TableField(value = "CODE")
    private String code;

    /**
     * 名称
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    public static final String COL_ID = "ID";

    public static final String COL_CODE = "CODE";

    public static final String COL_NAME = "NAME";

    public static final String COL_REMARK = "REMARK";

    public static final String COL_CREATE_TIME = "CREATE_TIME";
}