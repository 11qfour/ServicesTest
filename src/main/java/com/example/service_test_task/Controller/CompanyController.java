package com.example.service_test_task.Controller;

import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.CompanyResponseDto;
import com.example.service_test_task.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService COMPANY_SERVICE;

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(){
        List<CompanyResponseDto> companies = COMPANY_SERVICE.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long companyId){
        CompanyResponseDto company = COMPANY_SERVICE.getCompanyById(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto companyRequestDto){
        CompanyResponseDto createdCompany = COMPANY_SERVICE.create(companyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> updateCompany(
            @PathVariable Long companyId,
            @RequestBody CompanyRequestDto companyRequestDto) {
        CompanyResponseDto updatedCompany = COMPANY_SERVICE.updateCompany(companyId, companyRequestDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        COMPANY_SERVICE.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}
