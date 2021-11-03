package cn.wintersun.basebackend.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dto.User
 * @create 2021-10-13-10:15
 * @Description TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Modules {
    private Integer id;

    private String moduleName;

    private String moduleDesc;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Modules) {
            Modules modules = (Modules)obj;
            //根据需要判断
            return (this.id.equals(modules.getId())&&this.moduleName.equals(modules.getModuleName()));
        }
        return super.equals(obj);
    }

    /**
     * 用于去重
     * @return int
     **/
    @Override
    public int hashCode() {
        //取判断的参数hash值
        String in = this.id + this.moduleName;
        return in.hashCode();
    }

}
