package com.example.service_test_task.DTO;

import com.example.service_test_task.Entity.Company;
import com.example.service_test_task.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
    private String name;
    private Long budget;
}
