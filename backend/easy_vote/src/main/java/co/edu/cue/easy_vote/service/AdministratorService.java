package co.edu.cue.easy_vote.service;


import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.mapping.dto.AdministratorAuthDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorRequestDTO;

import java.util.List;

public interface AdministratorService {

    AdministratorAuthDTO createAdmin(AdministratorRequestDTO administratorRequestDTO);

    List<AdministratorDTO> getAdministrators();

    AdministratorDTO getAdministratorById(Long id);
}
