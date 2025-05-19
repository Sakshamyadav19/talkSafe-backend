package com.yourorg.talkSafe.backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    Dotenv dotenv = Dotenv.load();

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(dotenv.get("DB_URL"))
                .username(dotenv.get("DB_USER"))
                .password(dotenv.get("DB_PASS"))
                .build();
    }
}