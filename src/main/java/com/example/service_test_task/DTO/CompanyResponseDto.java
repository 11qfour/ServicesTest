package com.example.service_test_task.DTO;

import com.example.service_test_task.Entity.Company;
import com.example.service_test_task.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto {
    private Long id;
    private String name;
    private Long budget;
    private List<UserResponseDto> employees;
}
