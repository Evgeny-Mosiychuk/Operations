package com.example.Paysonix.dto;

import java.util.List;

public class ResponseDTO {
    private String status;
    private List<ResultDTO> result;

    public ResponseDTO(String status, List<ResultDTO> result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }
}
