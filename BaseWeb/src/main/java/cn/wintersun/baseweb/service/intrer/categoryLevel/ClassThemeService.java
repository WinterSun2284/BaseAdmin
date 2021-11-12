package cn.wintersun.baseweb.service.intrer.categoryLevel;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.ClassTheme;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.service.intrer.categoryLevel.ClassThemeService
 * @create 2021-11-10-10:23
 * @Description TODO
 */
public interface ClassThemeService {
    Result<List<ClassTheme>> getLarge();
    Result<List<ClassTheme>> getMedium(String code);

    Result<List<ClassTheme>> getSmall(String code);
}
