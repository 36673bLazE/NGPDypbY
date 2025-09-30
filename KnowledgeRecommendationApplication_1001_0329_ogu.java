// 代码生成时间: 2025-10-01 03:29:18
package com.example.knowledgerecommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class KnowledgeRecommendationApplication {

    private final Map<String, String> knowledgeBase = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeRecommendationApplication.class, args);
    }

    @GetMapping("/recommend/{topic}")
    public ResponseEntity<String> recommendKnowledge(@PathVariable String topic) {
        try {
            if (!knowledgeBase.containsKey(topic)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No recommendations found for topic: " + topic);
            }
            return ResponseEntity.ok(knowledgeBase.get(topic));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
