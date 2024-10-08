package com.example.Paysonix.service;

import com.example.Paysonix.common.AbstractTest;
import com.example.Paysonix.exception.HmacGenerationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SignatureServiceImplTest extends AbstractTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "secretKey", "my_secret_key");
        ReflectionTestUtils.setField(service, "algorithm", "HmacSHA256");
    }

    @Test
    void testGenerateSignatureWithValidParams() {
        Map<String, String> params = Map.of("name1", "value1", "name2", "value2");

        String result = service.generateSignature(params);

        assertThat(result).isNotEmpty();
    }

    @Test
    void testGenerateSignatureThrowsExceptionForInvalidAlgorithm() {
        ReflectionTestUtils.setField(service, "algorithm", "InvalidAlgorithm");

        Map<String, String> params = Map.of("name1", "value1");

        assertThrows(HmacGenerationException.class, () -> service.generateSignature(params));
    }

    @Test
    void tesInvalidKey() {
        ReflectionTestUtils.setField(service, "secretKey", "");
        Map<String, String> params = Map.of("name1", "value1");

        assertThrows(IllegalArgumentException.class, () -> service.generateSignature(params));
    }

    @Test
    void testBytesToHexConversion() {
        byte[] hmac = new byte[]{0x0F, (byte) 0xA8, (byte) 0xCC};
        String expectedHex = "0fa8cc";

        String result = ReflectionTestUtils.invokeMethod(service, "bytesToHex", hmac);

        assertThat(result).isEqualTo(expectedHex);
    }
}
