package cn.wintersun.baseweb.controller.categoryLevel;

import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.ClassTheme;
import cn.wintersun.baseweb.service.intrer.categoryLevel.ClassThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.controller.categoryLevel.ClassThemController
 * @create 2021-11-10-9:14
 * @Description TODO
 */
@RestController
@PreAuthorize("hasAuthority('5')")
@RequestMapping("/admin/categoryLevel/classThem")
public class ClassThemController {

    @Autowired
    private ClassThemeService classThemeService;

    @RequestMapping(value = "getLarge",method = RequestMethod.POST)
    public Result<List<ClassTheme>> getLarge(){
        return classThemeService.getLarge();
    }

    @RequestMapping(value = "getMedium",method = RequestMethod.POST)
    public Result<List<ClassTheme>> getMedium(@RequestBody String code){
        return classThemeService.getMedium(code);
    }

    @RequestMapping(value = "getSmall",method = RequestMethod.POST)
    public Result<List<ClassTheme>> getSmall(@RequestBody String code){
        return classThemeService.getSmall(code);
    }

}
