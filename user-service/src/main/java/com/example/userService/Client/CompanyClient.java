package com.example.userService.Client;

import com.example.service_test_task.DTO.CompanyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "company-service", path = "/api", fallback = CompanyClientFallback.class)
public interface CompanyClient {
    @GetMapping("/companies/{companyId}")
    CompanyResponseFromUserDto getCompanyById(@PathVariable Long companyId);
}