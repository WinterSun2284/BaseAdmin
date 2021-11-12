package cn.wintersun.baseweb.mapper;

import cn.wintersun.baseweb.dao.ClassTheme;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.mapper.ClassThemeMapper
 * @create 2021-11-10-9:11
 * @Description TODO
 */
@Mapper
public interface ClassThemeMapper extends BaseMapper<ClassTheme> {

    /**
     * 查询等级为大类的，也就是字符串长度为2的
     *
     * @return java.util.List<com.deepdt.dcgstar.dcgstarcommon.model.ClassTheme>
     **/
    List<ClassTheme> findLarge(@Param("name")String name);

    List<ClassTheme> findMedium(@Param("code")String code);

    List<ClassTheme> findSmall(@Param("code")String code);
}