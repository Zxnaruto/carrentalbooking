package com.zhaixin.carrentalbooking.entity.builder;

import com.zhaixin.carrentalbooking.constant.CommonResult;

public class CommonResultBuilder<T> {
    private long code = 200;
    private String message = "success";
    private T data;

    public CommonResultBuilder<T> withCode(long code) {
        this.code = code;
        return this;
    }

    public CommonResultBuilder<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResultBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    public CommonResult<T> builder() {
        return new CommonResult<>(code, message, data);
    }
}
