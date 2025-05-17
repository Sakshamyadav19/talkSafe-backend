package com.yourorg.talkSafe.backend.service;

import com.yourorg.talkSafe.backend.config.ConfigurationManager;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final ConfigurationManager config;

    public ExampleService(ConfigurationManager config) {
        this.config = config;
    }

    public void printConfig() {
        System.out.println("Server running on port: " + config.getServerPort());
    }
}