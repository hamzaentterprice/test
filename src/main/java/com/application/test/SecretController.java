package com.application.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    // This will inject the environment variable into this field
    @Value("${my.secret:Secret not set}")
    private String mySecret;

    @GetMapping("/")
    public String home() {
        // Don't do this in production - just for demo purposes
        return "Application is running with secret: " + mySecret;
    }
    
    @GetMapping("/check-secret")
    public String checkSecret() {
        if (mySecret.equals("Secret not set")) {
            return "Secret is not configured";
        }
        // In production, you'd never return the actual secret, just whether it exists
        return "Secret is configured and has " + mySecret.length() + " characters";
    }
}