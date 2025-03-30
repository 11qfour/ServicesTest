package com.example.service_test_task.Client;

import com.example.service_test_task.Client.Component.UserClientFallback;
import com.example.service_test_task.DTO.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "user-service", url = "${user.service.url}", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/users/company/{companyId}")
    List<UserResponseDto> getUsersByCompany(@PathVariable Long companyId);
    @DeleteMapping("/users/company/{companyId}")
    void deleteUsersByCompany(@PathVariable Long companyId);
}
