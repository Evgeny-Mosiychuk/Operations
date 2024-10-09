package com.example.Paysonix.controller;

import com.example.Paysonix.dto.ResponseDTO;
import com.example.Paysonix.dto.ResultDTO;
import com.example.Paysonix.service.SignatureService;
import com.example.Paysonix.validation.annotation.NonEmptyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/operation")
public class OperationController {
//    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private SignatureService signatureService;

    @PostMapping("/{operationId}")
    public ResponseEntity<ResponseDTO> handleRequest(
            @PathVariable String operationId,
            @Valid @NonEmptyMap @RequestParam Map<String, String> params) {

        String signature = signatureService.generateSignature(params);

        ResponseDTO responseDTO = new ResponseDTO("success", List.of(new ResultDTO(signature)));

        return ResponseEntity.ok(responseDTO);
    }

}

