package cn.wintersun.baseweb.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.common.RequestParam
 * @create 2021-10-15-11:34
 * @Description TODO
 */
@NoArgsConstructor
@Data
public class RequestPage {

    private transient Integer current;
    private transient Integer pageSize;
    private transient String sortField;
    private transient String sortOrder;

}
