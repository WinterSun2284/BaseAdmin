package cn.wintersun.baseweb.mapper;

import cn.wintersun.baseweb.dao.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**  
 * 
 * 
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.mapper.RolesMapper
 * @create 2021-10-14-9:52
 * @Description TODO
 */
@Mapper
public interface RolesMapper extends BaseMapper<Roles> {

    List<Roles> findByUserAccount(@Param("userAccount")String userAccount);

}