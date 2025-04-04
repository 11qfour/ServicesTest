package com.example.companyService.Mapper;
import com.example.companyService.Entity.Company;
import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    Company toEntity(CompanyRequestDto companyRequestDto);

    @Mapping(target = "employees", ignore = true) // Игнорируем рекурсивный список пользователей
    CompanyResponseDto toDto(Company company);

    List<CompanyResponseDto> toDtoList(List<Company> companies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompanyFromDto(CompanyRequestDto dto, @MappingTarget Company company);
}