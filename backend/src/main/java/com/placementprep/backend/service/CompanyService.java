package com.placementprep.backend.service;

import com.placementprep.backend.entity.Company;
import com.placementprep.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public Company updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = getCompanyById(id);

        existingCompany.setCompanyName(updatedCompany.getCompanyName());
        existingCompany.setLocation(updatedCompany.getLocation());
        existingCompany.setPackageOffered(updatedCompany.getPackageOffered());
        existingCompany.setEligibilityCgpa(updatedCompany.getEligibilityCgpa());
        existingCompany.setRoleOffered(updatedCompany.getRoleOffered());

        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        Company existingCompany = getCompanyById(id);
        companyRepository.delete(existingCompany);
    }
}