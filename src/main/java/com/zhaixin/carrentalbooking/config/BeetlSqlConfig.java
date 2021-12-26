package com.zhaixin.carrentalbooking.config;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @program: carrentalbooking
 * @description: beetlsql config
 * @author: Zhaixin
 * @created: 2021/12/24 12:00
 */
@Configuration
public class BeetlSqlConfig {
    //配置包扫描
    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.zhaixin.carrentalbooking");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }

    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Autowired DataSourceConfig mysql) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();

        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(mysql.dataSource());
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setInterceptors(new Interceptor[]{new LogDebugInterceptor()});
        factory.setNc(new UnderlinedNameConversion());//开启驼峰
        factory.setSqlLoader(new ClasspathLoader("/md"));//sql文件路径
        factory.setConfigLocation(new ClassPathResource("bin/btsql.properties"));
        return factory;
    }

    //开启事务
    @Bean(name = "txManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Autowired  DataSourceConfig mysql) {
        DataSourceTransactionManager dsm = new DataSourceTransactionManager();
        dsm.setDataSource(mysql.dataSource());
        return dsm;
    }
}