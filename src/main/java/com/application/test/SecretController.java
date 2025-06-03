package com.application.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Value("${my.secret:Secret not set}")
    private String mySecret;

    @GetMapping("/")
    public String home() {
        return "Application is running with secret: " + mySecret;
    }

    @GetMapping("/check-secret")
    public String checkSecret() {
        if (mySecret.equals("Secret not set")) {
            return "Secret is not configured";
        }
        return "Secret is configured and has " + mySecret.length() + " characters";
    }

    // This endpoint will directly display the secret value
    @GetMapping("/display-secret")
    public String displaySecret() {
        return "The complete secret value is: " + mySecret;
    }
}