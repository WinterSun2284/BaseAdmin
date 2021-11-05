package cn.wintersun.baseweb.service.impl.database;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.basecommon.common.ResultCode;
import cn.wintersun.basecommon.utils.Encryptor;
import cn.wintersun.baseweb.dao.DbConn;
import cn.wintersun.baseweb.dao.DbType;
import cn.wintersun.baseweb.dao.Roles;
import cn.wintersun.baseweb.dao.RolesModules;
import cn.wintersun.baseweb.mapper.DbConnMapper;
import cn.wintersun.baseweb.mapper.DbTypeMapper;
import cn.wintersun.baseweb.service.intrer.databse.DbConnService;
import cn.wintersun.baseweb.utils.JdbcConnectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.service.impl.database.DbConnServiceImpl
 * @create 2021-11-03-10:29
 * @Description TODO
 */
@Service
public class DbConnServiceImpl implements DbConnService {

    public static final Logger LOGGER = LoggerFactory.getLogger(DbConnServiceImpl.class);

    @Autowired
    private DbConnMapper dbConnMapper;

    @Autowired
    private DbTypeMapper dbTypeMapper;

    @Autowired
    private JdbcConnectionUtil jdbcConnectionUtil;

    @Override
    public Result<IPage<DbConn>> list(DbConn dbConn) {
        QueryWrapper<DbConn> query = Wrappers.query();
        if(dbConn.getDbType()!=null){
            query.eq(DbConn.COL_DB_TYPE, dbConn.getDbType());
        }

        if (StringUtils.isNotEmpty(dbConn.getDbName())){
            query.like(DbConn.COL_DB_NAME, dbConn.getDbName());
        }

        if (StringUtils.isNotEmpty(dbConn.getBusinessName())){
            query.like(DbConn.COL_BUSINESS_NAME, dbConn.getBusinessName());
        }

        if (StringUtils.isNotEmpty(dbConn.getDbIp())){
            query.like(DbConn.COL_DB_IP, dbConn.getDbIp());
        }

        if (StringUtils.isNotEmpty(dbConn.getDbPort())){
            query.eq(DbConn.COL_DB_PORT, dbConn.getDbPort());
        }

        if (StringUtils.isNotEmpty(dbConn.getServerName())){
            query.like(DbConn.COL_SERVER_NAME, dbConn.getServerName());
        }

        if (dbConn.getDetectOnoff()!=null){
            query.eq(DbConn.COL_DETECT_ONOFF, dbConn.getDetectOnoff());
        }

        if (dbConn.getConnStatus()!=null){
            query.eq(DbConn.COL_CONN_STATUS, dbConn.getConnStatus());
        }
        if (StringUtils.isNotEmpty(dbConn.getStartTime())) {
            query.ge(DbConn.COL_CREATE_TIME, dbConn.getStartTime());
        }

        if (StringUtils.isNotEmpty(dbConn.getEndTime())) {
            query.le(DbConn.COL_CREATE_TIME, dbConn.getEndTime());
        }


        Page<DbConn> page = new Page<>(dbConn.getCurrent(), dbConn.getPageSize());

        Page<DbConn> dbConnPage = dbConnMapper.selectPage(page, query);

        List<DbConn> records = dbConnPage.getRecords();
        for (DbConn record : records) {
            DbType dbType = dbTypeMapper.selectById(record.getDbType());
            record.setDbTypeName(dbType.getDbTypeName());
            record.setIpAndPort(record.getDbIp() + ":" + record.getDbPort());
        }

        dbConnPage.setRecords(records);

        return Result.ok(dbConnPage);
    }

    @Override
    public Result<List<DbType>> getDbTypes() {

        QueryWrapper<DbType> query = Wrappers.query();

        List<DbType> dbTypes = dbTypeMapper.selectList(query);


        return Result.ok(dbTypes);
    }

    @Override
    public Result<Object> testConn(DbConn dbConn) {
        if (StringUtils.isEmpty(dbConn.getDbPassword())) {
            if (dbConn.getId() != null) {
                DbConn dbConn1 = dbConnMapper.selectById(dbConn.getId());
                dbConn.setDbPassword(dbConn1.getDbPassword());
            }
        } else {
            dbConn.setDbPassword(Encryptor.encoder(dbConn.getDbPassword()));
        }

        Result<Boolean> result = jdbcConnectionUtil.testConn(dbConn);

        if (result.getData()) {
            return Result.failure(200, "连接成功！");
        } else {
            return Result.error(result.getMsg());
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> save(DbConn dbConn) {
        if (dbConn.getId() != null) {
            if (StringUtils.isNotEmpty(dbConn.getDbPassword())) {
                dbConn.setDbPassword(Encryptor.encoder(dbConn.getDbPassword()));
            } else {
                DbConn dbConn1 = dbConnMapper.selectById(dbConn.getId());
                dbConn.setDbPassword(dbConn1.getDbPassword());
            }
            dbConn.setConnStatus(jdbcConnectionUtil.testConn(dbConn).getData());
            dbConnMapper.updateById(dbConn);
        } else {
            dbConn.setDbPassword(Encryptor.encoder(dbConn.getDbPassword()));
            dbConn.setConnStatus(jdbcConnectionUtil.testConn(dbConn).getData());
            dbConn.setCreateTime(new Date());
            dbConnMapper.insert(dbConn);
        }

        //调用一次测试连接
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> delete(String ids) throws Exception {

        if (StringUtils.isBlank(ids)) {
            throw new Exception("被删除的id为空");
        }

        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());


        dbConnMapper.deleteBatchIds(idList);
        return Result.ok();
    }

    @Override
    public void autoDetectDb() {
        List<DbConn> dbConns = dbConnMapper.selectList(null);
        for (DbConn dbConn : dbConns) {
            if (dbConn.getDetectOnoff()) {
                Result<Boolean> result = jdbcConnectionUtil.testConn(dbConn);
                dbConnMapper.updateConnStatusById(result.getData(), dbConn.getId());
                if (!result.getData()) {
                    //TODO 执行告警逻辑
                    LOGGER.error("数据库连接检测，数据库{}检测到连接失败", dbConn.getDbName());
                }
                LOGGER.debug("数据库连接检测，当前数据库{}，数据库连接状态{}",dbConn.getDbName(),result.getData()?"正常":"异常");
            }
        }
    }

}
