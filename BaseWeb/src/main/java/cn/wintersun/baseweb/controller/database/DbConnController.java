package cn.wintersun.baseweb.controller.database;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.DbConn;
import cn.wintersun.baseweb.dao.DbType;
import cn.wintersun.baseweb.service.intrer.databse.DbConnService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Result<IPage<DbConn>> list(@RequestBody DbConn dbConn) {
        return dbConnService.list(dbConn);
    }

    @RequestMapping(value = "getDbTypes",method = RequestMethod.GET)
    public Result<List<DbType>> getDbTypes() {
       return dbConnService.getDbTypes();
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody DbConn dbConn) {
        return dbConnService.save(dbConn);
    }

    @RequestMapping(value = "testConn",method = RequestMethod.POST)
    public Result<Object> testConn(@RequestBody DbConn dbConn) {
        return dbConnService.testConn(dbConn);
    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Result<Object> delete(@RequestBody String ids){
        try {
            return dbConnService.delete(ids);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
