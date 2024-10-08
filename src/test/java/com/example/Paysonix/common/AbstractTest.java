package com.example.Paysonix.common;

import com.example.Paysonix.PaysonixApplication;
import com.example.Paysonix.service.SignatureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = PaysonixApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractTest {

    @Value("${custom.token}")
    private String token;
//    @Value("${custom.secret_key}")
//    private String secretKey;
//    @Value("${custom.algorithm}")
//    private String algorithm;
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected SignatureService service;

    protected MockHttpServletRequestBuilder createPostRequest(String url, String operationId) {
        return post(url, operationId)
                .header("Token", token)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
