package co.edu.cue.easy_vote.domain.entities;


import co.edu.cue.easy_vote.domain.enums.Grade;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private StudyingDay studyingDay;

    private Boolean data_state;
}
