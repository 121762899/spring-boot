package com.spring.boot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.spring.boot.mapper", sqlSessionFactoryRef = "inout2SqlSessionFactory")
@Slf4j
public class DataSource2Config {

    @Bean(name = "inout2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.inout2")
    public DataSource testDataSource() {
        log.info("datasource2");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "inout2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("inout2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // bean.setMapperLocations(
        // new
        // PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "inout2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("inout2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "inout2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("inout2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}