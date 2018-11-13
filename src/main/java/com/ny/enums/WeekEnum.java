package com.ny.enums;

/**
 *
 */
public enum WeekEnum implements BaseEnum {
    // Mysql WEEKDAY() == 0 表示星期一
    星期一(0),
    // Mysql WEEKDAY() == 1 表示星期二
    星期二(1),
    // Mysql WEEKDAY() == 2 表示星期三
    星期三(2),
    // Mysql WEEKDAY() == 3 表示星期四
    星期四(3),
    // Mysql WEEKDAY() == 4 表示星期五
    星期五(4),
    // Mysql WEEKDAY() == 5 表示星期六
    星期六(5),
    // Mysql WEEKDAY() == 6 表示星期天
    星期天(6);

    private int code;

    WeekEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
