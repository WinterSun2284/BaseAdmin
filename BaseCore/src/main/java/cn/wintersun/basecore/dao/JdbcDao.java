package cn.wintersun.basecore.dao;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.common.ResultCode;
import cn.wintersun.basecommon.dto.JdbcConnInfo;
import cn.wintersun.basecore.connection.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basecore.dao.JdbcDao
 * @create 2021-11-03-14:25
 * @Description TODO
 */
public class JdbcDao {
    public static Result<Boolean> testConn(JdbcConnInfo jdbcConnInfo) {

        Result<Connection> connInfo = JdbcConnection.getConnection(jdbcConnInfo);
        if (connInfo.getCode().equals(ResultCode.ERROR_CODE)) {
            return Result.error(connInfo.getMsg(),false);
        }

        Connection conn = connInfo.getData();

        Statement stat;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            return Result.error(e.getMessage(),false);
        }

        String sql = "select 1";

        try {
            ResultSet resultSet = stat.executeQuery(sql);
        } catch (SQLException e) {
            return Result.error(e.getMessage(),false);
        }


        return Result.ok(true);
    }
}
