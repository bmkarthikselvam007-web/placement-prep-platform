package com.placementprep.backend.service;

import com.placementprep.backend.entity.Company;
import com.placementprep.backend.entity.Job;
import com.placementprep.backend.repository.CompanyRepository;
import com.placementprep.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job saveJob(Job job) {
        Long companyId = job.getCompany().getId();

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        job.setCompany(company);

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public Job updateJob(Long id, Job updatedJob) {
        Job existingJob = getJobById(id);

        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setPackageOffered(updatedJob.getPackageOffered());
        existingJob.setEligibilityCgpa(updatedJob.getEligibilityCgpa());
        existingJob.setLastDateToApply(updatedJob.getLastDateToApply());

        Long companyId = updatedJob.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        existingJob.setCompany(company);

        return jobRepository.save(existingJob);
    }

    public void deleteJob(Long id) {
        Job existingJob = getJobById(id);
        jobRepository.delete(existingJob);
    }
}