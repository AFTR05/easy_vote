package co.edu.cue.easy_vote.mapping.dto;

import lombok.Data;

@Data
public class AdministratorAuthDTO {
    private AdministratorDTO administratorDTO;
    private AuthenticationResponseDTO authenticationResponseDTO;
    private StatusDTO statusDTO;
}
