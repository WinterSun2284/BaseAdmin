package cn.wintersun.baseweb.service.impl.categoryLevel;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.ClassTheme;
import cn.wintersun.baseweb.mapper.ClassThemeMapper;
import cn.wintersun.baseweb.service.intrer.categoryLevel.ClassThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.service.impl.categoryLevel.ClassThemeServiceImpl
 * @create 2021-11-10-10:24
 * @Description TODO
 */
@Service
public class ClassThemeServiceImpl implements ClassThemeService {

    @Autowired
    private ClassThemeMapper classThemeMapper;

    @Override
    public Result<List<ClassTheme>> getLarge() {


        List<ClassTheme> classThemes = classThemeMapper.findLarge("");

        return Result.ok(classThemes);
    }

    @Override
    public Result<List<ClassTheme>> getMedium(String code) {
        List<ClassTheme> classThemes = classThemeMapper.findMedium(code);
        return Result.ok(classThemes);
    }

    @Override
    public Result<List<ClassTheme>> getSmall(String code) {
        List<ClassTheme> classThemes = classThemeMapper.findSmall(code);
        return Result.ok(classThemes);
    }
}
