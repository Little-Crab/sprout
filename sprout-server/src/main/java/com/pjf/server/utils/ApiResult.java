package com.pjf.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pjf
 * 类名：RespBean 公共返回对象
 * 创建时间: 2022/2/2 16:18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果信息
     *
     * @param message 信息
     * @return 返回
     */
    public static ApiResult success(String message) {
        return new ApiResult(200, message, null);
    }

    /**
     * 成功返回结果
     *
     * @param message 信息
     * @param obj     对象
     * @return 返回
     */
    public static ApiResult success(String message, Object obj) {
        return new ApiResult(200, message, obj);
    }

    /**
     * 返回失败
     *
     * @param message 失败信息
     * @return 返回
     */
    public static ApiResult error(String message) {
        return new ApiResult(500, message, null);
    }

    /**
     * 返回失败信息
     *
     * @param message 信息
     * @param obj     对象
     * @return 返回
     */
    public static ApiResult error(String message, Object obj) {
        return new ApiResult(500, message, obj);
    }
}
