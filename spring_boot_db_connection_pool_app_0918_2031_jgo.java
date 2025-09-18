// 代码生成时间: 2025-09-18 20:31:52
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@EntityScan(basePackages = "com.example.demo.entity")
@EnableTransactionManagement
public class SpringBootDbConnectionPoolApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDbConnectionPoolApp.class, args);
    }
}

// REST API Controller for demonstrating database connection pool management.
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.MyService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final MyService myService;

    @Autowired
    public ApiController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/pool")
    public ResponseEntity<String> getPoolStatus() {
        return ResponseEntity.ok(myService.checkPoolStatus());
    }
}

// Service class for demonstrating database connection pool management.
package com.example.demo.service;

import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class MyService {

    private final DataSource dataSource;

    public MyService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String checkPoolStatus() {
        try (Connection connection = dataSource.getConnection()) {
            // Perform operations on the connection to check the pool status.
            return "Database connection pool is active.";
        } catch (SQLException e) {
            return "Database connection pool error: " + e.getMessage();
        }
    }
}

// Exception handling configuration.
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ExceptionHandlerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ResponseEntityExceptionHandler responseEntityExceptionHandler() {
        return new ResponseEntityExceptionHandler();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new CustomExceptionResolver());
    }
}

// Custom exception resolver for global exception handling.
package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class CustomExceptionResolver {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}