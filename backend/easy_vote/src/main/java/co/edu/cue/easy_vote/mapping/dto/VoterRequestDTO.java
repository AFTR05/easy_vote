package co.edu.cue.easy_vote.mapping.dto;

import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VoterRequestDTO (
        @JsonProperty("nid") String nid,
        @JsonProperty("studyingDay")  StudyingDay studyingDay
){

}
