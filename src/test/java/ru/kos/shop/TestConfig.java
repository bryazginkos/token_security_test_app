package ru.kos.shop;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by brjazgin on 06.04.2016.
 */
@ComponentScan(basePackages = {"ru.kos.shop"})
public class TestConfig {

    private static final String TEST_DB = "test_db";

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setName(TEST_DB)
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        final Properties properties = new Properties();
        properties.setProperty("authentication.password.salt", "$2a$11$qN73UZIw0Q6OHP.JriFoLO");
        properties.setProperty("authentication.token.secret", "secret");
        properties.setProperty("authentication.token.issuer", "issuer");
        properties.setProperty("authentication.token.subject", "subject");
        properties.setProperty("authentication.token.lifetime.ms", "10000");
        pspc.setProperties(properties);
        return pspc;
    }

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("session", simpleThreadScope());
        customScopeConfigurer.setScopes(scopes);
        return customScopeConfigurer;
    }

    @Bean
    public SimpleThreadScope simpleThreadScope() {
        return new SimpleThreadScope();
    }

}
