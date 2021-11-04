package cn.wintersun.baseweb.mapper;
import cn.wintersun.baseweb.dao.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.mapper.UserMapper
 * @create 2021-10-13-10:22
 * @Description TODO
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUserAccount(@Param("userAccount")String userAccount);

    User findByUserAccountAndPassword(@Param("userAccount")String userAccount,@Param("password")String password);

    IPage<User> selectUserPage(Page<User> page);
}
