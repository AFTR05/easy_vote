package co.edu.cue.easy_vote.mapping.dto;

import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VoterDTO (
        @JsonProperty("nid")  String nid,
        @JsonProperty("studyingDay")  StudyingDay studyingDay
){

}
