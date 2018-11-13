package com.ny.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ny.config.datasource.multiple.MultipleDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nieyan
 * @create 2018-11-09 10:51
 **/
@Configuration
@PropertySource(value = {"classpath:application-dev.yml"})
public class MyBatisPlusConfiguration implements EnvironmentAware {

    private Environment environment;

    @Bean(name = "ds1")
    public DataSource ds1() {
        return DruidDataSourceBuilder.create().build(environment,"spring.datasource.druid.ds1.");
    }

    @Bean(name = "ds2")
    public DataSource ds2() {
        return DruidDataSourceBuilder.create().build(environment,"spring.datasource.druid.ds2.");
    }
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public MultipleDataSource multipleDataSource(@Qualifier("ds1") DataSource ds1, @Qualifier("ds2") DataSource ds2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("ds1", ds1);
        targetDataSources.put("ds2", ds2);

        MultipleDataSource dataSource = new MultipleDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(ds1);// 默认的datasource设置为ds1

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(MultipleDataSource ds) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("com.wokeji.entity");// 指定基包
        fb.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));//

        return fb.getObject();
    }


    @Bean
    public DataSourceTransactionManager transactionManager(MultipleDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    /*
        https://www.cnblogs.com/hyl8218/p/5927337.html
    */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
