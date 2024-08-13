package hello.wanted.controller;

import hello.wanted.dto.*;
import hello.wanted.entity.JobPosting;
import hello.wanted.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobPostingController {

    private final JobPostingService jobPostingService;

    // 채용 공고 조회
    @GetMapping("/api/job-postings")
    public ResponseEntity<List<JobPostingResponse>> list() {
        try {
            List<JobPostingResponse> list = jobPostingService.findList();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            log.error("조회 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 채용 공고 등록
    @PostMapping("/api/job-postings")
    public ResponseEntity<CreateJobPostingResponse> createJobPosting(@RequestBody CreateJobPostingRequest request) {
        try {
            JobPosting jobPosting = jobPostingService.post(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateJobPostingResponse("등록 완료"));
        } catch (Exception e) {
            log.error("등록 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateJobPostingResponse("등록 실패"));
        }
    }

    // 채용 공고 수정
    @PutMapping("/api/job-postings/{jobPostingId}")
    public ResponseEntity<UpdateJobPostingResponse> updateJobPosting(@PathVariable("jobPostingId") Long id, @RequestBody UpdateJobPostingRequest request) {
        try {
            JobPosting updated = jobPostingService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(new UpdateJobPostingResponse("채용 공고 수정 완료"));
        } catch (Exception e) {
            log.error("수정 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UpdateJobPostingResponse("채용 공고가 존재하지 않습니다."));
        }
    }

}
