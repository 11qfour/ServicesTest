package com.example.service_test_task.Client;

import com.example.service_test_task.Client.Component.CompanyClientFallback;
import com.example.service_test_task.DTO.CompanyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "company-service", url = "${company.service.url}", fallback = CompanyClientFallback.class)
public interface CompanyClient {
    @GetMapping("/companies/{companyId}")
    CompanyResponseDto getCompanyById(@PathVariable Long companyId);
}