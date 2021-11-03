package cn.wintersun.basebackend.controller.admin;

import cn.wintersun.basebackend.common.Result;
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
public class Index {
    @RequestMapping("admin")
    public Result<Object> index(){
        return Result.ok("首页请求到数据");
    }
}
