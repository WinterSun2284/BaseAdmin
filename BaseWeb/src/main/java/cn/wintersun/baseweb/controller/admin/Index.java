package cn.wintersun.baseweb.controller.admin;

import cn.wintersun.basecommon.common.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.controller.admin.Index
 * @create 2021-10-13-14:11
 * @Description TODO
 */
@RestController
@PreAuthorize("hasAuthority('1')")
public class Index {
    @RequestMapping("admin")
    public Result<Object> index(){
        return Result.ok("首页请求到数据");
    }
}
