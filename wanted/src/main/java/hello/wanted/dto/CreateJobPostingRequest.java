package hello.wanted.dto;

import hello.wanted.entity.JobPosting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateJobPostingRequest {

    private Long companyId;
    private String position;
    private int reward;
    private String description;
    private String skills;

}
