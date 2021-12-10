package com.zlyang.mall.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author: zlyang
 * @date: 2021-11-28 18:50
 * @description: 使用Seata对数据源进行代理
 */
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 构造datasource代理对象，替换原来的datasource
     * 直接让DataSourceProxy bean返回的是一个被代理过的名为dataSource的bean,并且我们加入了@Primary,导致mp优先使用我们配置的数据源,
     * 这样就解决了mp因为seata代理了数据源跟创建了新的sqlSessionFactory,导致mp的插件,组件失效的bug
     * @param druidDataSource
     * @return
     */
    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }
}
