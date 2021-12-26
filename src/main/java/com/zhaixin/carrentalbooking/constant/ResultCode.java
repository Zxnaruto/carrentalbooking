package com.zhaixin.carrentalbooking.constant;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "没有相关权限"),
    PASSWORD_ERROR(600,"密码输入错误"),
    USER_NOT_EXIST(601,"用户不存在"),
    PHONE_EXIST(602,"手机号存在"),
    EMAIL_EXIST(603,"邮箱存在"),
    CAR_EXIST(604, "汽车存在"),
    CAR_TYPE_NOT_EXIST(605, "汽车类型不存在"),
    ORDER_NOT_EXIST(606,"订单不存在"),
    ORDER_EXPIRED_CANCEL(607, "订单过期已取消，请重新下单");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
