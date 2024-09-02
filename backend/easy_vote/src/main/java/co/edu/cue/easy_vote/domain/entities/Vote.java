package co.edu.cue.easy_vote.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Candidate candidate;

    @OneToOne(cascade = CascadeType.ALL)
    private Voter voter;
    private Boolean data_state;

}
