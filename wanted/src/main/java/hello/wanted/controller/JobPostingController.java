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

}
