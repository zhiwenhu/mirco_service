package com.my.springcloud.common;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Result
 *
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3826782301005013894L;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public Result() {
    }

    private Result(ResultCode resultCode) {
        this(resultCode.code, resultCode.msg);
    }

    private Result(ResultCode resultCode, T data) {
        this(resultCode.code, resultCode.msg, data);
    }

    private Result(int code, String msg) {
        this(code, msg, null);
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 返回成功
     *
     * @param <T> 泛型标记
     * @return 响应信息 {@code Result}
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }


    /**
     * 返回成功-携带数据
     *
     * @param data 响应数据
     * @param <T>  泛型标记
     * @return 响应信息 {@code Result}
     */
    public static <T> Result<T> success(@Nullable T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }
}
