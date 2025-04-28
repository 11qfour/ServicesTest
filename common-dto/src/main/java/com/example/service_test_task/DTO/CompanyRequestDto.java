package com.example.service_test_task.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull (message = "Budget must be provided")
    @PositiveOrZero (message = "Budget must be zero or positive")
    private Long budget;
}
