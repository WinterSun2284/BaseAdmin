package cn.wintersun.basebackend.controller.database;

import cn.wintersun.basebackend.common.Result;
import cn.wintersun.basebackend.dao.DbConn;
import cn.wintersun.basebackend.service.intrer.databse.DbConnService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.controller.database.DbConnController
 * @create 2021-11-03-10:28
 * @Description TODO
 */
@RestController
@RequestMapping("/admin/database/dbConn")
public class DbConnController {

    @Autowired private DbConnService dbConnService;

    @RequestMapping("list")
    public Result<IPage<DbConn>> list(@RequestBody DbConn dbConn) {
        return dbConnService.list(dbConn);
    }
}
