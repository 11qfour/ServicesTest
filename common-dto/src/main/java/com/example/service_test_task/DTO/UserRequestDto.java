package com.example.service_test_task.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "FirstName cannot be blank")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-]+$", message = "FirstName must contain only letters")
    private String firstName;
    @NotBlank(message = "LastName cannot be blank")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s-]+$", message = "LastName must contain only letters")
    private String lastName;
    @NotBlank(message = "PhoneNumber cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\s-]{10,}$", message = "Invalid phone number format")
    private String phoneNumber;
    @NotNull(message = "Budget must be provided")
    @Positive(message = "CompanyId must be positive")
    private Long companyId;
}
