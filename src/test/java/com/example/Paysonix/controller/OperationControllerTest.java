package com.example.Paysonix.controller;

import com.example.Paysonix.common.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OperationControllerTest extends AbstractTest {
    private static final String url = "/operation/{operationId}";
    private static final String operationId = "123";

    @Test
    public void testProcessOperation_Success() throws Exception {
        mvc.perform(createPostRequest(url, operationId)
                        .param("name1", "value1")
                        .param("name2", "value2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.result[0].signature").isNotEmpty());
    }

    @Test
    public void testHandleRequestWithNoParameters() throws Exception {
        mvc.perform(createPostRequest(url, operationId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("All parameters must be present and non-empty"));
    }

    @Test
    public void testHandleRequestWithEmptyParameterValues() throws Exception {
        mvc.perform(createPostRequest(url, operationId)
                        .param("name1", "value1")
                        .param("name2", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("All parameters must be present and non-empty"));
    }
    @Test
    public void testHandleRequestWithWrongToken() throws Exception {
        mvc.perform(post(url, operationId)
                        .header("Token", "Token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("Unauthorized access: Invalid or missing token."));
    }
    @Test
    public void testHandleRequestWithoutToken() throws Exception {
        mvc.perform(post(url, operationId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("Unauthorized access: Invalid or missing token."));
    }
}
