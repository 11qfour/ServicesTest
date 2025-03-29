package com.example.service_test_task.Mapper;

import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import com.example.service_test_task.Entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    Company toEntity(CompanyRequestDto companyRequestDto);

    @Mapping(target = "employees", ignore = true) // Игнорируем рекурсивный список пользователей
    CompanyResponseDto toDto(Company company);

    List<CompanyResponseDto> toDtoList(List<Company> companies);
}
