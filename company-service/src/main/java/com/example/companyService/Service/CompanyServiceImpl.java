package com.example.companyService.Service;

import com.example.companyService.Client.UserClient;
import com.example.companyService.Entity.Company;
import com.example.companyService.Exception.ResourceNotFoundException;
import com.example.companyService.Mapper.CompanyMapper;
import com.example.companyService.Repository.CompanyRepository;
import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserClient userClient;

    public CompanyResponseDto getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        CompanyResponseDto companyDto = companyMapper.toDto(company);

        try {
            companyDto.setEmployees(userClient.getUsersByCompany(companyId));
        } catch (Exception e) {
            log.error("Failed to fetch users from user-service for company {}: {}", companyId, e.getMessage());
            companyDto.setEmployees(Collections.emptyList()); // fallback
        }
        log.info("Returning company by id={}, result: {}", companyId, companyDto);
        return companyDto;
    }

    public List<CompanyResponseDto> getAllCompanies(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Company> companies = companyRepository.findAll(pageable).getContent();
        List<CompanyResponseDto> result = companyMapper.toDtoList(companies);

        for (CompanyResponseDto dto : result) {
            try {
                dto.setEmployees(userClient.getUsersByCompany(dto.getId()));
            } catch (Exception e) {
                log.error("Failed to fetch employees for company {}", dto.getId(), e);
                dto.setEmployees(Collections.emptyList());
            }
        }
        log.info("Returning {} companies", result.size());
        return result;
    }

    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        companyMapper.updateCompanyFromDto(companyRequestDto, company);
        Company updatedCompany = companyRepository.save(company);
        CompanyResponseDto response = companyMapper.toDto(updatedCompany);
        log.info("Updated company id={}, new data: {}", companyId, response);
        return response;
    }

    public void deleteCompany(Long companyId){
        if (!companyRepository.existsById(companyId)){
            throw new ResourceNotFoundException("Company not found");
        }

        try {
            userClient.deleteUsersByCompany(companyId);
        } catch (Exception e) {
            log.warn("Could not delete users from user-service for company {}", companyId, e);
        }
        log.info("Deleted company id={}", companyId);
        companyRepository.deleteById(companyId);
    }

    public CompanyResponseDto create(CompanyRequestDto companyRequestDto){
        if((companyRequestDto.getBudget()==null)||(companyRequestDto.getName()==null)){
            throw new ResourceNotFoundException("Any field's will be empty");
        }
        Company company = companyMapper.toEntity(companyRequestDto);
        var newCompany = companyRepository.save(company);
        CompanyResponseDto response = companyMapper.toDto(newCompany);
        log.info("Created new company with id={}, name={}", response.getId(), response.getName());
        return response;
    }
}
