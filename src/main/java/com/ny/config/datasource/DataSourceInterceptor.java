package com.ny.config.datasource;

import com.ny.config.datasource.multiple.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author nieyan
 * @create 2018-11-09 11:13
 **/
@Aspect
@Component
public class DataSourceInterceptor implements Ordered {

    public int getOrder() {
        return 0;
    }

    @Around("@annotation(dataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, DataSource dataSource) {
        Object result = null;
        try {
            String name = dataSource.name();
            if (DataSource.ds1.equals(name)) {
                DataSourceContextHolder.setDataSourceType(DataSource.ds1);
            }else {
                DataSourceContextHolder.setDataSourceType(DataSource.ds2);
            }
            result = proceedingJoinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            DataSourceContextHolder.clearDataSourceType();
        }

        return result;

    }

}