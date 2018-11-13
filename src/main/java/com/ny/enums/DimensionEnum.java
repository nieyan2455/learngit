package com.ny.enums;

/**
 * 时间维度
 *
 */
public enum DimensionEnum implements BaseEnum {
    // date 维度
    DATE(1),
    // week 维度
    WEEK(2),
    // month 维度
    MONTH(3),
    // quarter 维度
    QUARTER(4),
    // year 维度
    YEAR(5);

    private int code;
    DimensionEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
