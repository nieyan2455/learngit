package com.ny.config.datasource.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author nieyan
 * @create 2018-11-09 10:59
 **/
public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
