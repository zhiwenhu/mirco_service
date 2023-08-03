package com.my.springcloud.vo;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Result
 *
 */
@Data
public class ResultVo<T> implements Serializable {

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

    public ResultVo() {
    }

    private ResultVo(ResultCode resultCode) {
        this(resultCode.code, resultCode.msg);
    }

    private ResultVo(ResultCode resultCode, T data) {
        this(resultCode.code, resultCode.msg, data);
    }

    private ResultVo(int code, String msg) {
        this(code, msg, null);
    }

    private ResultVo(int code, String msg, T data) {
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
    public static <T> ResultVo<T> success() {
        return new ResultVo<>(ResultCode.SUCCESS);
    }


    /**
     * 返回成功-携带数据
     *
     * @param data 响应数据
     * @param <T>  泛型标记
     * @return 响应信息 {@code Result}
     */
    public static <T> ResultVo<T> success(@Nullable T data) {
        return new ResultVo<>(ResultCode.SUCCESS, data);
    }
}
