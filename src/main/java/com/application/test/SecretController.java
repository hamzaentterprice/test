package com.application.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SecretController {

    @Value("${my.secret:Secret not set}")
    private String mySecret;

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "running");
        response.put("message", "Application is running with a secret");
        response.put("secretValue", mySecret);
        return response;
    }

    @GetMapping("/check-secret")
    public Map<String, String> checkSecret() {
        Map<String, String> response = new HashMap<>();
        if (mySecret == null || mySecret.equals("Secret not set")) {
            response.put("status", "not configured");
            response.put("message", "Secret is not configured");
        } else {
            response.put("status", "configured");
            response.put("message", "Secret is configured and has " + mySecret.length() + " characters");
            response.put("secretValue", mySecret);
        }
        return response;
    }

    @GetMapping("/env")
    public Map<String, String> getEnvironment() {
        Map<String, String> envVars = new HashMap<>();

        // Add all environment variables related to secrets
        System.getenv().forEach((key, value) -> {
            if (key.contains("SECRET") || key.contains("secret") ||
                    key.equals("MY_GITHUB_SECRET")) {
                envVars.put(key, value);
            }
        });

        // Add the injected secret value
        envVars.put("injectedSecret", mySecret);

        return envVars;
    }
}