package com.example.Paysonix.service;

import java.util.Map;

public interface SignatureService {
    String generateSignature(Map<String, String> params);
}
