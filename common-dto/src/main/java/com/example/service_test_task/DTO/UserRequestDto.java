package com.example.service_test_task.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long companyId;
}
