package com.ny.util;

/**
 *
 *
 */
public class PageUtil {
    /**
     * 每页显示数量
     */
    private static final int PAGE_NUM = 1;
    /**
     * 页码
     */
    private static final int PAGE_SIZE = 20;

    public static int getPageNum(Integer pageNum) {
        return (pageNum == null || pageNum.intValue() <= 0) ? PAGE_NUM : pageNum.intValue();
    }

    public static int getPageSize(Integer pageSize) {
        return (pageSize == null || pageSize.intValue() <= 0) ? PAGE_SIZE : pageSize.intValue();
    }
}
