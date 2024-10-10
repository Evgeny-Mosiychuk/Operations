package com.example.Paysonix.common;

import com.example.Paysonix.PaysonixApplication;
import com.example.Paysonix.service.SignatureService;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = PaysonixApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractTest {

    @Value("${custom.token}")
    private String token;
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
