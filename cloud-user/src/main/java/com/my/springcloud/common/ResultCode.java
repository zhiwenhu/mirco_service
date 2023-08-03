package com.my.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultCode
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 接口调用成功
     */
    SUCCESS(0, "Request Successful"),

    /**
     * 服务器暂不可用，建议稍候重试。建议重试次数不超过3次。
     */
    FAILURE(-1, "Request failed");

    final int code;

    final String msg;

}
