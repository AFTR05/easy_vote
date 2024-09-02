package co.edu.cue.easy_vote.mapping.dto;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Voter;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VoteRequestDTO (
        @JsonProperty("candidate_id") Long candidate_id,
        @JsonProperty("voter_nid") String voter_nid
){
}
