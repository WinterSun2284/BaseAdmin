package cn.wintersun.baseweb.utils;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.dto.JdbcConnInfo;
import cn.wintersun.basecommon.utils.Encryptor;
import cn.wintersun.basecore.dao.JdbcDao;
import cn.wintersun.baseweb.dao.DbConn;
import cn.wintersun.baseweb.dao.DbType;
import cn.wintersun.baseweb.mapper.DbTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.utils.GetJdbcConnection
 * @create 2021-11-03-14:03
 * @Description TODO
 */
@Component
public class JdbcConnectionUtil {

    @Autowired private DbTypeMapper dbTypeMapper;

    public Result<Boolean> testConn(DbConn dbConn){

        DbType dbType = dbTypeMapper.selectById(dbConn.getDbType());

        String decoder = Encryptor.decoder(dbConn.getDbPassword());
        JdbcConnInfo jdbcConnInfo = JdbcConnInfo.builder()
                .ip(dbConn.getDbIp())
                .port(dbConn.getDbPort())
                .serverName(dbConn.getServerName())
                .driverType(dbType.getDbTypeName())
                .userName(dbConn.getDbAccount())
                .passWord(decoder).build();
        return JdbcDao.testConn(jdbcConnInfo);
    }

}
