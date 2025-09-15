// 代码生成时间: 2025-09-16 02:06:50
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class WebContentFetcherApplication {

    private final RestTemplate restTemplate;

    public WebContentFetcherApplication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebContentFetcherApplication.class, args);
    }

    @GetMapping("/fetch")
    public String fetchContent(@RequestParam String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new CustomWebContentFetchException("Failed to fetch content: " + e.getMessage());
        }
    }

    @ExceptionHandler(CustomWebContentFetchException.class)
    public ResponseEntity<String> handleCustomWebContentFetchException(CustomWebContentFetchException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

class CustomWebContentFetchException extends RuntimeException {
    public CustomWebContentFetchException(String message) {
        super(message);
    }
}