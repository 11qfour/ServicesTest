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
public class CompanyResponseDto {
    private Long id;
    private String name;
    private Long budget;
    private List<User> employees;

    public CompanyResponseDto(Company company, List<User> employees) {
        this.id = company.getId();
        this.name = company.getName();
        this.budget = company.getBudget();
        this.employees = employees;
    }
}
