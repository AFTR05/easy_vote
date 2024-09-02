package co.edu.cue.easy_vote.service;


import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import co.edu.cue.easy_vote.mapping.dto.VoterDTO;
import co.edu.cue.easy_vote.mapping.dto.VoterRequestDTO;

import java.util.List;
import java.util.Optional;

public interface VoterService {
    VoterDTO createVoter(VoterRequestDTO voterRequestDTO);

    List<VoterDTO> getVoters();

    VoterDTO getVoter(String nid);
    Optional<VoterDTO> getVoterByNID(String nid);

}
