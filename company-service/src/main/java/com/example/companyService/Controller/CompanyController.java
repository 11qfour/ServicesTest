package com.example.companyService.Controller;

import com.example.companyService.Service.CompanyService;
import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(){
        log.info("Get all companies");
        List<CompanyResponseDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long companyId){
        log.info("Got company for user by ID: {}", companyId);
        CompanyResponseDto company = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto){
        log.info("Created company by name: {} with value of budget: {}",
                companyRequestDto.getName(),companyRequestDto.getBudget());
        CompanyResponseDto createdCompany = companyService.create(companyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> updateCompany(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyRequestDto companyRequestDto) {
        log.info("Changed company by ID: {} to name: {} and value of budget: {}",
                companyId,companyRequestDto.getName(),companyRequestDto.getBudget());
        CompanyResponseDto updatedCompany = companyService.updateCompany(companyId, companyRequestDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        log.info("Deleted company by ID: {}", companyId);
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}
