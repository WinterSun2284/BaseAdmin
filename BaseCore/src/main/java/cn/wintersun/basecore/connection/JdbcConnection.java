package cn.wintersun.basecore.connection;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.dto.JdbcConnInfo;
import cn.wintersun.basecore.item.DriverType;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basecore.connection.JdbcConnection
 * @create 2021-11-03-13:40
 * @Description TODO
 */
public class JdbcConnection {
    private static final Map<String, Driver> DRIVER_MAP = new HashMap<>(10);

    public static Result<Connection> getConnection(JdbcConnInfo jdbcConnInfo) {

        try {
            String jdbcUrl;
            switch (jdbcConnInfo.getDriverType()) {
                case DriverType.MYSQL:
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        return Result.error(e.getMessage());
                    }
                    jdbcUrl = "jdbc:mysql://" + jdbcConnInfo.getIp() + ":" + jdbcConnInfo.getPort() + "/" + jdbcConnInfo.getServerName();
                    break;
                case DriverType.ORACLE:
                    return Result.error("");
                default:
                    return Result.error("");
            }

            Connection conn ;
            try {
                conn = DriverManager.getConnection(jdbcUrl, jdbcConnInfo.getUserName(), jdbcConnInfo.getPassWord());
            } catch (SQLException e) {
                return Result.error(e.getMessage());
            }
            return Result.ok(conn);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
