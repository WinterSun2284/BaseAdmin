package cn.wintersun.baseweb.controller.database;


import cn.wintersun.basecommon.common.Result;
import cn.wintersun.baseweb.dao.DbType;
import cn.wintersun.baseweb.service.intrer.databse.DbTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.controller.database.DbTypeController
 * @create 2021-11-02-14:41
 * @Description TODO
 */
@RestController
@RequestMapping("/admin/database/dbType")
@PreAuthorize("hasAuthority('4')")
public class DbTypeController {

    @Autowired private DbTypeService dbTypeService;

    @RequestMapping("list")
    public Result<IPage<DbType>> list(@RequestBody DbType dbType){
        return dbTypeService.list(dbType);
    }

}
