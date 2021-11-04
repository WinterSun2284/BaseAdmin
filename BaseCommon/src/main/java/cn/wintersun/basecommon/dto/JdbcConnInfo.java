package cn.wintersun.basecommon.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basecommon.dto.JdbcConnInfo
 * @create 2021-11-03-14:20
 * @Description TODO
 */
@Data
@Builder
public class JdbcConnInfo {

    private String ip;
    private String port;
    private String serverName;
    private String driverType;
    private String userName;
    private String passWord;
}
