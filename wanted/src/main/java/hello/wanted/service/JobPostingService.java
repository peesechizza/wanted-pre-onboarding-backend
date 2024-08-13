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

    // 목록 조회
    public List<JobPostingResponse> findList() {
        List<JobPosting> jobPostingList = jobPostingRepository.findAll();
        log.info("jobPostingList: {}", jobPostingList);

        List<JobPostingResponse> dtos = jobPostingList.stream()
                .map(entity -> JobPostingResponse.createDto(entity))
                .collect(Collectors.toList());

        log.info("dtos: {}", dtos);
        return dtos;
    }

    // 게시글 작성
    @Transactional
    public JobPosting post(CreateJobPostingRequest request) {
        JobPosting entity = toEntity(request);
        return jobPostingRepository.save(entity);
    }

    // 게시글 수정
    @Transactional
    public JobPosting update(Long id, UpdateJobPostingRequest request) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID와 일치하는 게시글이 없습니다: " + id));
        if (jobPosting.getCompany().getId().equals(request.getCompanyId())) {
            jobPosting.updateDetails(request.getPosition(), request.getReward(), request.getSkills(), request.getDescription());
            return jobPostingRepository.save(jobPosting);
        } else {
            throw new IllegalArgumentException("게시글의 회사 ID가 요청된 회사 ID와 일치하지 않습니다.");
        }
    }

    // dto -> 엔티티 변환
    private JobPosting toEntity(CreateJobPostingRequest request) {
        Company company =
                companyRepository.findById(request.getCompanyId())
                        .orElseThrow(() -> new RuntimeException("id와 일치하는 Company 없음: " + request.getCompanyId()));
        return new JobPosting(company, request.getPosition(), request.getReward(), request.getSkills(), request.getDescription());
    }
}
