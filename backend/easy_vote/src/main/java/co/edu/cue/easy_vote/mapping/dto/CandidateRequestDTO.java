package co.edu.cue.easy_vote.mapping.dto;

import co.edu.cue.easy_vote.domain.enums.Grade;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CandidateRequestDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("profileImage") String profileImage,
    @JsonProperty("grade") Grade grade,
    @JsonProperty("studyingDay") StudyingDay studyingDay
){
}
