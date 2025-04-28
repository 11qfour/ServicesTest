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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public interface CompanyService {
    CompanyResponseDto getCompanyById(Long companyId);
    List<CompanyResponseDto> getAllCompanies(int page, int size);
    CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto);
    void deleteCompany(Long companyId);
    CompanyResponseDto create(CompanyRequestDto companyRequestDto);
}


