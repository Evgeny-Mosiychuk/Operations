package com.example.Paysonix.service;

import com.example.Paysonix.exception.HmacGenerationException;
import com.example.Paysonix.util.SignatureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class SignatureServiceImpl implements SignatureService {
    private static final Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);
    @Value("${custom.secret_key}")
    private String secretKey;
    @Value("${custom.algorithm}")
    private String algorithm;


    public String generateSignature(Map<String, String> params) {
        String dataToSign = SignatureUtils.createDataToSign(params);
        byte[] hmac = generateHmac(dataToSign);
        return bytesToHex(hmac);
    }

    private byte[] generateHmac(String dataToSign) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), algorithm);
            mac.init(secretKeySpec);
            return mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm: {}", algorithm, e);
            throw new HmacGenerationException("Failed to generate HMAC due to invalid algorithm", e);
        } catch (InvalidKeyException e) {
            logger.error("Invalid key used for HMAC generation", e);
            throw new HmacGenerationException("Failed to generate HMAC due to invalid key", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
