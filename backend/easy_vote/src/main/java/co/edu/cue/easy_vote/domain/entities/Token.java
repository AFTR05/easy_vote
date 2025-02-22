package co.edu.cue.easy_vote.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "token", indexes = @Index(columnList = "idToken"))
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idToken = 0;

    @Column(name = "token")
    private String token;

    @Column(name = "is_log_out", nullable = false)
    private Boolean isLogOut;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

}
