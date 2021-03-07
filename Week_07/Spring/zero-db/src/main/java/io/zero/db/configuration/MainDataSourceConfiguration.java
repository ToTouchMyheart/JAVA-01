package io.zero.db.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = {"io.zero.db.dao", "io.zero.db.daocustomer"},
    sqlSessionTemplateRef = "ydbSqlSessionTemplate")
public class MainDataSourceConfiguration {

    @Primary
    @Bean("ydbDataSource")
    @ConfigurationProperties("spring.datasource.druid.mdb")
    public DataSource mainDBDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ydbTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("ydbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "ydbSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("ydbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setPlugins();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "ydbSqlSessionTemplate")
    public SqlSessionTemplate
        setSqlSessionTemplate(@Qualifier("ydbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
