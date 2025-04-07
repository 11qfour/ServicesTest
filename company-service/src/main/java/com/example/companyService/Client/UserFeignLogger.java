package com.example.companyService.Client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignLogger implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserFeignLogger.class);

    @Override
    public void apply(RequestTemplate template) {
        logger.info("Feign request: {} {}", template.method(), template.url());
    }
}
