package co.edu.cue.easy_vote.mapping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AdministratorRequestDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
) {
}
