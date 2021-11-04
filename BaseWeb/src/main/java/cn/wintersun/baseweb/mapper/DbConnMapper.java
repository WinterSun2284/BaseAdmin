package cn.wintersun.baseweb.mapper;

import org.apache.ibatis.annotations.Param;

import cn.wintersun.baseweb.dao.DbConn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.mapper.DbConnMapper
 * @create 2021-11-02-15:48
 * @Description TODO
 */
@Mapper
public interface DbConnMapper extends BaseMapper<DbConn> {

    /**
     * 更新数据库状态
     *
     * @param connStatus 状态
     * @param id         数据库id
     * @return int 成功条数
     **/
    int updateConnStatusById(@Param("connStatus") Boolean connStatus, @Param("id") Integer id);

}