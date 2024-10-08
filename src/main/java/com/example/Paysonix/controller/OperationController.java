package com.example.Paysonix.controller;

import com.example.Paysonix.service.SignatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/operation")
public class OperationController {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private SignatureService signatureService;

    @PostMapping("/{operationId}")
    public ResponseEntity<Map<String, Object>> handleRequest(
            @PathVariable String operationId,
            @RequestParam Map<String, String> params) {

        if (params.isEmpty() || params.values().stream().anyMatch(String::isEmpty)) {
            logger.warn("Request contains empty parameters or no parameters at all.");
            throw new IllegalArgumentException("All parameters must be present and non-empty");
        }

        String signature = signatureService.generateSignature(params);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "result", List.of(Map.of("signature", signature)))
        );
    }

}

