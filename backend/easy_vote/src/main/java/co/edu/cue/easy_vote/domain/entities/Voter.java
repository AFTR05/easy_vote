package co.edu.cue.easy_vote.domain.entities;


import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "voter")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nid;

    @Enumerated(EnumType.STRING)
    private StudyingDay studyingDay;
    private Boolean data_state;

}
