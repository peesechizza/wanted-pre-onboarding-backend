package hello.wanted.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateJobPostingRequest {

    private Long companyId;
    private String position;
    private int reward;
    private String description;
    private String skills;

}
