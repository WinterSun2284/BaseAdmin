package cn.wintersun.baseweb.mapper;

import cn.wintersun.baseweb.dao.Modules;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.mapper.ModulesMapper
 * @create 2021-10-22-17:53
 * @Description TODO
 */
@Mapper
public interface ModulesMapper extends BaseMapper<Modules> {
    List<Modules> findByRoleId(@Param("roleId") Integer roleId);
}