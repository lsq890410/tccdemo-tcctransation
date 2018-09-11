package org.tccdemo.tcctransation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
    @Bean(name = "busi")
    @Qualifier("busi")
    @ConfigurationProperties(prefix="spring.datasource.busi")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "busiTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("busi") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}