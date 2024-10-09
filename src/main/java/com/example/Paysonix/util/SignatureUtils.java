package com.example.Paysonix.util;

import com.google.common.base.Joiner;

import java.util.Map;
import java.util.TreeMap;

public class SignatureUtils {

    public static String createDataToSign(Map<String, String> params) {
        Map<String, String> sortedParams = new TreeMap<>(params);

        return Joiner.on("&")
                .withKeyValueSeparator("=")
                .join(sortedParams);
    }
}
