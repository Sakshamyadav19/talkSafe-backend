package com.yourorg.talkSafe.backend.controller;
import com.yourorg.talkSafe.backend.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ExampleService exampleService;

    @Autowired
    public HomeController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/")
    public void home() {
        exampleService.printConfig();
    }
}
