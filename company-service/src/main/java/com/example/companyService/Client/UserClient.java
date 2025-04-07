package com.example.companyService.Client;


import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.DTO.UserResponseFromCompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "user-service", contextId = "userClient", configuration =  UserFeignLogger.class,path = "/api", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/users/company/{companyId}")
    List<UserResponseFromCompanyDto> getUsersByCompany(@PathVariable Long companyId); //во избежание зацикливания, особая форма UserDTO
    @DeleteMapping("/users/company/{companyId}")
    void deleteUsersByCompany(@PathVariable Long companyId);
}
