package com.example.Paysonix.dto;

public class ResultDTO {
    private String signature;

    public ResultDTO(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
