package com.example.service_test_task.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponseFromUserDto {
    private Long id;
    private String name;
    private Long budget;
}
