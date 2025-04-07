package com.example.service_test_task.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponseDto {
    private Long id;
    private String name;
    private Long budget;
    private List<UserResponseFromCompanyDto> employees;
}
