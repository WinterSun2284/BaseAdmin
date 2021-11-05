package cn.wintersun.baseweb.dao;

import cn.wintersun.baseweb.common.RequestPage;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**  
 * 
 * 
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dao.DbConn
 * @create 2021-11-02-15:48
 * @Description TODO
 */
/**
    * 数据库连接表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "db_conn")
public class DbConn extends RequestPage {
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    /**
     * 数据库名称
     */
    @TableField(value = "DB_NAME")
    private String dbName;

    /**
     * 业务系统名称
     */
    @TableField(value = "BUSINESS_NAME")
    private String businessName;

    /**
     * 数据库账号
     */
    @TableField(value = "DB_ACCOUNT")
    private String dbAccount;

    /**
     * 数据库密码
     */
    @TableField(value = "DB_PASSWORD")
    private String dbPassword;

    /**
     * 数据库IP
     */
    @TableField(value = "DB_IP")
    private String dbIp;

    /**
     * 数据库端口
     */
    @TableField(value = "DB_PORT")
    private String dbPort;

    /**
     * 数据库服务名
     */
    @TableField(value = "SERVER_NAME")
    private String serverName;

    /**
     * 数据库类型
     */
    @TableField(value = "DB_TYPE")
    private Integer dbType;

    /**
     * 是否侦测
     */
    @TableField(value = "DETECT_ONOFF")
    private Boolean detectOnoff;

    /**
     * 最后一次侦测时间
     */
    @TableField(value = "LATEST_DETECT_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date latestDetectTime;

    /**
     * 数据库连接状态
     */
    @TableField(value = "CONN_STATUS")
    private Boolean connStatus;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "MODIFY_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;

    /**
     * 创建人信息
     */
    @TableField(value = "USER_NAME")
    private String userName;

    private transient String dbTypeName;

    private transient String ipAndPort;

    private transient String startTime;

    private transient String endTime;

    public static final String COL_ID = "ID";

    public static final String COL_DB_NAME = "DB_NAME";

    public static final String COL_BUSINESS_NAME = "BUSINESS_NAME";

    public static final String COL_DB_ACCOUNT = "DB_ACCOUNT";

    public static final String COL_DB_PASSWORD = "DB_PASSWORD";

    public static final String COL_DB_IP = "DB_IP";

    public static final String COL_DB_PORT = "DB_PORT";

    public static final String COL_SERVER_NAME = "SERVER_NAME";

    public static final String COL_DB_TYPE = "DB_TYPE";

    public static final String COL_DETECT_ONOFF = "DETECT_ONOFF";

    public static final String COL_LATEST_DETECT_TIME = "LATEST_DETECT_TIME";

    public static final String COL_CONN_STATUS = "CONN_STATUS";

    public static final String COL_CREATE_TIME = "CREATE_TIME";

    public static final String COL_MODIFY_TIME = "MODIFY_TIME";

    public static final String COL_USER_NAME = "USER_NAME";
}