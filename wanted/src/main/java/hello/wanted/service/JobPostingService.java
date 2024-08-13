package hello.wanted.service;

import hello.wanted.dto.CreateJobPostingRequest;
import hello.wanted.dto.JobPostingResponse;
import hello.wanted.dto.UpdateJobPostingRequest;
import hello.wanted.entity.Company;
import hello.wanted.entity.JobPosting;
import hello.wanted.repository.CompanyRepository;
import hello.wanted.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public List<JobPostingResponse> findList() {
        List<JobPosting> jobPostingList = jobPostingRepository.findAll();
        log.info("jobPostingList: {}", jobPostingList);

        List<JobPostingResponse> dtos = jobPostingList.stream()
                .map(entity -> JobPostingResponse.createDto(entity))
                .collect(Collectors.toList());

        log.info("dtos: {}", dtos);
        return dtos;
    }

}
