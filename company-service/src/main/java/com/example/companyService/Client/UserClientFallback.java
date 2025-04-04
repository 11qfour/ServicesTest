package com.example.companyService.Client;


import com.example.service_test_task.DTO.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserClientFallback implements UserClient {

    @Override
    public List<UserResponseFromCompanyDto> getUsersByCompany(Long companyId) {
        log.warn("Fallback: Returning empty user list for company {}", companyId);
        return Collections.emptyList();
    }

    @Override
    public void deleteUsersByCompany(Long companyId) {
        log.warn("Fallback: Failed to delete users for company {}", companyId);
    }
}
