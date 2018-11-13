package com.ny.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 *
 */
final class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateUtil() {
    }

    /**
     * 获取当前日期与时间
     */
    static String getCurrentDateTime() {
        synchronized (SIMPLE_DATE_FORMAT) {
            return SIMPLE_DATE_FORMAT.format(new Date());
        }
    }
}
