package ru.kos.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by brjazgin on 06.04.2016.
 */
@Configuration
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
    public static PropertySourcesPlaceholderConfigurer properties() {
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

}
