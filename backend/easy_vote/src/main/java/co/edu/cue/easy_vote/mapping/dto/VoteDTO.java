package co.edu.cue.easy_vote.mapping.dto;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Voter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public record VoteDTO (
        @JsonProperty("candidate") Candidate candidate,
        @JsonProperty("voter") Voter voter
){
}
