// 代码生成时间: 2025-09-20 22:24:30
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PerformanceTestService {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }

    @GetMapping("/load")
    public ResponseEntity<String> generateLoad() {
        // Simulate some heavy operation
        try {
            Thread.sleep(2000); // Simulate a 2-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ResponseEntity.ok("Load generated successfully");
    }

    // Exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(PerformanceTestService.class, args);
    }
}

// Global exception handler
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Global error: " + e.getMessage());
    }
}