package com.ny.enums;

/**
 * 用户状态，用户表status字段
 *
 *
 */
public enum StatusEnum implements BaseEnum {
    /**
     * 有效
     */
    VALID(1),
    /**
     * 无效
     */
    INVALID(0);

    private int code;

    StatusEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
