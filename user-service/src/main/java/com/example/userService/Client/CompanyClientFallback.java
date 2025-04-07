package com.example.userService.Client;

import com.example.service_test_task.DTO.CompanyResponseDto;
import com.example.service_test_task.DTO.CompanyResponseFromUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompanyClientFallback implements CompanyClient {
    @Override
    public CompanyResponseFromUserDto getCompanyById(Long companyId) {
        log.warn("Fallback: Returning empty company {}", companyId);
        return CompanyResponseFromUserDto.builder().build(); //not null
    }
}
