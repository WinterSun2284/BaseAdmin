package cn.wintersun.basebackend.service.intrer.databse;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.DbConn;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.intrer.databse.DbConnService
 * @create 2021-11-03-10:29
 * @Description TODO
 */
public interface DbConnService {
    Result<IPage<DbConn>> list(DbConn dbConn);
}
