// 代码生成时间: 2025-09-30 02:47:19
package com.example.modeldeploymenttool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ModelDeploymentToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelDeploymentToolApplication.class, args);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Model Deployment Tool is up and running.");
    }

    @GetMapping("/deploy")
    public ResponseEntity<String> deployModel(@RequestParam String modelPath) {
        try {
            // Logic to deploy the model would go here
            return ResponseEntity.ok("Model deployed successfully at: " + modelPath);
        } catch (Exception e) {
            // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deploying model: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("An error occurred: " + e.getMessage());
    }
}
