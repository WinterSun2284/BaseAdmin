package cn.wintersun.baseweb.service.intrer.databse;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.DbConn;
import cn.wintersun.baseweb.dao.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.intrer.databse.DbConnService
 * @create 2021-11-03-10:29
 * @Description TODO
 */
public interface DbConnService {
    Result<IPage<DbConn>> list(DbConn dbConn);

    Result<List<DbType>> getDbTypes();

    Result<Object> testConn(DbConn dbConn);

    Result<Object> save(DbConn dbConn);

    Result<Object> delete(String ids) throws Exception;
}
