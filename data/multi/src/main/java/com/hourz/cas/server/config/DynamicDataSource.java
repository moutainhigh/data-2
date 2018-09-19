package com.hourz.cas.server.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p> 动态数据源:AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource) </p>
 * @Author hourz
 * @since 2018-06-23
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
