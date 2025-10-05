// 代码生成时间: 2025-10-06 03:35:19
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
public class RiskControlService {

    public static void main(String[] args) {
        SpringApplication.run(RiskControlService.class, args);
    }
}

@RestController
class RiskControlController {

    @GetMapping("/risk")
    public ResponseEntity<String> getRiskLevel(@RequestParam String riskParameter) {
        try {
            if ("HIGH".equals(riskParameter)) {
                return ResponseEntity.ok("High risk identified");
            } else if ("MEDIUM".equals(riskParameter)) {
                return ResponseEntity.ok("Medium risk identified");
            } else if ("LOW".equals(riskParameter)) {
                return ResponseEntity.ok("Low risk identified");
            } else {
                throw new IllegalArgumentException("Invalid risk parameter");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
