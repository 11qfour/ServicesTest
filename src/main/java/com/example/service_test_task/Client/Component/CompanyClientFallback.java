package com.example.service_test_task.Client.Component;

import com.example.service_test_task.Client.CompanyClient;
import com.example.service_test_task.DTO.CompanyResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompanyClientFallback implements CompanyClient {
    @Override
    public CompanyResponseDto getCompanyById(Long companyId) {
        log.warn("Fallback: Returning empty company {}", companyId);
        return CompanyResponseDto.builder().build(); //not null
    }
}
