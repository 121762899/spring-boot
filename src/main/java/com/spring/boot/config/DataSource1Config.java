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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.spring.boot.mapper", sqlSessionFactoryRef = "inout1SqlSessionFactory")
@Slf4j
public class DataSource1Config {

  /**
   * 
   * @author: zhangxuesong 
   * @Description:
   * @date:   2019年5月10日 下午2:32:27   
   * @Param 
   *
   */
    @Bean(name = "inout1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.inout1")
    @Primary
    public DataSource testDataSource() {
        log.info("datasource1");
        return DataSourceBuilder.create().build();
    }

    /**
     * 
     * @author: zhangxuesong 
     * @Description:
     * @date:   2019年5月10日 下午2:32:39   
     * @Param 
     *
     */
    @Bean(name = "inout1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("inout1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // bean.setMapperLocations(
        // new
        // PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject();
    }

    /**
     * 
     * @author: zhangxuesong 
     * @Description:
     * @date:   2019年5月10日 下午2:32:45   
     * @Param 
     *
     */
    @Bean(name = "inout1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("inout1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "inout1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("inout1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
