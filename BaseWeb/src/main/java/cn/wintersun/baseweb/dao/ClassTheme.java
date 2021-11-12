package cn.wintersun.baseweb.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**  
 * 
 * 
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.dao.ClassTheme
 * @create 2021-11-10-9:11
 * @Description TODO
 */
/**
    * 主题分类表
    */
@Data
@Builder
@AllArgsConstructor
@TableName(value = "class_theme")
public class ClassTheme {
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public static final String COL_ID = "ID";

    public static final String COL_CODE = "CODE";

    public static final String COL_NAME = "NAME";

    public static final String COL_REMARK = "REMARK";

    public static final String COL_CREATE_TIME = "CREATE_TIME";
}