package cn.wintersun.basebackend.common;

public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),

    ERROR(500,"失败"),

    BAD_REQUEST(400, "参数或者语法不对"),

    UNAUTHORIZED(401, "认证失败"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "请求的资源不存在"),

    REFRESH_TOKEN_EXPIRED(401, "refreshToken过期"),

    VALID_TOKEN(407, "无效的token"),

    SERVER_ERROR(500, "服务器内部错误"),

    JDBC_FAIL(6001, "连接失败"),

    CHANGE_PASSWORD(5001,"修改过密码，请重新登录"),
    NO_ACCESS(5002,"您没有权限访问此页面，请联系管理员！")
;
    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
