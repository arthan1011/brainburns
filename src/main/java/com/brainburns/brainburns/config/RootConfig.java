package com.brainburns.brainburns.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

/**
 * Created by Arthan on 24.12.2016. Project: brainburns
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
@MapperScan(basePackages = "com.brainburns.brainburns.domain.mapper")
@ComponentScan(basePackages = "com.brainburns.brainburns")
public class RootConfig {

    private DataSource dataSource;

    private final Environment env;

    @Autowired
    public RootConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        if (this.dataSource == null) {
            final String dbUrlTemplate = "jdbc:postgresql://{0}:{1}{2}";
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");

            String username;
            String password;
            String url;

            String databaseUrl = System.getenv("DATABASE_URL");
            if (databaseUrl != null) {
                URI dbUri = new URI(databaseUrl);
                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
                url = MessageFormat.format(
                        dbUrlTemplate,
                        dbUri.getHost(),
                        Integer.toString(dbUri.getPort()),
                        dbUri.getPath()
                );
            } else {
                username = env.getProperty("db.username");
                password = env.getProperty("db.password");
                url = env.getProperty("db.url");
            }

            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            this.dataSource = dataSource;
        }

        return this.dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws URISyntaxException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws URISyntaxException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("com.brainburns.brainburns.domain.model");
        return sessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
    }
}
