package hello.wanted.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String position;
    private int reward;
    private String skills;
    private String description;

    public JobPosting(Company company, String position, int reward, String skills, String description) {
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.skills = skills;
        this.description = description;
    }

    public void updateDetails(String position, int reward, String skills, String description) {
        this.position = position;
        this.reward = reward;
        this.skills = skills;
        this.description = description;
    }
}
