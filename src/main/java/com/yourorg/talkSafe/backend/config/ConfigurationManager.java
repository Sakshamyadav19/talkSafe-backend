package com.yourorg.talkSafe.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Singleton for centralized application configuration.
 * Spring automatically creates this as a single bean.
 */
@Component
public class ConfigurationManager {

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${management.endpoints.web.exposure.include}")
    private String actuatorEndpoints;

    // Add more @Value properties as needed

    // Public getters
    public int getServerPort() {
        return serverPort;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getActuatorEndpoints() {
        return actuatorEndpoints;
    }
}