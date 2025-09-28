// 代码生成时间: 2025-09-29 00:03:16
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/smart_contract")
public class SmartContractService {

    private final Map<String, String> contractStorage = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SmartContractService.class, args);
    }

    // REST API to deploy a new smart contract
    @GetMapping("/deploy")
    public Map<String, String> deployContract(String contractName) {
        if (contractName == null || contractName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract name is required");
        }
        if (contractStorage.containsKey(contractName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Contract with the same name already exists");
        }
        contractStorage.put(contractName, "Contract deployed");
        return contractStorage;
    }

    // REST API to execute a smart contract
    @GetMapping("/execute")
    public Map<String, String> executeContract(String contractName) {
        if (!contractStorage.containsKey(contractName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contract not found");
        }
        // Simulate contract execution logic
        String result = contractStorage.get(contractName) + " - Execution successful";
        contractStorage.put(contractName, result);
        return contractStorage;
    }

    // Exception handling method
    @ExceptionHandler
    public Map<String, String> handleException(Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return errorResponse;
    }
}
