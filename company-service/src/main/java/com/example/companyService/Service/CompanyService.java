package com.example.companyService.Service;


import com.example.companyService.Client.UserClient;
import com.example.companyService.Client.UserFeignLogger;
import com.example.companyService.Entity.Company;
import com.example.companyService.Exception.ResourceNotFoundException;
import com.example.companyService.Mapper.CompanyMapper;
import com.example.companyService.Repository.CompanyRepository;
import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserFeignLogger.class);
    private final UserClient userClient;

    public CompanyResponseDto getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        CompanyResponseDto companyDto = companyMapper.toDto(company);
        try {
            companyDto.setEmployees(userClient.getUsersByCompany(companyId));
        } catch (Exception e) {
            logger.error("Failed to fetch users from user-service for company {}", companyId, e);
            companyDto.setEmployees(Collections.emptyList()); // fallback
        }
        return companyDto;
    }

    public List<CompanyResponseDto> getAllCompanies(){
        return companyRepository.findAll()
                .stream()
                .map(company->{
                    CompanyResponseDto dto = companyMapper.toDto(company);
                    dto.setEmployees(userClient.getUsersByCompany(company.getId()));
                    return dto;
                }).collect(Collectors.toList());
    }

    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        companyMapper.updateCompanyFromDto(companyRequestDto, company);
        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toDto(updatedCompany);
    }

    public void deleteCompany(Long companyId){
        if (!companyRepository.existsById(companyId)){
            throw new ResourceNotFoundException("Company not found");
        }

        try {
            userClient.deleteUsersByCompany(companyId);
        } catch (Exception e) {
            logger.warn("Could not delete users from user-service for company {}", companyId, e);
        }

        companyRepository.deleteById(companyId);
    }

    public CompanyResponseDto create(CompanyRequestDto companyRequestDto){
        if((companyRequestDto.getBudget()==null)||(companyRequestDto.getName()==null)){
            throw new ResourceNotFoundException("Any field's will be empty");
        }
        Company company = companyMapper.toEntity(companyRequestDto);
        var newCompany = companyRepository.save(company);
        return companyMapper.toDto(newCompany);
    }
}
