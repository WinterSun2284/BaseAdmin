package cn.wintersun.basecommon.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basecommon.common.Result
 * @create 2021-10-13-16:16
 * @Description TODO
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 783015033603078674L;

    private Integer code;
    private String msg;
    private T data;

    public Result(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCode resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }


    public static Result<Object> ok() {
        return ok(new HashMap<>(1));
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result<>(200, msg, data);
    }


    public static <T> Result<T> failure(ResultCode code) {
        return failure(code, null);
    }

    public static <T> Result<T> failure(ResultCode code, T o) {
        return new Result<>(code, o);
    }

    public static <T> Result<T> failure(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(500, msg, data);
    }

}
