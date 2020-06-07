package com.dlyong.yichu.common.api;

/**
 * 返回共通结果
 * @param <T>
 */

public class ResponseDTO<T> {
    protected Long code;
    protected String message;
    protected T data;

    protected ResponseDTO() {
    }


    protected ResponseDTO(long code, String message, T data) {
        this.code = code;
        this.message =message;
        this.data = data;
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponseDTO<T> success(T data, String message) {
        return new ResponseDTO<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> ResponseDTO<T> failed(ResultCode errorCode) {
        return new ResponseDTO<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResponseDTO<T> failed(ResultCode errorCode, String message) {
        return new ResponseDTO<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseDTO<T> failed(String message) {
        return new ResponseDTO<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseDTO<T> failed() {
        return failed(ResultCode.FAILED);
    }


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
