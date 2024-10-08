package com.example.Paysonix.utils;

import com.example.Paysonix.util.SignatureUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SignatureUtilsTest {
    @Test
    void testCreateDataToSignWithValidParams() {
        Map<String, String> params = Map.of(
                "name1", "value1",
                "name2", "value2",
                "name3", "value3"
        );

        String result = SignatureUtils.createDataToSign(params);

        assertThat(result).isEqualTo("name1=value1&name2=value2&name3=value3");
    }

    @Test
    void testCreateDataToSignWithEmptyParams() {
        Map<String, String> params = Map.of();

        String result = SignatureUtils.createDataToSign(params);

        assertThat(result).isEmpty();
    }

    @Test
    void testCreateDataToSignWithSingleParam() {
        Map<String, String> params = Map.of("name1", "value1");

        String result = SignatureUtils.createDataToSign(params);

        assertThat(result).isEqualTo("name1=value1");
    }

    @Test
    void testCreateDataToSignWithSpecialCharacters() {
        Map<String, String> params = Map.of(
                "name1", "value1",
                "special&name2", "special=value2"
        );

        String result = SignatureUtils.createDataToSign(params);

        assertThat(result).isEqualTo("name1=value1&special&name2=special=value2");
    }
}
