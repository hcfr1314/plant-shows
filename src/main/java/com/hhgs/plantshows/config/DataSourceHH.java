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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.hhgs.plantshows.mapper.hh"},sqlSessionTemplateRef = "hhTemplate")
public class DataSourceHH {

    @Bean("hhdatasource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "hhSqlFactory")
    @Primary
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("hhdatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/hh/*.xml"));
        return bean.getObject();
    }


    @Bean(name="hhTransManager")
    @Primary
    public DataSourceTransactionManager createDataSourceTransactionManager(@Qualifier("hhdatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("hhTemplate")
    @Primary
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("hhSqlFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

}
