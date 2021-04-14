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
@MapperScan(basePackages = {"com.hhgs.plantshows.mapper.avatar"},sqlSessionTemplateRef = "avatarTemplate")
public class DataSourceAvatar {

    @Bean("avatarDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "avatarSqlFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("avatarDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/avatar/*.xml"));
        return bean.getObject();
    }


    @Bean(name="avatarTransManager")
    public DataSourceTransactionManager createDataSourceTransactionManager(@Qualifier("avatarDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("avatarTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("avatarSqlFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

}
