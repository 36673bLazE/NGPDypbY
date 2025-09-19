// 代码生成时间: 2025-09-20 01:03:17
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RestController
public class SearchOptimizationService {

    // 模拟数据库数据
    private static final List<String> DATABASE = Collections.unmodifiableList(List.of("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew", "Kiwi"));

    @GetMapping("/search")
    public ResponseEntity<List<String>> search(@RequestParam String query) {
        if (query == null || query.isEmpty()) {
            return ResponseEntity
                .badRequest()
                .body(List.of());
        }

        List<String> results = DATABASE.stream()
            .filter(item -> item.toLowerCase().contains(query.toLowerCase()))
            .toList();

        return ResponseEntity.ok(results);
    }

    // 异常处理方法
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchOptimizationService.class, args);
    }
}