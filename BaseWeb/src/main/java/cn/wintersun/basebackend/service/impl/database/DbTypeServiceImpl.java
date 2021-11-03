package cn.wintersun.basebackend.service.impl.database;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.DbType;
import cn.wintersun.basebackend.mapper.DbTypeMapper;
import cn.wintersun.basebackend.service.intrer.databse.DbTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.database.DbTypeServiceImpl
 * @create 2021-11-02-14:43
 * @Description TODO
 */
@Service
public class DbTypeServiceImpl implements DbTypeService {

    @Autowired
    private DbTypeMapper dbTypeMapper;

    @Override
    public Result<IPage<DbType>> list(DbType dbType) {
        QueryWrapper<DbType> queryWrapper = Wrappers.query();

        if (StringUtils.isNotEmpty(dbType.getDbTypeName())){
            queryWrapper.like(DbType.COL_DB_TYPE_NAME, dbType.getDbTypeName());
        }

        Page<DbType> page = new Page<>(dbType.getCurrent(), dbType.getPageSize());
        IPage<DbType> iPage = dbTypeMapper.selectPage(page, queryWrapper);

        return Result.ok(iPage);
    }
}
