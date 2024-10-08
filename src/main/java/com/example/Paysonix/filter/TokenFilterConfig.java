package com.example.Paysonix.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenFilterConfig {
    @Value("${custom.token}")
    private String token;

    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter(token));
        registrationBean.addUrlPatterns("/operation/*");
        return registrationBean;
    }
}
