package cn.wintersun.basebackend.dao;

import cn.wintersun.basebackend.common.RequestPage;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dao.DbType
 * @create 2021-11-02-13:49
 * @Description 数据库类型
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "db_type")
public class DbType extends RequestPage {
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    /**
     * 数据库类型名称
     */
    @TableField(value = "DB_TYPE_NAME")
    private String dbTypeName;

    /**
     * 数据库类型备注
     */
    @TableField(value = "DB_TYPE_DESC")
    private String dbTypeDesc;

    public static final String COL_ID = "ID";

    public static final String COL_DB_TYPE_NAME = "DB_TYPE_NAME";

    public static final String COL_DB_TYPE_DESC = "DB_TYPE_DESC";
}