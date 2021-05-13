package com.hhgs.plantshows.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.hhgs.plantshows.mapper.mysqlAvatar4"},sqlSessionTemplateRef = "mysqlAvatar4Template")
public class DataSourceMysqlAvatar4 {

    @Bean("mysqlAvatar4Datasource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "mysqlAvatar4SqlFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("mysqlAvatar4Datasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysqlAvatar4/*.xml"));
        return bean.getObject();
    }


    @Bean(name="mysqlAvatar4TransManager")
    public DataSourceTransactionManager createDataSourceTransactionManager(@Qualifier("mysqlAvatar4Datasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mysqlAvatar4Template")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("mysqlAvatar4SqlFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

}
