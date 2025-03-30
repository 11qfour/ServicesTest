package com.example.service_test_task.Service;

import com.example.service_test_task.Client.UserClient;
import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import com.example.service_test_task.Entity.Company;
import com.example.service_test_task.Exception.ResourceNotFoundException;
import com.example.service_test_task.Mapper.CompanyMapper;
import com.example.service_test_task.Repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository COMPANY_REPOSITORY;
    private final CompanyMapper COMPANY_MAPPER;

    private final UserClient USER_CLIENT;

    public CompanyResponseDto getCompanyById(Long companyId) {
        Company company = COMPANY_REPOSITORY.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        CompanyResponseDto companyDto = COMPANY_MAPPER.toDto(company);
        companyDto.setEmployees(USER_CLIENT.getUsersByCompany(companyId));
        return companyDto;
    }

    public List<CompanyResponseDto> getAllCompanies(){
        return COMPANY_REPOSITORY.findAll()
                .stream()
                .map(company->{
                    CompanyResponseDto dto = COMPANY_MAPPER.toDto(company);
                    dto.setEmployees(USER_CLIENT.getUsersByCompany(company.getId()));
                    return dto;
                }).collect(Collectors.toList());
    }

    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto){
        Company company = COMPANY_REPOSITORY.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        COMPANY_MAPPER.updateCompanyFromDto(companyRequestDto, company);
        Company updatedCompany = COMPANY_REPOSITORY.save(company);

        return COMPANY_MAPPER.toDto(updatedCompany);
    }

    public void deleteCompany(Long companyId){
        if (!COMPANY_REPOSITORY.existsById(companyId)){
            throw new ResourceNotFoundException("Company not found");
        }
        // Удаление сотрудников
        USER_CLIENT.deleteUsersByCompany(companyId);

        COMPANY_REPOSITORY.deleteById(companyId);
    }

    public CompanyResponseDto create(CompanyRequestDto companyRequestDto){
        Company company = COMPANY_MAPPER.toEntity(companyRequestDto);
        var newCompany = COMPANY_REPOSITORY.save(company);
        return COMPANY_MAPPER.toDto(newCompany);
    }
}
