package cn.wintersun.basebackend.service.intrer.databse;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.intrer.databse.DbTypeService
 * @create 2021-11-02-14:42
 * @Description TODO
 */
public interface DbTypeService {
    Result<IPage<DbType>> list(DbType dbType);
}
