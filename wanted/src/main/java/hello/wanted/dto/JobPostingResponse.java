package hello.wanted.dto;

import hello.wanted.entity.JobPosting;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JobPostingResponse {

    private Long id;
    private String companyName;
    private String nation;
    private String country;
    private String position;
    private int reward;
    private String skills;
    private String description;


    public static JobPostingResponse createDto(JobPosting jobPosting) {
        return new JobPostingResponse(
            jobPosting.getId(),
                jobPosting.getCompany().getName(),
                jobPosting.getCompany().getNation(),
                jobPosting.getCompany().getCountry(),
                jobPosting.getPosition(),
                jobPosting.getReward(),
                jobPosting.getSkills(),
                jobPosting.getDescription()
        );
    }

}
