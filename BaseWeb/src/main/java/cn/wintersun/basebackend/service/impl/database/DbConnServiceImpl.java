package cn.wintersun.basebackend.service.impl.database;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.DbConn;
import cn.wintersun.basebackend.dao.DbType;
import cn.wintersun.basebackend.mapper.DbConnMapper;
import cn.wintersun.basebackend.mapper.DbTypeMapper;
import cn.wintersun.basebackend.service.intrer.databse.DbConnService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.database.DbConnServiceImpl
 * @create 2021-11-03-10:29
 * @Description TODO
 */
@Service
public class DbConnServiceImpl implements DbConnService {

    @Autowired private DbConnMapper dbConnMapper;

    @Autowired private DbTypeMapper dbTypeMapper;

    @Override
    public Result<IPage<DbConn>> list(DbConn dbConn) {
        QueryWrapper<DbConn> query = Wrappers.query();

        Page<DbConn> page = new Page<>(dbConn.getCurrent(), dbConn.getPageSize());

        Page<DbConn> dbConnPage = dbConnMapper.selectPage(page, query);

        List<DbConn> records = dbConnPage.getRecords();
        for (DbConn record : records) {
            DbType dbType = dbTypeMapper.selectById(record.getDbType());
            record.setDbTypeName(dbType.getDbTypeName());
            record.setIpAndPort(record.getDbIp()+":"+record.getDbPort());
        }

        dbConnPage.setRecords(records);

        return Result.ok(dbConnPage);
    }
}
