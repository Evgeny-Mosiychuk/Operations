package com.example.Paysonix.util;

import java.util.Map;

public class SignatureUtils {

    public static String createDataToSign(Map<String, String> params) {
        StringBuilder dataToSign = new StringBuilder();
        params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> dataToSign.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));

        if (dataToSign.length() > 0)
            dataToSign.setLength(dataToSign.length() - 1);
        return dataToSign.toString();
    }
}
