package com.ny.config.datasource.multiple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nieyan
 * @create 2018-11-09 11:00
 **/
public class DataSourceContextHolder {
    public static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> context = new ThreadLocal();

    public static void setDataSourceType(String type) {
        if (type == null ) throw new NullPointerException();
        context.set(type);
    }

    public static String getDataSourceType() {
        logger.info("dataSource========>" +context.get());
        return context.get();
    }

    public static void clearDataSourceType() {
        context.remove();
    }
}
